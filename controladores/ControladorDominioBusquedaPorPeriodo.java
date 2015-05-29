package controladores;

import java.util.HashSet;
import java.util.Map;

import time.*;
import utiles.ConjuntoDoble;
import dominio.*;
import dominio.algoritmos.*;

/**
 * Controlador encargado de calcular Afinidades entre los diputados del dominio. Haciendo uso de todos los datos disponibles en el dominio.
 * Este controlador genera la informaci�n necesaria para crear Resultados de B�squeda por Periodo.
 * @author Yoel Cabo
 *
 */
public class ControladorDominioBusquedaPorPeriodo extends
		ControladorDominioBusqueda {
	private Graf G;
	private DateInterval Periodo;
	/**
	 * Inicia una nueva búsqueda por periodo
	 * @param Periodo
	 */
	public void nuevaBusqueda(DateInterval Periodo) {
		if (Periodo.equals(DateInterval.NULL)) error.setCodiError(38);
		else {
			this.Periodo = Periodo;
			G = new Graf((HashSet<String>) prepararDiputados(Periodo));
		}
	}
	
	/**
	 * Añade pesos por el criterio estándar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en algún momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param ImportanciaModificada modificacion de la importancia de los eventos
	 * @param ponderacion del criterio	 
	 */
	public void addCriterioStandard(Map<String, Integer> ImportanciaModificada, Double ponderacion) {
		DateInterval PeriodoVotaciones = new DateInterval(cLeg.getFechaInicio(cLeg.getID(Periodo.getInicio())), cLeg.getFechaFinal(cLeg.getID(Periodo.getFin())));
		if (catchError(cLeg)) return;
		addCriterioStandard(G, prepararImportancias(ImportanciaModificada), prepararEventos(Periodo), prepararVotaciones(PeriodoVotaciones), ponderacion);
	}
	
	/**
	 * Añade pesos por el Partido Político de los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioPartidoPolitico(Double ponderacion) {
		addCriterioPartidoPolitico(G, ponderacion);
	}

	/**
	 * Añade pesos por el Estado de los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioEstado(Double ponderacion) {
		addCriterioEstado(G, ponderacion);
	}

	/**
	 * Añade pesos por parecido de nombre entre los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioNombresParecidos(Double ponderacion) {
		addCriterioNombresParecidos(G, ponderacion);
	}
	
	/**
	 * Ejecuta la búsqueda por perido con el Grafo construido y el periodo antes especificado.
	 * @param algoritmo Algoritmo de la búsqueda
	 * @param porcentaje Indice de afinidad.
	 */
	public void ejecutar(TipoAlgoritmo algoritmo, Integer porcentaje) {
		HashSet<HashSet<String> > hs = ejecutar(G,algoritmo,porcentaje);
		ConjuntoDoble<GrupoAfinPorDiputado,GrupoAfinPorPeriodo> s = new ConjuntoDoble(GrupoAfinPorDiputado.class,GrupoAfinPorPeriodo.class);
		Integer idgrupo = 1;
		for (HashSet<String> Comunidad : hs) {
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo(idgrupo++);
			for (String Diputado : Comunidad) {
				ga.addDiputado(Diputado);
			}
			s.addP(ga);
		}
		result = s;
	}

	
	

	/**
	 * Realiza una nueva b�squeda usando el criterio est�ndar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en alg�n momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param Algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param Periodo Periodo inclusivo de tiempo.
	 * @param ImportanciaModificada Modificaciones en la import�ncia predefinida de los Eventos.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */ 
	/*public ConjuntoGrupoAfin NuevaBusquedaStandard(TipoAlgoritmo algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(Periodo);
		if (this.hasError()) return null;
		
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		if (this.hasError()) return null;
		
		Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); 
		if (this.hasError()) return null;
		
		DateInterval PeriodoVotaciones = new DateInterval(cLeg.getFechaInicio(cLeg.getID(Periodo.getInicio())), cLeg.getFechaFinal(cLeg.getID(Periodo.getFin())));
		if (catchError(cLeg)) return null;
		
		//System.out.println(Periodo.toString()+ " --> "+ PeriodoVotaciones.toString() );
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(PeriodoVotaciones); 
		/*for (Set<String> tipoEvento : tiposYeventos.values()) {
			System.out.println(tipoEvento);
		}*//*
		Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		if (this.hasError()) return null;
		//System.out.println((new GrafLouvain(G)).sumaPesos());
		/*for (String nodo : G.getNodes()) {
			System.out.println("Adyacentes a "+nodo);
			for (String adj : G.getAdjacents(nodo)) System.out.print(adj+", ");
			System.out.println("");
		}*//*
			
		
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva b�squeda por periodo usando solamente el partido pol�tico de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	/*public ConjuntoGrupoAfin NuevaBusquedaPartidoPolitico(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		if (this.hasError()) return null;
		//for (String nodo : idDiputados) System.out.println(nodo);
		Graf G = construirGrafoPP(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva b�squeda por periodo usando solamente el estado de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	/*public ConjuntoGrupoAfin NuevaBusquedaEstado(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		if (this.hasError()) return null;
		Graf G = construirGrafoEstado(idDiputados);
		if (this.hasError()) return null;
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva b�squeda por periodo usando solamente el parecido en los nombres de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	/*public ConjuntoGrupoAfin NuevaBusquedaNombresParecidos(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		if (this.hasError()) return null;
		Graf G = construirGrafoNombresParecidos(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	*/




}
