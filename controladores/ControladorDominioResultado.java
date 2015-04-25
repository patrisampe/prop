package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dominio.ResultadoDeBusqueda;
import dominio.ResultadoDeBusquedaPorPeriodo;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.TipoAlgoritmo;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;
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
		Set<GrupoAfinPorPeriodo> resultado = new HashSet<GrupoAfinPorPeriodo>(); // Llamada a ControladorDeBusqueda.NuevaBusqueda()
		ultimoResultado = new ResultadoDeBusquedaPorPeriodo("Provisional", indiceAfinidad, algoritmo, importancia, false, resultado);
	}

	public void nuevoResultadoDeBusquedaPorDiputado(Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, DateInterval lapsoDeTiempo/*, Criterio criterio*/) {
		Set<GrupoAfinPorDiputado> resultado = new HashSet<GrupoAfinPorDiputado>(); // Llamada a ControladorDeBusqueda.NuevaBusqueda() 
		ultimoResultado = new ResultadoDeBusquedaPorDiputado("Provisional", indiceAfinidad, algoritmo, importancia, false, resultado);
	}
	
	public Boolean nombreDisponible(String nombre) {
		return conjuntoResultados.exists(nombre);
	}

	public void registraUltimoResultado(String nombre) {
		ultimoResultado.setNombre(nombre);
		conjuntoResultados.add(nombre, ultimoResultado);
	}
	
	
}
