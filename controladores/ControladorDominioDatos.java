package controladores;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exceptions.FileFormatException;
import persistencia.*;
import dominio.*;
import time.Date;
import utiles.CodiError;

public class ControladorDominioDatos extends ControladorDominio {
	
	private Set<String> ficheros;
	
	private static final String ficheroBase = "base.txt";
	
	private static ControladorFichero base;
	
	private static ControladorDominioDatos instance = null;
	
	private ControladorDominioDiputado cDip;
	private ControladorDominioLegislatura cLeg;
	private ControladorDominioEvento cEv;
	private ControladorDominioVotacion cVot;
	private ControladorDominioResultado cRes;
	
	private ControladorDominioDatos() {
		super();
		readFicheros();
		cDip = ControladorDominioDiputado.getInstance();
		cEv = ControladorDominioEvento.getInstance();
		cLeg = ControladorDominioLegislatura.getInstance();
		cVot = ControladorDominioVotacion.getInstance();
		cRes = ControladorDominioResultado.getInstance();
	}
	
	private void readFicheros() {
		ficheros = new HashSet<String>();
		base = new ControladorFichero();
		try {
			base.read(ficheroBase);
			for (int i = 0; i < base.size(); ++i) {
				ficheros.add(base.elementAt(1, i, 1));
			}
		}catch(FileNotFoundException e) {
			System.out.println("No se encuentra el fichero "+ficheroBase+". Procedemos a crearlo." );
		}		
	}

	public static ControladorDominioDatos getInstance() {
		if (instance == null) instance = new ControladorDominioDatos();
		return instance;
	}
	
	public void cargarDominio() {
		limpiarDominio();
		for (String fichero : ficheros) {
			cargarFichero(fichero);	
		}
	}
	
	private void cargarFichero(String fichero) {
		ControladorFichero f = new ControladorFichero();
		f.read(fichero);
		cargarDatos(f);
	}
	
	private void cargarDatos(ControladorFichero sf) throws FileFormatException {
		for (int i = 0; i < sf.size(); ++i) {
			for (int j = 0; j < sf.elementAt(i).size(); ++j) {
				StreamObject so = sf.elementAt(i, j);
				String nombre = sf.elementAt(i, j).getNombre();
				if(nombre.equals("Diputado")) {
					addDiputado(so);
					if (cDip.hasError()) addToLog(cDip.getError());
				}
				else if(nombre.equals("Votacion")) {
					addVotacion(so);
					if (cVot.hasError()) addToLog(cVot.getError());
				}
				else if(nombre.equals("TipoEvento")) {
					addTipoEvento(so);
					if (cEv.hasError()) addToLog(cEv.getError());
				}
				else if(nombre.equals("ResultadoPorDiputado")) {
					addResultadoPorDiputado(so);
					if (cRes.hasError()) addToLog(cRes.getError());
				}
				else if(nombre.equals("ResultadoPorPeriodo")) {
					addResultadoPorPeriodo(so);
					if (cRes.hasError()) addToLog(cRes.getError());
				}
				else if(nombre.equals("GrupoAfinPorPeriodo")) {
					addGrupoAfinPorPeriodo(so);
					if (cRes.hasError()) addToLog(cRes.getError());
				}
				else if(nombre.equals("GrupoAfinPorDiputado")) {
					addGrupoAfinPorDiputado(so);
					if (cRes.hasError()) addToLog(cRes.getError());
				}
				else if(nombre.equals("Evento")) {
					addEvento(so);
					if (cEv.hasError()) addToLog(cEv.getError());
				}
				else if(nombre.equals("Fichero")) {
					try {
						addFichero(so);
					}catch (FileFormatException e) {
						addToLog();
					}
				}
			}
		}
	}
	
	private void addGrupoAfinPorDiputado(StreamObject so) {
		// TODO Auto-generated method stub
		
	}

	private void addGrupoAfinPorPeriodo(StreamObject so) {
		// TODO Auto-generated method stub
		
	}

	private void addResultadoPorPeriodo(StreamObject so) {
		// TODO Auto-generated method stub
		
	}

	private void addResultadoPorDiputado(StreamObject so) {
		// TODO Auto-generated method stub
		cRes.addResultadoPorDiputado(nombre, indiceAfinidad, algoritmo, importancia, lapsoDeTiempo, diputadoRelevante, modificado, criterios);	
		cRes.ge
	}

	private void addToLog(CodiError error) {
		// TODO Auto-generated method stub
	}

	private void addFichero(StreamObject fichero) throws FileFormatException {
		cargarFichero(fichero.elementAt(1));		
	}

	private void addEvento(StreamObject evento) {
		cEv.addEvento(evento.elementAt(2), evento.elementAt(1),  Date.parseDate(evento.elementAt(3)), evento.setAt(4));
	}

	private void addTipoEvento(StreamObject tipoEvento) {
		cEv.addTipoEvento(tipoEvento.elementAt(1), Integer.parseInt(tipoEvento.elementAt(2)));
	}

	private void addVotacion(StreamObject votacion) {
		Map<String,TipoVoto> map = new HashMap<String,TipoVoto>();
		Set<String> diputados = votacion.setAt(3);
		Set<String> votos = votacion.setAt(4);
		if (diputados.size() != votos.size()) {
			CodiError e = new CodiError(40);
			e.addClauExterna(votacion.getNombre());
			e.addClauExterna(votacion.elementAt(1));
			addToLog(e);
			return;
		}
		Iterator<String> diputado = diputados.iterator();
		Iterator<String> voto = votos.iterator();
		while(diputado.hasNext()) {
			map.put(diputado.next(), stringToVoto(voto.next()));
		}
		cVot.addVotacion(votacion.elementAt(1), Date.parseDate(votacion.elementAt(2)), Integer.parseInt(votacion.elementAt(3)),map);	
	}

	private static TipoVoto stringToVoto(String s) {
		if (s.equals("A_FAVOR")) return TipoVoto.A_FAVOR;
		if (s.equals("EN_CONTRA")) return TipoVoto.EN_CONTRA;
		if (s.equals("ABSTENCION")) return TipoVoto.ABSTENCION;
		if (s.equals("AUSENCIA")) return TipoVoto.AUSENCIA;
		return null;
	}

	private void addDiputado(StreamObject diputado) {
		cDip.addDiputado(diputado.elementAt(1), diputado.elementAt(2), diputado.elementAt(3), Date.parseDate(diputado.elementAt(4)));
	}

	public void salvarDominio() {
		for (String fichero : ficheros) {
			ControladorFichero f = new ControladorFichero();
			f.read(fichero);
			salvarDatos(f);
			f.print(fichero);
		}
		cargarDominio();
	}

	private void salvarDatos(ControladorFichero antiguo) {
		for (int i = 0; i < antiguo.size(); ++i) {
			//TODO ModificarCosillas
		}
	}
	
	public void leerFichero(String fichero) {
		ControladorFichero f = new ControladorFichero();
		f.read(fichero);
		cargarDatos(f);
	}
	
	public void integrarFichero() {
		//pues no hace nada
	}
	
	public void noIntegrarFichero() {
		limpiarDominio();
		cargarDominio();
	}
	
	private void limpiarDominio() {
		cDip.clear();
		cLeg.clear();
		cEv.clear();
		cVot.clear();
		cRes.clear();
	}

	public void ImportarDatos(String fichero) {
		cargarFichero(fichero);
	}
	
}


/*
 * Necesitamos:
 * 
 * clear() en cada controlador.
 * static getSecondaryInstance()
 * static copyToSecondaryInstance()
 * static copyToPrimaryInstance()
 * 
 * Decidir políticas de Import (Qué hacer si ya existe lo que queremos añadir, se modifica o se descarta).
 *  
 * 
 * */
