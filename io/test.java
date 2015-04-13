package io;

public class test {
	
	public static void main(String[] args){
		Entrada EC = new Entrada();
		String Input = EC.ReadString();
		Entrada EF = new Entrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new Fitxer(Output);
		Sortida SC = new Consola();
		String aux = EF.ReadLine();
		SF.Write(aux);
		SC.Write(aux);
	}
	
}