package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import time.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utiles.Conjunto;
import controladores.*;
import dominio.GrupoAfinPorDiputado;
import dominio.GrupoAfinPorPeriodo;
import dominio.TipoAlgoritmo;
import dominio.TipoVoto;

/**
 * Driver de los Controladores de Búsqueda.
 * Por su naturaleza, este driver ejerce también de Driver del Dominio completo exceptuando Resultado de Búsqueda.
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
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		Sortida SC = new ConsolaSortida();
		int a= EF.ReadInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 SC.Write("Periodo Standard");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaStandard(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), ReadMap(EF), EF.ReadInteger()));
			     break;
			 case 2: 
				 SC.Write("Diputado Standard");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaStandard(ReadAlgoritmo(EF), EF.ReadInteger(), ReadMap(EF), EF.ReadInteger(), EF.ReadString()));
			     break;
			 case 3: 
				 SC.Write("Periodo Partido");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
			     break;
			 case 4: 
				 SC.Write("Diputado Partido");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaPartidoPolitico(ReadAlgoritmo(EF), EF.ReadInteger(), EF.ReadInteger(), EF.ReadString()));
			     break;
			 case 5:
				 SC.Write("Periodo Estado");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaEstado(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
				 break;
			 case 6:
				 SC.Write("Diputado Estado");
				 PrintConjGrupDiputado(SF, PorDip.NuevaBusquedaEstado(ReadAlgoritmo(EF), EF.ReadInteger(), EF.ReadInteger(), EF.ReadString()));
			 	break;
			 case 7:
				 SC.Write("Periodo Nombre");
				 PrintConjGrupPeriodo(SF,PorPer.NuevaBusquedaNombresParecidos(ReadAlgoritmo(EF), DateIntervalDriver.ReadDateInterval(EF), EF.ReadInteger()));
				 break;
			 case 8:
				 SC.Write("Diputado Nombre");
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
	
	private static void ReadAndAddDeTodo(Sortida sC, Entrada eF, ControladorDominioVotacion cVot, ControladorDominioLegislatura cLeg, ControladorDominioDiputado cDip, ControladorDominioEvento cEv) {
		String s = eF.ReadString();
		while (!s.equals("FIN")) {
			sC.Write(s);
			if (s.equals("DIPUTADO")) {
				cDip.addDiputado(eF.ReadString(), eF.ReadString(), eF.ReadString(), DateIntervalDriver.ReadDate(eF));
			}
			else if (s.equals("EVENTO")) {
				cEv.addEvento(eF.ReadString(), eF.ReadString(), DateIntervalDriver.ReadDate(eF), eF.ReadSetString(eF.ReadInt()));
			}
			else if (s.equals("TIPOEVENTO")) {
				cEv.addTipoEvento(eF.ReadString(), eF.ReadInteger());
			}
			else if (s.equals("LEGISLATURA")) {
				Integer identificador = eF.ReadInteger();
				Date fechaIni = DateIntervalDriver.ReadDate(eF);
				Date fechaFin = DateIntervalDriver.ReadDate(eF);
				cLeg.addLegislatura(identificador, fechaIni, fechaFin);
				Integer n = eF.ReadInteger();
				for (int i = 0; i < n; ++i) {
					sC.Write(i);
					cLeg.addDiputado(identificador, eF.ReadString());
				}
			}
			else if (s.equals("VOTACION")) {
				cVot.addVotacion(eF.ReadString(), DateIntervalDriver.ReadDate(eF), eF.ReadInteger(), ReadMapVotos(eF));
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

	public static void PrintGrupPeriodo(Sortida SF, GrupoAfinPorPeriodo grupAfi) {
		SF.Write(grupAfi.getID());
		Set<String> diputados = grupAfi.getDiputados();
		SF.Write(diputados.size());
		for (String diputado:diputados)
			SF.Write(diputado);
	}
	
	public static void PrintConjGrupPeriodo(Sortida sF,
			Conjunto<GrupoAfinPorPeriodo> Cjt) {
		sF.Write("El numero de grupos es: " + Cjt.size().toString());
		for (GrupoAfinPorPeriodo g : Cjt.getAll()) {
			PrintGrupPeriodo(sF, g);
		}
		
	}
	
	public static void PrintGrupDiputado(Sortida SF, GrupoAfinPorDiputado grupAfi) {
		SF.Write(grupAfi.getID());
		SF.Write(grupAfi.getFechaInicio().toString());
		SF.Write(grupAfi.getFechaFin().toString());
		Set<String> diputados = grupAfi.getDiputados();
		SF.Write(diputados);
		for (String diputado:diputados)
			SF.Write(diputado);
	}
	
	public static void PrintConjGrupDiputado(Sortida sF,
			Conjunto<GrupoAfinPorDiputado> Cjt) {
		sF.Write("El numero de grupos es: " + Cjt.size().toString());
		for (GrupoAfinPorDiputado g : Cjt.getAll()) {
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
		if (Alg == "Louvain") return TipoAlgoritmo.Louvain;
		if (Alg == "CliquePercolation") return TipoAlgoritmo.CliquePercolation;
		if (Alg == "GirvanNewmann") return TipoAlgoritmo.GirvanNewmann;
		return null;
	}
	
}
