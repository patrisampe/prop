package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Salida por fichero que permite escribir cualquier tipo de dato basico.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class FicheroSalida implements Salida {
	
	/**
	 * Buffer utilizado para la escritura por fichero.
	 */
	private BufferedWriter BW;

	/**
	 * Crea una nueva salida mediante fichero.
	 * @param FileName - ruta relativa del fichero de salida.
	 */
	public FicheroSalida(String fileName) throws IOException {
		File F = new File(fileName);
		BW = new BufferedWriter(new FileWriter(F));

	}
	
	public void close() {
	    if (BW != null) {
	        try {
				BW.close();
			} catch (IOException e) {
				System.out.println("Error en cerrar el fichero.");
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
	
	public void write(){
    	try {
			BW.write(System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(String s){
    	try {
			BW.write(String.format("%s%n", s));
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(char c){
    	try {
			BW.write(String.format("%c%n", c));
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(int n){
    	try {
			BW.write(String.format("%d%n", n));
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(Integer n){
    	try {
			BW.write(String.format("%d%n", n));
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(Double d){
    	try {
			BW.write(d.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(Boolean b){
    	try {
			BW.write(b.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}

	public void write(Long l){
    	try {
			BW.write(l.toString() + System.lineSeparator());
		} catch (IOException e) {
			System.out.println("Error en la escritura.");
		}
	}
	
	public void write(char[] a){
		for (int i = 0; i < a.length; ++i){
	    	try {
				if (i != a.length-1) BW.write(String.format("%c, ", a[i]));
				else BW.write(String.format("%c%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(int[] a){
		for (int i = 0; i < a.length; ++i){
	    	try {
				if (i != a.length-1) BW.write(String.format("%d, ", a[i]));
				else BW.write(String.format("%d%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(Character[] a) {
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i] + ", ");
				else BW.write(a[i] + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(Integer[] a){
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(Double[] a){
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(Boolean[] a){
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}

	public void write(String[] a){
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i] + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public void write(Long[] a){
		for (int i = 0; i < a.length; ++i){
			try {
				if (i != a.length-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
		}
	}
	
	public <T extends Object> void write(Set<T> set) {
		Integer count = 0;
		for (T toPrint:set){
			try {
				if(count != set.size()-1) BW.write(toPrint.toString() + ", ");
				else BW.write(toPrint.toString() + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en la escritura.");
			}
			++count;
		}
	}
	
}