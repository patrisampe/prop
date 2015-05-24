package time;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fecha basada en el calendario Gregoriano.
 * @author David Morán
 * @version 18/5/2015 22:00
 */
public class Date{

	/**
	 * Día de la fecha.
	 */
	private Integer day;
	/**
	 * Mes de la fecha.
	 */
	private Integer month;
	/**
	 * Año de la fecha.
	 */
	private Integer year;
	
	/**
	 * Diccionario de traducción de mes(Numero) a mes(Palabra).
	 */
	private static final Map<Integer, String> Meses = CreateMap();
	
	/**
	 * Instancia nula de la clase Date.
	 */
	public static final Date NULL = new Date(-1, -1, -1);
	
	/**
	 * Crea una nueva fecha.
	 * @param day - Día.
	 * @param month - Mes.
	 * @param year - Año.
	 */
	public Date(Integer day, Integer month, Integer year){
		this.day = day;
		this.month = month;
		this.year = year;
		if (!isValida()){
			this.day = -1;
			this.month = -1;
			this.year = -1;
		}
	}
	
	public Date(String date){
		Date parsed = parseDate(date);
		day = parsed.day;
		month = parsed.month;
		year = parsed.year;
		if (!isValida()){
			day = -1;
			month = -1;
			year = -1;
		}
	}
	
	/**
	 * Crea una nueva fecha, copia de D.
	 * @param D - Data a copiar.
	 */
	public Date(Date D){
			this.day = D.day;
			this.month = D.month;
			this.year = D.year;
	}
	
	/**
	 * Crea el diccionario de traducción de meses.
	 * @return El diccionario de traducción de meses.
	 */
	private static final Map<Integer, String> CreateMap() {
		Map<Integer, String> Diccionario = new HashMap<Integer, String>();
		Diccionario.put(1, "Enero");
		Diccionario.put(2, "Febrero");
		Diccionario.put(3, "Marzo");
		Diccionario.put(4, "Abril");
		Diccionario.put(5, "Mayo");
		Diccionario.put(6, "Junio");
		Diccionario.put(7, "Julio");
		Diccionario.put(8, "Agosto");
		Diccionario.put(9, "Septiembre");
		Diccionario.put(10, "Octubre");
		Diccionario.put(11, "Noviembre");
		Diccionario.put(12, "Diciembre");
        return Collections.unmodifiableMap(Diccionario);
	}

	/**
	 * Consulta el día.
	 * @return El día.
	 */
	public Integer getDay(){
		return day;
	}

	/**
	 * Consulta el mes.
	 * @return El mes.
	 */
	public Integer getMonth(){
		return month;
	}
	
	/**
	 * Consulta el año.
	 * @return El año.
	 */
	public Integer getYear(){
		return year;
	}
	
	/**
	 * Comprueba si la fecha es válida.
	 * @return <i>true</i> si la fecha es válida segun el calendario Gregoriano.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isValida(){
		if (day > 31 || day < 1) return false;
		if (month > 12 || month < 1) return false;
		if ((month == 4 || month == 6 || month == 9 || month == 11)
			 && day == 31) return false;
		if (month == 2) { //Febrer
			if (day > 29) return false;
			if (!isBisiesto(year) && day > 28) return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si la fecha es nula.
	 * @return <i>true</i> si la fecha es nula.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean isNull(){
		return equals(NULL);
	}
	
	/**
	 * Comprueba si el año es bisiesto.
	 * @return <i>true</i> si el año es bisiesto.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public static Boolean isBisiesto(Integer year){
		if (year%4 != 0) return false;
		if (year%100 != 0) return true;
		if (year%400 == 0) return true;
		return false;
	}

	/**
	 * Transforma la fecha a un String.
	 * @return La fecha, en formato String numerico.
	 */
	public String toString(){
		if (isNull()) return "NULL";
		String out = "";
		if (day < 10) out += "0";
		out += day.toString();
		out += "/";
		if (month < 10) out += "0";
		out += month.toString();
		out += "/";
		out += year.toString();
		return out;
	}
	 
	/**
	 * Transforma la fecha a un String.
	 * @return La fecha, en formato String con el nombre del mes.
	 */
	public String toNamedString(){
		if (isNull()) return "NULL";
		String out = "";
		if (day < 10) out += "0";
		out += day.toString();
		out += "/";
		out += Meses.get(month);
		out += "/";
		out += year.toString();
		return out;
	}
	
	/**
	 * Compara dos fechas.
	 * @param D - Fecha a comparar.
	 * @return <i>true</i> si las fechas son iguales.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(Date D){
		return (day == D.day && month == D.month && year == D.year);
	}
	
	/**
	 * Compara dos fechas.
	 * @param D - Fecha a comparar.
	 * @return <b>-1</b> si la fecha es anterior a D.
	 * <br>
	 * <b>0</b> si las fechas son iguales.
	 * <br>
	 * <b>1</b> si la fecha es posterior a D.
	 */
	public Integer compareTo(Date D){
		if (isNull() || D.isNull()) return 0;
		if (year > D.year) return 1;
		if (year < D.year) return -1;
		if (month > D.month) return 1;
		if (month < D.month) return -1;
		if (day > D.day) return 1;
		if (day < D.day) return -1;
		return 0;
	}
	
	/**
	 * Modifica el día.
	 * @param day - nuevo día.
	 */
	public Boolean setDay(Integer day){
		Integer temp = this.day;
		this.day = day;
		if (isValida()) return true;
		else {
			this.day = temp;
			return false;
		}
	}
	
	/**
	 * Modifica el mes.
	 * @param day - nuevo mes.
	 */
	public Boolean setMonth(Integer month){
		Integer temp = this.month;
		this.month = month;
		if (isValida()) return true;
		else {
			this.month = temp;
			return false;
		}
	}
	
	/**
	 * Modifica el año.
	 * @param year - nuevo año.
	 */
	public Boolean setYear(Integer year){
		Integer temp = this.year;
		this.year = year;
		if (isValida()) return true;
		else {
			this.year = temp;
			return false;
		}
	}

	/**
	 * Incrementa la fecha en un día.
	 */
	public void incremento(){
		++day;
		if (day > 31) {
			day = 1;
			++month;
			if (month > 12) {
				month = 1;
				++year;
			}
		}
		else if ((month == 4 || month == 6 || month == 9 || month == 11)
				 && day == 31) {
			day = 1;
			++month;
		}
		else if (month == 2){
			if (isBisiesto(year))
				if (day == 30) {
					day = 1;
					++month;
				}
			else
				if (day == 29) {
					day = 1;
					++month;
				}
		}		
	}
	
	/**
	 * Incrementa la fecha <i>n</i> días.
	 * @param n - numero de días a incrementar.
	 */
	public void incremento(Integer n){
		for (int i = 0; i < n; ++i){
			incremento();
		}
	}
	
	/**
	 * Incrementa la fecha tantos días como la longitud del intérvalo.
	 * @param DI - intérvalo de fechas con la longitud a avanzar.
	 */
	public void incremento(DateInterval DI){
		int n = DI.days();
		for (int i = 0; i < n; ++i){
			incremento();
		}
	}
	
	/**
	 * Convierte un string a una fecha válida.
	 * @param S - String con la fecha.
	 * @return Fecha contenida en S.
	 */
	public static Date parseDate(String S){
		String[] date = S.split("/");
		if (date.length != 3) date = S.split("-");
		if (date.length != 3) return Date.NULL;
		Date ANS;
		try {
			ANS = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			if (!ANS.isValida()) ANS = Date.NULL;
		} catch (NumberFormatException n){
			ANS = Date.NULL;
		}
		return ANS;
	}
	
	/**
	 * Calcula la fecha mediana entre dos fechas dadas.
	 * @param D1 - Primera fecha.
	 * @param D2 - Segunda fecha.
	 * @return Fecha situada en la mediana entre D1 y D2.
	 */
	public static Date mediana(Date D1, Date D2) {
		if (D1.compareTo(D2) > 0) return Date.mediana(D2, D1);
		DateInterval DI = new DateInterval(D1, D2);
		Integer dias = DI.days()/2;
		Date D = new Date (D1);
		D.incremento(dias);
		return D;
	}
	
}