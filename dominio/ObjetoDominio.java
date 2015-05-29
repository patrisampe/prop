package dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Objeto genérico del dominio de datos.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ObjetoDominio implements Comparable<ObjetoDominio> {
	
	/**
	 * Crea una instancia de un objeto genérico del dominio.
	 */
	protected ObjetoDominio(){
	}
	
	/**
	 * Consulta el nombre genérico de un objeto del dominio.
	 * @return Un <i>String</i> vacio ("").
	 */
	public String getNombre(){
		return "";
	}
	
	/**
	 * Consulta el identificador genérico de un objeto del dominio.
	 * @return Un <i>Integer</i> con valor -2.
	 */
	public Integer getID(){
		return -2;
	}

	/**
	 * Compara dos objetos genéricos del dominio.
	 * @param o - El objeto a comparar.
	 * @return El resultado de la comparación entre los dos objetos.
	 */
	public int compareTo(ObjetoDominio o) {
		if (getID() == -2) { //Clave externa -> String
			return getNombre().compareTo(o.getNombre());
		}
		else { //Clave externa -> Integer
			return getID().compareTo(o.getID());
		}
	}
	
	//A Partir de aqui son operaciones gancho
	
	public Integer getIndiceAfinidad() {
		return -1;
	}
	
	public TipoAlgoritmo getAlgoritmo() {
		return TipoAlgoritmo.GirvanNewmann;
	}
	
	public Boolean esModificado() {
		return false;
	}
	public String getPeriodo() {
		return "";
	}
	public String getDiputadoRelevante() {
		return "";
	}
	
	public Map<Criterio, Double> getCriterios(){
		return new TreeMap<Criterio,Double>();
	}
	
	public Vector<Set<String>> getResultado(){
		return new Vector<Set<String>>();
	}
	
	public Map<String, Integer> getImportancias() {
		return new HashMap<String, Integer>();
	}
	
	public String getTipoResultado(){
		return "NULL";
	}
	
	public void moveDiputado(String nombre, Integer desdeID, Integer hastaID) {
		
	}
	
	public  void removeDiputado(String nombre, Integer ID){}
	public  void removeDiputado(String nombre){}
	
	public void addDiputado(String nombre, Integer ID){}
	
}