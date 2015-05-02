package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


/**
 * Entrada por fichero que permite leer cualquier tipo de dato basico.
 * @author David Moran
 * @version 03/05/2015 01:02
 */
public class FitxerEntrada implements Entrada {
	
	/**
	 * Scanner utilizado para la lectura por fichero.
	 */
	private Scanner sc;

	/**
	 * Crea una nueva entrada mediante fichero.
	 * @param FileName - ruta del fichero de entrada.
	 */
	public FitxerEntrada(String FileName){
		File F = new File(FileName);
		try {
			sc = new Scanner(F);
		} catch (FileNotFoundException e) {
			System.out.println("Fitxer inexistent.");
		}
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
	
	public String ReadString(){
    	return sc.next();
	}
	
	public String ReadLine(){
    	return sc.nextLine();
	}
	
	public char ReadChar(){
    	return (char) sc.nextByte();
	}
	
	public int ReadInt(){
    	return sc.nextInt();
	}
	
	public Character ReadCharacter(){
    	return Character.valueOf((char) sc.nextByte());
	}
	
	public Integer ReadInteger(){
    	return Integer.valueOf(sc.nextInt());
	}
	
	public Double ReadDouble(){
		try {
			return sc.nextDouble();
		} catch (InputMismatchException e) {
			String S = sc.next();
			S.replace('.', ',');
			return Double.parseDouble(S);
		}
	}
	
	public Boolean ReadBoolean(){
    	return sc.nextBoolean();
	}
	
	public Long ReadLong(){
    	return sc.nextLong();
	}
	
	public char[] ReadChar(int n){
		char[] out = new char[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextByte()) out[i] = (char) sc.nextByte();
		}
    	return out;
	}
	
	public int[] ReadInt(int n){
		int[] out = new int[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextInt();
		}
    	return out;
	}
	
	public Integer[] ReadInteger(int n){
		Integer[] out = new Integer[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextInt();
		}
    	return out;
	}

	public Double[] ReadDouble(int n){
		Double[] out = new Double[n];
		for (int i = 0; i < n; ++i){
			try {
				if (sc.hasNextDouble()) out[i] = sc.nextDouble();
			} catch (InputMismatchException e) {
				String S = sc.next();
				S.replace('.', ',');
				out[i] = Double.parseDouble(S);
			}
		}
    	return out;
	}
	
	public Boolean[] ReadBoolean(int n){
		Boolean[] out = new Boolean[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextBoolean()) out[i] = sc.nextBoolean();
		}
    	return out;
	}
	
	public String[] ReadString(int n){
		String[] out = new String[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out[i] = sc.next();
		}
    	return out;
	}
	
	public Long[] ReadLong(int n){
		Long[] out = new Long[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextLong();
		}
    	return out;
	}
	
	public Set<String> ReadSetString(int n){
		Set<String> out = new TreeSet<String>();
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out.add(sc.next());
		}
    	return out;
	}
	
}