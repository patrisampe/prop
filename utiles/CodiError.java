package utiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
/**
 * Classe que nos serive para capturar los errores
 * @author Patricia Samepdro
 * @version 1.0 Mayo 2015
 */
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
		Diccionario.put(9, "La importancia %1 no es una importancia valida (1<=I<=5).");
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
		Diccionario.put(30, "El indice de afinidad debe ser un porcentaje (0-100)");
		Diccionario.put(31, "No existe ningun resultado de busqueda con nombre %1");
		Diccionario.put(32, "Ya existe un resultado de búsqueda con nombre %1");
		Diccionario.put(33, "No existe el grupo afin %1 en el resultado de busqueda %2");
		Diccionario.put(34, "No se puede hacer una busqueda cada %1 legislaturas");
		Diccionario.put(35, "No se ha realizado ninguna busqueda");
		Diccionario.put(36, "En la votacion %1 el diputado %2 no esta de baja");
		Diccionario.put(37, "La votacion %1 no pertenece a ninguna legislatura");
		Diccionario.put(38, "Periodo no valido");
		Diccionario.put(39, "%1 no es un tipo de voto correcto");
		Diccionario.put(40, "Se intenta leer el / la %1 %2, pero no tiene un formato correcto.");
		Diccionario.put(41, "El fichero %1 tiene un formato incorrecto.");
		Diccionario.put(42, "Formato Incorrecto");
		Diccionario.put(43, "No se ha encontrado el fichero %1.");
		Diccionario.put(44, "Error de checksum en el fichero %1.");
		
        return Collections.unmodifiableMap(Diccionario);
	}
	/**
	 * Crear un codi Error
	 */
	public CodiError() {
		codiError = 0;
		clauExterna = new Vector<String>();
	}

	/**
	 * Crear un CodiError con codiError=numError
	 * @param numError
	 */
	public CodiError(Integer numError) {
		codiError = numError;
		clauExterna = new Vector<String>();
	}
	
	public CodiError(Integer n,Vector<String> v){
		codiError=n;
		clauExterna=v;
	}
	/**
	 * Et permet tornar a utilitzar el mateix codi error, ja que torna a incialitzar tots els camps
	 */
	public void netejaCodiError(){
		codiError=0;
		clauExterna = new Vector<String>();
	}
	/**
	 * Copia del CodiError CE
	 * @param CE- CodiError que es vol copiar
	 */
	public CodiError(CodiError CE) {
		codiError = CE.codiError;
		clauExterna = CE.clauExterna;
	}
	/**
	 * Devuelve las Claves Externas del Error
	 * @return claves externas del Error
	 */

	public Vector<String> getClauExterna() {
		return clauExterna;
	}
	/**
	 * Inserta una nueva Clave Externa(que es un String)
	 * @param clauExterna
	 */
	public void addClauExterna(String clauExterna) {
		this.clauExterna.add(clauExterna);
	}
	/**
	 * Inserta una nueva Clave Externa(que es un Integer)
	 * @param clauExterna
	 */
	public void addClauExterna(Integer clauExterna) {
		this.clauExterna.add(clauExterna.toString());
	}
    /**
     * Devuelve el codigo error
     * @return codigo Error
     */
	public Integer getCodiError() {
		return codiError;
	}
/**
 * Modifica el codigo Error
 * @param codiError
 */
	public void setCodiError(Integer codiError) {
		this.codiError = codiError;
	}
	/**
	 * Devuelve el mensaje de error
	 * <dd><b>Preconditions:</b><dd> 
	 * 1- No se puede llamar si hay mas claves Externas que %i
	 * 2- Si hay menos claves externas que %i, despues no se podrans sustituir todos los %i y saldran en el mensaje.
	 * @return mensaje de error con las claves externas incorporadas
	 */
	public String getMensajeError() {
		String mensaje = mensajesError.get(codiError);
		for (Integer i = 1; i <= clauExterna.size(); ++i) {
			String aux = "%" + i;;
			mensaje=mensaje.replaceFirst(aux, clauExterna.elementAt(i-1));
		}
		return mensaje;
	}
	
}
