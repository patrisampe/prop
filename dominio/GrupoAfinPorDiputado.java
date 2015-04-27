package dominio;

import time.Date;

public class GrupoAfinPorDiputado extends GrupoAfin {
	
	private Date fechaInicio, fechaFin;

	public GrupoAfinPorDiputado(Integer ID, Date fechaInicio, Date fechaFin) {
		super(ID);
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Date getFechaInicio() {
		return new Date(fechaInicio);
	}
	
	public Date getFechaFin() {
		return new Date(fechaFin);
	}
	
}
