package controladores;

import java.util.Set;

import time.Date;
import utiles.Conjunto;
import dominio.Diputado;

public class ControladorDominioDiputado {
	
	//private CodiError errors; TODO
	private Conjunto<Diputado> conjuntoDiputados;
	private static ControladorDominioDiputado instance = null;
	
	
	protected ControladorDominioDiputado(){
		conjuntoDiputados = new Conjunto<Diputado>(Diputado.class);
	}
	
	public static ControladorDominioDiputado getInstance() {
	      if(instance == null) {
	         instance = new ControladorDominioDiputado();
	      }
	      return instance;
	}
	
	public Integer numeroDiputados(){
		return conjuntoDiputados.size();
	}
	
	public void addAll(Set<Diputado> diputados){
		conjuntoDiputados.addAll(diputados);
	}
	
	public Set<Diputado> getAll() {
		return conjuntoDiputados.getAll();
	}
	
	public void removeAll() {
		conjuntoDiputados.removeAll();
	}
	
	public void addDiputado(String nombreDiputado, String nombrePartido, String nombreEstado, Date FechaDeNacimiento) {
		Diputado D = new Diputado(nombreDiputado, nombrePartido, nombreEstado, FechaDeNacimiento);
		conjuntoDiputados.add(nombreDiputado, D);
	}

	public Boolean existsDiputado(String nombreDiputado) {
		return conjuntoDiputados.exists(nombreDiputado);
	}
	
	public void removeDiputado(String nombreDiputado) {
		conjuntoDiputados.remove(nombreDiputado);
	}
		
	public void setPartidoPolitico(String nombreDiputado, String nombrePartido) {
		conjuntoDiputados.get(nombreDiputado).setPartidoPolitico(nombrePartido);
	}

	public void setEstado(String nombreDiputado, String nombreEstado) {
		conjuntoDiputados.get(nombreDiputado).setEstado(nombreEstado);
	}
	
	public void setFechaDeNacimiento(String nombreDiputado, Date FechaDeNacimiento) {
		conjuntoDiputados.get(nombreDiputado).setFechaNacimiento(FechaDeNacimiento);
	}

	public String getPartidoPolitico(String nombreDiputado) {
		return conjuntoDiputados.get(nombreDiputado).getPartidoPolitico();
	}
	
	public String getEstado(String nombreDiputado) {
		return conjuntoDiputados.get(nombreDiputado).getEstado();
	}

	
	public Date getFechaDeNacimiento(String nombreDiputado) {
		return conjuntoDiputados.get(nombreDiputado).getFechaDeNacimiento();
	}

	public void addLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		conjuntoDiputados.get(nombreDiputado).addLegistura(identificadorLegislatura);
	}
	
	public void setLegisturas(String nombreDiputado, Set<Integer> legislaturas) {
		conjuntoDiputados.get(nombreDiputado).setLegisturas(legislaturas);
	}
	
	public Set<Integer> getLegislaturas(String nombreDiputado) {
		return conjuntoDiputados.get(nombreDiputado).getLegislaturas();
	}
	
	public Boolean existsLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		return conjuntoDiputados.get(nombreDiputado).hasLegistura(identificadorLegislatura);
	}
	
	public void removeLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		conjuntoDiputados.get(nombreDiputado).removeLegistura(identificadorLegislatura);
	}
	
	public void removeLegisturas(String nombreDiputado) {
		conjuntoDiputados.get(nombreDiputado).removeLegisturas();
	}
	
	public Boolean hasCodiError() {
		return false; //TODO
	}
	
	
	public Integer getCodiError() {
		return 0; //TODO
	}
	
}