package dominio;

import java.util.Map;
import java.util.Set;

import time.DateInterval;

public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	private Diputado diputadoRelevante;
	
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval lapsoDeTiempo, Set<GrupoAfin> gruposAfines) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado, lapsoDeTiempo, gruposAfines);
	}
	
}
