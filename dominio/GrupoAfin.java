package dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

abstract class GrupoAfin {
	private Integer identificador;
	private Set<String> diputados;
	
	public GrupoAfin() {
		identificador = -1;
		diputados = new TreeSet<String>();
	}
	
	public GrupoAfin(Integer ID) {
		if (ID < 0) identificador = -1;
		else identificador = ID;
		diputados = new TreeSet<String>();
	}
	
	public Boolean setID(Integer ID) {
		if (ID == -1) identificador = ID;
		else return false;
		return true;
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
