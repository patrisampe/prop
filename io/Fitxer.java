package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fitxer implements Sortida {
	private BufferedWriter BW;

	public Fitxer(String FileName){
		File F = new File(FileName);
		try {
			BW = new BufferedWriter(new FileWriter(F));
		} catch (IOException e) {
			System.out.println("Error en crear el fitxer.");
		}
	}
	
	protected void finalize () throws Throwable{
	    if (BW != null) {
	        BW.close();
	    }
	}
	
	public void Write(String s){
    	try {
			BW.write(s);
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
    	try {
			BW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Write(char c){
    	try {
			BW.write(String.format("%c%n", c));
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}
	
	public void Write(int n){
    	try {
			BW.write(String.format("%d%n", n));
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}
	
	public void Write(Integer n){
    	try {
			BW.write(n.toString());
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}
	
	public void Write(Double a){
    	try {
			BW.write(a.toString());
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}
	
	public void Write(Boolean b){
    	try {
			BW.write(b.toString());
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}

	public void Write(Long l){
    	try {
			BW.write(l.toString());
		} catch (IOException e) {
			System.out.println("Error l'escriptura.");
		}
	}
	
	public void Write(int n, char[] a){
		for (int i = 0; i < n; ++i){
	    	try {
				if (i != n-1) BW.write(String.format("%c, ", a[i]));
				else BW.write(String.format("%c%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error l'escriptura.");
			}
		}
	}
	
	public void Write(int n, int[] a){
		for (int i = 0; i < n; ++i){
	    	try {
				if (i != n-1) BW.write(String.format("%d, ", a[i]));
				else BW.write(String.format("%d%n", a[i]));
			} catch (IOException e) {
				System.out.println("Error l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Integer[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString());
			} catch (IOException e){
				System.out.println("Error l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Double[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString());
			} catch (IOException e){
				System.out.println("Error l'escriptura.");
			}
		}
	}
	
	public void Write(int n, Boolean[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString());
			} catch (IOException e){
				System.out.println("Error l'escriptura.");
			}
		}
	}

	public void Write(int n, Long[] a){
		for (int i = 0; i < n; ++i){
			try {
				if (i != n-1) BW.write(a[i].toString() + ", ");
				else BW.write(a[i].toString());
			} catch (IOException e){
				System.out.println("Error l'escriptura.");
			}
		}
	}
	
}

