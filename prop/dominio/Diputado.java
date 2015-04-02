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
		this.Partido_politico = D.Partido_politico;
		this.Estado = D.Estado;
		this.Fecha_de_nacimiento = D.Fecha_de_nacimiento;
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
	
	public Boolean Es_activo(Integer identificadorLegislatura){
		return Legislaturas.contains(identificadorLegislatura);
	}

	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}

	public Boolean setPartidoPolitico(String nuevoPartido){
		if (nuevoPartido.isEmpty()) return false;
		Partido_politico = nuevoPartido;
		return true;
	}
	
	public Boolean setEstado(String nuevoEstado) {
		if (nuevoEstado.isEmpty()) return false;
		Estado = nuevoEstado;
		return true;
	}
	
	public Boolean setFechaNacimiento(Date nuevaFecha) {
		if (!nuevaFecha.Es_valida()) return false;
		Fecha_de_nacimiento = new Date(nuevaFecha);
		return true;
	}
	
	public Boolean addLegistura(Integer identificadorLegislatura) {
		return Legislaturas.add(identificadorLegislatura);
	}
	
	public Boolean removeLegistura(Integer identificadorLegislatura) {
		return Legislaturas.remove(identificadorLegislatura);
	}
	
}