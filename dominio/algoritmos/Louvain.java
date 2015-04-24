package dominio.algoritmos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

public class Louvain {
	private static GrafLouvain G;
	private static HashSet<HashSet<String> > Comunidades;
	//Potser cal un diccionari<Node, Punter a Comunitat>. Per quan volem saber a quina comunitat pertany un cert node en un cert moment.
	private static Vector<TreeMap<String,HashSet<String> > > Historia;
	
	
	
	private static void init(GrafLouvain G)  {
		Historia = new Vector<TreeMap<String,HashSet<String> > >();
		Comunidades = new HashSet< HashSet<String> >();
		HashSet<String> Nodes = G.getNodes();
		Iterator<String> It = Nodes.iterator();
		TreeMap<String,HashSet<String> > Mapa = new TreeMap<String, HashSet<String>>();
		while(It.hasNext()) {
			HashSet<String> primera = new HashSet<String>();
			String act = new String(It.next());
			primera.add(act);
			Comunidades.add(primera);
			Mapa.put(act, primera);
			
		}
		Historia.addElement(Mapa);
	}
	
	private static void agregaGraf() {
		GrafLouvain NouGraf = new GrafLouvain();
		Iterator<HashSet<String> >  iHS = Comunidades.iterator();
		TreeMap<String,HashSet<String> > Mapa = new TreeMap<String, HashSet<String>>();
		for (Integer i = 0; iHS.hasNext(); ++i) {
			HashSet<String> Comunidad = new HashSet<String>(iHS.next()); 
			if (Comunidad.size() > 0) {
				NouGraf.addNode(i.toString());
				Mapa.put(i.toString(), Comunidad);
			}
		}
		Historia.addElement(Mapa); //Actualitzem la Història de l'algorisme amb un nou pas
		HashSet<String> Nodes = new HashSet<String> (Historia.get(Historia.size()-1).keySet()); //Agafa els noms tots els noms que se li ha donat als diversos nodes agregats de comunitats.
		Comunidades = HSStoHSHSS(Nodes); //Reiniciem les comunitats a comunitats individuals
		Iterator<String> It = Nodes.iterator();
		while (It.hasNext()) { //Omplim d'arestes el NouGraf
			Iterator<String> Jt = Nodes.iterator();
			while(Jt.hasNext()) {
				String a = new String(It.next());
				String b = new String(Jt.next());
				if(!NouGraf.existeixAresta(a, b)) {
					Double Pes = G.sumaPesosAdjacents(Historia.get(Historia.size()-1).get(a), Historia.get(Historia.size()-1).get(b));
					if (Pes > 0) NouGraf.addAresta(a, b, Pes);
				}
				
			}
		}
		G = NouGraf; //Graf actualitzat
	}
	

	private static boolean IncrementModularity() {
		HashSet<String> Nodes = G.getNodes();
		Iterator<String> It = Nodes.iterator();
		Boolean optimitzada = false;
		while (!optimitzada) {
			optimitzada = true;
			while (It.hasNext()) {
				String Node = It.next();
				HashSet<String> actual = getComunitat(Node);
				Iterator<HashSet<String>> iHS = Comunidades.iterator();
				while(iHS.hasNext()) {
					HashSet<String> aTractar = iHS.next();
					if (actual == aTractar) continue;
					if (ModularityInc(Node, actual, aTractar) > 0.0) {
						actual.remove(Node);
						aTractar.add(Node);
						actual = aTractar;
						optimitzada = false;
					}
					
				}
			}
		}
		return false;
	}
	
	private static Double ModularityInc(String node, HashSet<String> origen,
			HashSet<String> destino) {
		// TODO Auto-generated method stub
		// Tinc la formula en un paper i ara a la gespa me fa pal treure'l jajaj
		return 0.0;
	}

	private static HashSet<String> getComunitat(String node) {
		return null;
		// TODO Auto-generated method stub
		
	}

	private static HashSet<HashSet<String>> retorna(Integer percentatge) {
		Integer Total = Historia.size();
		Integer Interesante = (100-Total)*percentatge/100;
		HashSet<String> Generacion = new HashSet<String>(Historia.get(Interesante).keySet());
		HashSet<HashSet<String> >  ret = HSStoHSHSS(Generacion);
		Iterator<HashSet<String> > It = ret.iterator();
		while (It.hasNext()) {
			ret.add(historiador(Interesante, It.next()));
		}
		return ret;
	}
	
	
	
	private static HashSet<String> historiador(Integer Posicion, HashSet<String> Com) {
		HashSet<String> Merged = new HashSet<String>();
		if (Posicion == 0) {
		Merged.addAll(Com);	
		}
		else {
			Iterator<String> It = Com.iterator();
			while (It.hasNext()) {
				Merged.addAll(historiador(Posicion-1, Historia.get(Posicion).get(It.next())));
			}
		}
		return Merged;	
	}

	private static HashSet<HashSet<String>> HSStoHSHSS(HashSet<String> seed) {
		HashSet< HashSet<String> > Plant = new HashSet< HashSet<String> >();
		Iterator<String> iS = seed.iterator();
		while(iS.hasNext()) {
			HashSet<String> unit = new HashSet<String>();
			String act = new String(iS.next());
			unit.add(act);
			Plant.add(unit);
		}
		return Plant;
	}

	public static HashSet< HashSet<String> > executa(GrafLouvain Gr, Integer percentatge) {
		G = new GrafLouvain(Gr);
		init(Gr); 
		while(Comunidades.size() > 1) {
			while(IncrementModularity()); //TODO
			agregaGraf();
		}
		
		return retorna(percentatge);
		
	}

	
}