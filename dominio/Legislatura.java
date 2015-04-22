package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.Date;

public class Legislatura{
	private Integer Identificador;
	private Date Fecha_inicio;
	private Date Fecha_final;
	private Set<String> Diputados;
	
	public static final Legislatura NULL = new Legislatura(-1, Date.NULL);

	public Legislatura(Integer Identificador, Date Fecha_inicio, Date Fecha_final){
		this.Identificador = Identificador;
		this.Fecha_inicio = Fecha_inicio;
		this.Fecha_final = Fecha_final;
		Diputados = new TreeSet<String>();
	}
	
	public Legislatura(Integer Identificador, Date Fecha_inicio){
		this.Identificador = Identificador;
		this.Fecha_inicio = Fecha_inicio;
		this.Fecha_final = Date.NULL;
		Diputados = new TreeSet<String>();
	}
	
	public Legislatura(Integer Identificador, Legislatura L){
		this.Identificador = Identificador;
		Fecha_inicio = L.Fecha_inicio;
		Fecha_final = L.Fecha_final;
		Diputados = new TreeSet<String>(L.Diputados);
	}
	
	
	public Integer getID() {
		return Identificador;
	}
	
	public Date getFechaInicio() {
		return Fecha_inicio;
	}
	
	public Boolean TieneFechaFinal() {
		return Fecha_final.esNull();
	}
	
	public Date getFechaFinal() {
		return Fecha_final;
	}
	
	
	public Set<String> getDiputados(){
		return Diputados;
	}
	
	public Boolean esActivo(String nombreDiputado) {
		return Diputados.contains(nombreDiputado);
	}
	
	public Boolean esNull() {
		return (this.equals(NULL) || Identificador == -1);
	}
	
	public void setFechaInicio(Date nuevaFecha) {
		if (nuevaFecha.esValida()) Fecha_inicio = new Date(nuevaFecha);
	}
	
	public void setFechaFinal(Date nuevaFecha) {
		if (nuevaFecha.esValida()) Fecha_inicio = new Date(nuevaFecha);
	}
	
	public void addDiputado(String nombreDiputado) {
		Diputados.add(nombreDiputado);
	}
	
	public void removeDiputado(String nombreDiputado) {
		Diputados.remove(nombreDiputado);
	}
	
}