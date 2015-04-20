package dominio.algoritmos;

import java.util.HashSet;
import java.util.Iterator;

public class GrafLouvain extends Graf {
	
	private Double sumaFila(Integer i) {
		Double sum = 0.0;
		for (Integer j = 0; j < Matriu.get(0).size(); ++j) {
			sum += Matriu.get(i).get(j);
		}
		return sum;
	}
	
	private Double sumaColumna(Integer j) {
		Double sum = 0.0;
		for (Integer i = 0; i < Matriu.get(0).size(); ++i) {
			sum += Matriu.get(i).get(j);
		}
		return sum;
	}
	
	public GrafLouvain() {
		super();
	}
	
	public GrafLouvain(GrafLouvain G) {
		super(G);
	}
	
	public Double sumaPesos() {
		Double sum = 0.0;
		for (Integer i = 0; i < Matriu.size(); ++i) {
			sum += sumaFila(i);
		}
		if (Matriu.size() == 0) return -1.0;
		return sum/2;
	}
	
	public Double sumaPesos(HashSet<String> Comunitat) {
		if (Comunitat.isEmpty()) return -1.0;
		Double sum = 0.0;
		Iterator<String> it = Comunitat.iterator();
		while(it.hasNext()) {
			Double sumi = sumaPesosAdjacents(it.next(), Comunitat);
			if (sumi > 0) sum += sumi;
		}
		return sum;
	}
	
	
	
	public Double sumaPesosAdjacents(String id) {
		if (!existeixNode(id)) return -1.0;
		Integer Posicio = Diccionari.get(id);
		return sumaFila(Posicio);
	}
	
	public Double sumaPesosAdjacents(String id,HashSet<String> Comunitat) {//Incompletee
		if (!existeixNode(id)) return -1.0;
		if (Comunitat.isEmpty()) return -1.0;
		Double sum = 0.0;
		Integer Posicio = Diccionari.get(id);
		for (Integer i = 0; i < Matriu.size(); ++i) {
			if (Comunitat.contains(DiccionariInvers.get(i))) {
				sum += Matriu.get(Posicio).get(i);
			}
		}
		return sum;
	}
	
	public Double sumaPesosAdjacents(HashSet<String> C1, HashSet<String> C2) {
		if (C1.isEmpty() || C2.isEmpty()) return -1.0;
		if (C1 == C2) return sumaPesos(C1);
		Double sum = 0.0;
		Iterator<String> it = C1.iterator();
		while(it.hasNext()) {
			Double sumi = sumaPesosAdjacents(it.next(), C2);
			if (sumi > 0) sum += sumi;
		}
		return sum;
	}
	
	public Double sumaPesosAdjacentsInclusiva(HashSet<String> Comunitat) {
		if (Comunitat.isEmpty()) return -1.0;
		Double sum = 0.0;
		Iterator<String> it = Comunitat.iterator();
		while(it.hasNext()) {
			Double sumi = sumaPesosAdjacents(it.next());
			if (sumi > 0) sum += sumi;
		}
		return sum;
	}
	
	public Double sumaPesosAdjacentsExclusiva(HashSet<String> Comunitat) { 
		if (Comunitat.isEmpty()) return -1.0;
		return sumaPesosAdjacentsInclusiva(Comunitat) + sumaPesos(Comunitat);
	}
	
	
	
	
	
}