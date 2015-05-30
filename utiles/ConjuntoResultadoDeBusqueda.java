package utiles;

import java.util.Set;

import dominio.ResultadoDeBusqueda;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.ResultadoDeBusquedaPorPeriodo;
/**
 * Conjunto abstracto auxiliar que almacena resultados de Busqueda por diputado y por período.
 * @author patricia
 *
 */
public class ConjuntoResultadoDeBusqueda {
	/**
	 * Conjunto donde se almacenan los resultados de busqueda por diputado.
	 */
	private Conjunto<ResultadoDeBusquedaPorDiputado> conjuntoPorDiputado;
	
	/**
	 * Conjunto donde se almacenan los resultados de busqueda por periodo.
	 */
	private Conjunto<ResultadoDeBusquedaPorPeriodo> conjuntoPorPeriodo;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de clase ResultadoDeBusqueda.
	 */
	public ConjuntoResultadoDeBusqueda(){
		conjuntoPorDiputado = new Conjunto<ResultadoDeBusquedaPorDiputado>(ResultadoDeBusquedaPorDiputado.class);
		conjuntoPorPeriodo = new Conjunto<ResultadoDeBusquedaPorPeriodo>(ResultadoDeBusquedaPorPeriodo.class);
	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */
	public ConjuntoResultadoDeBusqueda(ConjuntoResultadoDeBusqueda C){
		conjuntoPorDiputado = new Conjunto<ResultadoDeBusquedaPorDiputado>(C.conjuntoPorDiputado);
		conjuntoPorPeriodo = new Conjunto<ResultadoDeBusquedaPorPeriodo>(C.conjuntoPorPeriodo);
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
	public void addAll(Set<ResultadoDeBusquedaPorDiputado> D, Set<ResultadoDeBusquedaPorPeriodo> P){
		conjuntoPorDiputado.addAll(D);
		conjuntoPorPeriodo.addAll(P);
	}
	
	/**
	 * Consulta los resultados de busqueda por diputado.
	 * @return Set que contiene todos los resultados de busqueda por diputado.
	 */
	public Set<ResultadoDeBusquedaPorDiputado> getAllPorDiputado() {
		return conjuntoPorDiputado.getAll();
	}
	
	/**
	 * Consulta los resultados de busqueda por periodo.
	 * @return Set que contiene todos los resultados de busqueda por periodo.
	 */
	public Set<ResultadoDeBusquedaPorPeriodo> getAllPorPeriodo() {
		return conjuntoPorPeriodo.getAll();
	}
	
	/**
	 * Consulta los identificadores de los resultados de busqueda por diputado.
	 * @return Las claves del conjunto.
	 */
	public Set<String> getKeysPorDiputado() {
		return conjuntoPorDiputado.getStringKeys();
	}
	
	/**
	 * Consulta los identificadores de los resultados de busqueda por periodo.
	 * @return Las claves del conjunto.
	 */
	public Set<String> getKeysPorPeriodo() {
		return conjuntoPorPeriodo.getStringKeys();
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(ResultadoDeBusquedaPorDiputado objeto) {
		conjuntoPorDiputado.add(objeto);
	}
	
	/**
	 * Añade un elemento al conjunto.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(ResultadoDeBusquedaPorPeriodo objeto) {
		conjuntoPorPeriodo.add(objeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public ResultadoDeBusquedaPorDiputado getPorDiputado(String nombreObjeto) {
		return conjuntoPorDiputado.get(nombreObjeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public ResultadoDeBusquedaPorPeriodo getPorPeriodo(String nombreObjeto) {
		return conjuntoPorPeriodo.get(nombreObjeto);
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(String nombreObjeto) {
		return conjuntoPorDiputado.exists(nombreObjeto) || conjuntoPorPeriodo.exists(nombreObjeto);
	}
	
	/**
	 * Consulta a que conjunto pertenece el elemento.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return <i>1</i> si el elemento pertenece al conjunto por diputado.
	 * <br>
	 * <i>2</i> si el elemento pertenece al conjunto por periodo.
	 * <br>
	 * <i>-1</i> si el elemento no pertenece a ningun conjunto.
	 */
	private Integer aQueConjunto(String nombreObjeto) {
		if (conjuntoPorDiputado.exists(nombreObjeto)) return 1;
		if (conjuntoPorPeriodo.exists(nombreObjeto)) return 2;
		return -1;
	}
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Identificador del elemento.
	 */
	public void remove(String nombreObjeto) {
		switch(aQueConjunto(nombreObjeto)){
		case 1:
			conjuntoPorDiputado.remove(nombreObjeto);
		break;
		case 2:
			conjuntoPorPeriodo.remove(nombreObjeto);
		break;
		default:
		break;
		}
	}
	
	/**
	 * Inserta un resultado de busqueda al conjunto
	 * Es una forma de ocultar el polimorfismo con generecidad
	 * @param RDB resultado de Busqueda que se quiere insertar
	 */
	public void add(ResultadoDeBusqueda RDB) {
		switch(aQueConjunto(RDB.getNombre())){
		case 1:
			conjuntoPorDiputado.add((ResultadoDeBusquedaPorDiputado) RDB);
		break;
		case 2:
			conjuntoPorPeriodo.add((ResultadoDeBusquedaPorPeriodo) RDB);
		break;
		default:
		break;
		}
	}
	/**
	 * Consulta los identificadores de todos los resultados de busqueda.
	 * @return Las claves del conjunto.
	 */
	public Set<String> getStringKeys(){
		
		Set<String> aux=conjuntoPorDiputado.getStringKeys();
		aux.addAll(conjuntoPorPeriodo.getStringKeys());
		return aux;
	}
	/**
	 * Devuelve el resultado de Busqueda identificado por nombreObjeto
	 * @param nombreObjeto es el identificador del Resultado de Busqueda 
	 * @return Resultado de Busqueda
	 */
	public ResultadoDeBusqueda get(String nombreObjeto) {
		if(conjuntoPorDiputado.exists(nombreObjeto))return conjuntoPorDiputado.get(nombreObjeto);
		return conjuntoPorPeriodo.get(nombreObjeto);
	}
	/**
	 * Consulta si existe un elemento del conjunto por Diputados.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto por Diputados contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsPorDiputado(String nombreObjeto){
		return conjuntoPorDiputado.exists(nombreObjeto);
	}
	/**
	 * Consulta si existe un elemento del conjunto por Periodo.
	 * @param nombreObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto por Periodo contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsPorPeriodo(String nombreObjeto){
		return conjuntoPorPeriodo.exists(nombreObjeto);
	}

}
