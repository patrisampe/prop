package drivers;

import java.util.Set;
import io.ConsolaEntrada;
import io.ConsolaSortida;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;
import dominio.Evento;
import dominio.TipoEvento;

public class TipoEventoDriver {

	
	public TipoEvento llegirTipoEvento(Entrada EF){
		String nombre=EF.ReadString();
		Integer imp=EF.ReadInteger();
		return new TipoEvento(nombre,imp);
	}
	public void setImportancia(Entrada EF,TipoEvento te){
		
	    Integer imp=EF.ReadInteger();
	    te.setImportancia(imp);
	}
	
	public void testEsValida(Entrada EF, Sortida SC){
		
		 Integer imp=EF.ReadInteger();
		 SC.Write(TipoEvento.esValidaImportancia(imp));
	}
	public void esEvento(Entrada EF, Sortida SC, TipoEvento te){
		
		String ev=EF.ReadString();
		SC.Write(te.esEvento(ev));
	}
	public void removeEvento(Entrada EF, Sortida SC, TipoEvento te){
		
		String ev=EF.ReadString();
		te.removeEvento(ev);
	}
	public void addEvento(Entrada EF, Sortida SF, TipoEvento te){
		
		EventoDriver ED= new EventoDriver();
		Evento e=ED.llegirEvento(EF);
		te.addEvento(e);
	}
	
	public void testGetEvento(Entrada EF, Sortida SF, TipoEvento te){
		EventoDriver ED= new EventoDriver();
		String ev=EF.ReadString();
		Evento e=te.getEvento(ev);
		ED.escriureEvento(SF, e);
	}
	
	public void testGetEventos(Entrada EF, Sortida SF,TipoEvento te){
		EventoDriver ED= new EventoDriver();
		Set<Evento> e=te.getEventos();
		for(Evento elem :e){
			ED.escriureEvento(SF, elem);
		}
	}
	
	public void escriureTipoEvento(Sortida SF, TipoEvento te){
		SF.Write(te.getNombre());
		SF.Write(te.getImportancia());
		int mida=te.getNombreEventos().size();
		SF.Write(mida, te.getNombreEventos().toArray(new String[mida]));
		SF.Write(" ");
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		TipoEventoDriver DTE= new TipoEventoDriver();
		Sortida SC = new ConsolaSortida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'event.");
		TipoEvento te= DTE.llegirTipoEvento(EF);
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DTE.setImportancia(EF, te);
			     break;
			 case 2: 
				 DTE.setImportancia(EF, te);
			     break;
			 case 3: 
			     DTE.testEsValida(EF, SC);
			     break;
			 case 4: 
			     DTE.esEvento(EF, SC, te);
			     break;
			 case 5:
				 String newName = EF.ReadString();
				 TipoEvento aux= new TipoEvento(newName,te);
				 DTE.escriureTipoEvento(SF, aux);
				 break;
			 case 6: 
			     DTE.removeEvento(EF, SC, te);
			     break;
			 case 7:
				 TipoEvento aux2 = DTE.llegirTipoEvento(EF);
				 DTE.escriureTipoEvento(SF, aux2);
				 break;
			 case 8:
				 DTE.testGetEvento(EF, SF, te);
				 break;
			 case 9:
				 DTE.testGetEventos(EF, SF, te);
				 break;
			 case 10:
				 DTE.escriureTipoEvento(SF, te);
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