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
	
}