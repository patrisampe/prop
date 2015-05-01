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

/**
 * Controlador encargado de calcular Afinidades entre los diputados del dominio. Haciendo uso de todos los datos disponibles en el dominio.
 * @author Yoel Cabo
 *
 */
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
		Set<String> tipoEventos = cEv.getTipoEvento();
		for (String tipoEvento : tipoEventos) {
			if (!res.containsKey(tipoEvento)) {
				res.put(tipoEvento, cEv.getImportanciaTipoEvento(tipoEvento)); 
			}
		}
		return res;
	}

	protected Map<String, Set<String>> prepararEventos(DateInterval periodo) {
		Map<String, Set<String>> res = new TreeMap<String, Set<String>>(); 
		
		Set<String> tipoEventos = cEv.getTipoEvento();
		for (String tipoEvento : tipoEventos) {
			res.put(tipoEvento, cEv.getEventos(tipoEvento, periodo.getInici(), periodo.getFi()));
		}
		return res;
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
		Map<String, Set<String>> mapa = new TreeMap<String, Set<String>>();
		for (String votacion : cVot.getVotaciones(periodo.getInici(), periodo.getFi())) {
			mapa.put(votacion+"_A_FAVOR", cVot.getDiputadosVotacion(votacion, TipoVoto.A_FAVOR));
			mapa.put(votacion+"_EN_CONTRA", cVot.getDiputadosVotacion(votacion, TipoVoto.EN_CONTRA));
		}
		return mapa;
	}
	
	protected Graf construirGrafo(Set<String> idDiputados,
			Map<String, Integer> importancias,
			Map<String, Set<String>> tiposYeventos,
			Map<String, Set<String>> votacionesSimp) {
		Graf G = new Graf((HashSet<String>) idDiputados);
		for (String tipoEvento : tiposYeventos.keySet()) {
			for(String evento : tiposYeventos.get(tipoEvento)) {
				interrelacionar(G, cEv.getDiputadosEvento(tipoEvento, evento), (Double) importancias.get(tipoEvento).doubleValue());
			}
		}
		for (Set<String> diputadosVotandoLoMismo : votacionesSimp.values()) {
			interrelacionar(G, diputadosVotandoLoMismo, 5.0);
		}
		return G;
	}
	
	private void interrelacionar(Graf g, Set<String> diputadosEvento,
			Double peso) {
		
		// TODO Auto-generated method stub
		
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
			hs = Louvain.executa(new GrafLouvain(g), porcentaje);
		}
		return hs;
	}
	

}
