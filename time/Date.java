package time;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Date{

	// Atributs
	private Integer Day;
	private Integer Month;
	private Integer Year;
	private static final Map<Integer, String> Meses = CreateMap();
	public static final Date NULL = new Date(-1, -1, -1);
	
	// Constructores
	public Date(Integer Day, Integer Month, Integer Year){
		this.Day = Day;
		this.Month = Month;
		this.Year = Year;
		if (!Es_valida()){
			this.Day = 0;
			this.Month = 0;
			this.Year = 0;
		}
	}
	
	public Date(Date D){
			this.Day = D.Day;
			this.Month = D.Month;
			this.Year = D.Year;
	}
	
	private static Map<Integer, String> CreateMap() {
		Map<Integer, String> Diccionario = new HashMap<Integer, String>();
		Diccionario.put(1, "Enero");
		Diccionario.put(2, "Febrero");
		Diccionario.put(3, "Marzo");
		Diccionario.put(4, "Abril");
		Diccionario.put(5, "Mayo");
		Diccionario.put(6, "Junio");
		Diccionario.put(7, "Julio");
		Diccionario.put(8, "Agosto");
		Diccionario.put(9, "Setiembre");
		Diccionario.put(10, "Octubre");
		Diccionario.put(11, "Noviembre");
		Diccionario.put(12, "Diciembre");
        return Collections.unmodifiableMap(Diccionario);
	}

	
	// Consultores
	public Integer Day(){
		return Day;
	}

	public Integer Month(){
		return Month;
	}
	
	public Integer Year(){
		return Day;
	}
	
	public Boolean Es_valida(){
		if (Day > 31 || Day < 1) return false;
		if (Month > 12 || Month < 1) return false;
		if ((Month == 4 || Month == 6 || Month == 9 || Month == 11)
			 && Day == 31) return false;
		if (Month == 2) { //Febrer
			if (Day > 29) return false;
			if (!Es_bisiesto(Year) && Day > 28) return false;
		}
		return true;
	}
	
	public Boolean Es_null(){
		return equals(NULL);
	}
	
	public static Boolean Es_bisiesto(Integer Year){
		if (Year%4 != 0) return false;
		if (Year%100 != 0) return true;
		if (Year%400 == 0) return true;
		return false;
	}

	public String ToString(){
		String out = "";
		if (Day < 10) out += "0";
		out += Day.toString();
		out += "/";
		if (Month < 10) out += "0";
		out += Month.toString();
		out += "/";
		out += Year.toString();
		return out;
	}
	
	public String ToNamedString(){
		String out = "";
		if (Day < 10) out += "0";
		out += Day.toString();
		out += "/";
		out += Meses.get(Month);
		out += "/";
		out += Year.toString();
		return out;
	}
	
	public Boolean equals(Date D){
		return (Day == D.Day && Month == D.Month && Year == D.Year);
	}

	public Boolean equals(String S){
		String D = S.substring(0, 2);
		String M = S.substring(3, 5);
		String Y = S.substring(6, S.length());
		return (Day == Integer.parseInt(D)
				&& Month == Integer.parseInt(M)
				&& Year == Integer.parseInt(Y));
	}
	
	public Integer compareTo(Date D){
		if (Year > D.Year) return 1;
		if (Year < D.Year) return -1;
		if (Month > D.Month) return 1;
		if (Month < D.Month) return -1;
		if (Day > D.Day) return 1;
		if (Day < D.Day) return -1;
		return 0;
	}
	
	// Modificadores
 	public Boolean SetDay(Integer Day){
		Integer temp = this.Day;
		this.Day = Day;
		if (Es_valida()) return true;
		else {
			this.Day = temp;
			return false;
		}
	}
	
	public Boolean SetMonth(Integer Month){
		Integer temp = this.Month;
		this.Month = Month;
		if (Es_valida()) return true;
		else {
			this.Month = temp;
			return false;
		}
	}
	
	public Boolean SetYear(Integer Year){
		Integer temp = this.Year;
		this.Year = Year;
		if (Es_valida()) return true;
		else {
			this.Year = temp;
			return false;
		}
	}

	public Date Incremento(){
		++Day;
		if (Day > 31) {
			Day = 1;
			++Month;
			if (Month > 12) {
				Month = 1;
				++Year;
			}
		}
		else if ((Month == 4 || Month == 6 || Month == 9 || Month == 11)
				 && Day == 31) {
			Day = 1;
			++Month;
		}
		else if (Month == 2){
			if (Es_bisiesto(Year))
				if (Day == 30) {
					Day = 1;
					++Month;
				}
			else
				if (Day == 29) {
					Day = 1;
					++Month;
				}
		}		
		return this;
	}
	
	public Date Incremento(Integer n){
		for (int i = 0; i < n; ++i){
			Incremento();
		}
		return this;
	}
	
	public static Date StringToDate(String S){
		String D = S.substring(0, 2);
		String M = S.substring(3, 5);
		String Y = S.substring(6, S.length());
		return new Date(Integer.parseInt(D), Integer.parseInt(M), Integer.parseInt(Y));
	}
	
}