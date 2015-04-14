package io;

public interface Sortida {
		
	public void close();

	public void Write(String s);
	
	public void Write(char c);
	
	public void Write(int n);
	
	public void Write(Integer n);
	
	public void Write(Double a);
	
	public void Write(Boolean b);

	public void Write(Long l);
	
	public void Write(int n, char[] a);
	
	public void Write(int n, int[] a);
	
	public void Write(int n, Integer[] a);
	
	public void Write(int n, Double[] a);
	
	public void Write(int n, Boolean[] a);

	public void Write(int n, Long[] a);
	
}