package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

public class Diputado {
	private String Nombre;
	private String Partido_politico;
	private String Estado;
	private Date Fecha_de_nacimiento;
	private Set<Integer> Legislaturas;

	public static final Diputado NULL = new Diputado("NULL", "", "", Date.NULL);
	
	public Diputado(String Nombre, String Partido_politico, String Estado, Date Fecha_de_nacimiento){
		this.Nombre = Nombre;
		this.Partido_politico = Partido_politico;
		this.Estado = Estado;
		this.Fecha_de_nacimiento = Fecha_de_nacimiento;
		Legislaturas = new TreeSet<Integer>();
	}
	
	public Diputado(String Nombre, Diputado D){
		this.Nombre = Nombre;
		Partido_politico = D.Partido_politico;
		Estado = D.Estado;
		Fecha_de_nacimiento = D.Fecha_de_nacimiento;
		Legislaturas = new TreeSet<Integer>(D.Legislaturas);
	}
	
	public String getNombre(){
		return Nombre;
	}
	
	public String getPartidoPolitico(){
		return Partido_politico;
	}
	
	public String getEstado(){
		return Estado;
	}
	
	public Date getFechaDeNacimiento(){
		return Fecha_de_nacimiento;
	}
	
	public Set<Integer> getLegislaturas(){
		return Legislaturas;
	}
	
	public Boolean esActivo(Integer identificadorLegislatura){
		return Legislaturas.contains(identificadorLegislatura);
	}

	public Boolean esNull(){
		return (this.equals(NULL)) || (Nombre.equals(""));
	}

	public void setPartidoPolitico(String nuevoPartido){
		if (!nuevoPartido.isEmpty()) Partido_politico = nuevoPartido;
	}
	
	public void setEstado(String nuevoEstado) {
		if (!nuevoEstado.isEmpty()) Estado = nuevoEstado;
	}
	
	public void setFechaNacimiento(Date nuevaFecha) {
		if (nuevaFecha.esValida()) Fecha_de_nacimiento = new Date(nuevaFecha);
	}
	
	public void addLegistura(Integer identificadorLegislatura) {
		Legislaturas.add(identificadorLegislatura);
	}
	
	public void removeLegistura(Integer identificadorLegislatura) {
		Legislaturas.remove(identificadorLegislatura);
	}
	
}