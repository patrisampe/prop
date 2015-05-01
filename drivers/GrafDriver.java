package drivers;

import java.util.HashSet;

import dominio.algoritmos.Graf;

public class GrafDriver { //No està gens fet, però s'intenta
	Graf G;
	private void createGraf() {
		G = new Graf();
	}
	
	private void createGraf(HashSet<String> NodesInicials) {
		G = new Graf(NodesInicials);
	}
	

	private void createGraf(Graf G) {
		this.G = new Graf(G);
	}
	
	private void size() {
		System.out.println(G.size());
	}
	
	private void getNodes() {
		System.out.print(G.getNodes());
	}
	
	private void addNode(String id) {
		System.out.print(G.addNode(id));
	}
	
	private void removeNode(String id) {
		System.out.print(G.removeNode(id));
	}
	
	private void existeixNode(String id) {
		System.out.print(G.existeixNode(id));
	}
	
	private void addAresta(String a, String b, Double Pes) {
		System.out.print(G.addAresta(a, b, Pes));
	}
	
	private void removeAresta(String a, String b) {
		System.out.print(G.removeAresta(a, b));
	}
	
	private void existeixAresta(String a, String b) {
		System.out.print(G.existeixAresta(a, b));
	}
	
	private void setPes(String a, String b, Double Pes) {
		System.out.print(G.setPes(a,b,Pes));
	}
	
	private void getPes(String a, String b) {
		System.out.print(G.getPes(a,b));
	}
	
	private void getAdjacents(String id) {
		System.out.print(G.getAdjacents(id));
	}

}
