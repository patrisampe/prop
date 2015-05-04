package dominio;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import time.*;


/**
 * Classe Votacion contiene las votaciones
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 */
public class Votacion extends ObjetoDominio {

	private String nombre;
	private Date fecha;
	private Integer importancia;
	private Map<String,TipoVoto> votos;
	private static final Integer importancia_min=1;
	private static final Integer importancia_max=5;
	
	/**
	 * Crea una nueva Votacion
	 * @param Nombre - Nombre de la votacion que se crea
	 * @param fecha
	 * 	- Data en que se realiza la votacion 
	 * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida
	 * @param importancia - Importancia del Evento
	 *  <dd><b>Precondition:</b><dd> tiene de ser una importancia valida
	 * @param nvotos - parejas de Diputado y voto que indica que ha votado cada diputado en esa votacion
	 * <dd><b>Precondition:</b><dd> No puede haber un diputado que no este activo en la legislatura de esa votacion ni al reves, ha de haver todos los diputados activos durante la legislatura de esa Votacion.
	 */
	public Votacion(String nombre, Date fecha, Integer importancia, Map<String,TipoVoto> nvotos) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.importancia = importancia;
		votos =new TreeMap<String,TipoVoto>(nvotos);
	}
	
	
	/**
	 * Copia una Votacion y le pone otro nombre
	 * @param nombre - Nombre de la Votacion que se crea
	 * @param vot - Votacion que se quiere copiar
	 */
	public Votacion(String nombre,Votacion vot) {
		this.nombre = nombre;
		fecha = vot.fecha;
		importancia = vot.importancia;
		votos =new TreeMap<String,TipoVoto>(vot.votos);
	}
	
	
	
	/**
	 * Devuelve el nombre de la votacion
	 * @return nombre de la Votacion
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Devuelve la fecha de la votacion
	 * @return fecha de la votacion
	 * 
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * Se actualiza la fecha del evento
	 * @param fecha - La nueva fecha de la votacion
	 * <dd><b>Precondition:</b><dd> data tiene de ser una Data valida

	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * Funcion que devuelve la importancia de la Votacion
	 * @return importancia de la Votacion
	 */
	public Integer getImportancia() {
		return importancia;
	}
	/**
	 * Modifica la importancia de la Votacion
	 * @param importancia - nueva importancia
	 * <dd><b>Precondition:</b><dd> tiene de ser una importancia valida
	 */
	public void setImportancia(Integer importancia) {
		this.importancia = importancia;
	}
	
	/**
	 * Devuelve las parejas de diputado y lo que ha votado dicho diputado
	 * @return votos de cada diputado y dicho diputado
	 */
	public Map<String, TipoVoto> getVotos() {
		return votos;
	}
	/**
	 * A partir de un Diputado te dice que ha votado
	 * @param nombreDiputado
	 * <dd><b>Precondition:</b><dd> </br>
	 * 1- nombreDiputado tiene de ser el nombre de un Diputado que exista
	 * 2- tiene de ser un nombreDiputado de un Diputado que haya votado en esa votacion
	 * @return devuelve que ha votado dicho Diputado
	 */
	public TipoVoto getVoto(String nombreDiputado){
		return votos.get(nombreDiputado);
	}
	
	/**
	 * Devuelve los diputados que ha votado en dicha Votacion
	 * @return diputados que han votado
	 */
	public Set<String> getDiputados(){
		return votos.keySet();
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
	 * Te devuelve todos los diputados que han votado <b>voto<dd></b>
	 * @param voto - es un Tipo de Voto
	 * @return diputados que han votado <i>voto<i>
	 */
	
	public Set<String>getDiputados(TipoVoto voto){
		
		Set<String> dip = new TreeSet<String>();
		for (Entry<String, TipoVoto> elem : votos.entrySet()){
			if(elem.getValue().equals(voto))dip.add(elem.getKey());
		}
		return dip;
	}
	/**
	 * Indica si un diputado ha votado o no en la votacion
	 * @param nombreDiputado - nombre del Diputado
	 * @return true si el diputado ha votado en esa Votacion y sino, false
	 */
	public Boolean esVotante(String nombreDiputado) {
		return votos.containsKey(nombreDiputado);
	}

	/**
	 * Inserta o modifica una nueva diputado-voto
	 * @param nombreDiputado - Diputado que ha votado
	 * @param voto -Lo que ha votado el diputado
	 */
	public void setaddVoto(String nombreDiputado, TipoVoto voto){
		votos.put(nombreDiputado, voto);
	}
	/**
	 * Elimina la pareja diputado-voto
	 * @param nombreDiputado
	 *  * <dd><b>Precondition:</b><dd> </br>
	 * 1- nombreDiputado tiene de ser el nombre de un Diputado que exista
	 * 2- tiene de ser un nombreDiputado de un Diputado que haya votado en esa votacion
	 * 3- nombreDIputado tiene de ser un Diputado que ya no este activo en el momento en el que se hace la votación
	 */
	public void removeVoto(String nombreDiputado){
		votos.remove(nombreDiputado);
	}
	
	
	
}


