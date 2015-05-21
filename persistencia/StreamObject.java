package persistencia;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import exceptions.ObjectFormatException;
import time.Date;

/**
 * Objeto que contiene codificado un objeto del dominio para su posterior almacenamiento.
 * @author David Moran
 * @version 18/5/2015 22:00
 * */
public class StreamObject {
	
	/**
	 * String que almacena el contenido del objeto.
	 */
	private String contenido;
	
	/**
	 * Punteros a cada atributo del objeto.
	 */
	private Vector<Integer> indices;

	/**
	 * Instancia nula de la clase.
	 */
	public static final StreamObject NULL = new StreamObject("NULL");
	
	/**
	 * Crea un nuevo StreamObject que contiene atributos de la clase indicada.
	 * @param className - Nombre de la clase de los atributos.
	 */
	public StreamObject(String className){
		indices = new Vector<Integer>();
		indices.add(0);
		contenido = className + ':';
	}

	/**
	 * Crea un nuevo StreamObject copia de otro StreamObject.
	 * @param SO - StreamObject a ser copiado.
	 */
	public StreamObject(StreamObject SO){
		contenido = SO.contenido;
		indices = SO.indices;
	}
	
	/**
	 * Crea un nuevo StreamObject indicando el contenido y los indices.
	 * @param contenido - String que contiene todos los atributos del objeto.
	 * @param indices - Vector de enteros que contiene todos los punteros a los atributos.
	 */
	private StreamObject(String contenido, Vector<Integer> indices){
		this.contenido = contenido;
		this.indices = indices;
	}
	
	/**
	 * Consulta el número de atributos del objeto.
	 * @return El número de atributos del objeto.
	 */
	public Integer size() {
		return indices.size();
	}
	
	/**
	 * Compara dos objetos.
	 * @param SO - objeto a comparar.
	 * @return <i>true</i> si los objetos son iguales.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(StreamObject SO){
		return contenido.equals(SO.contenido);
	}
	
	/**
	 * Comprueba si el objeto es nulo.
	 * @return <i>true</i> si el objeto es nulo.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isNull(){
		return equals(NULL);
	}
	
	/**
	 * Añade un nuevo atributo al objeto.
	 * @param s - String a insertar.
	 */
	public void add(String s){
		indices.add(contenido.length());
		contenido = contenido + s;
	}
	
	/**
	 * Añade un nuevo atributo al objeto.
	 * @param i - Entero a insertar.
	 */
	public void add(Integer i){
		indices.add(contenido.length());
		contenido = contenido + i;
	}
	
	/**
	 * Añade un nuevo atributo al objeto.
	 * @param D - Fecha a insertar.
	 */
	public void add(Date D){
		indices.add(contenido.length());
		contenido = contenido + D.toString();
	}
	
	/**
	 * Añade un nuevo atributo multivaluado.
	 * @param set - Set a insertar.
	 */
	public <T extends Object> void add(Set<T> set){
		indices.add(contenido.length());
		contenido = contenido + set.size() + ':';
		for (T t:set) contenido = contenido + t.toString() + ";";
	}
	
	/**
	 * Añade un nuevo atributo multivaluado.
	 * @param set - Set a insertar.
	 */
	public <T extends Object> void add(T[] array){
		indices.add(contenido.length());
		contenido = contenido + array.length + ':';
		for (T t:array) contenido = contenido + t.toString() + ";";
	}
	
	/**
	 * Añade un nuevo StreamObject.
	 * @param o - Objeto a insertar.
	 */
	public void addObject(StreamObject o){
		indices.add(contenido.length());
		contenido = contenido + StreamObject.convert(o);
	}
	
	/**
	 * Añade un nuevo set de StreamObject.
	 * @param set - Set a insertar.
	 */
	public void addObject(Set<StreamObject> set){
		indices.add(contenido.length());
		contenido = contenido + set.size() + ':';
		for (StreamObject o:set) contenido = contenido + StreamObject.convert(o) + ";";
	}
	
	/**
	 * Consulta un atributo del objeto.
	 * @param i - Posicion del elemento a consultar.
	 * @return Un String con la información contenida.
	 */
	public String elementAt(Integer i){
		if (i >= indices.size() || i <= 0) return "";
		Integer inicio = indices.elementAt(i);
		if (i == indices.size()-1)
			return contenido.substring(inicio, contenido.length());
		Integer fin = indices.elementAt(i+1);
		return contenido.substring(inicio, fin);
	}
	
	/**
	 * Consulta la clase del objeto.
	 * @return Un String con el nombre de la clase del objeto.
	 */
	public String getNombre(){
		Integer inicio = indices.elementAt(0);
		if (indices.size() == 1)
			return contenido.substring(inicio, contenido.length());
		Integer fin = indices.elementAt(1);
		return contenido.substring(inicio, fin-1);
	}
	
	/**
	 * Consulta un elemento multivaluado del objeto.
	 * @param i - Posicion del elemento a consultar.
	 * @return Un Set de Strings con la información contenida.
	 */
	public Set<String> setAt(Integer i){
		Set<String> out = new HashSet<String>();
		String set = elementAt(i);
		if (set.isEmpty()) return out;
		
		String aux = "";
		Integer j = 0;
		while (set.charAt(j) != ':') {
			aux += set.charAt(j);
			++j;
		}
		++j;
		Integer n = Integer.parseInt(aux);
		for (Integer k = 0; k < n; ++k){
			aux = "";
			while (set.charAt(j) != ';') {
				aux += set.charAt(j);
				++j;
			}
			out.add(aux);
			++j;
		}
		return out;
	}
	
	/**
	 * Consulta un elemento multivaluado del objeto.
	 * @param i - Posicion del elemento a consultar.
	 * @return Un array de Strings con la información contenida.
	 */
	public String[] arrayAt(Integer i){
		String[] out = new String[0];
		String array = elementAt(i);
		if (array.isEmpty()) return out;
		
		String aux = "";
		Integer j = 0;
		while (array.charAt(j) != ':') {
			aux += array.charAt(j);
			++j;
		}
		++j;
		Integer n = Integer.parseInt(aux);
		out = new String[n];
		for (Integer k = 0; k < n; ++k){
			aux = "";
			while (array.charAt(j) != ';') {
				aux += array.charAt(j);
				++j;
			}
			out[k] = aux;
			++j;
		}
		return out;
	}
		
	/**
	 * Añade un nuevo StreamObject.
	 * @param o - Objeto a insertar.
	 */
	public StreamObject objectAt(Integer i){
		return StreamObject.convert(elementAt(i));
	}
	
	/**
	 * Añade un nuevo set de StreamObject.
	 * @param set - Set a insertar.
	 */
	public Set<StreamObject> setObjectAt(Integer i){
		String set = elementAt(i);
		Set<StreamObject> out = new HashSet<StreamObject>();
		
		String aux = "";
		Integer j = 0;
		while (set.charAt(j) != ':') {
			aux += set.charAt(j);
			++j;
		}
		++j;
		Integer n = Integer.parseInt(aux);
		for (Integer k = 0; k < n; ++k){
			Integer jaux = j;
			while (set.charAt(j-1) != ';' || set.charAt(j) != ';') ++j;
			out.add(StreamObject.convert(set.substring(jaux, j)));
			++j;
		}
		return out;

	}
	
	/**
	 * Elimina todos los atributos del objeto, excepto su clase.
	 */
	public void clear(){
		contenido = elementAt(0) + ':';
		indices.clear();
		indices.add(0);
	}
	
	/**
	 * Codifica el StreamObject en un String.
	 * @param SO - StreamObject a codificar.
	 * @return El String con SO codificado.
	 */
	public static String convert(StreamObject SO){
		String out = "" + SO.indices.size() + ':';
		for (Integer i = 0; i < SO.indices.size(); ++i){
			out = out + SO.indices.elementAt(i).toString() + ';';
		}
		out += SO.contenido;
		return out;
	}
	
	/**
	 * Consulta el StreamObject codificado en un String.
	 * @param S - El String a decodificar.
	 * @return El StreamObject decodificado.
	 */
	public static StreamObject convert(String S) throws ObjectFormatException {
		if (S.isEmpty()) throw new ObjectFormatException(false, "String vacio.");
		Integer i = 0;
		Vector<Integer> V = new Vector<Integer>();
		String aux = "";
		while (S.charAt(i) != ':') {
			aux += S.charAt(i);
			++i;
		}
		Integer n;
		try {
			n = Integer.parseInt(aux);
		} catch (NumberFormatException e) {
			throw new ObjectFormatException(true, e.getMessage());
		}
		++i;
		for (Integer j = 0; j < n; ++j) {
			aux = "";
			while (S.charAt(i) != ';') {
				aux += S.charAt(i);
				++i;
			}
			try {
				V.add(Integer.parseInt(aux));
			} catch (NumberFormatException e) {
				throw new ObjectFormatException(true, e.getMessage());
			}
			++i;
		}
		S = S.substring(i, S.length());
		if (S.length() < V.lastElement()) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		return new StreamObject(S, V);
	}
}