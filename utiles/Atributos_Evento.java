package utiles;

import java.util.Map;
import java.util.TreeMap;
import time.Date;

public class Atributos_Evento {

	
	private Date Fecha;
	private Map<String, Boolean> Diputados;
	//true -> remove
	//false -> insert
	
	public Atributos_Evento(Date fecha) {
		Fecha = fecha;
		Diputados=new TreeMap<String,Boolean>();
	}
	
	public static final Date Unchanged_Date = Date.NULL;

	public Atributos_Evento() {
		Fecha = Unchanged_Date;
		Diputados = new TreeMap<String,Boolean> ();
	}
	
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public Map<String, Boolean> getDiputados() {
		return Diputados;
	}
	public void setDiputados(Map<String, Boolean> diputados) {
		Diputados = diputados;
	}
	
}
