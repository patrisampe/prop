package dominio;

import java.util.Map;
import utiles.Conjunto;

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
	 */
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, Integer lapsoDeTiempo, Conjunto<GrupoAfinPorDiputado> gruposAfines, String diputadoRelevante) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
			this.gruposAfines = new Conjunto<GrupoAfin>(GrupoAfin.class);
			for (GrupoAfinPorDiputado grup:gruposAfines.getAll()) {
				this.gruposAfines.add(grup.getID(), grup);
				if (existeGrupo(grup.getID())) System.out.println("Dishhhh");
			}
			this.diputadoRelevante = diputadoRelevante;
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

}
