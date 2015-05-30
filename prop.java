/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.ControladorDominioDiputado;
import controladores.ControladorDominioEvento;
import controladores.ControladorDominioLegislatura;
import controladores.ControladorDominioVotacion;
import controladores.ControladorVista;
import dominio.TipoVoto;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import time.Date;
import vista.*;

/**
 *
 * @author MiguelÁngel
 */
public class prop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorVista aux=ControladorVista.getInstance();
        aux.iniciaVista();
        
    }
    
}
