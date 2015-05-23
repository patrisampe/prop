package drivers;

import java.io.IOException;

import io.*;
import time.Date;
import time.DateInterval;

public class DateIntervalDriver {
	private static DateInterval DI;
	
	public static DateInterval ReadDateInterval(Entrada eF) throws IOException {
		DateInterval D = new DateInterval();
		D.setInicio(ReadDate(eF));
		D.setFin(ReadDate(eF));
		return D;
	}
	
	public static Date ReadDate(Entrada eF) throws IOException {
		return new Date(eF.readInteger(),eF.readInteger(),eF.readInteger());
	}
	
	
	public static void main (String args[]) throws IOException {
		Entrada EC = new ConsolaEntrada();
		String Input = EC.readString();
		Entrada EF = new FicheroEntrada(Input);
		String Output = EC.readString();
		Salida SF = new FicheroSalida(Output);
		int a= EF.readInt();
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
				 SF.write(DI.contains(ReadDate(EF)));
			     break;
			 case 5:
				 SF.write(DI.contains(ReadDateInterval(EF)));
				 break;
			 case 6: 
				 SF.write(DI.days());
			     break;
			 case 7:
				 SF.write(DI.equals(ReadDateInterval(EF)));
			     break;
			 case 8:
				 SF.write(DI.getFin().toString());
			     break;
			 case 9:
				 SF.write(DI.getInicio().toString());
				 break;
			 case 10:
				 SF.write(DI.intersects(ReadDateInterval(EF)));
				 break;
			 case 11:
				 DI.setFin(ReadDate(EF));
				 break;
			 case 12:
				 DI.setInicio(ReadDate(EF));
				 break;
			 case 13:
				 SF.write(DI.ToNamedString());
				 break;
			 case 14:
				 SF.write(DI.toString());
				 break;
			 case 15:
				 SF.write(DateInterval.intersection(ReadDateInterval(EF), ReadDateInterval(EF)).toString());
			 case 16:
				 SF.write(DateInterval.merge(ReadDateInterval(EF), ReadDateInterval(EF)).toString());
			 default: 
			    SF.write(" numero no correcto. Para cerrar -1 ");
			     break;
			 }
			a=EF.readInt();
		}
		EF.close();
		SF.close();
	}
}