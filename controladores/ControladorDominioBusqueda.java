package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.DateInterval;
import dominio.*;
import dominio.algoritmos.Clique;
import dominio.algoritmos.Graf;
import dominio.algoritmos.GrafLouvain;
import dominio.algoritmos.GrafNewman;
import dominio.algoritmos.Louvain;
import dominio.algoritmos.Newman;

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
		Set<String> tipoEventos = cEv.getTipoEvento(); //Suponiendo que no se cambie a Set
		for (String tipoEvento : tipoEventos) {
			if (!res.containsKey(tipoEvento)) {
				res.put(tipoEvento, cEv.getImportanciaTipoEvento(tipoEvento)); //Suponiendo que se implemente con este nombre
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
	
	protected Set<String> prepararDiputados(Integer legislaturaInicio,
			Integer legislaturaFin) {
		Set<String> res = new HashSet<String>();
		for (Integer i = legislaturaInicio; i <= legislaturaFin; ++i) {
			 if(cLeg.existsLegislatura(i)) res.addAll(cLeg.getDiputados(i));
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
		// TODO Auto-generated method stub
		return null;
	}
	
	protected HashSet<HashSet<String>> ejecutar(Graf g, TipoAlgoritmo algoritmo,
			Integer porcentaje) {
		HashSet<HashSet<String>> hs = new HashSet<HashSet<String>>();
		if (algoritmo == TipoAlgoritmo.CliquePercolation) {
			hs = Clique.executa(g,porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.GirvanNewmann) {
			hs = Newman.executa(new GrafNewman(g),porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.Louvain) {
			GrafLouvain gl = new GrafLouvain(g);
			hs = Louvain.executa(new GrafLouvain(g), porcentaje);
		}
		return hs;
	}
	

}
