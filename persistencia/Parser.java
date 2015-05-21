package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import io.*;
import time.Date;
import dominio.*;
import exceptions.FileChecksumException;
import exceptions.FileFormatException;
import exceptions.ObjectFormatException;

/**
 * Conjunto de métodos para el formateo utilizados en lectura i escritura de ficheros.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class Parser {
	
	/**
	 * Constructor privado, la clase no puede ser instanciada.
	 */
	private Parser(){
	}
	
	/**
	 * Codifica un Diputado en un StreamObject para su posterior almacenamiento.
	 * @param D - Diputado a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(Diputado D){ //TODO -> Controlador Domini
		StreamObject stream = new StreamObject(D.getClass().getSimpleName());
		stream.add(D.getNombre());
		stream.add(D.getPartidoPolitico());
		stream.add(D.getEstado());
		stream.add(D.getFechaDeNacimiento());
		stream.add(D.getLegislaturas());		
		return stream;
	}
	
	/**
	 * Extrae el diputado contenido en un StreamObject.
	 * @param diputado - StreamObject con la información a extraer.
	 * @return El Diputado conetnido en el StreamObject.
	 */
	public static Diputado parseDiputado(StreamObject diputado){ //TODO -> Objecte Domini
		if (!diputado.getNombre().equals(Diputado.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (diputado.size() != 6) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		Diputado D = new Diputado(
								diputado.elementAt(1),
								diputado.elementAt(2),
								diputado.elementAt(3),
								Date.parseDate(diputado.elementAt(4)));
		for (String s:diputado.setAt(5)) {
			D.addLegistura(Integer.parseInt(s));
		}
		return D;
	}
	
	
	/**
	 * Codifica un Evento en un StreamObject para su posterior almacenamiento.
	 * @param E - Evento a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(Evento E){
		StreamObject stream = new StreamObject(E.getClass().getSimpleName());
		stream.add(E.getNombre());
		stream.add(E.getFecha());
		stream.add(E.getdiputados());
		return stream;
	}
	
	/**
	 * Extrae el evento contenido en un StreamObject.
	 * @param evento - StreamObject con la información a extraer.
	 * @return El evento conetnido en el StreamObject.
	 */
	public static Evento parseEvento(StreamObject evento){
		if (!evento.getNombre().equals(Evento.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (evento.size() != 4) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		Evento E = new Evento(
							evento.elementAt(1),
							Date.parseDate(evento.elementAt(2)));
		/*
		Evento E = new Evento(
						evento.elementAt(1),
						Date.parseDate(evento.elementAt(2)),
						new HashSet<String>());
		 */
		for (String s:evento.setAt(3)) {
			E.addDiputado(s);
		}
		return E;
	}
	
	/**
	 * Codifica un Grupo Afín Por Diputado en un StreamObject para su posterior almacenamiento.
	 * @param G - Grupo a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(GrupoAfinPorDiputado G){
		StreamObject stream = new StreamObject(G.getClass().getSimpleName());
		stream.add(G.getID());
		stream.add(G.getFechaInicio());
		stream.add(G.getFechaFin());
		stream.add(G.getDiputados());
		return stream;
	}
	
	/**
	 * Extrae el Grupo Afín Por Diputado contenido en un StreamObject.
	 * @param grupo - StreamObject con la información a extraer.
	 * @return El grupo conetnido en el StreamObject.
	 */
	public static GrupoAfinPorDiputado parseGrupoAfinPorDiputado(StreamObject grupo){
		if (!grupo.getNombre().equals(GrupoAfinPorDiputado.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (grupo.size() != 5) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		GrupoAfinPorDiputado G = new GrupoAfinPorDiputado(
									Integer.parseInt(grupo.elementAt(1)),
									Date.parseDate(grupo.elementAt(2)),
									Date.parseDate(grupo.elementAt(3)));
		for (String s:grupo.setAt(4)) {
			G.addDiputado(s);
		}
		return G;
	}
	
	
	/**
	 * Codifica un Grupo Afín Por Período en un StreamObject para su posterior almacenamiento.
	 * @param G - Grupo a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(GrupoAfinPorPeriodo G){
		StreamObject stream = new StreamObject(G.getClass().getSimpleName());
		stream.add(G.getID());
		stream.add(G.getDiputados());
		return stream;
	}
	
	/**
	 * Extrae el Grupo Afín Por Período contenido en un StreamObject.
	 * @param grupo - StreamObject con la información a extraer.
	 * @return El grupo conetnido en el StreamObject.
	 */
	public static GrupoAfinPorPeriodo parseGrupoAfinPorPeriodo(StreamObject grupo){
		if (!grupo.getNombre().equals(GrupoAfinPorPeriodo.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (grupo.size() != 3) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		GrupoAfinPorPeriodo G = new GrupoAfinPorPeriodo(
									Integer.parseInt(grupo.elementAt(1)));
		for (String s:grupo.setAt(2)) {
			G.addDiputado(s);
		}
		return G;
	}
	
	/**
	 * Codifica una Legislatura en un StreamObject para su posterior almacenamiento.
	 * @param L - Legislatura a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(Legislatura L){
		StreamObject stream = new StreamObject(L.getClass().getSimpleName());
		stream.add(L.getID());
		stream.add(L.getFechaInicio());
		stream.add(L.getFechaFinal());
		stream.add(L.getDiputados());
		return stream;
	}
	
	/**
	 * Extrae la legislatura contenida en un StreamObject.
	 * @param legislatura - StreamObject con la información a extraer.
	 * @return La Legislatura conetnida en el StreamObject.
	 */
	public static Legislatura parseLegislatura(StreamObject legislatura){
		if (!legislatura.getNombre().equals(Diputado.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (legislatura.size() != 5) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		Date fechaFin = Date.parseDate(legislatura.elementAt(3));
		Legislatura L;
		if (fechaFin.isNull()) L = new Legislatura(
								Integer.parseInt(legislatura.elementAt(1)),
								Date.parseDate(legislatura.elementAt(2)));
		else L = new Legislatura(
								Integer.parseInt(legislatura.elementAt(1)),
								Date.parseDate(legislatura.elementAt(2)),
								fechaFin);
		for (String s:legislatura.setAt(4)) {
			L.addDiputado(s);
		}
		return L;
	}
	
	/*
	public static ParserStream toParserStream(ResultadoDeBusquedaPorDiputado R){
		String stream = R.getClass().getCanonicalName() + ":";
		stream = stream	+ R.getNombre() + ";";
		//No se quins atributs es volen salvar...
		return stream;
	}

	public static ParserStream toParserStream(ResultadoDeBusquedaPorPeriodo R){
		String stream = R.getClass().getCanonicalName() + ":";
		stream = stream	+ R.getNombre() + ";";
		//No se quins atributs es volen salvar...
		return stream;
	}
	
	*/

	/**
	 * Codifica un Tipo de Evento en un StreamObject para su posterior almacenamiento.
	 * @param T - TipoEvento a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(TipoEvento T){
		StreamObject stream = new StreamObject(T.getClass().getSimpleName());
		stream.add(T.getNombre());
		stream.add(T.getImportancia());
		Set<StreamObject> set = new HashSet<StreamObject>();
		for (Evento e:T.getEventos()) set.add(encode(e));
		stream.addObject(set);
		return stream;
	}
	
	/**
	 * Extrae el Tipo de Evento contenido en un StreamObject.
	 * @param tipoEvento - StreamObject con la información a extraer.
	 * @return El TipoEvento conetnido en el StreamObject.
	 */
	public static TipoEvento parseTipoEvento(StreamObject tipoEvento){
		if (!tipoEvento.getNombre().equals(TipoEvento.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (tipoEvento.size() != 4) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		
		TipoEvento T = new TipoEvento(
								tipoEvento.elementAt(1),
								Integer.parseInt(tipoEvento.elementAt(2)));
		
		for (StreamObject SO:tipoEvento.setObjectAt(3)) {
			T.addEvento(parseEvento(SO));
		}
		return T;
	}
	
	/**
	 * Codifica una Votacion en un StreamObject para su posterior almacenamiento.
	 * @param V - Votacion a codificar.
	 * @return Un StreamObject que contiene el objeto a codificar.
	 */
	public static StreamObject encode(Votacion V){
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
			votos[i] = V.getVoto(s);
			++i;
		}
		stream.add(diputados);
		stream.add(votos);
		return stream;
	}
	
	/**
	 * Extrae la Votacion contenida en un StreamObject.
	 * @param votacion - StreamObject con la información a extraer.
	 * @return La Votacion conetnida en el StreamObject.
	 */
	public static Votacion parseVotacion(StreamObject votacion){
		if (!votacion.getNombre().equals(TipoEvento.class.getSimpleName())) throw new ObjectFormatException(false, "Clase del objeto incorrecta.");
		if (votacion.size() != 4) throw new ObjectFormatException(false, "Longitud del objeto incorrecta.");
		
		Votacion V = new Votacion(
								votacion.elementAt(1),
								votacion.elementAt(2),
								votacion.elementAt(3));

		//Map <nomDiputado, Voto>
		String[] diputados = votacion.arrayAt(4);
		String[] votos = votacion.arrayAt(5);
		if (diputados.length != diputados.length) throw new ObjectFormatException(false, "Formato de objeto incorrecto.");
		for (Integer i = 0; i < diputados.length; ++i) V.setaddVoto(diputados[i], votos[i]);

		return V;
	}
	
	public static void main (String args[]) {
		Salida S = new ConsolaSalida();
		Diputado D = new Diputado("David Moran", "Los mejores", "Barbera del Valles", Date.parseDate("17/10/1995"));
		Diputado D2 = new Diputado("Anna Margalef", "Los mejores", "Barcelona", Date.parseDate("24/2/1996"));

		for (Integer i = 1; i < 100; i *= 2) D.addLegistura(i);
		for (Integer i = 100; i > 0; i /= 2) D2.addLegistura(i);
		
		
		Set<String> set = new HashSet<String>();
		set.add("David Morán");
		set.add("Anna Margalef");
		
		Set<Evento> setE = new HashSet<Evento>();
		
		setE.add(new Evento("reunio", new Date("1/1/2001"), set));
		setE.add(new Evento("acte", new Date("2/2/2002"), set));
		setE.add(new Evento("cosa extranya", new Date("31/12/1999"), set));
		
		
		TipoEvento TE = new TipoEvento("test TipoEvento", 4);
		for (Evento e:setE) TE.addEvento(e);
		
		
		StreamObject SO = encode(D);
		StreamObject SO2 = encode(D2);
		StreamObject SO3 = encode(TE);
		
		
		StreamContainer SC = new StreamContainer("Diputados test");
		SC.add(SO);
		SC.add(SO2);
		StreamContainer SC2 = new StreamContainer("Diputados test2");
		SC2.add(SO2);
		SC2.add(SO);
		StreamContainer SC3 = new StreamContainer("TipoEvento test");
		SC3.add(SO3);

		
		StreamFile SF = new StreamFile();
		SF.add(SC);
		SF.add(SC2);
		SF.add(SC3);

		try {
			SF.print(new FicheroSalida("test.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		StreamFile SFres = new StreamFile();
		try {
			try {
				SFres.read(new FicheroEntrada("test.txt"));
			} catch (FileNotFoundException e) {
				S.write("Fichero inexistente.");
				return;
			} catch (FileFormatException e) {
				S.write(e.getMessage());
				return;
			} catch (FileChecksumException e) {
				S.write(e.getMessage());
				return;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		StreamContainer SCres = SFres.elementAt(1); //1..n contenidors al fitxer
		StreamObject SOres = SCres.elementAt(1); //1..n objectes al contenidor (0 és el nom)
		Diputado Dres = Parser.parseDiputado(SOres); //1 Diputado a l'objecte
		
		Diputado Dres2 = Parser.parseDiputado(SFres.elementAt(1, 2));
		TipoEvento TEres = Parser.parseTipoEvento(SFres.elementAt(3, 1));
		
		
		try {
			S.write("Nombre: " + Dres.getNombre());
			S.write("Partido: " + Dres.getPartidoPolitico());
			S.write("Estado: " + Dres.getEstado());
			S.write("Fecha: " + Dres.getFechaDeNacimiento().toString());
			S.write("Legislaturas:");
			for(Integer i:Dres.getLegislaturas()) S.write(i);
	
			S.write("Nombre: " + Dres2.getNombre());
			S.write("Partido: " + Dres2.getPartidoPolitico());
			S.write("Estado: " + Dres2.getEstado());
			S.write("Fecha: " + Dres2.getFechaDeNacimiento().toString());
			S.write("Legislaturas:");
			for(Integer i:Dres2.getLegislaturas()) S.write(i);
			
			S.write("Nombre: " + TEres.getNombre());
			S.write("Importancia: " + TEres.getImportancia());
			S.write("Eventos:");
			for(Evento e:TEres.getEventos()) {
				S.write("Nombre: " + e.getNombre());
				S.write("Fecha: " + e.getFecha().toString());
				S.write("Participantes:");
				for (String s:e.getdiputados()) S.write(s);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}