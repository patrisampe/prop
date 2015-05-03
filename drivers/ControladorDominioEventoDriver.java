package drivers;

import java.util.Set;
import java.util.TreeSet;

import time.Date;
import controladores.ControladorDominioDiputado;
import controladores.ControladorDominioEvento;
import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Diputado;
import dominio.Evento;
/**
 * 
 * @author patricia
 *
 */
public class ControladorDominioEventoDriver {

	public Boolean testError(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		if(CDE.getHasError()){
			SF.Write("ERROR");
			SF.Write(CDE.getError().getMensajeError());
			CDE.netejaError();
			return true;
		}
		else SF.Write("OK");
		return false;
	}
	
	public void testaddTipoEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		
		String nombre=EF.ReadString();
		Integer imp=EF.ReadInteger();
		CDE.addTipoEvento(nombre, imp);
		testError(EF,SF,CDE);
	}
	public void testsetImportanciaTipoEvento(Entrada EF,Sortida SF, ControladorDominioEvento CDE){
		
		String nombre=EF.ReadString();
		Integer imp=EF.ReadInteger();
		CDE.setImportanciaTipoEvento(nombre, imp);
		testError(EF,SF,CDE);
	}
	
	public void testgetImportanciaTipoEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		
		String nombre=EF.ReadString();
		Integer imp=CDE.getImportanciaTipoEvento(nombre);
		if(!testError(EF,SF,CDE)){
			SF.Write(imp);
		}
	}
	
	public void testgetEventos(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		
		String nombre=EF.ReadString();
		Set<String> ev=CDE.getEventos(nombre);
		if(!testError(EF,SF,CDE)){
			int mida=ev.size();
			SF.Write(mida, ev.toArray(new String[mida]));
			SF.Write(" ");
		}
	}
	public void testgetEventosData(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		
		String nombre=EF.ReadString();
	    Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
	    Integer Day1=EF.ReadInteger();
	    Integer Month1=EF.ReadInteger();
	    Integer Year1=EF.ReadInteger();
	    Date Data1=new Date(Day1,Month1,Year1);
		Set<String> ev=CDE.getEventos(nombre, Data, Data1);
		if(!testError(EF,SF,CDE)){
			int mida=ev.size();
			SF.Write(mida, ev.toArray(new String[mida]));
			SF.Write(" ");
		}
	}
	
	public void testremoveDiputado(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombredip=EF.ReadString();
		CDE.removeDiputado(nombredip);
		testError(EF,SF,CDE);
	}
	
	public void testremoveTipoEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		CDE.removeTipoEvento(nombrete);
		testError(EF,SF,CDE);
	}
	
	public void testesTipoEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		SF.Write(CDE.esTipoEvento(nombrete));
	}
	
	public void testaddEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		EventoDriver ED=new EventoDriver();
		Evento e=ED.llegirEvento(EF);
		CDE.addEvento(nombrete, e.getNombre(), e.getFecha(), e.getdiputados());
		testError(EF,SF,CDE);
	}
	
	public void testremoveEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		CDE.removeEvento(nombrete, nombreev);
		testError(EF,SF,CDE);
	}
	
	public void testesEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		Boolean b=CDE.esEvento(nombrete, nombreev);
		if(!testError(EF,SF,CDE)){
			SF.Write(b);
		}
	}
	
	public void testsetFechaEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		Integer Day=EF.ReadInteger();
	    Integer Month=EF.ReadInteger();
	    Integer Year=EF.ReadInteger();
	    Date Data=new Date(Day,Month,Year);
	    CDE.setFechaEvento(nombrete, nombreev, Data);
		testError(EF,SF,CDE);
	}
	
	public void testgetFechaEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
	    Date Data=CDE.getFechaEvento(nombrete, nombreev);
		if(!testError(EF,SF,CDE)){
			SF.Write(Data.getDay());
			SF.Write(Data.getMonth());
			SF.Write(Data.getYear());
		}
	}
	
	public void testgetDiputadosEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
	    Set<String> diputados=CDE.getDiputadosEvento(nombrete, nombreev);
		if(!testError(EF,SF,CDE)){
			int mida=diputados.size();
			SF.Write(mida,diputados.toArray(new String[mida]));
		}
	}
	
	public void testaddDiputadosEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		String nombredip=EF.ReadString();
	    CDE.addDiputadoEvento(nombrete, nombreev, nombredip);
		testError(EF,SF,CDE);
	}
	
	public void testremoveDiputadosEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		String nombredip=EF.ReadString();
	    CDE.removeDiputadoEvento(nombrete, nombreev, nombredip);
		testError(EF,SF,CDE);
	}
	
	public void testesParticipanteEvento(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		String nombrete=EF.ReadString();
		String nombreev=EF.ReadString();
		String nombredip=EF.ReadString();
	    Boolean b=CDE.esParticipanteEnEvento(nombrete, nombreev, nombredip);
		if(!testError(EF,SF,CDE)){
			SF.Write(b);
		}
	}
	
	public void testescriureControlador(Entrada EF, Sortida SF, ControladorDominioEvento CDE){
		Set<String> diputados=CDE.getTipoEvento();
		int mida=diputados.size();
		SF.Write(mida,diputados.toArray(new String[mida]));
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
		ControladorDominioEventoDriver DCDE= new ControladorDominioEventoDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		ControladorDominioEvento CDE=ControladorDominioEvento.getInstance();
		int a= EF.ReadInt();
		while(a!=-1){
			SF.Write(a);
			switch(a) {
			 case 1: 
				 DCDE.testaddTipoEvento(EF, SF, CDE);
			     break;
			 case 2: 
				 DCDE.testsetImportanciaTipoEvento(EF, SF, CDE);
			     break;
			 case 3: 
			     DCDE.testgetImportanciaTipoEvento(EF, SF, CDE);
			     break;
			 case 4: 
			     DCDE.testgetEventos(EF, SF, CDE);
			     break;
			 case 5:
				 DCDE.testgetEventosData(EF, SF, CDE);
				 break;
			 case 6: 
			     DCDE.testremoveDiputado(EF, SF, CDE);
			     break;
			 case 7:
				 DCDE.testremoveTipoEvento(EF, SF, CDE);
				 break;
			 case 8:
				 DCDE.testaddEvento(EF, SF, CDE);
				 break;
			 case 9: 
			     DCDE.testremoveEvento(EF, SF, CDE);
			     break;
			 case 10:
				 DCDE.testesEvento(EF, SF, CDE);
				 break;
			 case 11: 
			     DCDE.testsetFechaEvento(EF, SF, CDE);
			     break;
			 case 12:
				 DCDE.testgetFechaEvento(EF, SF, CDE);
				 break;
			 case 13:
				 DCDE.testgetDiputadosEvento(EF, SF, CDE);
				 break;
			 case 14:
				 DCDE.testaddDiputadosEvento(EF, SF, CDE);
				 break;
			 case 15: 
			     DCDE.testremoveDiputadosEvento(EF, SF, CDE);
			     break;
			 case 16:
				 DCDE.testescriureControlador(EF, SF, CDE);
				 break;
			 case 17:
				 ControladorDominioEvento CDE2=ControladorDominioEvento.getInstance();
				 DCDE.testescriureControlador(EF, SF, CDE2);
				 break;
			 case 18:
				 DCDE.testesParticipanteEvento(EF, SF, CDE);
				 break;
			 case 19:
				 ControladorDominioDiputado CDD=ControladorDominioDiputado.getInstance();				 
				 Integer n = EF.ReadInteger();
			     Set<Diputado> S1 = new TreeSet<Diputado>();
				 for (Integer i = 0; i < n; ++i) S1.add(ControladorDominioDiputadoDriver.diputado(EF));
			     CDD.addAll(S1);
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
