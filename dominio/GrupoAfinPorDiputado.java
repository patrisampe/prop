package dominio;

import time.*;

public class GrupoAfinPorDiputado extends GrupoAfin{
	private Date fechaInicio, fechaFin;
	
	public GrupoAfinPorDiputado(Integer ID, Date inicio, Date fin) {
		setID(ID);
		fechaInicio = new Date(inicio);
		fechaFin = new Date(fin);
	}
	
	public void setFechaInicio(Date inicio) {
		fechaInicio = new Date(inicio);
	}
	
	public void setFechaFin(Date fin) {
		fechaFin = new Date (fin);
	}
	
	public Date getFechaInicio() {
		return new Date(fechaInicio);
	}
	
	public Date getFechaFin() {
		return new Date(fechaFin);
	}
}
