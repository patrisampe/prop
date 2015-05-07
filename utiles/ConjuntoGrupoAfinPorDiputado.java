package utiles;

import java.util.Set;

import dominio.GrupoAfinPorDiputado;

/**
 * Conjunto abstracto auxilitar que almacena grupos afines por diputado.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class ConjuntoGrupoAfinPorDiputado extends ConjuntoGrupoAfin {
	
	/**
	 * Conjunto base donde se almacenan los datos.
	 */
	Conjunto<GrupoAfinPorDiputado> conjunto;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de clase GrupoAfinPorDiputado.
	 */
	public ConjuntoGrupoAfinPorDiputado(){
		conjunto = new Conjunto<GrupoAfinPorDiputado>(GrupoAfinPorDiputado.class);

	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */

	public ConjuntoGrupoAfinPorDiputado(ConjuntoGrupoAfinPorDiputado C){
		conjunto = new Conjunto<GrupoAfinPorDiputado>(C.conjunto);
	}
	
	/**
	 * Consulta todos los elementos del conjunto.
	 * @return Set que contiene todos los elementos del conjunto.
	 */
	public Conjunto<GrupoAfinPorDiputado> getAllPorDiputado() {
		return conjunto;
	}
	
}
