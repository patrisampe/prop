package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class ResultadoDeBusqueda {
	
	//Atributs
	private String nombre; 
	private Integer indiceAfinidad;
	private TipoAlgoritmo algoritmo;
	private Map<String, Integer> importancia;
	private Boolean modificado;
	private Set<Integer> gruposAfines;
	//Creadors
	
	public ResultadoDeBusqueda(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado,  Set<Integer> gruposAfines) {
		this.nombre = new String(nombre);
		this.indiceAfinidad = new Integer(indiceAfinidad);
		this.algoritmo = algoritmo;
		this.importancia = new TreeMap<String,Integer>(importancia);
		this.modificado = new Boolean(modificado);
		this.gruposAfines = new TreeSet<Integer>(gruposAfines);
	}
	
	public ResultadoDeBusqueda(ResultadoDeBusqueda R) {
		this.nombre = new String(R.nombre);
		this.indiceAfinidad = new Integer(R.indiceAfinidad);
		this.algoritmo = R.algoritmo;
		this.importancia = new TreeMap<String,Integer>(R.importancia);
		this.modificado = new Boolean(R.modificado);
		this.gruposAfines = new TreeSet<Integer>(R.gruposAfines);
	}
	
	//Consultors
	public String getNombre() {
		return this.nombre;
	}
	
	public Set<Integer> consultarGruposAfines() {
		return this.gruposAfines;
	}
	
	public Integer getIndiceAfinidad() {
		return this.indiceAfinidad;
	}
	
	public TipoAlgoritmo getAlgoritmo() {
		return this.algoritmo;
	}
	
	public Boolean esModificado() {
		return this.modificado;
	}
	
	public Integer getImportancia(String nombreTipoEvento) {
		return this.importancia.get(nombreTipoEvento);
	}
	
	public Boolean setImportancia(String nombreTipoEvento, Integer importancia) {
		this.importancia.put(nombreTipoEvento,importancia);
		return true;
	}
}