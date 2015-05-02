package controladores;

import java.util.Set;
import java.util.TreeSet;

import time.*;
import utiles.CodiError;
import utiles.Conjunto;
import dominio.Diputado;

public class ControladorDominioDiputado {
	
	private CodiError error;
	private Conjunto<Diputado> conjuntoDiputados;
	private static ControladorDominioDiputado instance = null;
	
	protected ControladorDominioDiputado(){
		conjuntoDiputados = new Conjunto<Diputado>(Diputado.class);
		error = new CodiError();
	}
	
	public static ControladorDominioDiputado getInstance() {
	      if (instance == null) {
	         instance = new ControladorDominioDiputado();
	      }
	      return instance;
	}
	
	public Integer numeroDiputados(){
		return conjuntoDiputados.size();
	}
	
	public void addAll(Set<Diputado> diputados){
		conjuntoDiputados.addAll(diputados);
	}
	
	public Set<Diputado> getAll() {
		return conjuntoDiputados.getAll();
	}
	
	public Set<String> getNombres() {
		return conjuntoDiputados.getStringKeys();
	}
	
	public void addDiputado(String nombreDiputado, String nombrePartido, String nombreEstado, Date FechaDeNacimiento) {
		if (existsDiputado(nombreDiputado)) {
			error.setCodiError(4);
			error.setClauExterna(nombreDiputado);
		}
		else {
			Diputado D = new Diputado(nombreDiputado, nombrePartido, nombreEstado, FechaDeNacimiento);
			conjuntoDiputados.add(nombreDiputado, D);
		}
	}

	public Boolean existsDiputado(String nombreDiputado) {
		return conjuntoDiputados.exists(nombreDiputado);
	}
	
	public void removeDiputado(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.remove(nombreDiputado);
			/*GrupoAfin
 			ControladorDominioGrupoAfin CDGA = ControladorDominioGrupoAfin.getInstance();
			CDGA.removeDiputado(nombreDiputado);
			*/
 			ControladorDominioEvento CDE = ControladorDominioEvento.getInstance();
			CDE.removeDiputado(nombreDiputado);
 			ControladorDominioVotacion CDV = ControladorDominioVotacion.getInstance();
			CDV.removeDiputado(nombreDiputado);
			ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
			CDL.removeDiputadoFromLegislaturas(nombreDiputado);
		}
		//TODO: Falten completar les crides a eliminar
	}
		
	public void setPartidoPolitico(String nombreDiputado, String nombrePartido) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setPartidoPolitico(nombrePartido);
	}

	public void setEstado(String nombreDiputado, String nombreEstado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setEstado(nombreEstado);
	}
	
	public void setFechaDeNacimiento(String nombreDiputado, Date FechaDeNacimiento) {
		if (!existsDiputado(nombreDiputado)){
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).setFechaNacimiento(FechaDeNacimiento);
	}

	public String getPartidoPolitico(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
			return "";
		}
		else return conjuntoDiputados.get(nombreDiputado).getPartidoPolitico();
	}
	
	public String getEstado(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
			return "";
		}
		else return conjuntoDiputados.get(nombreDiputado).getEstado(); 
	}

	
	public Date getFechaDeNacimiento(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
			return Date.NULL;
		}
		else return conjuntoDiputados.get(nombreDiputado).getFechaDeNacimiento();
	}

	public void addLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else if (!CDL.existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else if (existsLegistura(nombreDiputado, identificadorLegislatura)) {
			error.setCodiError(12);
			error.setClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else conjuntoDiputados.get(nombreDiputado).addLegistura(identificadorLegislatura);
	}
	
	public void setLegisturas(String nombreDiputado, Set<Integer> legislaturas) {
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else {
			for (Integer identificadorLegislatura:legislaturas) {
				if (!CDL.existsLegislatura(identificadorLegislatura)) {
					error.setCodiError(17);
					error.setClauExterna(identificadorLegislatura);
					return;
				}
				else if (existsLegistura(nombreDiputado, identificadorLegislatura)) {
					error.setCodiError(12);
					error.setClauExterna(identificadorLegislatura);
					error.addClauExterna(nombreDiputado);
					return;
				}
			}
			conjuntoDiputados.get(nombreDiputado).setLegisturas(legislaturas);
		}
	}
	
	public Set<Integer> getLegislaturas(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
			return new TreeSet<Integer>();
		}
		else return conjuntoDiputados.get(nombreDiputado).getLegislaturas();
	}
	
	public Boolean existsLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
			return false;
		}
		else return conjuntoDiputados.get(nombreDiputado).hasLegistura(identificadorLegislatura);
	}
	
	public void removeLegistura(String nombreDiputado, Integer identificadorLegislatura) {
		ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else if (!CDL.existsLegislatura(identificadorLegislatura)) {
			error.setCodiError(17);
			error.setClauExterna(identificadorLegislatura);
		}
		else if (!existsLegistura(nombreDiputado, identificadorLegislatura)) {
			error.setCodiError(13);
			error.setClauExterna(identificadorLegislatura);
			error.addClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.get(nombreDiputado).removeLegistura(identificadorLegislatura);
			if (CDL.existsDiputado(identificadorLegislatura, nombreDiputado))
				CDL.removeDiputado(identificadorLegislatura, nombreDiputado);
		}
	}
	
	public void removeLegisturas(String nombreDiputado) {
		if (!existsDiputado(nombreDiputado)) {
			error.setCodiError(3);
			error.setClauExterna(nombreDiputado);
		}
		else {
			conjuntoDiputados.get(nombreDiputado).removeLegisturas();
			ControladorDominioLegislatura CDL = ControladorDominioLegislatura.getInstance();
			CDL.removeDiputadoFromLegislaturas(nombreDiputado);
		}
	}
	
	//Elimina la legislatura indicada de tots els diputats
	public void removeLegislaturaFromDiputados(Integer identificadorLegislatura) {
		for (Diputado D:conjuntoDiputados.getAll()) {
			String nombreDiputado = D.getNombre();
			if (existsLegistura(nombreDiputado, identificadorLegislatura))
				removeLegistura(nombreDiputado, identificadorLegislatura);
		}
	}	
	
	public Boolean hasCodiError() {
		return (error.getCodiError() != 0);
	}
	
	public CodiError getCodiError() {
		return error;
	}
	
}