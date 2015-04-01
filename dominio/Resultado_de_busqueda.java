package dominio;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Resultado_de_busqueda {
	
	//Atributs
	private String Nombre; 
	private Integer Indice_afinidad;
	private Tipo_Algoritmo Algoritmo;
	private Map<String, Integer> Importancia;
	private Boolean Modificado;
	private List<Integer> Grupos_afines;
	//Creadors
	
	public Resultado_de_busqueda(String Nombre, Integer Indice_afinidad, Tipo_Algoritmo Algoritmo, Map<String, Integer> Importancia, Boolean Modificado,  List<Integer> Grupos_afines) {
		this.Nombre = new String(Nombre);
		this.Indice_afinidad = new Integer(Indice_afinidad);
		this.Algoritmo = new Tipo_Algoritmo(Algoritmo);
		this.Importancia = new TreeMap<String,Integer>(Importancia);
		this.Modificado = new Boolean(Modificado);
		this.Grupos_afines = new List<Integer>(Grupos_afines);
	}
	
	public List<Integer> Consultar_grupos_afines() {
		return this.Grupos_afines;
	}
	
	public Resultado_de_busqueda(Resultado_de_busqueda R) {
		this.Nombre = new String(R.Nombre);
		this.Indice_afinidad = new Integer(R.Indice_afinidad);
		this.Algoritmo = new Tipo_Algoritmo(R.Algoritmo);
		this.Importancia = new TreeMap<String,Integer>(R.Importancia);
		this.Modificado = new Boolean(R.Modificado);
		this.Grupos_afines = new List<Integer>(R.Grupos_afines);
	}
	
	//Consultors
	public String getNombre() {
		return this.Nombre;
	}
	
	public Integer getIndice_afinidad() {
		return this.Indice_afinidad;
	}
	
	public Tipo_Algoritmo getAlgoritmo() {
		return this.Algoritmo;
	}
	
	public Boolean Es_modificado() {
		return this.Modificado;
	}
	
	public Integer Consultar_importancia(String nombreTipoEvento) {
		return this.Importancia.get(nombreTipoEvento);
	}
	
	public Boolean Cambiar_importancia(String nombreTipoEvento, Integer importancia) {
		Importancia.put(nombreTipoEvento,importancia);
		return true;
	}
}