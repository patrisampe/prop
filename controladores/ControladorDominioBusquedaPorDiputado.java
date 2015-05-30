package controladores;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
	
	private Integer lapso;
	
	/**
	 * Inicializa una nueva búsqueda por Diputado .
	 * @param lapso
	 */
	public void NuevaBusqueda(Integer lapso) {
		this.lapso = lapso;
		sg = new LinkedHashSet<Graf> ();
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return;
			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			if (catchError(cLeg)) return;
			sg.add(new Graf((HashSet<String>)prepararDiputados(legislaturaInicial,legislaturaFinal)));
			if (this.hasError()) return;
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
		Iterator<Graf> sgIt = sg.iterator();
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return;
			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval(cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			if (catchError(cLeg)) return;
			addCriterioStandard(sgIt.next(), prepararImportancias(ImportanciaModificada), prepararEventos(Periodo), prepararVotaciones(Periodo), ponderacion);
		}
	}
	
	/**
	 * Añade pesos por el Partido Político de los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioPartidoPolitico(Double ponderacion) {
		for (Graf g : sg) {
			addCriterioPartidoPolitico(g, ponderacion);
		}
	}


	/**
	 * Añade pesos por el Estado de los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioEstado(Double ponderacion) {
		for (Graf g : sg) {
			addCriterioEstado(g, ponderacion);
		}
	}

	/**
	 * Añade pesos por parecido de nombre entre los diputados
	 * @param ponderacion del criterio
	 */
	public void addCriterioNombresParecidos(Double ponderacion) {
		for (Graf g : sg) {
			addCriterioNombresParecidos(g, ponderacion);
		}
	}
	
	/**
	 * Ejecuta la búsqueda por diputado con los Grafos construidos y el lapso antes especificado.
	 * @param algoritmo Algoritmo de la búsqueda
	 * @param porcentaje Indice de afinidad.
	 */
	public void ejecutar(TipoAlgoritmo algoritmo, Integer porcentaje, String DiputadoRelevante) {
		ConjuntoGrupoAfin s= new ConjuntoGrupoAfin();
		Integer idgrupo = 1;
		Iterator<Graf> sgIt = sg.iterator();
		for (Iterator<Integer> It = cLeg.getIDs().iterator();It.hasNext();) {
			if (catchError(cLeg)) return;
			Integer legislaturaInicial = It.next();
			Integer legislaturaFinal = null;
			if(lapso == 1) legislaturaFinal = legislaturaInicial;
			else for (Integer i = 1; i < lapso && It.hasNext(); ++i) legislaturaFinal = It.next();
			DateInterval Periodo = new DateInterval(cLeg.getFechaInicio(legislaturaInicial), cLeg.getFechaFinal(legislaturaFinal));
			GrupoAfinPorDiputado ga = new GrupoAfinPorDiputado(++idgrupo, Periodo.getInicio(), Periodo.getFin());
			if (this.hasError()) return;
			ejecutar(sgIt.next(),ga,algoritmo, porcentaje, DiputadoRelevante);
			s.add(ga);
		}
		
		result = s;
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
