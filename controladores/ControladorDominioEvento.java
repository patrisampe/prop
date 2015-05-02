package controladores;

import java.util.Set;
import java.util.TreeSet;

import utiles.CodiError;
import utiles.Conjunto;
import dominio.Evento;
import dominio.TipoEvento;
import time.Date;
import time.DateInterval;

/**
 * Classe ControladorDominioEvento para la gestion tanto en conjunto como individualmente de los tipos de eventos, y en consequencia, eventos.
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 */
public class ControladorDominioEvento {
	private Conjunto<TipoEvento> conjuntoTipoEvento;
	private static ControladorDominioEvento instance = null;
	private CodiError error;
	private Boolean hasError;

		/**
		 * Crea un nuevo controlador de Dominio Evento
		 * 
		 */
	   protected ControladorDominioEvento(){
		   conjuntoTipoEvento=  new Conjunto<TipoEvento>(TipoEvento.class);
		   error = new CodiError();
		   hasError=false;
	   }
	   
	   /**
	    * Crea una nueva instancia de la classe.
	    * @return Nueva instancia del <i>singletone</i> de la clase.
	    */
	   
	   public static ControladorDominioEvento getInstance(){
	      if(instance == null) {
	         instance = new ControladorDominioEvento();  
	      }
	      return instance;
	   }
	   
	   /**
	    * Indica si en Tipo Evento existe o no y sino existe, captura el Error
	    * @param nombreTipoEvento
	    * @return <i>true<i> si el Tipo Evento existe, sino <i>false<i>
	    **/
	    private Boolean comprovacionTP(String nombreTipoEvento){
		   if(conjuntoTipoEvento.exists(nombreTipoEvento))return true;
		   else if(!hasError){
			   hasError=true;
			   error.setCodiError(15);
			   error.setClauExterna(nombreTipoEvento);
		   }
		   return false;
	   }
	    
	    /**
	     * Comprueva si el Diputado existe, y sino existe salta Error
	     * @param nombreDiputado
	     * @return <i>true<i> si el Diputado existe, sino <i>false<i>
	     */
		private Boolean esDiputado(String nombreDiputado){
			ControladorDominioDiputado CDD=ControladorDominioDiputado.getInstance();
			if(CDD.existsDiputado(nombreDiputado))return true;
			else if(!hasError){
				   hasError=true;
				   error.setClauExterna(nombreDiputado);
				   error.setCodiError(3);
			}
			 return false;
		}
		
		
		/**
		 * Comprueva si el Tipo Evento existe, sino captura Excepcion
		 * Tambien comprueva si el evento es de ese Tipo Evento, sino captura Error
		 * @param nombreTipoEvento
		 * @param nombreEvento
		 * @return <i>true<i> si el Evento es de ese Tipo Evento, sino <i>false<i>
		 */
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
	
	   
		/**
		 * Modifica la importancia del Tipo Evento con nombre nombreTipoEvento
		 * Si el Tipo Evento no existe o la importancia no es valida, no se realiza la modificacion y captura el Error
		 * @param nombreTipoEvento
		 * @param Importancia
		 * 
		 */
	   public void setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia){
		   if(comprovacionTP(nombreTipoEvento)){
			   if(TipoEvento.esValidaImportancia(Importancia))conjuntoTipoEvento.get(nombreTipoEvento).setImportancia(Importancia);
			   else if(!hasError){
				   hasError=true;
				   error.setCodiError(9);
				   error.setClauExterna(Importancia.toString());
			   }
		   }
	   }
	   /**
	    * Devueleve la importancia del Tipo Evento con nombre nombreTipoEvento
		 * Si el Tipo Evento no existe, devuelve -1 y se captura el Error
	    * @param nombreTipoEvento
	    * @return
	    */
	   public Integer getImportanciaTipoEvento(String nombreTipoEvento){
		   if(comprovacionTP(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getImportancia();
		   return -1;
	   }
	   
	   /**
	    * Devuelve un el nombre de todos los Tipo Evento que existen
	    * @return nombre de los Tipo Evento que existen
	    */
	   public Set<String> getTipoEvento(){
		   return conjuntoTipoEvento.getStringKeys();
	   }
	   /**
	    * Devuelve los eventos del Tipo Evento con nombre nombreTipoEvento
	    * Si el Tipo Evento no existe, devuelve un set vacio y captura el error.
	    * @param nombreTipoEvento
	    * @return los eventos de ese Tipo Evento, si existe
	    */
	   public Set<String> getEventos(String nombreTipoEvento){
		   if(comprovacionTP(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getNombreEventos();
		   return new TreeSet<String>();
	   }
	   
	   /**
	    * Devuelve los eventos del Tipo Evento con nombre nombreTipoEvento que esten entre las fechas dataInici y dataFi
	    * Si el Tipo Evento no existe, devuelve un set vacio y captura el error.<br>
	    * <dd><b>Precondition:</b><dd> dataInici<=dataFi
	    * @param nombreTipoEvento
	    * @param dataInici
	    * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	    * @param dataFi
	    * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	    * @return los eventos de ese Tipo Evento entre las dos fechas que se indica.
	    */
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
	   /**
	    * Elimina un Diputado de todos los eventos
	    * Si el diputado no existe se captura el error
	    * @param nombreDiputado
	    */
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
	  /**
	   * Si ha habido un error antes, nos deja los campos correspondientes como sino<br>
	   * Es necessario llamar esta funcion despues de usar cada funcion que capture un error.
	   */
	   public void netejaError(){
		   hasError=false;
		   error.netejaCodiError();
	   }
	   /**
	    * Se incorpora un nuevo TipoEvento. <br>
	    * Si el nombreTipoEvento ya existia o la importancia no es valida se captura excepcion y no se incorpora al Conjunto.
	    * @param nombreTipoEvento
	    * @param importancia
	    */
	   public void addTipoEvento(String nombreTipoEvento, Integer importancia){
		   
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   hasError=true;
			   error.setClauExterna(nombreTipoEvento);
			   error.setCodiError(14);
		   }
		   else if(TipoEvento.esValidaImportancia(importancia)){ 
			   TipoEvento aux=new TipoEvento(nombreTipoEvento,importancia);
			   conjuntoTipoEvento.add(nombreTipoEvento, aux);
		   }
		   else{
			   hasError=true;
			   error.setCodiError(9);
			   error.setClauExterna(importancia.toString());
		   }
	   }
	   /**
	    * Elimina el TipoEvento con nombre nombreTipoEvento
	    * Si no existe el TipoEvento, no se puede eliminar y se captura el Error.
	    * @param nombreTipoEvento
	    */
	   public void removeTipoEvento(String nombreTipoEvento){
		   
		   if(comprovacionTP(nombreTipoEvento))conjuntoTipoEvento.remove(nombreTipoEvento);
	   }

	   /**
	    * Indica si ha habido Error
	    * @return <i>true<i> si ha error, sino <i>false<i>
	    */
	   public Boolean getHasError() {
		  return hasError;
	   }
	   
	   /**
	    * Indica el error que se ha producido
	    * Solo llamar si ha habido error
	    * @return
	    */
	   public CodiError getError(){
		   return error;
	   }

	   /**
	    * Nos indica si es un Tipo Evento
	    * @param nombreTipoEvento
	    * @return <i>true<i> si es un Tipo Evento, sino <i>false<i>
	    */
	   public Boolean esTipoEvento(String nombreTipoEvento){
		   return conjuntoTipoEvento.exists(nombreTipoEvento);
	   }
	   
	   /**
	    * Inserta un nuevo Evento al TipoEvento nombreTipoEvento <br>
	    * Si el nombreTipoEvento no es un TipoEvento, si el Evento ya existe en ese TipoEvento o si alguno de los diputados no existe, el Evento no se inserta y se captura el error.
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param fecha
	    * @param Diputados
	    */
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
	   /**
	    * Elimina el Evento de ese Tipo Evento
	    * Si el TipoEvento no existe o el Evento no existe en ese Tipo Evento, no se elimina el Evento y se captura el error.
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    */
	   public void removeEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))conjuntoTipoEvento.get(nombreTipoEvento).removeEvento(nombreEvento);
	   }
	   /**
	    * Indica si es un Evento del Tipo Evento<br>
	    * Si no existe ese TipoEvento se devuelve <i>false<i> y se caputra el error.
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @return <i>true<i> si es un Evento de este Tipo, sino, false
	    */
	   public Boolean esEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionTP(nombreTipoEvento)){
			   return conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento);
		   }
		   return false;
	   }
	   /**
	    * 
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param fecha
	    */
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
	   
	   public Boolean esParticipanteEnEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado);
		   else return false;
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
	

