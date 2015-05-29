package utiles;

import java.util.Set;
import dominio.ObjetoDominio;

public class ConjuntoDoble<D extends ObjetoDominio, P extends ObjetoDominio> {

	private Conjunto<D> conjuntoPorDiputado;
	private Conjunto<P> conjuntoPorPeriodo;
	
	
	public ConjuntoDoble(Class<D> typeD, Class<P> typeP){
		conjuntoPorDiputado = new Conjunto<D>(typeD);
		conjuntoPorPeriodo = new Conjunto<P>(typeP);
	}
	
	 /** Consulta el número de elementos del conjunto.
	 * @return El número de elementos del conjunto.
	 */
	public Integer size(){
		return conjuntoPorDiputado.size() + conjuntoPorPeriodo.size();
	}
	
	/**
	 * Consulta si el conjunto está vacío.
	 * @return <i>true</i> si el conjunto está vacío.
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
	public void addAll(Set<D> Dip, Set<P> Per){
		conjuntoPorDiputado.addAll(Dip);
		conjuntoPorPeriodo.addAll(Per);
	}
	
	/**
	 * Consulta los grupos   por diputado.
	 * @return Set que contiene todos los grupos por diputado.
	 */
	public Set<D> getAllPorDiputado() {
		return conjuntoPorDiputado.getAll();
	}
	
	/**
	 * Consulta los grupos   por periodo.
	 * @return Set que contiene todos los grupos por periodo.
	 */
	public Set<P> getAllPorPeriodo() {
		return conjuntoPorPeriodo.getAll();
	}
	
	/**
	 * Consulta los identificadores de los grupos  por diputado.
	 * @return Las claves del conjunto.
	 */
	public Set<Integer> getKeysIPorDiputado() {
		return conjuntoPorDiputado.getIntegerKeys();
	}
	
	public Set<String> getStringKeys() {
		
		Set<String> aux= conjuntoPorDiputado.getStringKeys();
		aux.addAll(conjuntoPorPeriodo.getStringKeys());
		return aux;
		
	}
	public Set<Integer> getIntegerKeys() {
		
		Set<Integer> aux= conjuntoPorDiputado.getIntegerKeys();
		aux.addAll(conjuntoPorPeriodo.getIntegerKeys());
		return aux;
		
	}
	
	public void getDiputadoRelevante(){
		
	}
	
	
	public Set<String> getKeysSPorDiputado() {
		return conjuntoPorDiputado.getStringKeys();
	}
	/**
	 * Consulta los identificadores de los grupos   por periodo.
	 * @return Las claves del conjunto.
	 */
	public Set<Integer> getKeysIPorPeriodo() {
		return conjuntoPorPeriodo.getIntegerKeys();
	}
	
	public Set<String> getKeysSPorPeriodo() {
		return conjuntoPorPeriodo.getStringKeys();
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void addD(D objeto) {
		conjuntoPorDiputado.add(objeto);
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void addP(P objeto) {
		conjuntoPorPeriodo.add(objeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public D getPorDiputado(Integer idObjeto) {
		return conjuntoPorDiputado.get(idObjeto);
	}
	
	public D getPorDiputado(String idObjeto) {
		return conjuntoPorDiputado.get(idObjeto);
	}
	
	
	
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public P getPorPeriodo(Integer idObjeto) {
		return conjuntoPorPeriodo.get(idObjeto);
	}
	
	public ObjetoDominio get(String nombreOb){
		if(conjuntoPorDiputado.exists(nombreOb))return conjuntoPorDiputado.get(nombreOb);
		return conjuntoPorPeriodo.get(nombreOb);
	}
	
	public ObjetoDominio get(Integer idOb){
		if(conjuntoPorDiputado.exists(idOb))return conjuntoPorDiputado.get(idOb);
		return conjuntoPorPeriodo.get(idOb);
	}
	
	public P getPorPeriodo(String idObjeto) {
		return conjuntoPorPeriodo.get(idObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(Integer idObjeto) {
		return conjuntoPorDiputado.exists(idObjeto) || conjuntoPorPeriodo.exists(idObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(String nombreObjeto) {
		return conjuntoPorDiputado.exists(nombreObjeto) || conjuntoPorPeriodo.exists(nombreObjeto);
	}
	
	/**
	 * Consulta a que conjunto pertenece el elemento.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>1</i> si el elemento pertenece al conjunto por diputado.
	 * <br>
	 * <i>2</i> si el elemento pertenece al conjunto por periodo.
	 * <br>
	 * <i>-1</i> si el elemento no pertenece a ningun conjunto.
	 */
	public Integer aQueConjunto(Integer idObjeto) {
		if (conjuntoPorDiputado.exists(idObjeto)) return 1;
		if (conjuntoPorPeriodo.exists(idObjeto)) return 2;
		return -1;
	}
	
	public Integer aQueConjunto(String idObjeto) {
		if (conjuntoPorDiputado.exists(idObjeto)) return 1;
		if (conjuntoPorPeriodo.exists(idObjeto)) return 2;
		return -1;
	}
	
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 */
	public void remove(Integer idObjeto) {
		switch(aQueConjunto(idObjeto)){
		case 1:
			conjuntoPorDiputado.remove(idObjeto);
		break;
		case 2:
			conjuntoPorPeriodo.remove(idObjeto);
		break;
		default:
		break;
		}
	}
	public void remove(String idObjeto) {
		switch(aQueConjunto(idObjeto)){
		case 1:
			conjuntoPorDiputado.remove(idObjeto);
		break;
		case 2:
			conjuntoPorPeriodo.remove(idObjeto);
		break;
		default:
		break;
		}
	}
	
}
