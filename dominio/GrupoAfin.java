package dominio;

import java.util.TreeSet;

import time.Date;

/**
 * Grupo de diputados con cierta afinidad entre ellos (Clase abstracta).
 * @author Miguel Angel Aranda
 * @version 1.0 Mayo 2015
 */
public abstract class GrupoAfin extends ObjetoDominio {
	
	/**
	 * Identificador del grupo afin.
	 */
	private Integer identificador;
	
	/**
	 * Conjunto de los diputados que forman el grupo.
	 */
	private TreeSet<String> diputados;
	
	/**
	 * Constructora por defecto.
	 * @param ID - Identificador del grupo afin.
	 */
	public GrupoAfin(Integer ID) {
		identificador = ID;
		diputados = new TreeSet<String>();
	}
	
	/**
	 * Agrega un diputado al grupo afin.
	 * @param diputado - El nombre del diputado que se agrega.
	 */
	public void addDiputado(String diputado) {
		diputados.add(diputado);
	}
	
	/**
	 * Elimina un diputado del grupo afin.
	 * @param diputado - El nombre del diputado que se elimina.
	 */
	public void removeDiputado(String Diputado) {
		diputados.remove(Diputado);
	}
	
	/**
	 * Administra el identificador del grupo afin.
	 * @return El identificador en valor entero.
	 */
	public Integer getID() {
		return identificador;
	}
	
	/**
	 * Administra un conjunto de cadenas de texto con los diputados del grupo afin.
	 * @return Un conjunto de cadenas con el nombre de los diputados.
	 */
	public TreeSet<String> getDiputados() {
		return new TreeSet<String>(diputados);
	}
	
	/**
	 * Consulta si un diputado pertenece al grupo afin.
	 * @return <i>true</i> si el diputado pertenece al grupo.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean pertenece(String Diputado) {
		return diputados.contains(Diputado);
	}
	
	/**
	 * Consulta si el grupo es un grupo vacio.
	 * @return <i>true</i> si el el grupo no tiene ningun diputado.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esVacio() {
		return diputados.isEmpty();
	}
	
	/**
	 * Fecha de inicio de la busqueda.
	 */
	public Date getFechaInicio() {
		return null;
	}

	/**
	 * Fecha de fin de la busqueda.
	 */
	public Date getFechaFin() {
		return null;
	}

}
