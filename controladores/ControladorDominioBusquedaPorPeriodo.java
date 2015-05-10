package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import time.*;
import utiles.ConjuntoGrupoAfin;
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
	public ConjuntoGrupoAfin NuevaBusquedaStandard(TipoAlgoritmo algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(Periodo);
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); 
		DateInterval PeriodoVotaciones = new DateInterval(cLeg.getFechaInicio(cLeg.getID(Periodo.getInicio())), cLeg.getFechaFinal(cLeg.getID(Periodo.getFin())));
		System.out.println(Periodo.toString()+ " --> "+ PeriodoVotaciones.toString() );
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(PeriodoVotaciones); 
		/*for (Set<String> tipoEvento : tiposYeventos.values()) {
			System.out.println(tipoEvento);
		}*/
		Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		System.out.println((new GrafLouvain(G)).sumaPesos());
		/*for (String nodo : G.getNodes()) {
			System.out.println("Adyacentes a "+nodo);
			for (String adj : G.getAdjacents(nodo)) System.out.print(adj+", ");
			System.out.println("");
		}*/
			
		
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva b�squeda por periodo usando solamente el partido pol�tico de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaPartidoPolitico(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
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
	public ConjuntoGrupoAfin NuevaBusquedaEstado(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		Graf G = construirGrafoEstado(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva b�squeda por periodo usando solamente el parecido en los nombres de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaNombresParecidos(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		Graf G = construirGrafoNombresParecidos(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}


	private ConjuntoGrupoAfin ejecutarYretornar(Graf g, TipoAlgoritmo algoritmo, Integer porcentaje) {
		HashSet<HashSet<String> > hs = ejecutar(g,algoritmo,porcentaje);
		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		//for (String nodo : g.getNodes()) System.out.println(nodo);
		Integer idgrupo = 1;
		for (HashSet<String> Comunidad : hs) {
			//System.out.println("hellooooooooooooow");
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo(idgrupo++);
			for (String Diputado : Comunidad) {
				//System.out.println(Diputado);
				ga.addDiputado(Diputado);
			}
			s.add(ga);
		}
		return s;
	}


}
