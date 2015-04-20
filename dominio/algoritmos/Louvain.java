package dominio.algoritmos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

public class Louvain {
	private static GrafLouvain G;
	private static HashSet<HashSet<String> > Comunidades;
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
			NouGraf.addNode(i.toString());
			Mapa.put(i.toString(), Comunidad);
		}
		Historia.addElement(Mapa);
		Comunidades = new HashSet< HashSet<String> >();
		HashSet<String> Nodes = new HashSet<String> (Historia.get(Historia.size()-1).keySet()); //Agafa els noms tots els noms que se li ha donat als diversos nodes agregats de comunitats.
		Iterator<String> iS = Nodes.iterator();
		while(iS.hasNext()) {
			HashSet<String> unitaria = new HashSet<String>();
			String act = new String(iS.next());
			unitaria.add(act);
			Comunidades.add(unitaria);
		}
		
		
		
		G = NouGraf;
		
	}
	
	public static HashSet< HashSet<String> > executa(GrafLouvain Gr, Integer percentatge) {
		G = new GrafLouvain(Gr);
		init(Gr);
		return null;
		
	}
	
}