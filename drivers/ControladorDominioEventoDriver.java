package drivers;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Evento;

public class ControladorDominioEventoDriver {

	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		EventoDriver DE= new EventoDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		Evento e= DE.llegirEvento(EF);
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DE.esParticipante(EF, e, SF);
			     break;
			 case 2: 
				 DE.escriureEvento(SF,e);
			     break;
			 case 3: 
			     DE.addDiputados(EF, e);
			     break;
			 case 4: 
			     DE.setFecha(EF, e);
			     break;
			 case 5:
				 String newName = EF.ReadString();
				 Evento aux= new Evento(newName,e);
				 DE.escriureEvento(SF, aux);
				 break;
			 case 6: 
			     DE.removeDiputados(EF, e);
			     break;
			 case 7:
				 Evento aux2 = DE.llegirEvento(EF);
				 DE.escriureEvento(SF, aux2);
				 break;
			 default: 
			    SF.Write(" numero no correcto. Para cerrar -1 ");
			     break;
			 }
			a=EF.ReadInt();
		}
		EF.close();
		SF.close();
	}
	}

}
