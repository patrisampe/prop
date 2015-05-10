package controladores;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import time.Date;
import time.DateInterval;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.TipoVoto;
import dominio.Votacion;
/**
 * Classe ControladorDominioVotacion para la gestion tanto en conjunto como individualmente de las votaciones
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 */
public class ControladorDominioVotacion {

	private Conjunto<Votacion> conjuntoVotacion;
	private static ControladorDominioVotacion instance = null;
	private CodiError error;
	private Boolean hasError;

	/**
	 * Crea un nuevo controlador de Dominio Evento
	 * 
	 */
	 protected ControladorDominioVotacion() {
	      // Exists only to defeat instantiation.
		   conjuntoVotacion=  new Conjunto<Votacion>(Votacion.class);
		   error = new CodiError();
		   hasError=false;
	   }
	   
	   /**
	    * Crea una nueva instancia de la classe.
	    * @return Nueva instancia del <i>singletone</i> de la clase.
	    */
	 public static ControladorDominioVotacion getInstance() {
	      if(instance == null) {
	         instance = new ControladorDominioVotacion();  
	      }
	      return instance;
	 }
	
	 private Boolean comprovaExsitenciaVotacion(String nombreVotacion){
		 if(conjuntoVotacion.exists(nombreVotacion))return true;
		 else if(!hasError){
				   hasError=true;
				   error.addClauExterna(nombreVotacion);
				   error.setCodiError(22);
			}
		 return false;
	 }
	 
		private Boolean esDiputado(String nombreDiputado){
			ControladorDominioDiputado CDD=ControladorDominioDiputado.getInstance();
			if(CDD.existsDiputado(nombreDiputado))return true;
			else if(!hasError){
				   hasError=true;
				   error.addClauExterna(nombreDiputado);
				   error.setCodiError(3);
			}
			 return false;
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
		 * Modifica la fecha de la Votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
	     * 1- nombreVotacion no es una Votacion
	     * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param fecha
		 * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
		 */
	 public void setFechaVotacion(String nombreVotacion, Date fecha){
		if(comprovaExsitenciaVotacion(nombreVotacion))conjuntoVotacion.get(nombreVotacion).setFecha(fecha);
	  }
	/**
	 * Modifica la importancia
	 * Causas por las que no se realiza la operacion y se captura el error:<br>
	 * 1- nombreVotacion no es una Votacion <br>
	 * 2- importancia no es una importancia valida
	 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	 * @param nombreVotacion
	 * @param importancia
	 */
	  public void setImportanciaVotacion(String nombreVotacion,Integer importancia){
		  if(comprovaExsitenciaVotacion(nombreVotacion)){
			   if(Votacion.esValidaImportancia(importancia)) conjuntoVotacion.get(nombreVotacion).setImportancia(importancia);
			   else if(!hasError){
				   hasError=true;
				   error.setCodiError(9);
				   error.addClauExterna(importancia.toString());
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
	    * Indica si ha habido Error
	    * @return <i>true<i> si ha error, sino <i>false<i>
	    */
		public Boolean getHasError(){
			return hasError;
		}
		/**
		 * Devuelve la fecha de la Votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @return fecha de la votacion. Si hay Error, Date.NULL
		 */
		public Date getFechaVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getFecha();
			return Date.NULL;
		}
		/**
		 * Devuelve importancia de la Votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @return importancia de la votacion. Si hay Error, -1
		 */
		public Integer getImportanciaVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getImportancia();
			return -1;
		}
		/**
		 * Devuelve los diputados que han votado en esa votacion 
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @return diputados que han votado en la votacion. Si hay Error, set vacio
		 */
		public Set<String> getDiputadosVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getDiputados();
			return new TreeSet<String>();
		}
		
		/**
		 * Devuelve los diputados que han votado en esa votacion lo mismo, concretamente <b>voto</b>
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param voto
		 * @return diputados que han votado en la votacion. Si hay Error, set vacio
		 */
		public Set<String> getDiputadosVotacion(String nombreVotacion, TipoVoto voto){
			Set<String> result= new TreeSet<String>();
			if(comprovaExsitenciaVotacion(nombreVotacion)){
				Map<String,TipoVoto> aux=conjuntoVotacion.get(nombreVotacion).getVotos();
				   for (Entry<String, TipoVoto> elem : aux.entrySet()){
					  if(elem.getValue()==voto)result.add(elem.getKey());
				   }
			}
			return result;
		}
		/**
		 * Indica si un Diputado es Votante en esa Votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * 2- nombreDiputado no es un Diputado <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param nombreDiputdo
		 * @return <i>true<i> si el diputado ha votado en esa Votacion, sino <i>false<i>
		 */
		public Boolean esVotanteEnVotacion(String nombreVotacion, String nombreDiputado){
			if(comprovaExsitenciaVotacion(nombreVotacion) & esDiputado(nombreDiputado))return conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado);
			return false;
		}
		/**
		 * Devuelve el nombre de todas las votacions
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @return nombre de todas las votaciones
		 */
		public Set<String> getVotaciones(){
			return conjuntoVotacion.getStringKeys();
		}
		/**
		 * Elimina la votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion no es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 */
		public void removeVotacion(String nombreVotacion){
			if(comprovaExsitenciaVotacion(nombreVotacion))conjuntoVotacion.remove(nombreVotacion);
		}
		/**
		 * Inserta una votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion es una Votacion <br>
		 * 2- hay como minimo un diputado que no esta activo en la fecha de la Votacion
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param fecha
		 * @param imp
		 * @param votos
		 */
		public void addVotacion(String nombreVotacion, Date fecha,Integer imp, Map<String,TipoVoto> votos){
			if(conjuntoVotacion.exists(nombreVotacion)){
				hasError=true;
				error.addClauExterna(nombreVotacion);
				error.setCodiError(23);
			}
			else{
				ControladorDominioLegislatura CDL= ControladorDominioLegislatura.getInstance();
				Integer leg=CDL.getID(fecha);
				if(leg!=-1){
					Set<String> dip=CDL.getDiputados(leg);
					Map<String,TipoVoto> votosnew=new TreeMap<String,TipoVoto>();
					for(String elem :dip){
						if(esDiputado(elem)){
							TipoVoto nouvot=TipoVoto.AUSENCIA;
							if(votos.containsKey(elem)){
							 nouvot=votos.get(elem);
							 votos.remove(elem);
							}
							votosnew.put(elem, nouvot);
						}
						else return;
					}
					if(votos.size()>0){
						hasError=true;
						error.addClauExterna(nombreVotacion);
						Iterator<String> it = votos.keySet().iterator();
						error.addClauExterna(it.next());
						error.setCodiError(25);
						return;
					}
					Votacion aux= new Votacion(nombreVotacion,fecha,imp,votosnew);
					conjuntoVotacion.add(aux);
				}
				else{
					hasError=true;
					error.addClauExterna(nombreVotacion);
					error.setCodiError(37);
					return;
				}
			
			}
		}
		/**
		 * Indica si es una Votacion
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @return <i>true</i> si es una votacion, sino <i>false</i>
		 */
		public Boolean esVotacion(String nombreVotacion){
			return conjuntoVotacion.exists(nombreVotacion);
		}
		
		/**
		 * Devuelve las parejas diputado-lo que ha votado, de todos los diputados que han votado en este evento
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion es una Votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @return devuelve un map con los diputados y su voto, si ha habido error el map esta vacio
		 */
		public Map<String,TipoVoto> getVotos(String nombreVotacion){
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getVotos();
			return new TreeMap<String,TipoVoto>();
		}
		/**
		 * Devuelve que ha votado el diputado nombreDiputado en la Votacion nombreVotacion
		 * * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion es una Votacion <br>
		 * 2- nombreDiputado no existe
		 * 3- nombreDiputado no es votante
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param nombreDiputado
		 * @return el voto del diputado en esa votacion, si ha habido error ABSTENCION
		 */
		public TipoVoto getVotoDiputado(String nombreVotacion, String nombreDiputado){
				if(esVotanteEnVotacion(nombreVotacion, nombreDiputado))return conjuntoVotacion.get(nombreVotacion).getVoto(nombreDiputado);
				else if(!hasError){
					hasError=true;
					error.addClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			return TipoVoto.ABSTENCION;
			
		}
		/**
		 * Inserta o modifica un vot, es decir, una pareja: Diputado y lo que ha votado en esa votacion
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreVotacion es una Votacion <br>
		 * 2- nombreDiputado no es un Diputado <br>
		 * 3- nombreDiputado no es un Diputaado activo durante la realizacion de la votacion <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreVotacion
		 * @param nombreDiputado
		 * @param voto - lo que ha votado el Diputado con nombreDiputado
		 */
		public void setAddVoto(String nombreVotacion, String nombreDiputado, TipoVoto voto){
			if(comprovaExsitenciaVotacion(nombreVotacion) && esDiputado(nombreDiputado)){
				ControladorDominioLegislatura CDL=ControladorDominioLegislatura.getInstance();
				Integer leg=CDL.getID(conjuntoVotacion.get(nombreVotacion).getFecha());
				if(CDL.existsDiputado(leg, nombreDiputado))conjuntoVotacion.get(nombreVotacion).setaddVoto(nombreDiputado, voto);
				else{
						hasError=true;
						error.addClauExterna(nombreDiputado);
						error.addClauExterna(nombreVotacion);
						error.setCodiError(25);
					}
			}
		}
	   /**
	    * Elimina el diputado de la votacion, y en consequencia su voto
	    * Causas por las que no se realiza la operacion y se captura el error:<br>
		* 1- nombreVotacion es una Votacion <br>
		* 2- nombreDiputado no ha votado en esta votacion
		* 3- nombreDiputado esta activo durante la votacion
		* <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
	    * @param nombreVotacion
	    * @param nombreDiputado
	    */
		public void removeVotoDiputado(String nombreVotacion, String nombreDiputado){
			if(comprovaExsitenciaVotacion(nombreVotacion)){
				if(esVotanteEnVotacion(nombreVotacion, nombreDiputado)){
							ControladorDominioLegislatura CDL=ControladorDominioLegislatura.getInstance();
							Integer leg=CDL.getID(conjuntoVotacion.get(nombreVotacion).getFecha());
							if(CDL.existsDiputado(leg, nombreDiputado)){
								hasError=true;
								error.addClauExterna(nombreVotacion);
								error.addClauExterna(nombreDiputado);
								error.setCodiError(36);
							}
							else conjuntoVotacion.get(nombreVotacion).removeVoto(nombreDiputado);
				}
				else if(!hasError){
					hasError=true;
					error.addClauExterna(nombreDiputado);
					error.addClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			}
		}
		/**
		 * Devuelve las votacions que se han realizado durante un periodo de tiempo
		 *  <dd><b>Precondition:</b> dataInici<=dataFi
		 * @param dataInici
		 * @param dataFi
		 * @return devuelve el nombre de la votacions que se han realizado durante dicho periodo
		 */
		public Set<String> getVotaciones(Date dataInici, Date dataFi){
			Set<String>result=new TreeSet<String>();
			   DateInterval inter= new DateInterval(dataInici,dataFi);
			   
			   for (Votacion elem : conjuntoVotacion.getAll()){
					if(inter.contains(elem.getFecha()))result.add(elem.getNombre());
			   }
			return result;
		}
		/**
		 * Elimina el diputado de todas las votaciones
		 * Causas por las que no se realiza la operacion y se captura el error:<br>
		 * 1- nombreDiputado no es un Diputado <br>
		 * <dd><b>Precondition:</b><dd> Tienes de haber limpiado el error
		 * @param nombreDiputado
		 */
		public void removeDiputado(String nombreDiputado){
			 if(esDiputado(nombreDiputado)){
				 for (Votacion elem : conjuntoVotacion.getAll()){
					 if(elem.esVotante(nombreDiputado)){
						ControladorDominioLegislatura CDL=ControladorDominioLegislatura.getInstance();
						Integer leg=CDL.getID(elem.getFecha());
						if(!CDL.existsDiputado(leg, nombreDiputado))elem.removeVoto(nombreDiputado);
					 }
				 }
			 }
		
		}
}


