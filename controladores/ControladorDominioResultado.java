package controladores;

import java.util.Map.Entry;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

import dominio.Criterio;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;
import dominio.ResultadoDeBusqueda;
import dominio.ResultadoDeBusquedaPorPeriodo;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.TipoAlgoritmo;
import time.*;
import utiles.CodiError;
import utiles.Conjunto;
import utiles.ConjuntoGrupoAfin;

/**
 * Controlador de dominio para la gestion tanto en conjunto como individualmente de los resultados de busqueda.
 * @version 1.0 Mayo 2015 
 * @author Miguel Angel Aranda
 */
public class ControladorDominioResultado extends ControladorDominio {
	
	/**
	 * Instancia <i>singletone</i> de la clase.
	 */
	private static ControladorDominioResultado instancia = null;
	
	/**
	 * Conjunto de resultados de busqueda almacenados en el sistema.
	 */
	private Conjunto<ResultadoDeBusqueda> conjuntoResultados;
	
	/**
	 * Ultimo resultado de busqueda sin almacenar en el sistema.
	 */
	private ResultadoDeBusqueda ultimoResultado;
	private ResultadoDeBusquedaPorDiputado ultimoResultadoDip;
	private ResultadoDeBusquedaPorPeriodo ultimoResultadoPer;
	/**
	 * Crea una nuevo controlador de dominio de resultados.
	 */
	private ControladorDominioResultado() {
		conjuntoResultados = new Conjunto<ResultadoDeBusqueda>(ResultadoDeBusqueda.class);
		ultimoResultado = null;
	}
	
	/**
	 * Crea una nueva instancia de la classe.
	 * @return Nueva instancia del <i>singletone</i> de la clase.
	 */
	public static ControladorDominioResultado getInstance() {
		if (instancia == null) instancia = new ControladorDominioResultado();
		return instancia;
	}
	
	/**
	 * Comprueba si un nombre esta disponible en el conjunto de resultados y en caso de no ser asi genera codigo de error.
	 * @param nombre - Nombre del cual se desea comprobar la disponibilidad.
	 * @return <i>true</i> si el nombre esta disponible en el conjunto.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	private Boolean nombreResultadoDisponible(String nombre) {
		if (conjuntoResultados.exists(nombre)) {
			error = new CodiError(32);
			error.addClauExterna(nombre);
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si existe un diputado en concreto y en caso de existir genera codigo de error.
	 * @param nombre - Nombre del diputado que se desea comprobar la existencia.
	 * @return <i>true</i> si el diputado existe en el sistema.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	private Boolean existeDiputado(String nombre) {
		ControladorDominioDiputado controladorD = ControladorDominioDiputado.getInstance();
		if (controladorD.existsDiputado(nombre)) {
			error = new CodiError(3);
			error.addClauExterna(nombre);
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba si existe un resultado de busqueda en el conjunto de resultados y en caso de no existir genera codigo de error.
	 * @param nombre - Nombre del resultado que se desea comprobar la existencia.
	 * @return <i>true</i> si el resultado de busqueda existe en el sistema.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	private Boolean existeNombreResultado(String nombre) {
		if (!conjuntoResultados.exists(nombre)) {
			error = new CodiError(31);
			error.addClauExterna(nombre);
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si existe un grupo afin en un resultado de busqueda en concreto y en caso de no existir genera codigo de error.
	 * @param nombreResultado - Nombre del resultado en el que se comprueba la existencia del grupo afin.
	 * @param ID - Identificador del grupo afin que se desea comprobar.
	 * @return <i>true</i> si el grupo afin existe en el resultado de busqueda.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	private Boolean existeGrupoAfin(String nombreResultado, Integer ID) {
		if (!conjuntoResultados.get(nombreResultado).existeGrupo(ID)) {
			error = new CodiError(31);
			error.addClauExterna(ID);
			error.addClauExterna(nombreResultado);
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si la ultima busqueda realizada es correcta.
	 * @return <i>true</i> si ultima busqueda es correcta.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	private Boolean ultimaBusquedaCorrecta() {
		if (ultimoResultado == null) {
			error = new CodiError(35);
			return false;
		}
		return true;
	}
	
	/**
	 * Limpia el conjunto del Controlador
	 */
	public void clear(){
		conjuntoResultados.clear();
	}
	/**
	 * 
	 * @return devuelve todo el contenido del controlador
	 */
	public Set<ResultadoDeBusqueda> getAll(){	
		return conjuntoResultados.getAll();
	}
	
	
	public Set<ResultadoDeBusquedaPorDiputado> getAllPorDiputado(){
		Set<ResultadoDeBusqueda> rdb= conjuntoResultados.getAll();
		Set<ResultadoDeBusquedaPorDiputado> rdbd= new TreeSet<ResultadoDeBusquedaPorDiputado>();
		for(ResultadoDeBusqueda elem:rdb){
			if(elem.getClass()==ResultadoDeBusquedaPorDiputado.class){
				ResultadoDeBusquedaPorDiputado aux=(ResultadoDeBusquedaPorDiputado)elem;
				rdbd.add(aux);
			}
		}
		return rdbd;
	}
	
	public Set<ResultadoDeBusquedaPorPeriodo> getAllPorPeriodo(){
		Set<ResultadoDeBusqueda> rdb= conjuntoResultados.getAll();
		Set<ResultadoDeBusquedaPorPeriodo> rdbp= new TreeSet<ResultadoDeBusquedaPorPeriodo>();
		for(ResultadoDeBusqueda elem:rdp){
			if(elem.getClass()==ResultadoDeBusquedaPorPeriodo.class){
				ResultadoDeBusquedaPorPeriodo aux=(ResultadoDeBusquedaPorPeriodo)elem;
				rdbp.add(aux);
			}
			
		}
		return rdbp;
	}
	
	/**
	 * 
	 * @param nombreResultado: nombre del nombreResultado
	 * @return devuelve Resultado correspondiente del nombreResultado
	 */
	public ResultadoDeBusqueda get(String nombreResultado){
		return conjuntoResultados.get(nombreResultado);
	}
	
	/**
	 * Crea un nuevo resultado de busqueda por periodo llamando al controlador del dominio de busqueda por periodo y lo almacena en ultimoResultado.
	 * @param indiceAfinidad - Valor del porcentaje de afinidad que se desea obtener en la busqueda.
	 * @param algoritmo - Algoritmo que se desea utilizar para realizar la busqueda.
	 * @param importancia - Importancia de todos los eventos del sistema.
	 * @param periodo - Intervalo entre dos fechas a tener en cuenta en la busqueda.
	 * @param criterio - Criterio relevante para la busqueda de grupos afines.
	 */
	public void nuevoResultadoPorPeriodo(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval periodo, Map<Criterio,Double> criterios) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) { 
			error = new CodiError(30);
		}
		else {
			ControladorDominioBusquedaPorPeriodo controlDomBus = new ControladorDominioBusquedaPorPeriodo();
			
			controlDomBus.nuevaBusqueda(periodo);
			if(catchError(controlDomBus)) return;
			for (Entry<Criterio, Double> criterio : criterios.entrySet()) {
				switch (criterio.getKey().hashCode()) {
					case 0:		controlDomBus.addCriterioStandard(importancia, criterio.getValue());break;
					case 1:		controlDomBus.addCriterioEstado(criterio.getValue());break;
					case 2:		controlDomBus.addCriterioPartidoPolitico(criterio.getValue());;break;
					case 3:		controlDomBus.addCriterioNombresParecidos(criterio.getValue());break;
					default:	break; //TODO quizá hacer saltar un error
				}
				if(catchError(controlDomBus)) return;
			}
			controlDomBus.ejecutar(algoritmo, indiceAfinidad);
			if(catchError(controlDomBus)) return;
			ConjuntoGrupoAfin resultado = controlDomBus.getResult();
			if(catchError(controlDomBus)) return;
			ultimoResultado = new ResultadoDeBusquedaPorPeriodo("Provisional", indiceAfinidad, algoritmo, importancia, false, periodo, resultado, criterios);
		}
	}

	/**
	 * Crea un nuevo resultado de busqueda por diputado llamando al controlador del dominio de busqueda por diputado y lo almacena en ultimoResultado.
	 * @param indiceAfinidad - Valor del porcentaje de afinidad que se desea obtener en la busqueda.
	 * @param algoritmo - Algoritmo que se desea utilizar para realizar la busqueda.
	 * @param importancia - Importancia de todos los eventos del sistema.
	 * @param lapsoDeTiempo - Cantidad de tiempo a tener en cuenta en la busqueda.
	 * @param diputadoRelevante - Diputado en torno al cual se desean encontrar comunidades.
	 * @param criterio - Criterio relevante para la busqueda de grupos afines.
	 */
	public void nuevoResultadoPorDiputado(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Integer lapsoDeTiempo, String diputadoRelevante, Map<Criterio,Double> criterios) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) {
			error = new CodiError(30);
		}
		else if (lapsoDeTiempo < 1) {
			error = new CodiError(34);
		}
		else if (existeDiputado(diputadoRelevante)) {
			ControladorDominioBusquedaPorDiputado controlDomBus = new ControladorDominioBusquedaPorDiputado();
			controlDomBus.NuevaBusqueda(lapsoDeTiempo);
			if(catchError(controlDomBus)) return;
			for (Entry<Criterio, Double> criterio : criterios.entrySet()) {
				switch (criterio.getKey().hashCode()) {
					case 0:		controlDomBus.addCriterioStandard(importancia, criterio.getValue());break;
					case 1:		controlDomBus.addCriterioEstado(criterio.getValue());break;
					case 2:		controlDomBus.addCriterioPartidoPolitico(criterio.getValue());;break;
					case 3:		controlDomBus.addCriterioNombresParecidos(criterio.getValue());break;
					default:	break; //TODO quizá hacer saltar un error
				}
				if(catchError(controlDomBus)) return;
			}
			controlDomBus.ejecutar(algoritmo, indiceAfinidad, diputadoRelevante);
			if(catchError(controlDomBus)) return;
			ConjuntoGrupoAfin resultado = controlDomBus.getResult();
			if(catchError(controlDomBus)) return;
			ultimoResultado = new ResultadoDeBusquedaPorDiputado("Provisional", indiceAfinidad, algoritmo, importancia, false, lapsoDeTiempo, resultado, diputadoRelevante, criterios);
		}
	}
	
	public void nuevoResultadoPorDiputadoSinBusqueda(String nombre,Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Integer lapsoDeTiempo, String diputadoRelevante, Map<Criterio,Double> criterios) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) {
			error = new CodiError(30);
		}
		else if (lapsoDeTiempo < 1) {
			error = new CodiError(34);
		}
		else if (existeDiputado(diputadoRelevante)) {
			ConjuntoGrupoAfin resultado = new ConjuntoGrupoAfin();
			ultimoResultadoDip = new ResultadoDeBusquedaPorDiputado(nombre, indiceAfinidad, algoritmo, importancia, false, lapsoDeTiempo, resultado, diputadoRelevante, criterios);
		}
	}
	
	public void addGrupoResultadoDiputados(String nombreRes,Integer ID, Date fechaInicio, Date fechaFin,TreeSet<String> dip) {
		GrupoAfinPorDiputado gad= new GrupoAfinPorDiputado(ID,fechaInicio,fechaFin); 
		ultimoResultadoDip.addGrupo(gad);
	}
	
	public void addUltimoResultadoDiputados(String nombreRes) {
		conjuntoResultados.add(ultimoResultadoDip);
	}
	

	public void nuevoResultadoPorPeriodoSinBusqueda(String nombreResultado,Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval periodo, Map<Criterio,Double> criterios) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) { 
			error = new CodiError(30);
		}
		else {
			ConjuntoGrupoAfin resultado = new ConjuntoGrupoAfin();
			ultimoResultadoPer = new ResultadoDeBusquedaPorPeriodo("Provisional", indiceAfinidad, algoritmo, importancia, false, periodo, resultado, criterios);
		}
	}
	
	public void addGrupoResultadoPeriodo(String nombreRes,Integer ID) {
		GrupoAfinPorPeriodo gap= new GrupoAfinPorPeriodo(ID); 
		ultimoResultadoPer.addGrupo(gap);
	}
	
	public void addUltimoResultadoPeriodo() {
		conjuntoResultados.add(ultimoResultadoPer);
	}
	/**
	 * Registra el resultado de la variable ultimoResultado en el conjunto de resultados para que quede registrado en el sistema.
	 * @param nombre - Nombre con el cual se registra el resultado.
	 */
	public void registraUltimoResultado(String nombre) {
		if (nombreResultadoDisponible(nombre) && ultimaBusquedaCorrecta()) {
			ultimoResultado.setNombre(nombre);
			conjuntoResultados.add(ultimoResultado);
		}
	}
	
	/**
	 * Cambia el nombre de un resultado del conjunto de resultados.
	 * @param nombreAnterior - Nombre que se desea modificar.
	 * @param nuevoNombre - Nombre que toma el resultado.
	 */
	public void cambiaNombre(String nombreAnterior, String nuevoNombre) {
		if (existeNombreResultado(nombreAnterior) && nombreResultadoDisponible(nuevoNombre)){
			ResultadoDeBusqueda aux = conjuntoResultados.get(nombreAnterior);
			aux.setNombre(nuevoNombre);
			conjuntoResultados.remove(nombreAnterior);
			conjuntoResultados.add(aux);
		}
	}
	
	/**
	 * Elimina un resultado en concreto del conjunto de resultados.
	 * @param nombre - Nombre del resultado que se desea eliminar.
	 */
	public void removeResultado(String nombre) {
		if (nombreResultadoDisponible(nombre))
			conjuntoResultados.remove(nombre);
	}
	
	/**
	 * Elimina un diputado de todos los grupos afines del sistema, en caso de quedar el grupo afin o el resultado vacio lo elimina respectivamente.
	 * @param nombre - Nombre del diputado que se eliminara.
	 */
	public void removeDiputado(String nombre) {
		if (existeDiputado(nombre)) {
			Set<String> resultados = conjuntoResultados.getStringKeys();
			for (String res:resultados) {
				if (conjuntoResultados.get(res).getDiputadoRelevante().equals(nombre))
					conjuntoResultados.remove(res);
				else
					conjuntoResultados.get(res).removeDiputado(nombre);
			}
		}
	}
	
	/**
	 * Agrega un diputado al grupo afin de un resultado en concreto.
	 * @param nombreResultado - Nombre del resultado al que se agrega el diputado.
	 * @param nombreDiputado - Diputado que se debe agregar.
	 * @param ID - Identificador del grupo afin al que se agrega el diputado.
	 */
	public void addDiputado(String nombreResultado, String nombreDiputado, Integer ID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, ID))
			conjuntoResultados.get(nombreResultado).addDiputado(nombreDiputado, ID);
	}
	
	/**
	 * Elimina un diputado del grupo afin de un resultado en concreto.
	 * @param nombreResultado - Nombre del resultado del que se elimina el diputado.
	 * @param nombreDiputado - Diputado que se debe eliminar.
	 * @param ID - Identificador del grupo afin en el que se elimina el diputado.
	 */
	public void removeDiputado(String nombreResultado, String nombreDiputado, Integer ID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, ID))
			conjuntoResultados.get(nombreResultado).removeDiputado(nombreDiputado, ID);
	}
	
	/**
	 * Mueve un diputado de un grupo afin a otro de un resultado en concreto.
	 * @param nombreResultado - Nombre del resultado en el que se mueve el diputado.
	 * @param nombreDiputado - Diputado que se debe mover.
	 * @param desdeID - Identificador del grupo afin del que se extrae el diputado.
	 * @param hastaID - Identificador del grupo afin al que se agrega el diputado.
	 */
	public void moveDiputado(String nombreResultado, String nombreDiputado, Integer desdeID, Integer hastaID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, desdeID) && existeGrupoAfin(nombreResultado, hastaID))
			conjuntoResultados.get(nombreResultado).moveDiputado(nombreDiputado, desdeID, hastaID);
	}
	
	/**
	 * Comprueba si existe un resultado en concreto.
	 * @param nombre - Nombre del resultado que se desea comprobar.
	 * @return <i>true</i> si el resultado existe en el conjunto de resultados.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	public Boolean existeResultado(String nombre) {
		return conjuntoResultados.exists(nombre);
	}

	/**
	 * Suministra un conjunto de cadenas de texto con el nombre de todos los resultados registrados en el sistema.
	 * @return Nombre de todos los resultados registrados.
	 */
	public Set<String> getNombreResultados() {
		return conjuntoResultados.getStringKeys();
	}
	
	/**
	 * Suministra una cadena de texto con el tipo al que pertenece un resultado en concreto.
	 * @return El tipo de resultado al que pertenece.
	 */
	public String getTipoDeResultado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getTipoResultado();
		else return null;
	}
	
	/**
	 * Comprueba si un resultado en concreto ha sido modificado.
	 * @param nombre - Nombre del resultado que se desea consultar.
	 * @return <i>true</i> si el resultado ha sido modificado.
	 * <br>
	 * <i>false</i> en cualquier otro caso..
	 */
	public Boolean haSidoModificado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).esModificado();
		else return null;
	}
	
	/**
	 * Suministra una cadena de texto con el indice de afinidad de un resultado en concreto.
	 * @return El indice de afinidad del resultado.
	 */
	public String getIndiceAfinidad(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getIndiceAfinidad().toString();
		else return null;
	}
	
	/**
	 * Suministra una cadena de texto con el tipo de algoritmo con el que se ha realizado la busqueda de grupos afines.
	 * @return El tipo de algoritmo empleado.
	 */
	public String getTipoAlgoritmo(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getAlgoritmo().toString();
		else return null;
	}
	
	/**
	 * Administra una cadena de texto con el periodo empleado para el resultado de una busqueda en concreto.
	 * @return El periodo al que pertenece el resultado.
	 */
	public String getPeriodo(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getPeriodo().toString();
		else return null;
	}
	
	/**
	 * Administra un conjunto de cadenas con los grupos afines de un resultado en concreto.
	 * @return Los diputados de cada grupo afin y el grupo afin de cada comunidad.
	 */
	public Vector<Set<String>> getResultado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getResultado();
		else return null;
	}
	
	/**
	 * Suministra el criterio empleado en la busqueda.
	 * @return Criterio de búsqueda.
	 */
	public String getCriterios(String name) {
		return conjuntoResultados.get(name).getCriterios().toString();
	}
	
	/**
	 * Suministra las importancias temporales definidas por el usuario.
	 * @return Devuelve las importancias temporales.
	 */
	public Map<String, Integer> getImportancias(String nombre) {
		return conjuntoResultados.get(nombre).getImportancias();
	}
	
	/**
	 * Suministra una cadena de texto con el algoritmo utilizado para la busqueda.
	 * @return algoritmo utilizado.
	 */
	public String getAlgoritmo(String nombre) {
		return conjuntoResultados.get(nombre).getAlgoritmo().toString();
	}
}
