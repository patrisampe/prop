package dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Tipo_de_Evento {

	private String Nombre;
	private Integer Importancia;
	private Map<String,Evento> Conjunto_eventos;
	
	private static Map<String, Tipo_de_Evento> Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	private static Tipo_de_Evento NULL = new Tipo_de_Evento("NULL", 0);
	public static final Integer importancia_max=5;
	public static final Integer importancia_min=1;

	public Tipo_de_Evento(String Nombre, Integer Importancia) {
		//Pre: importancia_min<=Importancia<=importancia_max
		this.Nombre=Nombre;
		this.Importancia=Importancia;
		Conjunto_eventos=new TreeMap<String, Evento>();
	}
	
	public Tipo_de_Evento(String Nombre, Tipo_de_Evento TP){
		//Pre: importancia_min<=Importancia<=importancia_max
		this.Nombre=Nombre;
		this.Importancia=TP.Importancia;
		this.Conjunto_eventos=TP.Conjunto_eventos;
	}
	
	public Boolean ImportanciaValida(int Importancia){
		if(importancia_min<=Importancia )
	}
	
	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}
	
	public List<String> GetEventos(){
		return new ArrayList<String>(Conjunto_eventos.keySet());
	}
	
	public Integer GetImportancia(){
		return Importancia;
	}
	
	public Boolean SetImportancia(Integer nuevaImportancia){
		if(nuevaImportancia.)return false;
		Importancia=nuevaImportancia;
		return true;
	}

	public Boolean AddEvento(Evento nuevoEvento){
		if(Conjunto_eventos.containsKey(nuevoEvento.GetNombre()))return false;
		Conjunto_eventos.put(nuevoEvento.GetNombre(), nuevoEvento);
		return true;
	}

	public Boolean Es_Evento(String nombreEvento){
		return Conjunto_eventos.containsKey(nombreEvento);
	}
	
	public Evento GetEvento(String nombreEvento){
	//pre: nombreEvento ha de ser un Evento de ese tipo	
		return Conjunto_eventos.get(nombreEvento);
	}
	
	public Boolean RemoveEvento(String nombreEvento){
		if(!Conjunto_eventos.containsKey(nombreEvento))return false;
		Conjunto_eventos.remove(nombreEvento);
		return true;
	}
	
	public static Boolean AddTipo(Tipo_de_Evento nuevoTipo){
		if(Conjunto_tipos.containsKey(nuevoTipo.Nombre))return false;
		Conjunto_tipos.put(nuevoTipo.Nombre, nuevoTipo);
		return true;
	}

	public static Boolean Existe_tipo(String nombreTipo){
		return Conjunto_tipos.containsKey(nombreTipo);
	}
	
	public static Tipo_de_Evento GetTipo(String nombreEvento){
		for (Map.Entry<String,Tipo_de_Evento> entry : Conjunto_tipos.entrySet()) {
		    if(entry.getValue().Es_Evento(nombreEvento))return entry.getValue();
		}
		return Tipo_de_Evento.NULL;	
	}
	
	public static Boolean RemoveTipo(String nombreTipo){
		if(!Conjunto_tipos.containsKey(nombreTipo))return false;
		Conjunto_tipos.remove(nombreTipo);
		return true;
	}
}
