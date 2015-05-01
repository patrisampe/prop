package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

public class Evento extends ObjetoDominio{
	private String nombre;
	private Date fecha;
	
	private Set <String> diputados;
	/*
	public Evento(String Nombre, Date Fecha){
		 //Pre: Fecha valida y Nombre no existente
		nombre=Nombre;
		fecha=Fecha;
		diputados=new TreeSet<String>();
	}
	*/
	public Evento(String Nombre, Date data, Set<String> Diputados){
		 //Pre: Fecha valida y Nombre no existente
		nombre=Nombre;
		fecha=data;
		diputados=new TreeSet<String>(Diputados);
	}
	
	
	public Evento(String Nombre, Evento E){
		//Pre: Fecha valida y Nombre no existente
		nombre=Nombre;
		fecha=E.fecha;
		diputados= new TreeSet<String>(E.diputados);
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Set<String> getdiputados(){
		return diputados;
	}
	
	public Boolean esParticipante(String nombreDiputado){
		return diputados.contains(nombreDiputado);
	}
	
	public void setFecha(Date nuevaFecha){
		//Pre: Fecha valida
		fecha = new Date(nuevaFecha);
	}
	
	public void addDiputado(String nombreDiputado){
		//Pre: Diputado existe y no ha participado en este evento
		diputados.add(nombreDiputado);
	}
	
	public void removeDiputado(String nombreDiputado) {
		//Pre: Diputado existe y ha participado en este evento
		diputados.remove(nombreDiputado);
	}
	
	
}
