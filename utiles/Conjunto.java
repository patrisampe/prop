package utiles;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import dominio.*;

public class Conjunto<T extends ObjetoDominio> {
	private Map<String, T> conjunto;
	private final Boolean hasIntegerKey;
    private final Class<T> type;
	//private final Boolean esEvento; TODO
	
	public Conjunto(Class<T> type){
		this.type = type;
		conjunto = new TreeMap<String, T>();
		hasIntegerKey = (type.equals(Legislatura.class)
						 || type.equals(GrupoAfinPorDiputado.class)
					   /*|| type.equals(GrupoAfinPorPeriodo.class)*/);
		//esEvento = false; TODO
	}
	
	public Class<T> getValueType(){
		return type;
	}
	
	public Boolean KeyTypeIsInteger(){
		return hasIntegerKey;
	}
	
	public Integer size(){
		return conjunto.size();
	}
	
	public Boolean isEmpty(){
		return conjunto.isEmpty();
	}
	
	public void addAll(Set<T> S){
		if (!S.isEmpty()) {
			conjunto.clear();
			Iterator<T> it = S.iterator();
			while (it.hasNext()) {
				T elemento = it.next();
				if (!hasIntegerKey){ //String
					conjunto.put(elemento.getNombre(), elemento);
				}
				else { //Integer
					conjunto.put(elemento.getID().toString(), elemento);				
				}
			}
		}
	}
	
	public Set<T> getAll() {
		Set<T> S = new TreeSet<T>();
		Iterator<String> it = conjunto.keySet().iterator();
		while (it.hasNext()) S.add(conjunto.get(it.next()));
		return S;
	}
	
	public Set<Integer> getIntegerKeys() {
		if (!hasIntegerKey) return new TreeSet<Integer>();
		Set<Integer> S = new TreeSet<Integer>();
		Iterator<String> it = conjunto.keySet().iterator();
		while (it.hasNext()) S.add(Integer.parseInt(it.next()));			
		return S;
	}
	
	public Set<String> getStringKeys() {
		if (hasIntegerKey) return new TreeSet<String>();
		return conjunto.keySet();
	}
	
	public void removeAll() {
		conjunto.clear();
	}
	
	public void add(String nombreObjeto, T objeto) {
		if (!hasIntegerKey) conjunto.put(nombreObjeto, objeto);
	}
	
	public void add(Integer idObjeto, T objeto) {
		if (hasIntegerKey) conjunto.put(idObjeto.toString(), objeto);
	}
	
	public T get(String nombreObjeto) {
		if (!hasIntegerKey) return conjunto.get(nombreObjeto);
		return null;
	}
	
	public T get(Integer idObjeto) {
		if (hasIntegerKey) return conjunto.get(idObjeto.toString());
		return null;
	}
	
	public Boolean exists(String nombreObjeto) {
		if (!hasIntegerKey) return conjunto.containsKey(nombreObjeto);
		return false;
	}
	
	public Boolean exists(Integer idObjeto) {
		if (hasIntegerKey) return conjunto.containsKey(idObjeto.toString());
		return false;
	}
	
	public void remove(String nombreObjeto) {
		if (!hasIntegerKey) conjunto.remove(nombreObjeto);
	}
	
	public void remove(Integer idObjeto) {
		if (hasIntegerKey) conjunto.remove(idObjeto.toString());
	}
	
}