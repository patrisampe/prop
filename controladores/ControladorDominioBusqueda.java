package controladores;

import java.util.HashSet;
import java.util.Set;

import dominio.*;

public abstract class ControladorDominioBusqueda {
	
	protected ControladorDominioDiputado cDip;
	protected ControladorDominioLegislatura cLeg;
	protected ControladorDominioEvento cEv;
	
	public ControladorDominioBusqueda() {
		cDip = ControladorDominioDiputado.getInstance();
		cLeg = ControladorDominioLegislatura.getInstance();
		cEv = ControladorDominioEvento.getInstance();
	}
	

}
