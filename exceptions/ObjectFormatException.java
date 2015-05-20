package exceptions;

/**
 * Excepción lanzada cuando un objeto tiene un formato incorrecto.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ObjectFormatException extends RuntimeException {
	
	/**
	 * SerialVersion necesario para la creación de la excepción.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indica si el error se halla en el índice.
	 */
	private Boolean errorEnIndice;
	
	/**
	 * Información adicional sobre la excepción.
	 */
	private String otherInfo;
	
	/**
	 * Crea una nueva instancia de la excepción.
	 * @param errorEnIndice - <i>true</i> si el error se halla en el índice, <i>false</i> en cualquier otro caso.
	 * @param s - String con información adicional acerca de la excepción.
	 */
	public ObjectFormatException(Boolean errorEnIndice, String s){
		super();
		this.errorEnIndice = errorEnIndice;
		otherInfo = s;
	}
	
	/**
	 * Obtiene el mensaje de error de la excepción.
	 * @return El String que contiene el mensaje de error.
	 */
	public String getMessage() {
		return "Error de formato en " + (errorEnIndice? "el indice" : "el contenido") +  " del objeto."
				+ (!otherInfo.isEmpty()?System.lineSeparator():"") + otherInfo;
	}
	
}