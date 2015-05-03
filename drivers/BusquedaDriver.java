package drivers;

import io.ConsolaEntrada;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import java.util.HashSet;

import controladores.*;
import dominio.algoritmos.GrafLouvain;

/**
 * Driver de los Controladores de Búsqueda.
 * Por su naturaleza, este driver ejerce también de Driver del Dominio completo exceptuando Resultado de Búsqueda.
 * @author Yoel Cabo
 *
 */
public class BusquedaDriver {
	ControladorDominioBusquedaPorDiputado PorDip;
	ControladorDominioBusquedaPorPeriodo PorPer;
	ControladorDominioVotacion cVot;
	ControladorDominioEvento cEv;
	ControladorDominioDiputado cDip;
	
	public BusquedaDriver() {
		PorDip = new ControladorDominioBusquedaPorDiputado();
		PorPer = new ControladorDominioBusquedaPorPeriodo();
		cVot = ControladorDominioVotacion.getInstance();
		cEv = ControladorDominioEvento.getInstance();
		cDip = ControladorDominioDiputado.getInstance();
	}
	
	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		GrafLouvainDriver GD = new GrafLouvainDriver();
		int a= EF.ReadInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
			     break;
			 case 2: 
			     break;
			 case 3: 
			     break;
			 case 4: 
			     break;
			 case 5:

				 break;
			 case 6:
			 	break;
			 default: 
				    SF.Write(" Comanda incorrecta. Per a tancar -1 ");
				     break;
				 }
				a=EF.ReadInt();
		}
		EF.close();
		SF.close();
	}
	
}
