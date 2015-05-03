package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import controladores.ControladorDominioLegislatura;
import dominio.Diputado;
import dominio.Legislatura;
import io.*;

/**
 * Driver para el controlador de dominio de legislaturas.
 * @author David Moran
 * @version 04/05/2015 01:00
 */
public class ControladorDominioLegislaturaDriver {
	
	/**
	 * Lector de diputados.
	 * @param E - Interficie de entrada.
	 * @return Diputado leido por la entrada.
	 */
	public static Diputado diputado(Entrada E){
		String nombre = E.ReadString();
		String partidoPolitico = E.ReadString();
		String estado = E.ReadString();
		Date fechaDeNacimiento = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		return new Diputado(nombre, partidoPolitico, estado, fechaDeNacimiento);
	}
	
	/**
	 * Lector de legislaturas.
	 * @param E - Interficie de entrada.
	 * @return Legislatura leida por la entrada.
	 */
	public static Legislatura legislatura(Entrada E){
		Integer identificador = E.ReadInteger();
		Date fechaInicio = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		Date fechaFinal = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		return new Legislatura(identificador, fechaInicio, fechaFinal);
	}
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		Integer n;
		Integer codi = 0;
		while (codi != -1) {
			S.Write("Selecciona una operacion:");
			S.Write("-2: Modificar la configuracion I/O.");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("1: Consultar el numero de legislaturas del sistema.");
			S.Write("2: Insertar un conjunto de legislaturas.");
			S.Write("3: Consultar el conjunto de legislaturas.");
			S.Write("4: Consultar los identificadores de las legislaturas.");
			S.Write("5: Insertar una nueva legislatura.");
			S.Write("6: Insertar una nueva legislatura sin fecha de finalizacion.");
			S.Write("7: Comprobar si una legislatura existe.");
			S.Write("8: Eliminar una legislatura.");
			S.Write("9: Modificar la fecha de inicio de una legislatura.");
			S.Write("10: Modificar la fecha de finalizacion de una legislatura.");
			S.Write("11: Consultar la fecha de inicio de una legislatura.");
			S.Write("12: Consultar la fecha de finalizacion de una legislatura.");
			S.Write("13: Comprobar si la legislatura tiene fecha de finalizacion.");
			S.Write("14: Eliminar la fecha de finalizacion de una legislatura.");
			S.Write("15: Insertar diputado a una legislatura.");
			S.Write("16: Establecer el conjunto de diputados de una legislatura.");
			S.Write("17: Consultar el conjunto de diputados de una legislatura.");
			S.Write("18: Comprobar si el diputado es activo en la legislatura.");
			S.Write("19: Eliminar un diputado de una legislatura.");
			S.Write("20: Eliminar todos los diputados de la legislatura.");
			S.Write("21: Eliminar un diputado de todas las legislaturas.");
			S.Write("22: Consultar la legislatura a la que pertenece una fecha.");
			S.Write("23: Consultar el identificador de la ultima legislatura.");
			codi = E.ReadInteger();
			switch (codi) {
			case -2:
				S.Write("Selecciona una opcion:");
				S.Write("1: Utilizar la consola para entrada y salida");
				S.Write("2: Utilizar la consola para entrada y un fichero para salida");
				S.Write("3: Utilizar un fichero para entrada y la consola para salida");
				S.Write("4: Utilizar un fichero para entrada y salida");
				Integer io = E.ReadInteger();
				String fIn = "";
				String fOut = "";
				switch(io) {
				case 2:
					fOut = E.ReadString();
					E = new ConsolaEntrada();
					S = new FitxerSortida(fOut);
				break;
				case 3:
					fIn = E.ReadString();
					E = new FitxerEntrada(fIn);
					S = new ConsolaSortida();
				break;
				case 4:
					fIn = E.ReadString();
					fOut = E.ReadString();
					E = new FitxerEntrada(fIn);
					S = new FitxerSortida(fOut);
				break;
				default:
					E = new ConsolaEntrada();
					S = new ConsolaSortida();
				break;
				}
			break;
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 1:
				S.Write(CDL.numeroLegislaturas());
			break;
			case 2:
				n = E.ReadInteger();
				Set<Legislatura> S1 = new TreeSet<Legislatura>();
				for (Integer i = 0; i < n; ++i) S1.add(legislatura(E));
				CDL.addAll(S1);
			break;
			case 3:
				for (Legislatura L:CDL.getAll()) {
					String[] out = new String[3];
					out[0] = L.getID().toString();
					out[1] = L.getFechaInicio().toString();
					out[2] = L.getFechaFinal().toString();
					S.Write(3, out);
				}
			break;
			case 4:
				Set<String> S2 = new TreeSet<String>();
				for (Integer i:CDL.getIDs()){
					S2.add(i.toString());
				}
				S.Write(S2);
			break;
			case 5:
				CDL.addLegislatura(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 6:
				CDL.addLegislatura(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 7:
				S.Write("La legislatura" + (CDL.existsLegislatura(E.ReadInteger())?" ":" no ") + "existe.");
			break;
			case 8:
				CDL.removeLegislatura(E.ReadInteger());
			break;
			case 9:
				CDL.setFechaInicio(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 10:
				CDL.setFechaFinal(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 11:
				S.Write(CDL.getFechaInicio(E.ReadInteger()).toString());
			break;
			case 12:
				S.Write(CDL.getFechaFinal(E.ReadInteger()).toString());
			break;
			case 13:
				S.Write("La legislatura" + (CDL.hasFechaFinal(E.ReadInteger())?" ":" no ") + "tiene fecha de finalizacion.");
			break;
			case 14:
				CDL.removeFechaFinal(E.ReadInteger());
			break;
			case 15:
				CDL.addDiputado(E.ReadInteger(), E.ReadString());
			break;
			case 16:
				n = E.ReadInteger();
				Integer idLegislatura = E.ReadInteger();
				Set<String> S3 = E.ReadSetString(n);
				CDL.setDiputados(idLegislatura, S3);
			break;
			case 17:
				Set<String> S4 = CDL.getDiputados(E.ReadInteger());
				S.Write(S4);
				break;
			case 18:
				S.Write("El diputado" + (CDL.existsDiputado(E.ReadInteger(), E.ReadString())?" ":" no ") + "esta activo en esta legislatura.");
			break;
			case 19:
				CDL.removeDiputado(E.ReadInteger(), E.ReadString());
			break;
			case 20:
				CDL.removeDiputados(E.ReadInteger());
			break;
			case 21:
				CDL.removeDiputadoFromLegislaturas(E.ReadString());
			break;
			case 22:
				S.Write(CDL.getID(new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger())));
			break;
			case 23:
				S.Write(CDL.getIDLast());
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
			if (CDL.hasCodiError()) S.Write(CDL.getCodiError().getMensajeError());
		}
	}
}