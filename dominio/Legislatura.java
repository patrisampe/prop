package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.Date;

public class Legislatura extends ObjetoDominio{
	private Integer identificador;
	private Date fechaInicio;
	private Date fechaFinal;
	private Set<String> diputados;
	
	public static final Legislatura NULL = new Legislatura(-1, Date.NULL);

	public Legislatura(Integer identificador, Date fechaInicio, Date fechaFinal){
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		diputados = new TreeSet<String>();
	}
	
	public Legislatura(Integer identificador, Date fechaInicio){
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = Date.NULL;
		diputados = new TreeSet<String>();
	}
	
	public Legislatura(Integer identificador, Legislatura L){
		this.identificador = identificador;
		fechaInicio = L.fechaInicio;
		fechaFinal = L.fechaFinal;
		diputados = new TreeSet<String>(L.diputados);
	}
	
	public Integer getID() {
		return identificador;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Boolean hasFechaFinal() {
		return fechaFinal.esNull();
	}
	
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	public Set<String> getDiputados(){
		return diputados;
	}
	
	public Boolean esActivo(String nombreDiputado) {
		return diputados.contains(nombreDiputado);
	}
	
	public Boolean esNull() {
		return (this.equals(NULL) || identificador == -1);
	}
	
	public void setFechaInicio(Date nuevaFecha) {
		if (nuevaFecha.esValida()) fechaInicio = new Date(nuevaFecha);
	}
	
	public void setFechaFinal(Date nuevaFecha) {
		if (nuevaFecha.esValida()) fechaFinal = new Date(nuevaFecha);
	}
	
	public void removeFechaFinal() {
		fechaFinal = Date.NULL;
	}
	
	public void addDiputado(String nombreDiputado) {
		diputados.add(nombreDiputado);
	}
	
	public void setDiputados(Set<String> diputados) {
		this.diputados = new TreeSet<String>(diputados);
	}
	
	public Boolean hasDiputado(String nombreDiputado) {
		return diputados.contains(nombreDiputado);
	}
	
	public void removeDiputado(String nombreDiputado) {
		diputados.remove(nombreDiputado);
	}
	
	public void removeDiputados() {
		diputados.clear();
	}
	
}