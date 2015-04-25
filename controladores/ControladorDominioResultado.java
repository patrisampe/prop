package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

	public void nuevoResultadoDeBusquedaPorDiputado(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval lapsoDeTiempo/*, Criterio criterio*/) {
		Set<GrupoAfinPorDiputado> resultado = new HashSet<GrupoAfinPorDiputado>(); // Llamada a ControladorDeBusqueda.NuevaBusqueda() 
		ultimoResultado = new ResultadoDeBusquedaPorDiputado("Provisional", indiceAfinidad, algoritmo, importancia, false, lapsoDeTiempo, resultado);
	}
	
	public Boolean nombreDisponible(String nombre) {
		return conjuntoResultados.exists(nombre);
	}

	public void registraUltimoResultado(String nombre) {
		ultimoResultado.setNombre(nombre);
		conjuntoResultados.add(nombre, ultimoResultado);
	}
	
	public Set<String> getNombreResultados() {
		return conjuntoResultados.getStringKeys();
	}
	
	public String getTipoDeResultado(String nombre) {
		if (conjuntoResultados.get(nombre) instanceof ResultadoDeBusquedaPorDiputado) 
			return "Resultado de búsqueda por diputado";
		else
			return "Resultado de búsqueda por periodo";	
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
	
	public TreeSet<TreeSet<String>> getResultado(String nombre) {
		TreeSet<TreeSet<String>> listaResultado = new TreeSet<TreeSet<String>>();
		TreeSet<GrupoAfin>
		
		return listaResultado;
	}
	
}
