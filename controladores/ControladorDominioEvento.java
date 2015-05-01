package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utiles.CodiError;
import utiles.Conjunto;
import dominio.TipoEvento;
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
		   List<String> a=new ArrayList<String>();
		   
		   
		   
		   return a;
		   
	   }
	   
	   /*

	+ setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia): void
	+ addTipoEvento(String nombreTipoEvento, Integer importancia): void
	+ removeTipoEvento(String nombreTipoEvento): void
	+ esTipoEvento(String nombreTipoEvento): Boolean
	+ getEventos(String nombreTipoEvento, Date dataInici, Date dataFi): List<String>

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
