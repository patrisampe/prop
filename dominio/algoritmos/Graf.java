package dominio.algoritmos;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Graf {
	protected Map<String,Integer> Diccionari;
	protected Map<Integer,String> DiccionariInvers;
	protected Vector< Vector<Double> > Matriu;
	
	public Graf() {
		Diccionari = new TreeMap<String,Integer>();
		DiccionariInvers = new TreeMap<Integer,String>();
		Matriu = new Vector< Vector<Double> >();
	}
	
	public Graf(Graf G) {
		Diccionari = new TreeMap<String,Integer>(G.Diccionari);
		DiccionariInvers = new TreeMap<Integer,String>(G.DiccionariInvers);
		Matriu = new Vector< Vector<Double> >(G.Matriu);
	}
	
	public Integer size() {
		return Matriu.size();
	}
	
	public HashSet<String> getNodes() {
		return new HashSet<String>(Diccionari.keySet());
	}
	
	
	public Boolean addNode(String id) {
		if (existeixNode(id)) return false;
		Integer Posicio = Matriu.size();
		Matriu.add(new Vector<Double>(Posicio-1));
		
		//Nova fila al final inicialitzada a 0.0
		for(Integer i = 0; i < Matriu.size()-1; ++i){
			Matriu.get(Posicio).add(0.0);
		}
		
		//Nova columna al final inicialitzada a 0.0
		for(Integer i = 0; i < Matriu.size(); ++i){
			Matriu.get(i).add(0.0);
		}
		Diccionari.put(id,Posicio);
		DiccionariInvers.put(Posicio,id);
		return true;
	}
	
	public Boolean removeNode(String id) {
		if (!existeixNode(id)) return false;
		Integer Posicio = Diccionari.get(id);
		Integer Size = Matriu.size();
		for (Integer i = 0; i < Size; ++i){
			Matriu.get(i).remove(Posicio);
		}
		Matriu.remove(Posicio);
		Diccionari.remove(id);
		for (Integer i = Posicio+1; i < Size; ++i) {
			String iString = DiccionariInvers.get(i);
			Diccionari.put(iString,i-1);
			DiccionariInvers.put(i-1, iString);
		}
		
		DiccionariInvers.remove(Size-1);
		return true;
	}
	
	public Boolean existeixNode(String id) {
		return Diccionari.containsKey(id);
	}
	
	public Boolean addAresta(String a, String b, Double Pes) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b) || existeixAresta(a,b) || Pes < 0) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),Pes);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),Pes);
		return true;
	}
	
	public Boolean removeAresta(String a, String b) {
		if (!existeixAresta(a,b)) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),0.0);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),0.0);
		return true;
	}
	
	public Boolean existeixAresta(String a, String b) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b)) return false;
		if (Matriu.get(Diccionari.get(a)).get(Diccionari.get(b)) <= 0.0) return false;
		return true;
	}
	
	public Boolean setPes(String a, String b, Double Pes) {
		if (!existeixAresta(a,b) || Pes < 0) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),Pes);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),Pes);
		return true;
	}
	
	public Double getPes(String a, String b) {
		if (!existeixAresta(a,b)) return -1.0;
		return Matriu.get(Diccionari.get(a)).get(Diccionari.get(b));
	}
	
	public HashSet<String> getAdjacents(String id) {
		HashSet<String> Cjt = new HashSet<String>();
		Integer Posicio = Diccionari.get(id);
		Integer N = Matriu.size();
		for(Integer j = 0; j < N; ++j) {
			if (Matriu.get(Posicio).get(j) >= 0.0) Cjt.add(DiccionariInvers.get(j));
		}
		return Cjt;
	}
		
	
}