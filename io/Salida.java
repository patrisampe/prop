package io;

import java.util.Set;

/**
 * Salida genérica que permite escribir cualquier tipo de dato básico.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public interface Salida {

	/**
	 * Cierra el canal de salida.
	 */
	public void close();

	
	/**
	 * Escribe un salto de linea.
	 */
	public void write();

	/**
	 * Escribe un String.
	 * @param s - El String a escribir.
	 */
	public void write(String s);
	
	/**
	 * Escribe un carácter primitivo.
	 * @param c - El carácter primitivo a escribir.
	 */
	public void write(char c);
	
	/**
	 * Escribe un entero primitivo.
	 * @param n - El entero primitivo a escribir.
	 */
	public void write(int n);
	
	/**
	 * Escribe un entero.
	 * @param n - El entero a escribir.
	 */
	public void write(Integer n);
	
	/**
	 * Escribe un Double.
	 * @param d - El Double a escribir.
	 */
	public void write(Double d);
	
	/**
	 * Escribe un Boolean.
	 * @param b - El Boolean a escribir.
	 */
	public void write(Boolean b);

	/**
	 * Escribe un Long.
	 * @param l - El long a escribir.
	 */
	public void write(Long l);
	
	/**
	 * Escribe <i>n</i> carácteres primitivos.
	 * @param a - Array con los carácteres primitivos a escribir.
	 */
	public void write(char[] a);
	
	/**
	 * Escribe <i>n</i> enteros primitivos.
	 * @param a - Array con los enteros primitivos a escribir.
	 */
	public void write(int[] a);
	
	/**
	 * Escribe <i>n</i> carácteres.
	 * @param a - Array con los carácteres a escribir.
	 */
	public void write(Character[] a);

	/**
	 * Escribe <i>n</i> enteros.
	 * @param a - Array con los enteros a escribir.
	 */
	public void write(Integer[] a);
	
	/**
	 * Escribe <i>n</i> Strings.
	 * @param a - Array con los Strings a escribir.
	 */
	public void write(String[] a);
	
	/**
	 * Escribe <i>n</i> Doubles.
	 * @param a - Array con los Doubles a escribir.
	 */
	public void write(Double[] a);
	
	/**
	 * Escribe <i>n</i> Booleans.
	 * @param a - Array con los Booleans a escribir.
	 */
	public void write(Boolean[] a);

	/**
	 * Escribe <i>n</i> Longs.
	 * @param a - Array con los Longs a escribir.
	 */
	public void write(Long[] a);

	/**
	 * Escribe el contenido de un Set de Strings.
	 * @param set - Set con los Strings a escribir.
	 */
	public <T extends Object> void write(Set<T> set);

}