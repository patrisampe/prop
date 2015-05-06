package utiles;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import dominio.*;

/**
 * Conjunto abstracto que almacena diferentes objetos del dominio.
 * @author David Moran
 * @version 06/05/2015 15:00
 */
public class Conjunto<T extends ObjetoDominio> {
	
	/**
	 * Conjunto base donde se almacenan los datos.
	 */
	private Map<String, T> conjunto;
	
	/**
	 * Indica si la clave del conjunto abstracto es un numero (<i>true</i>) o un nombre (<i>false</i>).
	 */
	private final Boolean hasIntegerKey;
	
	/**
	 * Clase contenida por el conjutno.
	 */
    private final Class<T> type;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de calse T.
	 * @param type - Clase de los objetos que contendra el conjunto.
	 */
	public Conjunto(Class<T> type){
		this.type = type;
		conjunto = new TreeMap<String, T>();
		hasIntegerKey = (type.equals(Legislatura.class)
					  || type.equals(GrupoAfinPorDiputado.class)
					  || type.equals(GrupoAfinPorPeriodo.class));
	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */
	public Conjunto(Conjunto<T> C){
		this.type = C.type;
		conjunto = new TreeMap<String, T>(C.conjunto);
		hasIntegerKey = C.hasIntegerKey;
	}
	
	/**
	 * Consulta la clase del conjunto.
	 * @return Clase de los objetos que contiene el conjunto.
	 */
	public Class<T> getValueType(){
		return type;
	}
	
	/**
	 * Consulta el tipo de clave del conjunto.
	 * @return <i>true</i> si la clave del conjunto es un identificador.
	 * <br>
	 * <i>false</i> si la clave del conjunto es un nombre.
	 */
	public Boolean KeyTypeIsInteger(){
		return hasIntegerKey;
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
	public void addAll(Set<T> S){
		for (T elemento:S) {
			if (!hasIntegerKey) conjunto.put(elemento.getNombre(), elemento);
			else conjunto.put(elemento.getID().toString(), elemento);				
		}
	}
	
	/**
	 * Consulta todos los elementos del conjunto.
	 * @return Set que contiene todos los elementos del conjunto.
	 */
	public Set<T> getAll() {
		Set<T> S = new TreeSet<T>();
		for (String clave:conjunto.keySet()) S.add(conjunto.get(clave));
		return S;
	}
	
	/**
	 * Consulta las claves del conjunto.
	 * @return Las claves del conjunto, si y solo si el conjunto esta mapeado con identificadores.
	 */
	public Set<Integer> getIntegerKeys() {
		if (!hasIntegerKey) return new TreeSet<Integer>();
		Set<Integer> S = new TreeSet<Integer>();
		for (String clave:conjunto.keySet()) S.add(Integer.parseInt(clave));			
		return S;
	}
	
	/**
	 * Consulta las claves del conjunto.
	 * @return Las claves del conjunto, si y solo si el conjunto esta mapeado con nombres.
	 */
	public Set<String> getStringKeys() {
		if (hasIntegerKey) return new TreeSet<String>();
		return conjunto.keySet();
	}

	/**
	 * Introduce un elemento al conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(String nombreObjeto, T objeto) {
		if (!hasIntegerKey) conjunto.put(nombreObjeto, objeto);
	}
	
	/**
	 * Introduce un elemento al conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @param objeto - Elemento a introducir.
	 */
	public void add(Integer idObjeto, T objeto) {
		if (hasIntegerKey) conjunto.put(idObjeto.toString(), objeto);
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @return Objeto identificado con el nombre.
	 */
	public T get(String nombreObjeto) {
		if (!hasIntegerKey) return conjunto.get(nombreObjeto);
		else return null;
	}
	
	/**
	 * Consulta un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return Objeto identificado con el identificador.
	 */
	public T get(Integer idObjeto) {
		if (hasIntegerKey) return conjunto.get(idObjeto.toString());
		else return null;
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(String nombreObjeto) {
		if (!hasIntegerKey) return conjunto.containsKey(nombreObjeto);
		else return false;
	}
	
	/**
	 * Consulta si existe un elemento del conjunto.
	 * @param idObjeto - Identificador del elemento.
	 * @return <i>true</i> si el conjunto contiene el elemento.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean exists(Integer idObjeto) {
		if (hasIntegerKey) return conjunto.containsKey(idObjeto.toString());
		else return false;
	}
	
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 */
	public void remove(String nombreObjeto) {
		if (!hasIntegerKey) conjunto.remove(nombreObjeto);
	}
	
	/**
	 * Elimina un elemento del conjunto.
	 * @param nombreObjeto - Nombre del elemento.
	 * @param idObjeto - Identificador del elemento.
	 */
	public void remove(Integer idObjeto) {
		if (hasIntegerKey) conjunto.remove(idObjeto.toString());
	}
	
	/**
	 * Realiza la union de dos conjuntos del mismo tipo.
	 * @param C1 - Primer conjunto.
	 * @param C2 - Segundo conjunto.
	 * @return Conjunto resultante de la union (C1_u_C2).
	 */
	public static <U extends ObjetoDominio> Conjunto<U> union(Conjunto<U> C1, Conjunto<U> C2) {
		Set<U> S = new TreeSet<U>();
		for (U elemento:C1.getAll()) S.add(elemento);
		for (U elemento:C2.getAll()) S.add(elemento);
		Class<U> tipo = C1.type;
		Conjunto<U> C = new Conjunto<U>(tipo);
		C.addAll(S);
		return C;
	}
	
	/**
	 * Realiza la interseccion de dos conjuntos del mismo tipo.
	 * @param C1 - Primer conjunto.
	 * @param C2 - Segundo conjunto.
	 * @return Conjunto resultante de la interseccion (C1_n_C2).
	 */
	public static <U extends ObjetoDominio> Conjunto<U> intersection(Conjunto<U> C1, Conjunto<U> C2) {
		Set<U> S = new TreeSet<U>();		
		for (U elemento:C1.getAll()) {
			if (C2.getAll().contains(elemento)) S.add(elemento);
		}
		for (U elemento:C2.getAll()) {
			if (C1.getAll().contains(elemento)) S.add(elemento);
		}
		Class<U> tipo = C1.type;
		Conjunto<U> C = new Conjunto<U>(tipo);
		C.addAll(S);
		return C;
	}
	
	/**
	 * Realiza la diferencia de dos conjuntos del mismo tipo.
	 * @param C1 - Primer conjunto.
	 * @param C2 - Segundo conjunto.
	 * @return Conjunto resultante de la diferencia (C1 - C2).
	 */
	public static <U extends ObjetoDominio> Conjunto<U> difference(Conjunto<U> C1, Conjunto<U> C2) {
		Set<U> S = new TreeSet<U>();		
		for (U elemento:C1.getAll()) {
			if (!C2.getAll().contains(elemento)) S.add(elemento);
		}
		Class<U> tipo = C1.type;
		Conjunto<U> C = new Conjunto<U>(tipo);
		C.addAll(S);
		return C;
	}
	
}