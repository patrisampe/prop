package utiles;

import java.util.Set;

import dominio.GrupoAfin;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;

/**
 * Conjunto abstracto auxilitar que almacena grupos afines.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class ConjuntoGrupoAfin {
	
	/**
	 * Conjunto base donde se almacenan los datos.
	 */
	Conjunto<GrupoAfin> conjunto;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de clase GrupoAfin.
	 */
	public ConjuntoGrupoAfin(){
		conjunto = new Conjunto<GrupoAfin>(GrupoAfin.class);

	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */

	public ConjuntoGrupoAfin(ConjuntoGrupoAfin C){
		conjunto = new Conjunto<GrupoAfin>(C.conjunto);
	}
	
	/**
	 * Consulta el numero de elementos del conjunto.
	 * @return El numero de elementos del conjunto.
	 */
	public Integer size(){
		return conjunto.size();
	}
	
	/**
	 * Consulta si el conjunto esta vacio.
	 * @return <i>true</i> si el conjunto esta vacio.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isEmpty(){
		return conjunto.isEmpty();
	}
	
	/**
	 * Elimina todos los elementos del conjunto.
	 */
	public void clear() {
		conjunto.clear();
	}
	
	/**
	 * Introduce todos los elementos de un Set al conjunto.
	 * @param S - Set que contiene todos los elementos a introducir.
	 */
	public void addAll(Set<GrupoAfin> S){
		conjunto.addAll(S);
	}
	
	/**
	 * Consulta todos los elementos del conjunto.
	 * @return Set que contiene todos los elementos del conjunto.
	 */
	public Conjunto<GrupoAfin> getAll() {
		return conjunto;
	}
	
	// Modificado Miguel
	public Conjunto<GrupoAfinPorDiputado> getAllPorDiputado() {
		return null;
	}
	
	//Modificado Miguel
	public Conjunto<GrupoAfinPorPeriodo> getAllPorPeriodo() {
		return null;
	}
	
	/**
	 * Consulta las claves del conjunto.
	 * @return Las claves del conjunto, si y solo si el conjunto esta mapeado con identificadores.
	 */
	public Set<Integer> getKeys() {
		return conjunto.getIntegerKeys();
	}
	
	/**
	 * Introduce un elemento al conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(GrupoAfin objeto) {
		conjunto.add(objeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public GrupoAfin get(Integer idObjeto) {
		return conjunto.get(idObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(Integer idObjeto) {
		return conjunto.exists(idObjeto);
	}
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param idObjeto - Identificador del elemento.
	 */
	public void remove(Integer idObjeto) {
		conjunto.remove(idObjeto);
	}
	
	/**
	 * Obtiene el conjunto parametrizado.
	 * @return El Conjunto de GrupoAfin utilizado en el resto de clases.
	 */
	public Conjunto<GrupoAfin> getConjunto() {
		return conjunto;
	}
	
}
