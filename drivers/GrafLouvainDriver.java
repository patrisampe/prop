package drivers;

import io.*;

import java.io.IOException;
import java.util.HashSet;

import dominio.algoritmos.*;

/**
 * 
 * @author Yoel Cabo
 *
 */
public class GrafLouvainDriver { 
	private GrafLouvain G;
	
	
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
	
	public GrafLouvainDriver() {

	}
	
	public static void main (String args[]) throws IOException {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.readString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.readString();
		Salida SF = new FicheroSalida(Output);
		GrafLouvainDriver GD = new GrafLouvainDriver();
		int a= EF.readInt();
		SF.write(a);
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 GD.createGraf();
			     break;
			 case 2: 
				 GD.createGraf((HashSet<String>) EF.readSetString(EF.readInt()));
			     break;
			 case 3: 
				 Graf G2 = new Graf(ReadGraf(EF, EF.readInt(), EF.readInt()));
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
			 case 15:
				 SF.write("Suma total de Pesos del Graf:");
				 SF.write(GD.sumaPesos());
				 break;
			 case 16:
				 SF.write("Suma total de Pesos del Graf interns de la comunitat:");
				 SF.write(GD.sumaPesos((HashSet<String>) EF.readSetString(EF.readInt())));
				 break;
			 case 17:
				 SF.write("Suma total de les arestes Adjacents al node:");
				 SF.write(GD.sumaPesosAdjacents(EF.readString()));
				 break;
			 case 18:
				 SF.write("Suma total de les arestes entre el node i la comunitat:");
				 SF.write(GD.sumaPesosAdjacents(EF.readString(), (HashSet<String>) EF.readSetString(EF.readInt())));
				 break;
			 case 19:
				 SF.write("Suma total de les arestes entre les dues comunitat:");
				 SF.write(GD.sumaPesosAdjacents( (HashSet<String>) EF.readSetString(EF.readInt()), (HashSet<String>) EF.readSetString(EF.readInt())));
				 break;
			 case 20:
				 SF.write("Suma total de les arestes adjacents a nodes de la comunitat (les internes apareixen duplicades):");
				 SF.write(GD.sumaPesosAdjacentsInclusiva((HashSet<String>) EF.readSetString(EF.readInt())));
				 break;
			 case 21:
				 SF.write("Suma total de les arestes adjacents a nodes de la comunitat, sense incloure les internes:");
				 SF.write(GD.sumaPesosAdjacentsExclusiva((HashSet<String>) EF.readSetString(EF.readInt())));
				 break;
			 default: 
			    SF.write(" Comanda incorrecta. Per a tancar -1 ");
			     break;
			 }
			a=EF.readInt();
		}
		SF.write(a);
		EF.close();
		SF.close();
	}
	private void createGraf() {
		G = new GrafLouvain();
	}
	
	private void createGraf(HashSet<String> NodesInicials) {
		G = new GrafLouvain(NodesInicials);
	}
	

	private void createGraf(Graf G) {
		this.G = new GrafLouvain(G);
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
	
	public Double sumaPesos() {
		return G.sumaPesos();
	}
	
	public Double sumaPesos(HashSet<String> Comunitat) {
		return G.sumaPesos(Comunitat);
	}
	
	public Double sumaPesosAdjacents(String id) {
		return G.sumaPesosAdjacents(id);
	}
	
	public Double sumaPesosAdjacents(String id,HashSet<String> Comunitat) {
		return G.sumaPesosAdjacents(id, Comunitat);
	}
	
	public Double sumaPesosAdjacents(HashSet<String> C1, HashSet<String> C2) {
		return G.sumaPesosAdjacents(C1, C2);
	}
	
	public Double sumaPesosAdjacentsInclusiva(HashSet<String> Comunitat) {
		return G.sumaPesosAdjacentsInclusiva(Comunitat);
	}

	public Double sumaPesosAdjacentsExclusiva(HashSet<String> Comunitat) { 
		return G.sumaPesosAdjacentsExclusiva(Comunitat);
	}

}