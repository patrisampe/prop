package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import utiles.Conjunto;
import dominio.Diputado;
import dominio.Legislatura;
import io.*;

/**
 * Driver para la clase Conjunto.
 * @author David Moran
 * @version 07/05/2015 11:30
 */
public class ConjuntoDriver {
	
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
		Integer n = 0;
		Integer codi = -2;
		Boolean fitxer = false;
		while (codi < -1 || codi > 2) {
			S.Write("Selecciona una operacion:");
			S.Write("-1: Finalizar la ejecucion.");
			S.Write("1: Ejecucion con diputados.");
			S.Write("2: Ejecucion con legislaturas.");
			codi = E.ReadInteger();
			switch (codi) {
			case -1:
				S.Write("Finalizando el driver...");
			break;
			case 1:
				Conjunto<Diputado> CD = new Conjunto<Diputado>(Diputado.class);
				Set<Diputado> set = new TreeSet<Diputado>();
				while (codi != -1) {
					if (!fitxer) {
						S.Write("Selecciona una operacion:");
						S.Write("-2: Modificar la configuracion I/O.");
						S.Write("-1: Finalizar la ejecucion.");
						S.Write("1: Replicar el conjunto.");
						S.Write("2: Consultar el tipo contenido.");
						S.Write("3: Comprobar el tipo de la clave externa.");
						S.Write("4: Consultar el numero de elementos del conjunto.");
						S.Write("5: Comprobar si el conjunto esta vacio.");
						S.Write("6: Eliminar todos los elementos del conjunto.");
						S.Write("7: Insertar un conjunto de diputados.");
						S.Write("8: Consultar el conjunto de diputados.");
						S.Write("9: Consultar el conjunto de nombres de diputado.");
						S.Write("10: Insertar un diputado en el conjunto.");
						S.Write("11: Consultar un diputado en el conjunto.");
						S.Write("12: Comprobar si un diputado existe en el conjunto.");
						S.Write("13: Eliminar un diputado del conjunto.");
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
						CD = new Conjunto<Diputado>(CD);
					break;
					case 2:
						S.Write(CD.getValueType().getName());
					break;
					case 3:
						S.Write("La clave externa es " + (CD.KeyTypeIsInteger()?"Integer.":"String."));
					break;
					case 4:
						S.Write(CD.size());
					break;
					case 5:
						S.Write("El conjunto" + (CD.isEmpty()?" ":" no ") + "esta vacio.");
					break;
					case 6:
						CD.clear();
					break;
					case 7:
						n = E.ReadInteger();
						for (Integer i = 0; i < n; ++i) {
							set.add(diputado(E));
						}
						CD.addAll(set);
					break;			
					case 8:
						for (Diputado D:CD.getAll()) {
							String[] out = new String[4];
							out[0] = D.getNombre();
							out[1] = D.getPartidoPolitico();
							out[2] = D.getEstado();
							out[3] = D.getFechaDeNacimiento().toString();
							S.Write(4, out);
						}
					break;
					case 9:
						for (String s:CD.getStringKeys()) {
							S.Write(s);
						}
					break;
					case 10:
						Diputado D2 = diputado(E);
						CD.add(D2);
					break;
					case 11:
						Diputado D3 = CD.get(E.ReadString());
						String[] out2 = new String[4];
						out2[0] = D3.getNombre();
						out2[1] = D3.getPartidoPolitico();
						out2[2] = D3.getEstado();
						out2[3] = D3.getFechaDeNacimiento().toString();
						S.Write(4, out2);
					break;
					case 12:
						S.Write("El diputado" + (CD.exists(E.ReadString())?" ":" no ") + "existe.");
					break;
					case 13:
						CD.remove(E.ReadString());
					break;
					default:
						S.Write("Codigo incorrecto.");
					break;
					}
				}
			break;
			case 2:
				Conjunto<Legislatura> CL = new Conjunto<Legislatura>(Legislatura.class);
				Set<Legislatura> set2 = new TreeSet<Legislatura>();
				while (codi != -1) {
					if (!fitxer) {
						S.Write("Selecciona una operacion:");
						S.Write("-2: Modificar la configuracion I/O.");
						S.Write("-1: Finalizar la ejecucion.");
						S.Write("1: Replicar el conjunto.");
						S.Write("2: Consultar el tipo contenido.");
						S.Write("3: Comprobar el tipo de la clave externa.");
						S.Write("4: Consultar el numero de elementos del conjunto.");
						S.Write("5: Comprobar si el conjunto esta vacio.");
						S.Write("6: Eliminar todos los elementos del conjunto.");
						S.Write("7: Insertar un conjunto de legislaturas.");
						S.Write("8: Consultar el conjunto de legislaturas.");
						S.Write("9: Consultar el conjunto de identificadores de legislaturas.");
						S.Write("10: Insertar una legislatura en el conjunto.");
						S.Write("11: Consultar una legislatura en el conjunto.");
						S.Write("12: Comprobar si una legislatura existe en el conjunto.");
						S.Write("13: Eliminar una legislatura del conjunto.");
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
						CL = new Conjunto<Legislatura>(CL);
					break;
					case 2:
						S.Write(CL.getValueType().getName());
					break;
					case 3:
						S.Write("La clave externa es " + (CL.KeyTypeIsInteger()?"Integer.":"String."));
					break;
					case 4:
						S.Write(CL.size());
					break;
					case 5:
						S.Write("El conjunto" + (CL.isEmpty()?" ":" no ") + "esta vacio.");
					break;
					case 6:
						CL.clear();
					break;
					case 7:
						n = E.ReadInteger();
						for (Integer i = 0; i < n; ++i) {
							set2.add(legislatura(E));
						}
						CL.addAll(set2);
					break;			
					case 8:
						for (Legislatura L:CL.getAll()) {
							String[] out = new String[3];
							out[0] = L.getID().toString();
							out[1] = L.getFechaInicio().toString();
							out[2] = L.getFechaFinal().toString();
							S.Write(3, out);
						}
					break;
					case 9:
						for (Integer i:CL.getIntegerKeys()) {
							S.Write(i);
						}
					break;
					case 10:
						Legislatura L2 = legislatura(E);
						CL.add(L2);
					break;
					case 11:
						Legislatura L3 = CL.get(E.ReadInteger());
						String[] out2 = new String[3];
						out2[0] = L3.getID().toString();
						out2[1] = L3.getFechaInicio().toString();
						out2[2] = L3.getFechaFinal().toString();
						S.Write(3, out2);
					break;
					case 12:
						S.Write("La legislatura" + (CL.exists(E.ReadInteger())?" ":" no ") + "existe.");
					break;
					case 13:
						CL.remove(E.ReadInteger());
					break;
					default:
						S.Write("Codigo incorrecto.");
					break;
					}
				}
			break;
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
		}
		S.close();
	}
}