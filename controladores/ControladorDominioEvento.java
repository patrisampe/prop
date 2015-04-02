package controladores;

import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Iterator;

import utiles.Atributos_Evento;
import utiles.Atributos_TipoEvento;
import dominio.Tipo_de_Evento;
import dominio.Evento;


public class ControladorDominioEvento {

	private Map<String, Tipo_de_Evento> Conjunto_tipos;
	
	
	public ControladorDominioEvento(){
		Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	}
	
	public Boolean AddTipoEvento(String nombreTipoEvento, Integer importancia){
		if(Conjunto_tipos.containsKey(nombreTipoEvento))return false;
		Tipo_de_Evento aux=new Tipo_de_Evento(nombreTipoEvento,importancia);
		Conjunto_tipos.put(nombreTipoEvento, aux);
		return true;
	}
	
	public List<String> GetTipoEventos(){
		return new ArrayList<String>(Conjunto_tipos.keySet());
	}
	
	public List<String> GetEventos(String nombreTipoEvento){
		return Conjunto_tipos.get(nombreTipoEvento).GetEventos();
	}
	
	public Integer SetTipoEvento(String nombreTipoEvento, Atributos_TipoEvento atributos){
		Tipo_de_Evento original = Conjunto_tipos.get(nombreTipoEvento);
		Tipo_de_Evento nuevo= new Tipo_de_Evento(nombreTipoEvento,original);
		if(!nuevo.SetImportancia(atributos.getImportancia()))return 0;
		String[] aux2;
		aux2 = atributos.getEventos();
		
		
		for(int i=0; i<aux2.length;++i){
			if(!nuevo.RemoveEvento(aux2[i]))return i+1;
		}
		
		Conjunto_tipos.remove(nombreTipoEvento);
		Conjunto_tipos.put(nombreTipoEvento, nuevo);

		return -1;
	}
	
	public Atributos_TipoEvento GetTipoEvento(String nombreTipoEvento){
		
		return new Atributos_TipoEvento(Conjunto_tipos.get(nombreTipoEvento).GetImportancia());
	}
	
	public Boolean RemoveTipoEvento(String nombreTipoEvento){
		if(!Conjunto_tipos.containsKey(nombreTipoEvento))return false;
		Conjunto_tipos.remove(nombreTipoEvento);
		return true;
	}
	
	public Boolean Es_TipoEvento(String nombreTipoEvento){
		return Conjunto_tipos.containsKey(nombreTipoEvento);
	}
	
	
	public Boolean AddEvento(String nombreTipoEvento, String nombreEvento, Atributos_Evento atributos){
		Evento aux= new Evento(nombreEvento,atributos.getFecha());
		Tipo_de_Evento aux2= Conjunto_tipos.get(nombreTipoEvento);
		return aux2.AddEvento(aux);
	}

	
	public Integer SetAtributosEvento(String nombreTipoEvento, String nombreEvento, Atributos_Evento atributos){
		
		Tipo_de_Evento original= Conjunto_tipos.get(nombreTipoEvento);
	
		
		Evento eoriginal=original.GetEvento(nombreEvento);
		Evento enuevo= new Evento(eoriginal.GetNombre(),eoriginal);
		
		if(enuevo.Es_null())return -2;
		if(!enuevo.SetFecha(atributos.getFecha()))return 0;
		int i=1;
		Map<String,Boolean> dipu= atributos.getDiputados();
		Iterator<Entry<String, Boolean>> iterator = dipu.entrySet().iterator();
		while (iterator.hasNext()) {
			int error;
			Entry<String, Boolean> mapEntry = dipu.entrySet().iterator().next();
			if(mapEntry.getValue())error=enuevo.RemoveDiputado(mapEntry.getKey());
			else  error=enuevo.AddDiputado(mapEntry.getKey());
			
			++i;
		}
	 					
		return 1;
	}

	public Atributos_Evento GetAtributosEvento(String nombreTipoEvento, String nombreEvento){
		return new Atributos_Evento(Conjunto_tipos.get(nombreTipoEvento).GetEvento(nombreEvento).GetFecha());
	}
	
}
