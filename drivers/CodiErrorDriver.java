package drivers;

import io.ConsolaEntrada;
import io.ConsolaSalida;
import io.Entrada;
import io.FicheroEntrada;
import io.FicheroSalida;
import io.Salida;
import utiles.CodiError;

/**
 * @author  Patricia Sampedro
 * @version 1.0 Mayo 2015 
 *
 */
public class CodiErrorDriver{

	/**
	 * @param args
	 */
	public void escriureCodiError(Salida SF,CodiError ce){
		
	  
	   SF.Write("ESCRIU CodiError");
	   SF.Write(" ");
	   SF.Write(ce.getMensajeError());
	   int mida= ce.getClauExterna().size();
	   SF.Write(mida, ce.getClauExterna().toArray(new String[mida]));
	   SF.Write(ce.getCodiError());
	   SF.Write("  ");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.ReadString();
		Salida SF = new FicheroSalida(Output);
		CodiErrorDriver DCE= new CodiErrorDriver();
		Salida SC = new ConsolaSalida();
		SC.Write("Recorda: El primer que fem es inicialitzar l'error.");
		CodiError ce=new CodiError();
		int a= EF.ReadInt();
		while(a!=-1){
			switch(a) {
			 case 1: 
				 ce.netejaCodiError();
			     break;
			 case 2: 
				 CodiError aux= new CodiError(ce);
				 DCE.escriureCodiError(SF, aux);
			     break;
			 case 3:
				 Integer newCodi = EF.ReadInteger();
				 CodiError auxi= new CodiError(newCodi);
				 DCE.escriureCodiError(SF, auxi);
				 break;
			 case 4:
				 Integer noucodi= EF.ReadInteger();
				 ce.setCodiError(noucodi);
				 break;
			 case 5:
				 Integer clauex=EF.ReadInteger();
				 ce.addClauExterna(clauex);
				 break;
			 case 6:
				 String clauexs=EF.ReadString();
				 ce.addClauExterna(clauexs);
				 break;
			 case 7:
				 DCE.escriureCodiError(SF,ce);
				 break;
			 case 8:
				 int mida= ce.getClauExterna().size();
				   SF.Write(mida, ce.getClauExterna().toArray(new String[mida]));
				   break;
			 case 9:
				 SF.Write(ce.getCodiError());
				 break;
			 case 10:
				 SF.Write(ce.getMensajeError());
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

