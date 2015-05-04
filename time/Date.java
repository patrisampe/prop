package time;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fecha basada en el calendario gregoriano.
 * @author David Moran
 * @version 04/05/2015 01:00
 */
public class Date{

	/**
	 * Dia de la fecha.
	 */
	private Integer Day;
	/**
	 * Mes de la fecha.
	 */
	private Integer Month;
	/**
	 * "Year" de la fecha.
	 */
	private Integer Year;
	
	/**
	 * Diccionario de traduccion de mes(Numero) a mes(Palabra).
	 */
	private static final Map<Integer, String> Meses = CreateMap();
	
	/**
	 * Instancia nula de la classe Date.
	 */
	public static final Date NULL = new Date(-1, -1, -1);
	
	/**
	 * Crea una nueva fecha.
	 * @param Day - Dia.
	 * @param Month - Mes.
	 * @param Year - "Year".
	 */
	public Date(Integer Day, Integer Month, Integer Year){
		this.Day = Day;
		this.Month = Month;
		this.Year = Year;
		if (!esValida()){
			this.Day = -1;
			this.Month = -1;
			this.Year = -1;
		}
	}
	
	/**
	 * Crea una nueva fecha, copia de D.
	 * @param D - Data a copiar.
	 */
	public Date(Date D){
			this.Day = D.Day;
			this.Month = D.Month;
			this.Year = D.Year;
	}
	
	/**
	 * Crea el diccionario de traduccion de meses.
	 * @return El diccionario de traduccion de meses.
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
	 * Consulta el dia.
	 * @return El dia.
	 */
	public Integer getDay(){
		return Day;
	}

	/**
	 * Consulta el mes.
	 * @return El mes.
	 */
	public Integer getMonth(){
		return Month;
	}
	
	/**
	 * Consulta el "Year".
	 * @return El "Year".
	 */
	public Integer getYear(){
		return Year;
	}
	
	/**
	 * Comprueba si la fecha es valida.
	 * @return <i>true</i> si la fecha es valida segun el calendario gregoriano.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esValida(){
		if (Day > 31 || Day < 1) return false;
		if (Month > 12 || Month < 1) return false;
		if ((Month == 4 || Month == 6 || Month == 9 || Month == 11)
			 && Day == 31) return false;
		if (Month == 2) { //Febrer
			if (Day > 29) return false;
			if (!esBisiesto(Year) && Day > 28) return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si la fecha es nula.
	 * @return <i>true</i> si la fecha es nula.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean esNull(){
		return equals(NULL);
	}
	
	/**
	 * Comprueba si el "Year" es bisiesto.
	 * @return <i>true</i> si es bisiesto.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public static Boolean esBisiesto(Integer Year){
		if (Year%4 != 0) return false;
		if (Year%100 != 0) return true;
		if (Year%400 == 0) return true;
		return false;
	}

	/**
	 * Transforma la fecha a un String.
	 * @return La fecha, en formato String numerico.
	 */
	public String toString(){
		if (esNull()) return "NULL";
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
	
	/**
	 * Transforma la fecha a un String.
	 * @return La fecha, en formato String con el nombre del mes.
	 */
	public String toNamedString(){
		if (esNull()) return "NULL";
		String out = "";
		if (Day < 10) out += "0";
		out += Day.toString();
		out += "/";
		out += Meses.get(Month);
		out += "/";
		out += Year.toString();
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
		return (Day == D.Day && Month == D.Month && Year == D.Year);
	}

	/**
	 * Compara dos fechas.
	 * @param S - String con la fecha a comparar.
	 * @return <i>true</i> si las fechas son iguales.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(String S){
		String D = S.substring(0, 2);
		String M = S.substring(3, 5);
		String Y = S.substring(6, S.length());
		return (Day == Integer.parseInt(D)
				&& Month == Integer.parseInt(M)
				&& Year == Integer.parseInt(Y));
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
		if (esNull() || D.esNull()) return 0;
		if (Year > D.Year) return 1;
		if (Year < D.Year) return -1;
		if (Month > D.Month) return 1;
		if (Month < D.Month) return -1;
		if (Day > D.Day) return 1;
		if (Day < D.Day) return -1;
		return 0;
	}
	
	/**
	 * Modifica el dia.
	 * @param Day - nuevo dia.
	 */
	public Boolean setDay(Integer Day){
		Integer temp = this.Day;
		this.Day = Day;
		if (esValida()) return true;
		else {
			this.Day = temp;
			return false;
		}
	}
	
	/**
	 * Modifica el mes.
	 * @param Day - nuevo mes.
	 */
	public Boolean setMonth(Integer Month){
		Integer temp = this.Month;
		this.Month = Month;
		if (esValida()) return true;
		else {
			this.Month = temp;
			return false;
		}
	}
	
	/**
	 * Modifica el "year".
	 * @param Year - nuevo "year".
	 */
	public Boolean setYear(Integer Year){
		Integer temp = this.Year;
		this.Year = Year;
		if (esValida()) return true;
		else {
			this.Year = temp;
			return false;
		}
	}

	/**
	 * Incrementa la fecha en un dia.
	 * @return Nueva fecha.
	 */
	public Date incremento(){
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
			if (esBisiesto(Year))
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
	
	/**
	 * Incrementa la fecha <i>n</i> dias.
	 * @param n - numero de dias a incrementar.
	 * @return Nueva fecha.
	 */
	public Date incremento(Integer n){
		for (int i = 0; i < n; ++i){
			incremento();
		}
		return this;
	}
	
	/**
	 * Incrementa la fecha tantos dias como la longitud del intervalo.
	 * @param DI - Intervalo de fechas con la longitud a avanzar.
	 * @return Nueva fecha.
	 */
	public Date incremento(DateInterval DI){
		int n = DI.days();
		for (int i = 0; i < n; ++i){
			incremento();
		}
		return this;
	}
	
	/**
	 * Convierte un string a una fecha valida.
	 * @param S - String con la fecha.
	 * @return Fecha contenida en S.
	 */
	public static Date stringToDate(String S){
		String D = S.substring(0, 2);
		String M = S.substring(3, 5);
		String Y = S.substring(6, S.length());
		Date ANS;
		try {
			ANS = new Date(Integer.parseInt(D), Integer.parseInt(M), Integer.parseInt(Y));
			if (!ANS.esValida()) ANS = Date.NULL;
		} catch (NumberFormatException n){
			ANS = Date.NULL;
		}
		return ANS;
	}
	
	/**
	 * Calcula la fecha mediana entre dos fechas dadas.
	 * @param D1 - Primera fecha.
	 * @param D2 - Segunda fecha
	 * @return Fecha situada en la mediana entre D1 y D2.
	 */
	public static Date mediana(Date D1, Date D2) {
		if (D1.compareTo(D2) > 0) return Date.mediana(D2, D1);
		DateInterval DI = new DateInterval(D1, D2);
		Integer dias = DI.days()/2;
		return (new Date(D1)).incremento(dias);
	}
	
}