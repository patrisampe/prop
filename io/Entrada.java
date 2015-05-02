package io;

import java.util.Set;

/**
 * Entrada generica que permite leer cualquier tipo de dato basico.
 * @author David Moran
 * @version 03/05/2015 01:02
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
	public String ReadString();
	
	/**
	 * Lee una linea y la almacena en un String.
	 * @return La linea leida.
	 */
	public String ReadLine();
	
	/**
	 * Lee un caracter primitivo.
	 * @return El caracter primitivo leido.
	 */
	public char ReadChar();
	
	/**
	 * Lee un entero primitivo.
	 * @return El entero primitivo leido.
	 */
	public int ReadInt();
	
	/**
	 * Lee un caracter.
	 * @return El caracter leido.
	 */
	public Character ReadCharacter();
	
	/**
	 * Lee un entero.
	 * @return El entero leido.
	 */
	public Integer ReadInteger();
	
	/**
	 * Lee un Double.
	 * @return El Double leido.
	 */
	public Double ReadDouble();
	
	/**
	 * Lee un Boolean.
	 * @return El Boolean leido.
	 */
	public Boolean ReadBoolean();
	
	/**
	 * Lee un Long.
	 * @return El Long leido.
	 */
	public Long ReadLong();
	
	/**
	 * Lee <i>n</i> caracteres primitivos.
	 * @param n - Numero de caracteres a leer.
	 * @return Un array con los caracteres leidos, en orden.
	 */
	public char[] ReadChar(int n);
	
	/**
	 * Lee <i>n</i> enteros primitivos.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 */
	public int[] ReadInt(int n);
	
	/**
	 * Lee <i>n</i> enteros.
	 * @param n - Numero de enteros a leer.
	 * @return Un array con los enteros leidos, en orden.
	 */
	public Integer[] ReadInteger(int n);

	/**
	 * Lee <i>n</i> Doubles.
	 * @param n - Numero de Doubles a leer.
	 * @return Un array con los Doubles leidos, en orden.
	 */
	public Double[] ReadDouble(int n);
	
	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un array con los Strings leidos, en orden.
	 */
	public String[] ReadString(int n);
	
	/**
	 * Lee <i>n</i> Booleans.
	 * @param n - Numero de Booleans a leer.
	 * @return Un array con los Booleans leidos, en orden.
	 */
	public Boolean[] ReadBoolean(int n);

	/**
	 * Lee <i>n</i> Longs.
	 * @param n - Numero de Longs a leer.
	 * @return Un array con los Longs leidos, en orden.
	 */
	public Long[] ReadLong(int n);

	/**
	 * Lee <i>n</i> Strings.
	 * @param n - Numero de Strings a leer.
	 * @return Un Set con los Strings leidos.
	 */
	public Set<String> ReadSetString(int n);
	
}
