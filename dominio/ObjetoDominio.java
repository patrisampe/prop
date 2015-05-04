package dominio;

/**
 * Objeto generico del dominio de datos.
 * @author David Moran
 * @version 04/05/2015 01:00
 */
public class ObjetoDominio implements Comparable<ObjetoDominio> {
	
	/**
	 * Crea una instancia de un objeto generico del dominio.
	 */
	protected ObjetoDominio(){
	}
	
	/**
	 * Consulta el nombre generico de un objeto del dominio.
	 * @return Un <i>String</i> vacio ("").
	 */
	public String getNombre(){
		return "";
	}
	
	/**
	 * Consulta el identificador generico de un objeto del dominio.
	 * @return Un <i>Integer</i> con valor -2.
	 */
	public Integer getID(){
		return -2;
	}

	/**
	 * Consulta el identificador generico de un objeto del dominio.
	 * @return Un <i>Integer</i> con valor -2.
	 */
	public int compareTo(ObjetoDominio o) {
		if (getID() == -2) { //Clau externa -> String
			return getNombre().compareTo(o.getNombre());
		}
		else {
			return getID().compareTo(o.getID());
		}
	}
	
}