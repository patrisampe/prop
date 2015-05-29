/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ControladorDominioDiputado;
import controladores.ControladorDominioLegislatura;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import time.Date;
import vista.formularios.FormConsultarDiputado;
import vista.formularios.FormModificarDiputado;
import vista.formularios.FormNuevoDiputado;

/**
 *
 * @author tabletom
 */
public class VistaDiputado extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form VistaDiputado
     */
    private DefaultTableModel model = new DefaultTableModel();
    private final String titulos[] = {"Nombre", "Partido político", "Estado", "F. nacimiento"};
    private final ControladorDominioLegislatura ctrlLeg;
    private final ControladorDominioDiputado ctrlDip;

    public VistaDiputado() {
        initComponents();
        ctrlLeg = ControladorDominioLegislatura.getInstance();
        ctrlDip = ControladorDominioDiputado.getInstance();
        jComboBox1.addItem("Todas");
    }
    
    public void seleccionaLegislatura(Integer id) {
        jComboBox1.setSelectedItem(ctrlLeg.getFechaInicio(id) + "-" + ctrlLeg.getFechaFinal(id));
    }

    public void rellenaLegislaturas() {
        Set<Integer> idsLeg = ctrlLeg.getIDs();
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Todas");
        for (Integer id:idsLeg)
            jComboBox1.addItem(ctrlLeg.getFechaInicio(id) + "-" + ctrlLeg.getFechaFinal(id));
    }
    
    public void rellenaDiputados() {
        if (jComboBox1.getSelectedIndex() >= 0) {
            model = new DefaultTableModel(null, titulos);
            tablaDiputados.setModel(model);
            Set<String> nombres;
            if (jComboBox1.getSelectedItem().equals("Todas")) {
                nombres = ctrlDip.getNombres();
            }
            else {
                Integer idLeg = ctrlLeg.getID(Date.parseDate(jComboBox1.getSelectedItem().toString().substring(0, 10)));
                nombres = ctrlLeg.getDiputados(idLeg);
            }
            for (String nombre: nombres) {
                String[] fila = {nombre, ctrlDip.getPartidoPolitico(nombre), ctrlDip.getEstado(nombre), ctrlDip.getFechaDeNacimiento(nombre).toString()};
                model.addRow(fila);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonesDiputado = new javax.swing.JPanel();
        botonNuevoDiputado = new javax.swing.JButton();
        botonBajaDiputado = new javax.swing.JButton();
        botonConsultarDiputados = new javax.swing.JButton();
        botonModificarDiputado = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        panelScrollDiputados = new javax.swing.JScrollPane();
        tablaDiputados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        panelBotonesDiputado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        botonNuevoDiputado.setText("Nuevo Diputado");
        botonNuevoDiputado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoDiputadoActionPerformed(evt);
            }
        });

        botonBajaDiputado.setText("Baja Diputado");
        botonBajaDiputado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBajaDiputadoActionPerformed(evt);
            }
        });

        botonConsultarDiputados.setText("Consultar Diputados");
        botonConsultarDiputados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarDiputadosActionPerformed(evt);
            }
        });

        botonModificarDiputado.setText("Modificar Diputado");
        botonModificarDiputado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarDiputadoActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Legislatura:");

        javax.swing.GroupLayout panelBotonesDiputadoLayout = new javax.swing.GroupLayout(panelBotonesDiputado);
        panelBotonesDiputado.setLayout(panelBotonesDiputadoLayout);
        panelBotonesDiputadoLayout.setHorizontalGroup(
            panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesDiputadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonBajaDiputado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonNuevoDiputado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonModificarDiputado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonConsultarDiputados, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBotonesDiputadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBotonesDiputadoLayout.setVerticalGroup(
            panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesDiputadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(botonNuevoDiputado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(botonBajaDiputado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(botonConsultarDiputados, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(botonModificarDiputado, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        tablaDiputados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Partido político", "Estado", "F. nacimiento"
            }
        ));
        panelScrollDiputados.setViewportView(tablaDiputados);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel1.setText("Gestión de Diputados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotonesDiputado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelScrollDiputados, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(95, 95, 95))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonesDiputado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(panelScrollDiputados, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoDiputadoActionPerformed
        FormNuevoDiputado form = new FormNuevoDiputado(this);
        form.setVisible(true);
    }//GEN-LAST:event_botonNuevoDiputadoActionPerformed

    private void botonBajaDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBajaDiputadoActionPerformed
        Integer fila = tablaDiputados.getSelectedRow();
        if (fila >= 0) {
            ctrlDip.removeDiputado(model.getValueAt(fila, 0).toString());
            rellenaDiputados();
        }
    }//GEN-LAST:event_botonBajaDiputadoActionPerformed

    private void botonConsultarDiputadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarDiputadosActionPerformed
        Integer fila = tablaDiputados.getSelectedRow();
        if (fila >= 0) {
            FormConsultarDiputado form = new FormConsultarDiputado(this, model.getValueAt(fila, 0).toString());
            form.setVisible(true);
        }
    }//GEN-LAST:event_botonConsultarDiputadosActionPerformed

    private void botonModificarDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarDiputadoActionPerformed
        Integer fila = tablaDiputados.getSelectedRow();
        if (fila >= 0) {
            FormModificarDiputado form = new FormModificarDiputado(this, model.getValueAt(fila, 0).toString());
            form.setVisible(true);
        }
    }//GEN-LAST:event_botonModificarDiputadoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        rellenaDiputados();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBajaDiputado;
    private javax.swing.JButton botonConsultarDiputados;
    private javax.swing.JButton botonModificarDiputado;
    private javax.swing.JButton botonNuevoDiputado;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelBotonesDiputado;
    private javax.swing.JScrollPane panelScrollDiputados;
    private javax.swing.JTable tablaDiputados;
    // End of variables declaration//GEN-END:variables
}
