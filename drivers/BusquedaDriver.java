package drivers;

import io.*;
import time.*;

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
	
		
	public static void main (String args[]) {
		ControladorDominioBusquedaPorDiputado PorDip = new ControladorDominioBusquedaPorDiputado();
		ControladorDominioBusquedaPorPeriodo PorPer  = new ControladorDominioBusquedaPorPeriodo();
		ControladorDominioVotacion cVot = ControladorDominioVotacion.getInstance();
		ControladorDominioEvento cEv = ControladorDominioEvento.getInstance();
		ControladorDominioDiputado cDip = ControladorDominioDiputado.getInstance();
		ControladorDominioLegislatura cLeg = ControladorDominioLegislatura.getInstance();
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.ReadString();
		Salida SF = new FicheroSalida(Output);
		Salida SC = new ConsolaSalida();
		int a= EF.ReadInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 SF.Write("Periodo Standard");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaStandard(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), ReadMap(EF), EF.ReadInteger()));
			     break;
			 case 2: 
				 SF.Write("Diputado Standard");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaStandard(ReadAlgoritmo(EF), EF.ReadInteger(), ReadMap(EF), EF.ReadInteger(), EF.ReadString()));
			     break;
			 case 3: 
				 SF.Write("Periodo Partido");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
			     break;
			 case 4: 
				 SF.Write("Diputado Partido");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), EF.ReadInteger(), EF.ReadInteger(), EF.ReadString()));
			     break;
			 case 5:
				 SF.Write("Periodo Estado");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaEstado(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
				 break;
			 case 6:
				 SF.Write("Diputado Estado");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaEstado(ReadAlgoritmo(EF), EF.ReadInteger(), EF.ReadInteger(), EF.ReadString()));
			 	break;
			 case 7:
				 SF.Write("Periodo Nombre");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaNombresParecidos(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
				 break;
			 case 8:
				 SF.Write("Diputado Nombre");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaNombresParecidos(ReadAlgoritmo(EF), EF.ReadInteger(), EF.ReadInteger(), EF.ReadString()));
				 break;
			 case 9:
				 SC.Write("Lectura");
				 ReadAndAddDeTodo(SC, EF, cVot, cLeg, cDip, cEv);
				 break;
			 default: 
				 SC.Write(" Comanda incorrecta. Per a tancar -1 ");
				 SC.Write(a);
				 break;
				 }
				a=EF.ReadInt();
		}
		EF.close();
		SF.close();
	}
	
	private static void ReadAndAddDeTodo(Salida sC, Entrada eF, ControladorDominioVotacion cVot, ControladorDominioLegislatura cLeg, ControladorDominioDiputado cDip, ControladorDominioEvento cEv) {
		String s = eF.ReadString();
		while (!s.equals("FIN")) {
			sC.Write(s);
			if (s.equals("DIPUTADO")) {
				cDip.addDiputado(eF.ReadString(), eF.ReadString(), eF.ReadString(), DateIntervalDriver.ReadDate(eF));
				if (cDip.hasError()) sC.Write(cDip.getError().getMensajeError());
			}
			else if (s.equals("EVENTO")) {
				cEv.addEvento(eF.ReadString(), eF.ReadString(), DateIntervalDriver.ReadDate(eF), eF.ReadSetString(eF.ReadInt()));
				if (cEv.getHasError()) sC.Write(cEv.getError().getMensajeError());
			}
			else if (s.equals("TIPOEVENTO")) {
				cEv.addTipoEvento(eF.ReadString(), eF.ReadInteger());
				if (cEv.getHasError()) sC.Write(cEv.getError().getMensajeError());
			}
			else if (s.equals("LEGISLATURA")) {
				Integer identificador = eF.ReadInteger();
				Date fechaIni = DateIntervalDriver.ReadDate(eF);
				Date fechaFin = DateIntervalDriver.ReadDate(eF);
				cLeg.addLegislatura(identificador, fechaIni, fechaFin);
				if (cLeg.hasError()) sC.Write(cLeg.getError().getMensajeError());
				Integer n = eF.ReadInteger();
				for (int i = 0; i < n; ++i) {
					cLeg.addDiputado(identificador, eF.ReadString());
					if (cLeg.hasError()) sC.Write(cLeg.getError().getMensajeError());
				}
			}
			else if (s.equals("VOTACION")) {
				cVot.addVotacion(eF.ReadString(), DateIntervalDriver.ReadDate(eF), eF.ReadInteger(), ReadMapVotos(eF));
				if (cVot.getHasError()) sC.Write(cVot.getError().getMensajeError());

			}
			s = eF.ReadString();
		}
	}

	private static Map<String, TipoVoto> ReadMapVotos(Entrada eF) {
		Map<String,TipoVoto> ret = new TreeMap<String,TipoVoto>();
		int iteraciones = eF.ReadInt();
		for (int i = 0; i < iteraciones; ++i) {
			ret.put(eF.ReadString(), ReadTipoVoto(eF));
		}
		return ret;
	}

	private static TipoVoto ReadTipoVoto(Entrada eF) {
		String s = eF.ReadString();
		if (s.equals("A_FAVOR")) return TipoVoto.A_FAVOR;
		if (s.equals("EN_CONTRA")) return TipoVoto.EN_CONTRA;
		if (s.equals("ABSTENCION")) return TipoVoto.ABSTENCION;
		if (s.equals("AUSENCIA")) return TipoVoto.AUSENCIA;
		return null;
	}

	public static void PrintGrupPeriodo(Salida SF, GrupoAfinPorPeriodo grupAfi) {
		SF.Write(grupAfi.getID());
		Set<String> diputados = grupAfi.getDiputados();
		SF.Write(diputados);
	}
	
	public static void PrintConjGrupPeriodo(Salida sF,
			ConjuntoGrupoAfin conjuntoGrupoAfin) {
		sF.Write("El numero de grupos es: " + conjuntoGrupoAfin.size().toString());
		for (GrupoAfinPorPeriodo g : conjuntoGrupoAfin.getAllPorPeriodo()) {
			PrintGrupPeriodo(sF, g);
		}
		
	}
	
	public static void PrintGrupDiputado(Salida SF, GrupoAfinPorDiputado grupAfi) {
		SF.Write(grupAfi.getID());
		SF.Write(grupAfi.getFechaInicio().toString());
		SF.Write(grupAfi.getFechaFin().toString());
		Set<String> diputados = grupAfi.getDiputados();
		SF.Write(diputados);
	}
	
	public static void PrintConjGrupDiputado(Salida sF,
			ConjuntoGrupoAfin conjuntoGrupoAfin) {
		sF.Write("El numero de grupos es: " + conjuntoGrupoAfin.size().toString());
		for (GrupoAfinPorDiputado g : conjuntoGrupoAfin.getAllPorDiputado()) {
			PrintGrupDiputado(sF, g);
		}
		
	}

	public static Map<String, Integer> ReadMap(Entrada eF) {
		Map<String,Integer> ret = new TreeMap<String,Integer>();
		int iteraciones = eF.ReadInt();
		for (int i = 0; i < iteraciones; ++i) {
			ret.put(eF.ReadString(), eF.ReadInteger());
		}
		return ret;
	}

	public static TipoAlgoritmo ReadAlgoritmo(Entrada eF) {
		String Alg = eF.ReadString();
		if (Alg.equals("Louvain")) return TipoAlgoritmo.Louvain;
		if (Alg.equalsIgnoreCase("CliquePercolation")) return TipoAlgoritmo.CliquePercolation;
		if (Alg.equals("GirvanNewmann")) return TipoAlgoritmo.GirvanNewmann;
		System.out.println(Alg+" no es un Algoritmo conocido.");
		return null;
	}
	
}
