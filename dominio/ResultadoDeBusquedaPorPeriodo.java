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
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, ConjuntoGrupoAfin gruposAfines, Criterio criterio) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado, criterio);
		this.gruposAfines = new ConjuntoGrupoAfin(gruposAfines);
		this.periodo = new DateInterval(periodo);
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfinPorPeriodo nuevoGrupo) {
		gruposAfines.addPorPeriodo(nuevoGrupo);
	}
	
	/**
	 * Elimina un grupo del resultado.
	 * @param ID - Identificador del grupo del que es eliminado.
	 */
	public void eliminarGrupo(Integer ID) {
		gruposAfines.removePorPeriodo(ID);
	}
	
	/**
	 * Suministra un conjunto de cadenas de texto con todos los nommbres de los resultados.
	 * @return El periodo utilizado en la busqueda.
	 */
	@Override
	public Vector<Set<String>> getResultado() {
		Vector<Set<String>> listaResultado = new Vector<Set<String>>();
		for (GrupoAfin grup:gruposAfines.getAllPorPeriodo().getAll())
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
