package drivers;

import io.ConsolaEntrada;
import io.Entrada;
import io.FitxerEntrada;
import io.FitxerSortida;
import io.Sortida;

import time.Date;
import time.DateInterval;

public class DateIntervalDriver {
	private static DateInterval DI;
	
	public static DateInterval ReadDateInterval(Entrada eF) {
		DateInterval D = new DateInterval();
		D.setInicio(ReadDate(eF));
		D.setFin(ReadDate(eF));
		return D;
	}
	
	public static Date ReadDate(Entrada eF) {
		return new Date(eF.ReadInteger(),eF.ReadInteger(),eF.ReadInteger());
	}
	
	
	public static void main (String args[]) {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.ReadString();
		Entrada EF = new FitxerEntrada(Input);
		String Output = EC.ReadString();
		Sortida SF = new FitxerSortida(Output);
		int a= EF.ReadInt();
		while(a!=-1) {
			switch(a) {
			 case 1: 
				 DI = new DateInterval();
			     break;
			 case 2: 
				 DI = new DateInterval(ReadDate(EF), ReadDate(EF));
			     break;
			 case 3: 
				 DI = new DateInterval(ReadDateInterval(EF));
			     break;
			 case 4: 
				 SF.Write(DI.contains(ReadDate(EF)));
			     break;
			 case 5:
				 SF.Write(DI.contains(ReadDateInterval(EF)));
				 break;
			 case 6: 
				 SF.Write(DI.days());
			     break;
			 case 7:
				 SF.Write(DI.equals(ReadDateInterval(EF)));
			     break;
			 case 8:
				 SF.Write(DI.getFin().toString());
			     break;
			 case 9:
				 SF.Write(DI.getInicio().toString());
				 break;
			 case 10:
				 SF.Write(DI.intersects(ReadDateInterval(EF)));
				 break;
			 case 11:
				 DI.setFin(ReadDate(EF));
				 break;
			 case 12:
				 DI.setInicio(ReadDate(EF));
				 break;
			 case 13:
				 SF.Write(DI.ToNamedString());
				 break;
			 case 14:
				 SF.Write(DI.toString());
				 break;
			 case 15:
				 SF.Write(DateInterval.intersection(ReadDateInterval(EF), ReadDateInterval(EF)).toString());
			 case 16:
				 SF.Write(DateInterval.merge(ReadDateInterval(EF), ReadDateInterval(EF)).toString());
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