package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import time.*;
import dominio.*;

public class ControladorDominioBusquedaPorPeriodo extends
		ControladorDominioBusqueda {
	public Set<GrupoAfin> NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, DateInterval Periodo, Map<String, Integer> ImportanciaModificada) {
		
		Set<String> idDiputados = prepararDiputados(Periodo);
		//TODO
		return null;
	}
	
	private Set<String> prepararDiputados(DateInterval Periodo) {
		Integer legislaturaInicio = cLeg.getID(Periodo.getInici());
		Integer legislaturaFin = cLeg.getID(Periodo.getFi());
		Set<String> res = new HashSet<String>();
		for (Integer i = legislaturaInicio; i <= legislaturaFin; ++i) {
			 res.addAll(cLeg.getDiputados(i)); //hay que ver cual será el nombre de el método
		}
		return res;
	}
}
