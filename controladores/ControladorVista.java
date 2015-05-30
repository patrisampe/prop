package controladores;

import vista.VistaPrincipal;

public class ControladorVista {

	
	private static ControladorVista instance = null;
	
	private ControladorVista() {
		super();
	}
	/**
	 * Crea una nueva instancia de la classe.
	 * @return Nueva instancia del <i>singletone</i> de la clase.
	 */
	public static ControladorVista getInstance() {
		if (instance == null) instance = new ControladorVista();
		return instance;
	}
	/**
	 * Inicia la vista Principal y en consequencia todas las otras
	 */
	public void iniciaVista(){
		ControladorDominioDatos aux=ControladorDominioDatos.getInstance();
		aux.cargarDominio();
		VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);
	}
	/**
	 * Salva los datos del Dominio al fichero para cuando el usuario quiera cerrar
	 */
	public void salvarDominio(){
		ControladorDominioDatos aux=ControladorDominioDatos.getInstance();
		aux.salvarDominio();
	}

}
