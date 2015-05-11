package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Salida por fichero que permite escribir cualquier tipo de dato basico.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class FitxerSortida implements Sortida {
	
	/**
	 * Buffer utilizado para la escritura por fichero.
	 */
	private BufferedWriter BW;

	/**
	 * Crea una nueva salida mediante fichero.
	 * @param FileName - ruta del fichero de salida.
	 */
	public FitxerSortida(String FileName){
		File F = new File(FileName);
		try {
			BW = new BufferedWriter(new FileWriter(F));
		} catch (IOException e) {
			System.out.println("Error en crear el fitxer.");
		}
	}
	
	public void close() {
	    if (BW != null) {
	        try {
				BW.close();
			} catch (IOException e) {
				System.out.println("Error en tancar el fitxer.");
			}
	    }
	}
	
	protected void finalize (){
	    if (BW != null) {
	        try {
				BW.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void Write(String s){
    	try {
			BW.write(String.format("%s%n", s));
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(char c){
    	try {
			BW.write(String.format("%c%n", c));
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(int n){
    	try {
			BW.write(String.format("%d%n", n));
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(Integer n){
    	try {
			BW.write(String.format("%d%n", n));
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(Double d){
    	try {
			BW.write(d.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(Boolean b){
    	try {
			BW.write(b.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}

	public void Write(Long l){
    	try {
			BW.write(l.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en l'escriptura.");
		}
	}
	
	public void Write(int n, char[] a){
		for (int i = 0; i < n; ++i){
	    	try {
				if (i != n-1) BW.write(String.format("%c, ", a[i]));
				else BW.write(String.format("%c%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, int[] a){
		for (int i = 0; i < n; ++i){
	    	try {
				if (i != n-1) BW.write(String.format("%d, ", a[i]));
				else BW.write(String.format("%d%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Character[] a) {
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i] + ", ");
				else BW.write(a[i] + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Integer[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Double[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Boolean[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}

	public void Write(int n, String[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i] + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Long[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
	public <T extends Object> void Write(Set<T> set) {
		Integer count = 0;
		for (T toPrint:set){
			try {
				if(count != set.size()-1) BW.write(toPrint.toString() + ", ");
				else BW.write(toPrint.toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
			++count;
		}
	}
	
}