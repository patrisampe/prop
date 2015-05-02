package drivers;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import time.Date;
import controladores.ControladorDominioEvento;
import controladores.ControladorDominioVotacion;
import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.TipoVoto;
import dominio.Votacion;

public class ControladorDominioVotacionDriver {

	public Boolean testError(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		if(CDV.getHasError()){
			SF.Write("ERROR");
			SF.Write(CDV.getError().getMensajeError());
			CDV.netejaError();
			return true;
		}
		return false;
	}
	
	public void testsetImportanciaVotacion(Entrada EF,Sortida SF, ControladorDominioVotacion CDV){
		
		String nombre=EF.ReadString();
		Integer imp=EF.ReadInteger();
		CDV.setImportanciaVotacion(nombre, imp);
		testError(EF,SF,CDV);
	}
	
	public void testgetImportanciaVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		
		String nombre=EF.ReadString();
		Integer imp=CDV.getImportanciaVotacion(nombre);
		if(!testError(EF,SF,CDV)){
			SF.Write(imp);
		}
	}
	
	public void testsetFechaVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrete=EF.ReadString();
		Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
	    CDV.setFechaVotacion(nombrete, Data);
		testError(EF,SF,CDV);
	}
	
	public void testgetFechaVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrete=EF.ReadString();
	    Date Data=CDV.getFechaVotacion(nombrete);
		if(!testError(EF,SF,CDV)){
			SF.Write(Data.getDay());
			SF.Write(Data.getMonth());
			SF.Write(Data.getYear());
		}
	}
	
	
	public void testgetDipVotacions(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		
		String nombredip=EF.ReadString();
		Set<String> dip=CDV.getDiputadosVotacion(nombredip);
		if(!testError(EF,SF,CDV)){
			int mida=dip.size();
			SF.Write(mida, dip.toArray(new String[mida]));
			SF.Write(" ");
		}
	}
	
	public void testgetDipVotacionsTV(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		
		String nombredip=EF.ReadString();
		String tp=EF.ReadString();
		Set<String> dip=CDV.getDiputadosVotacion(nombredip,TipoVoto.valueOf(tp));
		if(!testError(EF,SF,CDV)){
			int mida=dip.size();
			SF.Write(mida, dip.toArray(new String[mida]));
			SF.Write(" ");
		}
	}
	
	public void testesVotanteVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrev=EF.ReadString();
		String nombredip=EF.ReadString();
	    Boolean b=CDV.esVotanteEnVotacion(nombrev, nombredip);
		if(!testError(EF,SF,CDV)){
			SF.Write(b);
		}
	}
	
	public void testgetVotacionesData(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		
	    Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
	    Integer Day1=EF.ReadInteger();
	    Integer Month1=EF.ReadInteger();
	    Integer Year1=EF.ReadInteger();
	    Date Data1=new Date(Day1,Month1,Year1);
		Set<String> vo=CDV.getVotaciones(Data, Data1);
		if(!testError(EF,SF,CDV)){
			int mida=vo.size();
			SF.Write(mida, vo.toArray(new String[mida]));
			SF.Write(" ");
		}
	}
	public void testremoveDiputado(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombredip=EF.ReadString();
		CDV.removeDiputado(nombredip);
		testError(EF,SF,CDV);
	}
	
	public void testremoveVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevot=EF.ReadString();
		CDV.removeVotacion(nombrevot);
		testError(EF,SF,CDV);
	}
	
	public void testesVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevot=EF.ReadString();
		SF.Write(CDV.esVotacion(nombrevot));
	}
	
	public void testaddVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		VotacionDriver ED=new VotacionDriver();
		Votacion e=ED.llegirVotacion(EF);
		CDV.addVotacion(e.getNombre(), e.getFecha(), e.getImportancia(),e.getVotos());
		testError(EF,SF,CDV);
	}
	
	public void testgetVotos(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevot=EF.ReadString();
		Map<String,TipoVoto> a= CDV.getVotos(nombrevot);
		if(!testError(EF,SF,CDV)){
			for (Entry<String, TipoVoto> entry : a.entrySet()){
				SF.Write("Diputado "+entry.getKey()+" voto "+entry.getValue());
			}
		}
	}
	
	public void testgetVotosdiputado(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevot=EF.ReadString();
		String dip=EF.ReadString();
		TipoVoto aux= CDV.getVotoDiputado(nombrevot, dip);
		if(!testError(EF,SF,CDV)){
			SF.Write("TIPO VOTO"+aux);
		}
	}
	
	
	
	public void testaddDiputadosVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevotacion=EF.ReadString();
		String nombreDiputado=EF.ReadString();
		String tipovoto=EF.ReadString();
	    CDV.setAddVoto(nombrevotacion, nombreDiputado, TipoVoto.valueOf(tipovoto));
		testError(EF,SF,CDV);
	}
	
	public void testremoveDiputadosVotacion(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		String nombrevotacion=EF.ReadString();;
		String nombredip=EF.ReadString();
	    CDV.removeVotoDiputado(nombrevotacion, nombredip);
		testError(EF,SF,CDV);
	}
	
	public void testescriureControlador(Entrada EF, Sortida SF, ControladorDominioVotacion CDV){
		Set<String> vot=CDV.getVotaciones();
		int mida=vot.size();
		SF.Write(mida,vot.toArray(new String[mida]));
	}
	
	
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
		ControladorDominioVotacionDriver DCDV= new ControladorDominioVotacionDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		ControladorDominioVotacion CDV=ControladorDominioVotacion.getInstance();
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DCDV.testsetImportanciaVotacion(EF, SF, CDV);
			     break;
			 case 2: 
				 DCDV.testgetImportanciaVotacion(EF, SF, CDV);
			     break;
			 case 3: 
			     DCDV.testsetFechaVotacion(EF, SF, CDV);
			     break;
			 case 4: 
			     DCDV.testgetFechaVotacion(EF, SF, CDV);
			     break;
			 case 5:
				 DCDV.testgetDipVotacions(EF, SF, CDV);
				 break;
			 case 6: 
			     DCDV.testgetDipVotacionsTV(EF, SF, CDV);
			     break;
			 case 7:
				 DCDV.testesVotanteVotacion(EF, SF, CDV);
				 break;
			 case 8:
				 DCDV.testgetVotacionesData(EF, SF, CDV);
				 break;
			 case 9: 
			     DCDV.testremoveDiputado(EF, SF, CDV);
			     break;
			 case 10:
				 DCDV.testremoveVotacion(EF, SF, CDV);
				 break;
			 case 11: 
				 DCDV.testesVotacion(EF, SF, CDV);
				 break;
			 case 12:
				 DCDV.testaddVotacion(EF, SF, CDV);
				 break;
			 case 13:
				 DCDV.testgetVotos(EF, SF, CDV);
				 break;
			 case 14:
				 DCDV.testgetVotosdiputado(EF, SF, CDV);
				 break;
			 case 15: 
			     DCDV.testaddDiputadosVotacion(EF, SF, CDV);
			     break;
			 case 16:
				 DCDV.testremoveDiputadosVotacion(EF, SF, CDV);
				 break;
			 case 17:
				 ControladorDominioVotacion CDV2=ControladorDominioVotacion.getInstance();
				 DCDV.testescriureControlador(EF, SF, CDV2);
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