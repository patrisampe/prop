package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.*;

class Evento {

	private String Nombre;
	private Date Fecha;
	
	private Set <String> Diputados;
	
	public Evento(String Nombre, Date Fecha){
		this.Nombre=Nombre;
		this.Fecha=Fecha;
		Diputados=new TreeSet<String>();
	}
	
	public Evento(String Nombre, Evento E){
		this.Nombre=Nombre;
		this.Fecha=E.Fecha;
		Diputados= new TreeSet<String>(E.Diputados);
	}
	
	public Date Consultar_fecha(){
		return this.Fecha;
	}
	
	public String Consultar_nombre(){
		return this.Nombre;
	}
	
	public Set<String> Consultar_diputados(){
		return this.Diputados;
	}
	
	public Boolean Ha_participado(String nombreDiputado){
		return Diputados.contains(nombreDiputado);
	}
	
	public Boolean Modificar_fecha(Date nuevaFecha){
		if (!nuevaFecha.Es_valida()) return false;
		Fecha = new Date(nuevaFecha);
		return true;
	}
	
	public Integer Añadir_diputado(String nombreDiputado){
		//1: ha anat bé
		//0: aquest nom de diputat no és un diputat
		//-1: aquest diputat ja participa en el event
		if(Diputados.contains(nombreDiputado))return -1;
		if(!Diputado.Existe_diputado(nombreDiputado))return 0;
		Diputados.add(nombreDiputado);
		return 1;
	}
	
	public Boolean Eliminar_diputado(String nombreDiputado) {
		if (!Diputados.contains(nombreDiputado)) return false;
		Diputados.remove(nombreDiputado);
		return true;
	}
	
	
}
