package dominio;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

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
	 * Conjunto de grupos afines.
	 */
	private Conjunto<GrupoAfinPorDiputado> gruposAfines;
	
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
			this.gruposAfines = new Conjunto<GrupoAfinPorDiputado>(GrupoAfinPorDiputado.class);
			for (GrupoAfinPorDiputado grup:gruposAfines.getAll()) {
				this.gruposAfines.add(grup.getID(), grup);
			}
			this.lapsoDeTiempo = lapsoDeTiempo;
			this.diputadoRelevante = diputadoRelevante;
	}
	
	/**
	 * Elimina un diputado de todos los grupos afines donde se encuentre.
	 * @param nombre - Nombre del diputado a eliminar.
	 */
	public void removeDiputado(String nombre) {
		for (GrupoAfinPorDiputado grup:gruposAfines.getAll()) {
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
	public void moveDiputado(String nombre, Integer desdeID, Integer hastaID) {
		addDiputado(nombre, hastaID);
		removeDiputado(nombre, desdeID);
	}
	
	/**
	 * Añade un nuevo grupo al conjunto de grupos afines.
	 */
	public void addGrupo(GrupoAfinPorDiputado nuevoGrupo) {
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
	 * Suministra un nuevo conjunto con todos los grupos afines del resultado.
	 * @return Conjunto de grupos afines.
	 */
	public Conjunto<GrupoAfinPorDiputado> getGruposAfines() {
		return new Conjunto<GrupoAfinPorDiputado>(this.gruposAfines);
	}
	
	/**
	 * Suministra un conjunto de cadenas de texto con todos los nombres de los resultados.
	 * @return Los nombres de todos los diputados separados por grupos.
	 */
	public Vector<Set<String>> getResultado() {
		Vector<Set<String>> listaResultado = new Vector<Set<String>>();
		for (GrupoAfinPorDiputado grup:gruposAfines.getAll())
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

}
