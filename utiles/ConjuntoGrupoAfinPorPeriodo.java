package utiles;

import dominio.GrupoAfinPorPeriodo;

/**
 * Conjunto abstracto auxilitar que almacena grupos afines por periodo.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class ConjuntoGrupoAfinPorPeriodo extends ConjuntoGrupoAfin {
	
	/**
	 * Conjunto base donde se almacenan los datos.
	 */
	Conjunto<GrupoAfinPorPeriodo> conjunto;
	
	/**
	 * Crea una nueva instancia del conjunto, que contiene objetos de clase GrupoAfinPorDiputado.
	 */
	public ConjuntoGrupoAfinPorPeriodo(){
		conjunto = new Conjunto<GrupoAfinPorPeriodo>(GrupoAfinPorPeriodo.class);

	}
	
	/**
	 * Crea una nueva instancia del conjunto a partir de los datos de otro conjunto.
	 * @param C - Conjunto del que se deben copiar los datos.
	 */

	public ConjuntoGrupoAfinPorPeriodo(ConjuntoGrupoAfinPorPeriodo C){
		conjunto = new Conjunto<GrupoAfinPorPeriodo>(C.conjunto);
	}
	
}
