package dominio;

import time.Date;

/**
 * Grupo afin obtenido en una busqueda por diputado.
 * @author Miguel Angel Aranda
 * @version 1.0 Mayo 2015
 */
public class GrupoAfinPorDiputado extends GrupoAfin {
	
	/**
	 * Fecha de inicio de la busqueda.
	 */
	private Date fechaInicio;
	
	/**
	 * Fecha de fin de la busqueda.
	 */
	private Date fechaFin;

	/**
	 * Crea una nueva instancia de grupo afin por diputado.
	 * @param ID - Identificador del grupo afin.
	 * @param FechaInicio - Fecha de inicio de la busqueda.
	 * @param FechaFin - Fecha de fin de la busqueda.
	 */
	public GrupoAfinPorDiputado(Integer ID, Date fechaInicio, Date fechaFin) {
		super(ID);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Introduce la fecha de inicio de la busqueda.
	 * @param fechaInicio - Fecha de inicio de la busqueda.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Introduce la fecha de fin de la busqueda.
	 * @param fechaFin - Fecha de fin de la busqueda.
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Suministra la fecha de inicio de la busqueda.
	 * @return Fecha de inicio de la busqueda.
	 */
	public Date getFechaInicio() {
		return new Date(fechaInicio);
	}
	
	/**
	 * Suministra la fecha de fin de la busqueda.
	 * @return Fecha de fin de la busqueda.
	 */
	public Date getFechaFin() {
		return new Date(fechaFin);
	}
	
}
