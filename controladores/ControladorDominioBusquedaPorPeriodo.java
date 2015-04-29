package controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.*;
import dominio.*;

public class ControladorDominioBusquedaPorPeriodo extends
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
	public Set<GrupoAfin> NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada) {
		Set<String> idDiputados = prepararDiputados(Periodo);
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); //En el esquema pone list
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(Periodo); //Divide las votaciones en conjuntos de diputados que votan lo mismo.
		//TODO
		return null;
	}


}
