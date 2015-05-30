package utiles;

import java.util.Set;

import dominio.GrupoAfin;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;

/**
 * Conjunto abstracto auxiliar que almacena grupos afines por diputado y por período.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ConjuntoGrupoAfin {
	
	/**
	 * Conjunto donde se almacenan los grupos afines por diputado.
	 */
	private Conjunto<GrupoAfinPorDiputado> conjuntoPorDiputado;
	
	/**
	 * Conjunto donde se almacenan los grupos afines por periodo.
	 */
	private Conjunto<GrupoAfinPorPeriodo> conjuntoPorPeriodo;
	
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
	 * Consulta el número de elementos del conjunto.
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
	public void addAll(Set<GrupoAfinPorDiputado> D, Set<GrupoAfinPorPeriodo> P){
		conjuntoPorDiputado.addAll(D);
		conjuntoPorPeriodo.addAll(P);
	}
	
	/**
	 * Consulta los grupos afines por diputado.
	 * @return Set que contiene todos los grupos afines por diputado.
	 */
	public Set<GrupoAfinPorDiputado> getAllPorDiputado() {
		return conjuntoPorDiputado.getAll();
	}
	
	/**
	 * Consulta los grupos afines por periodo.
	 * @return Set que contiene todos los grupos afines por periodo.
	 */
	public Set<GrupoAfinPorPeriodo> getAllPorPeriodo() {
		return conjuntoPorPeriodo.getAll();
	}
	
	/**
	 * Consulta los identificadores de los grupos afines por diputado.
	 * @return Las claves del conjunto.
	 */
	public Set<Integer> getKeysPorDiputado() {
		return conjuntoPorDiputado.getIntegerKeys();
	}
	
	/**
	 * Consulta los identificadores de los grupos afines por periodo.
	 * @return Las claves del conjunto.
	 */
	public Set<Integer> getKeysPorPeriodo() {
		return conjuntoPorPeriodo.getIntegerKeys();
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(GrupoAfinPorDiputado objeto) {
		conjuntoPorDiputado.add(objeto);
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(GrupoAfinPorPeriodo objeto) {
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
	public Boolean exists(Integer idObjeto) {
		return conjuntoPorDiputado.exists(idObjeto) || conjuntoPorPeriodo.exists(idObjeto);
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
	
	/**
	 * Devuele el Grupo Afin identificado por idObjeto
	 * @param idObjeto
	 * @return GrupoAfin
	 */
	public GrupoAfin get(Integer idObjeto) {
		if(conjuntoPorDiputado.exists(idObjeto))return conjuntoPorDiputado.get(idObjeto);
		return conjuntoPorPeriodo.get(idObjeto);
	}

}
