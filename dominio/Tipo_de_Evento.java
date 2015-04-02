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
	//public static final Tipo_de_Evento NULL = new Tipo_de_Evento("NULL", 0);
	public static final Integer importancia_max=5;
	public static final Integer importancia_min=1;

	public Tipo_de_Evento(String Nombre, Integer Importancia) {
		//Pre: importancia ha de ser valida y nombre no existente
		this.Nombre=Nombre;
		this.Importancia=Importancia;
		Conjunto_eventos=new TreeMap<String, Evento>();
	}
	
	public Tipo_de_Evento(String Nombre, Tipo_de_Evento TP){
		//Pre: importancia ha de ser valida  y nombre no existente
		this.Nombre=Nombre;
		Importancia=TP.Importancia;
		Conjunto_eventos=TP.Conjunto_eventos;
	}
	public String getNombre() {
		return Nombre;
	}
	
	public List<String> GetEventos(){
		return new ArrayList<String>(Conjunto_eventos.keySet());
	}
	
	public Integer GetImportancia(){
		return Importancia;
	}
	
	public static Boolean es_validaImportancia(int nuevaImportancia){
		return (nuevaImportancia>=importancia_min && nuevaImportancia<=importancia_max);
	}
	
	public void SetImportancia(Integer nuevaImportancia){
		//Pre: nuevaImportancia tiene de ser valida
		Importancia=nuevaImportancia;
	}

	public Boolean Es_Evento(String nombreEvento){
		return Conjunto_eventos.containsKey(nombreEvento);
	}
	
	public Evento GetEvento(String nombreEvento){
	//pre: nombreEvento ha de ser un Evento de ese tipo	
		return Conjunto_eventos.get(nombreEvento);
	}
	
	public void RemoveEvento(String nombreEvento){
		//Pre: El evento tiene de existir en ese tipo
		Conjunto_eventos.remove(nombreEvento);
	}
	public void AddEvento(Evento nuevoEvento){
		// Pre: El evento no puede existir ya en este tipo
		Conjunto_eventos.put(nuevoEvento.GetNombre(), nuevoEvento);
	}
}
