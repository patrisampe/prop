package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.*;

public class Evento {

	private String Nombre;
	private Date Fecha;
	
	private Set <String> Diputados;
	public static final Evento NULL = new Evento("NULL", Date.NULL);

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
	
	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}
	
	public String GetNombre(){
		return Nombre;
	}
	
	public Date GetFecha(){
		return Fecha;
	}
	public Boolean SetFecha(Date nuevaFecha){
		if (!nuevaFecha.Es_valida()) return false;
		Fecha = new Date(nuevaFecha);
		return true;
	}
	
	public Set<String> GetDiputados(){
		return Diputados;
	}
	
	public Boolean Es_participante(String nombreDiputado){
		return Diputados.contains(nombreDiputado);
	}
	
	
	
	public Integer AddDiputado(String nombreDiputado){
		//1: ha anat b�
		//0: aquest nom de diputat no �s un diputat
		//-1: aquest diputat ja participa en el event
		if(Diputados.contains(nombreDiputado))return -1;
		if(!Diputado.Existe_diputado(nombreDiputado))return 0;
		Diputados.add(nombreDiputado);
		return 1;
	}
	
	public Integer RemoveDiputado(String nombreDiputado) {
		if(!Diputado.Existe_diputado(nombreDiputado))return 0;
		if (!Diputados.contains(nombreDiputado)) return -1;
		Diputados.remove(nombreDiputado);
		return 1;
	}
	
	
}
