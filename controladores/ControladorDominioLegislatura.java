package controladores;

import java.util.Iterator;
import java.util.Set;

import time.Date;
import time.DateInterval;
import utiles.Conjunto;
import dominio.Legislatura;

public class ControladorDominioLegislatura {
	
	//private CodiError errors; TODO
	private Conjunto<Legislatura> conjuntoLegislaturas;
	private static ControladorDominioLegislatura instance = null;
	
	protected ControladorDominioLegislatura(){
		conjuntoLegislaturas = new Conjunto<Legislatura>(Legislatura.class);
	}
	
	public static ControladorDominioLegislatura getInstance() {
	      if(instance == null) {
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
	
	public void removeAll() {
		conjuntoLegislaturas.removeAll();
	}
	
	public Integer getID(Date FechaContenida) {
		Set<Legislatura> S = conjuntoLegislaturas.getAll();
		if (S.isEmpty()) return -1;
		Iterator<Legislatura> it = S.iterator();
		while (it.hasNext()) {
			Legislatura L = it.next();
			if (L.hasFechaFinal()) {
				DateInterval DI = new DateInterval(L.getFechaInicio(), L.getFechaFinal());
				if (DI.contains(FechaContenida)) return L.getID();
			}
			else {
				if (L.getFechaInicio().compareTo(FechaContenida) >= 0) return L.getID();
			}
		}
		return -1;
	}

	public Integer getIDLast() {
		Set<Legislatura> S = conjuntoLegislaturas.getAll();
		if (S.isEmpty()) return -1;
		Iterator<Legislatura> it = S.iterator();
		Integer max = it.next().getID();
		while (it.hasNext()) {
			Integer t = it.next().getID();
			if (t > max) max = t;
		}
		return max;
	}

	
	public void addLegislatura(Integer identificadorLegislatura, Date FechaInicio, Date FechaFinal) {
		Legislatura L = new Legislatura(identificadorLegislatura, FechaInicio, FechaFinal);
		conjuntoLegislaturas.add(identificadorLegislatura, L);
	}

	public void addLegislatura(Integer identificadorLegislatura, Date FechaInicio) {
		Legislatura L = new Legislatura(identificadorLegislatura, FechaInicio);
		conjuntoLegislaturas.add(identificadorLegislatura, L);
	}
	
	public Boolean existsLegislatura(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.exists(identificadorLegislatura);
	}

	
	public void removeLegislatura(Integer identificadorLegislatura) {
		conjuntoLegislaturas.remove(identificadorLegislatura);
	}
	
	public void setFechaInicio(Integer identificadorLegislatura, Date FechaInicio) {
		conjuntoLegislaturas.get(identificadorLegislatura).setFechaInicio(FechaInicio);
	}
	
	public void setFechaFinal(Integer identificadorLegislatura, Date FechaFin) {
		conjuntoLegislaturas.get(identificadorLegislatura).setFechaInicio(FechaFin);
	}
	
	public Date getFechaInicio(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.get(identificadorLegislatura).getFechaInicio();
	}
	
	public Date getFechaFinal(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.get(identificadorLegislatura).getFechaFinal();
	}

	public Boolean hasFechaFinal(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.get(identificadorLegislatura).hasFechaFinal();
	}

	public void removeFechaFinal(Integer identificadorLegislatura) {
		conjuntoLegislaturas.get(identificadorLegislatura).removeFechaFinal();
	}
	
	public void addRDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		conjuntoLegislaturas.get(identificadorLegislatura).addDiputado(nombreDiputado);
	}
	
	public void setRDiputado(Integer identificadorLegislatura, Set<String> diputados) {
		conjuntoLegislaturas.get(identificadorLegislatura).setDiputados(diputados);
	}
	
	public Boolean existsRDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		return conjuntoLegislaturas.get(identificadorLegislatura).hasDiputado(nombreDiputado);
	}
	
	public void removeRDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		conjuntoLegislaturas.get(identificadorLegislatura).removeDiputado(nombreDiputado);
	}
	
	public void removeRDiputado(Integer identificadorLegislatura) {
		conjuntoLegislaturas.get(identificadorLegislatura).removeDiputados();
	}
	
	public Boolean hasCodiError() {
		return false; //TODO
	}
	
	
	public Integer getCodiError() {
		return 0; //TODO
	}
	
	public void abrirArchivo() {
		//TODO
	}
	
	public void crearArchivo() {
		//TODO
	}
	
	public void cargarDatos() {
		//TODO
	}
	
	public void guardarDatos() {
		//TODO
	}

}