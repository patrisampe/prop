package controladores;

import java.util.Set;
import java.util.TreeSet;

import time.*;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.Diputado;

/**
 * Controlador de dominio para la gestion tanto en conjunto como individualmente de los diputados.
 * @author David Morán
 * @version 18/05/2015 22:00
 */
public class ControladorDominioDiputado extends ControladorDominio {
	
	/**
	 * Conjunto de diputados almacenados en el sistema.
	 */
	private Conjunto<Diputado> conjuntoDiputados;
	/**
	 * Instancia <i>singletone</i> de la clase.
	 */
	private static ControladorDominioDiputado instance = null;
	
	/**
	 * Crea una nuevo controlador de dominio de diputados.
	 */
	protected ControladorDominioDiputado(){
		conjuntoDiputados = new Conjunto<Diputado>(Diputado.class);
		error = new CodiError();
	}
	
	/**
	 * Crea una nueva instancia de la classe.
	 * @return Nueva instancia del <i>singletone</i> de la clase.
	 */
	public static ControladorDominioDiputado getInstance() {
	      if (instance == null) {
	         instance = new ControladorDominioDiputado();
	      }
	      return instance;
	}
	
	/**
	 * Consulta el número de diputados existentes en el sistema.
	 * @return Número de diputados del sistema.
	 */
	public Integer numeroDiputados(){
		return conjuntoDiputados.size();
	}
	
	/**
	 * Consulta los diputados existentes en el sistema.
	 * @return Conjunto de diputados del sistema.
	 */
	public Set<Diputado> getAll() {
		return conjuntoDiputados.getAll();
	}
	
	
	/**
	 * Limpia el conjunto del Controlador
	 */
	public void clear(){
		conjuntoDiputados.clear();
	}

	/**
	 * 
	 * @param nombreDiputado: nombre del Diputado
	 * @return devuelve la Votacion correspondiente del nombreDiputado
	 */
	public Diputado get(String nombreDiputado){
		return conjuntoDiputados.get(nombreDiputado);
	}
	
	/**
	 * Consulta los nombres de los diputados existentes en el sistema.
	 * @return Conjunto de nombres diputados del sistema.
	 */
	public Set<String> getNombres() {
		return conjuntoDiputados.getStringKeys();
	}
	
	/**
	 *  Introduce en el sistema un diputado a partir de sus datos.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param nombrePartido - Nombre del partido político al que pertenece el diputado.
	 * @param nombreEstado - Nombre del estado al que representa el diputado.
	 * @param fechaDeNacimiento - Fecha de nacimiento del diputado.
	 */
	public void addDiputado(String nombreDiputado, String nombrePartido, String nombreEstado, Date fechaDeNacimiento) {
		if (existsDiputado(nombreDiputado)) {
			error.setCodiError(4);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.add(new Diputado(nombreDiputado, nombrePartido, nombreEstado, fechaDeNacimiento));
	}

	/**
	 * Consulta si un cierto diputado existe en el sistema.
	 * @param nombreDiputado - Nombre del diputado.
	 * @return <i>true</i> si el diputado indicado existe en el sistema.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsDiputado(String nombreDiputado) {
		return conjuntoDiputados.exists(nombreDiputado);
	}
	
	/**
	 * Elimina del sistema al diputado indicado.
	 * <p>
	 * Este metodo tiene efectos colaterales en todas las estructuras del sistema que contengan informacion acerca del diputado indicado.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void removeDiputado(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else {
			ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
			CDL.removeDiputadoFromLegislaturas(nombreDiputado);
			catchError(CDL);
 			ControladorDominioResultado CDR = ControladorDominioResultado.getInstance();
			CDR.removeDiputado(nombreDiputado);
			catchError(CDR);
 			ControladorDominioEvento CDE = ControladorDominioEvento.getInstance();
			CDE.removeDiputado(nombreDiputado);
			catchError(CDE);
 			ControladorDominioVotacion CDV = ControladorDominioVotacion.getInstance();
			CDV.removeDiputado(nombreDiputado);
			catchError(CDV);
			if (!hasError()) {
				conjuntoDiputados.remove(nombreDiputado);
			}
		}
	}
	
	/**
	 * Modifica el partido político de un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param nombrePartido - Nombre del partido político al que pertenece el diputado.
	 */
	public void setPartidoPolitico(String nombreDiputado, String nombrePartido) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setPartidoPolitico(nombrePartido);
	}

	/**
	 * Modifica el estado al que representa un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param nombreEstado - Nombre del estado al que representa el diputado.
	 */
	public void setEstado(String nombreDiputado, String nombreEstado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setEstado(nombreEstado);
	}
	
	/**
	 * Modifica la fecha de nacimiento de un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param fechaDeNacimiento - Fecha de nacimiento del diputado.
	 */
	public void setFechaDeNacimiento(String nombreDiputado, Date FechaDeNacimiento) {
		if (!existsDiputado(nombreDiputado)){
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setFechaNacimiento(FechaDeNacimiento);
	}

	/**
	 * Consulta el partido político de un diputado.
 	 * @param nombreDiputado - Nombre del diputado.
	 * @return Nombre del partido político al que pertenece el diputado.
	 */
	public String getPartidoPolitico(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
			return "";
		}
		else return conjuntoDiputados.get(nombreDiputado).getPartidoPolitico();
	}
	
	/**
	 * Consulta el estado al que representa un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @return Nombre del estado al que representa el diputado.
	 */
	public String getEstado(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
			return "";
		}
		else return conjuntoDiputados.get(nombreDiputado).getEstado(); 
	}

	/**
	 * Consulta la fecha de nacimiento de un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @return Fecha de nacimiento del diputado.
	 */
	public Date getFechaDeNacimiento(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
			return Date.NULL;
		}
		else return conjuntoDiputados.get(nombreDiputado).getFechaDeNacimiento();
	}

	/**
	 * Añade una legislatura a la lista de legislaturas activas de un diputado.
	 * <p>
	 * Este metodo garantiza que el diputado será introducido (si no lo ha sido ya) en la lista de diputados activos de la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param identificadorLegislatura - Número que identifica la legislatura.
	 */
	public void addLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else if (!CDL.existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (existsLegistura(nombreDiputado, identificadorLegislatura)) {
			error.setCodiError(12);
			error.addClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.get(nombreDiputado).addLegistura(identificadorLegislatura);
			if (!CDL.existsDiputado(identificadorLegislatura, nombreDiputado))
				CDL.addDiputado(identificadorLegislatura, nombreDiputado);
			catchError(CDL);
		}
	}
	
	/**
	 * Consulta la lista de leguslaturas activas de un diputado.
	 * @param nombreDiputado - Nombre del diputado.
	 * @return Conjunto de identificadores de las legislaturas activas del diputado.
	 */
	public Set<Integer> getLegislaturas(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
			return new TreeSet<Integer>();
		}
		else return conjuntoDiputados.get(nombreDiputado).getLegislaturas();
	}
	
	/**
	 * Consulta si una legislatura pertenece a la lista de leguslaturas activas de un diputado.
	 * @param nombreDiputado - Nombre del diputado.
 	 * @param identificadorLegislatura - Número que identifica la legislatura.
	 * @return <i>true</i> si el diputado es activo en la legislatura.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean existsLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
			return false;
		}
		else return conjuntoDiputados.get(nombreDiputado).esActivo(identificadorLegislatura);
	}
	
	/**
	 * Elimina una legislatura de la lista de leguslaturas activas de un diputado.
	 * <p>
	 * Este metodo garantiza que el diputado será eliminado (si no lo ha sido ya) de la lista de diputados activos de la legislatura.
	 * @param nombreDiputado - Nombre del diputado.
	 * @param identificadorLegislatura - Número que identifica la legislatura.
	 */
	public void removeLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else if (!CDL.existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.addClauExterna(identificadorLegislatura);
		}
		else if (!existsLegistura(nombreDiputado, identificadorLegislatura)) {
			error.setCodiError(13);
			error.addClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.get(nombreDiputado).removeLegistura(identificadorLegislatura);
			if (CDL.existsDiputado(identificadorLegislatura, nombreDiputado))
				CDL.removeDiputado(identificadorLegislatura, nombreDiputado);
			catchError(CDL);
		}
	}
	
	/**
	 * Elimina todas las legislaturas de la lista de leguslaturas activas de un diputado.
	 * <p>
	 * Este metodo garantiza que todos el diputado será eliminado (si no lo ha sido ya) de las listas de diputados activos de todas las legislaturas.
	 * @param nombreDiputado - Nombre del diputado.
	 */
	public void removeLegisturas(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.get(nombreDiputado).removeLegisturas();
			ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
			CDL.removeDiputadoFromLegislaturas(nombreDiputado);
			catchError(CDL);
		}
	}
	
	/**
	 * Elimina una legislatura de las listas de leguslaturas activas de todos los diputados.
	 * <p>
	 * Este metodo garantiza que todos los diputados serán eliminados (si no lo han sido ya) de la listas de diputados activos de la legislatura.
	 * @param identificadorLegislatura - Número que identifica la legislatura.
	 */
	public void removeLegislaturaFromDiputados(Integer identificadorLegislatura) {
		for (Diputado D:conjuntoDiputados.getAll()) {
			String nombreDiputado = D.getNombre();
			if (existsLegistura(nombreDiputado, identificadorLegislatura))
				removeLegistura(nombreDiputado, identificadorLegislatura);
		}
	}
	
}