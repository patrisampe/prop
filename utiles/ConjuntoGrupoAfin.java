package utiles;

import java.util.Set;

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
	protected Conjunto<GrupoAfinPorDiputado> conjuntoPorDiputado;
	protected Conjunto<GrupoAfinPorPeriodo> conjuntoPorPeriodo;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de clase GrupoAfin.
	 */
	public ConjuntoGrupoAfin(){
		conjuntoPorDiputado = new Conjunto<GrupoAfinPorDiputado>(GrupoAfinPorDiputado.class);
		conjuntoPorPeriodo = new Conjunto<GrupoAfinPorPeriodo>(GrupoAfinPorPeriodo.class);
	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */

	public ConjuntoGrupoAfin(ConjuntoGrupoAfin C){
		conjuntoPorDiputado = new Conjunto<GrupoAfinPorDiputado>(C.conjuntoPorDiputado);
		conjuntoPorPeriodo = new Conjunto<GrupoAfinPorPeriodo>(C.conjuntoPorPeriodo);
	}
	
	/**
	 * Consulta el numero de elementos del conjunto.
	 * @return El numero de elementos del conjunto.
	 */
	public Integer size(){
		return conjuntoPorDiputado.size() + conjuntoPorPeriodo.size();
	}
	
	/**
	 * Consulta si el conjunto esta vacio.
	 * @return <i>true</i> si el conjunto esta vacio.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isEmpty(){
		return conjuntoPorDiputado.isEmpty() && conjuntoPorPeriodo.isEmpty();
	}
	
	/**
	 * Elimina todos los elementos del conjunto.
	 */
	public void clear() {
		conjuntoPorDiputado.clear();
		conjuntoPorPeriodo.clear();
	}
	
	/**
	 * Introduce todos los elementos de un Set al conjunto.
	 * @param S - Set que contiene todos los elementos a introducir.
	 */
	public void addAll(Set<GrupoAfinPorDiputado> D, Set<GrupoAfinPorPeriodo> P){
		conjuntoPorDiputado.addAll(D);
		conjuntoPorPeriodo.addAll(P);
	}
	
	// Modificado Miguel
	public Conjunto<GrupoAfinPorDiputado> getAllPorDiputado() {
		return conjuntoPorDiputado;
	}
	
	//Modificado Miguel
	public Conjunto<GrupoAfinPorPeriodo> getAllPorPeriodo() {
		return conjuntoPorPeriodo;
	}
	
	/**
	 * Consulta las claves del conjunto.
	 * @return Las claves del conjunto, si y solo si el conjunto esta mapeado con identificadores.
	 */
	public Set<Integer> getKeysPorDiputado() {
		return conjuntoPorDiputado.getIntegerKeys();
	}
	
	/**
	 * Consulta las claves del conjunto.
	 * @return Las claves del conjunto, si y solo si el conjunto esta mapeado con identificadores.
	 */
	public Set<Integer> getKeysPorPeriodo() {
		return conjuntoPorPeriodo.getIntegerKeys();
	}
	
	/**
	 * Introduce un elemento al conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param objeto - Elemento a introducir.
	 */
	public void addPorDiputado(GrupoAfinPorDiputado objeto) {
		conjuntoPorDiputado.add(objeto);
	}
	public void addPorPeriodo(GrupoAfinPorPeriodo objeto) {
		conjuntoPorPeriodo.add(objeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public GrupoAfinPorDiputado getPorDiputado(Integer idObjeto) {
		return conjuntoPorDiputado.get(idObjeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public GrupoAfinPorPeriodo getPorPeriodo(Integer idObjeto) {
		return conjuntoPorPeriodo.get(idObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsPorDiputado(Integer idObjeto) {
		return conjuntoPorDiputado.exists(idObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsPorPeriodo(Integer idObjeto) {
		return conjuntoPorPeriodo.exists(idObjeto);
	}
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param idObjeto - Identificador del elemento.
	 */
	public void removePorDiputado(Integer idObjeto) {
		conjuntoPorDiputado.remove(idObjeto);
	}

	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param idObjeto - Identificador del elemento.
	 */
	public void removePorPeriodo(Integer idObjeto) {
		conjuntoPorPeriodo.remove(idObjeto);
	}

}
