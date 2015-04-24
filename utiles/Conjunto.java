package utiles;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dominio.*;

public class Conjunto<T extends ObjetoDominio> {
	private Map<String, T> conjunto;
	private final Boolean hasIntegerKey;
    private final Class<T> type;
	//private final Boolean esEvento;
	
	public Conjunto(Class<T> type){
		this.type = type;
		conjunto = new TreeMap<String, T>();
		if (type.getName().equals("Legislatura") || type.getName().equals("GrupoAfin")
			|| type.getName().equals("GrupoAfinPorDiputado")
			|| type.getName().equals("GrupoAfinPorPeriodo"))
				hasIntegerKey = true;
		else hasIntegerKey = false;
		//CLASSES AMB INTEGER:
			//Legislatura
			//GrupoAfin
		//esEvento = false;
	}
	
	public Class<T> getValueType(){
		return type;
	}
	
	public String getKeyType(){
		if (hasIntegerKey) return "Integer";
		return "String";
	}
	
	public Integer size(){
		return conjunto.size();
	}
	
	public Boolean isEmpty(){
		return conjunto.isEmpty();
	}
	
	public <U> void addAll(Map<U, T> M){
		if (!M.isEmpty()) {
			Boolean integerKey = M.keySet().iterator().next().getClass().equals(Integer.class);
			Boolean stringKey = M.keySet().iterator().next().getClass().equals(String.class);
			if (!hasIntegerKey && stringKey){ //String
				conjunto = new TreeMap<String, T>();
				Iterator<U> it = M.keySet().iterator();
				while (it.hasNext()) {
					U clave = it.next();
					conjunto.put(clave.toString(), M.get(clave));
				}
			}
			if (hasIntegerKey && integerKey){ //Integer
				conjunto = new TreeMap<String, T>();
				Iterator<U> it = M.keySet().iterator();
				while (it.hasNext()) {
					U clave = it.next();
					conjunto.put(clave.toString(), M.get(clave));
				}
			}
		}
	}
	
	public Map<?, T> getAll() {
		Map<Integer, T> M = new TreeMap<Integer, T>();
		if (!hasIntegerKey) return conjunto;
		else {
			Iterator<String> it = conjunto.keySet().iterator();
			while (it.hasNext()) {
				String clave = it.next();
				M.put(Integer.parseInt(clave), conjunto.get(clave));
			}
		}
		return M;
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
	
	/*
	public void addAllString(Map<String, T> M){
		if (!hasIntegerKey) conjunto = new TreeMap<String, T>(M);
	}
	
	public void addAllInteger(Map<Integer, T> M){
		if (hasIntegerKey){
			conjunto = new TreeMap<String, T>();
			Iterator<Integer> it = M.keySet().iterator();
			while (it.hasNext()) {
				Integer clave = it.next();
				conjunto.put(clave.toString(), M.get(clave));
			}
		}
	}
		
	public Map<String, T> getAllString() {
		if (!hasIntegerKey) return conjunto;
		return new TreeMap<String, T>();
	}
	
	public Map<Integer, T> getAllInteger() {
		Map<Integer, T> M = new TreeMap<Integer, T>();
		if (hasIntegerKey){
			Iterator<String> it = conjunto.keySet().iterator();
			while (it.hasNext()) {
				String clave = it.next();
				M.put(Integer.parseInt(clave), conjunto.get(clave));
			}
		}
		return M;
	}
	
	*/
	
}