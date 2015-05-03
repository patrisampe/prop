package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utiles.Conjunto;

/**
 * Resultado obtenido por la busqueda de grupos afines entre diputados (Clase abstracta).
 * @author Miguel Angel Aranda
 * @version 1.0 Mayo 2015
 */
public abstract class ResultadoDeBusqueda extends ObjetoDominio{
	
	/**
	 * Nombre del resultado(identificador).
	 */
	private String nombre; 
	
	/**
	 * Indice de afinidad utilizado para obtener el resultado.
	 */
	private Integer indiceAfinidad;
	
	/**
	 * Algoritmo utilizado para obtener el resultado.
	 */
	private TipoAlgoritmo algoritmo;
	
	/**
	 * Importancias temporales introducidas por el usuario.
	 */
	private Map<String, Integer> importancia;
	
	/**
	 * Importancias temporales introducidas por el usuario.
	 */
	private Boolean modificado;
	
	/**
	 * Conjunto de grupos afines.
	 */
	protected Conjunto<GrupoAfin> gruposAfines;
	
	/**
	 * Constructor de la clase resultado de busqueda.
	 * @param nombre - Nombre del resultado.
	 * @param indiceAfinidad - Indice de afinidad utilizado para obtener el resultado.
	 * @param algoritmo - Algoritmo utilizado para obtener el resultado.
	 * @param importancia - Importancias temporales introducidas por el usuario.
	 * @param modificado - Importancias temporales introducidas por el usuario.
	 */
	public ResultadoDeBusqueda(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado) {
		this.nombre = new String(nombre);
		this.indiceAfinidad = new Integer(indiceAfinidad);
		this.algoritmo = algoritmo;
		this.importancia = new TreeMap<String,Integer>(importancia);
		this.modificado = new Boolean(modificado);
	}
	
	/**
	 * Introduce el nombre del resultado.
	 * @param nombre - Nombre que adquiere el resutado.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Agrega la importancia temporal de un evento al resultado.
	 * @param nombreTipoEvento - Nombre del evento que adquiere una importancia temporal.
	 * @param importancia - Grado de importancia que adquiere el evento.
	 */
	public Boolean setImportancia(String nombreTipoEvento, Integer importancia) {
		this.importancia.put(nombreTipoEvento,importancia);
		return true;
	}
	
	/**
	 * Elimina un diputado de todos los grupos afines donde se encuentre.
	 * @param nombre - Nombre del diputado a eliminar.
	 */
	public void removeDiputado(String nombre) {
		for (GrupoAfin grup:gruposAfines.getAll()) {
			grup.removeDiputado(nombre);
			if (grup.esVacio()) eliminarGrupo(grup.getID());
		}
	}
	
	/**
	 * Agrega un diputado a un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a agregar.
	 * @param ID - Identificador del grupo al que es agregado.
	 */
	public void addDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).addDiputado(nombre);
	}
	
	/**
	 * Elimina un diputado de un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a eliminar.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	public void removeDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).removeDiputado(nombre);
		if (gruposAfines.get(ID).esVacio())
			gruposAfines.remove(ID);
	}

	/**
	 * Mueve un diputado de un grupo afin a otro.
	 * @param nombreDiputado - Diputado que se debe mover.
	 * @param desdeID - Identificador del grupo afin del que se extrae el diputado.
	 * @param hastaID - Identificador del grupo afin al que se agrega el diputado.
	 */
	public void moveDiputado(String nombre, Integer desdeID, Integer hastaID) {
		addDiputado(nombre, hastaID);
		removeDiputado(nombre, desdeID);
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfin nuevoGrupo) {
		gruposAfines.add(nuevoGrupo.getID(), nuevoGrupo);
	}
	
	/**
	 * Elimina un grupo del resultado.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	public void eliminarGrupo(Integer ID) {
		gruposAfines.remove(ID);
	}

	/**
	 * Comprueba si existe un grupo en concreto.
	 * @param ID - Identificador del grupo que se desea comprobar.
	 * @return <i>true</i> si el grupo existe en el conjunto de grupos afines.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	public Boolean existeGrupo(Integer ID) {
		return gruposAfines.exists(ID);
	}
	
	/**
	 * Suministra una cadena de texto con el nombre del resultado.
	 * @return Nombre del resultado.
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * Suministra el valor del indice de afinidad utilizado en la busqueda.
	 * @return Indice de afinidad.
	 */
	public Integer getIndiceAfinidad() {
		return this.indiceAfinidad;
	}
	
	/**
	 * Suministra una cadena de texto con el algoritmo utilizado para la busqueda.
	 * @return algoritmo utilizado.
	 */
	public TipoAlgoritmo getAlgoritmo() {
		return this.algoritmo;
	}
	
	/**
	 * Comprueba si el resultado ha sido modificado manualmente.
	 * @return <i>true</i> si el grupo ha sido modificado.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	public Boolean esModificado() {
		return this.modificado;
	}
	
	/**
	 * Suministra la importancia temporal de un tipo de evento en concreto.
	 * @param nombreTipoEvento - nombre del evento del que se desea obtener la importancia.
	 * @return Valor de la importancia del tipo de evento.
	 */
	public Integer getImportancia(String nombreTipoEvento) {
		return this.importancia.get(nombreTipoEvento);
	}
	
	/**
	 * Suministra una cadena de texto con el nombre del diputado relevante para la busqueda.
	 * @return Nombre del diputado utilizado para la busqueda.
	 */
	public String getDiputadoRelevante() {
		return "";
	}
	
	/**
	 * Suministra una cadena de texto con el periodo utilizado para la busqueda.
	 * @return El periodo utilizado en la busqueda.
	 */
	public String getPeriodo() {
		return "";
	}
	
	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Conjunto<GrupoAfin> getGruposAfines() {
		return new Conjunto<GrupoAfin>(this.gruposAfines);
	}
	
	/**
	 * Suministra un conjunto de cadenas de texto con todos los nommbres de los resultados.
	 * @return El periodo utilizado en la busqueda.
	 */
	public Set<Set<String>> getResultado() {
		Set<Set<String>> listaResultado = new TreeSet<Set<String>>();
		for (GrupoAfin grup:gruposAfines.getAll())
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}
	
	/**
	 * Suministra una cadena de texto con el nombre de la subclase.
	 * @return El nombre de la subclase.
	 */
	public abstract String getTipoResultado();
	
}