package dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import time.DateInterval;

public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	private String diputadoRelevante;
	
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval lapsoDeTiempo, Set<GrupoAfin> gruposAfines, String diputadoRelevante) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado, lapsoDeTiempo, gruposAfines);
			this.diputadoRelevante = diputadoRelevante;
	}
	
	public ResultadoDeBusquedaPorDiputado(ResultadoDeBusquedaPorDiputado R) {
		super(R);
		this.gruposAfines = new TreeSet<GrupoAfin>(R.gruposAfines);
		this.diputadoRelevante = R.diputadoRelevante;
	}

	@Override
	public String getTipoResultado() {
		return "Búsqueda por diputado";
	}

	@Override
	public String getDiputadoRelevante() {
		return diputadoRelevante;
	}
	
}
