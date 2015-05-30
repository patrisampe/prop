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
        ControladorDominioLegislatura ctrlLeg = ControladorDominioLegislatura.getInstance();
        ControladorDominioDiputado ctrlDip = ControladorDominioDiputado.getInstance();
        ControladorDominioEvento ctrlEve = ControladorDominioEvento.getInstance();
        ControladorDominioVotacion ctrlVot = ControladorDominioVotacion.getInstance();
        ctrlLeg.addLegislatura(0, Date.parseDate("01/01/1990"), Date.parseDate("31/12/1991"));
        ctrlLeg.addLegislatura(1, Date.parseDate("01/01/1992"), Date.parseDate("31/12/1993"));
        ctrlLeg.addLegislatura(2, Date.parseDate("01/01/1994"), Date.parseDate("31/12/1995"));
        ctrlLeg.addLegislatura(3, Date.parseDate("01/01/1996"), Date.parseDate("31/12/1997"));
        ctrlLeg.addLegislatura(4, Date.parseDate("01/01/1998"), Date.parseDate("31/12/1999"));
        ctrlLeg.addLegislatura(5, Date.parseDate("01/01/2000"), Date.parseDate("31/12/2001"));
        ctrlLeg.addLegislatura(6, Date.parseDate("01/01/2002"), Date.parseDate("31/12/2003"));
        ctrlLeg.addLegislatura(7, Date.parseDate("01/01/2004"), Date.parseDate("31/12/2005"));
        ctrlLeg.addLegislatura(8, Date.parseDate("01/01/2006"), Date.parseDate("31/12/2007"));
        ctrlLeg.addLegislatura(9, Date.parseDate("01/01/2008"), Date.parseDate("31/12/2009"));
        ctrlDip.addDiputado("Carlos", "Partido Popular", "Ojallo", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Carlos", 0);
        ctrlDip.addLegistura("Carlos", 1);
        ctrlDip.addDiputado("Pedro", "Partido Socialista Obrero Español", "California", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Pedro", 0);
        ctrlDip.addLegistura("Pedro", 1);
        ctrlDip.addDiputado("Silvia", "Convergencia i unió", "Carolina del Norte", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Silvia", 0);
        ctrlDip.addLegistura("Silvia", 1);
        ctrlDip.addDiputado("María", "Pablemos", "Nuevo México", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("María", 0);
        ctrlDip.addLegistura("María", 1);
        ctrlDip.addDiputado("Jose", "Izquierda Unida", "Texas", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Jose", 0);
        ctrlDip.addLegistura("Jose", 1);
        ctrlDip.addDiputado("Luís", "Ciudadanos", "Orejón", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Luís", 0);
        ctrlDip.addLegistura("Luís", 1);
        ctrlDip.addDiputado("Miguel", "Convergencia i unió", "Illinua", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Miguel", 0);
        ctrlDip.addLegistura("Miguel", 1);
        ctrlDip.addDiputado("Juan", "Partido Socialista Obrero Español", "Visconsin", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Juan", 0);
        ctrlDip.addLegistura("Juan", 1);
        ctrlDip.addDiputado("Sara", "Partido Popular", "Dakota del Norte", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Sara", 0);
        ctrlDip.addLegistura("Sara", 1);
        ctrlDip.addDiputado("Antonio", "Pablemos", "Güayomin", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Antonio", 0);
        ctrlDip.addLegistura("Antonio", 1);
        ctrlDip.addDiputado("Sebastián", "Convergencia i unió", "Kansas", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Sebastián", 0);
        ctrlDip.addLegistura("Sebastián", 1);
        ctrlDip.addDiputado("Anita", "Partido Socialista Obrero Español", "Colorado", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Anita", 0);
        ctrlDip.addLegistura("Anita", 1);
        ctrlDip.addDiputado("Lucía", "Izquierda Unida", "Virginia", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Lucía", 0);
        ctrlDip.addLegistura("Lucía", 1);
        ctrlDip.addDiputado("Federico", "Pablemos", "Arizona", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Federico", 0);
        ctrlDip.addLegistura("Federico", 1);
        ctrlDip.addDiputado("Manolo", "Partido Popular", "Alabama", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Manolo", 0);
        ctrlDip.addLegistura("Manolo", 1);
        ctrlDip.addDiputado("Víctor", "Convergencia i unió", "Tennessee", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Víctor", 0);
        ctrlDip.addLegistura("Víctor", 1);
        ctrlDip.addDiputado("Sandra", "Partido Socialista Obrero Español", "Oklahoma", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Sandra", 0);
        ctrlDip.addLegistura("Sandra", 1);
        ctrlDip.addDiputado("Alberto", "Izquierda Unida", "Nebraska", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Alberto", 0);
        ctrlDip.addLegistura("Alberto", 1);
        ctrlDip.addDiputado("Susana", "Partido Popular", "Washington", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Susana", 0);
        ctrlDip.addLegistura("Susana", 1);
        ctrlDip.addDiputado("Basilio", "Pablemos", "Massachussetts", Date.parseDate("25/03/1970"));
        ctrlDip.addLegistura("Basilio", 0);
        ctrlDip.addLegistura("Basilio", 1);
        ctrlEve.addTipoEvento("Meeting", 4);
        ctrlEve.addTipoEvento("Reunión", 3);
        ctrlEve.addTipoEvento("Manifestación", 2);
        ctrlEve.addTipoEvento("Cafelito en el bar", 5);
        ctrlEve.addTipoEvento("Cumpleaños", 1);
        Set<String> diputados = new TreeSet<>();
        diputados.add("Carlos");
        diputados.add("Pedro");
        diputados.add("Silvia");
        diputados.add("María");
        diputados.add("Jose");
        Set<String> diputados2 = new TreeSet<>();
        diputados2.add("Luís");
        diputados2.add("Miguel");
        diputados2.add("Juan");
        diputados2.add("Sara");
        diputados2.add("Antonio");
        Set<String> diputados3 = new TreeSet<>();
        diputados3.add("Sebastián");
        diputados3.add("Anita");
        diputados3.add("Lucía");
        diputados3.add("Federico");
        diputados3.add("Manolo");
        Set<String> diputados4 = new TreeSet<>();
        diputados4.add("Víctor");
        diputados4.add("Sandra");
        diputados4.add("Alberto");
        diputados4.add("Susana");
        diputados4.add("Basilio");
        ctrlEve.addEvento("Meeting", "meet_1", Date.parseDate("14/01/1990"), diputados);
        ctrlEve.addEvento("Meeting", "meet_2", Date.parseDate("14/01/1990"), diputados2);
        ctrlEve.addEvento("Meeting", "meet_3", Date.parseDate("14/01/1990"), diputados3);
        ctrlEve.addEvento("Meeting", "meet_4", Date.parseDate("14/01/1990"), diputados4);
        Map<String, TipoVoto> votaciones = new HashMap<>();
        //Carlos, Pedro, Silvia, María, Jose, Luís, Miguel, Juan, Sara, Antonio, Sebastián, Anita, Lucía, Federico, Manolo, Víctor, Sandra, Alberto, Susana, Basilio
        votaciones.put("Carlos", TipoVoto.A_FAVOR);
        votaciones.put("Pedro", TipoVoto.EN_CONTRA);
        votaciones.put("Silvia", TipoVoto.ABSTENCION);
        votaciones.put("María", TipoVoto.A_FAVOR);
        votaciones.put("Jose", TipoVoto.AUSENCIA);
        votaciones.put("Luís", TipoVoto.A_FAVOR);
        votaciones.put("Miguel", TipoVoto.A_FAVOR);
        votaciones.put("Juan", TipoVoto.EN_CONTRA);
        votaciones.put("Sara", TipoVoto.EN_CONTRA);
        votaciones.put("Antonio", TipoVoto.ABSTENCION);
        votaciones.put("Sebastián", TipoVoto.EN_CONTRA);
        votaciones.put("Anita", TipoVoto.A_FAVOR);
        votaciones.put("Lucía", TipoVoto.A_FAVOR);
        votaciones.put("Federico", TipoVoto.EN_CONTRA);
        votaciones.put("Manolo", TipoVoto.EN_CONTRA);
        votaciones.put("Víctor", TipoVoto.AUSENCIA);
        votaciones.put("Sandra", TipoVoto.A_FAVOR);
        votaciones.put("Alberto", TipoVoto.EN_CONTRA);
        votaciones.put("Susana", TipoVoto.A_FAVOR);
        votaciones.put("Basilio", TipoVoto.ABSTENCION);
        ctrlVot.addVotacion("votación_1", Date.parseDate("25/04/1990"), 3,  votaciones);
        votaciones = new HashMap<>();
        //Carlos, Pedro, Silvia, María, Jose, Luís, Miguel, Juan, Sara, Antonio, Sebastián, Anita, Lucía, Federico, Manolo, Víctor, Sandra, Alberto, Susana, Basilio
        votaciones.put("Carlos", TipoVoto.EN_CONTRA);
        votaciones.put("Pedro", TipoVoto.AUSENCIA);
        votaciones.put("Silvia", TipoVoto.A_FAVOR);
        votaciones.put("María", TipoVoto.AUSENCIA);
        votaciones.put("Jose", TipoVoto.ABSTENCION);
        votaciones.put("Luís", TipoVoto.EN_CONTRA);
        votaciones.put("Miguel", TipoVoto.EN_CONTRA);
        votaciones.put("Juan", TipoVoto.ABSTENCION);
        votaciones.put("Sara", TipoVoto.A_FAVOR);
        votaciones.put("Antonio", TipoVoto.A_FAVOR);
        votaciones.put("Sebastián", TipoVoto.A_FAVOR);
        votaciones.put("Anita", TipoVoto.EN_CONTRA);
        votaciones.put("Lucía", TipoVoto.EN_CONTRA);
        votaciones.put("Federico", TipoVoto.ABSTENCION);
        votaciones.put("Manolo", TipoVoto.AUSENCIA);
        votaciones.put("Víctor", TipoVoto.A_FAVOR);
        votaciones.put("Sandra", TipoVoto.EN_CONTRA);
        votaciones.put("Alberto", TipoVoto.A_FAVOR);
        votaciones.put("Susana", TipoVoto.EN_CONTRA);
        votaciones.put("Basilio", TipoVoto.ABSTENCION);
        ctrlVot.addVotacion("votación_2", Date.parseDate("25/04/1990"), 5,  votaciones);
        ControladorVista aux=ControladorVista.getInstance();
        aux.iniciaVista();
        
    }
    
}
