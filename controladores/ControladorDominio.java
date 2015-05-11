package controladores;

import utiles.CodiError;

/**
 * Alias Super Controlador :)
 * @author Yoel Cabo
 *
 */
public class ControladorDominio {

	/**
	 * Codigo de error del último metodo ejecutado.
	 */
	protected CodiError error;
	/**
	 * Indica si se ha producido un error en el último método ejecutado.
	 */
	protected Boolean hasError;
	
	/**
	 * Creadora por defecto.
	 */
	public ControladorDominio() {
		error = new CodiError(0);
	}
	
	/**
	 * 
	 * @return true si el ControladorDominio tiene un error, false en caso contrario
	 */
	public Boolean hasError() {
		return error.getCodiError() != 0;
	}
	
	/**
	 * Consultora de Error, esta constructora es destructiva.
	 * @return si hay error lo devuelve, en caso contrario devuelve null.
	 */
	public CodiError getError() {
		if (this.hasError()) {
			error = new CodiError(0);
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
		if (cd.hasError()) {
			error = cd.getError();
			return true;
		}
		return false;
	}
	
}
