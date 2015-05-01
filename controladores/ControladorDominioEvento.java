package controladores;

import java.util.Set;
import java.util.TreeSet;

import utiles.CodiError;
import utiles.Conjunto;
import dominio.Evento;
import dominio.TipoEvento;
import time.Date;
import time.DateInterval;


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
	   
	   private Boolean comprovacionTP(String nombreTipoEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento))return true;
		   else if(!hasError){
			   hasError=true;
			   error.setCodiError(15);
			   error.setClauExterna(nombreTipoEvento);
		   }
		   return false;
	   }
		private Boolean esDiputado(String nombreDiputado){
			ControladorDominioDiputado CDD=new ControladorDominioDiputado();
			if(CDD.existsDiputado(nombreDiputado))return true;
			else if(!hasError){
				   hasError=true;
				   error.setClauExterna(nombreDiputado);
				   error.setCodiError(3);
			}
			 return false;
		}
		
		private Boolean comprovacionEvento(String nombreTipoEvento, String nombreEvento){
			if(comprovacionTP(nombreTipoEvento) && conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento))return true;
			else if(!hasError){
			   hasError=true;
			   error.setClauExterna(nombreEvento);
			   error.addClauExterna(nombreTipoEvento);
			   error.setCodiError(7);
		     } 
			return false;
		}
	
	   
	   public void setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia){
		   if(comprovacionTP(nombreTipoEvento)){
			   if(TipoEvento.esValidaImportancia(Importancia))conjuntoTipoEvento.get(nombreTipoEvento).setImportancia(Importancia);
			   else if(!hasError){
				   hasError=true;
				   error.setCodiError(9);
				   error.setClauExterna(nombreTipoEvento);
			   }
		   }
	   }
	   
	   public Integer getImportanciaTipoEvento(String nombreTipoEvento){
		   if(comprovacionTP(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getImportancia();
		   return -1;
	   }
	   
	   public Set<String> getTipoEvento(){
		   return conjuntoTipoEvento.getStringKeys();
	   }
	   
	   public Set<String> getEventos(String nombreTipoEvento){
		   if(comprovacionTP(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getNombreEventos();
		   return new TreeSet<String>();
	   }
	   
	   public Set<String> getEventos(String nombreTipoEvento, Date dataInici, Date dataFi){
		   Set<String> result=new TreeSet<String>();
		   if(comprovacionTP(nombreTipoEvento)){
			   Set<Evento> aux= conjuntoTipoEvento.get(nombreTipoEvento).getEventos();
			   DateInterval inter= new DateInterval(dataInici,dataFi);		   
			   for (Evento elem : aux) {
				   if(inter.contains(elem.getFecha()))result.add(elem.getNombre());
			   }
		   }
		   return result;
	   }
	   
	   public void removeDiputado(String nombreDiputado){
				if(esDiputado(nombreDiputado)){
					
					Set<TipoEvento> tp= conjuntoTipoEvento.getAll();
					 for (TipoEvento tpelem : tp){
						 
						  Set<Evento> ev= tpelem.getEventos();
						   for (Evento evelem : ev){
							   if(evelem.esParticipante(nombreDiputado))evelem.removeDiputado(nombreDiputado);
						   }
					   }
				}
	   }
	  
	   public void netejaError(){
		   hasError=false;
	   }
	   
	   public void addTipoEvento(String nombreTipoEvento, Integer importancia){
		   TipoEvento aux=new TipoEvento(nombreTipoEvento,importancia);
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(14);
		   }
		   else conjuntoTipoEvento.add(nombreTipoEvento, aux);
	   }
	   
	   public void removeTipoEvento(String nombreTipoEvento){
		   
		   if(comprovacionTP(nombreTipoEvento))conjuntoTipoEvento.remove(nombreTipoEvento);
	   }

	   public Boolean getHasError() {
		  return hasError;
	   }
	   
	   
	   public CodiError getError(){
		   return error;
	   }

	   public Boolean esTipoEvento(String nombreTipoEvento){
		   return conjuntoTipoEvento.exists(nombreTipoEvento);
	   }
	   
	   public void addEvento(String nombreTipoEvento, String nombreEvento, Date fecha, Set<String> Diputados){
		   
		   if(comprovacionTP(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento)){
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(8);
			   }
			   else{
				   for (String nombreDiputado : Diputados) {
						if(!esDiputado(nombreDiputado))return;
				   }
				   Evento aux=new Evento(nombreEvento,fecha,Diputados);
				   conjuntoTipoEvento.get(nombreTipoEvento).addEvento(aux);
			   }
		   }
			  
	   }
	   
	   public void removeEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))conjuntoTipoEvento.get(nombreTipoEvento).removeEvento(nombreEvento);
	   }
	   
	   public Boolean esEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionTP(nombreTipoEvento)){
			   return conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento);
		   }
		   return false;
	   }
	   
	   public void setFechaEvento(String nombreTipoEvento, String nombreEvento, Date fecha){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).setFecha(fecha);
	   }
	   
	   public Date getFechaEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getFecha();
		   return Date.NULL;
	   }
	   
	   public Set<String> getDiputadosEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getdiputados();
		   return new TreeSet<String>();
	   }
	   
	   public void addDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento)){
				   if(!conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).addDiputado(nombreDiputado);
				   else if(!hasError){
					   hasError=true;
					   error.setClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(6);
				   }
		   }
	   }
	   
	   public void removeDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento)){
			   if(!conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).removeDiputado(nombreDiputado);
			   else if(!hasError){
					   hasError=true;
					   error.setClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(5);
			   }
	      }
	   }
	   
}
	

