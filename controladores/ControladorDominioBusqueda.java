package controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.DateInterval;
import dominio.*;

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
	

}