package controladores;

import utiles.CodiError;

/**
 * Alias Super Controlador :)
 * @author Yoel Cabo
 *
 */
public class ControladorDominio {

	/**
	 * Codigo de error del ultimo metodo ejecutado.
	 */
	protected CodiError error;
	/**
	 * Indica si se ha producido un error en el ultimo metodo ejecutado.
	 */
	protected Boolean hasError;
	
	/**
	 * Creadora por defecto.
	 */
	public ControladorDominio() {
		hasError = false;
	}
	
	/**
	 * 
	 * @return true si el ControladorDominio tiene un error, false en caso contrario
	 */
	public Boolean hasError() {
		return hasError;
	}
	
	/**
	 * Consultora de Error, esta constructora es destructiva.
	 * @return si hay error lo devuelve, en caso contrario devuelve null.
	 */
	public CodiError getError() {
		if (hasError) {
			hasError = false;
			return error;
		}
		return null;
	}
	
	/**
	 * Incorpora el error de otro Controlador del Dominio.
	 * @param cd Controlador de Dominio 
	 * @return true si cd tenia error, false en caso contrario.
	 */
	protected Boolean catchError(ControladorDominio cd) {
		if (cd.hasError) {
			hasError = true;
			error = cd.getError();
			return true;
		}
		return false;
	}
	
}
