package controladores;

import java.util.Set;
import java.util.Map;

import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;
import dominio.ResultadoDeBusqueda;
import dominio.ResultadoDeBusquedaPorPeriodo;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.TipoAlgoritmo;
import time.*;
import utiles.CodiError;
import utiles.Conjunto;

public class ControladorDominioResultado {
	
	private static ControladorDominioResultado instancia = null;
	private Conjunto<ResultadoDeBusqueda> conjuntoResultados;
	private ResultadoDeBusqueda ultimoResultado;
	private CodiError error;
	private Boolean hasError;

	private ControladorDominioResultado() {
		conjuntoResultados = new Conjunto<ResultadoDeBusqueda>(ResultadoDeBusqueda.class);
		ultimoResultado = null;
		hasError = false;
	}
	
	//Creadores
	public static ControladorDominioResultado getInstance() {
		if (instancia == null) instancia = new ControladorDominioResultado();
		return instancia;
	}
	
	private Boolean nombreResultadoDisponible(String nombre) {
		if (conjuntoResultados.exists(nombre)) {
			hasError = true;
			error = new CodiError(32);
			error.setClauExterna(nombre);
			return false;
		}
		hasError = false;
		return true;
	}
	
	private Boolean existeDiputado(String nombre) {
		ControladorDominioDiputado controladorD = ControladorDominioDiputado.getInstance();
		if (controladorD.existsDiputado(nombre)) {
			hasError = true;
			error = new CodiError(3);
			error.setClauExterna(nombre);
			return true;
		}
		hasError = false;
		return false;
	}
	
	private Boolean existeNombreResultado(String nombre) {
		if (!conjuntoResultados.exists(nombre)) {
			hasError = true;
			error = new CodiError(31);
			error.setClauExterna(nombre);
			return false;
		}
		hasError = false;
		return true;
	}
	
	private Boolean existeGrupoAfin(String nombreResultado, Integer ID) {
		if (!conjuntoResultados.get(nombreResultado).existeGrupo(ID)) {
			hasError = true;
			error = new CodiError(31);
			error.setClauExterna(ID);
			error.addClauExterna(nombreResultado);
			return false;
		}
		hasError = false;
		return true;
	}
	
	//Modificadores
	public void nuevoResultadoPorPeriodo(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval periodo/*, Criterio criterio*/) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) { 
			hasError = true;
			error = new CodiError(30);
		}
		else {
			ControladorDominioBusquedaPorPeriodo controlDomBus = new ControladorDominioBusquedaPorPeriodo();
			Conjunto<GrupoAfinPorPeriodo> resultado = controlDomBus.NuevaBusquedaStandard(algoritmo, periodo, importancia, indiceAfinidad);
			ultimoResultado = new ResultadoDeBusquedaPorPeriodo("Provisional", indiceAfinidad, algoritmo, importancia, false, periodo, resultado);
		}
	}

	public void nuevoResultadoPorDiputado(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Integer lapsoDeTiempo, String diputadoRelevante/*, Criterio criterio*/) {
		if (indiceAfinidad < 0 || indiceAfinidad > 100) {
			hasError = true;
			error = new CodiError(30);
		}
		else if (existeDiputado(diputadoRelevante)) {
			ControladorDominioBusquedaPorDiputado controlDomBus = new ControladorDominioBusquedaPorDiputado();
			Conjunto<GrupoAfinPorDiputado> resultado = controlDomBus.NuevaBusquedaStandard(algoritmo, lapsoDeTiempo, importancia, indiceAfinidad, diputadoRelevante);
			ultimoResultado = new ResultadoDeBusquedaPorDiputado("Provisional", indiceAfinidad, algoritmo, importancia, false, lapsoDeTiempo, resultado, diputadoRelevante);
		}
	}
	
	public void registraUltimoResultado(String nombre) {
		if (nombreResultadoDisponible(nombre)) {
			ultimoResultado.setNombre(nombre);
			conjuntoResultados.add(nombre, ultimoResultado);
		}
	}
	
	public void cambiaNombre(String nombreAnterior, String nuevoNombre) {
		if (existeNombreResultado(nombreAnterior) && nombreResultadoDisponible(nuevoNombre)){
			ResultadoDeBusqueda aux = conjuntoResultados.get(nombreAnterior);
			aux.setNombre(nuevoNombre);
			conjuntoResultados.remove(nombreAnterior);
			conjuntoResultados.add(nuevoNombre, aux);
		}
	}
	
	public void removeResultado(String nombre) {
		if (nombreResultadoDisponible(nombre))
			conjuntoResultados.remove(nombre);
	}
	
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
	
	public void addDiputado(String nombreResultado, String nombreDiputado, Integer ID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, ID))
			conjuntoResultados.get(nombreResultado).addDiputado(nombreDiputado, ID);
	}
	
	public void removeDiputado(String nombreResultado, String nombreDiputado, Integer ID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, ID))
			conjuntoResultados.get(nombreResultado).removeDiputado(nombreDiputado, ID);
	}
	
	public void moveDiputado(String nombreResultado, String nombreDiputado, Integer desdeID, Integer hastaID) {
		if (existeNombreResultado(nombreResultado) && existeDiputado(nombreDiputado) && existeGrupoAfin(nombreResultado, desdeID) && existeGrupoAfin(nombreResultado, hastaID))
			conjuntoResultados.get(nombreResultado).moveDiputado(nombreDiputado, desdeID, hastaID);
	}
	
	//Consultores
	public Boolean existeResultado(String nombre) {
		return conjuntoResultados.exists(nombre);
	}

	public Set<String> getNombreResultados() {
		return conjuntoResultados.getStringKeys();
	}
	
	public String getTipoDeResultado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getTipoResultado();
		else return null;
	}
	
	public Boolean haSidoModificado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).esModificado();
		else return null;
	}
	
	public String getIndiceAfinidad(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getIndiceAfinidad().toString();
		else return null;
	}
	
	public String getTipoAlgoritmo(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getAlgoritmo().toString();
		else return null;
	}
	
	public String getPeriodo(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getPeriodo().toString();
		else return null;
	}
	
	public Set<Set<String>> getResultado(String nombre) {
		if (existeNombreResultado(nombre))
			return conjuntoResultados.get(nombre).getResultado();
		else return null;
	}
	
	public Boolean getHasError() {
		return hasError;
	}
	   
	public CodiError getError(){
		return error;
	}
	
}
