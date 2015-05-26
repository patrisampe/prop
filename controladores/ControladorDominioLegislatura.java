package controladores;

import java.util.Set;
import java.util.TreeSet;

import time.*;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.Legislatura;

/**
 * Controlador de dominio para la gestión tanto en conjunto como individualmente de las legislaturas.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ControladorDominioLegislatura extends ControladorDominio{
	
	/**
	 * Conjunto de legislaturas almacenadas en el sistema.
	 */
	private Conjunto<Legislatura> conjuntoLegislaturas;
	/**
	 * Instancia <i>singletone</i> de la clase.
	 */
	private static ControladorDominioLegislatura instance = null;
	
	/**
	 * Crea una nuevo controlador de dominio de legislaturas.
	 */
	protected ControladorDominioLegislatura(){
		conjuntoLegislaturas = new Conjunto<Legislatura>(Legislatura.class);
		error = new CodiError();
	}
	
	/**
	 * Crea una nueva instancia de la clase.
	 * @return Nueva instancia del <i>singletone</i> de la clase.
	 */
	public static ControladorDominioLegislatura getInstance() {
	      if (instance == null) {
	         instance = new ControladorDominioLegislatura();
	      }
	      return instance;
	}
	
	/**
	 * Limpia el conjunto del Controlador
	 */
	public void clear(){
		conjuntoLegislaturas.clear();
	}
	
	/**
	 * 
	 * @param nombreTipoEvento: nombre de la idLegislatura
	 * @return devuelve la Votacion correspondiente del idLegislatura
	 */
	public Legislatura get(Integer idLegislatura){
		return conjuntoLegislaturas.get(idLegislatura);
	}
	
	
	/**
	 * Consulta el número de legislaturas existentes en el sistema.
	 * @return Número de legislaturas del sistema.
	 */
	public Integer numeroLegislaturas(){
		return conjuntoLegislaturas.size();
	}
	
	/**
	 * Consulta las legislaturas existentes en el sistema.
	 * @return Conjunto de legislaturas del sistema.
	 */
	public Set<Legislatura> getAll() {
		return conjuntoLegislaturas.getAll();
	}
	
	/**
	 * Consulta los identificadores de las legislaturas existentes en el sistema.
	 * @return Conjunto de identificadores de legislaturas del sistema.
	 */
	public Set<Integer> getIDs() {
		return conjuntoLegislaturas.getIntegerKeys();
	}
	
	/** 
	 * Consulta el identificador de las legislaturas anterior y siguiente a una fecha concreta.
	 * @param fechaContenida - Fecha de la cual se desea obtener su legislatura.
	 * @return Array de dos posiciones que contiene las legislaturas anterior [0] y siguiente [1].
	 * <p>
	 * En caso que la fecha este contenida en una legislatura, los dos valores de la array contendrán la legislatura.
	 * <p>
	 * En caso que la fecha sea anterior a la primera legislatura, devuelve el valor -1 como anterior legislatura.
	 * <p>
	 * En caso que la fecha sea posterior a la ultima legislatura, devuelve el valor -1 como siguiente legislatura.
	 */
	public Integer[] getNearID(Date fechaContenida) {
		Integer[] res = new Integer[2];
		res[0] = getID(fechaContenida);
		res[1] = res[0];
		if (res[0] != -1) return res;
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			res[0] = res[1];
			res[1] = L.getID();
			if (fechaContenida.compareTo(L.getFechaInicio()) < 0) return res;
		}
		res[0] = res[1];
		res[1] = -1;
		return res;		
	}
	
	/** 
	 * Consulta el identificador de la legislatura que contiene una fecha concreta.
	 * @param fechaContenida - Fecha de la cual se desea obtener su legislatura.
	 * @return Identificador de la legislatura que contiene la fecha concreta.
	 */
	public Integer getID(Date fechaContenida) {
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			if (L.hasFechaFinal()) {
				DateInterval DI = new DateInterval(L.getFechaInicio(), L.getFechaFinal());
				if (DI.contains(fechaContenida)) return L.getID();
			}
			else if (L.getFechaInicio().compareTo(fechaContenida) >= 0) return L.getID();
		}
		return -1;
	}

	/**
	 * Consulta el identificador de la legislatura que contiene el mayor ID (la legislatura más actual).
	 * @return Identificador de la legislatura más actual del sistema.
	 */
	public Integer getIDLast() {
		int n = conjuntoLegislaturas.size();
		Integer[] a = new Integer[n];
		conjuntoLegislaturas.getIntegerKeys().toArray(a);
		return (n > 0 ? a[n-1] : -1);
	}

	/**
	 * Consulta los límites en que se pueden modificar las fechas de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura deseada.
	 * @return Intervalo de fechas en los que se puede modificar la legislatura.
	 */
	private DateInterval limits(Integer identificadorLegislatura) {
		Integer idA = identificadorLegislatura - 1;
		Integer idP = identificadorLegislatura;
		while (!existsLegislatura(idA) && idA >= 0) --idA;
		Integer IDLast = getIDLast();
		if (identificadorLegislatura < IDLast) {
			++idP;
			while (!existsLegislatura(idP)) ++idP;
		}
		Date inici = (idA == -1 ? Date.NULL : conjuntoLegislaturas.get(idA).getFechaFinal());
		Date fi = ((idP == identificadorLegislatura || IDLast == -1)  ? Date.NULL : conjuntoLegislaturas.get(idP).getFechaInicio());
		return new DateInterval(inici, fi);
	}
	
	/**
	 * Añade al sistema una legislatura a partir de sus datos.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param fechaInicio - Fecha de inicio de la legislatura.
	 * @param fechaFinal - Fecha de finalización de la legislatura.
	 */
	public void addLegislatura(Integer identificadorLegislatura, Date fechaInicio, Date fechaFinal) {
		if (existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(16);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (fechaInicio.compareTo(fechaFinal) > 0){
			error.setCodiError(26);
			error.addClauExterna(fechaInicio.toString());
			error.addClauExterna(fechaFinal.toString());
		}
		else {
			Integer idData = getID(fechaInicio);
			if (idData != -1 && idData != identificadorLegislatura){
				error.setCodiError(27);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaInicio.toString());
				error.addClauExterna(idData);
			}
			else if (idData == -1 && !(limits(identificadorLegislatura).contains(fechaInicio))) {
				error.setCodiError(28);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaInicio.toString());
			}
			else {
				idData = getID(fechaFinal);
				if (idData != -1 && idData != identificadorLegislatura){
					error.setCodiError(27);
					error.addClauExterna(identificadorLegislatura);
					error.addClauExterna(fechaFinal.toString());
					error.addClauExterna(idData);
				}
				else if (idData == -1 && !limits(identificadorLegislatura).contains(fechaFinal)) {
					error.setCodiError(28);
					error.addClauExterna(identificadorLegislatura);
					error.addClauExterna(fechaFinal.toString());
				}
				else conjuntoLegislaturas.add(new Legislatura(identificadorLegislatura, fechaInicio, fechaFinal));
			}
		}
	}

	
	/**
	 * Añade al sistema una legislatura a partir de sus datos.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param fechaInicio - Fecha de inicio de la legislatura.
	 */
	public void addLegislatura(Integer identificadorLegislatura, Date fechaInicio) {
		if (existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(16);
			error.addClauExterna(identificadorLegislatura);
		}
		else {
			Integer idL = getIDLast();
			Integer idC = getID(fechaInicio);
			if (idL != -1 && !conjuntoLegislaturas.get(idL).hasFechaFinal()){
				error.setCodiError(18);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(idL);
			}
			else if (idC != -1) {
				error.setCodiError(27);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaInicio.toString());
				error.addClauExterna(idC);
			}
			else conjuntoLegislaturas.add(new Legislatura(identificadorLegislatura, fechaInicio));
		} 
	}
	
	/**
	 * Consulta si existe una legislatura determinada en el sistema.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @return <i>true</i> si la legislatura indicada existe en el sistema.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsLegislatura(Integer identificadorLegislatura) {
		return conjuntoLegislaturas.exists(identificadorLegislatura);
	}

	/**
	 * Elimina del sistema la legislatura indicada.
	 * <p>
	 * Este método tiene efectos colaterales en todas las estructuras del sistema que contengan información acerca de la legislatura indicada.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 */
	public void removeLegislatura(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else conjuntoLegislaturas.remove(identificadorLegislatura);
	}
	
	/**
	 * Modifica la fecha de inicio de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param fechaInicio - Fecha de inicio de la legislatura.
	 */
	public void setFechaInicio(Integer identificadorLegislatura, Date fechaInicio) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (!(fechaInicio.compareTo(conjuntoLegislaturas.get(identificadorLegislatura).getFechaFinal()) <= 0)){
			error.setCodiError(26);
			error.addClauExterna(fechaInicio.toString());
			error.addClauExterna(conjuntoLegislaturas.get(identificadorLegislatura).getFechaFinal().toString());
		}
		else {
			Integer idData = getID(fechaInicio);
			if (idData != -1 && idData != identificadorLegislatura){
				error.setCodiError(27);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaInicio.toString());
				error.addClauExterna(idData);
			}
			else if (idData == -1 && !limits(identificadorLegislatura).contains(fechaInicio)) {
				error.setCodiError(28);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaInicio.toString());
			}
			else conjuntoLegislaturas.get(identificadorLegislatura).setFechaInicio(fechaInicio);
		}
	}
	
	/**
	 * Modifica la fecha de finalización de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param fechaFinal - Fecha de finalización de la legislatura.
	 */
	public void setFechaFinal(Integer identificadorLegislatura, Date fechaFinal) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (conjuntoLegislaturas.get(identificadorLegislatura).getFechaInicio().compareTo(fechaFinal) > 0){
			error.setCodiError(26);
			error.addClauExterna(conjuntoLegislaturas.get(identificadorLegislatura).getFechaInicio().toString());
			error.addClauExterna(fechaFinal.toString());
		}
		else {
			Integer idData = getID(fechaFinal);
			if (idData != -1 && idData != identificadorLegislatura){
				error.setCodiError(27);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaFinal.toString());
				error.addClauExterna(idData);
			}
			else if (idData == -1 && !limits(identificadorLegislatura).contains(fechaFinal)) {
				error.setCodiError(28);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(fechaFinal.toString());
			}
			else conjuntoLegislaturas.get(identificadorLegislatura).setFechaFinal(fechaFinal);
		}
	}
	
	/**
	 * Consulta la fecha de inicio de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @return Fecha de inicio de la legislatura.
	 */
	public Date getFechaInicio(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getFechaInicio();
	}
	
	/**
	 * Consulta la fecha finalización de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @return Fecha de finalización de la legislatura.
	 */
	public Date getFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else if (!hasFechaFinal(identificadorLegislatura)) {
			error.setCodiError(19);
			error.addClauExterna(identificadorLegislatura);
			return Date.NULL;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getFechaFinal();
	}

	/**
	 * Consulta si existe finalización de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @return <i>true</i> si la legislatura indicada tiene fecha de finalización.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean hasFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
			return false;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).hasFechaFinal();
	}

	
	/**
	 * Elimina la fecha de finalización de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 */
	public void removeFechaFinal(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (!hasFechaFinal(identificadorLegislatura)) {
			error.setCodiError(19);
			error.addClauExterna(identificadorLegislatura);
		}
		else {
			Integer id = getIDLast();
			if (id != identificadorLegislatura) {
				error.setCodiError(29);
				error.addClauExterna(identificadorLegislatura);
				error.addClauExterna(id);
			}
			else conjuntoLegislaturas.get(identificadorLegislatura).removeFechaFinal();
		}
	}
	
	/**
	 * Introduce un diputado en la lista de diputados activos de una legislatura.
	 * <p>
	 * Este metodo garantiza que la legislatura será introducida (si no lo ha sido ya) en la lista de legislaturas activas del diputado.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void addDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		if (!CDD.existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (existsDiputado(identificadorLegislatura, nombreDiputado)) {
			error.setCodiError(20);
			error.addClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoLegislaturas.get(identificadorLegislatura).addDiputado(nombreDiputado);
			if (!CDD.existsLegistura(nombreDiputado, identificadorLegislatura))
				CDD.addLegistura(nombreDiputado, identificadorLegislatura);
			catchError(CDD);
		}
	}
	
	/**
	 * Consulta la lista de diputados activos en una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @return Conjunto de nombres de los diputados activos en la legislatura.
	 */
	public Set<String> getDiputados(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
			return new TreeSet<String>();
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).getDiputados();
	}

	/**
	 * Consulta si un diputado pertenece a la lista de diputados activos de una legislatura.
	 * @param identificadorLegislatura - Identificador de la legislatura.
 	 * @param nombreDiputado - Nombre del diputado.
	 * @return <i>true</i> si el diputado es activo en la legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
			return false;
		}
		else return conjuntoLegislaturas.get(identificadorLegislatura).esActivo(nombreDiputado);
	}
	
	/**
	 * Elimina un diputado de la lista de diputados activos de una legislatura.
	 * <p>
	 * Este metodo garantiza que la legislatura será eliminada (si no lo ha sido ya) de la lista de legislaturas activas del diputado.
	 * @param identificadorLegislatura - Identificador de la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void removeDiputado(Integer identificadorLegislatura, String nombreDiputado) {
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		if (!CDD.existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (!existsDiputado(identificadorLegislatura, nombreDiputado)) {
			error.setCodiError(21);
			error.addClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoLegislaturas.get(identificadorLegislatura).removeDiputado(nombreDiputado);
			if (CDD.existsLegistura(nombreDiputado, identificadorLegislatura))
				CDD.removeLegistura(nombreDiputado, identificadorLegislatura);
			catchError(CDD);
			}
	}
	
	/**
	 * Elimina todos los diputados de la lista de diputados activos de una legislatura.
	 * <p>
	 * Este metodo garantiza que la legislatura será eliminada (si no lo ha sido ya) de las listas de legislaturas activas de todos los diputados.
 	 * @param identificadorLegislatura - Identificador de la legislatura.
 	 */
	public void removeDiputados(Integer identificadorLegislatura) {
		if (!existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else {
			conjuntoLegislaturas.get(identificadorLegislatura).removeDiputados();
			ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
			CDD.removeLegislaturaFromDiputados(identificadorLegislatura);
			catchError(CDD);
		}
	}
	
	/**
	 * Elimina un diputado de las listas de diputados activos de todas las legislaturas.
	 * <p>
	 * Este metodo garantiza que todas las legislaturas serán eliminadas (si no lo han sido ya) de la listas de legislaturas activas del diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void removeDiputadoFromLegislaturas(String nombreDiputado) {
		for (Legislatura L:conjuntoLegislaturas.getAll()) {
			Integer identificadorLegislatura = L.getID();
			if (existsDiputado(identificadorLegislatura, nombreDiputado))
				removeDiputado(identificadorLegislatura, nombreDiputado);
		}
	}	

}