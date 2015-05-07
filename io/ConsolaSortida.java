package io;

import java.util.Set;

/**
 * Salida por consola que permite escribir cualquier tipo de dato basico.
 * @author David Moran
 * @version 06/05/2015 15:00
 */
public class ConsolaSortida implements Sortida {
	
	/**
	 * Crea una nueva salida mediante la consola.
	 */
	public ConsolaSortida(){
	}
	
	public void close(){
	}
	
	public void Write(String s){
    	System.out.println(s);
	}
	
	public void Write(char c){
		System.out.printf("%c%n", c);
	}
	
	public void Write(int n){
		System.out.printf("%d%n", n);
	}
	
	public void Write(Integer n){
    	System.out.println(n.toString());
	}
	
	public void Write(Double d){
    	System.out.println(d.toString());
	}
	
	public void Write(Boolean b){
    	System.out.println(b.toString());
	}

	public void Write(Long l){
    	System.out.println(l.toString());
	}
	
	public void Write(int n, char[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.printf("%c, ", a[i]);
			else System.out.printf("%c%n", a[i]);
		}
	}
	
	public void Write(int n, int[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.printf("%d, ", a[i]);
			else System.out.printf("%d%n", a[i]);
		}
	}
	
	public void Write(int n, Character[] a) {
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void Write(int n, Integer[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void Write(int n, Double[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void Write(int n, Boolean[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public void Write(int n, String[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void Write(int n, Long[] a){
		for (int i = 0; i < n; ++i){
			if (i != n-1) System.out.print(a[i].toString() + ", ");
			else System.out.println(a[i].toString());
		}
	}
	
	public <T extends Object> void Write(Set<T> set) {
		Integer count = 0;
		for (T toPrint:set){
			if(count != set.size()-1) System.out.print(toPrint.toString() + ", ");
			else System.out.print(toPrint.toString() + System.lineSeparator());
			++count;
		}
	}
	
}