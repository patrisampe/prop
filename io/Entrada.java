package io;

import java.io.IOException;
import java.util.Set;

/**
 * Entrada genérica que permite leer cualquier tipo de dato básico.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public interface Entrada {

	/**
	 * Cierra el canal de entrada.
	 */
	public void close();
	
	/**
	 * Lee un String.
	 * @return El String leido.
	 * @throws IOException 
	 */
	public String readString() throws IOException;
	
	/**
	 * Lee una linea y la almacena en un String.
	 * @return La linea leida.
	 * @throws IOException 
	 */
	public String readLine() throws IOException;
	
	/**
	 * Lee un carácter primitivo.
	 * @return El carácter primitivo leido.
	 * @throws IOException 
	 */
	public char readChar() throws IOException;
	
	/**
	 * Lee un entero primitivo.
	 * @return El entero primitivo leido.
	 * @throws IOException 
	 */
	public int readInt() throws IOException;
	
	/**
	 * Lee un carácter.
	 * @return El carácter leido.
	 * @throws IOException 
	 */
	public Character readCharacter() throws IOException;
	
	/**
	 * Lee un entero.
	 * @return El entero leido.
	 * @throws IOException 
	 */
	public Integer readInteger() throws IOException;
	
	/**
	 * Lee un Double.
	 * @return El Double leido.
	 * @throws IOException 
	 */
	public Double readDouble() throws IOException;
	
	/**
	 * Lee un Boolean.
	 * @return El Boolean leido.
	 * @throws IOException 
	 */
	public Boolean readBoolean() throws IOException;
	
	/**
	 * Lee un Long.
	 * @return El Long leido.
	 */
	public Long readLong();
	
	/**
	 * Lee <i>n</i> carácteres primitivos.
	 * @param n - Numero de carácteres a leer.
	 * @return Un array con los carácteres leidos, en orden.
	 * @throws IOException 
	 */
	public char[] readChar(int n) throws IOException;
	
	/**
	 * Lee <i>n</i> enteros primitivos.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 * @throws IOException 
	 */
	public int[] readInt(int n) throws IOException;
	
	/**
	 * Lee <i>n</i> carácteres.
	 * @param n - Numero de carácteres a leer.
	 * @return Un array con los carácteres leidos, en orden.
	 * @throws IOException 
	 */
	public Character[] readCharacter(int n) throws IOException;

	
	/**
	 * Lee <i>n</i> enteros.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 * @throws IOException 
	 */
	public Integer[] readInteger(int n) throws IOException;

	/**
	 * Lee <i>n</i> Doubles.
	 * @param n - Numero de Doubles a leer.
	 * @return Un array con los Doubles leidos, en orden.
	 * @throws IOException 
	 */
	public Double[] readDouble(int n) throws IOException;
	
	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un array con los Strings leidos, en orden.
	 * @throws IOException 
	 */
	public String[] readString(int n) throws IOException;
	
	/**
	 * Lee <i>n</i> Booleans.
	 * @param n - Numero de Booleans a leer.
	 * @return Un array con los Booleans leidos, en orden.
	 * @throws IOException 
	 */
	public Boolean[] readBoolean(int n) throws IOException;

	/**
	 * Lee <i>n</i> Longs.
	 * @param n - Numero de Longs a leer.
	 * @return Un array con los Longs leidos, en orden.
	 * @throws IOException 
	 */
	public Long[] readLong(int n) throws IOException;

	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un Set con los Strings leidos.
	 * @throws IOException 
	 */
	public Set<String> readSetString(int n) throws IOException;
	
	/**
	 * Lee <i>n</i> Integers.
	 * @param n - Numero de Integers a leer.
	 * @return Un Set con los Integers leidos.
	 * @throws IOException 
	 */
	public Set<Integer> readSetInteger(int n) throws IOException;

}
