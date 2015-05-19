package io;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Entrada por consola que permite leer cualquier tipo de dato básico.
 * @author David Morán
 * @version 28/5/2015 22:00
 */
public class ConsolaEntrada implements Entrada {
	
	/**
	 * Scanner utilizado para la lectura por consola.
	 */
	private Scanner sc;

	/**
	 * Crea una nueva entrada mediante la consola.
	 */
	public ConsolaEntrada(){
		 sc = new Scanner(System.in);
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
	
	public String readString(){
    	return (sc.hasNext() ? sc.next() : "");
	}
	
	public String readLine(){
    	return (sc.hasNextLine() ? sc.nextLine() : "");
	}
	
	public char readChar(){
    	return (sc.hasNextByte() ? (char) sc.nextByte() : ' ');
	}
	
	public int readInt(){
    	return (sc.hasNextInt() ? sc.nextInt() : -1);
	}
	
	public Character readCharacter(){
    	return (sc.hasNextByte() ? Character.valueOf((char) sc.nextByte()) : ' ');
	}
	
	public Integer readInteger(){
    	return (sc.hasNextInt() ? Integer.valueOf(sc.nextInt()) : -1);
	}
	
	public Double readDouble(){
		try {
			return sc.nextDouble();
		} catch (InputMismatchException e) {
			String S = sc.next();
			S.replace('.', ',');
			return Double.parseDouble(S);
		}
	}
	
	public Boolean readBoolean(){
    	return (sc.hasNextBoolean() ? sc.nextBoolean() : false);
	}
	
	public Long readLong(){
    	return (sc.hasNextLong() ? sc.nextLong() : -1);
	}
	
	public char[] readChar(int n){
		char[] out = new char[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextByte()) out[i] = (char) sc.nextByte();
		}
    	return out;
	}
	
	public int[] readInt(int n){
		int[] out = new int[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextInt();
		}
    	return out;
	}
	
	public Character[] readCharacter(int n) {
		Character[] out = new Character[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextByte()) out[i] = Character.valueOf((char) sc.nextByte());
			else return new Character[0];
		}
    	return out;
	}
	
	public Integer[] readInteger(int n){
		Integer[] out = new Integer[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextInt();
			else return new Integer[0];
		}
    	return out;
	}

	public Double[] readDouble(int n){
		Double[] out = new Double[n];
		for (int i = 0; i < n; ++i){
			try {
				out[i] = sc.nextDouble();
			} catch (InputMismatchException e) {
				String S = sc.next();
				S.replace('.', ',');
				out[i] = Double.parseDouble(S);
			}
		}
    	return out;
	}
	
	public Boolean[] readBoolean(int n){
		Boolean[] out = new Boolean[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextBoolean()) out[i] = sc.nextBoolean();
			else return new Boolean[0];
		}
    	return out;
	}

	public String[] readString(int n){
		String[] out = new String[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out[i] = sc.next();
			else return new String[0];
		}
    	return out;
	}
	
	public Long[] readLong(int n){
		Long[] out = new Long[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextLong();
			else return new Long[0];
		}
    	return out;
	}
	
	public Set<String> readSetString(int n){
		Set<String> out = new TreeSet<String>();
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out.add(sc.next());
			else return new HashSet<String>();
		}
    	return out;
	}
	
	public Set<Integer> readSetInteger(int n){
		Set<Integer> out = new TreeSet<Integer>();
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out.add(Integer.valueOf(sc.nextInt()));
			else return new HashSet<Integer>();
		}
    	return out;
	}
	
}