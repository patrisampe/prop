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
	
	private Boolean removeAresta(String a, String b) {
		if (!existeixAresta(a,b)) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),0.0);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),0.0);
		return true;
	}
	
	/**
	 * Consulta si existeix una aresta entre dos Nodes del Graf.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @return true si existeix, false si no existeix.
	 */
	private Boolean existeixAresta(String a, String b) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b)) return false;
		if (Matriu.get(Diccionari.get(a)).get(Diccionari.get(b)) <= 0.0) return false;
		return true;
	}
	
	/**
	 * Donada una aresta existent modifica el seu pes.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @param Pes Pes de l'aresta entre a i b. Ha de ser >= 0.
	 * @return false si l'aresta no existia o si el Pes és incorrecte, true altrament.
	 */
	public Boolean setPes(String a, String b, Double Pes) {
		if (!existeixAresta(a,b) || Pes < 0) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),Pes);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),Pes);
		return true;
	}
	
	/**
	 * Donada una aresta existent, retorna el seu pes.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @return Pes de l'aresta si existeix, -1 si no existeix.
	 */
	public Double getPes(String a, String b) {
		if (!existeixAresta(a,b)) return -1.0;
		return Matriu.get(Diccionari.get(a)).get(Diccionari.get(b));
	}
	
	/**
	 * Donat un Node, retorna els Nodes adjacents a aquest.
	 * @param id Node
	 * @return Conjunt de nodes adjacent a id. Si id no existeix, el conjunt és buit.
	 */
	public HashSet<String> getAdjacents(String id) {
		HashSet<String> Cjt = new HashSet<String>();
		if(!existeixNode(id)) return Cjt;
		Integer Posicio = Diccionari.get(id);
		Integer N = Matriu.size();
		for(Integer j = 0; j < N; ++j) {
			if (Matriu.get(Posicio).get(j) > 0.0) Cjt.add(DiccionariInvers.get(j));
		}
		return Cjt;
	}

}
