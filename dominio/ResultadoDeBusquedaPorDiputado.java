package dominio;

import java.util.Map;
import utiles.Conjunto;

public class ResultadoDeBusquedaPorDiputado extends ResultadoDeBusqueda {
	
	private String diputadoRelevante;
	private Integer lapsoDeTiempo;
	
	public ResultadoDeBusquedaPorDiputado(String nombre, Integer indiceAfinidad, TipoAlgoritmo algoritmo, Map<String, Integer> importancia, Boolean modificado, Integer lapsoDeTiempo, Conjunto<GrupoAfinPorDiputado> gruposAfines, String diputadoRelevante) {
			super(nombre, indiceAfinidad, algoritmo, importancia, modificado);
			this.gruposAfines = new Conjunto<GrupoAfin>(GrupoAfin.class);
			for (GrupoAfinPorDiputado grup:gruposAfines.getAll())
				this.gruposAfines.add(grup.getID(), grup);
			this.diputadoRelevante = diputadoRelevante;
	}
	
		@Override
	public String getTipoResultado() {
		return "Búsqueda por diputado";
	}

	public String getDiputadoRelevante() {
		return diputadoRelevante;
	}
	
	public String getLapsoDetiempo() {
		return lapsoDeTiempo.toString();
	}
	
}
