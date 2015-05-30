package controladores;

import java.util.Set;
import java.util.TreeSet;

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
public class ControladorDominioEvento extends ControladorDominio {
	private Conjunto<TipoEvento> conjuntoTipoEvento;
	private static ControladorDominioEvento instance = null;

		/**
		 * Crea un nuevo controlador de Dominio Evento
		 * 
		 */
	   protected ControladorDominioEvento(){
		   super();
		   conjuntoTipoEvento=  new Conjunto<TipoEvento>(TipoEvento.class);
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
		   else if(!super.hasError()){
			   error.setCodiError(15);
			   error.addClauExterna(nombreTipoEvento);
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
			else if(!super.hasError()){
				   error.addClauExterna(nombreDiputado);
				   error.setCodiError(3);
			}
			 return false;
		}
		
		/**
		 * Limpia el conjunto del Controlador
		 */
		public void clear(){
			conjuntoTipoEvento.clear();
		}
		/**
		 * 
		 * @return devuelve todo el contenido del controlador
		 */
		public Set<TipoEvento> getAll(){
			return conjuntoTipoEvento.getAll();
		}
		
		/**
		 * 
		 * @param nombreTipoEvento: nombre de la TipoEvento
		 * @return devuelve la Votacion correspondiente del nombreTipoEvento
		 */
		public TipoEvento get(String nombreTipoEvento){
			return conjuntoTipoEvento.get(nombreTipoEvento);
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
			else if(!super.hasError()){
			   error.addClauExterna(nombreEvento);
			   error.addClauExterna(nombreTipoEvento);
			   error.setCodiError(7);
		     } 
			return false;
		}
	
	   
		/**
		 * Modifica la importancia del Tipo Evento con nombre nombreTipoEvento
		 * Si el Tipo Evento no existe o la importancia no es valida, no se realiza la modificacion y captura el Error
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreTipoEvento
		 * @param Importancia
		 * 
		 */
	   public void setImportanciaTipoEvento(String nombreTipoEvento, Integer Importancia){
		   if(comprovacionTP(nombreTipoEvento)){
			   if(TipoEvento.esValidaImportancia(Importancia))conjuntoTipoEvento.get(nombreTipoEvento).setImportancia(Importancia);
			   else if(!super.hasError()){
				   error.setCodiError(9);
				   error.addClauExterna(Importancia.toString());
			   }
		   }
	   }
	   /**
	    * Devueleve la importancia del Tipo Evento con nombre nombreTipoEvento
		 * Si el Tipo Evento no existe, devuelve -1 y se captura el Error
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @return
	    */
	   public Integer getImportanciaTipoEvento(String nombreTipoEvento){
		   if(comprovacionTP(nombreTipoEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getImportancia();
		   return -1;
	   }
	   
	   /**
	    * Devuelve un el nombre de todos los Tipo Evento que existen
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @return nombre de los Tipo Evento que existen
	    */
	   public Set<String> getTipoEvento(){
		   return conjuntoTipoEvento.getStringKeys();
	   }
	   /**
	    * Devuelve los eventos del Tipo Evento con nombre nombreTipoEvento
	    * Si el Tipo Evento no existe, devuelve un set vacio y captura el error.
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
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
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
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
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
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
	    * Se incorpora un nuevo TipoEvento. <br>
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- El nombreTipoEvento ya existia <br>
	    * 2- La importancia no es valida <br>
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param importancia
	    */
	   public void addTipoEvento(String nombreTipoEvento, Integer importancia){
		   
		   if(conjuntoTipoEvento.exists(nombreTipoEvento)){
			   error.addClauExterna(nombreTipoEvento);
			   error.setCodiError(14);
		   }
		   else if(TipoEvento.esValidaImportancia(importancia)){ 
			   TipoEvento aux=new TipoEvento(nombreTipoEvento,importancia);
			   conjuntoTipoEvento.add(aux);
		   }
		   else{
			   error.setCodiError(9);
			   error.addClauExterna(importancia.toString());
		   }
	   }
	   /**
	    * Elimina el TipoEvento con nombre nombreTipoEvento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    *  <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    *  @param nombreTipoEvento
	    */
	   public void removeTipoEvento(String nombreTipoEvento){
		   
		   if(comprovacionTP(nombreTipoEvento))conjuntoTipoEvento.remove(nombreTipoEvento);
	   }

	   /**
	    * Nos indica si es un Tipo Evento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @return <i>true<i> si es un Tipo Evento, sino <i>false<i>
	    */
	   public Boolean esTipoEvento(String nombreTipoEvento){
		   return conjuntoTipoEvento.exists(nombreTipoEvento);
	   }
	   
	   /**
	    * Inserta un nuevo Evento al TipoEvento nombreTipoEvento <br>
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * 3- Alguno de los Diputados no existe
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param fecha
	    * @param Diputados
	    */
	   public void addEvento(String nombreTipoEvento, String nombreEvento, Date fecha, Set<String> Diputados){
		   
		   if(comprovacionTP(nombreTipoEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).esEvento(nombreEvento)){
				   error.addClauExterna(nombreEvento);
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
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    */
	   public void removeEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))conjuntoTipoEvento.get(nombreTipoEvento).removeEvento(nombreEvento);
	   }
	   /**
	    * Indica si es un Evento del Tipo Evento<br>
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
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
	    * Modifica la fecha de un Evento de un Tipo de Evento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param fecha
	    * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	    */
	   public void setFechaEvento(String nombreTipoEvento, String nombreEvento, Date fecha){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).setFecha(fecha);
	   }
	   /**
	    * Retorna la data de un Evento de un TipoEvento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @return Data del Evento y si hay error, una data NULL
	    */
	   public Date getFechaEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getFecha();
		   return Date.NULL;
	   }
	   /**
	    * Devuelve los diputados de un Evento de un tipo de Evento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @return devuelve los diputados de ese Evento, sino existe, esta vacio
	    */
	   public Set<String> getDiputadosEvento(String nombreTipoEvento, String nombreEvento){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).getdiputados();
		   return new TreeSet<String>();
	   }
	   /**
	    * Inserta un Diputado al Evento de un Tipo Evento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento
	    * 2- nombreEvento no es un Evento de ese TipoEvento
	    * 3- nombreDiputado no es un Diputado
	    * 4- Diputado ya ha participado en ese Evento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param nombreDiputado
	    */
	   public void addDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento)){
			   if(esDiputado(nombreDiputado)){
				   if(!conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).addDiputado(nombreDiputado);
				   else if(!super.hasError()){
					   error.addClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(6);
				   }
			   }
		   }
	   }
	   
	   /**
	    * Indica si un Diputado ha participado en un evento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento <br>
	    * 2- nombreEvento no es un Evento de ese TipoEvento <br>
	    * 3- nombreDiputado no es un Diputado
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param nombreDiputado
	    * @return <i>true si ha participado en ese Evento, sino, <i>false<i>
	    */
	   public Boolean esParticipanteEnEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento)&& esDiputado(nombreDiputado))return conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado);
		   else return false;
	   }
	   /**
	    * Elimina el Diputado de un evento de un TipoEvento
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
	    * 1- nombreTipoEvento no es un TipoEvento <br>
	    * 2- nombreEvento no es un Evento de ese TipoEvento <br>
	    * 3- nombreDiputado no ha participado en ese Evento
	    * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreTipoEvento
	    * @param nombreEvento
	    * @param nombreDiputado
	    */
	   public void removeDiputadoEvento(String nombreTipoEvento, String nombreEvento, String nombreDiputado){
		   if(comprovacionEvento(nombreTipoEvento,nombreEvento)){
			   if(conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).esParticipante(nombreDiputado))conjuntoTipoEvento.get(nombreTipoEvento).getEvento(nombreEvento).removeDiputado(nombreDiputado);
			   else if(!super.hasError()){
					   error.addClauExterna(nombreDiputado);
					   error.addClauExterna(nombreEvento);
					   error.addClauExterna(nombreTipoEvento);
					   error.setCodiError(5);
			   }
	      }
	   }
	   
	   
		public Set<TreeSet<String>> getlistatipo(){
			Set<TreeSet<String>> res= new TreeSet<TreeSet<String>>();
			for(TipoEvento elem:conjuntoTipoEvento.getAll()){
				TreeSet<String> linea=new TreeSet<String>();
				linea.add(elem.getNombre());
				linea.add(elem.getImportancia().toString());
				res.add(linea);
			}
			return res;
		}
		
		public Set<TreeSet<String>> getlistalevento(String nombreTipoEvento){
			Set<TreeSet<String>> res= new TreeSet<TreeSet<String>>();
			
			for(Evento elem: conjuntoTipoEvento.get(nombreTipoEvento).getEventos()){
				TreeSet<String> linea=new TreeSet<String>();
				linea.add(elem.getNombre());
				linea.add(elem.getFecha().toString());
				res.add(linea);
			}
			return res;
		}
	   
}
	
