package controladores;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

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
	   
	   
	   public void setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia){
		   
		   if(TipoEvento.esValidaImportancia(Importancia)){
			   if(conjuntoTipoEvento.exists(nombreTipoEvento))conjuntoTipoEvento.get(nombreTipoEvento).setImportancia(Importancia);
			   else{
				   hasError=true;
				   error.setCodiError(15);
				   error.setClauExterna(nombreTipoEvento);
			   }
		   }
		   else{
			   hasError=true;
			   error.setCodiError(9);
			   error.setClauExterna(nombreTipoEvento);
		   }
		   
	   }
	   
	   public Integer getImportanciaTipoEvento(String nombreTipoEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getImportancia();
		   else{
			   hasError=true;
			   error.setCodiError(15);
			   error.setClauExterna(nombreTipoEvento);
			   return -1;
		   } 

	   }
	   
	   public Set<String> getTipoEvento(){
		   return conjuntoTipoEvento.getStringKeys();
	   }
	   
	   public Set<String> getEventos(String nombreTipoEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEventos();
		   else{
			   hasError=true;
			   error.setCodiError(15);
			   error.setClauExterna(nombreTipoEvento);
			   return new TreeSet<String>();
		   }
		   
	   }
	   
	   public Set<String> getEventos(String nombreTipoEvento, Date dataInici, Date dataFi){
		   Set<String> result=new TreeSet<String>();
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   Conjunto<Evento> aux= conjuntoTipoEvento.get(nombreTipoEvento).getEventosmap();
			   DateInterval inter= new DateInterval(dataInici,dataFi);
			   for (Entry<String, Evento> elem : aux.entrySet()){
					if(inter.contains(elem.getValue().getFecha()))result.add(elem.getKey());
			   }
		   }
		   else{
			   hasError=true;
			   error.setCodiError(15);
			   error.setClauExterna(nombreTipoEvento);
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
		   
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   conjuntoTipoEvento.remove(nombreTipoEvento);
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   } 
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
		   Evento aux=new Evento(nombreEvento,fecha,Diputados);
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento)){
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(8);
			   }
			   else conjuntoTipoEvento.get(nombreTipoEvento).addEvento(aux);
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
			  
	   }
	   
	   public void removeEvento(String nombreTipoEvento, String nombreEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento))
				   	conjuntoTipoEvento.get(nombreTipoEvento).removeEvento(nombreEvento);
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
		   
		   
	   }
	   
	   public Boolean esEvento(String nombreTipoEvento, String nombreEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   return conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento);
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
			   return false;
		   }
	   }
	   
	   public void setFechaEvento(String nombreTipoEvento, String nombreEvento, Date fecha){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento))
				   	conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).setFecha(fecha);
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
		   
	   }
	   
	   public Date getFechaEvento(String nombreTipoEvento, String nombreEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento))
				   	return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getFecha();
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
		   return Date.NULL;
	   }
	   
	   public Set<String> getDiputadosEvento(String nombreTipoEvento, String nombreEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento))
				   	return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getdiputados();
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
		   return new TreeSet<String>();
	   }
	   
	   public void addDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento)){
				   if(conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado)){
					   hasError=true;
					   error.setClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(6);
				   }
				   else conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).addDiputado(nombreDiputado);
			   }
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
	   }
	   
	   public void removeDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento)){
				   if(conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado)){
					   conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).removeDiputado(nombreDiputado);
				   }
				   else{
					   hasError=true;
					   error.setClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(5);
				   }
			   }
			   else{
				   hasError=true;
				   error.setClauExterna(nombreEvento);
				   error.addClauExterna(nombreTipoEvento);
				   error.setCodiError(7);
			   } 
		   }
		   else{
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(15);
		   }
	   }	   
	   
}
	

