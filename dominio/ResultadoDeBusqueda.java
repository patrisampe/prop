package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.Iterator;

import time.DateInterval;


public abstract class ResultadoDeBusqueda extends ObjetoDominio{
	
	//Atributs
	private String nombre; 
	private Integer indiceAfinidad;
	private TipoAlgoritmo algoritmo;
	private Map<String, Integer> importancia;
	private Boolean modificado;
	private DateInterval intervalo;
	protected Set<GrupoAfin> gruposAfines;
	//Creadores
	
	public ResultadoDeBusqueda(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval intervalo, Set<GrupoAfin> gruposAfines) {
		this.nombre = new String(nombre);
		this.indiceAfinidad = new Integer(indiceAfinidad);
		this.algoritmo = algoritmo;
		this.importancia = new TreeMap<String,Integer>(importancia);
		this.modificado = new Boolean(modificado);
		this.intervalo = new DateInterval(intervalo);
		this.gruposAfines = new TreeSet<GrupoAfin>(gruposAfines);
	}
	
	public ResultadoDeBusqueda(ResultadoDeBusqueda R) {
		this.nombre = new String(R.nombre);
		this.indiceAfinidad = new Integer(R.indiceAfinidad);
		this.algoritmo = R.algoritmo;
		this.importancia = new TreeMap<String,Integer>(R.importancia);
		this.modificado = new Boolean(R.modificado);
		this.intervalo = new DateInterval(R.intervalo);
		this.gruposAfines = new TreeSet<GrupoAfin>(R.gruposAfines);
	}
	
	//Modificadores
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Boolean setImportancia(String nombreTipoEvento, Integer importancia) {
		this.importancia.put(nombreTipoEvento,importancia);
		return true;
	}
	
	public void removeDiputado(String nombre) {
		for (GrupoAfin grup:gruposAfines) {
			grup.removeDiputado(nombre);
			if (grup.esVacio()) eliminarGrupo(grup.getID());
		}
	}
	
	public void insertarDiputado(String nombre, Integer ID) {
		Iterator<GrupoAfin> it = gruposAfines.iterator();
		Boolean found = false;
		while(it.hasNext() && !found) {
			GrupoAfin grup = it.next();
			if (grup.getID().equals(ID)) {
				grup.addDiputado(nombre);
				found = true;
			}
		}
	}
	
	public void eliminarDiputado(String nombre, Integer ID) {
		Iterator<GrupoAfin> it = gruposAfines.iterator();
		Boolean found = false;
		while(it.hasNext() && !found) {
			GrupoAfin grup = it.next();
			if (grup.getID().equals(ID)) {
				grup.removeDiputado(nombre);
				found = true;
			}
		}
	}

	public void moverDiputado(String nombre, Integer desdeID, Integer hastaID) {
		insertarDiputado(nombre, hastaID);
		eliminarDiputado(nombre, desdeID);
	}
	
	public Integer addGrupo(Set<GrupoAfin> nuevoGrupo) {
		return 3;
	}
	
	public void eliminarGrupo(Integer ID) {
		gruposAfines.remove(ID);
	}

//Consultores
	public String getNombre() {
		return this.nombre;
	}
	
	public Set<GrupoAfin> getGruposAfines() {
		return new TreeSet<GrupoAfin>(this.gruposAfines);
	}
	
	public Set<Set<String>> getResultado() {
		Set<Set<String>> listaResultado = new TreeSet<Set<String>>();
		for (GrupoAfin grup:gruposAfines)
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}
	
	public Integer getIndiceAfinidad() {
		return this.indiceAfinidad;
	}
	
	public TipoAlgoritmo getAlgoritmo() {
		return this.algoritmo;
	}
	
	public DateInterval getIntervalo() {
		return this.intervalo;
	}
	
	public Boolean esModificado() {
		return this.modificado;
	}
	
	public Integer getImportancia(String nombreTipoEvento) {
		return this.importancia.get(nombreTipoEvento);
	}
	
	public abstract String getTipoResultado();
	public abstract String getDiputadoRelevante();
	
}