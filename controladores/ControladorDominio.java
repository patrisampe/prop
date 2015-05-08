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
	private CodiError error;
	/**
	 * Indica si se ha producido un error en el ultimo metodo ejecutado.
	 */
	private Boolean hasError;
	
	/**
	 * Creadora por defecto.
	 */
	public ControladorDominio() {
		hasError = false;
	}
}
