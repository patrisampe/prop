package controladores;

import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Iterator;

import utiles.Atributos_Evento;
import utiles.Atributos_TipoEvento;
import utiles.Error;
import dominio.Tipo_de_Evento;
import dominio.Evento;


public class ControladorDominioEvento {

	private Map<String, Tipo_de_Evento> Conjunto_tipos;
	
	
	public ControladorDominioEvento(){
		Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	}
	
	public Error AddTipoEvento(String nombreTipoEvento, Integer importancia){
		
		if(Conjunto_tipos.containsKey(nombreTipoEvento))return new Error(12,nombreTipoEvento);
		Tipo_de_Evento aux=new Tipo_de_Evento(nombreTipoEvento,importancia);
		Conjunto_tipos.put(nombreTipoEvento, aux);
		return new Error(0,nombreTipoEvento);
	}
	
	public List<String> GetTipoEventos(){
		return new ArrayList<String>(Conjunto_tipos.keySet());
	}
	
	public List<String> GetEventos(String nombreTipoEvento){
		return Conjunto_tipos.get(nombreTipoEvento).GetEventos();
	}
	
	public Error SetAtributosTipoEvento(String nombreTipoEvento, Atributos_TipoEvento atributos){
		Tipo_de_Evento original = Conjunto_tipos.get(nombreTipoEvento);
		Tipo_de_Evento nuevo= new Tipo_de_Evento(nombreTipoEvento,original);
		
		Integer nuevaImportancia=atributos.getImportancia();
		
		if(!Tipo_de_Evento.es_validaImportancia(nuevaImportancia))return new Error(9,nombreTipoEvento);
		nuevo.SetImportancia(nuevaImportancia);
		
		String[] aux2;
		aux2 = atributos.getEventos();
		
		
		for(int i=0; i<aux2.length;++i){
			if(!nuevo.Es_Evento(aux2[i])) return new Error(7,aux2[i]);
			nuevo.RemoveEvento(aux2[i]);
		}
		Conjunto_tipos.put(nombreTipoEvento, nuevo);
		return new Error(0,nombreTipoEvento);
	}
	
	public Atributos_TipoEvento GetTipoEvento(String nombreTipoEvento){
		
		return new Atributos_TipoEvento(Conjunto_tipos.get(nombreTipoEvento).GetImportancia(),Atributos_TipoEvento.EventosUnchanged);
	}
	
	public Error RemoveTipoEvento(String nombreTipoEvento){
		if(!Conjunto_tipos.containsKey(nombreTipoEvento))return new Error(13,nombreTipoEvento);
		Conjunto_tipos.remove(nombreTipoEvento);
		return new Error(0,nombreTipoEvento);
	}
	
	public Boolean Es_TipoEvento(String nombreTipoEvento){
		return Conjunto_tipos.containsKey(nombreTipoEvento);
	}
	
	
	public Error AddEvento(String nombreTipoEvento, String nombreEvento, Atributos_Evento atributos){
		Tipo_de_Evento tp= Conjunto_tipos.get(nombreTipoEvento);
		if(tp.Es_Evento(nombreEvento))return new Error(8,nombreEvento);
		if(!atributos.getFecha().Es_valida()) return new Error(2,nombreEvento);
		Evento event = new Evento(nombreEvento,atributos.getFecha());
		tp.AddEvento(event);
		return new Error(0,nombreTipoEvento);
	}

	
	public Error SetAtributosEvento(String nombreTipoEvento, String nombreEvento, Atributos_Evento atributos){
		
		
		if(!atributos.getFecha().Es_valida())return new Error(2,nombreEvento);
		Tipo_de_Evento original= Conjunto_tipos.get(nombreTipoEvento);
		Evento eoriginal=original.GetEvento(nombreEvento);
		Evento enuevo= new Evento(eoriginal.GetNombre(),eoriginal);
		enuevo.SetFecha(atributos.getFecha());
		
		Map<String,Boolean> dipu= atributos.getDiputados();
		Iterator<Entry<String, Boolean>> iterator = dipu.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Boolean> mapEntry = dipu.entrySet().iterator().next();
			if(mapEntry.getValue())
			{
				//insertamos evento
				if(enuevo.Es_participante(mapEntry.getKey()))return new Error(15,mapEntry.getKey());
				enuevo.AddDiputado(mapEntry.getKey());
			}
			else
			{
				//borramos evento
				if(!enuevo.Es_participante(mapEntry.getKey()))return new Error(14,mapEntry.getKey());
				enuevo.RemoveDiputado(mapEntry.getKey());
			}
		}
		original.AddEvento(enuevo);//remplazamos el evento que ya habia
		return new Error(0,nombreTipoEvento);
	}

	public Atributos_Evento GetAtributosEvento(String nombreTipoEvento, String nombreEvento){
		return new Atributos_Evento(Conjunto_tipos.get(nombreTipoEvento).GetEvento(nombreEvento).GetFecha(),Atributos_Evento.DiputadosUnchanged);
	}
	
}
