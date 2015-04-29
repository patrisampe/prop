package controladores;

import java.util.Map;
import java.util.Set;

import time.DateInterval;
import dominio.GrupoAfinPorDiputado;
import dominio.Legislatura;
import dominio.TipoAlgoritmo;
import dominio.algoritmos.Graf;

public class ControladorDominioBusquedaPorDiputado extends
		ControladorDominioBusqueda {
	public Set<GrupoAfinPorDiputado> NuevaBusquedaStandard(TipoAlgoritmo Algoritmo, Integer Lapso, Map<String, Integer> ImportanciaModificada, Integer porcentaje, String DiputadoRelevante) {
		Set<Legislatura> legislaturas = cLeg.getAll();
		Map<String,Integer> importancias = prepararImportancias(ImportanciaModificada);
		
		for () {
			Map<String, Set<String> > tiposYeventos = prepararEventos(Periodo); //En el esquema pone list
			Map<String, Set<String> > votacionesSimp = prepararVotaciones(Periodo); //Divide las votaciones en conjuntos de diputados que votan lo mismo.
			Graf G = construirGrafo(idDiputados,importancias,tiposYeventos,votacionesSimp);
		}
		
		return null;
		
		
	}
}
