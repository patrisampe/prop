package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

public class Evento {
	private String Nombre;
	private Date Fecha;
	
	private Set <String> Diputados;
	
	public Evento(String Nombre, Date Fecha){
		 //Pre: Fecha valida y Nombre no existente
		this.Nombre=Nombre;
		this.Fecha=Fecha;
		Diputados=new TreeSet<String>();
	}
	
	public Evento(String Nombre, Evento E){
		//Pre: Fecha valida y Nombre no existente
		this.Nombre=Nombre;
		this.Fecha=E.Fecha;
		Diputados= new TreeSet<String>(E.Diputados);
	}
	
	public Date GetFecha(){
		return this.Fecha;
	}
	
	public String GetNombre(){
		return this.Nombre;
	}
	
	public Set<String> GetDiputados(){
		return this.Diputados;
	}
	
	public Boolean Es_participante(String nombreDiputado){
		return Diputados.contains(nombreDiputado);
	}
	
	public void SetFecha(Date nuevaFecha){
		//Pre: Fecha valida
		Fecha = new Date(nuevaFecha);
	}
	
	public void AddDiputado(String nombreDiputado){
		//Pre: Diputado existe y no ha participado en este evento
		Diputados.add(nombreDiputado);
	}
	
	public void RemoveDiputado(String nombreDiputado) {
		//Pre: Diputado existe y ha participado en este evento
		Diputados.remove(nombreDiputado);
	}
	
	
}
