package controladores;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import time.DateInterval;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.Legislatura;

public class ControladorDominioLegislatura {
	
	private CodiError error;
	private Conjunto<Legislatura> conjuntoLegislaturas;
	private static ControladorDominioLegislatura instance = null;
	
	protected ControladorDominioLegislatura(){
		conjuntoLegislaturas = new Conjunto<Legislatura>(Legislatura.class);
		error = new CodiError();
	}
	
	public static ControladorDominioLegislatura getInstance() {
	      if (instance == null) {
	         instance = new ControladorDominioLegislatura();
	      }
	      return instance;
	}
	
	public Integer numeroLegislaturas(){
		return conjuntoLegislaturas.size();
	}
	
	public void addAll(Set<Legislatura> legislaturas){
		conjuntoLegislaturas.addAll(legislaturas);
	}
	
	public Set<Legislatura> getAll() {
		return conjuntoLegislaturas.getAll();
	}
	
	public Set<Integer> getIDs() {
		return conjuntoLegislaturas.getIntegerKeys();
	}
	
	public Integer getID(Date FechaContenida) {
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			if (L.hasFechaFinal()) {
				DateInterval DI = new DateInterval(L.getFechaInicio(), L.getFechaFinal());
				if (DI.contains(FechaContenida)) return L.getID();
			}
			else if (L.getFechaInicio().compareTo(FechaContenida) >= 0) return L.getID();
		}
		return -1;
	}

	public Integer getIDLast() {
		Integer max = -1;
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			Integer t = L.getID();
			if (t > max) max = t;
		}
		return max;
	}

	public DateInterval limits(Integer identificadorLegislatura) {
		Integer idA = identificadorLegislatura - 1;
		Integer idP = identificadorLegislatura + 1;
		while (!existsLegislatura(idA) && idA >= 0) --idA;
		if (idP != getIDLast()) while (!existsLegislatura(idP)) ++idP;
		Date inici = (idA == -1 ? Date.NULL : conjuntoLegislaturas.get(idA).getFechaFinal());
		Date fi = (idP == getIDLast() ? Date.NULL : conjuntoLegislaturas.get(idA).getFechaFinal());
		return new DateInterval(inici, fi);
	}
	
	public void addLegislatura(Integer identificadorLegislatura, Date FechaInicio, Date FechaFinal) {
		if (existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(16);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			Legislatura L = new Legislatura(identificadorLegislatura, FechaInicio, FechaFinal);
			conjuntoLegislaturas.add(identificadorLegislatura, L);			
		}
	}

	public void addLegislatura(Integer identificadorLegislatura, Date FechaInicio) {
		if (existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(16);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			Integer id = getIDLast();
			if (id != -1){
				error.setCodiError(16);
				error.setClauExterna(identificadorLegislatura);
				error.addClauExterna(id);
			}
			else {
				Legislatura L = new Legislatura(identificadorLegislatura, FechaInicio);
				conjuntoLegislaturas.add(identificadorLegislatura, L);
			}
		} 
	}
	
	public Boolean existsLegislatura(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.exists(identificadorLegislatura);
	}

	public void removeLegislatura(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			conjuntoLegislaturas.remove(identificadorLegislatura);
		}
	}
	
	public void setFechaInicio(Integer identificadorLegislatura, Date FechaInicio) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			Integer idData = getID(FechaInicio);
			if (idData != -1 && idData != identificadorLegislatura){
				error.setCodiError(22);
				error.setClauExterna(identificadorLegislatura);
				error.addClauExterna(FechaInicio.toString());
			}
			else if (idData == -1 && limits(identificadorLegislatura).contains(FechaInicio)) {
				error.setCodiError(23);
				error.setClauExterna(identificadorLegislatura);
				error.addClauExterna(FechaInicio.toString());
			}
			else conjuntoLegislaturas.get(identificadorLegislatura).setFechaInicio(FechaInicio);
		}
	}
	
	public void setFechaFinal(Integer identificadorLegislatura, Date FechaFin) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			Integer idData = getID(FechaFin);
			if (idData != -1 && idData != identificadorLegislatura){
				error.setCodiError(22);
				error.setClauExterna(identificadorLegislatura);
				error.addClauExterna(FechaFin.toString());
			}
			else if (idData == -1 && limits(identificadorLegislatura).contains(FechaFin)) {
				error.setCodiError(23);
				error.setClauExterna(identificadorLegislatura);
				error.addClauExterna(FechaFin.toString());
			}
			else conjuntoLegislaturas.get(identificadorLegislatura).setFechaInicio(FechaFin);
		}
	}
	
	public Date getFechaInicio(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getFechaInicio();
	}
	
	public Date getFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else if (!hasFechaFinal(identificadorLegislatura)) {
			error.setCodiError(19);
			error.setClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getFechaFinal();
	}

	public Boolean hasFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
			return false;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).hasFechaFinal();
	}

	public void removeFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else if (!hasFechaFinal(identificadorLegislatura)) {
			error.setCodiError(19);
			error.setClauExterna(identificadorLegislatura);
		}
		else conjuntoLegislaturas.get(identificadorLegislatura).removeFechaFinal();
	}
	
	public void addDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		if (!CDD.existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else if (existsDiputado(identificadorLegislatura, nombreDiputado)) {
			error.setCodiError(20);
			error.setClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoLegislaturas.get(identificadorLegislatura).addDiputado(nombreDiputado);
	}
	
	public void setDiputados(Integer identificadorLegislatura, Set<String> diputados) {
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			for (String nombreDiputado:diputados) {
				if (!CDD.existsDiputado(nombreDiputado)) {
					error.setCodiError(3);
					error.setClauExterna(nombreDiputado);
					return;
				}
				else if (existsDiputado(identificadorLegislatura, nombreDiputado)) {
					error.setCodiError(20);
					error.setClauExterna(nombreDiputado);
					error.addClauExterna(identificadorLegislatura);
					return;
				}
			}
			conjuntoLegislaturas.get(identificadorLegislatura).setDiputados(diputados);
		}

	}
	
	public Set<String> getDiputados(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
			return new TreeSet<String>();
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getDiputados();
	}

	public Boolean existsDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
			return false;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).hasDiputado(nombreDiputado);
	}
	
	public void removeDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		if (!CDD.existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else if (!existsDiputado(identificadorLegislatura, nombreDiputado)) {
			error.setCodiError(21);
			error.setClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoLegislaturas.get(identificadorLegislatura).removeDiputado(nombreDiputado);
			if (CDD.existsLegistura(nombreDiputado, identificadorLegislatura))
				CDD.removeLegistura(nombreDiputado, identificadorLegislatura);
			}
	}
	
	public void removeDiputados(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else {
			conjuntoLegislaturas.get(identificadorLegislatura).removeDiputados();
			ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
			CDD.removeLegislaturaFromDiputados(identificadorLegislatura);
		}
	}
	
	//Elimina el diputat indicat de totes les legislatures
	public void removeDiputadoFromLegislaturas(String nombreDiputado) {
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			Integer identificadorLegislatura = L.getID();
			if (existsDiputado(identificadorLegislatura, nombreDiputado))
				removeDiputado(identificadorLegislatura, nombreDiputado);
		}
	}	
	
	public Boolean hasCodiError() {
		return (error.getCodiError() != 0);
	}

	public CodiError getCodiError() {
		return error;
	}

}