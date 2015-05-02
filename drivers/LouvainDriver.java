package drivers;

import java.util.HashSet;

import dominio.algoritmos.Louvain;
import io.ConsolaEntrada;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

public class LouvainDriver {

	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		HashSet<HashSet<String>> comunidades = Louvain.executa(GrafLouvainDriver.ReadGraf(EF, EF.ReadInt(), EF.ReadInt()), EF.ReadInt()); 
		Integer i = 1;
		for(HashSet<String> comunidad : comunidades) {
			SF.Write("Comunidad "+i.toString()+":");
			SF.Write(comunidad);
			++i;
		}
	}
}
