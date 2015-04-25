package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import time.DateInterval;

public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	private Diputado diputadoRelevante;
	
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval lapsoDeTiempo, Set<GrupoAfinPorDiputado> gruposAfines) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado, lapsoDeTiempo);
			this.gruposAfines = new TreeSet<GrupoAfin>(gruposAfines);
	}
	
	public ResultadoDeBusquedaPorDiputado(ResultadoDeBusquedaPorDiputado R) {
		super(R);
		this.gruposAfines = new TreeSet<GrupoAfin>(R.gruposAfines);
	}
	
}
