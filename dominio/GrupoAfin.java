package dominio;

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
	
	public Set<String> getDiputados() {
		return new TreeSet<String>(diputados);
	}
	
	public Boolean pertenece(String Diputado) {
		return diputados.contains(Diputado);
	}
	
	public Boolean removeDiputado(String Diputado) {
		return diputados.remove(Diputado);
	}
	
}
