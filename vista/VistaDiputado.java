/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

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
    public VistaDiputado() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void initComponents() {

        panelBotonesDiputado = new javax.swing.JPanel();
        botonNuevoDiputado = new javax.swing.JButton();
        botonBajaDiputado = new javax.swing.JButton();
        botonConsultarDiputados = new javax.swing.JButton();
        botonModificarDiputado = new javax.swing.JButton();
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

        javax.swing.GroupLayout panelBotonesDiputadoLayout = new javax.swing.GroupLayout(panelBotonesDiputado);
        panelBotonesDiputado.setLayout(panelBotonesDiputadoLayout);
        panelBotonesDiputadoLayout.setHorizontalGroup(
            panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesDiputadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonBajaDiputado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonNuevoDiputado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonModificarDiputado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonConsultarDiputados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBotonesDiputadoLayout.setVerticalGroup(
            panelBotonesDiputadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesDiputadoLayout.createSequentialGroup()
                .addGap(100, 100, 100)
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
                "Nombre", "Partido político", "Estado", "F. nacimiento", "Legislatura"
            }
        ));
        panelScrollDiputados.setViewportView(tablaDiputados);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/logos/Diputados.png"))); // NOI18N

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
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonesDiputado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelScrollDiputados, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoDiputadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonNuevoDiputadoActionPerformed

    private void botonBajaDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBajaDiputadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBajaDiputadoActionPerformed

    private void botonConsultarDiputadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarDiputadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonConsultarDiputadosActionPerformed

    private void botonModificarDiputadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarDiputadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonModificarDiputadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBajaDiputado;
    private javax.swing.JButton botonConsultarDiputados;
    private javax.swing.JButton botonModificarDiputado;
    private javax.swing.JButton botonNuevoDiputado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelBotonesDiputado;
    private javax.swing.JScrollPane panelScrollDiputados;
    private javax.swing.JTable tablaDiputados;
    // End of variables declaration//GEN-END:variables
}
