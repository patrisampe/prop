package drivers;

import io.*;

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
			out.addNode(eF.ReadString());
		}
		for (int i = 0; i < a; ++i) {
			out.addAresta(eF.ReadString(), eF.ReadString(), eF.ReadDouble());
		}
		return out;
		
	}
	
	public GrafLouvainDriver() {

	}
	
	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		GrafLouvainDriver GD = new GrafLouvainDriver();
		int a= EF.ReadInt();
		SF.Write(a);
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 GD.createGraf();
			     break;
			 case 2: 
				 GD.createGraf((HashSet<String>) EF.ReadSetString(EF.ReadInt()));
			     break;
			 case 3: 
				 Graf G2 = new Graf(ReadGraf(EF, EF.ReadInt(), EF.ReadInt()));
				 GD.createGraf(G2);
			     break;
			 case 4: 
				 SF.Write("Size del Graf:");
				 SF.Write(GD.size());
			     break;
			 case 5:
				 SF.Write("Nodes del Graf:");
				 SF.Write(GD.getNodes());
				 break;
			 case 6: 
				 if(!GD.addNode(EF.ReadString())) SF.Write("El node ja existeix");
			     break;
			 case 7:
				 if(!GD.removeNode(EF.ReadString())) SF.Write("El node no existeix");
			     break;
			 case 8:
				 if(GD.existeixNode(EF.ReadString())) SF.Write("El node existeix");
				 else SF.Write("El node no existeix");
			     break;
			 case 9:
				 if(!GD.addAresta(EF.ReadString(), EF.ReadString(), EF.ReadDouble())) SF.Write("No es pot afegir l'aresta.");
				 break;
			 case 10:
				 if(!GD.removeAresta(EF.ReadString(), EF.ReadString())) SF.Write("No es pot eliminar l'aresta.");
				 break;
			 case 11:
				 if(GD.existeixAresta(EF.ReadString(), EF.ReadString())) SF.Write("L'aresta existeix.");
				 else SF.Write("No existeix cap aresta entre aquests nodes");
				 break;
			 case 12:
				 if(!GD.setPes(EF.ReadString(), EF.ReadString(), EF.ReadDouble())) SF.Write("No es pot modificar l'aresta.");
				 break;
			 case 13:
				 SF.Write("Pes de l'aresta:");
				 SF.Write(GD.getPes(EF.ReadString(), EF.ReadString()));
				 break;
			 case 14:
				 SF.Write("Nodes Adjacents:");
				 SF.Write(GD.getAdjacents(EF.ReadString()));
				 break;
			 case 15:
				 SF.Write("Suma total de Pesos del Graf:");
				 SF.Write(GD.sumaPesos());
				 break;
			 case 16:
				 SF.Write("Suma total de Pesos del Graf interns de la comunitat:");
				 SF.Write(GD.sumaPesos((HashSet<String>) EF.ReadSetString(EF.ReadInt())));
				 break;
			 case 17:
				 SF.Write("Suma total de les arestes Adjacents al node:");
				 SF.Write(GD.sumaPesosAdjacents(EF.ReadString()));
				 break;
			 case 18:
				 SF.Write("Suma total de les arestes entre el node i la comunitat:");
				 SF.Write(GD.sumaPesosAdjacents(EF.ReadString(), (HashSet<String>) EF.ReadSetString(EF.ReadInt())));
				 break;
			 case 19:
				 SF.Write("Suma total de les arestes entre les dues comunitat:");
				 SF.Write(GD.sumaPesosAdjacents( (HashSet<String>) EF.ReadSetString(EF.ReadInt()), (HashSet<String>) EF.ReadSetString(EF.ReadInt())));
				 break;
			 case 20:
				 SF.Write("Suma total de les arestes adjacents a nodes de la comunitat (les internes apareixen duplicades):");
				 SF.Write(GD.sumaPesosAdjacentsInclusiva((HashSet<String>) EF.ReadSetString(EF.ReadInt())));
				 break;
			 case 21:
				 SF.Write("Suma total de les arestes adjacents a nodes de la comunitat, sense incloure les internes:");
				 SF.Write(GD.sumaPesosAdjacentsExclusiva((HashSet<String>) EF.ReadSetString(EF.ReadInt())));
				 break;
			 default: 
			    SF.Write(" Comanda incorrecta. Per a tancar -1 ");
			     break;
			 }
			a=EF.ReadInt();
		}
		SF.Write(a);
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