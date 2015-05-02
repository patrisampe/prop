package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import java.util.HashSet;

import dominio.Evento;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.algoritmos.Graf;

public class GrafDriver { //No està gens fet, però s'intenta
	private Graf G;
	
	public GrafDriver() {

	}
	
	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		GrafDriver GD = new GrafDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		int a= EF.ReadInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 GD.createGraf();
			     break;
			 case 2: 
				 GD.createGraf((HashSet<String>) EF.ReadSetString(EF.ReadInt()));
			     break;
			 case 3: 
				 Graf G2 = EF.ReadGraf(EF.ReadInt(), EF.ReadInt());
				 GD.createGraf(G2);
			     break;
			 case 4: 
				 SF.Write("Size del Grafo:");
				 SF.Write(GD.size());
			     break;
			 case 5:
				 SF.Write("Nodos del Grafo:");
				 SF.Write(GD.getNodes());
				 break;
			 case 6: 
				 if(!GD.addNode(EF.ReadString())) SF.Write("El nodo ya existe");
			     break;
			 case 8:
				 if(!GD.removeNode(EF.ReadString())) SF.Write("El nodo no existe");
			     break;
			 case 9:
				 if(GD.existeixNode(EF.ReadString())) SF.Write("El nodo existe");
				 else SF.Write("El nodo no existe");
			     break;
			 case 10:
				 if(!GD.addAresta(EF.ReadString(), EF.ReadString(), EF.ReadDouble())) SF.Write("Ja existeix una aresta entre aquests nodes");
				 break;
			 case 11:
				 if(!GD.removeAresta(EF.ReadString(), EF.ReadString())) SF.Write("No existeix cap aresta entre aquests nodes");
				 break;
			 case 12:
				 if(GD.existeixAresta(EF.ReadString(), EF.ReadString())) SF.Write("L'aresta existeix.");
				 else SF.Write("No existeix cap aresta entre aquests nodes");
				 break;
			 case 13:
				 if(!GD.setPes(EF.ReadString(), EF.ReadString(), EF.ReadDouble())) SF.Write("No existeix cap aresta entre aquests nodes");
				 break;
			 case 14:
				 SF.Write("Peso de la aresta:");
				 SF.Write(GD.getPes(EF.ReadString(), EF.ReadString()));
				 break;
			 case 15:
				 SF.Write("Peso de la aresta:");
				 SF.Write(GD.getAdjacents(EF.ReadString()));
				 break;
			 default: 
			    SF.Write(" numero no correcto. Para cerrar -1 ");
			     break;
			 }
			a=EF.ReadInt();
		}
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
