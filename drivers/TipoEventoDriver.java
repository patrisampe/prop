package drivers;

import java.util.Set;
import io.ConsolaEntrada;
import io.ConsolaSalida;
import io.Entrada;
import io.FicheroEntrada;
import io.FicheroSalida;
import io.Salida;
import dominio.Evento;
import dominio.TipoEvento;
/**
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 *
 */
public class TipoEventoDriver {

	
	public TipoEvento llegirTipoEvento(Entrada EF){
		String nombre=EF.ReadString();
		Integer imp=EF.ReadInteger();
		return new TipoEvento(nombre,imp);
	}
	public void testsetImportancia(Entrada EF,TipoEvento te){
		
	    Integer imp=EF.ReadInteger();
	    te.setImportancia(imp);
	}
	
	public void testEsValida(Entrada EF, Salida SF){
		
		 Integer imp=EF.ReadInteger();
		 SF.Write(TipoEvento.esValidaImportancia(imp));
	}
	public void testesEvento(Entrada EF, Salida SF, TipoEvento te){
		
		String ev=EF.ReadString();
		SF.Write(te.esEvento(ev));
	}
	public void testremoveEvento(Entrada EF, Salida SF, TipoEvento te){
		
		String ev=EF.ReadString();
		te.removeEvento(ev);
	}
	public void testaddEvento(Entrada EF, Salida SF, TipoEvento te){
		
		EventoDriver ED= new EventoDriver();
		Evento e=ED.llegirEvento(EF);
		te.addEvento(e);
	}
	
	public void testGetEvento(Entrada EF, Salida SF, TipoEvento te){
		EventoDriver ED= new EventoDriver();
		String ev=EF.ReadString();
		Evento e=te.getEvento(ev);
		ED.escriureEvento(SF, e);
	}
	
	public void testGetEventos(Entrada EF, Salida SF,TipoEvento te){
		EventoDriver ED= new EventoDriver();
		Set<Evento> e=te.getEventos();
		for(Evento elem :e){
			ED.escriureEvento(SF, elem);
		}
	}
	
	public void escriureTipoEvento(Salida SF, TipoEvento te){
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
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.ReadString();
		Salida SF = new FicheroSalida(Output);
		TipoEventoDriver DTE= new TipoEventoDriver();
		Salida SC = new ConsolaSalida();
		SC.Write("Recorda: Lo primero que hacemos es inicializar tipo evento.");
		TipoEvento te= DTE.llegirTipoEvento(EF);
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 DTE.testsetImportancia(EF, te);
			     break;
			 case 2: 
				 DTE.testaddEvento(EF, SF, te);
			     break;
			 case 3: 
			     DTE.testEsValida(EF, SF);
			     break;
			 case 4: 
			     DTE.testesEvento(EF, SF, te);
			     break;
			 case 5:
				 String newName = EF.ReadString();
				 TipoEvento aux= new TipoEvento(newName,te);
				 DTE.escriureTipoEvento(SF, aux);
				 break;
			 case 6: 
			     DTE.testremoveEvento(EF, SF, te);
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
