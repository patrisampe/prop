package drivers;

import java.util.Set;
import java.util.TreeSet;

public class GrupoAfinPorPeriodoDriver {

	public void addDiputado(String Diputado) {}
	public void removeDiputado(String nombreDiputado) {}
	public Integer getID() {return 2;}
	public Set<String> getDiputados() {return new TreeSet<String>();}
	public Boolean pertenece(String Diputado) {return true;}
	public Boolean esVacio() {return true;}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
