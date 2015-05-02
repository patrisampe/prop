package io;

import java.util.HashSet;
import java.util.Set;

import dominio.algoritmos.Graf;

public interface Entrada {

	public void close();
		
	public String ReadString();
	
	public String ReadLine();
	
	public char ReadChar();
	
	public int ReadInt();
	
	public Character ReadCharacter();
	
	public Integer ReadInteger();
	
	public Double ReadDouble();
	
	public Boolean ReadBoolean();
	
	public Long ReadLong();
	
	public char[] ReadChar(int n);
	
	public int[] ReadInt(int n);
	
	public Integer[] ReadInteger(int n);

	public Double[] ReadDouble(int n);
	public String[] ReadString(int n);
	public Boolean[] ReadBoolean(int n);

	public Long[] ReadLong(int n);

	public Set<String> ReadSetString(int n);
	
}
