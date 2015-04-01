package utiles;

public class Atributos_TipoEvento {

	private Integer Importancia;
	private String[] Eventos;//Los evento que esten son los que se quieren eliminar
	
	public static final Integer ImportanciaNull=0;
	public static final String[] EventosNull=new String[0];
	public static final String EventoiNull="NULL";
	
	public Atributos_TipoEvento() {
		Importancia = ImportanciaNull;
		Eventos= EventosNull;
	}
	
	public Atributos_TipoEvento(Integer importancia) {
		Importancia = importancia;
		Eventos = EventosNull;
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
