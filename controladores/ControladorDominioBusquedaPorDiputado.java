package controladores;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import time.*;
import dominio.GrupoAfinPorDiputado;
import dominio.TipoAlgoritmo;
import dominio.algoritmos.Graf;

public class ControladorDominioBusquedaPorDiputado extends
		ControladorDominioBusqueda {
	
	/**
	 * Realiza una nueva búsqueda usando el criterio estándar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en algún momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param Algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param Periodo Periodo inclusivo de tiempo.
	 * @param ImportanciaModificada Modificaciones en la importáncia predefinida de los Eventos.
	 * @return Conjunto de Grupos Afines resultantes de la búsqueda.
	 */ 
	public Set<GrupoAfinPorDiputado> NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, Integer Lapso, Map<String, Integer> ImportanciaModificada, Integer porcentaje, String DiputadoRelevante) {
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		Set<GrupoAfinPorDiputado> s = new HashSet<GrupoAfinPorDiputado>();
		Integer idgrupo = 1;
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(Lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < Lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval( cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			Set<String> idDiputados = prepararDiputados(legislaturaInicial,legislaturaFinal);
 			Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); 
			Map<String, Set<String> > votacionesSimp = prepararVotaciones(Periodo); //Divide las votaciones en conjuntos de diputados que votan lo mismo.
			Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, Periodo.getInici(), Periodo.getFi());
			ejecutar(G,ga,Algoritmo, porcentaje, DiputadoRelevante);
			s.add(ga);
		}
		
		return s;
		
		
	}

	private void ejecutar(Graf g, GrupoAfinPorDiputado ga,
			TipoAlgoritmo algoritmo, Integer porcentaje,
			String diputadoRelevante) {
		HashSet<HashSet<String>> hs = ejecutar(g, algoritmo,porcentaje);
		for (HashSet<String> comunidad : hs) {
			if (comunidad.contains(diputadoRelevante)) {
				for (String diputado : comunidad) {
					ga.addDiputado(diputado);
				}
				break;
			}
		}
	}

}
