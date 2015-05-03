package dominio;

import java.util.Map;
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
	 * Crea una instancia de la clase resultado de busqueda.
	 * @param nombre - Nombre del resultado.
	 * @param indiceAfinidad - Indice de afinidad utilizado para obtener el resultado.
	 * @param algoritmo - Algoritmo utilizado para obtener el resultado.
	 * @param importancia - Importancias temporales introducidas por el usuario.
	 * @param modificado - Indica si el resultado ha sido modificado manualmente.
	 * @param periodo - Indica el periodo en el que se ha realizado realizado la busqueda.
	 * @param gruposAfines - Conjunto de los grupos afines que forman el resultado
	 */
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, Conjunto<GrupoAfinPorPeriodo> gruposAfines) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
		this.gruposAfines = new Conjunto<GrupoAfin>(GrupoAfin.class);
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAll())
			this.gruposAfines.add(grup.getID(), grup);
		this.periodo = new DateInterval(periodo);
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
	
}
