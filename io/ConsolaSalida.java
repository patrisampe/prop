package io;

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
	public ConsolaSalida(){
	}
	
	public void close(){
	}
	
	public void write(){
    	System.out.println();
	}
	
	public void write(String s){
    	System.out.println(s);
	}
	
	public void write(char c){
		System.out.printf("%c%n", c);
	}
	
	public void write(int n){
		System.out.printf("%d%n", n);
	}
	
	public void write(Integer n){
    	System.out.println(n.toString());
	}
	
	public void write(Double d){
    	System.out.println(d.toString());
	}
	
	public void write(Boolean b){
    	System.out.println(b.toString());
	}

	public void write(Long l){
    	System.out.println(l.toString());
	}
	
	public void write(char[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.printf("%c, ", a[i]);
			else System.out.printf("%c%n", a[i]);
		}
	}
	
	public void write(int[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.printf("%d, ", a[i]);
			else System.out.printf("%d%n", a[i]);
		}
	}
	
	public void write(Character[] a) {
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void write(Integer[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(Double[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(Boolean[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void write(String[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void write(Long[] a){
		for (int i = 0; i < a.length; ++i){
			if (i != a.length-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public <T extends Object> void write(Set<T> set) {
		Integer count = 0;
		for (T toPrint:set){
			if(count != set.size()-1) System.out.print(toPrint.toString() + ", ");
			else System.out.print(toPrint.toString() + System.lineSeparator());
			++count;
		}
	}
	
}