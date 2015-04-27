package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

public class Diputado extends ObjetoDominio {
	private String nombre;
	private String partidoPolitico;
	private String estado;
	private Date fechaDeNacimiento;
	private Set<Integer> legislaturas;

	public static final Diputado NULL = new Diputado("NULL", "", "", Date.NULL);
	
	public Diputado(String nombre, String partidoPolitico, String estado, Date fechaDeNacimiento){
		this.nombre = nombre;
		this.partidoPolitico = partidoPolitico;
		this.estado = estado;
		this.fechaDeNacimiento = fechaDeNacimiento;
		legislaturas = new TreeSet<Integer>();
	}
	
	public Diputado(String nombre, Diputado D){
		this.nombre = nombre;
		partidoPolitico = D.partidoPolitico;
		estado = D.estado;
		fechaDeNacimiento = D.fechaDeNacimiento;
		legislaturas = new TreeSet<Integer>(D.legislaturas);
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getPartidoPolitico(){
		return partidoPolitico;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public Date getFechaDeNacimiento(){
		return fechaDeNacimiento;
	}
	
	public Set<Integer> getLegislaturas(){
		return legislaturas;
	}
	
	public Boolean esActivo(Integer identificadorLegislatura){
		return legislaturas.contains(identificadorLegislatura);
	}

	public Boolean esNull(){
		return (this.equals(NULL)) || (nombre.equals(""));
	}

	public void setPartidoPolitico(String nuevoPartido){
		if (!nuevoPartido.isEmpty()) partidoPolitico = nuevoPartido;
	}
	
	public void setEstado(String nuevoEstado) {
		if (!nuevoEstado.isEmpty()) estado = nuevoEstado;
	}
	
	public void setFechaNacimiento(Date nuevaFecha) {
		if (nuevaFecha.esValida()) fechaDeNacimiento = new Date(nuevaFecha);
	}
	
	public void addLegistura(Integer identificadorLegislatura) {
		legislaturas.add(identificadorLegislatura);
	}
	
	public void setLegisturas(Set<Integer> legislaturas) {
		this.legislaturas = new TreeSet<Integer>(legislaturas);
	}
	
	public Boolean hasLegistura(Integer identificadorLegislatura) {
		return legislaturas.contains(identificadorLegislatura);
	}
	
	public void removeLegistura(Integer identificadorLegislatura) {
		legislaturas.remove(identificadorLegislatura);
	}
	
	public void removeLegisturas() {
		legislaturas.clear();
	}
	
}