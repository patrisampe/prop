package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import java.util.Set;

import dominio.GrupoAfinPorPeriodo;

public class GrupoAfinPorPeriodoDriver {

	static Entrada EC, EF;
	static Sortida SC, SF;
	static String fichEnt;
	static String fichSal;
	static GrupoAfinPorPeriodo grupAfi;
	
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
		SC.Write("1.-Crear nueva instancia de GrupoAfinPorPeriodo");
		SC.Write("2.-Añadir un Diputado");
		SC.Write("3.-Eliminar un diputado");
		SC.Write("4.-Obtener identificador del GrupoAfin");
		SC.Write("5.-Obtener lista de diputados");
		SC.Write("6.-Comprobar si un diputado pertenece al grupo");
		SC.Write("7.-Comprobar si el grupo está vacío");
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
		grupAfi = new GrupoAfinPorPeriodo(EF.ReadInteger());
		Integer numdip = EF.ReadInteger();
		for (Integer i = 0; i < numdip; ++i)
			grupAfi.addDiputado(EF.ReadString());
		EF.close();
	}
	
	public static void guardaInstancia() {
		SF = new FitxerSortida(fichSal);
		SF.Write(grupAfi.getID());
		Set<String> diputados = grupAfi.getDiputados();
		SF.Write(diputados.size());
		for (String diputado:diputados)
			SF.Write(diputado);
		SF.close();
	}
	
	public static void nuevaInstancia() {
		SC.Write("Introduzca un ID para el grupo:");
		int ID = EC.ReadInt();
		grupAfi = new GrupoAfinPorPeriodo(ID);
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
		EC = new ConsolaEntrada();
		SC = new ConsolaSortida();
		fichEnt = "jocproves/jocProvaGrupoAfinPorPeriodo.txt";
		fichSal = "jocproves/sortidaJocProvaGrupoAfinPorPeriodo.txt";
		grupAfi = null;
		menuPrincipal();
	}
}
