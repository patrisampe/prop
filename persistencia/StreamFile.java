package persistencia;

import io.Entrada;
import io.Salida;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import exceptions.ContainerFormatException;
import exceptions.FileChecksumException;
import exceptions.FileFormatException;
import time.Date;

/**
 * Objeto que contiene el conjunto de StreamContainer que hay que almacenar en un fichero.
 * @author David Moran
 * @version 18/5/2015 22:00
 * */
public class StreamFile {

	/**
	 * String que contiene la primera línea del fichero.
	 * */
	private String info;
	
	/**
	 * Vector que almacena el conjunto de contenedores del fichero.
	 * */
	private Vector<StreamContainer> contenido;
	
	/**
	 * Estructura que mapea cada nombre a su posición en el contenido.
	 * */
	private Map<String, Integer> indices;
	
	/**
	 * Instancia nula de la clase.
	 */
	public static final StreamFile NULL = new StreamFile();

	/**
	 * Calendario utilizado para calcular la fecha actual.
	 */
	private static Calendar calendar = Calendar.getInstance();

	/**
	 * Fecha actual.
	 */
	private static final Date D = new Date(calendar.get(5), calendar.get(2)+1, calendar.get(1));

	/**
	 * Mensaje auxiliar de la clase.
	 */
	private static final String message1 = "Fichero utilizado para el almacenamiento de informacion del programa "
											+ '"' + "Diputados de Estados Unidos" + '"'
											+ ", Creado por Miguel Angel Aranda, Yoel Cabo, David Moran y Patricia Sampedro.";
	/**
	 * Mensaje auxiliar de la clase.
	 */
	private static final String message2 = "Fichero creado el dia " + D.toString() + " a las "
											+ (calendar.get(11) > 10?"":'0') + calendar.get(11)
											+ ':' + (calendar.get(12) > 10?"":'0') + calendar.get(12)
											+ ':' + (calendar.get(13) > 10?"":'0') + calendar.get(13);
	
	/**
	 * Crea un nuevo StreamFile.
	 */
	public StreamFile(){
		indices = new HashMap<String, Integer>();
		contenido = new Vector<StreamContainer>();
		info = "" + message1 + ' ' + message2;
	}
	
	/**
	 * Crea un nuevo StreamFile copia de otro StreamFile.
	 * @param SF - StreamFile a ser copiado.
	 */
	public StreamFile(StreamFile SF){
		indices = SF.indices;
		contenido = SF.contenido;
		info = SF.info;
	}
	
	/**
	 * Consulta el numero de líneas del fichero.
	 * @return El numero de líneas del fichero.
	 */
	public Integer size() {
		return indices.size();
	}
	
	/**
	 * Compara dos objetos.
	 * @param SC - objeto a comparar.
	 * @return <i>true</i> si los objetos son iguales.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(StreamFile SF){
		return contenido.equals(SF.contenido);
	}
	
	/**
	 * Comprueba si el objeto es nulo.
	 * @return <i>true</i> si el objeto es nulo.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isNull(){
		return equals(NULL);
	}
	
	/**
	 * Inserta un nuevo StreamContainer al fichero.
	 * @param SC - StreamContainer a insertar.
	 */
	public void add(StreamContainer SC){
		if (!indices.containsKey(SC.getName())) {
			indices.put(SC.getName(), contenido.size());
			contenido.add(SC);
		}
	}
	
	/**
	 * Añade un nuevo StreamContainer en la posicion del fichero indicada. 
	 * @param SC - StreamContainer a añadir.
	 * @param i - Posicion donde se desea insertar SC.
	 */
	public void add(StreamContainer SC, Integer i){
		if (i > contenido.size() || i < 0) return;
		if (indices.containsKey(SC.getName())) return;
		String clave_aux = "";
		for (String s:indices.keySet()) {
			if (indices.get(s) == i) clave_aux = s;
		}
		indices.put(SC.getName(), i);
		indices.put(clave_aux, contenido.size());
		StreamContainer aux = contenido.elementAt(i);
		contenido.set(i, SC);
		contenido.add(aux);
		return;
	}
	
	/**
	 * Consulta la información del fichero.
	 * @return Un String con la informacion del fichero.
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * Consulta un elemento del fichero.
	 * @param i - Posición del elemento deseado.
	 * @return El StreamContainer situado en la posición indicada.
	 * @throws FileFormatException 
	 */
	public StreamContainer elementAt(Integer i) throws FileFormatException {
		--i;
		if (i >= contenido.size() || i < 0) throw new FileFormatException(i, "Linea invalida.");
		else return contenido.elementAt(i);
	}
	
	/**
	 * Consulta un objeto dentro de un elemento del fichero.
	 * @param i - Posición del elemento deseado en el fichero.
	 * @param j - Posición del elemento deseado en el contenedor.
	 * @return El StreamObject situado en la posición indicada.
	 * @throws FileFormatException 
	 */
	public StreamObject elementAt(Integer i, Integer j) throws FileFormatException {
		try {
			return elementAt(i).elementAt(j);
		} catch (ContainerFormatException e) {
			throw new FileFormatException(i, e.getMessage());
		}
	}
	
	/**
	 * Consulta un atributo dentro de un elemento del fichero.
	 * @param i - Posición del elemento deseado en el fichero.
	 * @param j - Posición del elemento deseado en el contenedor.
	 * @param k - Posición del atributo deseado en el contenedor.
	 * @return El StreamObject situado en la posición indicada.
	 * @throws FileFormatException 
	 */
	public String elementAt(Integer i, Integer j, Integer k) throws FileFormatException {
		try {
			return elementAt(i).elementAt(j, k);
		} catch (ContainerFormatException e) {
			throw new FileFormatException(i, e.getMessage());
		}
	}
	
	/**
	 * Consulta un elemento del fichero.
	 * @param name - Nombre del elemento deseado.
	 * @return El StreamContainer con el nombre indicado.
	 * @throws FileFormatException 
	 */
	public StreamContainer get(String name) throws FileFormatException{
		if (!indices.containsKey(name)) throw new FileFormatException(-1, "Nombre inexistente.");
		else return contenido.elementAt(indices.get(name));
	}
	
	/**
	 * Elimina todos los elementos del fichero, excepto la informacion.
	 */
	public void clear(){
		indices = new HashMap<String, Integer>();
		contenido = new Vector<StreamContainer>();
		info = "" + message1 + ' ' + message2;
	}
	
	/**
	 * Escribe el contenido en un fichero en formato CIOF.
	 * @param S - Salida por la que se desea escribir el contenido.
	 */
	public void print(Salida S) {
		String index = "" + indices.size() + ':';
		for (String s:indices.keySet()) {
			index = index + s + ':' + indices.get(s) + ';';
		}
		String[] containers = new String[contenido.size()+1];
		containers[0] = index;
		for (Integer i = 1; i <= contenido.size(); ++i) {
			containers[i] = StreamContainer.convert(contenido.elementAt(i-1));
		}
		
		Integer checksum = checksum(containers);
		
		try {
			S.write("File Encoding Format:CIOF;"
					+ Integer.toHexString(checksum).toUpperCase() + ';'
					+ (contenido.size()+2) + ';'
					+ info);
			for (String cont:containers) S.write(cont);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		S.close();
	}
	
	/**
	 * Carga el contenido de un fichero.
	 * @param E - Entrada de donde se desea leer el contenido.
	 */
	public void read(Entrada E) throws FileFormatException, FileChecksumException {
		String info_aux = "";
		try {
			info_aux = E.readLine();
		} catch (IOException e) {
			E.close();
			throw new FileFormatException(1, e.getMessage());
		}
		
		if (info_aux.isEmpty()) {
			E.close();
			throw new FileFormatException(1, "Formato de archivo incorrecto.");
		}
		
		String encoding = "";
		Integer j = 0;

		while (info_aux.charAt(j) != ';') {
			encoding += info_aux.charAt(j);
			if (info_aux.charAt(j) == ':') encoding = "";
			++j;
			if (j >= info_aux.length()){
				E.close();
				throw new FileFormatException(1, "Formato de archivo incorrecto.");
			}
		}
		++j;
		
		switch(encoding){
		case "CIOF":
			leerC(E, info_aux, j);
			break;
		case "SIOF":
			leerS(E);
			break;
		default:
			E.close();
			throw new FileFormatException(1, "Formato de fichero no soportado.");
		}
		E.close();
	}
	
	/**
	 * Calcula el checksum de un array de Strings.
	 * @param containers - Array que contiene los contenedores a proteger por el checksum.
	 * @return Un entero los tres bytes de menor peso del cual contienen 24 bits de checksum para proteger los contenedores del fichero.
	 */
	private Integer checksum(String[] containers) {
		int cs1[] = new int[containers.length];
		for (Integer j = 0; j < containers.length; ++j) {
			String cont = containers[j];
			Integer n = cont.length()/3;
			int[] cs2 = new int[n];
			for (Integer i = 0; i < n; ++i) {
				cs2[i] = 0;
				cs2[i] += cont.charAt(3*i) << 16;
				cs2[i] += cont.charAt(3*i+1) << 8;
				cs2[i] += cont.charAt(3*i+2);
			}
			cs1[j] = 0;
			for (Integer i = 0; i < n; ++i) cs1[j] += cs2[i];
			if (cont.length()%3 == 1) cs1[j] += cont.charAt(cont.length()-1) << 16;
			if (cont.length()%3 == 2) {
				Integer aux = cont.charAt(cont.length()-2) << 16;
				aux += cont.charAt(cont.length()-1) << 8;
				cs1[j] += aux;
			}
			int cplmt = cs1[j] >>> 24;
			cs1[j] &= 0x00FFFFFF;
			cs1[j] += cplmt;
			cs1[j] = 0x00FFFFFF - cs1[j]; //Ca1
		}
		int checksum = 0;
		for (Integer j = 0; j < containers.length; ++j) checksum += cs1[j];
		int cplmt2 = checksum >>> 24;
		checksum &= 0x00FFFFFF;
		checksum += cplmt2;
		checksum = 0x00FFFFFF - checksum; //Ca1
		return checksum;
	}
	
	/**
	 * Integra los datos de un fichero CIOF (Complex Imput/Output Format) en el StreamFile.
	 * @param E - Entrada por la que se desea leer los datos.
	 * @param info_aux - String con la informacion de la primera línea.
	 * @param j - Numero de caracter por el que hay que empezar a leer la primera línea.
	 */
	private void leerC(Entrada E, String info_aux, Integer j) throws FileFormatException, FileChecksumException {
		int checksum;
		String checksum_aux = "";
		String index_aux = "";
		Integer index = 0;
		Integer n;
		String n_aux = "";
	
		//TRADUCIMOS LA PRIMERE LINEA: INFORMACION DEL FICHERO
		if (j >= info_aux.length()){
			E.close();
			throw new FileFormatException(1, "Formato de archivo incorrecto.");
		}
		while (info_aux.charAt(j) != ';') {
			checksum_aux += info_aux.charAt(j);
			++j;
			if (j >= info_aux.length()){
				E.close();
				throw new FileFormatException(1, "Formato de archivo incorrecto.");
			}
		}
		++j;
		try {
			checksum = Integer.parseInt(checksum_aux, 16);
		} catch (NumberFormatException e) {
			E.close();
			throw new FileFormatException(1, e.getMessage());
		}
		if (j >= info_aux.length()){
			E.close();
			throw new FileFormatException(1, "Formato de archivo incorrecto.");
		}
		while (info_aux.charAt(j) != ';') {
			n_aux += info_aux.charAt(j);
			++j;
			if (j >= info_aux.length()){
				E.close();
				throw new FileFormatException(1, "Formato de archivo incorrecto.");
			}
		}		
		try {
			n = Integer.parseInt(n_aux);
		} catch (NumberFormatException e) {
			E.close();
			throw new FileFormatException(1, e.getMessage());
		}
		//TRADUCIMOS LA PRIMERE LINEA: INFORMACION DEL FICHERO
	
		//VALIDAMOS EL CHECKSUM Y LEEMOS EL RESTO DEL FICHERO
		String[] containers = new String[n-1];
		for (Integer i = 0; i < n-1; ++i) {
			try {
				containers[i] = E.readLine();
			} catch (IOException e) {
				E.close();
				throw new FileFormatException(i+2, e.getMessage());
			}
		}
		if (checksum != checksum(containers)) {
			E.close();
			throw new FileChecksumException(checksum(containers), Integer.valueOf(checksum));
		}
		//VALIDAMOS EL CHECKSUM Y LEEMOS EL RESTO DEL FICHERO
	
		//TRADUCIMOS LA SEGUNDA LINEA: EL INDICE DEL FICHERO
		j = 0;
		index_aux = "";
		if (containers[0].isEmpty()){
			E.close();
			throw new FileFormatException(2, "Formato de archivo incorrecto.");
		}
		while (containers[0].charAt(j) != ':') {
			index_aux += containers[0].charAt(j);
			++j;
			if (j >= containers[0].length()){
				E.close();
				throw new FileFormatException(2, "Formato de archivo incorrecto.");
			}
		}
		++j;
		try {
			index = Integer.parseInt(index_aux);
		} catch (NumberFormatException e) {
			E.close();
			throw new FileFormatException(2, e.getMessage());
		}
		Map<String, Integer> indices = new HashMap<String, Integer>();
		for (Integer k = 0; k < index; ++k) {
			index_aux = "";
			String aux = "";
			if (j >= containers[0].length()){
				E.close();
				throw new FileFormatException(2, "Formato de archivo incorrecto.");
			}
			while (containers[0].charAt(j) != ':') {
				aux += containers[0].charAt(j);
				++j;
				if (j >= containers[0].length()){
					E.close();
					throw new FileFormatException(2, "Formato de archivo incorrecto.");
				}
			}
			++j;
			if (j >= containers[0].length()){
				E.close();
				throw new FileFormatException(2, "Formato de archivo incorrecto.");
			}
			while (containers[0].charAt(j) != ';') {
				index_aux += containers[0].charAt(j);
				++j;
				if (j >= containers[0].length()){
					E.close();
					throw new FileFormatException(2, "Formato de archivo incorrecto.");
				}
			}
			try {
				indices.put(aux, Integer.parseInt(index_aux));
			} catch (NumberFormatException e) {
				E.close();
				throw new FileFormatException(2, e.getMessage());
			}
		}
		//TRADUCIMOS LA SEGUNDA LINEA: EL INDICE DEL FICHERO
	
		//TRADUCIMOS LAS OTRAS LINEAS: EL CONTENIDO DEL FICHERO
		Vector<StreamContainer> contenido = new Vector<StreamContainer>();
		for (Integer i = 1; i < n-1; ++i) {
			StreamContainer SC;
			try {
				SC = StreamContainer.convert(containers[i]);
			} catch (ContainerFormatException e) {
				E.close();
				throw new FileFormatException(i+2, e.getMessage());
			}
			contenido.add(SC);
		}
	
		//TRADUCIMOS LAS OTRAS LINEAS: EL CONTENIDO DEL FICHERO
		
		this.contenido = contenido;
		this.indices = indices;
		this.info =  "" + message1 + ' ' + message2;
	}
	
	/**
	 * Integra los datos de un fichero SIOF (Simple Imput/Output Format) en el StreamFile.
	 * @param E - Entrada por la que se desea leer los datos.
	 */
	private void leerS(Entrada E) throws FileFormatException {
		StreamContainer SC = new StreamContainer("Import");
		Boolean fi = false;
		Integer j = 1;
		String linea = "";
		try {
			linea = E.readLine();
		} catch (IOException e) {
			fi = true;
		}
		while (!fi) {
			++j;
			if (!linea.isEmpty() && linea.charAt(0) != '#') {
				Integer n = -1;
				StreamObject SO;
				Set<String> set;
	
				String[] atribs = linea.split(";");
				if (atribs.length < 4) { //Numero minimo de elementos de una clase de dominio.
					E.close();
					throw new FileFormatException(j, "Numero de atributos menor del esperado.");
				}

				switch (atribs[0]){
				case "Diputado":
					try {
						n = Integer.parseInt(atribs[5]);
					} catch (NumberFormatException e) {
						E.close();
						throw new FileFormatException(j, e.getMessage());
					}
					if (atribs.length != n+6) {
						E.close();
						throw new FileFormatException(j, "Numero de atributos incorrecto.");
					}
					SO = new StreamObject("Diputado");
					SO.add(atribs[1]);
					SO.add(atribs[2]);
					SO.add(atribs[3]);
					SO.add(atribs[4]);
					set = new HashSet<String>();
					for (Integer i = 0; i < n; ++i) set.add(atribs[i+6]);
					SO.add(set);		
					SC.add(SO);
				break;
				case "Evento":
					try {
						n = Integer.parseInt(atribs[3]);
					} catch (NumberFormatException e) {
						E.close();
						throw new FileFormatException(j, e.getMessage());
					}
					if (atribs.length != n+4) {
						E.close();
						throw new FileFormatException(j, "Numero de atributos incorrecto.");
					}
					SO = new StreamObject("Evento");
					SO.add(atribs[1]);
					SO.add(atribs[2]);
					set = new HashSet<String>();
					for (Integer i = 0; i < n; ++i) set.add(atribs[i+4]);
					SO.add(set);		
					SC.add(SO);
				break;
				case "Legislatura":
					if (atribs.length != 4) {
						E.close();
						throw new FileFormatException(j, "Numero de atributos incorrecto.");
					}
					SO = new StreamObject("Legislatura");
					SO.add(atribs[1]);
					SO.add(atribs[2]);
					SO.add(atribs[3]);
					SC.add(SO);
				break;
				case "TipoEvento":
					try {
						n = Integer.parseInt(atribs[3]);
					} catch (NumberFormatException e) {
						E.close();
						throw new FileFormatException(j, e.getMessage());
					}

					Integer[] n2 = new Integer[n];
					Integer sum = 0;
					for (Integer i = 0; i < n; ++i) {
						if (i != 0) sum += n2[i-1];
						try {
							n2[i] = Integer.parseInt(atribs[6+3*i + sum]);
						} catch (NumberFormatException e) {
							E.close();
							throw new FileFormatException(j, e.getMessage());
						}
					}
					sum += n2[n-1];
					if (atribs.length != 4 + 3*n + sum) {
						E.close();
						throw new FileFormatException(j, "Numero de atributos incorrecto.");
					}
					SO = new StreamObject("TipoEvento");
					SO.add(atribs[1]);
					SO.add(atribs[2]);
					sum = 0;
					for (Integer k = 0; k < n; ++k) {
						if (k != 0) sum += n2[k-1];
						StreamObject evento = new StreamObject("Evento");
						evento.add(atribs[4 + 3*k + sum]);
						evento.add(atribs[5 + 3*k + sum]);
						set = new HashSet<String>();
						for (Integer l = 0; l < n2[k]; ++l) {
							set.add(atribs[7 + 3*k + sum + l]);
						}
						evento.add(set);
						SO.addObject(evento);
					}
					SC.add(SO);
				break;
				case "Votacion":
					try {
						n = Integer.parseInt(atribs[4]);
					} catch (NumberFormatException e) {
						E.close();
						throw new FileFormatException(j, e.getMessage());
					}
					if (atribs.length != 2*n+5) {
						E.close();
						throw new FileFormatException(j, "Numero de atributos incorrecto.");
					}
					SO = new StreamObject("Votacion");
					SO.add(atribs[1]);
					SO.add(atribs[2]);
					SO.add(atribs[3]);
					String[] diputados = new String[n];
					String[] votos = new String[n];
					for (Integer i = 0; i < n; ++i) diputados[i] = atribs[5+i];
					for (Integer i = 0; i < n; ++i) votos[i] = atribs[5+n+i];
					SO.add(diputados);
					SO.add(votos);
					SC.add(SO);
				break;
				default:
					E.close();
					throw new FileFormatException(j, "Formato de objeto no soportado.");
				}
			}
			try {
				linea = E.readLine();
			} catch (IOException e) {
				E.close();
				fi = true;
			}
		}
		add(SC);
	}

}