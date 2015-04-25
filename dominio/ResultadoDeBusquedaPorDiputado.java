package dominio;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado,  Set<GrupoAfinPorDiputado> gruposAfines) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
		gruposAfines = new HashSet<GrupoAfinPorDiputado>(gruposAfines);
	}
	
}
