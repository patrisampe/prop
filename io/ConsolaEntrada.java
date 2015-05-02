package io;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import dominio.algoritmos.Graf;

public class ConsolaEntrada implements Entrada {
	private Scanner sc;

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
    	return sc.nextDouble();
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
			if (sc.hasNextDouble()) out[i] = sc.nextDouble();
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
		Set<String> out = new HashSet<String>();
		for (int i = 0; i < n; ++i){
			if (sc.hasNext()) out.add(sc.next());
		}
    	return out;
	}
	
	public Graf ReadGraf(int v, int a) {
		Graf out = new Graf();
		for (int i = 0; i < v; ++i) {
			if (sc.hasNext()) out.addNode(sc.next());
		}
		for (int i = 0; i < a; ++i) {
			String Node1 = null;
			String Node2 = null;
			Double Pes = null;
			if (sc.hasNext()) Node1 = sc.next();
			if (sc.hasNext()) Node2 = sc.next();
			if (sc.hasNextDouble()) Pes = sc.nextDouble();
			out.addAresta(Node1, Node2, Pes);
		}
		return out;
		
	}
}