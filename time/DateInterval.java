package time;


public class DateInterval{
	
	//Atributs
	private Date Inici;
	private Date Fi;
	public static final DateInterval NULL = new DateInterval(Date.NULL,Date.NULL);
	
	public DateInterval(){
		Inici = new Date(Date.NULL);
		Fi = new Date(Date.NULL);
	}
	
	public DateInterval(Date I, Date F){
		if (I.compareTo(F) <= 0) {
			Inici = new Date(I);
			Fi = new Date(F);
		}
		else {
			Inici = new Date(Date.NULL);
			Fi = new Date(Date.NULL);
		}
	}
	
	public DateInterval(DateInterval DI){
			Inici = new Date(DI.Inici);
			Fi = new Date(DI.Fi);
	}

	
	//Consultores
	public Boolean equals(DateInterval DI) {
		return (Inici.equals(DI.Inici) && Fi.equals(DI.Fi));
	}
	
	public Boolean contains(Date D) {
		return (Inici.compareTo(D) >= 0 && Fi.compareTo(D) <= 0);
	}
	
	public Boolean contains(DateInterval DI) {
		return (contains(DI.Inici) && contains(DI.Fi));
	}
	
	public Boolean intersects(DateInterval DI) {
		return (contains(DI.Inici) || contains(DI.Fi));
	}
	
	public Integer days() {
		Date aux = new Date(Inici);
		Integer count = 0;
		while (!aux.equals(Fi)) {
			++count;
			aux.Incremento();
		}
		return count;
	}
	
	public Date getInici() {
		return this.Inici;
	}
	
	public Date getFi() {
		return this.Fi;
	}
	
	//Modificadores
	public Boolean setInici(Date Ini) {
		if (Ini.compareTo(Fi) <= 0) {
			Inici = Ini;
			return true;
		}
		return false;
	} 
	
	public Boolean setFi(Date Final) {
		if (Inici.compareTo(Final) <= 0) {
			Fi = Final;
			return true;
		}
		return false;
	}
	
	public String ToString() {
		return this.Inici.ToString() + " " + this.Fi.ToString();
	}
	
	public String ToNamedString() {
		return this.Inici.ToNamedString() + " " + this.Fi.ToNamedString();
	}
	
		//retorna la unió si son continus
	public static DateInterval merge(DateInterval DI1, DateInterval DI2) {
		DateInterval ret = new DateInterval();
		if(DI1.intersects(DI2)) {
			ret.Inici = new Date(min(DI1.Inici, DI2.Inici));
			ret.Fi = new Date(max(DI1.Inici, DI2.Fi));
		}
		else ret = DateInterval.NULL;
		return ret;
	}
	
	public static DateInterval intersection(DateInterval DI1, DateInterval DI2) {
		DateInterval ret = new DateInterval();
		if(DI1.intersects(DI2)) {
			ret.Inici = new Date(max(DI1.Inici, DI2.Fi));
			ret.Fi = new Date(min(DI1.Inici, DI2.Fi));
		}
		else ret = DateInterval.NULL;
		return ret;
	}
	
	
	
	
	//funcions auxiliars
	private static Date min(Date a, Date b) {
		return a.compareTo(b) <= 0 ? a : b;
	}
	
	private static Date max(Date a, Date b) {
		return a.compareTo(b) >= 0 ? a : b;	}
		
}
