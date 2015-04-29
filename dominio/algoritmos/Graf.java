package dominio.algoritmos;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * La classe Graf implementa un graf ponderat no dirigit on els nodes són Strings.
 * @author Yoel Cabo
 *
 */

public class Graf {
	protected Map<String,Integer> Diccionari;
	protected Map<Integer,String> DiccionariInvers;
	protected Vector< Vector<Double> > Matriu;
	
	/**
	 * Creadora per defecte.
	 */
	public Graf() {
		Diccionari = new TreeMap<String,Integer>();
		DiccionariInvers = new TreeMap<Integer,String>();
		Matriu = new Vector< Vector<Double> >();
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
	 * Creadora per còpia a partir d'un Graf.
	 * @param G Graf que es copiarà.
	 */
	public Graf(Graf G) {
		Diccionari = new TreeMap<String,Integer>(G.Diccionari);
		DiccionariInvers = new TreeMap<Integer,String>(G.DiccionariInvers);
		Matriu = new Vector< Vector<Double> >(G.Matriu);
	}
	
	/**
	 * 
	 * @return Nombre de nodes del Graf.
	 */
	public Integer size() {
		return Matriu.size();
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
		Integer Posicio = Matriu.size();
		Matriu.add(new Vector<Double>(Posicio+1));
		
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
	
	/**
	 * Esborra un Node del Graf i totes les seves Arestes adjacents.
	 * @param id Node a esborrar.
	 * @return false si no existia, true si s'ha esborrat
	 */
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
	
	/**
	 * 
	 * @param id Node a comprovar la existència.
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
	 * @return false si l'aresta ja existia o no existeix un dels dos nodes o el pes no és correcte, true altrament.
	 */
	public Boolean addAresta(String a, String b, Double Pes) {
		if (!Diccionari.containsKey(a) || !Diccionari.containsKey(b) || existeixAresta(a,b) || Pes < 0) return false;
		Matriu.get(Diccionari.get(a)).set(Diccionari.get(b),Pes);
		Matriu.get(Diccionari.get(b)).set(Diccionari.get(a),Pes);
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
	public Boolean existeixAresta(String a, String b) {
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