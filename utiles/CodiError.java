package utiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CodiError {

	private Integer codiError;
	private Vector<String> clauExterna;
	
	private static final Map<Integer, String> mensajesError = CreateMap();
	
	private static Map<Integer, String> CreateMap() {
		Map<Integer, String> Diccionario = new HashMap<Integer, String>();
		Diccionario.put(0, "No hay error.");
		Diccionario.put(1, "Dato en blanco.");
		Diccionario.put(2, "Fecha no valida.");
		Diccionario.put(3, "El diputado %1 no existe.");
		Diccionario.put(4, "El diputado %1 ya existe.");
		Diccionario.put(5, "El diputado %1 no existe en el evento %2 en el tipo de evento %3.");
		Diccionario.put(6, "El diputado %1 ya existe en el evento %2 en el tipo de evento %3.");
		Diccionario.put(7, "El evento %1 no existe en el tipo de evento %2.");
		Diccionario.put(8, "El evento %1 ya existe en el tipo de evento %2.");
		Diccionario.put(9, "El TipoEvento %1 no tiene una importancia valida (1<=I<=5).");
		Diccionario.put(10, "El diputado %1 ya existe en la legislatura %2.");
		Diccionario.put(11, "El diputado %1 no existe en la legislatura %2.");
		Diccionario.put(12, "La legislatura %1 ya existe en el diputado %2.");
		Diccionario.put(13, "La legislatura %1 no existe en el diputado %2.");
		Diccionario.put(14, "El tipo de evento %1 ya existe.");
		Diccionario.put(15, "El tipo de evento %1 no existe.");
		Diccionario.put(16, "La legislatura %1 ya existe.");
		Diccionario.put(17, "La legislatura %1 no existe.");
		Diccionario.put(18, "La legislatura %1 no tiene fecha final, pero ya existe una legislatura sin fecha final (%2).");
		Diccionario.put(19, "La legislatura %1 no tiene fecha final.");
		Diccionario.put(20, "El diputado %1 ya existe en la legislatura %2.");
		Diccionario.put(21, "El diputado %1 no existe en la legislatura %2.");
		Diccionario.put(22, "La votacion %1 no existe");
		Diccionario.put(23, "La votacion %1 ya existe");
		Diccionario.put(24, "El Diputado %1 no ha votado en la votacion %2");
		Diccionario.put(25, "En la votacion %1 el diputado %2 esta de baja");
		Diccionario.put(26, "La fecha de inicio (%1) es posterior a la fecha final (%2)");
		Diccionario.put(27, "La legislatura %1 no puede contener la fecha %2, ya que pertenece a la legislatura %3.");
		Diccionario.put(28, "La legislatura %1 no puede contener la fecha %2, ya que se produce solapamiento entre legislaturas.");
		Diccionario.put(29, "No se puede eliminar la fecha de finalizacion de la legislatura %1 ya que ya existe una legislatura sin fecha final (%2).");
        return Collections.unmodifiableMap(Diccionario);
        /*
		USAGE: (CE: objecte de la classe CodiError)
			CE.setCodiError(10); //El diputado D ya existe en la legislatura L
			CE.setClauExterna(D.getNombre()); //D->"Pepito"
			CE.addClauExterna(L.getID().toString()); //L->42
			CE.getMensajeError() //El diputado Pepito ya existe en la legislatura 42.
        */
	}

	public CodiError() {
		codiError = 0;
		clauExterna = new Vector<String>();
	}
	
	public CodiError(CodiError CE) {
		codiError = CE.codiError;
		clauExterna = CE.clauExterna;
	}
	
	public CodiError(Integer codiError) {
		this.codiError = codiError;
		clauExterna = new Vector<String>();
	}
	
	public CodiError(Integer codiError, String clauExterna) {
		this.codiError = codiError;
		this.clauExterna = new Vector<String>();
		this.clauExterna.add(clauExterna);
	}
	
	public CodiError(Integer codiError, Vector<String> clauExterna) {
		this.codiError = codiError;
		this.clauExterna = clauExterna;
	}

	public Vector<String> getClauExterna() {
		return clauExterna;
	}

	public void setClauExterna(String clauExterna) {
		this.clauExterna = new Vector<String>();
		this.clauExterna.add(clauExterna);
	}
	
	public void setClauExterna(Integer clauExterna) {
		this.clauExterna = new Vector<String>();
		this.clauExterna.add(clauExterna.toString());
	}
	
	public void setClauExterna(Vector<String> clauExterna) {
		this.clauExterna = clauExterna;
	}

	public void addClauExterna(String clauExterna) {
		this.clauExterna.add(clauExterna);
	}
	
	public void addClauExterna(Integer clauExterna) {
		this.clauExterna.add(clauExterna.toString());
	}
	
	public void addClauExterna(Vector<String> clauExterna) {
		for (Integer i = 0; i < clauExterna.size(); ++i) {
			this.clauExterna.add(clauExterna.elementAt(i));
		}
	}

	public Integer getCodiError() {
		return codiError;
	}

	public void setCodiError(Integer codiError) {
		this.codiError = codiError;
	}
	
	public String getMensajeError() {
		String mensaje = mensajesError.get(codiError);
		for (Integer i = 1; i <= clauExterna.size(); ++i) {
			String aux = "%" + i;
			mensaje.replaceFirst(aux, clauExterna.elementAt(i-1));
		}
		return mensaje;
	}
	
}
