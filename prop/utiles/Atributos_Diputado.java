package utiles;

import java.util.Map;
import java.util.TreeMap;

import time.Date;

public class Atributos_Diputado {
	private String Partido_politico;
	private String Estado;
	private Date Fecha_de_nacimiento;
	private Map<Integer, Boolean> Legislaturas;
		//Si una legislatura no apareix al map ==> Unchanged
		//Si una legislatura apareix amb valor false ==> Remove
		//Si una legislatura apareix amb valor true ==> Add
	
	public static final String Unchanged_String = "";
	public static final Date Unchanged_Date = Date.NULL;
	public static final Map<Integer, Boolean> Unchanged_Legislatura = new TreeMap<Integer, Boolean>();
	public static final Boolean Remove_Legislatura = false;
	public static final Boolean Add_Legislatura = true;
	public static final Atributos_Diputado NULL = new Atributos_Diputado();
	
	
	public Atributos_Diputado(){
		Partido_politico = Unchanged_String;
		Estado = Unchanged_String;
		Fecha_de_nacimiento = Unchanged_Date;
		Legislaturas = Unchanged_Legislatura;
	}

	public String getPartidoPolitico(){
		return Partido_politico;
	}
	
	public String getEstado(){
		return Estado;
	}
	
	public Date getFechaDeNacimiento(){
		return Fecha_de_nacimiento;
	}
	
	public Map<Integer, Boolean> getLegislaturas(){
		return Legislaturas;
	}
	
	public void setPartidoPolitico(String Partido_politico){
		this.Partido_politico = Partido_politico;
	}
	
	public void setEstado(String Estado){
		this.Estado = Estado;
	}
	
	public void setFechaDeNacimiento(Date Fecha_de_nacimiento){
		this.Fecha_de_nacimiento = Fecha_de_nacimiento;
	}
	
	public void setLegislaturas(Map<Integer, Boolean> Legislaturas){
		this.Legislaturas = Legislaturas;
	}
	
	public Boolean Es_null(){
		return equals(NULL);
	}
	
}