package dominio;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import time.*;

class Diputado {
	private String Nombre;
	private String Partido_politico;
	private String Estado;
	private Date Fecha_de_nacimiento;
	
	private static Map<String, Diputado> Conjunto_diputados = new TreeMap<String, Diputado>();
	//private static Diputado NULL = new Diputado("NULL", "", "", new Date(0, 0, 0));
	private Set<Integer> Legislaturas;
	
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
	
	public String Consultar_partido_politico(){
		return Partido_politico;
	}
	
	public String Consultar_Estado(){
		return Estado;
	}
	
	public Date Consultar_Fecha_de_nacimiento(){
		return Fecha_de_nacimiento;
	}
	
	public Set<Integer> Consultar_legislaturas(){
		return Legislaturas;
	}
	
	public Boolean Es_activo(Integer identificadorLegislatura){
		return Legislaturas.contains(identificadorLegislatura);
	}

	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}

	public Boolean Modificar_partido_politico(String nuevoPartido){
		if (nuevoPartido.isEmpty()) return false;
		Partido_politico = nuevoPartido;
		return true;
	}
	
	public Boolean Modificar_estado(String nuevoEstado) {
		if (nuevoEstado.isEmpty()) return false;
		Estado = nuevoEstado;
		return true;
	}
	
	public Boolean Modificar_fecha(Date nuevaFecha) {
		if (!nuevaFecha.Es_valida()) return false;
		Fecha_de_nacimiento = new Date(nuevaFecha);
		return true;
	}
	
	public Boolean Añadir_legistura(Integer identificadorLegislatura) {
		if (Legislaturas.contains(identificadorLegislatura)) return false;
		Legislaturas.add(identificadorLegislatura);
		return true;
	}
	
	public Boolean Eliminar_legistura(Integer identificadorLegislatura) {
		if (!Legislaturas.contains(identificadorLegislatura)) return false;
		Legislaturas.remove(identificadorLegislatura);
		return true;
	}
	
	public static Boolean Añadir_diputado(Diputado nuevoDiputado) {
		String Nombre = nuevoDiputado.Nombre;
		if (Conjunto_diputados.containsKey(Nombre)) return false;
		Conjunto_diputados.put(Nombre, nuevoDiputado);
		return true;
	}
	
	public static Diputado Consultar_diputado(String nombreDiputado) {
		return Conjunto_diputados.get(nombreDiputado);
		//return Conjunto_diputados.getOrDefault(nombreDiputado, Diputado.NULL);
	}
	
	public static Boolean Existe_diputado(String nombreDiputado) {
		return Consultar_diputado(nombreDiputado).Es_null();
	}
	
	
	public static Boolean Eliminar_diputado(String nombreDiputado) {
		if (!Conjunto_diputados.containsKey(nombreDiputado)) return false;
		Conjunto_diputados.remove(nombreDiputado);
		return true;
	}
}
