package drivers;

import io.*;

/**
 * Driver para el controlador las clases de Entrada/Salida.
 * @author David Moran
 * @version 06/05/2015 15:00
 */
public class IODriver {
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		Boolean fitxer = false;
		Integer codi = 0;
		Integer n = 0;
		while (codi != -1) {
			if (!fitxer) {
				S.Write("Selecciona una operacion:");
				S.Write("-2: Modificar la configuracion I/O.");
				S.Write("-1: Finalizar la ejecucion.");
				S.Write("1: Un String.");
				S.Write("2: Una linea.");
				S.Write("3: Un caracter.");
				S.Write("4: Un entero.");
				S.Write("5: Un Double.");
				S.Write("6: Un Boolean.");
				S.Write("7: Un Long.");
				S.Write("8: n Strings.");
				S.Write("9: n caracteres.");
				S.Write("10: n enteros.");
				S.Write("11: n Doubles.");
				S.Write("12: n Booleans.");
				S.Write("13: n Longs.");
				S.Write("14: n Strings en un set.");
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
			case 1:
				S.Write(E.ReadString());
			break;
			case 2:
				S.Write(E.ReadLine());
			break;
			case 3:
				S.Write(E.ReadCharacter());
			break;
			case 4:
				S.Write(E.ReadInteger());
			break;
			case 5:
				S.Write(E.ReadDouble());
			break;
			case 6:
				S.Write(E.ReadBoolean());
			break;
			case 7:
				S.Write(E.ReadLong());
			break;
			case 8:
				n = E.ReadInteger();
				S.Write(n, E.ReadString(n));
			break;
			case 9:
				n = E.ReadInteger();
				S.Write(n, E.ReadCharacter(n));
			break;
			case 10:
				n = E.ReadInteger();
				S.Write(n, E.ReadInteger(n));
			break;
			case 11:
				n = E.ReadInteger();
				S.Write(n, E.ReadDouble(n));
			break;
			case 12:
				n = E.ReadInteger();
				S.Write(n, E.ReadBoolean(n));
			break;
			case 13:
				n = E.ReadInteger();
			break;
			case 14:
				S.Write(E.ReadSetString(E.ReadInteger()));
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		S.close();
	}
}