package drivers;

import time.Date;
import io.*;

/**
 * Driver para la clase Date.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class DateDriver {
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		Date D = Date.NULL;
		Boolean fitxer = false;
		Integer codi = -2;
		while (codi < -1 || codi > 2) {
			S.Write("Selecciona una operacion:");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("0: Insertar una nueva fecha nula.");
			S.Write("1: Insertar una nueva fecha (por atributos).");
			S.Write("2: Insertar una nueva fecha (por copia).");
			codi = E.ReadInteger();
			switch (codi) {
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 0:
				D = Date.NULL;
			break;
			case 1:
				D = new Date (E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
			break;
			case 2:
				Date aux = new Date (E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
				D = new Date(aux);
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		while (codi != -1) {
			if (!fitxer){
				S.Write("Selecciona una operacion:");
				S.Write("-2: Modificar la configuracion I/O.");
				S.Write("-1: Finalizar la ejecucion.");
				S.Write("0: Insertar una nueva fecha nula.");
				S.Write("1: Insertar una nueva fecha (por atributos).");
				S.Write("2: Insertar una nueva fecha (por copia).");
				S.Write("3: Consultar el dia de la fecha.");
				S.Write("4: Consultar el mes de la fecha.");
				S.Write("5: Consultar el " + '"' + "year" + '"' +  " de la fecha.");
				S.Write("6: Comprobar si la fecha es valida.");
				S.Write("7: Comprobar si la fecha es nula.");
				S.Write("8: Consultar la fecha.");
				S.Write("9: Consultar la fecha con el mes en nombre.");
				S.Write("10: Comparar si la fecha es igual a otra.");
				S.Write("11: Comparar la fecha con otra.");
				S.Write("12: Modificar el dia de la fecha.");
				S.Write("13: Modificar el mes de la fecha.");
				S.Write("14: Modificar el " + '"' + "year" + '"' +  " de la fecha.");
				S.Write("15: Incrementar la fecha n dias.");
				S.Write("16: Comprobar si un año es bisiesto.");
				S.Write("17: Calcular la mediana de dos fechas.");
			}
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
					fitxer = true;
				break;
				case 3:
					fIn = E.ReadString();
					E = new FitxerEntrada(fIn);
					S = new ConsolaSortida();
					fitxer = false;
				break;
				case 4:
					fIn = E.ReadString();
					fOut = E.ReadString();
					E = new FitxerEntrada(fIn);
					S = new FitxerSortida(fOut);
					fitxer = true;
				break;
				default:
					E = new ConsolaEntrada();
					S = new ConsolaSortida();
					fitxer = false;
				break;
				}
			break;
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 0:
				D = Date.NULL;
			break;
			case 1:
				D = new Date (E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
			break;
			case 2:
				Date aux = new Date (E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
				D = new Date(aux);
			break;
			case 3:
				S.Write(D.getDay());
			break;
			case 4:
				S.Write(D.getMonth());
			break;
			case 5:
				S.Write(D.getYear());
			break;
			case 6:
				S.Write("La fecha" + (D.esValida() ? " " : " no ") + "es valida.");
			break;
			case 7:
				S.Write("La fecha" + (D.esNull() ? " " : " no ") + "es nula.");
			break;
			case 8:
				S.Write(D.toString());
			break;
			case 9:
				S.Write(D.toNamedString());
			break;
			case 10:
				Date D2 = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
				S.Write("Las fechas" + (D.equals(D2) ? " " : " no ") + "son iguales.");
			break;
			case 11:
				Date D3 = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
				switch(D.compareTo(D3)) {
				case -1:
					S.Write("La fecha es menor.");
				break;
				case 0:
					S.Write("Las fechas son iguales.");
				break;
				case 1:
					S.Write("La fecha es mayor.");
				break;
				}
			break;
			case 12:
				D.setDay(E.ReadInteger());
			break;
			case 13:
				D.setMonth(E.ReadInteger());
			break;
			case 14:
				D.setYear(E.ReadInteger());
			break;
			case 15:
				D.incremento(E.ReadInteger());
			break;
			case 16:
				Integer year = E.ReadInteger();
				S.Write(year.toString() + (Date.esBisiesto(year) ? " " : " no ") + "es bisiesto.");
			break;
			case 17:
				Date D4 = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());;
				Date D5 = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());;
				S.Write(Date.mediana(D4, D5).toString());
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		S.close();
	}
}