package drivers;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import time.Date;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Votacion;

public class VotacionDriver {

	
	public Votacion llegirVotacion(Entrada EF){
		
		    String nomVotacion = EF.ReadString();
		    Integer Day=EF.ReadInteger();
		    Integer Month=EF.ReadInteger();
		    Integer Year=EF.ReadInteger();
		    Date Data=new Date(Day,Month,Year);
		    Integer imp= EF.ReadInteger();
			
			return new Votacion(nomVotacion,Data,imp);
	}
	public void setFecha(Entrada EF,Votacion v){
		
	    Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
		v.setFecha(Data);
	}
	
	public void setImportancia(Entrada EF,Votacion v){
		Integer nuevaimp=EF.ReadInteger();
		v.setImportancia(nuevaimp);
	}
	public void removeDiputados(Entrada EF,Evento e){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i)e.removeDiputado(dip[i]);
	}
	
	public void esParticipante(Entrada EF,Evento e, Sortida SF){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i){
			SF.Write(dip[i]+","+e.esParticipante(dip[i]));
		}
		SF.Write(" ");
		
	}
	
	public void escriureEvento(Sortida SF, Evento e){
		SF.Write(e.getNombre());
		SF.Write(e.getFecha().getDay());
		SF.Write(e.getFecha().getMonth());
		SF.Write(e.getFecha().getYear());
		int mida=e.getdiputados().size();
		SF.Write(mida, e.getdiputados().toArray(new String[mida]));
		SF.Write(" ");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		EventoDriver DE= new EventoDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		Evento e= DE.llegirEvento(EF,SC);
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
				 Evento aux2 = DE.llegirEvento(EF,SC);
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
