package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import dominio.GrupoAfin;
import dominio.ResultadoDeBusqueda;
import dominio.ResultadoDeBusquedaPorPeriodo;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.TipoAlgoritmo;
import time.*;
import utiles.Conjunto;

public class ControladorDominioResultado {
	
	private static ControladorDominioResultado instancia = null;
	private Conjunto<ResultadoDeBusqueda> conjuntoResultados;
	private ResultadoDeBusqueda ultimoResultado;
	
	private ControladorDominioResultado() {}
	
	public static ControladorDominioResultado getInstancia() {
		if (instancia == null) instancia = new ControladorDominioResultado();
		return instancia;
	}
	
	public void nuevoResultadoDeBusquedaPorPeriodo(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval periodo/*, Criterio criterio*/) {
		Set<GrupoAfin> resultado = new HashSet<GrupoAfin>(); // Llamada a ControladorDeBusqueda.NuevaBusqueda()
		ultimoResultado = new ResultadoDeBusquedaPorPeriodo("Provisional", indiceAfinidad, algoritmo, importancia, false, periodo, resultado);
	}

	public void nuevoResultadoDeBusquedaPorDiputado(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval lapsoDeTiempo, String diputadoRelevante/*, Criterio criterio*/) {
		Set<GrupoAfin> resultado = new HashSet<GrupoAfin>(); // Llamada a ControladorDeBusqueda.NuevaBusqueda() 
		ultimoResultado = new ResultadoDeBusquedaPorDiputado("Provisional", indiceAfinidad, algoritmo, importancia, false, lapsoDeTiempo, resultado, diputadoRelevante);
	}
	
	public Boolean nombreDisponible(String nombre) {
		return conjuntoResultados.exists(nombre);
	}

	public void registraUltimoResultado(String nombre) {
		ultimoResultado.setNombre(nombre);
		conjuntoResultados.add(nombre, ultimoResultado);
	}
	
	public void cambiaNombre(String nombreAnterior, String nuevoNombre) {
		ResultadoDeBusqueda aux = conjuntoResultados.get(nombreAnterior);
		aux.setNombre(nuevoNombre);
		conjuntoResultados.remove(nombreAnterior);
		conjuntoResultados.add(nuevoNombre, aux);
	}
	
	public Set<String> getNombreResultados() {
		return conjuntoResultados.getStringKeys();
	}
	
	public String getTipoDeResultado(String nombre) {
		return conjuntoResultados.get(nombre).getTipoResultado();	
	}
	
	public Boolean haSidoModificado(String nombre) {
		return conjuntoResultados.get(nombre).esModificado();
	}
	
	public String getIndiceAfinidad(String nombre) {
		return conjuntoResultados.get(nombre).getIndiceAfinidad().toString();
	}
	
	public String getTipoAlgoritmo(String nombre) {
		return conjuntoResultados.get(nombre).getAlgoritmo().toString();
	}
	
	public String getPeriodo(String nombre) {
		return conjuntoResultados.get(nombre).getIntervalo().toString();
	}
	
	public Set<Set<String>> getResultado(String nombre) {
		return conjuntoResultados.get(nombre).getResultado();
	}
	
	public void removeResultado(String nombre) {
		conjuntoResultados.remove(nombre);
	}
	
}
