package utiles;

public class Utiles {
	
	public static String NormalizaString(String S){
		if (S.isEmpty()) return S;
		S = S.toLowerCase();
		Character c = S.charAt(0);
		c = Character.toUpperCase(c);
		S = S.substring(1);
		return c + S;
	}
	
}