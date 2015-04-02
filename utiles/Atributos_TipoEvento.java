package utiles;

public class Atributos_TipoEvento {

	private Integer Importancia;
	private String[] Eventos;//Los evento que esten son los que se quieren eliminar
	
	public static final Integer ImportanciaUnchanged=0;
	public static final String[] EventosUnchanged=new String[0];
	//public static final String EventoiNull="NULL";
	
	public Atributos_TipoEvento() {
		Importancia = ImportanciaUnchanged;
		Eventos= EventosUnchanged;
	}
	
	public Atributos_TipoEvento(Integer importancia, String[] eventos) {
		Importancia = importancia;
		Eventos = eventos;
	}
	
	public Integer getImportancia() {
		return Importancia;
	}
	
	public void setImportancia(Integer importancia) {
		Importancia = importancia;
	}
	
	public String[] getEventos() {
		return Eventos;
	}
	
	public void setEventos(String[] eventos) {
		Eventos = eventos;
	}
	
	
}