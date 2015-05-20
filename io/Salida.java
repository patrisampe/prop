package io;

import java.io.IOException;
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
	 * @throws IOException 
	 */
	public void write() throws IOException;

	/**
	 * Escribe un String.
	 * @param s - El String a escribir.
	 * @throws IOException 
	 */
	public void write(String s) throws IOException;
	
	/**
	 * Escribe un carácter primitivo.
	 * @param c - El carácter primitivo a escribir.
	 * @throws IOException 
	 */
	public void write(char c) throws IOException;
	
	/**
	 * Escribe un entero primitivo.
	 * @param n - El entero primitivo a escribir.
	 * @throws IOException 
	 */
	public void write(int n) throws IOException;
	
	/**
	 * Escribe un entero.
	 * @param n - El entero a escribir.
	 * @throws IOException 
	 */
	public void write(Integer n) throws IOException;
	
	/**
	 * Escribe un Double.
	 * @param d - El Double a escribir.
	 * @throws IOException 
	 */
	public void write(Double d) throws IOException;
	
	/**
	 * Escribe un Boolean.
	 * @param b - El Boolean a escribir.
	 * @throws IOException 
	 */
	public void write(Boolean b) throws IOException;

	/**
	 * Escribe un Long.
	 * @param l - El long a escribir.
	 * @throws IOException 
	 */
	public void write(Long l) throws IOException;
	
	/**
	 * Escribe <i>n</i> carácteres primitivos.
	 * @param a - Array con los carácteres primitivos a escribir.
	 * @throws IOException 
	 */
	public void write(char[] a) throws IOException;
	
	/**
	 * Escribe <i>n</i> enteros primitivos.
	 * @param a - Array con los enteros primitivos a escribir.
	 * @throws IOException 
	 */
	public void write(int[] a) throws IOException;
	
	/**
	 * Escribe <i>n</i> carácteres.
	 * @param a - Array con los carácteres a escribir.
	 * @throws IOException 
	 */
	public void write(Character[] a) throws IOException;

	/**
	 * Escribe <i>n</i> enteros.
	 * @param a - Array con los enteros a escribir.
	 * @throws IOException 
	 */
	public void write(Integer[] a) throws IOException;
	
	/**
	 * Escribe <i>n</i> Strings.
	 * @param a - Array con los Strings a escribir.
	 * @throws IOException 
	 */
	public void write(String[] a) throws IOException;
	
	/**
	 * Escribe <i>n</i> Doubles.
	 * @param a - Array con los Doubles a escribir.
	 * @throws IOException 
	 */
	public void write(Double[] a) throws IOException;
	
	/**
	 * Escribe <i>n</i> Booleans.
	 * @param a - Array con los Booleans a escribir.
	 * @throws IOException 
	 */
	public void write(Boolean[] a) throws IOException;

	/**
	 * Escribe <i>n</i> Longs.
	 * @param a - Array con los Longs a escribir.
	 * @throws IOException 
	 */
	public void write(Long[] a) throws IOException;

	/**
	 * Escribe el contenido de un Set de Strings.
	 * @param set - Set con los Strings a escribir.
	 * @throws IOException 
	 */
	public <T extends Object> void write(Set<T> set) throws IOException;

}