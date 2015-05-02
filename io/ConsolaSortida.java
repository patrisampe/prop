package io;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class ConsolaSortida implements Sortida {
	
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
	
	public void Write(Double a){
    	System.out.println(a.toString());
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
	
	public void Write(Set<String> set) {
		for (Iterator<String> it = set.iterator(); it.hasNext();){
			try {
				String toPrint = it.next();
				if(it.hasNext()) BW.write(toPrint + ", ");
				else	BW.write(toPrint + System.lineSeparator());
			} catch (IOException e){
				System.out.println("Error en l'escriptura.");
			}
		}
	}
	
}