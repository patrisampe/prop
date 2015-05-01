package controladores;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import time.Date;
import time.DateInterval;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.Evento;
import dominio.TipoVoto;
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
	
	 public void setFechaVotacion(String nombreVotacion, Date fecha){
		if(conjuntoVotacion.exists(nombreVotacion))conjuntoVotacion.get(nombreVotacion).setFecha(fecha);
		else{
			   hasError=true;
			   error.setClauExterna(nombreVotacion);
			   error.setCodiError(22);
		}
	  }
	
	  public void setImportanciaVotacion(String nombreVotacion,Integer importancia){
		if(conjuntoVotacion.exists(nombreVotacion))conjuntoVotacion.get(nombreVotacion).setImportancia(importancia);
		else{
			   hasError=true;
			   error.setClauExterna(nombreVotacion);
			   error.setCodiError(22);
		 }
	  }
	
	
	   public void netejaError(){
		   hasError=false;
	   }
	   
		public Boolean getHasError(){
			return hasError;
		}
		
		public Date getFechaVotacion(String nombreVotacion){
			
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getFecha();
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return Date.NULL;
			 }
		}
		
		public Integer getImportanciaVotacion(String nombreVotacion){
			
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getImportancia();
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return -1;
			 }
		}
		
		public Set<String> getDiputadosVotacion(String nombreVotacion){
			
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getDiputados();
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return new TreeSet<String>();
			 }
		}	
		public Set<String> getDiputadosVotacion(String nombreVotacion, TipoVoto voto){
			Set<String> result= new TreeSet<String>();
			if(conjuntoVotacion.exists(nombreVotacion)){
				Map<String,TipoVoto> aux=conjuntoVotacion.get(nombreVotacion).getVotos();
				   for (Entry<String, TipoVoto> elem : aux.entrySet()){
					  if(elem.getValue()==voto)result.add(elem.getKey());
				   }
			}
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
			 }
			return result;
		}
		
		public Boolean esVotanteEnVotacion(String nombreVotacion, String nombreDiputado){
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado);
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return false;
			 }
		}
		
		public Set<String> getVotaciones(){
			return conjuntoVotacion.getStringKeys();
		}
		
		public void removeVotacion(String nombreVotacion){
			if(conjuntoVotacion.exists(nombreVotacion))conjuntoVotacion.remove(nombreVotacion);
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
			 }
		}
		
		public void adddVotacion(String nombreVotacion, Integer Importancia, Date fecha){
			if(conjuntoVotacion.exists(nombreVotacion)){
				hasError=true;
				error.setClauExterna(nombreVotacion);
				error.setCodiError(23);
			}
			else{
				Votacion aux= new Votacion(nombreVotacion,fecha,Importancia);
				conjuntoVotacion.add(nombreVotacion, aux);
			}
			
		}
		
		public Boolean esVotacion(String nombreVotacion){
			
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.exists(nombreVotacion);
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return false;
			 }
			
		}
		
		public Map<String,TipoVoto> getVotos(String nombreVotacion){
			if(conjuntoVotacion.exists(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getVotos();
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
				   return new TreeMap<String,TipoVoto>();
			 }
		}
		
		public TipoVoto getVotoDiputado(String nombreVotacion, String nombreDiputado){
			if(conjuntoVotacion.exists(nombreVotacion)){
				if(conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado)){
					return conjuntoVotacion.get(nombreVotacion).getVoto(nombreDiputado);
				}
				else{
					hasError=true;
					error.setClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			}
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
			 }
			return TipoVoto.ABSTENCION;
			
		}
		
		public void setAddVoto(String nombreVotacion, String nombreDiputado, TipoVoto voto){
			if(conjuntoVotacion.exists(nombreVotacion)){
					conjuntoVotacion.get(nombreVotacion).addSetVoto(nombreDiputado, voto);
			}
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
			 }
		}
	   
		public void removeVotoDiputado(String nombreVotacion, String nombreDiputado){
			if(conjuntoVotacion.exists(nombreVotacion)){
				if(conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado)){
					conjuntoVotacion.get(nombreVotacion).removeVoto(nombreDiputado);
				}
				else{
					hasError=true;
					error.setClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			}
			else{
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
			 }
			
		}
		
		public Set<String> getVotaciones(Date dataInici, Date dataFi){
			Set<String>result=new TreeSet<String>();
			   DateInterval inter= new DateInterval(dataInici,dataFi);
			   for (Entry<String, Evento> elem : conjuntoVotacion.entrySet()){
					if(inter.contains(elem.getValue().getFecha()))result.add(elem.getKey());
			   }
			return result;
		}
		
}
