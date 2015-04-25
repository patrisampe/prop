package dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class GrupoAfin extends ObjetoDominio {
	private Integer identificador;
	private Set<String> diputados;
	
	public GrupoAfin(Integer ID) {
		identificador = ID;
		diputados = new TreeSet<String>();
	}
	
	public Boolean addDiputado(String Diputado) {
		return diputados.add(Diputado);
	}
	
	public Integer getID() {
		return identificador;
	}
	
	public List<String> getDiputados() {
		List<String> Lista_Diputados = new ArrayList<String>();
		Iterator<String> it = diputados.iterator();
		while (it.hasNext())
			Lista_Diputados.add(it.next());
		return Lista_Diputados;
	}
	
	public Boolean pertenece(String Diputado) {
		return diputados.contains(Diputado);
	}
	
	public Boolean removeDiputado(String Diputado) {
		return diputados.remove(Diputado);
	}
	
}
