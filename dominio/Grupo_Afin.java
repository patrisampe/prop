package dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Grupo_Afin {
	private Integer Identificador;
	private Set<String> Diputados;
	private Map<String, Resultado_de_busqueda> Conjunto_resultados;
	
	public Grupo_Afin(Integer ID) {
		Identificador = ID;
		Diputados = new TreeSet<String>();
		Conjunto_resultados = new TreeMap<String, Resultado_de_busqueda>();
	}
	
	public Boolean addDiputado(String Diputado) {
		return Diputados.add(Diputado);
	}
	
	public Integer getID() {
		return Identificador;
	}
	
	public List<String> getDiputados() {
		List<String> Lista_Diputados = new ArrayList<String>();
		Iterator<String> it = Diputados.iterator();
		while (it.hasNext())
			Lista_Diputados.add(it.next());
		return Lista_Diputados;
	}
	
	public Boolean pertenece(String Diputado) {
		return Diputados.contains(Diputado);
	}
	
	public Boolean removeDiputado(String Diputado) {
		return Diputados.remove(Diputado);
	}
	
}
