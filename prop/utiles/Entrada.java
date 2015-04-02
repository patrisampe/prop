package utiles;

import java.util.Scanner;

public class Entrada{
	private Scanner sc;

	public Entrada(){
		 sc = new Scanner(System.in);
	}
	
	public Entrada(String FileName){
		 sc = new Scanner(FileName);
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
	
	public Integer ReadInteger(){
    	return sc.nextInt();
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

	public Long[] ReadLong(int n){
		Long[] out = new Long[n];
		for (int i = 0; i < n; ++i){
			if (sc.hasNextInt()) out[i] = sc.nextLong();
		}
    	return out;
	}

}
