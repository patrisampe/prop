package dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class TipoEvento {
	private String nombre;
	private Integer importancia;
	private Map<String,Evento> conjuntoEventos;
	
	//private static Map<String, Tipo_de_Evento> Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	//public static final Tipo_de_Evento NULL = new Tipo_de_Evento("NULL", 0);
	public static final Integer importancia_max=5;
	public static final Integer importancia_min=1;

	public TipoEvento(String Nombre, Integer Importancia) {
		//Pre: importancia ha de ser valida
		nombre=Nombre;
		importancia=Importancia;
		conjuntoEventos=new TreeMap<String, Evento>();
	}
	
	public TipoEvento(String Nombre, TipoEvento TP){
		//Pre: importancia ha de ser valida
		nombre=Nombre;
		importancia=TP.importancia;
		conjuntoEventos=new TreeMap<String, Evento>(TP.conjuntoEventos);
	}
	public String getNombre() {
		return nombre;
	}
	
	public List<String> getEventos(){
		return new ArrayList<String>(conjuntoEventos.keySet());
	}
	
	public Integer getImportancia(){
		return importancia;
	}
	
	public static Boolean esValidaImportancia(Integer nuevaImportancia){
		return (nuevaImportancia>=importancia_min && nuevaImportancia<=importancia_max);
	}
	
	public void setImportancia(Integer nuevaImportancia){
		//Pre: nuevaImportancia tiene de ser valida
		importancia=nuevaImportancia;
	}

	public Boolean esEvento(String nombreEvento){
		return conjuntoEventos.containsKey(nombreEvento);
	}
	
	public Evento getEvento(String nombreEvento){
	//pre: nombreEvento ha de ser un Evento de ese tipo	
		return conjuntoEventos.get(nombreEvento);
	}
	
	public void removeEvento(String nombreEvento){
		//Pre: El evento tiene de existir en ese tipo
		conjuntoEventos.remove(nombreEvento);
	}
	public void addEvento(Evento nuevoEvento){
		//Pre: El evento no puede existir en ese tipo
		conjuntoEventos.put(nuevoEvento.getNombre(), nuevoEvento);
	}
}
