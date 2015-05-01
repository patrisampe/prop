package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import utiles.CodiError;
import utiles.Conjunto;
import dominio.Evento;
import dominio.TipoEvento;
import dominio.TipoVoto;
import time.Date;


public class ControladorDominioEvento {
	private Conjunto<TipoEvento> conjuntoTipoEvento;
	private static ControladorDominioEvento instance = null;
	private CodiError error;
	private Boolean hasError;

	
	   protected ControladorDominioEvento() {
	      // Exists only to defeat instantiation.
		   conjuntoTipoEvento=  new Conjunto<TipoEvento>(TipoEvento.class);
	   }
	   
	   public static ControladorDominioEvento getInstance() {
	      if(instance == null) {
	         instance = new ControladorDominioEvento();  
	      }
	      
	      return instance;
	   }
	   
	   
	   public void setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia){
		   
	   }
	   
	   public Integer getImportanciaTipoEvento(String nombreTipoEvento){
		  return conjuntoTipoEvento.get(nombreTipoEvento).getImportancia();
	   }
	   
	   public Set<String> getTipoEvento(){
		   return conjuntoTipoEvento.getStringKeys();
	   }
	   
	   public List<String> getEventos(String nombreTipoEvento){
		   return conjuntoTipoEvento.get(nombreTipoEvento).getEventos();
		   
	   }
	   
	   public List<String> getEventos(String nombreTipoEvento, Date dataInici, Date dataFi){
		   List<String> result=new ArrayList<String>();
		   Conjunto<Evento> aux= conjuntoTipoEvento.get(nombreTipoEvento).getEventosmap();
		   for (Entry<String, Evento> elem : aux.entrySet()){
			   Date act=elem.getValue().getFecha();
				if(act.compareTo(dataInici)!=-1 & act.compareTo(dataFi)!=1)result.add(elem.getKey());
		 }
		   return result;
	   }
	   
	   public void removeDiputado(String nombreDiputado){
		  
		   for (Entry<String, TipoEvento> elem : conjuntoTipoEvento.entrySet()){
			  Conjunto<Evento> aux= elem.getValue().getEventosmap();
			   for (Entry<String, Evento> elem2 : aux.entrySet()){
				   if(elem2.getValue().esParticipante(nombreDiputado))elem2.getValue().removeDiputado(nombreDiputado);
			   }
		   }
	   }
	   
	   	/*
	+ setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia): void
	+ addTipoEvento(String nombreTipoEvento, Integer importancia): void
	+ removeTipoEvento(String nombreTipoEvento): void
	+ esTipoEvento(String nombreTipoEvento): Boolean

	+ addEvento(String nombreTipoEvento, String nombreEvento, Date fecha, Set<String> Diputados):void
	+ removeEvento(String nombreTipoEvento, String nombreEvento):void
	+ esEvento(String nombreTipoEvento, String nombreEvento):Boolean
	+ setFechaEvento(String nombreTipoEvento, String nombreEvento, Date fecha): void
	+ getFechaEvento(String nombreTipoEvento, String nombreEvento): Date
	+ getDiputadosEvento(String nombreTipoEvento, String nombreEvento): Set<String>
	+ addDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado):void
	+ removeDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado):void
	+ removeDiputado(String nombreDIputado):void
	    */
	
}
