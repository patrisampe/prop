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

public class ControladorDominioVotacion {

	private Conjunto<Votacion> conjuntoVotacion;
	private static ControladorDominioVotacion instance = null;
	private CodiError error;
	private Boolean hasError;


	 protected ControladorDominioVotacion() {
	      // Exists only to defeat instantiation.
		   conjuntoVotacion=  new Conjunto<Votacion>(Votacion.class);
		   error = new CodiError();
	   }
	   

	 public static ControladorDominioVotacion getInstance() {
	      if(instance == null) {
	         instance = new ControladorDominioVotacion();  
	      }
	      return instance;
	 }
	
	 public void removeDiputado(String nombreDiputado){
		 for (Votacion elem : conjuntoVotacion.getAll()){
			 if(elem.esVotante(nombreDiputado))elem.removeVoto(nombreDiputado);
		 }
	 }
	 
	 private Boolean comprovaExsitenciaVotacion(String nombreVotacion){
		 if(conjuntoVotacion.exists(nombreVotacion))return true;
		 else if(!hasError){
				   hasError=true;
				   error.setClauExterna(nombreVotacion);
				   error.setCodiError(22);
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
	
	 public void setFechaVotacion(String nombreVotacion, Date fecha){
		if(comprovaExsitenciaVotacion(nombreVotacion))conjuntoVotacion.get(nombreVotacion).setFecha(fecha);
	  }
	
	  public void setImportanciaVotacion(String nombreVotacion,Integer importancia){
		  if(comprovaExsitenciaVotacion(nombreVotacion))conjuntoVotacion.get(nombreVotacion).setImportancia(importancia);
	  }
	
	
	   public void netejaError(){
		   hasError=false;
	   }
	   
		public Boolean getHasError(){
			return hasError;
		}
		
		public Date getFechaVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getFecha();
			return Date.NULL;
		}
		
		public Integer getImportanciaVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getImportancia();
			return -1;
		}
		
		public Set<String> getDiputadosVotacion(String nombreVotacion){
			
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getDiputados();
			return new TreeSet<String>();
		}	
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
		
		public Boolean esVotanteEnVotacion(String nombreVotacion, String nombreDiputado){
			if(comprovaExsitenciaVotacion(nombreVotacion) & esDiputado(nombreDiputado) & conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado))return conjuntoVotacion.get(nombreVotacion).esVotante(nombreDiputado);
			return false;
		}
		
		public Set<String> getVotaciones(){
			return conjuntoVotacion.getStringKeys();
		}
		
		public void removeVotacion(String nombreVotacion){
			if(comprovaExsitenciaVotacion(nombreVotacion))conjuntoVotacion.remove(nombreVotacion);
		}
		
		public void addVotacion(String nombreVotacion, Integer Importancia, Date fecha, Map<String,TipoVoto> votos){
			if(conjuntoVotacion.exists(nombreVotacion)){
				hasError=true;
				error.setClauExterna(nombreVotacion);
				error.setCodiError(23);
			}
			else{
				ControladorDominioLegislatura CDL= new ControladorDominioLegislatura();
				Integer leg=CDL.getID(fecha);
				Set<String> dip=CDL.getDiputados(leg);
				Map<String,TipoVoto> votosnew=new TreeMap<String,TipoVoto>();
				for(String elem :dip){
					if(esDiputado(elem)){
						TipoVoto nouvot=TipoVoto.ABSTENCION;
						if(votos.containsKey(dip)){
						 nouvot=votos.get(elem);
						 votos.remove(elem);
						}
						votosnew.put(elem, nouvot);
					}
					else return;
				}
				if(votos.size()>0){
					hasError=true;
					error.setClauExterna(nombreVotacion);
					Iterator<String> it = votos.keySet().iterator();
					error.addClauExterna(it.next());
					error.setCodiError(25);
					return;
				}
				Votacion aux= new Votacion(nombreVotacion,fecha,Importancia,votosnew);
				conjuntoVotacion.add(nombreVotacion, aux);
			}
			
		}
		
		public Boolean esVotacion(String nombreVotacion){
			return conjuntoVotacion.exists(nombreVotacion);
		}
		
		public Map<String,TipoVoto> getVotos(String nombreVotacion){
			if(comprovaExsitenciaVotacion(nombreVotacion))return conjuntoVotacion.get(nombreVotacion).getVotos();
			return new TreeMap<String,TipoVoto>();
		}
		
		public TipoVoto getVotoDiputado(String nombreVotacion, String nombreDiputado){
				if(esVotanteEnVotacion(nombreVotacion, nombreDiputado))return conjuntoVotacion.get(nombreVotacion).getVoto(nombreDiputado);
				else if(!hasError){
					hasError=true;
					error.setClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			return TipoVoto.ABSTENCION;
			
		}
		
		public void setAddVoto(String nombreVotacion, String nombreDiputado, TipoVoto voto){
			if(comprovaExsitenciaVotacion(nombreVotacion) && esDiputado(nombreDiputado))conjuntoVotacion.get(nombreVotacion).addSetVoto(nombreDiputado, voto);
		}
	   
		public void removeVotoDiputado(String nombreVotacion, String nombreDiputado){
			if(comprovaExsitenciaVotacion(nombreVotacion)){
				if(esVotanteEnVotacion(nombreVotacion, nombreDiputado)){
					conjuntoVotacion.get(nombreVotacion).removeVoto(nombreDiputado);
				}
				else if(!hasError){
					hasError=true;
					error.setClauExterna(nombreVotacion);
					error.setCodiError(24);
				}
			}
		}
		
		public Set<String> getVotaciones(Date dataInici, Date dataFi){
			Set<String>result=new TreeSet<String>();
			   DateInterval inter= new DateInterval(dataInici,dataFi);
			   
			   for (Votacion elem : conjuntoVotacion.getAll()){
					if(inter.contains(elem.getFecha()))result.add(elem.getNombre());
			   }
			return result;
		}
		
}
