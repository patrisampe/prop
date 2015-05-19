package exceptions;

/**
 * Excepción lanzada cuando un fichero tiene un checksum incorrecto.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class FileChecksumException extends Exception {

	/**
	 * SerialVersion necesario para la creación de la excepción.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Checksum calculado a partir de los datos del fichero.
	 */
	private Integer checksumGot;
	
	/**
	 * Checksum integrado en los datos del fichero.
	 */
	private Integer checksumExpected;
	
	/**
	 * Crea una nueva instancia de la excepción.
	 * @param got - Checksum calculado a partir de los datos del fichero.
	 * @param expected - Checksum integrado en los datos del fichero.
	 */
	public FileChecksumException(Integer got, Integer expected){
		super();
		checksumGot = got;
		checksumExpected = expected;
		}
	
	/**
	 * Obtiene el mensaje de error de la excepción.
	 * @return El String que contiene el mensaje de error.
	 */
	public String getMessage() {
		return "Error de checksum: El checksum incluido en el fichero es " + Integer.toHexString(checksumExpected).toUpperCase()
				+ " pero el checksum calculado es " + Integer.toHexString(checksumGot).toUpperCase() + ".";
	}

	
}