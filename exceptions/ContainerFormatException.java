package exceptions;

/**
 * Excepción lanzada cuando un contenedor tiene un formato incorrecto.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ContainerFormatException extends Exception {
	
	/**
	 * SerialVersion necesario para la creación de la excepción.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indica si el error se halla en el índice.
	 */
	private Boolean errorEnIndice;
	
	/**
	 * Indica el elemento en el que se halla el error.
	 */
	private Integer elemento = -1;

	/**
	 * Información adicional sobre la excepción.
	 */
	private String otherInfo;

	/**
	 * Crea una nueva instancia de la excepción.
	 * @param s - String con información adicional acerca de la excepción.
	 */
	public ContainerFormatException(String s){
		super();
		errorEnIndice = true;
		otherInfo = s;
	}
	
	/**
	 * Crea una nueva instancia de la excepción.
	 * @param objeto - Numero del elemento que ha provocado el error.
	 * @param s - String con información adicional acerca de la excepción.
	 */
	public ContainerFormatException(Integer objeto, String s){
		super();
		errorEnIndice = false;
		elemento = objeto;
		otherInfo = s;
	}
	
	/**
	 * Obtiene el mensaje de error de la excepción.
	 * @return El String que contiene el mensaje de error.
	 */
	public String getMessage() {
		String out = "Error de formato en " + (errorEnIndice? "el indice" : "el contenido")
					 + " del contenedor.";
		if (!errorEnIndice) {
			out += System.lineSeparator();
			if (elemento > 0) out = out + "Elemento " + elemento + " incorrecto.";
			else out = out + "El contenedor tiene menor longitud de la esperada.";
		} 
		if (!otherInfo.isEmpty()) out = out + System.lineSeparator() + otherInfo;
		return out;
	}

}