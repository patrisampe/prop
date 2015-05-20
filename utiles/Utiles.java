package utiles;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Conjunto de métodos con finalidades auxiliares para uso general.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class Utiles {
	
	/**
	 * Formatea el String para que la primera letra de cada palabra sea una mayúscula y el resto sean todas minúsculas.
	 * @param S - String a formatear.
	 * @return El string escrito con la primera letra de cada palabra mayúscula y el resto minúsculas.
	 */
	public static String NormalizaString(String S){
		if (S.isEmpty()) return S;
		S = S.toLowerCase();
		String aux[] = S.split(" ");
		S = "";
		for (String s:aux){
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
			S = S + " ";
		}
		return S.substring(0, S.length()-1);
	}
	
	/**
	 * Transforma un conjunto de objetos de un tipo cualquiera a un conjunto de Strings.
	 * @param set - conjunto con los parametros a formatear.
	 * @return Un Set que contiene todos los elementos del conjunto en forma de String.
	 */
	public static <T extends Object> Set<String> SetObjetToSetString(Set<T> set){
		Set<String> out = new TreeSet<String>();
		for (T o:set) out.add(o.toString());
		return out;
	}
	
	
	/**
	 * Consulta la ruta sobre la que se está ejecutando la aplicación.
	 * @return Un String que contiene la ruta de ejecucion.
	 */
	public static String getPath() {
	     File miDir = new File (".");
	     try {
			return miDir.getCanonicalPath();
		} catch (IOException e) {
			return "Ruta no encontrada.";
		}
	}
	
}