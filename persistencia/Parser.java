package persistencia;

import java.io.IOException;
import java.util.HashMap;
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
	public static StreamObject encode(Diputado D){
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
	public static Diputado parseDiputado(StreamObject diputado){
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
		/*Evento E = new Evento(
							evento.elementAt(1),
							Date.parseDate(evento.elementAt(2)));
							TODO
							*/
		Evento E = new Evento(evento.elementAt(1), Date.parseDate(evento.elementAt(2)), new HashSet<String>());
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
		return L;
	}
	
	
	//TODO
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
			votos[i] = V.getVoto(s).toString(); //TODO
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
		/*
		Votacion V = new Votacion(
								votacion.elementAt(1),
								Date.parseDate(votacion.elementAt(2)),
								Integer.parseInt(votacion.elementAt(3)));
		TODO
		 */
		Votacion V = new Votacion(
								votacion.elementAt(1),
								Date.parseDate(votacion.elementAt(2)),
								Integer.parseInt(votacion.elementAt(3)),
								new HashMap<String,TipoVoto>());

		String[] diputados = votacion.arrayAt(4);
		String[] votos = votacion.arrayAt(5);
		if (diputados.length != diputados.length) throw new ObjectFormatException(false, "Formato de objeto incorrecto.");
		//for (Integer i = 0; i < diputados.length; ++i) V.setaddVoto(diputados[i], votos[i]); TODO
		for (Integer i = 0; i < diputados.length; ++i) {
			TipoVoto voto;
			switch(votos[i]) {
			case "ABSTENCION":
				voto = TipoVoto.ABSTENCION;
				break;
			case "EN_CONTRA":
				voto = TipoVoto.EN_CONTRA;
				break;
			case "A_FAVOR":
				voto = TipoVoto.A_FAVOR;
				break;
			default:
				voto = TipoVoto.AUSENCIA;
				break;
			}
			V.setaddVoto(diputados[i], voto);
		}
		return V;
	}
	
	public static void main (String args[]) {
		Entrada E = new ConsolaEntrada();
		Salida S = new ConsolaSalida();
		Entrada F = new ConsolaEntrada();
		Boolean file = true;
		try {
			S.write("Introduce el nombre del fichero SIOF.");
			F = new FicheroEntrada(E.readString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			file = false;
		}
		
		if (file) {
			StreamFile SF = new StreamFile();
			try {
				SF.read(F);
			} catch (FileFormatException | FileChecksumException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return;
			}
			try {
				S.write("Fichero SIOF leido con exito.");
			} catch (IOException e) {
				e.getMessage();
			}

			
			try {
				SF.print(new FicheroSalida("CIOF.txt"));
				S.write("Fichero CIOF creado con exito.");
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return;
			}
		}
		
		try {
			S.write("Introduce el nombre del fichero CIOF.");
			F = new FicheroEntrada(E.readString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		StreamFile SF = new StreamFile();
		try {
			SF.read(F);
		} catch (FileFormatException | FileChecksumException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		try {
			S.write("Fichero CIOF leido con exito.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			for (Integer i = 1; i <= SF.size(); ++i) {
				S.write("Contenedor " + SF.elementAt(i).getName());
				for (Integer j = 1; j < SF.elementAt(i).size(); ++j) {
					S.write("Clase " + SF.elementAt(i, j).getNombre());
					for (Integer k = 1; k < SF.elementAt(i, j).size(); ++k) {
						S.write(SF.elementAt(i, j, k));
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		
		
	}

}