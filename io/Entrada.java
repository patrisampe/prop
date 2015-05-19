package io;

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
	 */
	public String readString();
	
	/**
	 * Lee una linea y la almacena en un String.
	 * @return La linea leida.
	 */
	public String readLine();
	
	/**
	 * Lee un carácter primitivo.
	 * @return El carácter primitivo leido.
	 */
	public char readChar();
	
	/**
	 * Lee un entero primitivo.
	 * @return El entero primitivo leido.
	 */
	public int readInt();
	
	/**
	 * Lee un carácter.
	 * @return El carácter leido.
	 */
	public Character readCharacter();
	
	/**
	 * Lee un entero.
	 * @return El entero leido.
	 */
	public Integer readInteger();
	
	/**
	 * Lee un Double.
	 * @return El Double leido.
	 */
	public Double readDouble();
	
	/**
	 * Lee un Boolean.
	 * @return El Boolean leido.
	 */
	public Boolean readBoolean();
	
	/**
	 * Lee un Long.
	 * @return El Long leido.
	 */
	public Long readLong();
	
	/**
	 * Lee <i>n</i> carácteres primitivos.
	 * @param n - Numero de carácteres a leer.
	 * @return Un array con los carácteres leidos, en orden.
	 */
	public char[] readChar(int n);
	
	/**
	 * Lee <i>n</i> enteros primitivos.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 */
	public int[] readInt(int n);
	
	/**
	 * Lee <i>n</i> carácteres.
	 * @param n - Numero de carácteres a leer.
	 * @return Un array con los carácteres leidos, en orden.
	 */
	public Character[] readCharacter(int n);

	
	/**
	 * Lee <i>n</i> enteros.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 */
	public Integer[] readInteger(int n);

	/**
	 * Lee <i>n</i> Doubles.
	 * @param n - Numero de Doubles a leer.
	 * @return Un array con los Doubles leidos, en orden.
	 */
	public Double[] readDouble(int n);
	
	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un array con los Strings leidos, en orden.
	 */
	public String[] readString(int n);
	
	/**
	 * Lee <i>n</i> Booleans.
	 * @param n - Numero de Booleans a leer.
	 * @return Un array con los Booleans leidos, en orden.
	 */
	public Boolean[] readBoolean(int n);

	/**
	 * Lee <i>n</i> Longs.
	 * @param n - Numero de Longs a leer.
	 * @return Un array con los Longs leidos, en orden.
	 */
	public Long[] readLong(int n);

	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un Set con los Strings leidos.
	 */
	public Set<String> readSetString(int n);
	
}
