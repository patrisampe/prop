package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Entrada por fichero que permite leer cualquier tipo de dato básico.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class FicheroEntrada implements Entrada {
	
	/**
	 * Scanner utilizado para la lectura por fichero.
	 */
	private Scanner sc;

	/**
	 * Crea una nueva entrada mediante fichero.
	 * @param FileName - ruta relativa del fichero de entrada.
	 */
	public FicheroEntrada(String fileName) throws FileNotFoundException {
		File F = new File(fileName);
		sc = new Scanner(F);

	}
	
	public void close() {
	    if (sc != null) {
	        sc.close();
	    }
	}
	
	protected void finalize () {
	    if (sc != null) {
	        sc.close();
	    }
	}
	
	public String readString() throws IOException{
		try {
			return sc.next();
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		}
	}
	
	public String readLine() throws IOException{
		try {
			return sc.nextLine();
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		}
	}
	
	public char readChar() throws IOException{
		try {
	    	return (char) sc.nextByte();
		} catch (InputMismatchException  e) {
			throw new IOException("Formato de entrada incorrecto.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public int readInt() throws IOException{
		try {
	    	return sc.nextInt();
		} catch (InputMismatchException  e) {
			throw new IOException("Formato de entrada incorrecto.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public Character readCharacter() throws IOException{
		try {
	    	return Character.valueOf((char) sc.nextByte());
		} catch (InputMismatchException  e) {
			throw new IOException("Formato de entrada incorrecto.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public Integer readInteger() throws IOException{
		try {
	    	return Integer.valueOf(sc.nextInt());
		} catch (InputMismatchException  e) {
			throw new IOException("Formato de entrada incorrecto.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public Double readDouble() throws IOException{
		try {
			return sc.nextDouble();
		} catch (InputMismatchException e) {
			String S = sc.next();
			S.replace('.', ',');
			try {
				return Double.parseDouble(S);
			} catch (NumberFormatException e2) {
				throw new IOException("Formato de entrada incorrecto.");
			}
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public Boolean readBoolean() throws IOException{
		try {
	    	return sc.nextBoolean();
		} catch (InputMismatchException  e) {
			throw new IOException("Formato de entrada incorrecto.");
		} catch (IllegalStateException e) {
			throw new IOException("Entrada cerrada.");
		} catch (NoSuchElementException e) {
			throw new IOException("Fin de la entrada.");
		}
	}
	
	public Long readLong(){
    	return sc.nextLong();
	}
	
	public String[] readString(int n) throws IOException{
		String[] out = new String[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.next();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public char[] readChar(int n) throws IOException{
		char[] out = new char[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = (char) sc.nextByte();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public int[] readInt(int n) throws IOException{
		int[] out = new int[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextInt();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Character[] readCharacter(int n) throws IOException {
		Character[] out = new Character[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = Character.valueOf((char) sc.nextByte());
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Integer[] readInteger(int n) throws IOException{
		Integer[] out = new Integer[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextInt();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}

	public Double[] readDouble(int n) throws IOException{
		Double[] out = new Double[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextDouble();
			} catch (InputMismatchException e) {
				String S = sc.next();
				S.replace('.', ',');
				try {
					out[i] = Double.parseDouble(S);
				} catch (NumberFormatException e2) {
					throw new IOException("Formato de entrada incorrecto.");
				}
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Boolean[] readBoolean(int n) throws IOException{
		Boolean[] out = new Boolean[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextBoolean();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Long[] readLong(int n) throws IOException{
		Long[] out = new Long[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextLong();
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Set<String> readSetString(int n) throws IOException{
		Set<String> out = new TreeSet<String>();
		for (int i = 0; i < n; ++i){
			try {
				out.add(sc.next());
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
	public Set<Integer> readSetInteger(int n) throws IOException{
		Set<Integer> out = new TreeSet<Integer>();
		for (int i = 0; i < n; ++i){
			try {
				out.add(Integer.valueOf(sc.nextInt()));
			} catch (InputMismatchException  e) {
				throw new IOException("Formato de entrada incorrecto.");
			} catch (IllegalStateException e) {
				throw new IOException("Entrada cerrada.");
			} catch (NoSuchElementException e) {
				throw new IOException("Fin de la entrada.");
			}
		}
    	return out;
	}
	
}