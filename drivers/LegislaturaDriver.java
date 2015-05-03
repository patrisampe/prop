package drivers;

import time.Date;
import dominio.Legislatura;
import io.*;

/**
 * Driver para la clase Legislatura.
 * @author David Moran
 * @version 03/05/2015 01:02
 */
public class LegislaturaDriver {
	
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

	/**
	 * Lector de legislaturas sin fecha de finalizacion.
	 * @param E - Interficie de entrada.
	 * @return Legislatura leida por la entrada.
	 */
	public static Legislatura legislaturaSinFin(Entrada E){
		Integer identificador = E.ReadInteger();
		Date fechaInicio = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		return new Legislatura(identificador, fechaInicio);
	}
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		Legislatura L = Legislatura.NULL;
		Integer codi = -2;
		while (codi < -1 || codi > 2) {
			S.Write("Selecciona una operacion:");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("0: Insertar una nueva legislatura nula.");
			S.Write("1: Insertar una nueva legislatura(por atributos).");
			S.Write("2: Insertar una nueva legislatura sin fin(por atributos).");
			S.Write("3: Insertar una nueva legislatura(por copia).");
			codi = E.ReadInteger();
			switch (codi) {
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 0:
				L = Legislatura.NULL;
			break;
			case 1:
				L = legislatura(E);
			break;
			case 2:
				L = legislaturaSinFin(E);
			break;
			case 3:
				Legislatura aux = legislatura(E);
				L = new Legislatura(aux.getID(), aux);
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		while (codi != -1) {
			S.Write("Selecciona una operacion:");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("0: Insertar una nueva legislatura nula.");
			S.Write("1: Insertar una nueva legislatura(por atributos).");
			S.Write("2: Insertar una nueva legislatura sin fin(por atributos).");
			S.Write("3: Insertar una nueva legislatura(por copia).");
			S.Write("4: Consultar el identificador de la legislatura.");
			S.Write("5: Consultar la fecha de inicio de la legislatura.");
			S.Write("6: Comprobar si la legislatura tiene fecha de finalizacion.");
			S.Write("7: Consultar la fecha de finalizacion de la legislatura.");
			S.Write("8: Consultar los diputados activos de la legislatura.");
			S.Write("9: Comprobar si el la legislatura tiene el diputado activo.");
			S.Write("10: Comprobar si la legislatura es una legislatura nula.");
			S.Write("11: Modificar la fecha de inicio de la legislatura.");
			S.Write("12: Modificar la fecha de finalizacion de la legislatura.");
			S.Write("13: Eliminar la fecha de finalizacion de la legislatura.");
			S.Write("14: Insertar un nuevo diputado en la legislatura.");
			S.Write("15: Establecer los n diputados de la legislatura.");
			S.Write("16: Eliminar un diputado de la legislatura.");
			S.Write("17: Eliminar todos los diputados de la legislatura.");
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
			case 0:
				L = Legislatura.NULL;
			break;
			case 1:
				L = legislatura(E);
			break;
			case 2:
				L = legislaturaSinFin(E);
			break;
			case 3:
				Legislatura aux = legislatura(E);
				L = new Legislatura(aux.getID(), aux);
			break;
			case 4:
				S.Write(L.getID());
			break;
			case 5:
				S.Write(L.getFechaInicio().toString());
			break;
			case 6:
				S.Write("La legislatura" + (L.hasFechaFinal()? " ":" no ") + "tiene fecha de finalizacion");
			break;
			case 7:
				S.Write((L.hasFechaFinal()? L.getFechaFinal().toString():"NULL"));
			break;
			case 8:
				S.Write(L.getDiputados());
			break;
			case 9:
				String diputado = E.ReadString();
				S.Write("La legislatura" + (L.esActivo(diputado)?" ":" no ") + "tiene al diputado activo.");
			break;
			case 10:
				S.Write("La legislatura" + (L.esNull()?" ":" no ") + "es nula.");
			break;
			case 11:
				L.setFechaInicio(new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 12:
				L.setFechaFinal(new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 13:
				L.removeFechaFinal();
			break;
			case 14:
				L.addDiputado(E.ReadString());
			break;
			case 15:
				Integer n = E.ReadInteger();
				L.setDiputados(E.ReadSetString(n));
			break;
			case 16:
				L.removeDiputado(E.ReadString());
			break;
			case 17:
				L.removeDiputados();
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
	}
}