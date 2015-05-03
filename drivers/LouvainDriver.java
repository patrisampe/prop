package drivers;

import java.util.HashSet;

import dominio.algoritmos.Louvain;
import io.*;

public class LouvainDriver {

	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		Sortida SC = new ConsolaSortida();
		SC.Write("Fitxers Oberts, procedim a l'execucio.");
		HashSet<HashSet<String>> comunidades = Louvain.executa(SC, GrafLouvainDriver.ReadGraf(EF, EF.ReadInt(), EF.ReadInt()), EF.ReadInt()); 
		SC.Write("Execucio completada, procedim a imprimir les comunitats.");
		Integer i = 1;
		for(HashSet<String> comunidad : comunidades) {
			SF.Write("Comunidad "+i.toString()+":");
			SF.Write(comunidad);
			++i;
		}
		EF.close();
		SF.close();
	}
}
