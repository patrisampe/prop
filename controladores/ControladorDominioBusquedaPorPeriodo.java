package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import time.*;
import utiles.Conjunto;
import dominio.*;
import dominio.algoritmos.*;

/**
 * Controlador encargado de calcular Afinidades entre los diputados del dominio. Haciendo uso de todos los datos disponibles en el dominio.
 * Este controlador genera la información necesaria para crear Resultados de Búsqueda por Periodo.
 * @author Yoel Cabo
 *
 */
public class ControladorDominioBusquedaPorPeriodo extends
		ControladorDominioBusqueda {
	
	/**
	 * Realiza una nueva búsqueda usando el criterio estándar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en algún momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param Algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param Periodo Periodo inclusivo de tiempo.
	 * @param ImportanciaModificada Modificaciones en la importáncia predefinida de los Eventos.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la búsqueda.
	 */ 
	public Conjunto<GrupoAfinPorPeriodo> NuevaBusquedaStandard(TipoAlgoritmo algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(Periodo);
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); 
		DateInterval PeriodoVotaciones = new DateInterval(cLeg.getFechaInicio(cLeg.getID(Periodo.getInicio())), cLeg.getFechaFinal(cLeg.getID(Periodo.getFin())));
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(PeriodoVotaciones); 
		Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva búsqueda por periodo usando solamente el partido político de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la búsqueda.
	 */
	public Conjunto<GrupoAfinPorPeriodo> NuevaBusquedaPartidoPolitico(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		Graf G = construirGrafoPP(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva búsqueda por periodo usando solamente el estado de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la búsqueda.
	 */
	public Conjunto<GrupoAfinPorPeriodo> NuevaBusquedaEstado(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		Graf G = construirGrafoEstado(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}
	
	/**
	 * Realiza una nueva búsqueda por periodo usando solamente el parecido en los nombres de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la búsqueda.
	 */
	public Conjunto<GrupoAfinPorPeriodo> NuevaBusquedaNombresParecidos(TipoAlgoritmo algoritmo, DateInterval periodo, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(periodo);
		Graf G = construirGrafoNombresParecidos(idDiputados);
		return ejecutarYretornar(G,algoritmo,porcentaje);
	}


	private Conjunto<GrupoAfinPorPeriodo> ejecutarYretornar(Graf g, TipoAlgoritmo algoritmo, Integer porcentaje) {
		HashSet<HashSet<String> > hs = ejecutar(g,algoritmo,porcentaje);
		
		Conjunto<GrupoAfinPorPeriodo> s = new Conjunto<GrupoAfinPorPeriodo>(GrupoAfinPorPeriodo.class);
		Integer idgrupo = 1;
		for (Set<String> Comunidad : hs) {
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo(idgrupo++);
			for (String Diputado : Comunidad) {
				ga.addDiputado(Diputado);
			}
			s.add(idgrupo,ga);
		}
		return s;
	}


}
