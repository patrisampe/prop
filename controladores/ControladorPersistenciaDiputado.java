package controladores;

import java.util.Map;
import java.util.TreeMap;

import dominio.Diputado;

public class ControladorPersistenciaDiputado{
	public ControladorPersistenciaDiputado(){
		
	}
	
	public Map<String, Diputado> Importa_fichero(){
		return new TreeMap<String, Diputado>();
	}
	
	public Boolean Exporta_fichero(Map<String, Diputado> Datos) {
		return true;
	}
	
	public Map<String, Diputado> Carga_datos() {
		return new TreeMap<String, Diputado>();
	}
	
	public Boolean Guarda_datos(Map<String, Diputado> Datos) {
		return true;
	}
	
}