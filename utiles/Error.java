package utiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Error {

	private Integer codiError;
	private String clauExterna;
	
	private static final Map<Integer, String> mensagesError = CreateMap();

	
	private static Map<Integer, String> CreateMap() {
		Map<Integer, String> Diccionario = new HashMap<Integer, String>();
		Diccionario.put(0, "No hay error");
		Diccionario.put(1, "Dato en blanco.");
		Diccionario.put(2, "Fecha no valida.");
		Diccionario.put(3, "El diputado no existe.");
		Diccionario.put(4, "El diputado ya existe.");
		Diccionario.put(5, "El diputado no existe en este evento.");
		Diccionario.put(6, "El diputado ya existe en este evento.");
		Diccionario.put(7, "El evento no existe.");
		Diccionario.put(8, "El evento ya existe.");
		Diccionario.put(9, "El evento no tiene una importancia valida.1<=I<=5");
		Diccionario.put(10, "El diputado no existe en esta legislatura.");
		Diccionario.put(11, "El diputado ya existe en esta legislatura.");
		Diccionario.put(12, "Este tipo de evento ya existe");
		Diccionario.put(13, "Este tipo de evento no existe");
		Diccionario.put(14, "El diputado no existe en este evento.");
		Diccionario.put(15, "El diputado ya existe en este evento.");
        return Collections.unmodifiableMap(Diccionario);
	}

	public Error(Integer codiError, String clauExterna) {
		this.codiError = codiError;
		this.clauExterna = clauExterna;
	}



	public String getClauExterna() {
		return clauExterna;
	}


	public void setClauExterna(String clauExterna) {
		this.clauExterna = clauExterna;
	}


	public Integer getCodiError() {
		return codiError;
	}


	public void setCodiError(Integer codiError) {
		this.codiError = codiError;
	}


	public static String getMensagesError(int codiError) {
		return mensagesError.get(codiError);
	}
	
	public  String getMensagesError() {
		return mensagesError.get(codiError);
	}
	
	
}
