package dominio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


class Tipo_de_Evento {

	private String Nombre;
	private Integer Importancia;
	private Map<String,Evento> Conjunto_eventos;
	
	private static Map<String, Tipo_de_Evento> Conjunto_tipos = new TreeMap<String, Tipo_de_Evento>();
	private static Tipo_de_Evento NULL = new Tipo_de_Evento("NULL", 0);

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
	public Boolean Es_null(){
		return (Nombre.equals("NULL"));
	}
	
	public List<String> Consultar_eventos(){
		List<String> list = new ArrayList<String>(Conjunto_eventos.keySet());
		return list;
	}
	
	public Integer Consultar_importancia(){
		return Importancia;
	}
	
	public Boolean Modificar_importancia(Integer nuevaImportancia){
		if(nuevaImportancia.equals(0))return false;
		Importancia=nuevaImportancia;
		return true;
	}

	public Boolean Añadir_evento(Evento nuevoEvento){
		if(Conjunto_eventos.containsKey(nuevoEvento.Consultar_nombre()))return false;
		Conjunto_eventos.put(nuevoEvento.Consultar_nombre(), nuevoEvento);
		return true;
	}

	public Boolean Existe_evento(String nombreEvento){
		return Conjunto_eventos.containsKey(nombreEvento);
	}
	
	public Evento Consultar_evento(String nombreEvento){
	//pre: nombreEvento ha de ser un Evento de ese tipo	
		return Conjunto_eventos.get(nombreEvento);
	}
	
	public Boolean Eliminar_evento(String nombreEvento){
		if(!Conjunto_eventos.containsKey(nombreEvento))return false;
		Conjunto_eventos.remove(nombreEvento);
		return true;
	}
	
	public static Boolean Añadir_tipo(Tipo_de_Evento nuevoTipo){
		if(Conjunto_tipos.containsKey(nuevoTipo.Nombre))return false;
		Conjunto_tipos.put(nuevoTipo.Nombre, nuevoTipo);
		return true;
	}

	public static Boolean Existe_tipo(String nombreTipo){
		return Conjunto_tipos.containsKey(nombreTipo);
	}
	
	public static Tipo_de_Evento Consultar_tipo(String nombreEvento){
		for (Map.Entry<String,Tipo_de_Evento> entry : Conjunto_tipos.entrySet()) {
		    if(entry.getValue().Existe_evento(nombreEvento))return entry.getValue();
		}
		return Tipo_de_Evento.NULL;	
	}
	
	public static Boolean Eliminar_tipo(String nombreTipo){
		if(!Conjunto_tipos.containsKey(nombreTipo))return false;
		Conjunto_tipos.remove(nombreTipo);
		return true;
	}
}
