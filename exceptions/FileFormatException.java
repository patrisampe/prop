package exceptions;

/**
 * Excepción lanzada cuando un fichero tiene un formato incorrecto.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class FileFormatException extends Exception {

	/**
	 * SerialVersion necesario para la creación de la excepción.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Línea en la que se ha detectado el error.
	 */
	private Integer lineaDeFallo;
	
	/**
	 * Información adicional sobre la excepción.
	 */
	private String otherInfo;

	/**
	 * Crea una nueva instancia de la excepción.
	 * @param l - Línea en la que se ha detectado el error.
	 * @param s - String con información adicional acerca de la excepción.
	 */
	public FileFormatException(Integer l, String s){
		super();
		lineaDeFallo = l;
		otherInfo = s;
	}
	
	/**
	 * Obtiene el mensaje de error de la excepción.
	 * @return El String que contiene el mensaje de error.
	 */
	public String getMessage() {
		return "Error de formato el la linea " + lineaDeFallo + " del fichero."
				+ (!otherInfo.isEmpty()?System.lineSeparator():"") + otherInfo;
	}
	
}