package dominio;

import java.util.Map;
import time.DateInterval;
import utiles.Conjunto;

public class ResultadoDeBusquedaPorPeriodo extends ResultadoDeBusqueda {
	
	private DateInterval periodo;
	
	public ResultadoDeBusquedaPorPeriodo(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, DateInterval periodo, Conjunto<GrupoAfinPorPeriodo> gruposAfines) {
		super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
		this.gruposAfines = new Conjunto<GrupoAfin>(GrupoAfin.class);
		for (GrupoAfinPorPeriodo grup:gruposAfines.getAll())
			this.gruposAfines.add(grup.getID(), grup);
		this.periodo = new DateInterval(periodo);
	}
	
		@Override
	public String getTipoResultado() {
		return "Búsqueda por periodo";
	}

	public String getPeriodo() {
		return periodo.toString();
	}
	
}
