package controladores;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
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
	
	private static StreamFile base;
	
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
		base = new StreamFile();
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
		StreamFile f = new StreamFile();
		f.read(fichero);
		cargarDatos(f);
	}
	
	private void cargarDatos(StreamFile sf) throws FileFormatException {
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
				else if(nombre.equals("Resultado")) {
					addResultado(so);
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
	
	private void addToLog(CodiError error) {
		// TODO Auto-generated method stub
	}

	private void addResultado(StreamObject resultado) {
		// TODO Auto-generated method stub	
	}

	private void addFichero(StreamObject fichero) throws FileFormatException {
		cargarFichero(fichero.elementAt(1));		
	}

	private void addEvento(StreamObject evento) {
		// TODO Auto-generated method stub
	}

	private void addTipoEvento(StreamObject tipoEvento) {
		// TODO Auto-generated method stub
	}

	private void addVotacion(StreamObject votacion) {
		Map<String,TipoVoto> map = new HashMap<String,TipoVoto>();
		Set<String> diputados = votacion.setAt(3);
		Set<String> votos = votacion.setAt(4);
		if ()
		cVot.addVotacion(votacion.elementAt(1), Date.parseDate(votacion.elementAt(2)),map);	
	}

	private void addDiputado(StreamObject diputado) {
		try {
			cDip.addDiputado(diputado.elementAt(1), diputado.elementAt(2), diputado.elementAt(3), Date.parseDate(diputado.elementAt(4)));
		} catch (FileFormatException e) {
			e.printStackTrace();
		}
		for (String s:sf.At(i,j,5)) {
			cDip.addLegistura(sf.elementAt(i, j, 1), Integer.parseInt(s));
		}
	}

	public void salvarDominio() {
		for (String fichero : ficheros) {
			StreamFile f = new StreamFile();
			f.read(fichero);
			salvarDatos(f);
			f.print(fichero);
		}
		cargarDominio();
	}

	private void salvarDatos(StreamFile antiguo) {
		for (int i = 0; i < antiguo.size(); ++i) {
			//TODO ModificarCosillas
		}
	}
	
	public void leerFichero(String fichero) {
		StreamFile f = new StreamFile();
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
