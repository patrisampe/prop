package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utiles.Conjunto;

public abstract class ResultadoDeBusqueda extends ObjetoDominio{
	
	//Atributs
	private String nombre; 
	private Integer indiceAfinidad;
	private TipoAlgoritmo algoritmo;
	private Map<String, Integer> importancia;
	private Boolean modificado;
	protected Conjunto<GrupoAfin> gruposAfines;
	//Creadores
	
	public ResultadoDeBusqueda(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado) {
		this.nombre = new String(nombre);
		this.indiceAfinidad = new Integer(indiceAfinidad);
		this.algoritmo = algoritmo;
		this.importancia = new TreeMap<String,Integer>(importancia);
		this.modificado = new Boolean(modificado);
	}
	
	public ResultadoDeBusqueda(ResultadoDeBusqueda R) {
		this.nombre = new String(R.nombre);
		this.indiceAfinidad = new Integer(R.indiceAfinidad);
		this.algoritmo = R.algoritmo;
		this.importancia = new TreeMap<String,Integer>(R.importancia);
		this.modificado = new Boolean(R.modificado);
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
		for (GrupoAfin grup:gruposAfines.getAll()) {
			grup.removeDiputado(nombre);
			if (grup.esVacio()) eliminarGrupo(grup.getID());
		}
	}
	
	public void addDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).addDiputado(nombre);
	}
	
	public void removeDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).removeDiputado(nombre);
		if (gruposAfines.get(ID).esVacio())
			gruposAfines.remove(ID);
	}

	public void moveDiputado(String nombre, Integer desdeID, Integer hastaID) {
		addDiputado(nombre, hastaID);
		removeDiputado(nombre, desdeID);
	}
	
	public void addGrupo(GrupoAfinPorPeriodo nuevoGrupo) {
		gruposAfines.add(nuevoGrupo.getID(), nuevoGrupo);
	}
	
	public void eliminarGrupo(Integer ID) {
		gruposAfines.remove(ID);
	}

	//Consultores
	public String getNombre() {
		return this.nombre;
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
	
	public String getDiputadoRelevante() {
		return "";
	}
	
	public String getPeriodo() {
		return "";
	}
	
	public Conjunto<GrupoAfin> getGruposAfines() {
		return new Conjunto<GrupoAfin>(this.gruposAfines);
	}
	
	public Set<Set<String>> getResultado() {
		Set<Set<String>> listaResultado = new TreeSet<Set<String>>();
		for (GrupoAfin grup:gruposAfines.getAll())
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}
	
	public abstract String getTipoResultado();
	
}