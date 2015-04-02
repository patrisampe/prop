package utiles;

import java.util.Map;
import java.util.TreeMap;
import time.Date;

public class Atributos_Evento {

	
	private Date Fecha;
	private Map<String, Boolean> Diputados;
	//true -> insert
	//false -> remove
	public static final Map <String,Boolean> DiputadosUnchanged=new TreeMap<String,Boolean> ();
	public static final Date DateUnchanged = Date.NULL;
	
	public Atributos_Evento(Date fecha,Map<String, Boolean> diputados ) {
		Fecha = fecha;
		Diputados=diputados;
	}
	
	public Atributos_Evento() {
		Fecha = DateUnchanged;
		Diputados = DiputadosUnchanged;
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