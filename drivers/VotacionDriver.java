package drivers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import time.Date;

import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.TipoVoto;
import dominio.Votacion;
/**
 * 
 * @author patricia
 *
 */
public class VotacionDriver {

	private TipoVoto convert1(Integer n){
		switch(n){
			case 1:
				return TipoVoto.A_FAVOR;
			case 2:
				return TipoVoto.ABSTENCION;
			case 3:
				return TipoVoto.AUSENCIA;
			default:
				return TipoVoto.EN_CONTRA;
			}
	}
	
	
	public Votacion llegirVotacion(Entrada EF){
		
		    String nomVotacion = EF.ReadString();
		    Integer Day=EF.ReadInteger();
		    Integer Month=EF.ReadInteger();
		    Integer Year=EF.ReadInteger();
		    Date Data=new Date(Day,Month,Year);
		    Integer imp= EF.ReadInteger();

			int end=EF.ReadInt();
			String dip[]=EF.ReadString(end);
			Integer[] vot=EF.ReadInteger(end);
			Map<String,TipoVoto> votos=new TreeMap<String, TipoVoto>();
			for(int i=0;i<end;++i){
				votos.put(dip[i], convert1(vot[i]));
			}
			return new Votacion(nomVotacion,Data,imp,votos);
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
	public void removeVoto(Entrada EF,Votacion v){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i)v.removeVoto(dip[i]);
	}
	
	public void esVotante(Entrada EF,Votacion v, Sortida SF){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i){
			SF.Write(dip[i]+","+v.esVotante(dip[i]));
		}
		SF.Write(" ");
		
	}
	
	public void getVotos(Entrada EF,Votacion v, Sortida SF){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		for(int i=0;i<end;++i){
			SF.Write(dip[i]+","+v.getVoto(dip[i]));
		}
		SF.Write(" ");
		
	}
	
	public void setVotos(Entrada EF,Votacion v, Sortida SF){
		
		int end=EF.ReadInt();
		String dip[]=EF.ReadString(end);
		Integer[] vot= EF.ReadInteger(end);
		for(int i=0;i<end;++i){
			v.setaddVoto(dip[i], convert1(vot[i]));
		}
		SF.Write(" ");
		
	}
	
	public void testEsValida(Entrada EF, Sortida SF){
		
		 Integer imp=EF.ReadInteger();
		 SF.Write(Votacion.esValidaImportancia(imp));
	}
	
	public void escriureVotacion(Sortida SF, Votacion v){
		SF.Write(v.getNombre());
		SF.Write(v.getFecha().getDay());
		SF.Write(v.getFecha().getMonth());
		SF.Write(v.getFecha().getYear());
		int mida=v.getDiputados().size();
		SF.Write(v.getImportancia());
		SF.Write("Diputados de la votacion");
		SF.Write(mida, v.getDiputados().toArray(new String[mida]));
		SF.Write("Diputados que votan ABSTENCION");
		SF.Write(v.getDiputados(TipoVoto.ABSTENCION));
		SF.Write("Diputados que votan A_FAVOR");
		SF.Write(v.getDiputados(TipoVoto.A_FAVOR));
		SF.Write("Diputados que votan AUSENCIA");
		SF.Write(v.getDiputados(TipoVoto.AUSENCIA));
		SF.Write("Diputados que votan EN_CONTRA");
		SF.Write(v.getDiputados(TipoVoto.EN_CONTRA));
		SF.Write("Diputados y votos");
		Map<String,TipoVoto> a= v.getVotos();	
		for (Entry<String, TipoVoto> entry : a.entrySet()){
			SF.Write("Diputado "+entry.getKey()+" voto "+ entry.getValue());
		}
		
		SF.Write(" ");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		VotacionDriver DV= new VotacionDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: Lo primero que hacemos es inicialitzar la votacion.");
		Votacion v= DV.llegirVotacion(EF);
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DV.esVotante(EF, v, SF);
			     break;
			 case 2: 
				 DV.escriureVotacion(SF,v);
			     break;
			 case 3: 
			     DV.setFecha(EF, v);
			     break;
			 case 4: 
			     DV.setImportancia(EF, v);
			     break;
			 case 5:
				 String newName = EF.ReadString();
				 Votacion aux= new Votacion(newName,v);
				 DV.escriureVotacion(SF, aux);
				 break;
			 case 6: 
			     DV.setVotos(EF, v, SF);
			     break;
			 case 7:
				 Votacion aux2 = DV.llegirVotacion(EF);
				 DV.escriureVotacion(SF, aux2);
				 break;
			 case 8: 
			     DV.getVotos(EF, v, SF);
			     break;
			 case 9:
				 DV.removeVoto(EF, v);
				 break;
			 case 10:
				 DV.testEsValida(EF, SF);
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

