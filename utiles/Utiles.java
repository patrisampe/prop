package utiles;

import java.util.Set;
import java.util.TreeSet;

/**
 * Conjunto de metodos con finalidades auxiliares para uso general.
 * @author David Moran
 * @version 03/05/2015 01:02
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
	public static Set<String> SetObjetToSetString(Set<Object> set){
		Set<String> out = new TreeSet<String>();
		for (Object o:set) out.add(o.toString());
		return out;
	}
	
}