package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import controladores.ControladorDominioDiputado;
import dominio.Diputado;
import dominio.Legislatura;
import io.*;

public class ControladorDominioDiputadoDriver {
	
	public static Diputado diputado(Entrada E){
		String nombre = E.ReadString();
		String partidoPolitico = E.ReadString();
		String estado = E.ReadString();
		Date fechaDeNacimiento = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		return new Diputado(nombre, partidoPolitico, estado, fechaDeNacimiento);
	}
	
	public static Legislatura legislatura(Entrada E){
		Integer identificador = E.ReadInteger();
		Date fechaInicio = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		Date fechaFinal = new Date(E.ReadInteger(), E.ReadInteger(), E.ReadInteger());
		return new Legislatura(identificador, fechaInicio, fechaFinal);
	}
	
	public static void main(String[] args) {
		Entrada E = new ConsolaEntrada();
		Sortida S = new ConsolaSortida();
		ControladorDominioDiputado CDD = ControladorDominioDiputado.getInstance();
		Integer n;
		Integer codi = 0;
		while (codi != -1) {
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
			//TODO
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
			default:
				S.Write("Codigo incorrecto.");
			break;
			}
			if (CDD.hasCodiError()) S.Write(CDD.getCodiError().getMensajeError());
		}
	}
}