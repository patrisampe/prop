package controladores;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utiles.Atributos_Diputado;
import dominio.Diputado;

class ControladorDominioDiputado {
	
	private Map<String, Diputado> Conjunto_diputados;
	
	public ControladorDominioDiputado(){
		Conjunto_diputados = new TreeMap<String, Diputado>();
	}
	
	public Diputado Crear_diputado(String nombreDiputado, Atributos_Diputado atributos){
		return new Diputado(nombreDiputado, atributos.getPartidoPolitico(), atributos.getEstado(),
							atributos.getFechaDeNacimiento());
	}
	
	public Atributos_Diputado getAtributosDiputado(String nombreDiputado){
		Diputado D = getDiputado(nombreDiputado);
		Atributos_Diputado A = new Atributos_Diputado();
		A.setPartidoPolitico(D.getPartidoPolitico());
		A.setEstado(D.getEstado());
		A.setFechaDeNacimiento(D.getFechaDeNacimiento());
		Set<Integer> S = D.getLegislaturas();
		Map<Integer, Boolean> M = new TreeMap<Integer, Boolean>();
		Iterator<Integer> it = S.iterator();
		while(it.hasNext()) M.put(it.next(), true);
		A.setLegislaturas(M);
		return A;
	}

	public Boolean addDiputado(Diputado nuevoDiputado) {
		String Nombre = nuevoDiputado.getNombre();
		if (Conjunto_diputados.containsKey(Nombre)) return false;
		Conjunto_diputados.put(Nombre, nuevoDiputado);
		return true;
	}
	
	public String setDiputado(String nombreDiputado, Atributos_Diputado atributos) {
		Diputado D = getDiputado(nombreDiputado);
		if (!atributos.getPartidoPolitico().equals(Atributos_Diputado.Unchanged_String))
			if (!D.setPartidoPolitico(atributos.getPartidoPolitico())) return "ERROR";
		if (!atributos.getEstado().equals(Atributos_Diputado.Unchanged_String))
			if (!D.setEstado(atributos.getEstado())) return "ERROR";
		if (!atributos.getFechaDeNacimiento().equals(Atributos_Diputado.Unchanged_Date))
			if (!D.setFechaNacimiento(atributos.getFechaDeNacimiento())) return "ERROR";
		Set<Integer> S = atributos.getLegislaturas().keySet();
		Iterator<Integer> it = S.iterator();
		while(it.hasNext()){
			Integer aux = it.next();
			if(!(atributos.getLegislaturas().get(aux) ? D.addLegistura(aux) : D.removeLegistura(aux)))
				return "ERROR";
		}
		return "ERROR";
	}

	public Diputado getDiputado(String nombreDiputado) {
		if (!Conjunto_diputados.containsKey(nombreDiputado)) return Diputado.NULL;
		return Conjunto_diputados.get(nombreDiputado);
	}
	
	public Boolean Existe_diputado(String nombreDiputado) {
		return Conjunto_diputados.containsKey(nombreDiputado);
	}
	
	public Boolean removeDiputado(String nombreDiputado) {
		if (!Conjunto_diputados.containsKey(nombreDiputado)) return false;
		Conjunto_diputados.remove(nombreDiputado);
		return true;
	}

	public Boolean Abrir_archivo(String FileName) {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		Conjunto_diputados = new TreeMap<String, Diputado>();
		Map<String, Atributos_Diputado> M;
		M = C.Importa_fichero(FileName);
		Set<String> S = M.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()){
			String aux = it.next();
			Conjunto_diputados.put(aux, Crear_diputado(aux, M.get(aux)));
		}
		return true;
	}
	
	public Boolean Crear_archivo(String FileName) {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		Map<String,Atributos_Diputado> M = new TreeMap<String, Atributos_Diputado>();
		Set<String> S = Conjunto_diputados.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()) {
			String aux = it.next();
			M.put(aux, getAtributosDiputado(aux));
		}
		return C.Exporta_fichero(M, FileName);
	}
	
	public Boolean Cargar_datos() {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		//Si hi ha algun problema al carregar: return false;
		Conjunto_diputados = new TreeMap<String, Diputado>();
		Map<String, Atributos_Diputado> M = C.Carga_datos();
		Set<String> S = M.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()){
			String aux = it.next();
			Conjunto_diputados.put(aux, Crear_diputado(aux, M.get(aux)));
		}
		return true;
	}
	
	public Boolean Guardar_datos() {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		Map<String,Atributos_Diputado> M = new TreeMap<String, Atributos_Diputado>();
		Set<String> S = Conjunto_diputados.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()) {
			String aux = it.next();
			M.put(aux, getAtributosDiputado(aux));
		}
		return C.Guarda_datos(M);	}

}