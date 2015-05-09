package dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import utiles.ConjuntoGrupoAfin;

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
	 * Indica si el resultado se ha modificado manualmente.
	 */
	private Boolean modificado;
	
	/**
	 * Indica el criterio de búsqueda que se ha empleado.
	 */
	private Criterio criterio;
	
	/**
	 * Conjunto de grupos afines.
	 */
	protected ConjuntoGrupoAfin gruposAfines;
	
	/**
	 * Constructor de la clase resultado de busqueda.
	 * @param nombre - Nombre del resultado.
	 * @param indiceAfinidad - Indice de afinidad utilizado para obtener el resultado.
	 * @param algoritmo - Algoritmo utilizado para obtener el resultado.
	 * @param importancia - Importancias temporales introducidas por el usuario.
	 * @param modificado - Importancias temporales introducidas por el usuario.
	 */
	public ResultadoDeBusqueda(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, Criterio criterio) {
		this.nombre = new String(nombre);
		this.indiceAfinidad = new Integer(indiceAfinidad);
		this.algoritmo = algoritmo;
		this.importancia = new TreeMap<String,Integer>(importancia);
		this.modificado = new Boolean(modificado);
		this.criterio = criterio;
		this.gruposAfines = new ConjuntoGrupoAfin();
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
	public void addImportancia(String nombreTipoEvento, Integer importancia) {
		this.importancia.put(nombreTipoEvento,importancia);
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
	 * Comprueba si existe un grupo en concreto.
	 * @param ID - Identificador del grupo que se desea comprobar.
	 * @return <i>true</i> si el grupo existe en el conjunto de grupos afines.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	public Boolean existeGrupo(Integer ID) {
		return gruposAfines.exists(ID) || gruposAfines.exists(ID);
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
	 * Suministra todas las importancias temporales definidas por el usuario.
	 * @return Devuelve las importancias temporales.
	 */
	public Map<String, Integer> getImportancias() {
		return new HashMap<String, Integer>(this.importancia);
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
	 * Suministra el multiplicador de las legislaturas.
	 * @return El multiplicador de las legislaturas.
	 */
	public String getLapsoDetiempo() {
		return "";
	}
	
	/**
	 * Suministra el criterio empleado en la busqueda.
	 * @return Criterio de búsqueda.
	 */
	public String getCriterio() {
		return this.criterio.toString();
	}
	
	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public ConjuntoGrupoAfin getGruposAfines() {
		return new ConjuntoGrupoAfin(this.gruposAfines);
	}
	
	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Set<GrupoAfinPorDiputado> getGruposAfinesPorDiputado() {
		return null;
	}
	
	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Set<GrupoAfinPorPeriodo> getGruposAfinesPorPeriodo() {
		return null;
	}
	
	/**
	 * Agrega un diputado a un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a agregar.
	 * @param ID - Identificador del grupo al que es agregado.
	 */
	public abstract void addDiputado(String nombre, Integer ID);
	
	/**
	 * Elimina un diputado de todos los grupos afines donde se encuentre.
	 * @param nombre - Nombre del diputado a eliminar.
	 */
	public abstract void removeDiputado(String nombre);
	
	/**
	 * Elimina un diputado de un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a eliminar.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	public abstract void removeDiputado(String nombre, Integer ID);

	/**
	 * Suministra una cadena de texto con el nombre de la subclase.
	 * @return El nombre de la subclase.
	 */
	public abstract String getTipoResultado();
	
	/**
	 * Suministra un conjunto de cadenas de texto con todos los nombres de los resultados.
	 * @return Los nombres de todos los diputados separados por grupos.
	 */
	public abstract Vector<Set<String>> getResultado();
	
}