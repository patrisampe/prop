package controladores;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utiles.Atributos_Diputado;
import utiles.Error;
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
	
	public Error setDiputado(String nombreDiputado, Atributos_Diputado atributos) {
		Diputado D = getDiputado(nombreDiputado);
		if (!atributos.getPartidoPolitico().equals(Atributos_Diputado.Unchanged_String))
			if (atributos.getPartidoPolitico().isEmpty()) return new Error(1, D.getNombre());
			D.setPartidoPolitico(atributos.getPartidoPolitico());
		if (!atributos.getEstado().equals(Atributos_Diputado.Unchanged_String))
			if (atributos.getEstado().isEmpty()) return new Error(1, D.getNombre());
			D.setEstado(atributos.getEstado());
		if (!atributos.getFechaDeNacimiento().equals(Atributos_Diputado.Unchanged_Date))
			if (!atributos.getFechaDeNacimiento().Es_valida()) return new Error(2, D.getNombre());
			D.setFechaNacimiento(atributos.getFechaDeNacimiento());
		Set<Integer> S = atributos.getLegislaturas().keySet();
		Iterator<Integer> it = S.iterator();
		while(it.hasNext()){
			Integer aux = it.next();
			if (atributos.getLegislaturas().get(aux)) {
				if (D.Es_activo(aux)) return new Error(11, D.getNombre());
				D.addLegistura(aux);
			}
			else {
				if (!D.Es_activo(aux)) return new Error(10, D.getNombre());
				D.removeLegistura(aux);
			}
		}
		return new Error(0, D.getNombre());
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

	public Error addDiputado(Diputado nuevoDiputado) {
		String Nombre = nuevoDiputado.getNombre();
		if (Conjunto_diputados.containsKey(Nombre)) return new Error(4, Nombre);
		Conjunto_diputados.put(Nombre, nuevoDiputado);
		return new Error(0, Nombre);
	}
	
	public Diputado getDiputado(String nombreDiputado) {
		if (!Conjunto_diputados.containsKey(nombreDiputado)) return Diputado.NULL;
		return Conjunto_diputados.get(nombreDiputado);
	}
	
	public Boolean Existe_diputado(String nombreDiputado) {
		return Conjunto_diputados.containsKey(nombreDiputado);
	}
	
	public Error removeDiputado(String nombreDiputado) {
		if (!Conjunto_diputados.containsKey(nombreDiputado)) return new Error(3, nombreDiputado);
		Conjunto_diputados.remove(nombreDiputado);
		return new Error(0, nombreDiputado);
	}

	public Error Abrir_archivo(String FileName) {
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
		return new Error(0, FileName);
	}
	
	public Error Crear_archivo(String FileName) {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		Map<String,Atributos_Diputado> M = new TreeMap<String, Atributos_Diputado>();
		Set<String> S = Conjunto_diputados.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()) {
			String aux = it.next();
			M.put(aux, getAtributosDiputado(aux));
		}
		C.Exporta_fichero(M, FileName);
		return new Error(0, FileName);
	}
	
	public Error Cargar_datos() {
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
		return new Error(0, "");
	}
	
	public Error Guardar_datos() {
		ControladorPersistenciaDiputado C = new ControladorPersistenciaDiputado();
		Map<String,Atributos_Diputado> M = new TreeMap<String, Atributos_Diputado>();
		Set<String> S = Conjunto_diputados.keySet();
		Iterator<String> it = S.iterator();
		while (it.hasNext()) {
			String aux = it.next();
			M.put(aux, getAtributosDiputado(aux));
		}
		C.Guarda_datos(M);
		return new Error(0, "");
	}
	
}