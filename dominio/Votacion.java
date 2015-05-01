package dominio;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import time.*;

public class Votacion extends ObjetoDominio {

	private String nombre;
	private Date fecha;
	private Integer importancia;
	private Map<String,TipoVoto> votos;
	
	
	
	public Votacion(String nombre, Date fecha, Integer importancia) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.importancia = importancia;
		votos =new TreeMap<String,TipoVoto>();
	}
	
	public Votacion(String nombre,Votacion vot) {
		this.nombre = nombre;
		fecha = vot.fecha;
		importancia = vot.importancia;
		votos =new TreeMap<String,TipoVoto>(vot.votos);
	}
	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the importancia
	 */
	public Integer getImportancia() {
		return importancia;
	}
	/**
	 * @param importancia the importancia to set
	 */
	public void setImportancia(Integer importancia) {
		this.importancia = importancia;
	}
	
	public Map<String, TipoVoto> getVotos() {
		return votos;
	}
	public TipoVoto getVoto(String nombreDiputado){
		return votos.get(nombreDiputado);
	}
	
	public Set<String> getDiputados(){
		return votos.keySet();
	}
	public Set<String>getDiputados(TipoVoto voto){
		
		Set<String> dip = new TreeSet<String>();
		for (Entry<String, TipoVoto> elem : votos.entrySet()){
			if(elem.getValue().equals(voto))dip.add(elem.getKey());
		}
		return dip;
	}
	
	public Boolean esVotante(String nombreDiputado) {
		return votos.containsKey(nombreDiputado);
	}

	public void addSetVoto(String nombreDiputado, TipoVoto voto){
		votos.put(nombreDiputado, voto);
	}
	
	public void removeVoto(String nombreDiputado){
		votos.remove(nombreDiputado);
	}
	
	
	
}
