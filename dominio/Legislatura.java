package dominio;

import java.util.Set;
import java.util.TreeSet;

import time.Date;

/**
 * Periodo de tiempo transcurrido a lo largo de un mandato.
 * @author David Moran
 */
public class Legislatura extends ObjetoDominio{
	
	/**
	 * Identificador de la legislatura.
	 */
	private Integer identificador;
	
	/**
	 * Fecha de inicio de la legislatura.
	 */
	private Date fechaInicio;
	
	/**
	 * Fecha de finalizacion de la legislatura.
	 */
	private Date fechaFinal;
	
	/**
	 * Conjunto de diputados activos en la legislatura.
	 */
	private Set<String> diputados;
	
	/**
	 * Instancia nula de una legislatura.
	 */
	public static final Legislatura NULL = new Legislatura(-1, Date.NULL);

	/**
	 * Crea una nueva instancia de una legislatura.
	 * @param identificador - Identificador de la legislatura.
	 * @param fechaInicio - Fecha de inicio de la legislatura.
	 * @param fechaFinal - Fecha de finalizacion de la legislatura.
	 */
	public Legislatura(Integer identificador, Date fechaInicio, Date fechaFinal){
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		diputados = new TreeSet<String>();
	}
	
	/**
	 * Crea una nueva instancia de una legislatura sin fecha de finalizacion.
	 * @param identificador - Identificador de la legislatura.
	 * @param fechaInicio - Fecha de inicio de la legislatura.
	 */
	public Legislatura(Integer identificador, Date fechaInicio){
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = Date.NULL;
		diputados = new TreeSet<String>();
	}
	
	/**
	 * Crea una nueva instancia de una legislatura copia de la legislatura L.
	 * @param identificador - Identificador de la legislatura.
	 * @param L - Legislatura de la cual se deben copiar los datos, excepto el identificador.
	 */
	public Legislatura(Integer identificador, Legislatura L){
		this.identificador = identificador;
		fechaInicio = L.fechaInicio;
		fechaFinal = L.fechaFinal;
		diputados = new TreeSet<String>(L.diputados);
	}
	
	/**
	 * Consulta el identificador de la legislatura.
	 * @return El identificador de la legislatura.
	 */
	public Integer getID() {
		return identificador;
	}
	
	/**
	 * Consulta la fecha de inicio de la legislatura.
	 * @return La fecha de inicio de la legislatura.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Consulta si la legislatura tiene fecha de finalizacion.
	 * @return <i>true</i> si la legislatura tiene fecha de finalizacion.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean hasFechaFinal() {
		return fechaFinal.esNull();
	}
	
	/**
	 * Consulta la fecha de finalizacion de la legislatura.
	 * @return La fecha de finalizacion de la legislatura.
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	/**
	 * Consulta el conjunto de diputados activos en la legislatura.
	 * @return El conjunto de diputados activos en la legislatura.
	 */
	public Set<String> getDiputados(){
		return diputados;
	}
	
	/**
	 * Consulta si un diputado es activo en la legislatura.
	 * @return <i>true</i> si el diputado es activo en la legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esActivo(String nombreDiputado) {
		return diputados.contains(nombreDiputado);
	}
	
	/**
	 * Consulta si la legislatura es una instancia nula de Legislatura.
	 * @return <i>true</i> si la legislatura es una instancia nula de Legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esNull() {
		return (this.equals(NULL) || identificador <= -1);
	}
	
	/**
	 * Modifica la fecha de inicio de la legislatura.
	 * @param nuevaFecha - La fecha de inicio de la legisatura.
	 */
	public void setFechaInicio(Date nuevaFecha) {
		fechaInicio = new Date(nuevaFecha);
	}
	
	/**
	 * Modifica la fecha de finalizacion de la legislatura.
	 * @param nuevaFecha - La fecha de finalizacion de la legisatura.
	 */
	public void setFechaFinal(Date nuevaFecha) {
		fechaFinal = new Date(nuevaFecha);
	}
	
	/**
	 * Elimina la fecha de finalizacion de la legislatura.
	 */
	public void removeFechaFinal() {
		fechaFinal = Date.NULL;
	}
	
	/**
	 * Introduce el diputado en la lista de diputados activos de la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void addDiputado(String nombreDiputado) {
		diputados.add(nombreDiputado);
	}
	
	/**
	 * Establece un conjunto de diputados como lista de diputados activos de la legislatura.
	 * @param diputados - Conjunto de nombres de diputados.
	 */
	public void setDiputados(Set<String> diputados) {
		this.diputados = new TreeSet<String>(diputados);
	}
	
	/**
	 * Consulta si el diputado es activo en una legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 * @return <i>true</i> si el diputado es activo en la legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean hasDiputado(String nombreDiputado) {
		return diputados.contains(nombreDiputado);
	}
	
	/**
	 * Elimina un diputado de la lista de diputados activos en la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void removeDiputado(String nombreDiputado) {
		diputados.remove(nombreDiputado);
	}
	
	/**
	 * Elimina todos los diputados de la lista de diputados activos en la legislatura.
	 */
	public void removeDiputados() {
		diputados.clear();
	}
	
}