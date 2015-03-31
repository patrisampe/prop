package controladores;

import java.util.Map;
import java.util.TreeMap;

import utiles.Atributos_Diputado;

public class ControladorPersistenciaDiputado{

	public ControladorPersistenciaDiputado(){
		
	}
	
	public Map<String, Atributos_Diputado> Importa_fichero(){
		return new TreeMap<String, Atributos_Diputado>();
	}
	
	public Boolean Exporta_fichero(Map<String, Atributos_Diputado> Datos) {
		return true;
	}
	
	public Map<String, Atributos_Diputado> Carga_datos() {
		return new TreeMap<String, Atributos_Diputado>();
	}
	
	public Boolean Guarda_datos(Map<String, Atributos_Diputado> Datos) {
		return true;
	}
	
}