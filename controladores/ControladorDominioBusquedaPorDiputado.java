package controladores;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import time.*;
import utiles.ConjuntoGrupoAfin;
import dominio.GrupoAfinPorDiputado;
import dominio.TipoAlgoritmo;
import dominio.algoritmos.Graf;

/**
 * Controlador encargado de calcular Afinidades entre los diputados del dominio. Haciendo uso de todos los datos disponibles en el dominio.
 * Este controlador genera la informaci�n necesaria para crear Resultados de B�squeda por Diputado
 * @author Yoel Cabo
 *
 */
public class ControladorDominioBusquedaPorDiputado extends
		ControladorDominioBusqueda {
	
	private Set<Graf> sg;
	
	/**
	 * Inicializa una nueva búsqueda por Diputado.
	 * @param lapso
	 */
	public void NuevaBusqueda(Integer lapso) {
		sg = new TreeSet<Graf> ();
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return;
			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval( cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			if (catchError(cLeg)) return;
			sg.add(new Graf((HashSet<String>)prepararDiputados(legislaturaInicial,legislaturaFinal)));
			if (this.hasError()) return;
		}
	}
	
	public void addCriterioStandard(Map<String, Integer> ImportanciaModificada, Double ponderacion) {
		DateInterval PeriodoVotaciones = new DateInterval(cLeg.getFechaInicio(cLeg.getID(Periodo.getInicio())), cLeg.getFechaFinal(cLeg.getID(Periodo.getFin())));
		if (catchError(cLeg)) return;
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			addCriterioStandard(G, prepararImportancias(ImportanciaModificada), prepararEventos(Periodo), prepararVotaciones(PeriodoVotaciones), ponderacion);
		}
	}
	public void addCriterioPartidoPolitico(Double ponderacion) {
		addCriterioPartidoPolitico(G, ponderacion);
	}


	public void addCriterioEstado(Double ponderacion) {
		addCriterioEstado(G, ponderacion);
	}

	public void addCriterioNombresParecidos(Double ponderacion) {
		addCriterioNombresParecidos(G, ponderacion);
	}
	
	/**
	 * Realiza una nueva b�squeda durante toda la historia fij�ndose en la evoluci�n de un diputado concreto
	 * usando el criterio est�ndar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en alg�n momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param Algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param Lapso Numero de legislaturas del lapso de tiempo, ha de ser mayor a 0.
	 * @param ImportanciaModificada Modificaciones en la import�ncia predefinida de los Eventos.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @param DiputadoRelevante Diputado cuya evoluci�n buscamos.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, Integer Lapso, Map<String, Integer> ImportanciaModificada, Integer porcentaje, String DiputadoRelevante) {
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		if (this.hasError()) return null;
		Integer idgrupo = 1;

		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		
 			Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); 
 			if (this.hasError()) return null;

			Map<String, Set<String> > votacionesSimp = prepararVotaciones(Periodo); //Divide las votaciones en conjuntos de diputados que votan lo mismo.
			if (this.hasError()) return null;

			Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
			if (this.hasError()) return null;

			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, Periodo.getInicio(), Periodo.getFin());
			if (this.hasError()) return null;

			ejecutar(G,ga,Algoritmo, porcentaje, DiputadoRelevante);
			s.add(ga);
		}
		
		return s;
		
	}

	/**
	 * Realiza una nueva b�squeda durante toda la historia fij�ndose en la evoluci�n de un diputado concreto
	 * usando solamente el Estado de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param lapso Numero de legislaturas del lapso de tiempo, ha de ser mayor a 0.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 * @param diputadoRelevante Diputado cuya evoluci�n buscamos.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaEstado(TipoAlgoritmo algoritmo, Integer lapso, Integer porcentaje, String diputadoRelevante) {
		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		Integer idgrupo = 1;
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return null;

			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval( cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			if (catchError(cLeg)) return null;

			Set<String> idDiputados = prepararDiputados(legislaturaInicial,legislaturaFinal);
			if (this.hasError()) return null;

			Graf G = construirGrafoEstado(idDiputados);
			if (this.hasError()) return null;

			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, Periodo.getInicio(), Periodo.getFin());
			ejecutar(G,ga,algoritmo, porcentaje, diputadoRelevante);
			s.add(ga);
		}
		
		return s;
	}
	
	/**
	 * Realiza una nueva b�squeda durante toda la historia fij�ndose en la evoluci�n de un diputado concreto
	 * usando solamente el parecido en los nombres de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param lapso Numero de legislaturas del lapso de tiempo, ha de ser mayor a 0.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @param diputadoRelevante Diputado cuya evoluci�n buscamos.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaNombresParecidos(TipoAlgoritmo algoritmo, Integer lapso, Integer porcentaje, String diputadoRelevante) {
		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		Integer idgrupo = 1;
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return null;

			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval( cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			if (catchError(cLeg)) return null;

			Set<String> idDiputados = prepararDiputados(legislaturaInicial,legislaturaFinal);
			if (this.hasError()) return null;

			Graf G = construirGrafoNombresParecidos(idDiputados);
			if (this.hasError()) return null;

			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, Periodo.getInicio(), Periodo.getFin());
			ejecutar(G,ga,algoritmo, porcentaje, diputadoRelevante);
			s.add(ga);
		}
		
		return s;
	}
	
	/**
	 * Realiza una nueva b�squeda durante toda la historia fij�ndose en la evoluci�n de un diputado concreto
	 * usando solamente el Partido Pol�tico de los diputados.
	 * @param algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param lapso Numero de legislaturas del lapso de tiempo, ha de ser mayor a 0.
	 * @param periodo Periodo inclusivo de tiempo.
	 * @param porcentaje Porcentaje de afinidad deseado.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 * @param diputadoRelevante Diputado cuya evoluci�n buscamos.
	 */
	public ConjuntoGrupoAfin NuevaBusquedaPartidoPolitico(TipoAlgoritmo algoritmo, Integer lapso, Integer porcentaje, String diputadoRelevante) {
		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		Integer idgrupo = 1;
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return null;

			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval periodo = new DateInterval( cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			if (catchError(cLeg)) return null;

			Set<String> idDiputados = prepararDiputados(legislaturaInicial,legislaturaFinal);
			if (this.hasError()) return null;

			Graf G = construirGrafoPP(idDiputados);
			if (this.hasError()) return null;

			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, periodo.getInicio(), periodo.getFin());
			ejecutar(G,ga,algoritmo, porcentaje, diputadoRelevante);
			s.add(ga);
		}
		
		return s;
	}

	private void ejecutar(Graf g, GrupoAfinPorDiputado ga,
			TipoAlgoritmo algoritmo, Integer porcentaje,
			String diputadoRelevante) {
		HashSet<HashSet<String>> hs = ejecutar(g, algoritmo,porcentaje);
		//System.out.println("Comunidad:");
		for (HashSet<String> comunidad : hs) {
			//for (String Diputado : comunidad) System.out.println(Diputado);
			if (comunidad.contains(diputadoRelevante)) {
				//System.out.println("Lo encontr� "+diputadoRelevante);
				for (String diputado : comunidad) {
					ga.addDiputado(diputado);
				}
				break;
			}
		}
	}

}
