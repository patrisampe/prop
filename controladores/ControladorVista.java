package controladores;

import vista.VistaPrincipal;

public class ControladorVista {

	private static ControladorVista instance = null;
	
	private ControladorVista() {
		super();
	}
	public static ControladorVista getInstance() {
		if (instance == null) instance = new ControladorVista();
		return instance;
	}
	
	public void iniciaVista(){
		ControladorDominioDatos aux=ControladorDominioDatos.getInstance();
		aux.cargarDominio();
		VistaPrincipal vista = new VistaPrincipal();
        vista.setVisible(true);
	}
	
	public void salvarDominio(){
		ControladorDominioDatos aux=ControladorDominioDatos.getInstance();
		aux.salvarDominio();
	}

}
