package drivers;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import time.Date;

import io.ConsolaEntrada;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Evento;
public class EventoDriver {

	
	public Evento llegirEvento(Entrada EF, Sortida SF){
		
		    String nomEvento = EF.ReadString();
		    Integer Day=EF.ReadInteger();
		    Integer Month=EF.ReadInteger();
		    Integer Year=EF.ReadInteger();
		    Date Data=new Date(Day,Month,Year);
			int end=EF.ReadInt();
			String dip[]=EF.ReadString(end);
			Set<String>dipu=new TreeSet<String> (Arrays.asList(dip));
			return new Evento(nomEvento,Data,dipu);
	}
	public void setFecha(Entrada EF,Evento e){
		
	    Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
		e.setFecha(Data);
	}
	
	public void addDiputados(Entrada EF,Evento e){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i)e.addDiputado(dip[i]);
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
		Evento e=DE.llegirEvento(EF,SF);
		
		int end=EF.ReadInt();
		for(int i=0;i<end;++i){
			DE.escriureEvento(SF, e);
			DE.setFecha(EF,e);
			DE.addDiputados(EF,e);
			DE.escriureEvento(SF, e);
			DE.esParticipante(EF,e,SF);
			DE.removeDiputados(EF,e);
			DE.escriureEvento(SF, e);
			DE.esParticipante(EF,e,SF);
		}
		EF.close();
		SF.close();
		
		
	}

}
