package drivers;

import io.*;

import java.io.IOException;
import java.util.HashSet;

import dominio.algoritmos.Graf;

/**
 * 
 * @author Yoel Cabo
 *
 */
public class GrafDriver { //No est� gens fet, per� s'intenta
	private Graf G;
	
	
	public static Graf ReadGraf(Entrada eF, int v, int a) {
		Graf out = new Graf();
		for (int i = 0; i < v; ++i) {
			out.addNode(eF.readString());
		}
		for (int i = 0; i < a; ++i) {
			out.addAresta(eF.readString(), eF.readString(), eF.readDouble());
		}
		return out;
		
	}
	
	public GrafDriver() {

	}
	
	public static void main (String args[]) throws IOException {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.readString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.readString();
		Salida SF = new FicheroSalida(Output);
		GrafDriver GD = new GrafDriver();
		int a= EF.readInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 GD.createGraf();
			     break;
			 case 2: 
				 GD.createGraf((HashSet<String>) EF.readSetString(EF.readInt()));
			     break;
			 case 3: 
				 Graf G2 = ReadGraf(EF, EF.readInt(), EF.readInt());
				 GD.createGraf(G2);
			     break;
			 case 4: 
				 SF.write("Size del Graf:");
				 SF.write(GD.size());
			     break;
			 case 5:
				 SF.write("Nodes del Graf:");
				 SF.write(GD.getNodes());
				 break;
			 case 6: 
				 if(!GD.addNode(EF.readString())) SF.write("El node ja existeix");
			     break;
			 case 7:
				 if(!GD.removeNode(EF.readString())) SF.write("El node no existeix");
			     break;
			 case 8:
				 if(GD.existeixNode(EF.readString())) SF.write("El node existeix");
				 else SF.write("El node no existeix");
			     break;
			 case 9:
				 if(!GD.addAresta(EF.readString(), EF.readString(), EF.readDouble())) SF.write("No es pot afegir l'aresta.");
				 break;
			 case 10:
				 if(!GD.removeAresta(EF.readString(), EF.readString())) SF.write("No es pot eliminar l'aresta.");
				 break;
			 case 11:
				 if(GD.existeixAresta(EF.readString(), EF.readString())) SF.write("L'aresta existeix.");
				 else SF.write("No existeix cap aresta entre aquests nodes");
				 break;
			 case 12:
				 if(!GD.setPes(EF.readString(), EF.readString(), EF.readDouble())) SF.write("No es pot modificar l'aresta.");
				 break;
			 case 13:
				 SF.write("Pes de l'aresta:");
				 SF.write(GD.getPes(EF.readString(), EF.readString()));
				 break;
			 case 14:
				 SF.write("Nodes Adjacents:");
				 SF.write(GD.getAdjacents(EF.readString()));
				 break;
			 default: 
			    SF.write(" numero no correcto. Para cerrar -1 ");
			     break;
			 }
			a=EF.readInt();
		}
		EF.close();
		SF.close();
	}
	private void createGraf() {
		G = new Graf();
	}
	
	private void createGraf(HashSet<String> NodesInicials) {
		G = new Graf(NodesInicials);
	}
	

	private void createGraf(Graf G) {
		this.G = new Graf(G);
	}
	
	private Integer size() {
		return G.size();
	}
	
	private HashSet<String> getNodes() {
		return G.getNodes();
	}
	
	private Boolean addNode(String id) {
		return G.addNode(id);
	}
	
	private Boolean removeNode(String id) {
		return G.removeNode(id);
	}
	
	private Boolean existeixNode(String id) {
		return G.existeixNode(id);
	}
	
	private Boolean addAresta(String a, String b, Double Pes) {
		return G.addAresta(a, b, Pes);
	}
	
	private Boolean removeAresta(String a, String b) {
		return G.removeAresta(a, b);
	}
	
	private Boolean existeixAresta(String a, String b) {
		return G.existeixAresta(a, b);
	}
	
	private Boolean setPes(String a, String b, Double Pes) {
		return G.setPes(a,b,Pes);
	}
	
	private Double getPes(String a, String b) {
		return G.getPes(a,b);
	}
	
	private HashSet<String> getAdjacents(String id) {
		return G.getAdjacents(id);
	}

}
