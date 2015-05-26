package persistencia;

import java.util.Vector;

import exceptions.ContainerFormatException;
import exceptions.ObjectFormatException;

/**
 * Objeto que contiene codificado varios StreamObject agrupados bajo un mismo nombre para su posterior almacenamiento.
 * @author David Moran
 * @version 18/5/2015 22:00
 * */
public class StreamContainer {
	
	/**
	 * String que almacena el contenido del objeto.
	 */
	private String contenido;
	
	/**
	 * Punteros a cada objeto del contenedor.
	 */
	private Vector<Integer> indices;

	/**
	 * Instancia nula de la clase.
	 */
	public static final StreamContainer NULL = new StreamContainer("NULL");
	
	/**
	 * Crea un nuevo StreamContainer con el nombre indicado.
	 * @param name - Nombre atorgado al StreamContainer.
	 */
	public StreamContainer(String name){
		indices = new Vector<Integer>();
		indices.add(0);
		contenido = name + ':';
	}
	
	/**
	 * Crea un nuevo StreamContainer copia de otro StreamContainer.
	 * @param SC - StreamContainer a ser copiado.
	 */
	public StreamContainer(StreamContainer SC){
		indices = SC.indices;
		contenido = SC.contenido;
	}
	
	/**
	 * Crea un nuevo StreamContainer directamente indicando el contenido y los indices.
	 * @param contenido - String que contiene todos los StreamObject del objeto.
	 * @param indices - Vector de enteros que contiene todos los punteros a los StreamObject.
	 */
	private StreamContainer(String contenido, Vector<Integer> indices){
		this.contenido = contenido;
		this.indices = indices;
	}
	
	/**
	 * Consulta el número de elementos del contenedor (incluyendo el nombre).
	 * @return El número de elementos del contenedor.
	 */
	public Integer size() {
		return indices.size();
	}
	
	/**
	 * Consulta la longitud (en carácteres) del contenedor.
	 * @return La longitud del contenedor.
	 */
	public Integer length() {
		Integer l = 0;
		l += Integer.valueOf(indices.size()).toString().length();
		l = l + indices.size() + 1;
		for (Integer i = 0; i < indices.size(); ++i)
			l += indices.elementAt(i).toString().length();
		l += contenido.length();
		return l;
	}
	
	/**
	 * Compara dos objetos.
	 * @param SC - objeto a comparar.
	 * @return <i>true</i> si los objetos son iguales.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(StreamContainer SC){
		return contenido.equals(SC.contenido);
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
	 * Inserta un nuevo StreamObject al contenedor.
	 * @param SO - StreamObject a insertar.
	 */
	public void add(StreamObject SO){
		indices.add(contenido.length());
		contenido = contenido + StreamObject.convert(SO);
	}
	
	/**
	 * Consulta el nombre del contenedor.
	 * @return Un String con el nombre del conjunto.
	 */
	public String getName() {
		Integer inicio = indices.elementAt(0);
		Integer fin = (indices.size() > 1 ? indices.elementAt(1) : contenido.length());
		return contenido.substring(inicio, fin-1);
	}
	
	/**
	 * Consulta un elemento del contenedor.
	 * @param i - Posicion del elemento deseado.
	 * @return El StreamObject situado en la posición indicada.
	 * @throws ContainerFormatException 
	 */
	public StreamObject elementAt(Integer i) throws ContainerFormatException{
		if (i >= indices.size() || i <= 0) throw new ContainerFormatException("El objeto indicado no es valido.");
		Integer inicio = indices.elementAt(i);
		if (i == indices.size()-1)
			return StreamObject.convert(contenido.substring(inicio, contenido.length()));
		Integer fin = indices.elementAt(i+1);
		return StreamObject.convert(contenido.substring(inicio, fin)); 
	}
	
	/**
	 * Consulta un objeto dentro de un elemento del contenedor.
	 * @param i - Posición del elemento deseado en el contenedor.
	 * @param j - Posición del atributo deseado en el objeto.
	 * @return El atributo situado en la posición indicada.
	 * @throws ContainerFormatException 
	 */
	public String elementAt(Integer i, Integer j) throws ContainerFormatException {
		return elementAt(i).elementAt(j);
	}
	
	/**
	 * Elimina todos los elementos del conjunto, excepto su nombre.
	 */
	public void clear(){
		contenido = getName() + ':';
		indices.clear();
		indices.add(0);
	}
	
	/**
	 * Codifica el StreamContainer en un String.
	 * @param SC - StreamContainer a codificar.
	 * @return El String con SC codificado.
	 */
	public static String convert(StreamContainer SC){
		String out = "" + SC.indices.size() + ':';
		for (Integer i = 0; i < SC.indices.size(); ++i){
			out = out + SC.indices.elementAt(i).toString() + ';';
		}
		out += SC.contenido;
		return out;
	}
	
	/**
	 * Consulta el StreamContainer codificado en un String.
	 * @param S - El String a decodificar.
	 * @return El StreamContainer decodificado.
	 */
	public static StreamContainer convert(String S) throws ContainerFormatException {
		if (S.isEmpty()) throw new ContainerFormatException(-1, "String vacio.");
		Integer i = 0;
		Vector<Integer> V = new Vector<Integer>();
		String aux = "";
		while (S.charAt(i) != ':') {
			aux += S.charAt(i);
			++i;
			if (i >= S.length()) throw new ContainerFormatException("Formato de contenedor invalido.");
		}
		Integer n;
		try {
			n = Integer.parseInt(aux);
		} catch (NumberFormatException e) {
			throw new ContainerFormatException(e.getMessage());
		}
		++i;
		for (Integer j = 0; j < n; ++j) {
			aux = "";
			while (S.charAt(i) != ';') {
				aux += S.charAt(i);
				++i;
				if (i >= S.length()) throw new ContainerFormatException("Formato de contenedor invalido.");
			}
			try {
				V.add(Integer.parseInt(aux));
			} catch (NumberFormatException e) {
				throw new ContainerFormatException(e.getMessage());
			}
			++i;
			if (i >= S.length()) throw new ContainerFormatException("Formato de contenedor invalido.");
		}

		S = S.substring(i, S.length());
		if (S.length() < V.lastElement()) throw new ContainerFormatException(-1, "Longitud del contenedor incorrecta.");
		StreamContainer SC = new StreamContainer(S, V);
		for (Integer k = 1; k < V.size(); ++k) {
			try {
				SC.elementAt(k);
			} catch (ObjectFormatException e) {
				throw new ContainerFormatException(k, e.getMessage());
			}
		}
		return new StreamContainer(S, V);
	}
	
}