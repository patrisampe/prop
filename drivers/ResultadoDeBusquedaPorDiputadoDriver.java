package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import time.Date;
import utiles.Conjunto;
import dominio.GrupoAfin;
import dominio.ResultadoDeBusquedaPorDiputado;
import dominio.GrupoAfinPorDiputado;
import dominio.TipoAlgoritmo;

public class ResultadoDeBusquedaPorDiputadoDriver {
	private static Entrada EC, EF;
	private static Sortida SC, SF;
	private static String fichEnt;
	private static String fichSal;
	private static ResultadoDeBusquedaPorDiputado resultado;
	
	private static Integer muestraMenuPrincipal() {
		SC.Write("0.-Salir");
		SC.Write("1.-Cambiar fichero de pruebas (ACTUAL: " + fichEnt + ")");
		SC.Write("2.-Cambiar fichero de salida  (ACTUAL: " + fichSal + ")");
		SC.Write("3.-Cargar instancia del fichero de pruebas actual");
		SC.Write("4.-Guardar instancia en el fichero de salida actual");
		SC.Write("5.-Probar métodos creadores/modificadores de la clase");
		SC.Write("6.-Probar métodos consultores de la clase");
		return EC.ReadInteger();
	}
	
	private static Integer muestraMenuMetodosCreaMod() {
		SC.Write("0.-Atrás");
		SC.Write("1.-Crear nueva instancia de Resultado de búsqueda por diputado");
		SC.Write("2.-Introducir el nombre del resultado");
		SC.Write("3.-Añadir importancia");
		SC.Write("4.-Eliminar un Diputado del resultado");
		SC.Write("5.-Añadir un Diputado a un grupo");
		SC.Write("6.-Eliminar un diputado de un grupo");
		SC.Write("7.-Mover diputado");
		SC.Write("8.-Añadir grupo");
		SC.Write("9.-Eliminar grupo");
		return EC.ReadInteger();
	}
	
	private static Integer muestraMenuMetodosCons() {
		SC.Write("0.-Atrás");
		SC.Write("1.-Existe grupo?");
		SC.Write("2.-Consultar nombre del grupo");
		SC.Write("3.-Consultar indice de afinidad");
		SC.Write("4.-Consultar algoritmo");
		SC.Write("5.-Ha sido modificado?");
		SC.Write("6.-Consultar diputado relevante");
		SC.Write("7.-Consultar periodo");
		SC.Write("8.-Consultar grupos afines");
		SC.Write("9.-Consultar resultados");
		SC.Write("10.-Consultar tipo resultado");
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
	
	private static void cargaFichero() {
		EF = new FitxerEntrada(fichEnt);
		String nombre = EF.ReadString();
		Integer indiceAfinidad = EF.ReadInteger();
		TipoAlgoritmo algoritmo = TipoAlgoritmo.valueOf(EF.ReadString());
		Integer importancias = (EF.ReadInteger());
		Map<String, Integer> importancia = new HashMap<String, Integer>();
		for (Integer i = 0; i < importancias; ++i)
			importancia.put(EF.ReadString(), EF.ReadInteger());
		Boolean modificado = EF.ReadBoolean();
		Integer lapsoDeTiempo = EF.ReadInteger();
		
		// Conjunto de Grupos Afines Por Diputado
		Conjunto<GrupoAfinPorDiputado> conjuntoDip = new Conjunto<GrupoAfinPorDiputado>(GrupoAfinPorDiputado.class);
		Integer numGrupos = EF.ReadInteger();
		for (Integer i = 0; i < numGrupos; ++i) {
			GrupoAfinPorDiputado grupo = new GrupoAfinPorDiputado(EF.ReadInteger(), Date.stringToDate(EF.ReadString()), Date.stringToDate(EF.ReadString()));
			Integer numDip = EF.ReadInteger();
			for (Integer j = 0; j < numDip; ++j)
				grupo.addDiputado(EF.ReadString());
			conjuntoDip.add(grupo.getID(), grupo);
		}
		
		String diputadoRelevante = EF.ReadString();
		resultado = new ResultadoDeBusquedaPorDiputado(nombre, indiceAfinidad, algoritmo, importancia, modificado, lapsoDeTiempo, conjuntoDip, diputadoRelevante);
		EF.close();
	}
	
	private static void guardaInstancia() {
		SF = new FitxerSortida(fichSal);
		SF.Write(resultado.getNombre());
		SF.Write(resultado.getIndiceAfinidad());
		SF.Write(resultado.getAlgoritmo().toString());
		Map<String, Integer> importancias = resultado.getImportancias();
		SF.Write(importancias.size());
		for (String evento:importancias.keySet()) {
			SF.Write(evento);
			SF.Write(importancias.get(evento));
		}
		SF.Write(resultado.esModificado());
		SF.Write(resultado.getLapsoDetiempo());
		Conjunto<GrupoAfin> conj = resultado.getGruposAfines();
		SF.Write(conj.size());
		for (GrupoAfin grup:conj.getAll()) {
			SF.Write(grup.getID());
			SF.Write(grup.getFechaInicio().toString());
			SF.Write(grup.getFechaFin().toString());
			SF.Write(grup.getDiputados().size());
			for (String diputado:grup.getDiputados())
				SF.Write(diputado);
		}
		SF.Write(resultado.getDiputadoRelevante());
		SF.close();
	}
	
	private static void nuevaInstancia() {
		SC.Write("Introduzca un nombre para el resultado:");
		String nombre = EC.ReadString();
		SC.Write("Introduzca un indice de afinidad para el resultado:");
		Integer indiceAfinidad = EC.ReadInteger();
		SC.Write("Seleccione el tipo de algoritmo(CliquePercolation, GirvanNewmann, Louvain): ");
		TipoAlgoritmo algoritmo = TipoAlgoritmo.valueOf(EC.ReadString());
		SC.Write("Indique el número de importancias de eventos temporales");
		Map<String, Integer> importancias = new HashMap<String, Integer>();
		Integer numimp = EC.ReadInteger();
		for (Integer i = 0; i < numimp; ++i) {
			SC.Write("Introduzca el nombre del evento: ");
			String nombreEv = EC.ReadString();
			SC.Write("Introduzca su importancia: ");
			Integer importan = EC.ReadInteger();
			importancias.put(nombreEv, importan);			
		}
		SC.Write("El algoritmo ha sido modificado? (true/false): ");
		Boolean esModificado = EC.ReadBoolean();
		SC.Write("Indique el lapso de tiempo: ");
		Integer lapsoDeTiempo = EC.ReadInteger();
		Conjunto<GrupoAfinPorDiputado> con = new Conjunto<GrupoAfinPorDiputado>(GrupoAfinPorDiputado.class);
		SC.Write("Indique el número de grupos afines que contiene el resultado:");
		Integer numGrup = EC.ReadInteger();
		for (Integer i = 0; i < numGrup; ++i) {
			SC.Write("Introduzca un ID para el grupo:");
			Integer ID = EC.ReadInteger();
			SC.Write("Introduzca la fecha de inicio DD/MM/AAAA:");
			String fI = EC.ReadString();
			SC.Write("Introduzca la fecha de fin DD/MM/AAAA:");
			String fF = EC.ReadString();
			GrupoAfinPorDiputado grup = new GrupoAfinPorDiputado(ID, Date.stringToDate(fI), Date.stringToDate(fF));
			SC.Write("Indique el número de diputados del grupo:");
			Integer numDip = EC.ReadInteger();
			for (Integer j = 0; j < numDip; ++j) {
				SC.Write("Nombre: ");
				grup.addDiputado(EC.ReadString());
			}
			con.add(grup.getID(), grup);
		}
		SC.Write("Introduzca el nombre del diputado relevante:");
		String diputadoRelevante = EC.ReadString();
		resultado = new ResultadoDeBusquedaPorDiputado(nombre, indiceAfinidad, algoritmo, importancias, esModificado, lapsoDeTiempo, con, diputadoRelevante);
	}
	
	private static void setNombre() {
		SC.Write("Nombre del resultado:");
		String nomGrup = EC.ReadString();
		resultado.setNombre(nomGrup);
	}
	
	private static void addImportancia() {
		SC.Write("Nombre del evento:");
		String evento = EC.ReadString();
		SC.Write("Importancia:");
		Integer importancia = EC.ReadInteger();
		resultado.addImportancia(evento, importancia);
	}
	
	private static void eliminarDiputadoResul() {
		SC.Write("Nombre del diputado a eliminar:");
		String nombre = EC.ReadString();
		resultado.removeDiputado(nombre);
	}
	
	private static void addDiputadoAGrupo() {
		SC.Write("Nombre del diputado:");
		String diputado = EC.ReadString();
		SC.Write("Identificador del grupo:");
		Integer ID = EC.ReadInteger();
		resultado.addDiputado(diputado, ID);
	}
	
	private static void removeDiputadoDeGrupo() {
		SC.Write("Nombre del diputado:");
		String diputado = EC.ReadString();
		SC.Write("Identificador del grupo:");
		Integer ID = EC.ReadInteger();
		resultado.removeDiputado(diputado, ID);
	}
	
	private static void moverDiputado() {
		SC.Write("Nombre del diputado:");
		String diputado = EC.ReadString();
		SC.Write("Identificador del grupo de origen:");
		Integer desdeID = EC.ReadInteger();
		SC.Write("Identificador del grupo de destino:");
		Integer hastaID = EC.ReadInteger();
		resultado.moveDiputado(diputado, desdeID, hastaID);
	}
	
	private static void addGrupo() {
		SC.Write("Introduzca un ID para el grupo:");
		Integer ID = EC.ReadInteger();
		SC.Write("Introduzca la fecha de inicio DD/MM/AA:");
		String fI = EC.ReadString();
		SC.Write("Introduzca la fecha de fin DD/MM/AA:");
		String fF = EC.ReadString();
		GrupoAfinPorDiputado grup = new GrupoAfinPorDiputado(ID, Date.stringToDate(fI), Date.stringToDate(fF));
		SC.Write("Indique el número de diputados del grupo:");
		Integer numDip = EC.ReadInteger();
		for (Integer j = 0; j < numDip; ++j)
			grup.addDiputado(EC.ReadString());
		resultado.addGrupo(grup);
	}
	
	private static void removeGrupo() {
		SC.Write("Introduzca el ID del grupo a eliminar:");
		Integer ID = EC.ReadInteger();
		resultado.eliminarGrupo(ID);
	}
	
	private static void existeGrupo() {
		SC.Write("Introduzca el ID del grupo a comprobar:");
		Integer ID = EC.ReadInteger();
		if (resultado.existeGrupo(ID)) SC.Write("El grupo " + ID + " existe");
		else SC.Write("El grupo "+ ID + " no existe");
	}
	
	private static void getNombreResultado() {
		SC.Write("El nombre del resultado es: " + resultado.getNombre());
	}
	
	private static void getIndiceAfinidad() {
		SC.Write("El índice de afinidad es: " + resultado.getIndiceAfinidad());
	}
	
	private static void getAlgoritmo() {
		SC.Write("El tipo de algoritmo es: " + resultado.getAlgoritmo().toString());
	}
	
	private static void esModificado() {
		if (resultado.esModificado()) SC.Write("El resultado ha sido modificado");
		else SC.Write("El resultado no ha sido modificado");
	}
	
	private static void getDiputadoRelevante() {
		SC.Write("El diputado relevante es: " + resultado.getDiputadoRelevante());
	}
	
	private static void getPeriodo() {
		SC.Write("El periodo es: " + resultado.getPeriodo());
	}
	
	private static void getGruposAfines() {
		SC.Write("Los grupos afines son: ");
		Conjunto<GrupoAfin> conj = resultado.getGruposAfines();
		for (GrupoAfin grup:conj.getAll()) {
			SC.Write("ID: " + grup.getID());
			SC.Write("Fecha inicio:" + grup.getFechaInicio().toString());
			SC.Write("Fecha fin:" + grup.getFechaFin().toString());
			SC.Write("Diputados:");
			for (String diputado:grup.getDiputados())
				SC.Write(diputado);
		}
	}
	
	private static void getResultados() {
		Set<Set<String>> res = resultado.getResultado();
		Integer i = 0;
		for (Set<String> conj:res) {
			SC.Write("Grupo " + ++i + ": ");
			for (String dip:conj)
				SC.Write(dip);
		}
	}
	
	private static void getTipoResultado() {
		SC.Write("El tipo de resultado es: " + resultado.getTipoResultado());
	}
	
	private static void menuMetodosCreaMod() {
		Integer a = 1;
		while (a != 0) {
			a = muestraMenuMetodosCreaMod();
			switch (a) {
			case 1:	nuevaInstancia(); break;
			case 2:	setNombre(); break;
			case 3:	addImportancia(); break;
			case 4:	eliminarDiputadoResul(); break;
			case 5: addDiputadoAGrupo(); break;	
			case 6:	removeDiputadoDeGrupo(); break;
			case 7:	moverDiputado(); break;
			case 8:	addGrupo(); break;
			case 9:	removeGrupo(); break;
			default: break;
			}
		}
	}
	
	private static void menuMetodosCons() {
		Integer a = 1;
		while (a != 0) {
			a = muestraMenuMetodosCons();
			switch (a) {
			case 1:	existeGrupo(); break;
			case 2:	getNombreResultado(); break;
			case 3:	getIndiceAfinidad(); break;
			case 4:	getAlgoritmo(); break;
			case 5:	esModificado(); break;	
			case 6:	getDiputadoRelevante(); break;
			case 7:	getPeriodo(); break;
			case 8:	getGruposAfines(); break;
			case 9:	getResultados(); break;
			case 10:getTipoResultado(); break;
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
			case 3:	cargaFichero(); break;
			case 4:	guardaInstancia(); break;
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
		menuPrincipal();
	}
}
