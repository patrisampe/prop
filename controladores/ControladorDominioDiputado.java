package controladores;

import java.util.Map;

import time.Date;
import utiles.Conjunto;
import dominio.Diputado;

class ControladorDominioDiputado {
	
	//private CodiError errors;
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
	
	public void addAll(Map<String, Diputado> M){
		conjuntoDiputados.addAll(M);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Diputado> getAll() {
		return (Map<String, Diputado>) conjuntoDiputados.getAll();
	}
	
	public void removeAll() {
		conjuntoDiputados.removeAll();
	}
	
	public void addDiputado(String nombreDiputado, String nombrePartido, String nombreEstado, Date FechaDeNacimiento) {
		Diputado D = new Diputado(nombreDiputado, nombrePartido, nombreEstado, FechaDeNacimiento);
		conjuntoDiputados.add(nombreDiputado, D);
	}

	public Boolean existeDiputado(String nombreDiputado) {
		return conjuntoDiputados.exists(nombreDiputado);
	}

	
	public void removeDiputado(String nombreDiputado) {
		conjuntoDiputados.remove(nombreDiputado);
	}
		
	public void setPartidoPolitico(String nombreDiputado, String nombrePartido) {
		Diputado D = conjuntoDiputados.get(nombreDiputado);
		D.setPartidoPolitico(nombrePartido);
	}

	public void setEstado(String nombreDiputado, String nombreEstado) {
		Diputado D = conjuntoDiputados.get(nombreDiputado);
		D.setEstado(nombreEstado);
	}
	
	public void setFechaDeNacimiento(String nombreDiputado, Date FechaDeNacimiento) {
		Diputado D = conjuntoDiputados.get(nombreDiputado);
		D.setFechaNacimiento(FechaDeNacimiento);
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

	public void addRLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		Diputado D = conjuntoDiputados.get(nombreDiputado);
		D.addLegistura(identificadorLegislatura);
	}
	
	public Boolean existeRLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		Diputado D =  conjuntoDiputados.get(nombreDiputado);
		return D.hasLegistura(identificadorLegislatura);
	}
	
	public void removeRLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		Diputado D = conjuntoDiputados.get(nombreDiputado);
		D.removeLegisturas();
	}
	
	public Boolean teCodiError() {
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