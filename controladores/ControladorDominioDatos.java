package controladores;

import io.Entrada;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.FileChecksumException;
import exceptions.FileFormatException;
import persistencia.*;
import dominio.*;
import time.Date;
import time.DateInterval;
import utiles.CodiError;
import utiles.ExportSet;
import utiles.ImportLog;

/**
 * 
 * @author Yoel Cabo
 *
 */
public class ControladorDominioDatos extends ControladorDominio {
	
	private ImportLog log;
	
	private Boolean leyendo;
	
	private String fLeyendo;
	
	private Set<String> ficheros;
	
	private ArrayList<StreamObject> temp;
	
	private static final String ext = ".ciof";
	
	private static final String ficheroBase = "base"+ext;
	
	private static final String rutaPersistencia = "save/";
	
	private static final Integer ficheroSize = 10000;
	
	private static final Integer lineaSize = 300;
	
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
			log.add(so.elementAt(2));
			addGrupoAfinPorPeriodo(so);
			if (cRes.hasError()) log.addError(cRes.getError());
		}
		else if(nombre.equals("GrupoAfinPorDiputado")) {
			log.add(so.elementAt(2));
			addGrupoAfinPorDiputado(so);
			if (cRes.hasError()) log.addError(cRes.getError());
		}
		else if(nombre.equals("Evento")) {
			log.add(so.elementAt(2));
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
		cRes.addGrupoResultadoDiputados(so.elementAt(2), Integer.parseInt(so.elementAt(1)), Date.parseDate(so.elementAt(3)),  Date.parseDate(so.elementAt(4)), so.setAt(5));
		if (cRes.hasError()) log.addError(cRes.getError());

	}

	private void addGrupoAfinPorPeriodo(StreamObject so) {
		cRes.addGrupoResultadoPeriodo(so.elementAt(2), Integer.parseInt(so.elementAt(1)), so.setAt(3));
		if (cRes.hasError()) log.addError(cRes.getError());
	}

	private void addResultadoPorPeriodo(StreamObject so) {
		Map<String,Integer> importancias = new LinkedHashMap<String,Integer>();
		Map<Criterio,Double> criterios = new LinkedHashMap<Criterio,Double>();
		String[] impKey = so.arrayAt(4);
		String[] impValue = so.arrayAt(5);
		String[] critKey = so.arrayAt(7);
		String[] critValue = so.arrayAt(8);
		if (impKey.length != impValue.length || critKey.length != critValue.length) {
			this.error = new CodiError(42);
			return;
		}
		for (int i = 0; i < impKey.length; ++i) {
			importancias.put(impKey[i], Integer.parseInt(impValue[i]));
		}
		for (int i = 0; i < critKey.length; ++i) {
			criterios.put(Criterio.valueOf(critKey[i]), Double.parseDouble(critValue[i]));
		}
		cRes.nuevoResultadoPorPeriodoSinBusqueda(so.elementAt(1), Integer.parseInt(so.elementAt(2)), TipoAlgoritmo.valueOf(so.elementAt(3)), importancias, DateInterval.parseDateInterval(so.elementAt(6)), criterios);
		if (cRes.hasError()) log.addError(cRes.getError());
	}

	private void addResultadoPorDiputado(StreamObject so) {
		Map<String,Integer> importancias = new LinkedHashMap<String,Integer>();
		Map<Criterio,Double> criterios = new LinkedHashMap<Criterio,Double>();
		String[] impKey = so.arrayAt(4);
		String[] impValue = so.arrayAt(5);
		String[] critKey = so.arrayAt(8);
		String[] critValue = so.arrayAt(9);
		if (impKey.length != impValue.length || critKey.length != critValue.length) {
			this.error = new CodiError(42);
		}
		for (int i = 0; i < impKey.length; ++i) {
			importancias.put(impKey[i], Integer.parseInt(impValue[i]));
		}
		for (int i = 0; i < critKey.length; ++i) {
			criterios.put(Criterio.valueOf(critKey[i]), Double.parseDouble(critValue[i]));
		}
		cRes.nuevoResultadoPorDiputadoSinBusqueda(so.elementAt(1), Integer.parseInt(so.elementAt(2)), TipoAlgoritmo.valueOf(so.elementAt(3)), importancias, Integer.parseInt(so.elementAt(6)), so.elementAt(7), criterios);
		if (cRes.hasError()) log.addError(cRes.getError());
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
		Integer i = 0;
		Integer j = 0;
		ControladorFichero sf = new ControladorFichero();
		StreamContainer sc = new StreamContainer("Dominio"+(++j));
		for (Diputado d : cDip.getAll()) {
			sc.add(encode(d));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
		}
		
		for (Legislatura l : cLeg.getAll()) {
			sc.add(encode(l));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
		}
		
		for (TipoEvento t : cEv.getAll()) {
			sc.add(encode(t));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
			for (Evento e : t.getEventos()) {
				sc.add(encode(e, t.getNombre()));
				adjust(i,j,sc,sf);
				if (this.hasError()) return;
			}
		}
		
		for (Votacion v : cVot.getAll()) {
			sc.add(encode(v));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
		}
		
		for (ResultadoDeBusquedaPorDiputado r : cRes.getAllPorDiputado()) {
			sc.add(encode(r));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
			for (GrupoAfinPorDiputado g : r.getGruposAfinesPorDiputado()) {
				sc.add(encode(g, r.getNombre()));
				adjust(i,j,sc,sf);
				if (this.hasError()) return;
			}
		}
		
		for (ResultadoDeBusquedaPorPeriodo r : cRes.getAllPorPeriodo()) {
			sc.add(encode(r));
			adjust(i,j,sc,sf);
			if (this.hasError()) return;
			for (GrupoAfinPorPeriodo g : r.getGruposAfinesPorPeriodo()) {
				sc.add(encode(g, r.getNombre()));
				adjust(i,j,sc,sf);
				if (this.hasError()) return;
			}
		}
		updateBase();
	}
	
	private StreamObject encode(ResultadoDeBusquedaPorPeriodo r) {
		StreamObject so = new StreamObject("ResultadoDeBusquedaPorPeriodo");
		so.add(r.getNombre());
		so.add(r.getIndiceAfinidad());
		so.add(r.getAlgoritmo().toString());
		so.add(r.getImportancias().keySet().toArray());
		so.add(r.getImportancias().values().toArray());
		so.add(r.getPeriodo());
		so.add(r.getCriterios().keySet().toArray());
		so.add(r.getCriterios().values().toArray());
		return so;
	}

	private StreamObject encode(ResultadoDeBusquedaPorDiputado r) {
		StreamObject so = new StreamObject("ResultadoDeBusquedaPorDiputado");
		so.add(r.getNombre());
		so.add(r.getIndiceAfinidad());
		so.add(r.getAlgoritmo().toString());
		so.add(r.getImportancias().keySet().toArray());
		so.add(r.getImportancias().values().toArray());
		so.add(r.getLapsoDetiempo());
		so.add(r.getDiputadoRelevante());
		so.add(r.getCriterios().keySet().toArray());
		so.add(r.getCriterios().values().toArray());
		return so;
	}

	private void adjust(Integer i, Integer j, StreamContainer sc,
			ControladorFichero sf) {
		if (sc.length() > lineaSize/2*3) {
			sf.add(sc);
			sc = new StreamContainer("Dominio"+(++j));
		}
		
		if (sf.length() > ficheroSize/2*3){
			sf.add(sc);
			sc = new StreamContainer("Dominio"+(++j));
			String fichero = "save"+(++i)+ext;
			j = 0;
			try {
				sf.print(rutaPersistencia+fichero);
				ficheros.add(fichero);
			} catch (IOException e) {
				error = new CodiError(45);
				error.addClauExterna(rutaPersistencia+fichero);
			}
		}
		
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
		try {
			base.print(rutaPersistencia+ficheroBase);
		} catch (IOException e) {
			error = new CodiError(45);
			error.addClauExterna(rutaPersistencia+ficheroBase);
			return;
		}
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
		ControladorFichero sf = new ControladorFichero();
		int i = 0;
		StreamContainer sc = new StreamContainer("Export"+(++i));
		for (ExportSet e : l) {
			switch (e.getType()) {
			case "Diputado":
				sc.add(encode(cDip.get(e.getForeignKey())));
				break;
			case "TipoEvento": 
				sc.add(encode(cEv.get(e.getForeignKey())));
				break;
			case "Evento":
				sc.add(encode(cEv.get(e.getForeingKey2()).getEvento(e.getForeignKey()), e.getForeingKey2()));
				break;
			case "Votacion":
				sc.add(encode(cVot.get(e.getForeignKey())));
				break;
			case "ResultadoDeBusquedaPorDiputado": 
				sc.add(encode( (ResultadoDeBusquedaPorDiputado) cRes.get(e.getForeignKey())));
				break;
			case "ResultadoDeBusquedaPorPeriodo":
				sc.add(encode( (ResultadoDeBusquedaPorPeriodo) cRes.get(e.getForeignKey())));
				break;
			case "GrupoAfinPorDiputado":
				sc.add(encode(((GrupoAfinPorDiputado)cRes.getGrupo(e.getForeignKey(),Integer.parseInt(e.getForeignKey()))), e.getForeingKey2()));
				break;
			case "GrupoAfinPorPeriodo":
				sc.add(encode(((GrupoAfinPorPeriodo)cRes.getGrupo(e.getForeignKey(),Integer.parseInt(e.getForeignKey()))), e.getForeingKey2()));
				break;
			}
			if (sc.size() > lineaSize*3/2) {
				sf.add(sc);
				sc = new StreamContainer("Export"+(++i));
			}
		}
	}
	
	public ImportLog getLog() {
		if (leyendo) return log;
		return null;
	}
	public ImportLog getLogForDebugging() {
		return log;
	}
	
	/**
	 * Codifica una Legislatura en un StreamObject para su posterior almacenamiento.
	 * @param L - Legislatura a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(Legislatura L){
		StreamObject stream = new StreamObject(L.getClass().getSimpleName());
		stream.add(L.getID());
		stream.add(L.getFechaInicio());
		stream.add(L.getFechaFinal());
		return stream;
	}
	
	/**
	 * Codifica un Evento en un StreamObject para su posterior almacenamiento.
	 * @param E - Evento a codificar.
	 * @param tipoEvento - tipo de Evento al que pertenece E.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(Evento E, String tipoEvento){
		StreamObject stream = new StreamObject(E.getClass().getSimpleName());
		stream.add(E.getNombre());
		stream.add(tipoEvento);
		stream.add(E.getFecha());
		stream.add(E.getdiputados());
		return stream;
	}
	
	/**
	 * Codifica un Diputado en un StreamObject para su posterior almacenamiento.
	 * @param D - Diputado a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(Diputado D){
		StreamObject stream = new StreamObject(D.getClass().getSimpleName());
		stream.add(D.getNombre());
		stream.add(D.getPartidoPolitico());
		stream.add(D.getEstado());
		stream.add(D.getFechaDeNacimiento());
		stream.add(D.getLegislaturas());		
		return stream;
	}
	
	/**
	 * Codifica un Tipo de Evento en un StreamObject para su posterior almacenamiento.
	 * @param T - TipoEvento a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(TipoEvento T){
		StreamObject stream = new StreamObject(T.getClass().getSimpleName());
		stream.add(T.getNombre());
		stream.add(T.getImportancia());
		return stream;
	}
	
	/**
	 * Codifica una Votacion en un StreamObject para su posterior almacenamiento.
	 * @param V - Votacion a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(Votacion V){
		StreamObject stream = new StreamObject(V.getClass().getSimpleName());
		stream.add(V.getNombre());
		stream.add(V.getFecha());
		stream.add(V.getImportancia());
		Set<String> set = V.getDiputados();
		String[] diputados = new String[set.size()];
		String[] votos = new String[set.size()];
		Integer i = 0;
		for (String s:set) {
			diputados[i] = s;
			votos[i] = Votacion.convert(V.getVoto(s));
			++i;
		}
		stream.add(diputados);
		stream.add(votos);
		return stream;
	}
	
	/**
	 * Codifica un Grupo Afín Por Período en un StreamObject para su posterior almacenamiento.
	 * @param G - Grupo a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(GrupoAfinPorPeriodo G, String nombreResultado){
		StreamObject stream = new StreamObject(G.getClass().getSimpleName());
		stream.add(G.getID());
		stream.add(nombreResultado);
		stream.add(G.getDiputados());
		return stream;
	}
	
	/**
	 * Codifica un Grupo Afín Por Diputado en un StreamObject para su posterior almacenamiento.
	 * @param G - Grupo a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	private static StreamObject encode(GrupoAfinPorDiputado G, String nombreResultado){
		StreamObject stream = new StreamObject(G.getClass().getSimpleName());
		stream.add(G.getID());
		stream.add(nombreResultado);
		stream.add(G.getFechaInicio());
		stream.add(G.getFechaFin());
		stream.add(G.getDiputados());
		return stream;
	}
	
}

