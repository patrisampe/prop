package dominio;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import time.DateInterval;
import utiles.Conjunto;

/**
 * Resultado obtenido por la busqueda por periodo de grupos afines entre diputados.
 * @author Miguel Angel Aranda
 * @version 1.0 Mayo 2015
 */
public class ResultadoDeBusquedaPorPeriodo extends ResultadoDeBusqueda {
	
	/**
	 * Intervalo de tiempo en el que se ha realizado la busqueda.
	 */
	private DateInterval periodo;
	
	/**
	 * Conjunto de grupos afines.
	 */
	private Conjunto<GrupoAfinPorPeriodo> gruposAfines;
	
	/**
	 * Crea una instancia de la clase resultado de busqueda.
	 * @param nombre - Nombre del resultado.
	 * @param indiceAfinidad - Indice de afinidad utilizado para obtener el resultado.
	 * @param algoritmo - Algoritmo utilizado para obtener el resultado.
	 * @param importancia - Importancias temporales introducidas por el usuario.
	 * @param modificado - Indica si el resultado ha sido modificado manualmente.
	 * @param periodo - Indica el periodo en el que se ha realizado realizado la busqueda.
	 * @param gruposAfines - Conjunto de los grupos afines que forman el resultado.
	 * @param criterio - Indica el criterio de búsqueda que se ha utilizado.
	 */
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, Conjunto<GrupoAfinPorPeriodo> gruposAfines, Criterio criterio) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado, criterio);
		this.gruposAfines = new Conjunto<GrupoAfinPorPeriodo>(GrupoAfinPorPeriodo.class);
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAll())
			this.gruposAfines.add(grup.getID(), grup);
		this.periodo = new DateInterval(periodo);
	}
	
	/**
	 * Elimina un diputado de todos los grupos afines donde se encuentre.
	 * @param nombre - Nombre del diputado a eliminar.
	 */
	@Override
	public void removeDiputado(String nombre) {
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAll()) {
			grup.removeDiputado(nombre);
			if (grup.esVacio()) eliminarGrupo(grup.getID());
		}
	}

	/**
	 * Mueve un diputado de un grupo afin a otro.
	 * @param nombreDiputado - Diputado que se debe mover.
	 * @param desdeID - Identificador del grupo afin del que se extrae el diputado.
	 * @param hastaID - Identificador del grupo afin al que se agrega el diputado.
	 */
	@Override
	public void moveDiputado(String nombre, Integer desdeID, Integer hastaID) {
		addDiputado(nombre, hastaID);
		removeDiputado(nombre, desdeID);
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfinPorPeriodo nuevoGrupo) {
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
	@Override
	public Boolean existeGrupo(Integer ID) {
		return gruposAfines.exists(ID);
	}
	
	/**
	 * Agrega un diputado a un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a agregar.
	 * @param ID - Identificador del grupo al que es agregado.
	 */
	@Override
	public void addDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).addDiputado(nombre);
	}
	
	/**
	 * Elimina un diputado de un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a eliminar.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	@Override
	public void removeDiputado(String nombre, Integer ID) {
		gruposAfines.get(ID).removeDiputado(nombre);
		if (gruposAfines.get(ID).esVacio())
			gruposAfines.remove(ID);
	}

	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Conjunto<GrupoAfinPorPeriodo> getGruposAfines() {
		return new Conjunto<GrupoAfinPorPeriodo>(this.gruposAfines);
	}
	
	/**
	 * Suministra un conjunto de cadenas de texto con todos los nommbres de los resultados.
	 * @return El periodo utilizado en la busqueda.
	 */
	@Override
	public Vector<Set<String>> getResultado() {
		Vector<Set<String>> listaResultado = new Vector<Set<String>>();
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAll())
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}

	/**
	 * Suministra una cadena de texto con el nombre de la subclase.
	 * @return El nombre de la subclase.
	 */
	@Override
	public String getTipoResultado() {
		return "Búsqueda por periodo";
	}

	/**
	 * Suministra una cadena de texto con el periodo en el que se ha realizado la busqueda.
	 * @return Periodo en el que se realizo la busqueda.
	 */
	public String getPeriodo() {
		return periodo.toString();
	}

	/**
	 * Suministra DateInterval con el periodo en el que se ha realizado la busqueda.
	 * @return Periodo en el que se realizo la busqueda.
	 */
	public DateInterval getInterval() {
		return new DateInterval(periodo);
	}

}
