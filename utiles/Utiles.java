package utiles;

import java.util.Set;
import java.util.TreeSet;

/**
 * Conjunto de metodos con finalidades auxiliares para uso general.
 * @author David Moran
 * @version 11/05/2015 14:00
 */
public class Utiles {
	
	/**
	 * Formatea el String para que la primera letra sera una mayuscula y el resto sean todas minusculas.
	 * @param S - String a formatear.
	 * @return El string escrito con la primera letra mayuscula y el resto minusculas.
	 */
	public static String NormalizaString(String S){
		if (S.isEmpty()) return S;
		S = S.toLowerCase();
		Character c = S.charAt(0);
		c = Character.toUpperCase(c);
		S = S.substring(1);
		return c + S;
	}
	
	/**
	 * Formatea el String para que la primera letra sera una mayuscula y el resto sean todas minusculas.
	 * @param S - String a formatear.
	 * @return El string escrito con la primera letra mayuscula y el resto minusculas.
	 */
	public static <T extends Object> Set<String> SetObjetToSetString(Set<T> set){
		Set<String> out = new TreeSet<String>();
		for (T o:set) out.add(o.toString());
		return out;
	}
	
}