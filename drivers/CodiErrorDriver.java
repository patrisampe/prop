package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Evento;
import utiles.CodiError;

public class CodiErrorDriver{

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		CodiErrorDriver DCE= new CodiErrorDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		CodiError ce=new CodiError();
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DE.esParticipante(EF, e, SF);
			     break;
			 case 2: 
				 DE.escriureEvento(SF,e);
			     break;

			}
		}
	}
}

