package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import dominio.Diputado;
import io.*;

/**
 * Driver para la clase Diputado.
 * @author David Moran
 * @version 03/05/2015 01:02
 */
public class DiputadoDriver {
	
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
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		Diputado D = Diputado.NULL;
		Set<String> setS = new TreeSet<String>();
		Set<Integer> setI = new TreeSet<Integer>();
		Integer codi = -2;
		while (codi < -1 || codi > 2) {
			S.Write("Selecciona una operacion:");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("0: Insertar un nuevo diputado nulo.");
			S.Write("1: Insertar un nuevo diputado(por atributos).");
			S.Write("2: Insertar un nuevo diputado(por copia).");
			codi = E.ReadInteger();
			switch (codi) {
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 0:
				D = Diputado.NULL;
			break;
			case 1:
				D = diputado(E);
			break;
			case 2:
				Diputado aux = diputado(E);
				D = new Diputado(aux.getNombre(), aux);
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		while (codi != -1) {
			S.Write("Selecciona una operacion:");
			S.Write("-2: Modificar la configuracion I/O.");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("0: Insertar un nuevo diputado nulo.");
			S.Write("1: Insertar un nuevo diputado(por atributos).");
			S.Write("2: Insertar un nuevo diputado(por copia).");
			S.Write("3: Consultar el nombre del diputado.");
			S.Write("4: Consultar el partido politico del diputado.");
			S.Write("5: Consultar el estado del diputado.");
			S.Write("6: Consultar la fecha de nacimiento del diputado.");
			S.Write("7: Consultar las legislaturas activas del diputado.");
			S.Write("8: Comprobar si el diputado es activo en una legislatura.");
			S.Write("9: Comprobar si el diputado es un diputado nulo.");
			S.Write("10: Modificar el partido politico del diputado.");
			S.Write("11: Modificar el estado del diputado.");
			S.Write("12: Modificar la fecha de nacimiento del diputado.");
			S.Write("13: Insertar una nueva legislatura en el diputado.");
			S.Write("14: Establecer las n legislaturas del diputado.");
			S.Write("15: Eliminar una legislatura del diputado.");
			S.Write("16: Eliminar todas las legislatura del diputado.");
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
				D = Diputado.NULL;
			break;
			case 1:
				D = diputado(E);
			break;
			case 2:
				Diputado aux = diputado(E);
				D = new Diputado(aux.getNombre(), aux);
			break;
			case 3:
				S.Write(D.getNombre());
			break;
			case 4:
				S.Write(D.getPartidoPolitico());
			break;
			case 5:
				S.Write(D.getEstado());
			break;
			case 6:
				S.Write(D.getFechaDeNacimiento().toString());
			break;
			case 7:
				setS.clear();
				for (Integer i:D.getLegislaturas()) setS.add(i.toString());
				S.Write(setS);
			break;
			case 8:
				Integer legislatura = E.ReadInteger();
				S.Write("El diputado" + (D.esActivo(legislatura)?" ":" no ") + "es activo en la legislatura.");
			break;
			case 9:
				S.Write("El diputado" + (D.esNull()?" ":" no ") + "es nulo.");
			break;
			case 10:
				D.setPartidoPolitico(E.ReadString());
			break;
			case 11:
				D.setEstado(E.ReadString());
			break;
			case 12:
				D.setFechaNacimiento(new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 13:
				D.addLegistura(E.ReadInteger());
			break;
			case 14:
				setI.clear();
				Integer n = E.ReadInteger();
				setS = E.ReadSetString(n);
				for (String s:setS) setI.add(Integer.parseInt(s));
				D.setLegisturas(setI);
			break;
			case 15:
				D.removeLegistura(E.ReadInteger());
			break;
			case 16:
				D.removeLegisturas();
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
	}
}