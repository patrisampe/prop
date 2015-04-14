package io;

public class test {
	
	public static void main(String[] args){
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		Sortida SC = new ConsolaSortida();
		String aux = EF.ReadLine();
		SF.Write(aux);
		SC.Write(aux);
	}
	
}