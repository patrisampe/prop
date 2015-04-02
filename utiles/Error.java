package utiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Error {

	private Integer codi_error;
	private String clau_externa;
	
	private static final Map<Integer, String> mensages_error = CreateMap();

	
	private static Map<Integer, String> CreateMap() {
		Map<Integer, String> Diccionario = new HashMap<Integer, String>();
		Diccionario.put(0, "No hay error");
		Diccionario.put(1, "Diputado no existe");
		Diccionario.put(2, "Diputado no esta en este evento");
		Diccionario.put(3, "Diputado ja esta en este evento");
		Diccionario.put(4, "Este evento no existe");
		Diccionario.put(5, "Este evento ya existe");
		Diccionario.put(6, "Data no valida");
		Diccionario.put(7, "Este evento no tiene una importancia valida. 1<=I<=5");
		Diccionario.put(8, "Este tipo de evento ya existe");
        return Collections.unmodifiableMap(Diccionario);
	}


	public String getClau_externa() {
		return clau_externa;
	}


	public void setClau_externa(String clau_externa) {
		this.clau_externa = clau_externa;
	}


	public Integer getCodi_error() {
		return codi_error;
	}


	public void setCodi_error(Integer codi_error) {
		this.codi_error = codi_error;
	}


	public static String getMensagesError(int i) {
		return mensages_error.get(i);
	}
	
	
}
