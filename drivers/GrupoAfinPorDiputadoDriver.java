package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.GrupoAfinPorDiputado;
import time.Date;

public class GrupoAfinPorDiputadoDriver {

	static Entrada EC, EF;
	static Sortida SC, SF;
	static String fichEnt;
	static String fichSal;
	static GrupoAfinPorDiputado grupAfi;
	
	public static Integer muestraMenuPrincipal() {
		SC.Write("0.-Salir");
		SC.Write("1.-Cambiar fichero de pruebas (ACTUAL: " + fichEnt + ")");
		SC.Write("2.-Cambiar fichero de salida  (ACTUAL: " + fichSal + ")");
		SC.Write("3.-Cargar instancia del fichero de pruebas actual");
		SC.Write("4.-Guardar instancia en el fichero de salida actual");
		SC.Write("5.-Probar métodos de la clase");
		return EC.ReadInteger();
	}
	
	public static Integer muestraMenuMetodos() {
		SC.Write("0.-Atrás");
		SC.Write("1.-Crear nueva instancia de GrupoAfinPorDiputado");
		SC.Write("2.-Añadir un Diputado");
		SC.Write("3.-Eliminar un diputado");
		SC.Write("4.-Obtener identificador del GrupoAfin");
		SC.Write("5.-Obtener lista de diputados");
		SC.Write("6.-Comprobar si un diputado pertenece al grupo");
		SC.Write("7.-Comprobar si el grupo está vacío");
		SC.Write("8.-Introducir una nueva fecha de inicio");
		SC.Write("9.-Introducir una nueva fecha de fin");
		SC.Write("10.-Obtener fecha de inicio");
		SC.Write("11.-Ontener fecha de fin");
		return EC.ReadInteger();
	}
	
	public static void cambiaFicheroEntrada() {
		SC.Write("Introduzca un nuevo archivo de pruebas: ");
		fichEnt = EC.ReadString();
		SC.Write("El nuevo archivo de pruebas es: " + fichEnt);
	}
	
	public static void cambiaFicheroSalida() {
		SC.Write("Introduzca un nuevo archivo de salida: ");
		fichSal = EC.ReadString();
		SC.Write("El nuevo archivo de salida es: " + fichSal);
	}
	
	public static void cargaFichero() {
		EF = new FitxerEntrada(fichEnt);
		
	}
	
	public static void guardaInstancia() {
		SF = new FitxerSortida(fichSal);
	}
	
	public static void nuevaInstancia() {
		SC.Write("Introduzca un ID para el grupo:");
		int ID = EC.ReadInt();
		SC.Write("Introduzca la fecha de inicio DD/MM/AA:");
		String fI = EC.ReadString();
		SC.Write("Introduzca la fecha de fin DD/MM/AA:");
		String fF = EC.ReadString();
		grupAfi = new GrupoAfinPorDiputado(ID, Date.stringToDate(fI), Date.stringToDate(fF));
	}
	
	public static void addDiputado() {
		SC.Write("Nombre del diputado:");
		String diputado = EC.ReadString();
		grupAfi.addDiputado(diputado);
	}
	
	public static void removeDiputado() {
		SC.Write("Nombre del diputado:");
		String diputado = EC.ReadString();
		grupAfi.removeDiputado(diputado);
	}
	
	public static void getID() {
		SC.Write("Identificador: " + grupAfi.getID());
	}
	
	public static void getDiputados() {
		SC.Write(grupAfi.getDiputados());
	}
	
	public static void pertenece() {
		SC.Write("Escriba el nombre del diputado que desea comprobar: ");
		String nombre = EC.ReadString();
		if (grupAfi.pertenece(nombre)) SC.Write("El diputado " + nombre + " pertenece al grupo");
		else SC.Write("El diputado " + nombre + " no pertenece al grupo");
	}
	
	public static void esVacio() {
		if (grupAfi.esVacio()) SC.Write("El grupo está vacío");
		else SC.Write("El grupo no está vacío");
	}
	
	public static void setFechaInicio() {
		SC.Write("Introduzca la fecha de inicio DD/MM/AA:");
		String fecha = EC.ReadString();
		grupAfi.setFechaInicio(Date.stringToDate(fecha));
	}
	public static void setFechaFin() {
		SC.Write("Introduzca la fecha de fin DD/MM/AA:");
		String fecha = EC.ReadString();
		grupAfi.setFechaFin(Date.stringToDate(fecha));
	}
	public static void getFechaInicio() {
		SC.Write("La fecha de inicio es: " + grupAfi.getFechaInicio());
	}
	public static void getFechaFin() {
		SC.Write("La fecha de fin es: " + grupAfi.getFechaFin());
	}
	
	/*1.-Crear nueva instancia de GrupoAfinPorDiputado
	2.-Añadir un Diputado
	3.-Eliminar un diputado
	4.-Obtener identificador del GrupoAfin
	5.-Obtener lista de diputados
	6.-Comprobar si un diputado pertenece al grupo
	7.-Comprobar si el grupo está vacío
	8.-Introducir una nueva fecha de inicio
	9.-Introducir una nueva fecha de fin
	10.-Obtener fecha de inicio
	11.-Ontener fecha de fin*/
	
	public static void menuMetodos() {
		int a = 1;
		while (a != 0) {
			a = muestraMenuMetodos();
			switch (a) {
			case 1:	nuevaInstancia(); break;
			case 2:	addDiputado(); break;
			case 3:	removeDiputado(); break;
			case 4:	getID(); break;
			case 5: getDiputados(); break;	
			case 6:	pertenece(); break;
			case 7:	esVacio(); break;
			case 8:	setFechaInicio(); break;
			case 9:	setFechaFin(); break;
			case 10: getFechaInicio(); break;	
			case 11: getFechaFin(); break;	
			default: break;
			}
		}
	}
	
	public static void menuPrincipal() {
		int a = 1;
		while (a != 0) {
			a = muestraMenuPrincipal();
			switch (a) {
			case 1:	cambiaFicheroEntrada(); break;
			case 2:	cambiaFicheroSalida(); break;
			case 3:	cargaFichero(); break;
			case 4:	guardaInstancia(); break;
			case 5: menuMetodos(); break;	
			default: break;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EC = new ConsolaEntrada();
		SC = new ConsolaSortida();
		fichEnt = "jocproves/jocProvaGrupoAfinPorDiputado.txt";
		fichSal = "jocproves/sortidaJocProvaGrupoAfinPorDiputado.txt";
		grupAfi = null;
		menuPrincipal();
	}

}
