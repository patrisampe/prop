package dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Tipo_de_Evento {

	private String Nombre;
	private Integer Importancia;
	private Map<String,Evento> Conjunto_eventos;
	
	//private static Map<String, Tipo_de_Evento> Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	//private static Tipo_de_Evento NULL = new Tipo_de_Evento("NULL", 0);

	public Tipo_de_Evento(String Nombre, Integer Importancia) {
		
		this.Nombre=Nombre;
		this.Importancia=Importancia;
		Conjunto_eventos=new TreeMap<String, Evento>();
	}
	
	public Tipo_de_Evento(String Nombre, Tipo_de_Evento TP){
		this.Nombre=Nombre;
		Importancia=TP.Importancia;
		Conjunto_eventos=TP.Conjunto_eventos;
	}
	/*
	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}
	*/
	public String GetNombre(){
		return Nombre;
	} 
	
	public List<String> GetEventos(){
		List<String> list = new ArrayList<String>(Conjunto_eventos.keySet());
		return list;
	}
	
	public Integer GetImportancia(){
		return Importancia;
	}
	
	
	public Boolean SetImportancia(Integer nuevaImportancia){
		if(nuevaImportancia.equals(0))return false;
		Importancia=nuevaImportancia;
		return true;
	}

	public Boolean AddEvento(Evento nuevoEvento){
		if(Conjunto_eventos.containsKey(nuevoEvento.GetNombre()))return false;
		Conjunto_eventos.put(nuevoEvento.GetNombre(), nuevoEvento);
		return true;
	}

	public Evento GetEvento(String nombreEvento){
		//pre: nombreEvento ha de ser un Evento de ese tipo
		return Conjunto_eventos.get(nombreEvento);
		//return Conjunto_eventos.getOrDefault(nombreEvento, Evento.NULL);
	}
	
	public Boolean Existe_evento(String nombreEvento){
		return Conjunto_eventos.containsKey(nombreEvento);
	}
	
	public Boolean RemoveEvento(String nombreEvento){
		if(!Conjunto_eventos.containsKey(nombreEvento))return false;
		Conjunto_eventos.remove(nombreEvento);
		return true;
	}

}

