package controladores;

import java.util.HashSet;
import java.util.Map;

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
		ConjuntoGrupoAfin s = new ConjuntoGrupoAfin();
		Integer idgrupo = 1;
		for (HashSet<String> Comunidad : hs) {
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo(idgrupo++);
			for (String Diputado : Comunidad) {
				ga.addDiputado(Diputado);
			}
			s.add(ga);
		}
		result = s;
	}

}
