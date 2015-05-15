package dominio.algoritmos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

/**
 * La classe Graf implementa un graf ponderat no dirigit on els nodes són Strings.
 * @author Yoel Cabo
 *
 */

public class Graf {
	protected Map<String,Integer> Diccionari;
	protected Map<Integer,String> DiccionariInvers;
	protected LlistaAdjacencia llista;
	
	protected class LlistaAdjacencia {
		private Vector< HashMap<Integer,Double> > lista;
		public LlistaAdjacencia() {
			lista = new Vector< HashMap<Integer,Double> >();
		}
		public LlistaAdjacencia(LlistaAdjacencia l) {
			lista = new Vector< HashMap<Integer,Double> >(l.lista.size());
			for (int i = 0; i < l.lista.size();++i) {
				lista.add(new HashMap<Integer,Double>(l.lista.get(i)));
			}
		}
		public Boolean exists(Integer i, Integer j) {
			return lista.get(i).containsKey(j);
		}
		public Double get(Integer i, Integer j) {
			if(exists(i,j)) return lista.get(i).get(j); 
			return 0.0;
		}
		public void set(Integer i, Integer j, Double value) {
			if(exists(i,j)) {
				lista.get(i).replace(j, value);
				lista.get(j).replace(i, value);
			}
			else {
				lista.get(i).put(j, value);
				lista.get(j).put(i, value);
			}
		}
		public void remove(Integer index) {
			for (int i = 0; i < lista.size(); ++i) {
				lista.get(i).remove(index);
			}
			lista.remove((int) index);
		}
		public int size() {
			return lista.size();
		}
		public Integer add() {
			lista.add(new HashMap<Integer,Double>());
			return llista.size()-1;
		}
		public void remove(Integer i, Integer j) {
			set(i,j,0.0);
		}
		public HashMap<Integer,Double> adjacents(Integer i) {
			return lista.get(i);
		}
		
	}
	/**
	 * Creadora per defecte.
	 */
	public Graf() {
		Diccionari = new HashMap<String,Integer>();
		DiccionariInvers = new HashMap<Integer,String>();
		llista = new LlistaAdjacencia();
	}
	
	/**
	 * Creadora a partir de HashSet. Crea un Graf que té per nodes el contingut del HashSet.
	 * @param NodesInicials Nodes del Graf que es crea.
	 */
	public Graf(HashSet<String> NodesInicials) {
		this();
		for (String Node : NodesInicials) {
			this.addNode(Node);
		}
	}
	
	/**
	 * Creadora per c�pia a partir d'un Graf.
	 * @param G Graf que es copiar�.
	 */
	public Graf(Graf G) {
		Diccionari = new HashMap<String,Integer>(G.Diccionari);
		DiccionariInvers = new HashMap<Integer,String>(G.DiccionariInvers);
		llista = new LlistaAdjacencia(G.llista);
	}
	
	/**
	 * 
	 * @return Nombre de nodes del Graf.
	 */
	public Integer size() {
		return llista.size();
	}
	
	/**
	 * 
	 * @return Conjunt de Nodes del Graf
	 */
	public HashSet<String> getNodes() {
		return new HashSet<String>(Diccionari.keySet());
	}
	
	/**
	 * Afegeix un Node al Graf, sense cap aresta adjacent.
	 * @param id Node a afegir.
	 * @return true si s'ha afegit, false si ja existia.
	 */
	public Boolean addNode(String id) {
		if (existeixNode(id)) return false;
		Integer Posicio = llista.add();
		Diccionari.put(id,Posicio);
		DiccionariInvers.put(Posicio,id);
		return true;
	}
	
	/**
	 * Esborra un Node del Graf i totes les seves Arestes adjacents.
	 * @param id Node a esborrar.
	 * @return false si no existia, true si s'ha esborrat
	 */
	public Boolean removeNode(String id) {
		if (!existeixNode(id)) return false;
		Integer Posicio = Diccionari.get(id);
		Integer Size = llista.size();
		llista.remove(Posicio);
		Diccionari.remove(id);
		for (Integer i = Posicio; i < Size-1; ++i) {//TODO Corregir errores
			String iString = DiccionariInvers.get(i+1);
			Diccionari.put(iString,i);
			DiccionariInvers.put(i, iString);
		}
		
		DiccionariInvers.remove(Size-1);
		return true;
	}
	
	/**
	 * 
	 * @param id Node a comprovar la exist�ncia.
	 * @return true si existeix, false altrament.
	 */
	public Boolean existeixNode(String id) {
		return Diccionari.containsKey(id);
	}
	
	/**
	 * Afegeix una aresta d'un pes determinat entre dos nodes.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @param Pes Pes de l'aresta entre a i b. Ha de ser >= 0.
	 * @return false si l'aresta ja existia o no existeix un dels dos nodes o el pes no �s correcte, true altrament.
	 */
	public Boolean addAresta(String a, String b, Double Pes) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b) || existeixAresta(a,b) || Pes < 0) return false;
		llista.set(Diccionari.get(a),Diccionari.get(b),Pes);
		llista.set(Diccionari.get(b),Diccionari.get(a),Pes);
		return true;
	}
	
	/**
	 * Elimina una aresta entre dos Nodes del Graf.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @return false si l'aresta ja no existia, true altrament. 
	 */
	public Boolean removeAresta(String a, String b) {
		if (!existeixAresta(a,b)) return false;
		llista.remove(Diccionari.get(a),Diccionari.get(b));
		return true;
	}
	
	/**
	 * Consulta si existeix una aresta entre dos Nodes del Graf.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @return true si existeix, false si no existeix.
	 */
	public Boolean existeixAresta(String a, String b) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b)) return false;
		if (llista.get(Diccionari.get(a), Diccionari.get(b)) <= 0.0) return false;
		return true;
	}
	
	/**
	 * Donada una aresta existent modifica el seu pes.
	 * @param a Un dels Nodes que connecta l'aresta.
	 * @param b L'altre dels Nodes que connecta l'aresta.
	 * @param Pes Pes de l'aresta entre a i b. Ha de ser >= 0.
	 * @return false si l'aresta no existia o si el Pes �s incorrecte, true altrament.
	 */
	public Boolean setPes(String a, String b, Double Pes) {
		if (!existeixAresta(a,b) || Pes < 0) return false;
		llista.set(Diccionari.get(a),Diccionari.get(b),Pes);
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
		return llista.get(Diccionari.get(a),Diccionari.get(b));
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
		Integer N = llista.size();
		for(Integer j = 0; j < N; ++j) {
			if (llista.get(Posicio,j) > 0.0) Cjt.add(DiccionariInvers.get(j));
		}
		return Cjt;
	}
		
	
}