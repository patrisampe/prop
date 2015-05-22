package drivers;

import io.*;
import time.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;













import utiles.ConjuntoGrupoAfin;
import controladores.*;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;
import dominio.TipoAlgoritmo;
import dominio.TipoVoto;

/**
 * Driver de los Controladores de B�squeda.
 * Por su naturaleza, este driver ejerce tambi�n de Driver del Dominio completo exceptuando Resultado de B�squeda.
 * @author Yoel Cabo
 *
 */
public class BusquedaDriver {
	
		
	public static void main (String args[]) throws IOException {
		ControladorDominioBusquedaPorDiputado PorDip = new ControladorDominioBusquedaPorDiputado();
		ControladorDominioBusquedaPorPeriodo PorPer  = new ControladorDominioBusquedaPorPeriodo();
		ControladorDominioVotacion cVot = ControladorDominioVotacion.getInstance();
		ControladorDominioEvento cEv = ControladorDominioEvento.getInstance();
		ControladorDominioDiputado cDip = ControladorDominioDiputado.getInstance();
		ControladorDominioLegislatura cLeg = ControladorDominioLegislatura.getInstance();
		Entrada EC = new ConsolaEntrada();
		String Input = EC.readString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.readString();
		Salida SF = new FicheroSalida(Output);
		Salida SC = new ConsolaSalida();
		int a= EF.readInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 SF.write("Periodo Standard");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaStandard(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), ReadMap(EF), EF.readInteger()));
				 if (PorPer.hasError()) SC.write(PorPer.getError().getMensajeError());
				 break;
			 case 2: 
				 SF.write("Diputado Standard");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaStandard(ReadAlgoritmo(EF), EF.readInteger(), ReadMap(EF), EF.readInteger(), EF.readString()));
				 if (PorDip.hasError()) SC.write(PorDip.getError().getMensajeError());
				 break;
			 case 3: 
				 SF.write("Periodo Partido");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.readInteger()));
				 if (PorPer.hasError()) SC.write(PorPer.getError().getMensajeError());
				 break;
			 case 4: 
				 SF.write("Diputado Partido");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), EF.readInteger(), EF.readInteger(), EF.readString()));
				 if (PorDip.hasError()) SC.write(PorDip.getError().getMensajeError());
				 break;
			 case 5:
				 SF.write("Periodo Estado");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaEstado(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.readInteger()));
				 if (PorPer.hasError()) SC.write(PorPer.getError().getMensajeError());
				 break;
			 case 6:
				 SF.write("Diputado Estado");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaEstado(ReadAlgoritmo(EF), EF.readInteger(), EF.readInteger(), EF.readString()));
				 if (PorDip.hasError()) SC.write(PorDip.getError().getMensajeError());
				 break;
			 case 7:
				 SF.write("Periodo Nombre");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaNombresParecidos(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.readInteger()));
				 if (PorPer.hasError()) SC.write(PorPer.getError().getMensajeError());
				 break;
			 case 8:
				 SF.write("Diputado Nombre");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaNombresParecidos(ReadAlgoritmo(EF), EF.readInteger(), EF.readInteger(), EF.readString()));
				 if (PorDip.hasError()) SC.write(PorDip.getError().getMensajeError());

				 break;
			 case 9:
				 SC.write("Lectura");
				 ReadAndAddDeTodo(SC, EF, cVot, cLeg, cDip, cEv);
				 break;
			 default: 
				 SC.write(" Comanda incorrecta. Per a tancar -1 ");
				 SC.write(a);
				 break;
				 }
				a=EF.readInt();
		}
		EF.close();
		SF.close();
	}
	
	private static void ReadAndAddDeTodo(Salida sC, Entrada eF, ControladorDominioVotacion cVot, ControladorDominioLegislatura cLeg, ControladorDominioDiputado cDip, ControladorDominioEvento cEv) throws IOException {
		String s = eF.readString();
		while (!s.equals("FIN")) {
			sC.write(s);
			if (s.equals("DIPUTADO")) {
				cDip.addDiputado(eF.readString(), eF.readString(), eF.readString(), DateIntervalDriver.ReadDate(eF));
				if (cDip.hasError()) sC.write(cDip.getError().getMensajeError());
			}
			else if (s.equals("EVENTO")) {
				cEv.addEvento(eF.readString(), eF.readString(), DateIntervalDriver.ReadDate(eF), eF.readSetString(eF.readInt()));
				if (cEv.hasError()) sC.write(cEv.getError().getMensajeError());
			}
			else if (s.equals("TIPOEVENTO")) {
				cEv.addTipoEvento(eF.readString(), eF.readInteger());
				if (cEv.hasError()) sC.write(cEv.getError().getMensajeError());
			}
			else if (s.equals("LEGISLATURA")) {
				Integer identificador = eF.readInteger();
				Date fechaIni = DateIntervalDriver.ReadDate(eF);
				Date fechaFin = DateIntervalDriver.ReadDate(eF);
				cLeg.addLegislatura(identificador, fechaIni, fechaFin);
				if (cLeg.hasError()) sC.write(cLeg.getError().getMensajeError());
				Integer n = eF.readInteger();
				for (int i = 0; i < n; ++i) {
					cLeg.addDiputado(identificador, eF.readString());
					if (cLeg.hasError()) sC.write(cLeg.getError().getMensajeError());
				}
			}
			else if (s.equals("VOTACION")) {
				cVot.addVotacion(eF.readString(), DateIntervalDriver.ReadDate(eF), eF.readInteger(), ReadMapVotos(eF));
				if (cVot.hasError()) sC.write(cVot.getError().getMensajeError());

			}
			s = eF.readString();
		}
	}

	private static Map<String, TipoVoto> ReadMapVotos(Entrada eF) throws IOException {
		Map<String,TipoVoto> ret = new TreeMap<String,TipoVoto>();
		int iteraciones = eF.readInt();
		for (int i = 0; i < iteraciones; ++i) {
			ret.put(eF.readString(), ReadTipoVoto(eF));
		}
		return ret;
	}

	private static TipoVoto ReadTipoVoto(Entrada eF) throws IOException {
		String s = eF.readString();
		if (s.equals("A_FAVOR")) return TipoVoto.A_FAVOR;
		if (s.equals("EN_CONTRA")) return TipoVoto.EN_CONTRA;
		if (s.equals("ABSTENCION")) return TipoVoto.ABSTENCION;
		if (s.equals("AUSENCIA")) return TipoVoto.AUSENCIA;
		return null;
	}

	public static void PrintGrupPeriodo(Salida SF, GrupoAfinPorPeriodo grupAfi) throws IOException {
		SF.write(grupAfi.getID());
		Set<String> diputados = grupAfi.getDiputados();
		SF.write(diputados);
	}
	
	public static void PrintConjGrupPeriodo(Salida sF,
			ConjuntoGrupoAfin conjuntoGrupoAfin) throws IOException {
		sF.write("El numero de grupos es: " + conjuntoGrupoAfin.size().toString());
		for (GrupoAfinPorPeriodo g : conjuntoGrupoAfin.getAllPorPeriodo()) {
			PrintGrupPeriodo(sF, g);
		}
		
	}
	
	public static void PrintGrupDiputado(Salida SF, GrupoAfinPorDiputado grupAfi) throws IOException {
		SF.write(grupAfi.getID());
		SF.write(grupAfi.getFechaInicio().toString());
		SF.write(grupAfi.getFechaFin().toString());
		Set<String> diputados = grupAfi.getDiputados();
		SF.write(diputados);
	}
	
	public static void PrintConjGrupDiputado(Salida sF,
			ConjuntoGrupoAfin conjuntoGrupoAfin) throws IOException {
		sF.write("El numero de grupos es: " + conjuntoGrupoAfin.size().toString());
		for (GrupoAfinPorDiputado g : conjuntoGrupoAfin.getAllPorDiputado()) {
			PrintGrupDiputado(sF, g);
		}
		
	}

	public static Map<String, Integer> ReadMap(Entrada eF) throws IOException {
		Map<String,Integer> ret = new TreeMap<String,Integer>();
		int iteraciones = eF.readInt();
		for (int i = 0; i < iteraciones; ++i) {
			ret.put(eF.readString(), eF.readInteger());
		}
		return ret;
	}

	public static TipoAlgoritmo ReadAlgoritmo(Entrada eF) throws IOException {
		String Alg = eF.readString();
		if (Alg.equals("Louvain")) return TipoAlgoritmo.Louvain;
		if (Alg.equalsIgnoreCase("CliquePercolation")) return TipoAlgoritmo.CliquePercolation;
		if (Alg.equals("GirvanNewmann")) return TipoAlgoritmo.GirvanNewmann;
		System.out.println(Alg+" no es un Algoritmo conocido.");
		return null;
	}
	
}
