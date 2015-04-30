package controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.DateInterval;
import dominio.*;
import dominio.algoritmos.Graf;
import dominio.algoritmos.GrafLouvain;
import dominio.algoritmos.Louvain;

public abstract class ControladorDominioBusqueda {
	
	protected ControladorDominioDiputado cDip;
	protected ControladorDominioLegislatura cLeg;
	protected ControladorDominioEvento cEv;
	protected ControladorDominioVotacion cVot;
	
	public ControladorDominioBusqueda() {
		cDip = ControladorDominioDiputado.getInstance();
		cLeg = ControladorDominioLegislatura.getInstance();
		cEv = ControladorDominioEvento.getInstance();
		cVot = ControladorDominioVotacion.getInstance();
	}
	
	
	protected Map<String, Integer> prepararImportancias(
			Map<String, Integer> importanciaModificada) {
		Map<String, Integer> res = new TreeMap<String,Integer>(); 
		res.putAll(importanciaModificada);
		List<String> tipoEventos = cEv.GetTipoEventos(); //Suponiendo que no se cambie a Set
		for (String tipoEvento : tipoEventos) {
			if (!res.containsKey(tipoEvento)) {
				res.put(tipoEvento, cEv.getImportancia(tipoEvento)); //Suponiendo que se implemente con este nombre
			}
		}
		return res;
	}

	protected Map<String, Set<String>> prepararEventos(DateInterval periodo) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Set<String> prepararDiputados(DateInterval Periodo) {
		Integer legislaturaInicio = cLeg.getID(Periodo.getInici());
		Integer legislaturaFin = cLeg.getID(Periodo.getFi());
		return prepararDiputados(legislaturaInicio, legislaturaFin);
	}
	
	private Set<String> prepararDiputados(Integer legislaturaInicio,
			Integer legislaturaFin) {
		Set<String> res = new HashSet<String>();
		for (Integer i = legislaturaInicio; i <= legislaturaFin; ++i) {
			 res.addAll(cLeg.getDiputados(i));
		}
		return res;
	}

	
	protected Map<String, Set<String>> prepararVotaciones(DateInterval periodo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected Graf construirGrafo(Set<String> idDiputados,
			Map<String, Integer> importancias,
			Map<String, Set<String>> tiposYeventos,
			Map<String, Set<String>> votacionesSimp) {
		HashSet<String>	Nodos = new HashSet<String>(idDiputados);
		Graf G = new Graf(Nodos);
		for (String tipoDeEvento : tiposYeventos.keySet()) {
			Integer importancia = importancias.get(tipoDeEvento);
			for (String evento : tiposYeventos.get(tipoDeEvento)) {
				interrelacionar(G, cEv.getDiputados(evento), importancia);
			}
		}
		for (Set<String> votacion : votacionesSimp.values()) {
			interrelacionar(G, votacion, 5.0);
		}
		
		return G;
	}


	private void interrelacionar(Graf g, Set<String> diputados, Double d) {
		for (String diputado1: diputados) {
			for (String diputado2: diputados) {
				if(g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, d/2+g.getPes(diputado1, diputado2));
				else g.addAresta(diputado1, diputado2, d/2);
			}
		}
	}
	
	

}
