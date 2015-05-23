package controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import time.DateInterval;
import utiles.ConjuntoGrupoAfin;
import dominio.*;
import dominio.algoritmos.Clique;
import dominio.algoritmos.Graf;
import dominio.algoritmos.GrafLouvain;
import dominio.algoritmos.GrafNewman;
import dominio.algoritmos.Louvain;
import dominio.algoritmos.Newman;

/**
 * Controlador encargado de calcular Afinidades entre los diputados del dominio. Haciendo uso de todos los datos disponibles en el dominio.
 * Este tipo de controlador depende directamente del Controlador de Resultados De B�squeda.
 * @author Yoel Cabo
 *
 */
public abstract class ControladorDominioBusqueda extends ControladorDominio {
	
	protected ControladorDominioDiputado cDip;
	protected ControladorDominioLegislatura cLeg;
	protected ControladorDominioEvento cEv;
	protected ControladorDominioVotacion cVot;
	
	protected ConjuntoGrupoAfin result; 

	
	/**
	 * Creadora por defecto.
	 */
	public ControladorDominioBusqueda() {
		cDip = ControladorDominioDiputado.getInstance();
		cLeg = ControladorDominioLegislatura.getInstance();
		cEv = ControladorDominioEvento.getInstance();
		cVot = ControladorDominioVotacion.getInstance();
	}
	
	public ConjuntoGrupoAfin getResult() {
		return result;
	}
 	
	protected Map<String, Integer> prepararImportancias(
			Map<String, Integer> importanciaModificada) {
		Map<String, Integer> res = new TreeMap<String,Integer>(); 
		res.putAll(importanciaModificada);
		Set<String> tipoEventos = cEv.getTipoEvento();
		if (catchError(cEv)) return null;
		for (String tipoEvento : tipoEventos) {
			if (!res.containsKey(tipoEvento)) {
				res.put(tipoEvento, cEv.getImportanciaTipoEvento(tipoEvento));
				if (catchError(cEv)) return null;

			}
		}
		return res;
	}

	protected Map<String, Set<String>> prepararEventos(DateInterval periodo) {
		Map<String, Set<String>> res = new TreeMap<String, Set<String>>(); 
		
		Set<String> tipoEventos = cEv.getTipoEvento();
		if (catchError(cEv)) return null;
		for (String tipoEvento : tipoEventos) {
			res.put(tipoEvento, cEv.getEventos(tipoEvento, periodo.getInicio(), periodo.getFin()));
			if (catchError(cEv)) return null;
		}
		return res;
	}

	protected Set<String> prepararDiputados(DateInterval Periodo) {
		Integer legislaturaInicio = cLeg.getNearID(Periodo.getInicio())[1];
		if (catchError(cLeg)) return null;
		Integer legislaturaFin = cLeg.getNearID(Periodo.getFin())[0];
		if (catchError(cLeg)) return null;
		//TODO if (legislaturaInicio == -1 || legislaturaFin == -1 ) ver qué hacemos;
		return prepararDiputados(legislaturaInicio, legislaturaFin);
	}
	
	/*protected Graf construirGrafoPP(Set<String> idDiputados) {
		Graf g = new Graf((HashSet<String>) idDiputados);
		Double peso = 5.0;
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				if (cDip.getPartidoPolitico(diputado1).equals(cDip.getPartidoPolitico(diputado2)) && !diputado1.equals(diputado2)) {
					if (catchError(cDip)) return null;
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		return g;
	}*/
	
	protected void addCriterioPartidoPolitico(Graf g, Double ponderacion) {
		Set<String> idDiputados = g.getNodes();
		Double peso = 5.0*ponderacion;
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				if (cDip.getPartidoPolitico(diputado1).equals(cDip.getPartidoPolitico(diputado2)) && !diputado1.equals(diputado2)) {
					if (catchError(cDip)) return;
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		
	}
	
	protected void addCriterioEstado(Graf g, Double ponderacion) {
		Set<String> idDiputados = g.getNodes();
		Double peso = 5.0*ponderacion;
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				if (cDip.getEstado(diputado1).equals(cDip.getEstado(diputado2)) && !diputado1.equals(diputado2)) {
					if (catchError(cDip)) return;
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		
	}
	
	/*protected Graf construirGrafoEstado(Set<String> idDiputados) {
		Graf g = new Graf((HashSet<String>) idDiputados);
		Double peso = 5.0;
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				if (cDip.getEstado(diputado1).equals(cDip.getEstado(diputado2)) && !diputado1.equals(diputado2)) {
					if (catchError(cDip)) return null;
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		return g;
	}*/
	
	protected void addCriterioNombresParecidos(Graf g, Double ponderacion) {
		Set<String> idDiputados = g.getNodes();
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				Double peso = 0.0;
				if (!diputado1.equals(diputado2)) peso = 100/((double) LevenshteinDistance(diputado1.toCharArray(), diputado2.toCharArray()));
				if (peso > 0.0) {
					peso *= ponderacion;
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
	}
	
	/*protected Graf construirGrafoNombresParecidos(Set<String> idDiputados) {
		Graf g = new Graf((HashSet<String>) idDiputados);
		for (String diputado1 : idDiputados) {
			for (String diputado2 : idDiputados) {
				Double peso = 0.0;
				if (!diputado1.equals(diputado2)) peso = parecidoStrings(diputado1, diputado2);
				if (peso > 0.0) {
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		return g;
	}*/
	
	protected static Double parecidoStrings(String diputado1, String diputado2) {
		Double res = 0.0;
		int largestlength = diputado1.length();
		int shortestlength = diputado2.length();
		if (shortestlength > largestlength) {
			int aux = shortestlength;
			shortestlength = largestlength;
			largestlength = aux;
		}
		for (int index = 0; index < diputado1.length(); ++index) {
			int index2 = diputado2.indexOf(diputado1.charAt(index));
			if (index2 > 0) res += largestlength - Math.abs(index2-index);
		}
		for (int index = 0; index < diputado2.length(); ++index) {
			int index2 = diputado1.indexOf(diputado2.charAt(index));
			if (index2 > 0) res += largestlength - Math.abs(index2-index);
		}
		res /= 2*shortestlength;
		return res*100;
	}


	protected Set<String> prepararDiputados(Integer legislaturaInicio,
			Integer legislaturaFin) {
		Set<String> res = new HashSet<String>();
		for (Integer i = legislaturaInicio; i <= legislaturaFin; ++i) {
			 if(cLeg.existsLegislatura(i)) res.addAll(cLeg.getDiputados(i));
			 if (catchError(cLeg)) return null;
		}
		return res;
	}

	
	protected Map<String, Set<String>> prepararVotaciones(DateInterval periodo) {
		Map<String, Set<String>> mapa = new TreeMap<String, Set<String>>();
		for (String votacion : cVot.getVotaciones(periodo.getInicio(), periodo.getFin())) {
			if (catchError(cVot)) return null;
			/*System.out.println(votacion);
			System.out.println(cVot.getDiputadosVotacion(votacion,TipoVoto.A_FAVOR));
			System.out.println(cVot.getDiputadosVotacion(votacion,TipoVoto.EN_CONTRA));*/
			mapa.put(votacion+"_A_FAVOR__", cVot.getDiputadosVotacion(votacion, TipoVoto.A_FAVOR));//Embellecer de cara a la tercera entrega
			mapa.put(votacion+"_EN_CONTRA", cVot.getDiputadosVotacion(votacion, TipoVoto.EN_CONTRA));
		}
		return mapa;
	}
	
	/*protected Graf construirGrafo(Set<String> idDiputados,
			Map<String, Integer> importancias,
			Map<String, Set<String>> tiposYeventos,
			Map<String, Set<String>> votacionesSimp) {
		Graf G = new Graf((HashSet<String>) idDiputados);
		for (String tipoEvento : tiposYeventos.keySet()) {
			for(String evento : tiposYeventos.get(tipoEvento)) {
				interrelacionar(G, cEv.getDiputadosEvento(tipoEvento, evento), (Double) importancias.get(tipoEvento).doubleValue());
				if (catchError(cEv)) return null;
			}
		}
		for (String votacionSimp : votacionesSimp.keySet()) {
			String votacion = votacionSimp.substring(0, votacionSimp.length()-10);
			//System.out.println(votacionSimp+" ---> "+votacion+ "Importancia: "+cVot.getImportanciaVotacion(votacion).toString());
			interrelacionar(G, votacionesSimp.get(votacionSimp), (Double) cVot.getImportanciaVotacion(votacion).doubleValue());
			if (catchError(cVot)) return null;
		}
		return G;
	}*/
	
	protected void addCriterioStandard(Graf G,
			Map<String, Integer> importancias,
			Map<String, Set<String>> tiposYeventos,
			Map<String, Set<String>> votacionesSimp, Double ponderacion) {
		for (String tipoEvento : tiposYeventos.keySet()) {
			for(String evento : tiposYeventos.get(tipoEvento)) {
				interrelacionar(G, cEv.getDiputadosEvento(tipoEvento, evento), (Double) importancias.get(tipoEvento).doubleValue()*ponderacion);
				if (catchError(cEv)) return;
			}
		}
		for (String votacionSimp : votacionesSimp.keySet()) {
			String votacion = votacionSimp.substring(0, votacionSimp.length()-10);
			//System.out.println(votacionSimp+" ---> "+votacion+ "Importancia: "+cVot.getImportanciaVotacion(votacion).toString());
			interrelacionar(G, votacionesSimp.get(votacionSimp), (Double) cVot.getImportanciaVotacion(votacion).doubleValue()*ponderacion);
			if (catchError(cVot)) return;
		}
		
	}
	
	protected static void interrelacionar(Graf g, Set<String> diputadosRelacionados,
			Double peso) {
		for (String diputado1 : diputadosRelacionados) {
			for (String diputado2 : diputadosRelacionados) {
				if (!diputado1.equals(diputado2) && g.existeixNode(diputado1) && g.existeixNode(diputado2)) {
					//System.out.println(diputado1+" "+diputado2);
					if (g.existeixAresta(diputado1, diputado2)) g.setPes(diputado1, diputado2, g.getPes(diputado1, diputado2)+peso/2);
					else g.addAresta(diputado1, diputado2, peso/2);
				}
			}
		}
		
	}


	protected static HashSet<HashSet<String>> ejecutar(Graf g, TipoAlgoritmo algoritmo,
			Integer porcentaje) {
		HashSet<HashSet<String>> hs = new HashSet<HashSet<String>>();
		if (algoritmo == TipoAlgoritmo.CliquePercolation) {
			hs = Clique.executa(g,porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.GirvanNewmann) {
			hs = Newman.executa(new GrafNewman(g),porcentaje);
		}
		else if (algoritmo == TipoAlgoritmo.Louvain) {
			hs = Louvain.executa(new GrafLouvain(g), porcentaje);
		}
		//else TODO ver qué hacemos
		return hs;
	}
	
	private int LevenshteinDistance(char s1[], char s2[]) {
		int d[][] = new int[s1.length+1][s2.length+1];
	 
	   for (int i = 0; i < s1.length; ++i) d[i][0] = i;
	   for (int j = 0; j < s2.length; ++j) d[0][j] = j;

	   for (int i = 1; i < s1.length; ++i) {
		   for (int j = 1; j < s2.length; ++j) {
	           int cost;
			   if (s1[i] == s2[j]) cost = 0;
	           else cost = 1;
	           d[i][j] = min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1] + cost );
		   }
	   }
	 
	   return d[s1.length][s2.length];
	}

	private int min(int i, int j, int k) {
		return min(i,min(j,k));
	}

	private int min(int i, int j) {
		if (i < j) return i;
		return j;
	}

}
