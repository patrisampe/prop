package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.Sortida;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import controladores.ControladorDominioResultado;
import time.Date;
import time.DateInterval;
import utiles.CodiError;
import dominio.Criterio;
import dominio.TipoAlgoritmo;

public class ControladorDominioResultadoDriver {
	private static Entrada EC, EF;
	private static Sortida SC, SF;
	private static String fichEnt;
	private static String fichSal;
	private static ControladorDominioResultado controlDomRes;

	
	private static Integer muestraMenuPrincipal() {
		SC.Write("0.-Salir");
		SC.Write("1.-Cambiar fichero de pruebas (ACTUAL: " + fichEnt + ")");
		SC.Write("2.-Cambiar fichero de salida  (ACTUAL: " + fichSal + ")");
		SC.Write("3.-Cargar conjunto de resultados del fichero de pruebas actual");
		SC.Write("4.-Guardar conjunto de resultados en el fichero de salida actual");
		SC.Write("5.-Probar métodos creadores/modificadores de la clase");
		SC.Write("6.-Probar métodos consultores de la clase");
		return EC.ReadInteger();
	}
	
	private static Integer muestraMenuMetodosCreaMod() {
		SC.Write("0.-Atrás");
		SC.Write("1.-Nuevo resultado de búsqueda por periodo");
		SC.Write("2.-Nuevo resultado de búsqueda por diputado");
		SC.Write("3.-Registra ultimo resultado");
		SC.Write("4.-Cambiar nombre de un resultado");
		SC.Write("5.-Borrar un resultado");
		SC.Write("6.-Borrar un diputado en cuaquier resultado");
		SC.Write("7.-Añadir diputado a un resultado");
		SC.Write("8.-Borrar diputado de un resultado");
		SC.Write("9.-Mover diputado de un resultado");
		return EC.ReadInteger();
	}
	
	private static Integer muestraMenuMetodosCons() {
		SC.Write("0.-Atrás");
		SC.Write("1.-Existe resultado?");
		SC.Write("2.-Consultar nombre de los resultados");
		SC.Write("3.-Consultar el tipo de un resultado");
		SC.Write("5.-Ha sido modificado?");
		SC.Write("6.-Consultar indice de afinidad");
		SC.Write("7.-Consultar tipo de algoritmo");
		SC.Write("8.-Consultar periodo");
		SC.Write("9.-Consultar resultados");
		SC.Write("10.-Consultar si ha ocurrido un error");
		SC.Write("11.-Consultar código de error");
		return EC.ReadInteger();
	}
	
	private static void cambiaFicheroEntrada() {
		SC.Write("Introduzca un nuevo archivo de pruebas: ");
		fichEnt = EC.ReadString();
		SC.Write("El nuevo archivo de pruebas es: " + fichEnt);
	}
	
	private static void cambiaFicheroSalida() {
		SC.Write("Introduzca un nuevo archivo de salida: ");
		fichSal = EC.ReadString();
		SC.Write("El nuevo archivo de salida es: " + fichSal);
	}
	
	private static void nuevoResultadoPorPeriodo() {
		SC.Write("Introduzca indice de afinidad: ");
		Integer indiceAfinidad = EC.ReadInteger();
		SC.Write("Seleccione el tipo de algoritmo(CliquePercolation, GirvanNewmann, Louvain): ");
		TipoAlgoritmo algoritmo = TipoAlgoritmo.valueOf(EC.ReadString());
		SC.Write("Indique el número de importancias de eventos temporales");
		Map<String, Integer> importancias = new TreeMap<String, Integer>();
		Integer numimp = EC.ReadInteger();
		for (Integer i = 0; i < numimp; ++i) {
			SC.Write("Introduzca el nombre del evento: ");
			String nombreEv = EC.ReadString();
			SC.Write("Introduzca su importancia: ");
			Integer importan = EC.ReadInteger();
			importancias.put(nombreEv, importan);			
		}
		SC.Write("Indique la fecha de Inicio: ");
		Date fechaInicio = Date.stringToDate(EC.ReadString());
		SC.Write("Indique la fecha de Fin: ");
		Date fechaFin = Date.stringToDate(EC.ReadString());
		DateInterval periodo = new DateInterval(fechaInicio, fechaFin);
		SC.Write("Indique el criterio de búsqueda(Standard, Estado, PartidoPolitico, ParecidoNombres): ");
		Criterio criterio = Criterio.valueOf(EC.ReadString());
		controlDomRes.nuevoResultadoPorPeriodo(indiceAfinidad, algoritmo, importancias, periodo, criterio);
	}
	
	private static void nuevoResultadoPorDiputado() {
		SC.Write("Introduzca indice de afinidad: ");
		Integer indiceAfinidad = EC.ReadInteger();
		SC.Write("Seleccione el tipo de algoritmo(CliquePercolation, GirvanNewmann, Louvain): ");
		TipoAlgoritmo algoritmo = TipoAlgoritmo.valueOf(EC.ReadString());
		SC.Write("Indique el número de importancias de eventos temporales");
		Map<String, Integer> importancias = new TreeMap<String, Integer>();
		Integer numimp = EC.ReadInteger();
		for (Integer i = 0; i < numimp; ++i) {
			SC.Write("Introduzca el nombre del evento: ");
			String nombreEv = EC.ReadString();
			SC.Write("Introduzca su importancia: ");
			Integer importan = EC.ReadInteger();
			importancias.put(nombreEv, importan);			
		}
		SC.Write("Indique el lapso de tiempo: ");
		Integer lapsoDeTiempo = EC.ReadInteger();
		SC.Write("Introduzca el nombre del diputado relevante:");
		String diputadoRelevante = EC.ReadString();
		SC.Write("Indique el criterio de búsqueda(Standard, Estado, PartidoPolitico, ParecidoNombres): ");
		Criterio criterio = Criterio.valueOf(EC.ReadString());
		controlDomRes.nuevoResultadoPorDiputado(indiceAfinidad, algoritmo, importancias, lapsoDeTiempo, diputadoRelevante, criterio);
	}
	
	private static void cargaConjunto() {
		Integer n = EF.ReadInteger();
		for (Integer i = 0; i < n; ++i) {
			String tipo = EF.ReadString();
			if (tipo.equals("Búsqueda por diputado")) {
				
			}
		}
	}
	
	private static void guardaConjunto() {
		Set<String> resultados = controlDomRes.getNombreResultados();
		SF.Write(resultados.size());
		for (String resul:resultados) {
			SF.Write(controlDomRes.getTipoDeResultado(resul));
			SF.Write(resul);
			SF.Write(controlDomRes.getIndiceAfinidad(resul));
			SF.Write(controlDomRes.getAlgoritmo(resul));
			Map<String, Integer> importancias = controlDomRes.getImportancia(resul);
			SF.Write(importancias.size());
			for (String evento:importancias.keySet()) {
				SF.Write(evento);
				SF.Write(importancias.get(evento));
			}
			SF.Write(controlDomRes.haSidoModificado(resul));
			Vector<Set<String>> gruposAfines = controlDomRes.getResultado(resul);
			SF.Write(gruposAfines.size());
			for (Set<String> grup:gruposAfines) {
				SF.Write(grup.size());
				for (String diputado:grup) {
					SF.Write(diputado);
				}
			}
		}
		SF.close();
	}
	
	private static void registraUltimoResultado() {
		SC.Write("Introduzca el nombre del resultado: ");
		String nombre = EC.ReadString();
		controlDomRes.registraUltimoResultado(nombre);
	}
	
	private static void cambiaNombre() {
		SC.Write("Introduzca el nombre que desea cambiar: ");
		String nombreAnterior = EC.ReadString();
		SC.Write("Introduzca el nuevo nombre: ");
		String nuevoNombre = EC.ReadString();
		controlDomRes.cambiaNombre(nombreAnterior, nuevoNombre);
	}
	
	private static void removeResultado() {
		SC.Write("Introduzca el nombre del resultado que desea borrar: ");
		String nombre = EC.ReadString();
		controlDomRes.removeResultado(nombre);
	}
	
	private static void removeDiputadoDeResultados() {
		SC.Write("Introduzca el nombre del diputado que desea borrar: ");
		String nombre = EC.ReadString();
		controlDomRes.removeDiputado(nombre);
	}
	
	private static void addDiputado() {
		SC.Write("Introduzca el nombre del resultado: ");
		String nombreResultado = EC.ReadString();
		SC.Write("Introduzca el nombre del diputado: ");
		String nombreDiputado = EC.ReadString();
		SC.Write("Introduzca el identificador del grupo: ");
		Integer ID = EC.ReadInteger();
		controlDomRes.addDiputado(nombreResultado, nombreDiputado, ID);
	}
	
	private static void removeDiputado() {
		SC.Write("Introduzca el nombre del resultado: ");
		String nombreResultado = EC.ReadString();
		SC.Write("Introduzca el nombre del diputado: ");
		String nombreDiputado = EC.ReadString();
		SC.Write("Introduzca el identificador del grupo: ");
		Integer ID = EC.ReadInteger();
		controlDomRes.removeDiputado(nombreResultado, nombreDiputado, ID);
	}
	
	private static void moveDiputado() {
		SC.Write("Introduzca el nombre del resultado: ");
		String nombreResultado = EC.ReadString();
		SC.Write("Introduzca el nombre del diputado: ");
		String nombreDiputado = EC.ReadString();
		SC.Write("Introduzca el identificador del grupo origen: ");
		Integer desdeID = EC.ReadInteger();
		SC.Write("Introduzca el identificador del grupo destino: ");
		Integer hastaID = EC.ReadInteger();
		controlDomRes.moveDiputado(nombreResultado, nombreDiputado, desdeID, hastaID);
	}

	private static Boolean existeResultado() {
		SC.Write("Introduzca el nombre del resultado a buscar: ");
		String nombre = EC.ReadString();
		if (controlDomRes.existeResultado(nombre)) return true;
		else return false;
	}
	
	private static Set<String> getNombreResultados() {
		return controlDomRes.getNombreResultados();
	}
	
	private static String getTipoDeResultado() {
		SC.Write("Introduzca el nombre del resultado: ");
		String nombre = EC.ReadString();
		return controlDomRes.getTipoDeResultado(nombre).toString();
	}
	
	private static Boolean haSidoModificado() {
		SC.Write("Introduzca el nombre del resultado a comprobar: ");
		String nombre = EC.ReadString();
		return controlDomRes.haSidoModificado(nombre);
	}
	
	private static String getIndiceAfinidad() {
		SC.Write("Introduzca el nombre del resultado a buscar: ");
		String nombre = EC.ReadString();
		return controlDomRes.getIndiceAfinidad(nombre);
	}
	
	private static String getTipoAlgoritmo() {
		SC.Write("Introduzca el nombre del resultado a buscar: ");
		String nombre = EC.ReadString();
		return controlDomRes.getAlgoritmo(nombre);
	}
	
	private static String getPeriodo() {
		SC.Write("Introduzca el nombre del resultado a buscar: ");
		String nombre = EC.ReadString();
		return controlDomRes.getPeriodo(nombre);
	}
	
	private static Vector<Set<String>> getResultado() {
		SC.Write("Introduzca el nombre del resultado a buscar: ");
		String nombre = EC.ReadString();
		return controlDomRes.getResultado(nombre);
	}
	
	private static Boolean hasError() {
		return controlDomRes.hasError();
	}
	
	private static CodiError getCodiError() {
		return controlDomRes.getError();
	}
	
	private static void menuMetodosCreaMod() {
		Integer a = 1;
		while (a != 0) {
			a = muestraMenuMetodosCreaMod();
			switch (a) {
			case 1:	nuevoResultadoPorPeriodo(); break;
			case 2:	nuevoResultadoPorDiputado(); break;
			case 3:	registraUltimoResultado(); break;
			case 4:	cambiaNombre(); break;
			case 5:	removeResultado(); break;	
			case 6:	removeDiputadoDeResultados(); break;
			case 7:	addDiputado(); break;
			case 8:	removeDiputado(); break;
			case 9:	moveDiputado(); break;
			default: break;
			}
		}
	}

	private static void menuMetodosCons() {
		Integer a = 1;
		while (a != 0) {
			a = muestraMenuMetodosCons();
			switch (a) {
			case 1:	existeResultado(); break;
			case 2:	getNombreResultados(); break;
			case 3:	getTipoDeResultado(); break;
			case 4:	haSidoModificado(); break;
			case 5:	getIndiceAfinidad(); break;	
			case 6:	getTipoAlgoritmo(); break;
			case 7:	getPeriodo(); break;
			case 8:	getResultado(); break;
			case 9:	hasError(); break;
			case 10:getCodiError(); break;
			default: break;
			}
		}
	}
	
	private static void menuPrincipal() {
		Integer a = 1;
		while (a != 0) {
			a = muestraMenuPrincipal();
			switch (a) {
			case 1:	cambiaFicheroEntrada(); break;
			case 2:	cambiaFicheroSalida(); break;
			case 3:	cargaConjunto(); break;
			case 4:	guardaConjunto(); break;
			case 5: menuMetodosCreaMod(); break;	
			case 6: menuMetodosCons(); break;	
			default: break;
			}
		}
	}
	
	public static void main(String[] args) {
		EC = new ConsolaEntrada();
		SC = new ConsolaSortida();
		fichEnt = "jocproves/jocProvaResultadoDeBusquedaPorDiputado.txt";
		fichSal = "jocproves/sortidaJocProvaResultadoDeBusquedaPorDiputado.txt";
		controlDomRes = ControladorDominioResultado.getInstance();
		menuPrincipal();
	}
}
