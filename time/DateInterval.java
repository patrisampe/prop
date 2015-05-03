package time;

/**
 * Intervalo de tiempo entre dos fechas.
 * @author David Moran
 * @version 03/05/2015 01:02
 */
public class DateInterval {
	
	/**
	 * Inicio del intervalo.
	 */
	private Date Inicio;
	
	/**
	 * Fin del intervalo.
	 */
	private Date Fin;
	
	/**
	 * Instancia nula de la clase.
	 */
	public static final DateInterval NULL = new DateInterval(Date.NULL, Date.NULL);
	
	/**
	 * Crea un nuevo intervalo vacio.
	 */
	public DateInterval(){
		Inicio = new Date(Date.NULL);
		Fin = new Date(Date.NULL);
	}
	
	/**
	 * Crea un nuevo intervalo a partir de las dos fechas.
	 * @param I - Fecha de inicio.
	 * @param F - Fecha de fin.
	 */
	public DateInterval(Date I, Date F){
		if (I.compareTo(F) <= 0) {
			Inicio = new Date(I);
			Fin = new Date(F);
		}
		else {
			Inicio = new Date(Date.NULL);
			Fin = new Date(Date.NULL);
		}
	}
	
	/**
	 * Crea un nuevo intervalo copia de otro DateInterval.
	 * @param DI - DateInterval a ser copiado.
	 */
	public DateInterval(DateInterval DI){
		Inicio = new Date(DI.Inicio);
		Fin = new Date(DI.Fin);
	}

	/**
	 * Compara dos intervalos.
	 * @param DI - DateInterval a ser comparado.
	 * @return <i>true</i> en caso de que los intervalos coincidan completamente.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean equals(DateInterval DI) {
		return (Inicio.equals(DI.Inicio) && Fin.equals(DI.Fin));
	}
	
	/**
	 * Consulta si el intervalo contiene una fecha.
	 * @param D - Fecha a ser comparado.
	 * @return <i>true</i> en caso de que el intervalo contenga la fecha.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean contains(Date D) {
		return ((Inicio.compareTo(D) <= 0 && Fin.compareTo(D) >= 0) ||
				(Inicio.compareTo(D) <= 0 && Fin.esNull()) ||
				(Inicio.esNull() && Fin.compareTo(D) >= 0));
	}
	
	/**
	 * Consulta si el intervalo contiene otro intervalo dado.
	 * @param DI - DateInterval a ser comparado.
	 * @return <i>true</i> en caso de que el intervalo contenga DI.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean contains(DateInterval DI) {
		return ((contains(DI.Inicio) && contains(DI.Fin)) ||
				(contains(DI.Inicio) && DI.Fin.esNull()) || 
				(DI.Inicio.esNull() && contains(DI.Fin)));
	}
	
	/**
	 * Consulta si el intervalo intersecciona con otro intervalo dado.
	 * @param DI - DateInterval a ser comparado.
	 * @return <i>true</i> en caso de que el intervalo interseccione con DI.
	 * <br>
	 * <i>false</i> en cualquier otro caso.
	 */
	public Boolean intersects(DateInterval DI) {
		return (contains(DI.Inicio) || contains(DI.Fin));
	}
	
	/**
	 * Consulta la longitud en dias del intervalo.
	 * @return Numero de dias contenidos en el intervalo.
	 */
	public Integer days() {
		Date aux = new Date(Inicio);
		Integer count = 0;
		while (!aux.equals(Fin)) {
			++count;
			aux.incremento();
		}
		return count;
	}
	
	/**
	 * Consulta la fecha de inicio del intervalo.
	 * @return Fecha de inicio del intervalo.
	 */
	public Date getInicio() {
		return Inicio;
	}
	
	/**
	 * Consulta la fecha de fin del intervalo.
	 * @return Fecha de fin del intervalo.
	 */
	public Date getFin() {
		return Fin;
	}
	
	/**
	 * Modifica la fecha de inicio del intervalo.
	 */
	public void setInicio(Date Ini) {
		if (Ini.compareTo(Fin) <= 0) Inicio = Ini;
	} 
	
	/**
	 * Modifica la fecha de fin del intervalo.
	 */
	public void setFin(Date Final) {
		if (Inicio.compareTo(Final) <= 0) Fin = Final;
	}
	
	/**
	 * Transforma el intervalo a un String con las dos fechas.
	 * @return Las dos fechas, en formato String numerico.
	 */
	public String toString() {
		return Inicio.toString() + " - " + Fin.toString();
	}
	
	/**
	 * Transforma el intervalo a un String con las dos fechas.
	 * @return Las dos fechas, en formato String con el nombre del mes.
	 */
	public String ToNamedString() {
		return Inicio.toNamedString() + " - " + Fin.toNamedString();
	}
	
	/**
	 * Calcula la union de los dos intervalos.
	 * @param DI1 - Primer DateInterval.
	 * @param DI2 - Segundo DateInterval.
	 * @return La union (DI1_u_DI2).
	 */
	public static DateInterval merge(DateInterval DI1, DateInterval DI2) {
		DateInterval ret = new DateInterval();
		if(DI1.intersects(DI2)) {
			ret.Inicio = new Date(min(DI1.Inicio, DI2.Inicio));
			ret.Fin = new Date(max(DI1.Fin, DI2.Fin));
		}
		else ret = DateInterval.NULL;
		return ret;
	}
	
	/**
	 * Calcula la interseccion de los dos intervalos.
	 * @param DI1 - Primer DateInterval.
	 * @param DI2 - Segundo DateInterval.
	 * @return La union (DI1_n_DI2).
	 */
	public static DateInterval intersection(DateInterval DI1, DateInterval DI2) {
		DateInterval ret = new DateInterval();
		if(DI1.intersects(DI2)) {
			ret.Inicio = new Date(max(DI1.Inicio, DI2.Fin));
			ret.Fin = new Date(min(DI1.Inicio, DI2.Fin));
		}
		else ret = DateInterval.NULL;
		return ret;
	}
	
	/**
	 * Compara dos fechas y devuelve la menor.
	 * @param D1 - Primera fecha.
	 * @param D2 - Segunda fecha.
	 * @return min(D1, D2).
	 */
	private static Date min(Date D1, Date D2) {
		return D1.compareTo(D2) <= 0 ? D1 : D2;
	}
	
	/**
	 * Compara dos fechas y devuelve la mayor.
	 * @param D1 - Primera fecha.
	 * @param D2 - Segunda fecha.
	 * @return max(D1, D2).
	 */
	private static Date max(Date D1, Date D2) {
		return D1.compareTo(D2) >= 0 ? D1 : D2;
	}
		
}