package controladores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import utiles.ImportLog;

public class ControladorDominioDatos extends ControladorDominio {
	
	private ImportLog log;
	
	private Boolean leyendo;
	
	private Set<String> ficheros;
	
	private static final String ficheroBase = "base.txt";
	
	private static final String rutaPersistencia = "save/";
	
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
			base.read(rutaPersistencia+ficheroBase);
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
		try {
			ControladorFichero f = new ControladorFichero();
			f.read(fichero);
			cargarDatos(f);
		}catch(FileNotFoundException e) {
			error = new CodiError(43);
			error.addClauExterna(fichero);
		}		
	}
	
	private void cargarDatos(ControladorFichero sf) throws FileFormatException {
		for (int i = 0; i < sf.size(); ++i) {
			for (int j = 0; j < sf.elementAt(i).size(); ++j) {
				StreamObject so = sf.elementAt(i, j);
				String nombre = so.getNombre();
				log.add(so.getNombre());
				log.add(so.elementAt(1));
				if(nombre.equals("Diputado")) {
					addDiputado(so);
					if (cDip.hasError()) log.addError(cDip.getError());
				}
				else if(nombre.equals("Votacion")) {
					addVotacion(so);
					if (cVot.hasError()) log.addError(cVot.getError());
				}
				else if(nombre.equals("TipoEvento")) {
					addTipoEvento(so);
					if (cEv.hasError()) log.addError(cEv.getError());
				}
				else if(nombre.equals("ResultadoPorDiputado")) {
					addResultadoPorDiputado(so);
					if (cRes.hasError()) log.addError(cRes.getError());
				}
				else if(nombre.equals("ResultadoPorPeriodo")) {
					addResultadoPorPeriodo(so);
					if (cRes.hasError()) log.addError(cRes.getError());
				}
				else if(nombre.equals("GrupoAfinPorPeriodo")) {
					addGrupoAfinPorPeriodo(so);
					if (cRes.hasError()) log.addError(cRes.getError());
				}
				else if(nombre.equals("GrupoAfinPorDiputado")) {
					addGrupoAfinPorDiputado(so);
					if (cRes.hasError()) log.addError(cRes.getError());
				}
				else if(nombre.equals("Evento")) {
					addEvento(so);
					if (cEv.hasError()) log.addError(cEv.getError());
				}
				else if(nombre.equals("Legislatura")) {
					addLegislatura(so);
					if (cEv.hasError()) log.addError(cLeg.getError());
				}
				else if(nombre.equals("Fichero")) {
					try {
						addFichero(so);
					}catch (FileFormatException e) {
						log.addError(new CodiError(42));
					}
				}
			}
		}
	}
	
	private void addLegislatura(StreamObject so) {
		Integer id = Integer.parseInt(so.elementAt(1));
		cLeg.addLegislatura(id, Date.parseDate(so.elementAt(2)), Date.parseDate(so.elementAt(3)));
		if (cLeg.hasError()) {
			CodiError err = cLeg.getError();
			if (err.getCodiError() == 16) {
				log.addW("La legislatura ya existia, se sobreescribe y se mezclan los diputados");
			}
			else log.addError(err);
		}
		for (String diputado : so.setAt(4)) {
			cLeg.addDiputado(id, diputado);
			if (cLeg.hasError()) {
				CodiError err = cLeg.getError();
				if (err.getCodiError() != 20) {
					log.addError(err);
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
	}

	private void addFichero(StreamObject fichero) throws FileFormatException {
		cargarFichero(fichero.elementAt(1));		
	}

	private void addEvento(StreamObject evento) {
		cEv.addEvento(evento.elementAt(2), evento.elementAt(1),  Date.parseDate(evento.elementAt(3)), evento.setAt(4));
		if(cEv.hasError()) {
			CodiError err = cEv.getError();
			if (err.getCodiError() == 15) {
				log.addW("El tipo de evento no existia, se ha creado");
				cEv.addTipoEvento(evento.elementAt(2), 1);
			}
			else if (err.getCodiError() == 8) {
				log.addW("El evento ya existia, se ha sobreescrito su fecha y mezclado los diputados");
				cEv.setFechaEvento(evento.elementAt(2), evento.elementAt(1),  Date.parseDate(evento.elementAt(3)));
				for (String diputado : evento.setAt(4)) {
					cEv.addDiputadoEvento(evento.elementAt(2), evento.elementAt(1), diputado);
					if (cEv.hasError()) {
						CodiError err2 = cEv.getError();
						if (err2.getCodiError() != 4) {
							log.addError(err2);
						}
					}
				}
			}
			else log.addError(err);
		}
	}

	private void addTipoEvento(StreamObject tipoEvento) {
		cEv.addTipoEvento(tipoEvento.elementAt(1), Integer.parseInt(tipoEvento.elementAt(2)));
		if (cEv.hasError()) {
			CodiError err = cEv.getError();
			if (err.getCodiError() == 14) {
				log.addW("Se ha sobreescrito.");
				cEv.setImportanciaTipoEvento(tipoEvento.elementAt(1), Integer.parseInt(tipoEvento.elementAt(2)));
			}
			else log.addError(err);
		}
	}

	private void addVotacion(StreamObject votacion) {
		Map<String,TipoVoto> map = new HashMap<String,TipoVoto>();
		String[] diputados = votacion.arrayAt(3);
		String[] votos = votacion.arrayAt(4);
		if (diputados.length != votos.length) {
			CodiError e = new CodiError(40);
			e.addClauExterna(votacion.getNombre());
			e.addClauExterna(votacion.elementAt(1));
			log.addError(e);
			return;
		}
		for (int i = 0; i < diputados.length; ++i) {
			map.put(diputados[i], stringToVoto(votos[i]));
		}
		cVot.addVotacion(votacion.elementAt(1), Date.parseDate(votacion.elementAt(2)), Integer.parseInt(votacion.elementAt(3)),map);	
	}

	private TipoVoto stringToVoto(String s) {
		if (s.equals("A_FAVOR")) return TipoVoto.A_FAVOR;
		if (s.equals("EN_CONTRA")) return TipoVoto.EN_CONTRA;
		if (s.equals("ABSTENCION")) return TipoVoto.ABSTENCION;
		if (s.equals("AUSENCIA")) return TipoVoto.AUSENCIA;
		log.addError(new CodiError(42));
		return null;
	}

	private void addDiputado(StreamObject diputado) {
		cDip.addDiputado(diputado.elementAt(1), diputado.elementAt(2), diputado.elementAt(3), Date.parseDate(diputado.elementAt(4)));
		if (cDip.hasError()) {
			CodiError err = cDip.getError();
			if (err.getCodiError() == 4) { 
				log.addW("Se ha sobreescrito.");
				cDip.setPartidoPolitico(diputado.elementAt(1), diputado.elementAt(2));
				cDip.setEstado(diputado.elementAt(1), diputado.elementAt(3));
				cDip.setFechaDeNacimiento(diputado.elementAt(1), Date.parseDate(diputado.elementAt(4)));
			}
			else log.addError(err);
		}

	}

	public void salvarDominio() {
		ficheros = new HashSet<String>();
		for (String fichero : ficheros) {
			ControladorFichero f = new ControladorFichero();
			f.read(fichero);
			salvarDatos(f);
			f.print(fichero);
		}
		changeBase();
		cargarDominio();
	}

	private void salvarDatos(ControladorFichero antiguo) {
		for (int i = 0; i < antiguo.size(); ++i) {
			//TODO ModificarCosillas
		}
	}
	
	public void leerFichero(String fichero) {
		leyendo = true;
		salvarDominio();
		log.clear();
		cargarFichero(fichero);
	}
	
	public void integrarFichero() {
		leyendo = false;
	}
	
	public void noIntegrarFichero() {
		limpiarDominio();
		cargarDominio();
		leyendo = false;
	}
	
	private void limpiarDominio() {
		cDip.clear();
		cLeg.clear();
		cEv.clear();
		cVot.clear();
		cRes.clear();
	}

	public void ImportarDatos(String fichero) {
		salvarDominio();
		cargarFichero(fichero);
	}
	
	public ImportLog getLog() {
		if (leyendo) return log;
		return null;
	}
	public ImportLog getLogForDebugging() {
		return log;
	}
	
}


/*
 * Necesitamos:
 * 
 * clear() en cada controlador.
 * 
 * Decidir políticas de Import (Qué hacer si ya existe lo que queremos añadir, se modifica o se descarta).
 *  
 * 
 * */
