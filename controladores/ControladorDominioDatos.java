package controladores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.FileChecksumException;
import exceptions.FileFormatException;
import persistencia.*;
import dominio.*;
import time.Date;
import utiles.CodiError;
import utiles.ImportLog;

public class ControladorDominioDatos extends ControladorDominio {
	
	private ImportLog log;
	
	private Boolean leyendo;
	
	private String fLeyendo;
	
	private Set<String> ficheros;
	
	private ArrayList<StreamObject> temp;
	
	private static final String ficheroBase = "base.txt";
	
	private static final String ficheroTemp = "temp.txt";
	
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
		} catch (FileFormatException e) {
			System.out.println("Error de formato en el fichero "+ficheroBase+". Procedemos a eliminarlo." );
			ControladorFichero.erase(rutaPersistencia+ficheroBase);
		} catch (FileChecksumException e) {
			System.out.println("Error de c en el fichero "+ficheroBase+". Procedemos a eliminarlo." );
			ControladorFichero.erase(rutaPersistencia+ficheroBase);
		}		
	}

	public static ControladorDominioDatos getInstance() {
		if (instance == null) instance = new ControladorDominioDatos();
		return instance;
	}
	
	public void cargarDominio() {
		limpiarDominio();
		for (String fichero : ficheros) {
			cargarFichero(rutaPersistencia+fichero);	
		}
	}
	
	private void cargarFichero(String fichero){
		try {
			ControladorFichero f = new ControladorFichero();
			f.read(fichero);
			cargarDatos(f);
		}catch(FileNotFoundException e) {
			error = new CodiError(43);
			error.addClauExterna(fichero);
		} catch (FileFormatException e) {
			error = new CodiError(41);
			error.addClauExterna(fichero);
		} catch (FileChecksumException e) {
			error = new CodiError(44);
			error.addClauExterna(fichero);
		}
	}
	
	private void cargarDatos(ControladorFichero sf) throws FileFormatException {
		for (int i = 0; i < sf.size(); ++i) { 
			for (int j = 0; j < sf.elementAt(i).size(); ++j) {
				log.add(i);
				log.add(j);
				StreamObject so = sf.elementAt(i, j);
				cargarDatos(so);
				log.ok();
			}
		}
	}
	
	private void cargarDatos(StreamObject so) {
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

	private void addLegislatura(StreamObject so) {
		Integer id = Integer.parseInt(so.elementAt(1));
		cLeg.addLegislatura(id, Date.parseDate(so.elementAt(2)), Date.parseDate(so.elementAt(3)));
		if (cLeg.hasError()) {
			CodiError err = cLeg.getError();
			if (err.getCodiError() == 16) {
				log.addW("La legislatura ya existia, se sobreescribe y se mezclan los diputados");
				cLeg.setFechaInicio(id, Date.parseDate(so.elementAt(2)));
				cLeg.setFechaFinal(id, Date.parseDate(so.elementAt(3)));
				if (cLeg.hasError()) {
					log.addError(cLeg.getError());
					return;
				}
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
		if (votacion.size() != 5) {
			CodiError e = new CodiError(40);
			e.addClauExterna(votacion.getNombre());
			e.addClauExterna(votacion.elementAt(1));
			log.addError(e);
			return;
		}
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
		if (cVot.hasError()) {
			CodiError err = cVot.getError();
			if (err.getCodiError() == 23) {
				cVot.setFechaVotacion(votacion.elementAt(1), Date.parseDate(votacion.elementAt(2)));
				cVot.setImportanciaVotacion(votacion.elementAt(1),Integer.parseInt(votacion.elementAt(3)));
				for (Entry<String,TipoVoto> e : map.entrySet()) {
					cVot.setAddVoto(votacion.elementAt(1), e.getKey(), e.getValue());
					if (cVot.hasError()) {
						log.addError(cVot.getError());
					}
				}
			}
		}
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
		int i = 0;
		int j = 0;
		StreamContainer sc = new StreamContainer("Dominio "+(++j));
		//TODO
		ControladorFichero sf = new ControladorFichero();
		
		updateBase();
	}
	
	private void updateBase() {
		StreamContainer sc = new StreamContainer("Rutas");
		for(String fichero : ficheros) {
			StreamObject so = new StreamObject("Fichero");
			so.add(fichero);
			sc.add(so);
		}
		readFicheros();
		base.clear();
		base.add(sc);
		base.print(rutaPersistencia+ficheroBase);
		for(String fichero : ficheros) {
			ControladorFichero.erase(rutaPersistencia+fichero);
		}
		cargarDominio();
		
		
	}

	public void leerFichero(String fichero) {
		leyendo = true;
		fLeyendo = fichero;
		salvarDominio();
		log.clear();
		cargarFichero(fLeyendo);
	}
	
	public Set<CodiError> integrarFichero(ArrayList<Integer> aIntegrar) {
		leyendo = false;
		ControladorFichero sf = new ControladorFichero();
		try {
			sf.read(fLeyendo);
		}catch(FileNotFoundException e) {
			error = new CodiError(43);
			error.addClauExterna(fLeyendo);
		} catch (FileFormatException e) {
			error = new CodiError(41);
			error.addClauExterna(fLeyendo);
		} catch (FileChecksumException e) {
			error = new CodiError(44);
			error.addClauExterna(fLeyendo);
		}
		recargarDominio();
		log.clearErrors();
		for (Integer inte : aIntegrar) {
			try {
				cargarDatos(sf.elementAt(log.getContainer(inte), log.getObject(inte)));
			} catch (FileFormatException e) {
				error = new CodiError(41);
				error.addClauExterna(fLeyendo);
				break;
			}
		}
		return validaImport();
	}
	
	private Set<CodiError> validaImport() {
		Set<CodiError> se = log.getErrors();
		if (this.hasError()) se.add(this.getError());
		if (!se.isEmpty()) {
			recargarDominio();
		}
		log.clear();
		return se;
	}

	public Set<CodiError> integrarFichero() {
		leyendo = false;
		return validaImport();
	}
	
	public void noIntegrarFichero() {
		recargarDominio();
		leyendo = false;
	}
	
	private void limpiarDominio() {
		cDip.clear();
		cLeg.clear();
		cEv.clear();
		cVot.clear();
		cRes.clear();
	}
	
	private void recargarDominio() {
		limpiarDominio();
		cargarDominio();
	}

	public Set<CodiError> importarDatos(String fichero) {
		log.clear();
		salvarDominio();
		cargarFichero(fichero);
		return validaImport();
	}
	
	public void exportarDatos(String fichero, ArrayList<ExportSet> l) {
		//TODO
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
 * read y print con parametro String
 * 
 * clear() en cada controlador.
 * 
 * empty() o getAll() en cada controlador.
 * 
 * Decidir políticas de Import (Qué hacer si ya existe lo que queremos añadir, se modifica o se descarta).
 *  
 * 
 * */
