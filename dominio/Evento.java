package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.*;
import controladores.*;

public class Evento {

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
	
	public Boolean SetFecha(Date nuevaFecha){
		if (!nuevaFecha.Es_valida()) return false;
		Fecha = new Date(nuevaFecha);
		return true;
	}
	
	public Integer AddDiputado(String nombreDiputado){
		//1: ha anat b�
		//0: aquest nom de diputat no �s un diputat
		//-1: aquest diputat ja participa en el event
		if(Diputados.contains(nombreDiputado))return 3;
		//if(!ControladorDominioDiputado.Existe_diputado(nombreDiputado))return 1;
		Diputados.add(nombreDiputado);
		return 0;
	}
	
	public Integer RemoveDiputado(String nombreDiputado) {
		//if(!ControladorDominioDiputado.Existe_diputado(nombreDiputado))return 1;
		if (!Diputados.contains(nombreDiputado)) return 2;
		Diputados.remove(nombreDiputado);
		return 0;
	}
	
	
}
