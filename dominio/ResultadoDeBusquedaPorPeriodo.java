package dominio;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResultadoDeBusquedaPorPeriodo extends ResultadoDeBusqueda {
	
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado,  Set<GrupoAfinPorPeriodo> gruposAfines) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
		gruposAfines = new HashSet<GrupoAfinPorPeriodo>(gruposAfines);
	}
	
}
