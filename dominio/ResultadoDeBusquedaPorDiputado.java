package dominio;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import utiles.Conjunto;
import utiles.ConjuntoGrupoAfin;
import utiles.ConjuntoGrupoAfinPorDiputado;

/**
 * Resultado obtenido por la busqueda por diputado de grupos afines entre diputados.
 * @author Miguel Angel Aranda
 * @version 1.0 Mayo 2015
 */
public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	/**
	 * El nombre del diputado sobre el que se ha realizado la busqueda.
	 */
	private String diputadoRelevante;
	
	/**
	 * Indica el multiplicador aplicado a legislatura para realizar la busqueda.
	 */
	private Integer lapsoDeTiempo;
	
	/**
	 * Crea una instancia de la clase resultado de busqueda por diputado.
	 * @param nombre - Nombre del resultado.
	 * @param indiceAfinidad - Indice de afinidad utilizado para obtener el resultado.
	 * @param algoritmo - Algoritmo utilizado para obtener el resultado.
	 * @param importancia - Importancias temporales introducidas por el usuario.
	 * @param modificado - Indica si el resultado ha sido modificado manualmente.
	 * @param lapsoDeTiempo - Indica el multiplicador aplicado a legislatura para realizar la busqueda.
	 * @param gruposAfines - Conjunto de los grupos afines que forman el resultado
	 * @param diputadoRelevante - El nombre del diputado sobre el que se ha realizado la busqueda
	 * @param criterio - Indica el criterio de búsqueda que se ha utilizado.
	 */
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, Integer lapsoDeTiempo, ConjuntoGrupoAfinPorDiputado gruposAfines, String diputadoRelevante, Criterio criterio) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado, criterio);
			this.gruposAfines = new ConjuntoGrupoAfinPorDiputado(gruposAfines);
			this.lapsoDeTiempo = lapsoDeTiempo;
			this.diputadoRelevante = diputadoRelevante;
			System.out.println(gruposAfines.getConjunto().getAll());
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfinPorDiputado nuevoGrupo) {
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
	 * Suministra un conjunto de cadenas de texto con todos los nombres de los resultados.
	 * @return Los nombres de todos los diputados separados por grupos.
	 */
	@Override
	public Vector<Set<String>> getResultado() {
		Vector<Set<String>> listaResultado = new Vector<Set<String>>();
		for (GrupoAfin grup:gruposAfines.getAll())
			listaResultado.add(grup.getDiputados());
		return listaResultado;
	}
	
	/**
	 * Suministra una cadena de texto con el nombre de la subclase.
	 * @return El nombre de la subclase.
	 */
	@Override
	public String getTipoResultado() {
		return "Búsqueda por diputado";
	}

	/**
	 * Suministra una cadena de texto con el nombre del diputado relevante para la busqueda.
	 * @return El nombre del diputado utilizado para la busqueda.
	 */
	@Override
	public String getDiputadoRelevante() {
		return diputadoRelevante;
	}
	
	/**
	 * Suministra el multiplicador de las legislaturas.
	 * @return El multiplicador de las legislaturas.
	 */
	@Override
	public String getLapsoDetiempo() {
		return lapsoDeTiempo.toString();
	}
	
	/**
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Conjunto<GrupoAfinPorDiputado> getGruposAfinesPorDiputado() {
		return gruposAfines.getAllPorDiputado();
	}

}
