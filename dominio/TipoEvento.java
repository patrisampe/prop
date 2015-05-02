package dominio;
import java.util.Set;


import utiles.Conjunto;

/**
 * Classe TipoEvento contiene los diferentes tipos de Evento, dentro de los cuales hay eventos
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 */
public class TipoEvento extends ObjetoDominio {
	private String nombre;
	private Integer importancia;
	private Conjunto<Evento> conjuntoEventos;
	private static final Integer importancia_max=5;
	private static final Integer importancia_min=1;

	/**
	 * Crea un nuevo Evento
	 * @param Nombre - Nombre del evento que se crea
	 * @param Importancia - Importancia del evento que se crea
	 * <dd><b>Precondition:</b><dd>Importancia tiene de ser valida
	 */
	public TipoEvento(String Nombre, Integer Importancia) {
		nombre=Nombre;
		importancia=Importancia;
		conjuntoEventos=new Conjunto<Evento>(Evento.class);
	}
	
	/**
	 * Copia un Tipo Evento y le pone otro nombre
	 * @param Nombre - Nombre del TipoEvento que se crea
	 * @param TE - TipoEvento que se quiere copiar
	 */
	
	
	public TipoEvento(String Nombre, TipoEvento TE){
		//Pre: importancia ha de ser valida
		nombre=Nombre;
		importancia=TE.importancia;
		conjuntoEventos=new Conjunto<Evento>(TE.conjuntoEventos);
	}
	
	/**
	 * Funcion que devuelve el nombre del evento
	 * @return nombre del evento
	 * 
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Funcion que devuelve el nombre de todos los eventos que contiene ese tipo evento
	 * @return nombre de los eventos que contiene
	 * 
	 */
	public Set<String> getNombreEventos(){
		return conjuntoEventos.getStringKeys();
	}
	/**
	 * Funcion que devuelve los eventos que contiene ese tipo evento
	 * @return eventos que contiene
	 * 
	 */
	
	public Set<Evento> getEventos(){
		return conjuntoEventos.getAll();
	}
	/**
	 * Funcion que devuelve la importancia del tipo evento
	 * @return importancia del tipo evento
	 * 
	 */
	public Integer getImportancia(){
		return importancia;
	}
	/**
	 * Funcion que devuelve si una importancia es valida o no
	 * @param nuevaImportancia - Importancia a evaluar
	 * @return false la importancia no es valida <br>
	 * true la importancia es valida
	 * 
	 */
	
	public static Boolean esValidaImportancia(Integer nuevaImportancia){
		return (nuevaImportancia>=importancia_min && nuevaImportancia<=importancia_max);
	}
	
	/**
	 * Funcion que cambia la importancia del tipo de evento
	 * @param nuevaImportancia - La nueva Importancia del tipo evento.<br> 
	 * <dd><b>Precondition:</b><dd>Importancia tiene de ser valida
	 * 
	 */
	
	public void setImportancia(Integer nuevaImportancia){
		importancia=nuevaImportancia;
	}
	/**
	 * Funcion que indica si el Evento es de ese tipo o no
	 * @param nombreEvento - nombre que identifica un Evento.<br> 
	 * @return si nombreEvento es un evento de este tipo, true, sino, false
	 * 
	 */
	public Boolean esEvento(String nombreEvento){
		return conjuntoEventos.exists(nombreEvento);
	}
	/**
	 * Funcion que devuelve el Evento con nombreEvento
	 * @param nombreEvento - nombre que identifica un Evento.<br> 
	 * <dd><b>Precondition:</b><dd>nombreEvento tiene de ser un Evento de ese Tipo Evento
	 * @return Evento con nombre nombreEvento
	 * 
	 */
	public Evento getEvento(String nombreEvento){
		return conjuntoEventos.get(nombreEvento);
	}
	/**
	 * Funcion que borra el Evento con nombreEvento
	 * @param nombreEvento - nombre que identifica un Evento.<br> 
	 * <dd><b>Precondition:</b><dd>nombreEvento tiene de ser un Evento de ese Tipo Evento
	 * 
	 */
	public void removeEvento(String nombreEvento){
		conjuntoEventos.remove(nombreEvento);
	}
	/**
	 * Funcion que añade un Evento 
	 * @param nuevoEvento - Evento que añadiremos
	 * <dd><b>Precondition:</b><dd>nuevoEvento no puede tener un nombre de un Evento de ese Tipo Evento
	 * 
	 */
	public void addEvento(Evento nuevoEvento){
		conjuntoEventos.add(nuevoEvento.getNombre(), nuevoEvento);
	}
}
