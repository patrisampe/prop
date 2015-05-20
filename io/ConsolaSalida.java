package io;

import java.io.IOException;
import java.util.Set;

/**
 * Salida por consola que permite escribir cualquier tipo de dato básico.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class ConsolaSalida implements Salida {
	
	/**
	 * Crea una nueva salida mediante la consola.
	 */
	private Boolean open;
	
	/**
	 * Crea una nueva salida mediante la consola.
	 */
	public ConsolaSalida(){
		open = true;
	}
	
	public void close(){
		open = false;
	}
	
	public void write() throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println();
	}
	
	public void write(String s) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println(s);
	}
	
	public void write(char c) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		System.out.printf("%c%n", c);
	}
	
	public void write(int n) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		System.out.printf("%d%n", n);
	}
	
	public void write(Integer n) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println(n.toString());
	}
	
	public void write(Double d) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println(d.toString());
	}
	
	public void write(Boolean b) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println(b.toString());
	}

	public void write(Long l) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
    	System.out.println(l.toString());
	}
	
	public void write(char[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.printf("%c, ", a[i]);
			else System.out.printf("%c%n", a[i]);
		}
	}
	
	public void write(int[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.printf("%d, ", a[i]);
			else System.out.printf("%d%n", a[i]);
		}
	}
	
	public void write(Character[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void write(Integer[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(Double[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(Boolean[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(String[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void write(Long[] a) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public <T extends Object> void write(Set<T> set) throws IOException {
		if (!open) throw new IOException("Error en la escritura.");
		Integer count = 0;
		for (T toPrint:set){
			if(count != set.size()-1) System.out.print(toPrint.toString() + ", ");
			else System.out.print(toPrint.toString() + System.lineSeparator());
			++count;
		}
	}
	
}