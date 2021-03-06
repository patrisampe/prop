package dominio;
import java.util.Set;
import java.util.TreeSet;

import time.*;

/**
 * Representante de un estado que ocupa un cargo publico en el senado.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class Diputado extends ObjetoDominio {
	
	/**
	 * Nombre del diputado.
	 */
	private String nombre;
	
	/**
	 * Nombre del partido político al cual pertenece el diputado.
	 */
	private String partidoPolitico;
	
	/**
	 * Nombre del estado al cual representa el diputado.
	 */
	private String estado;
	
	/**
	 * Fecha de nacimiento del diputado.
	 */
	private Date fechaDeNacimiento;
	
	/**
	 * Conjunto de legislaturas en las que el diputado ha estado activo.
	 */
	private Set<Integer> legislaturas;

	/**
	 * Instancia nula de un diputado.
	 */
	public static final Diputado NULL = new Diputado("NULL", "", "", Date.NULL);
	
	
	/**
	 * Crea una nueva instancia de un diputado.
	 * @param nombre - Nombre del diputado.
	 * @param partidoPolitico - Partido político al cual pertenece el diputado.
	 * @param estado - Estado al cual representa el diputado.
	 * @param fechaDeNacimiento - Fecha de nacimiento del diputado.
	 */
	public Diputado(String nombre, String partidoPolitico, String estado, Date fechaDeNacimiento){
		this.nombre = nombre;
		this.partidoPolitico = partidoPolitico;
		this.estado = estado;
		this.fechaDeNacimiento = fechaDeNacimiento;
		legislaturas = new TreeSet<Integer>();
	}
	
	/**
	 * Crea una nueva instancia de un diputado copia del diputado D.
	 * @param nombre - Nombre del diputado.
	 * @param D - Diputado del cual se deben copiar los datos, excepto el nombre.
	 */
	public Diputado(String nombre, Diputado D){
		this.nombre = nombre;
		partidoPolitico = D.partidoPolitico;
		estado = D.estado;
		fechaDeNacimiento = D.fechaDeNacimiento;
		legislaturas = new TreeSet<Integer>(D.legislaturas);
	}
	
	/**
	 * Consulta el nombre del diputado.
	 * @return El nombre del diputado.
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Consulta el partido político del diputado.
	 * @return El partido político del diputado.
	 */
	public String getPartidoPolitico(){
		return partidoPolitico;
	}
	
	/**
	 * Consulta el estado al que representa el diputado.
	 * @return El estado al que representa el diputado.
	 */
	public String getEstado(){
		return estado;
	}
	
	/**
	 * Consulta la fecha de nacimiento del diputado.
	 * @return La fecha de nacimiento del diputado.
	 */
	public Date getFechaDeNacimiento(){
		return fechaDeNacimiento;
	}
	/**
	 * Consulta el conjunto de legislaturas en las que ha estado activo el diputado.
	 * @return El conjunto de legislaturas en las que ha estado activo el diputado.
	 */
	public Set<Integer> getLegislaturas(){
		return legislaturas;
	}
	
	/**
	 * Consulta si el diputado es activo en una legislatura.
	 * @param identificadorLegislatura - Identificador de una legislatura.
	 * @return <i>true</i> si el diputado es activo en la legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esActivo(Integer identificadorLegislatura){
		return legislaturas.contains(identificadorLegislatura);
	}

	/**
	 * Consulta si el diputado es una instancia nula de Diputado.
	 * @return <i>true</i> si el diputado es una instancia nula de Diputado.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esNull(){
		return (this.equals(NULL)) || (nombre.equals(""));
	}

	/**
	 * Modifica el partido político del diputado.
	 * @param nuevoPartido - El partido político del diputado.
	 */
	public void setPartidoPolitico(String nuevoPartido){
		if (!nuevoPartido.isEmpty()) partidoPolitico = nuevoPartido;
	}
	
	/**
	 * Modifica el estado al que representa el diputado.
	 * @param nuevoEstado - El estado al que representa el diputado.
	 */
	public void setEstado(String nuevoEstado) {
		if (!nuevoEstado.isEmpty()) estado = nuevoEstado;
	}
	
	/**
	 * Modifica la fecha de nacimiento del diputado.
	 * @param nuevaFecha - La fecha de nacimiento del diputado.
	 */
	public void setFechaNacimiento(Date nuevaFecha) {
		fechaDeNacimiento = new Date(nuevaFecha);
	}
	
	/**
	 * Añade la legislatura a la lista de legislaturas activas del diputado.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 */
	public void addLegistura(Integer identificadorLegislatura) {
		legislaturas.add(identificadorLegislatura);
	}
	
	/**
	 * Elimina una legislatura de la lista de legislaturas en las que el diputado ha estado activo.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 */
	public void removeLegistura(Integer identificadorLegislatura) {
		legislaturas.remove(identificadorLegislatura);
	}
	
	/**
	 * Elimina todas las legislaturas de la lista de legislaturas en las que el diputado ha estado activo.
	 */
	public void removeLegisturas() {
		legislaturas.clear();
	}
	
}