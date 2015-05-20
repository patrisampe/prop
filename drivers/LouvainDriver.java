package drivers;

import java.io.IOException;
import java.util.HashSet;

import dominio.algoritmos.Louvain;
import io.*;

public class LouvainDriver {

	public static void main (String args[]) throws IOException {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.readString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.readString();
		Salida SF = new FicheroSalida(Output);
		Salida SC = new ConsolaSalida();
		SC.write("Fitxers Oberts, procedim a l'execucio.");
		HashSet<HashSet<String>> comunidades = Louvain.executa(GrafLouvainDriver.ReadGraf(EF, EF.readInt(), EF.readInt()), EF.readInt()); 
		SC.write("Execucio completada, procedim a imprimir les comunitats.");
		Integer i = 1;
		for(HashSet<String> comunidad : comunidades) {
			SF.write("Comunidad "+i.toString()+":");
			SF.write(comunidad);
			++i;
		}
		EF.close();
		SF.close();
	}
}
