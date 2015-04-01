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
		return new Diputado(nombreDiputado, atributos.Partido_politico, atributos.Estado, atributos.Fecha_de_nacimiento);
	}
	
	public Integer Modificar_diputado(String nombreDiputado, Atributos_Diputado atributos) {
		Integer comptador = 0;
		Diputado D = Consultar_diputado(nombreDiputado);
		if (!atributos.Partido_politico.equals(Atributos_Diputado.Unchanged_String))
			if (!D.setPartidoPolitico(atributos.Partido_politico)) return comptador;
		++comptador;
		if (!atributos.Estado.equals(Atributos_Diputado.Unchanged_String))
			if (!D.setEstado(atributos.Estado)) return comptador;
		++comptador;
		if (!atributos.Fecha_de_nacimiento.equals(Atributos_Diputado.Unchanged_Date))
			if (!D.setFechaNacimiento(atributos.Fecha_de_nacimiento)) return comptador;
		++comptador;
		Set<Integer> S = atributos.Legislaturas.keySet();
		Iterator<Integer> it = S.iterator();
		while(it.hasNext()){
			Integer aux = it.next();
			if(!(atributos.Legislaturas.get(aux) ? D.addLegistura(aux) : D.removeLegistura(aux)))
				return comptador;
			++comptador;
		}
		return -1;
	}

	public Atributos_Diputado Consultar_atributos_diputado(String nombreDiputado){
		Diputado D = Consultar_diputado(nombreDiputado);
		Atributos_Diputado A = new Atributos_Diputado();
		A.Partido_politico = D.getPartidoPolitico();
		A.Estado = D.getEstado();
		A.Fecha_de_nacimiento = D.getFechaDeNacimiento();
		Set<Integer> S = D.getLegislaturas();
		A.Legislaturas = new TreeMap<Integer, Boolean>();
		Iterator<Integer> it = S.iterator();
		while(it.hasNext()) A.Legislaturas.put(it.next(), true);
		return A;
	}

	public Boolean Añadir_diputado(Diputado nuevoDiputado) {
		String Nombre = nuevoDiputado.getNombre();
		if (Conjunto_diputados.containsKey(Nombre)) return false;
		Conjunto_diputados.put(Nombre, nuevoDiputado);
		return true;
	}
	
	public Diputado Consultar_diputado(String nombreDiputado) {
		return Conjunto_diputados.getOrDefault(nombreDiputado, Diputado.NULL);
	}
	
	public Boolean Existe_diputado(String nombreDiputado) {
		return Conjunto_diputados.containsKey(nombreDiputado);
	}
	
	public Boolean Eliminar_diputado(String nombreDiputado) {
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
			M.put(aux, Consultar_atributos_diputado(aux));
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
			M.put(aux, Consultar_atributos_diputado(aux));
		}
		return C.Guarda_datos(M);	}

}