package io;

import java.util.Set;

/**
 * Salida generica que permite escribir cualquier tipo de dato basico.
 * @author David Moran
 * @version 03/05/2015 01:02
 */
public interface Sortida {

	/**
	 * Cierra el canal de salida.
	 */
	public void close();

	/**
	 * Escribe un String.
	 * @param s - El String a escribir.
	 */
	public void Write(String s);
	
	/**
	 * Escribe un caracter primitivo.
	 * @param c - El caracter primitivo a escribir.
	 */
	public void Write(char c);
	
	/**
	 * Escribe un entero primitivo.
	 * @param n - El entero primitivo a escribir.
	 */
	public void Write(int n);
	
	/**
	 * Escribe un entero.
	 * @param n - El entero a escribir.
	 */
	public void Write(Integer n);
	
	/**
	 * Escribe un Double.
	 * @param d - El Double a escribir.
	 */
	public void Write(Double d);
	
	/**
	 * Escribe un Boolean.
	 * @param b - El Boolean a escribir.
	 */
	public void Write(Boolean b);

	/**
	 * Escribe un Long.
	 * @param l - El long a escribir.
	 */
	public void Write(Long l);
	
	/**
	 * Escribe <i>n</i> caracteres primitivos.
	 * @param n - Numero de caracteres primitivos a escribir.
	 * @param a - Array con los caracteres primitivos a escribir.
	 */
	public void Write(int n, char[] a);
	
	/**
	 * Escribe <i>n</i> enteros primitivos.
	 * @param n - Numero de enteros primitivos a escribir.
	 * @param a - Array con los enteros primitivos a escribir.
	 */
	public void Write(int n, int[] a);
	
	/**
	 * Escribe <i>n</i> enteros.
	 * @param n - Numero de enteros a escribir.
	 * @param a - Array con los enteros a escribir.
	 */
	public void Write(int n, Integer[] a);
	
	/**
	 * Escribe <i>n</i> Strings.
	 * @param n - Numero de Strings a escribir.
	 * @param a - Array con los Strings a escribir.
	 */
	public void Write(int n, String[] a);
	
	/**
	 * Escribe <i>n</i> Doubles.
	 * @param n - Numero de Doubles a escribir.
	 * @param a - Array con los Doubles a escribir.
	 */
	public void Write(int n, Double[] a);
	
	/**
	 * Escribe <i>n</i> Booleans.
	 * @param n - Numero de Booleans a escribir.
	 * @param a - Array con los Booleans a escribir.
	 */
	public void Write(int n, Boolean[] a);

	/**
	 * Escribe <i>n</i> Longs.
	 * @param n - Numero de Longs a escribir.
	 * @param a - Array con los Longs a escribir.
	 */
	public void Write(int n, Long[] a);

	/**
	 * Escribe el contenido de un Set de Strings.
	 * @param set - Set con los Strings a escribir.
	 */
	public void Write(Set<String> set);
		
}