package dominio.algoritmos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

public class Louvain {
	private static GrafLouvain G;
	private static HashSet<HashSet<String> > Comunidades;
	//Potser cal un diccionari<Node, Punter a Comunitat>. Per quan volem saber a quina comunitat pertany un cert node en un cert moment.
	//O potser no :)
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
		Boolean optimitzada = false;
		while (!optimitzada) {
			optimitzada = true;
			for (String Node : Nodes) {
				HashSet<String> actual = getComunitat(Node);
				HashSet<String> maxCom = actual;
				Double max = 0.0;
				for(HashSet<String> aTractar : Comunidades) {
					if (actual == aTractar) continue;
					Double Inc = ModularityInc(Node, actual, aTractar);
					if (Inc > max) {
						max = Inc;
						maxCom = aTractar;
						optimitzada = false;
					}
				}
				actual.remove(Node);
				maxCom.add(Node);
			}
		}
		return false;
	}
	
	private static Double ModularityInc(String node, HashSet<String> origen,
			HashSet<String> destino) {
		Double grauNode = G.sumaPesosAdjacents(node);
		Double m = G.sumaPesos();
		Double res = (G.sumaPesosAdjacentsInclusiva(origen)-G.sumaPesosAdjacentsInclusiva(destino) - grauNode)*grauNode/m; //Casi segur és inclusiva, però ens hem d'assegurar que no sigui exclusiva
		res += G.sumaPesosAdjacents(node, destino) - G.sumaPesosAdjacents(node, origen);
		res /= 2*m;	
		return res;
	}

	private static HashSet<String> getComunitat(String node) {
		for (HashSet<String> Comunidad : Comunidades) {
			if (Comunidad.contains(node)) return Comunidad;
		}
		return null; //Nunca llegará aquí.
		
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
	/**
	 * Executa l'algorisme Louvain fent el percentatge% dels passos que faria l'algorisme si no se l'aturés.
	 * @param Gr Graf sobre el que s'executarà l'algorisme.
	 * @param percentatge 
	 * @return Conjunt de Comunitats resultant de l'execució.
	 */
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