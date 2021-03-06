package auxNonProject;

import java.io.IOException;
import java.util.HashSet;

import dominio.algoritmos.Graf;
import dominio.algoritmos.Louvain;
import io.*;
/**
 * Convierte el grafo de la wikipedia en uno para juegos de prueba standard.
 * @author Yoel
 *
 */
public class WikiToGraf {
	public static Graf ReadGraf(int n) throws IOException {
		Graf G = new Graf();
		Entrada EF = new FicheroEntrada("auxNonProject/cats.txt");
		for (int i = 0; i < n; ++i) {
			add(G,EF.readString(),EF.readString(),conseguirImportancia(EF.readString()),EF.readString(),EF.readString());
		}
		EF.close();
		return G;
	}

	private static Double conseguirImportancia(String tipo) {
		if (tipo.equals("CsupC")) return 100.0;
		if (tipo.equals("CsubC")) return 100.0;
		if (tipo.equals("CP")) return 200.0;
		if (tipo.equals("PC")) return 200.0;
		return null;
	}

	private static void add(Graf g, String Node1, String pepe,
			Double importancia, String Node2, String pepe2) {
			tryadd(g,Node1);
			tryadd(g,Node2);
			g.addAresta(Node1, Node2, importancia);
	}

	private static void tryadd(Graf g, String node1) {
		if (!g.existeixNode(node1)) g.addNode(node1);
		
	}
	public static void main (String args[]) throws IOException {
		Salida SF = new FicheroSalida("auxNonProject/wikipedia.txt");
		Salida SC = new ConsolaSalida();
		SC.write("Fitxers Oberts, procedim a la lectura del Graf.");
		Graf G = ReadGraf(95114); //95114
		SC.write("Lectura Completada, procedim a l'execucio.");
		HashSet<HashSet<String>> comunidades = Louvain.executa(G, 20); 
		SC.write("Execucio completada, procedim a imprimir les comunitats.");
		Integer i = 1;
		for(HashSet<String> comunidad : comunidades) {
			SF.write("Comunidad "+i.toString()+":");
			SF.write(comunidad);
			++i;
		}
		SF.close();
	}
}
