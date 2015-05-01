package controladores;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import utiles.CodiError;
import utiles.Conjunto;
import dominio.TipoEvento;
import dominio.Votacion;

public class ControladorDominioVotacion {

	private Conjunto<Votacion> conjuntoVotacion;
	private static ControladorDominioVotacion instance = null;
	private CodiError error;
	private Boolean hasError;

	
	 protected ControladorDominioVotacion() {
	      // Exists only to defeat instantiation.
		   conjuntoVotacion=  new Conjunto<Votacion>(Votacion.class);
	   }
	   
	   public static ControladorDominioVotacion getInstance() {
	      if(instance == null) {
	         instance = new ControladorDominioVotacion();  
	      }
	      
	      return instance;
	   }
	
	public void removeDiputado(String nombreDiputado){
		 for (Entry<String, Votacion> elem : conjuntoVotacion.entrySet()){
			 if(elem.getValue().esVotante(nombreDiputado))elem.getValue().removeVoto(nombreDiputado);
		 }
	}
	
	
}
