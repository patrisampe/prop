package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import controladores.ControladorDominioDiputado;
import controladores.ControladorDominioLegislatura;
import dominio.Diputado;
import dominio.Legislatura;
import io.*;

/**
 * Driver para el controlador de dominio de diputados.
 * @author David Moran
 * @version 11/05/2015 14:00
 */
public class ControladorDominioDiputadoDriver {
	
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
		Salida S = new ConsolaSalida();
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		Integer n;
		Integer codi = 0;
		Boolean fitxer = false;
		while (codi != -1) {
			if (!fitxer) {
				S.Write("Selecciona una operacion:");
				S.Write("-2: Modificar la configuracion I/O.");
				S.Write("-1: Finalizar la ejecucion.");
				S.Write("1: Consultar el numero de diputados del sistema.");
				S.Write("2: Insertar un conjunto de diputados.");
				S.Write("3: Consultar el conjunto de diputados.");
				S.Write("4: Consultar los nombres de los diputados.");
				S.Write("5: Insertar un nuevo diputado.");
				S.Write("6: Comprobar si un diputado existe.");
				S.Write("7: Eliminar un diputado.");
				S.Write("8: Modificar el partido politico de un diputado.");
				S.Write("9: Modificar el estado de un diputado.");
				S.Write("10: Modificar la fecha de nacimiento de un diputado.");
				S.Write("11: Consultar el partido politico de un diputado.");
				S.Write("12: Consulta el estado de un diputado.");
				S.Write("13: Consulta la fecha de nacimiento de un diputado.");
				S.Write("14: Insertar legislatura a un diputado.");
				S.Write("15: Establecer el conjunto de legislaturas de un diputado.");
				S.Write("16: Consultar el conjunto de legislaturas de un diputado.");
				S.Write("17: Comprobar si el diputado es activo en la legislatura.");
				S.Write("18: Eliminar una legislatura de un diputado.");
				S.Write("19: Eliminar todas las legislaturas de un diputado.");
				S.Write("20: Eliminar una legislatura de todos los diputados.");
				S.Write("21: Insertar una nueva legislatura en el sistema.");
				S.Write("22: Insertar una nueva legislatura sin fecha de finalizacion en el sistema.");
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
					S = new FicheroSalida(fOut);
					fitxer = true;
				break;
				case 3:
					fIn = E.ReadString();
					E = new FicheroEntrada(fIn);
					S = new ConsolaSalida();
					fitxer = false;
				break;
				case 4:
					fIn = E.ReadString();
					fOut = E.ReadString();
					E = new FicheroEntrada(fIn);
					S = new FicheroSalida(fOut);
					fitxer = true;
				break;
				default:
					E = new ConsolaEntrada();
					S = new ConsolaSalida();
					fitxer = false;
				break;
				}
			break;
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 1:
				S.Write(CDD.numeroDiputados());
			break;
			case 2:
				n = E.ReadInteger();
				Set<Diputado> S1 = new TreeSet<Diputado>();
				for (Integer i = 0; i < n; ++i) S1.add(diputado(E));
				CDD.addAll(S1);
			break;
			case 3:
				for (Diputado D:CDD.getAll()) {
					String[] out = new String[4];
					out[0] = D.getNombre();
					out[1] = D.getPartidoPolitico();
					out[2] = D.getEstado();
					out[3] = D.getFechaDeNacimiento().toString();
					S.Write(4, out);
				}
			break;
			case 4:
				S.Write(CDD.getNombres());
			break;
			case 5:
				CDD.addDiputado(E.ReadString(), E.ReadString(), E.ReadString(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 6:
				S.Write("El diputado" + (CDD.existsDiputado(E.ReadString())?" ":" no ") + "existe.");
			break;
			case 7:
				CDD.removeDiputado(E.ReadString());
			break;
			case 8:
				CDD.setPartidoPolitico(E.ReadString(), E.ReadString());
			break;
			case 9:
				CDD.setEstado(E.ReadString(), E.ReadString());
			break;
			case 10:
				CDD.setFechaDeNacimiento(E.ReadString(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 11:
				S.Write(CDD.getPartidoPolitico(E.ReadString()));
			break;
			case 12:
				S.Write(CDD.getEstado(E.ReadString()));
			break;
			case 13:
				S.Write(CDD.getFechaDeNacimiento(E.ReadString()).toString());
			break;
			case 14:
				CDD.addLegistura(E.ReadString(), E.ReadInteger());
			break;
			case 15:
				n = E.ReadInteger();
				String nombreDiputado = E.ReadString();
				Set<Integer> S2 = new TreeSet<Integer>();
				for (Integer i = 0; i < n; ++i) S2.add(E.ReadInteger());
				CDD.setLegisturas(nombreDiputado, S2);
			break;
			case 16:
				Set<Integer> S3 = CDD.getLegislaturas(E.ReadString());
				for (Integer i:S3) S.Write(i);
			break;
			case 17:
				S.Write("El diputado" + (CDD.existsLegistura(E.ReadString(), E.ReadInteger())?" ":" no ") + "esta activo en esta legislatura.");
			break;
			case 18:
				CDD.removeLegistura(E.ReadString(), E.ReadInteger());
			break;
			case 19:
				CDD.removeLegisturas(E.ReadString());
			break;
			case 20:
				CDD.removeLegislaturaFromDiputados(E.ReadInteger());
			break;
			case 21:
				CDL.addLegislatura(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			case 22:
				CDL.addLegislatura(E.ReadInteger(), new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger()));
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
			if (CDD.hasError()) S.Write(CDD.getError().getMensajeError());
		}
		S.close();
	}
}