package utiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Error {

	private Integer codi_error;
	private String clau_externa;
	
	private static final Map<Integer, String> mensajes_error = CreateMap();

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
		Diccionario.put(9, "El evento no tiene una importancia valida.");
		Diccionario.put(10, "El diputado no existe en esta legislatura.");
		Diccionario.put(11, "El diputado ya existe en esta legislatura.");
        return Collections.unmodifiableMap(Diccionario);
	}

	public Error(Integer codi_error, String clau_externa){
		this.codi_error = codi_error;
		this.clau_externa = clau_externa;
	}

	public String getClauExterna() {
		return clau_externa;
	}


	public void setClauExterna(String clau_externa) {
		this.clau_externa = clau_externa;
	}


	public Integer getCodiError() {
		return codi_error;
	}


	public void setCodiError(Integer codi_error) {
		this.codi_error = codi_error;
	}


	public static String getMensaje(Integer codi_error) {
		return mensajes_error.get(codi_error);
	}
	
	
}
