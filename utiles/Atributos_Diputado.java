package utiles;

import java.util.Map;
import java.util.TreeMap;

import time.Date;

public class Atributos_Diputado {
	public String Partido_politico;
	public String Estado;
	public Date Fecha_de_nacimiento;
	public Map<Integer, Boolean> Legislaturas;
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
	
	public Boolean Es_null(){
		return equals(NULL);
	}
	
}