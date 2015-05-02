package dominio;

/**
 * Objeto generico del dominio de datos.
 * @author David Moran
 */
public class ObjetoDominio {
	
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
	
}