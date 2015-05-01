package dominio;

import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

import time.DateInterval;

public class ResultadoDeBusquedaPorPeriodo extends ResultadoDeBusqueda {
	
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, Set<GrupoAfin> gruposAfines) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado, periodo, gruposAfines);
	}
	
	public ResultadoDeBusquedaPorPeriodo(ResultadoDeBusquedaPorPeriodo R) {
		super(R);
		this.gruposAfines = new TreeSet<GrupoAfin>(R.gruposAfines);
	}

	@Override
	public String getTipoResultado() {
		return "Búsqueda por periodo";
	}
	
}
