package controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.*;
import dominio.*;
import dominio.algoritmos.Graf;
import dominio.algoritmos.GrafLouvain;
import dominio.algoritmos.Louvain;

public class ControladorDominioBusquedaPorPeriodo extends
		ControladorDominioBusqueda {
	
	/**
	 * Realiza una nueva b�squeda usando el criterio est�ndar: Teniendo en cuenta Eventos y Votaciones, 
	 * para los diputados activos en alg�n momento del periodo especificado, Eventos dentro del periodo y Votaciones
	 * dentro de las legislaturas que incluyan el periodo.
	 * @param Algoritmo Tipo de algoritmo a ejecutar, puede ser CliquePercolation, GirvanNewmann o Louvain.
	 * @param Periodo Periodo inclusivo de tiempo.
	 * @param ImportanciaModificada Modificaciones en la import�ncia predefinida de los Eventos.
	 * @return Conjunto de Grupos Afines resultantes de la b�squeda.
	 */ 
	public Set<GrupoAfinPorPeriodo> NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada, Integer porcentaje) {
		Set<String> idDiputados = prepararDiputados(Periodo);
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); //En el esquema pone list
		Map<String, Set<String> > votacionesSimp = prepararVotaciones(Periodo); //Divide las votaciones en conjuntos de diputados que votan lo mismo.
		
		Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		
		return ejecutar(G,Algoritmo,porcentaje);
	}

	protected Set<GrupoAfinPorPeriodo> ejecutar(Graf g, TipoAlgoritmo algoritmo, Integer porcentaje) {
		HashSet<HashSet<String> > hs = new HashSet<HashSet<String>>();
		if (algoritmo == TipoAlgoritmo.CliquePercolation) {
			hs = Clique.executa(g,porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.GirvanNewmann) {
			hs = Newman.executa((new GrafNewman(g),porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.Louvain) {
			GrafLouvain gl = new GrafLouvain(g);
			hs = Louvain.executa(new GrafLouvain(g), porcentaje);
		}
		
		Set<GrupoAfin> s = new HashSet<GrupoAfin>();
		
		for (Set<String> Comunidad : hs) {
			GrupoAfinPorPeriodo ga = new GrupoAfinPorPeriodo();
			for (String Diputado : Comunidad) {
				ga.addDiputado(Diputado);
			}
			s.add(ga);
		}
		return s;
	}
	


}
