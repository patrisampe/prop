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
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(PeriodoVotaciones); 
		Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		
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
		Integer idgrupo = 1;
		for (Set<String> Comunidad : hs) {
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo(idgrupo++);
			for (String Diputado : Comunidad) {
				ga.addDiputado(Diputado);
			}
			s.addPorPeriodo(ga);
		}
		return s;
	}


}
