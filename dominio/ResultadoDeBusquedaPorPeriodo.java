package dominio;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import time.DateInterval;
import utiles.ConjuntoGrupoAfin;

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
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, ConjuntoGrupoAfin gruposAfines, Map<Criterio,Double> criterios) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado, criterios);
		this.gruposAfines = new ConjuntoGrupoAfin(gruposAfines);
		this.periodo = new DateInterval(periodo);
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfinPorPeriodo nuevoGrupo) {
		gruposAfines.add(nuevoGrupo);
	}
	
	/**
	 * Elimina un grupo del resultado.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	public void eliminarGrupo(Integer ID) {
		gruposAfines.remove(ID);
	}
	
	/**
	 * Agrega un diputado a un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a agregar.
	 * @param ID - Identificador del grupo al que es agregado.
	 */
	@Override
	public void addDiputado(String nombre, Integer ID) {
			gruposAfines.getPorPeriodo(ID).addDiputado(nombre);
	}
	
	/**
	 * Elimina un diputado de todos los grupos afines donde se encuentre.
	 * @param nombre - Nombre del diputado a eliminar.
	 */
	@Override
	public void removeDiputado(String nombre) {
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAllPorPeriodo()) {
			grup.removeDiputado(nombre);
			if (grup.esVacio()) gruposAfines.remove(grup.getID());
		}
	}

	/**
	 * Elimina un diputado de un grupo afin en concreto.
	 * @param nombre - Nombre del diputado a eliminar.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	@Override
	public void removeDiputado(String nombre, Integer ID) {
		gruposAfines.getPorPeriodo(ID).removeDiputado(nombre);
		if (gruposAfines.getPorPeriodo(ID).esVacio())
			gruposAfines.remove(ID);
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
	 * Suministra un conjunto de cadenas de texto con todos los nommbres de los resultados.
	 * @return El periodo utilizado en la busqueda.
	 */
	@Override
	public Vector<Set<String>> getResultado() {
		Vector<Set<String>> listaResultado = new Vector<Set<String>>();
		for (GrupoAfin grup:gruposAfines.getAllPorPeriodo())
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}

	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	@Override
	public Set<GrupoAfinPorPeriodo> getGruposAfinesPorPeriodo() {
		return gruposAfines.getAllPorPeriodo();
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
