package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

/**
 * Classe Evento contiene los eventos
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 */
public class Evento extends ObjetoDominio{
	private String nombre;
	private Date fecha;
	private Set <String> diputados;
	
	/**
	 * Crea un nuevo Evento
	 * @param Nombre - Nombre del evento que se crea
	 * @param data 
	 * 	- Data en que se realiza el evento. 
	 * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	 * @param Diputados - Conjunto de Diputados que participan en el evento
	 */
	public Evento(String Nombre, Date data, Set<String> Diputados){
		nombre=Nombre;
		fecha=data;
		diputados=new TreeSet<String>(Diputados);
	}
	/**
	 * Copia un Evento y le pone otro nombre
	 * @param Nombre - Nombre del evento que se crea
	 * @param E - Evento que se quiere copiar
	 */
	
	public Evento(String Nombre, Evento E){
		nombre=Nombre;
		fecha=E.fecha;
		diputados= new TreeSet<String>(E.diputados);
	}
	
	/**
	 * Funcion que devuelve la fecha del evento
	 * @return Fecha del evento
	 */
	
	public Date getFecha(){
		return fecha;
	}
	
	/**
	 * Funcion que devuelve el nombre del evento
	 * @return nombre del evento
	 * 
	 */
	
	public String getNombre(){
		return nombre;
	}
	/**
	 * Funcion que devuelve los diputados que participan en el evento
	 * @return diputados que participan en el evento
	 * 
	 */
	public Set<String> getdiputados(){
		return diputados;
	}
	
	/**
	 * Indica si un Diputado es participante
	 * @param nombreDiputado
	 * 	- Diputado del que queremos saber si ha participado en el Evento. 
	 * <dd><b>Precondition:</b><dd> nombreDiputado tiene de ser el nombre de un Diputado existente
	 * @return un boolean que indica si es participante o no
	 * 
	 */
	
	public Boolean esParticipante(String nombreDiputado){
		return diputados.contains(nombreDiputado);
	}
	
	/**
	 * Modifica la data del evento
	 * @param nuevaFecha 
	 * 	- Data en que se realiza el evento. 
	 * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	 */
	
	public void setFecha(Date nuevaFecha){
		fecha = new Date(nuevaFecha);
	}
	
	/**
	 * Añades este diputado a este evento
	 * @param nombreDiputado
	 * 	- Diputado del que queremos que participe en el evento.   <br> 
	 * <dd><b>Precondition:</b><dd> <br>
	 * 1 -nombreDiputado tiene de ser el nombre de un Diputado existente<br>
	 * 2- Este diputado no ha participado en este evento
	 * 
	 */
	
	
	public void addDiputado(String nombreDiputado){
		//Pre: Diputado existe y no ha participado en este evento
		diputados.add(nombreDiputado);
	}
	
	/**
	 * Eliminas este diputado de este evento
	 * @param nombreDiputado
	 * 	- Diputado del que queremos borrar del evento.   <br> 
	 * <dd><b>Precondition:</b><dd> <br>
	 * 1 -nombreDiputado tiene de ser el nombre de un Diputado existente<br>
	 * 2- Este diputado ha participado en este evento
	 * 
	 */
	
	public void removeDiputado(String nombreDiputado) {
		diputados.remove(nombreDiputado);
	}
	
	public int compareTo(Evento E) {
		return fecha.compareTo(E.getFecha());
	}
	
}


