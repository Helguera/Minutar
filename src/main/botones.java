/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Component;
import static java.awt.Label.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Sociograph
 */
public class botones extends javax.swing.JPanel {
    
    private Controlador controlador;

    /**
     * Creates new form botones
     */
    public botones(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        int cont = 1;
        for (int i = 0; i < 60; i++) {

            switch (i % 3) {
                case 1:
                    JLabel etiqueta = new JLabel();
                    etiqueta.setText("Etiqueta " + cont);
                    etiqueta.setHorizontalAlignment(JLabel.CENTER);
                    etiqueta.setVisible(true);
                    etiqueta.setName("Etiqueta" + i);
                    jPanel4.add(etiqueta);
                    jPanel4.updateUI();
                    cont++;
                    break;
                case 0: {
                    JButton boton = new JButton();
                    boton.setText("Inicio");
                    boton.setVisible(true);
                    boton.setName("Boton" + i);
                    boton.addActionListener((ActionEvent e) -> {
                        Component comp[] = jPanel4.getComponents();
                        int pos = 0;
                        for (int j = 0; j < comp.length; j++) {
                            if (e.getSource().hashCode() == (comp[j].hashCode())) {
                                pos = j;
                            }
                        }
                        comp[pos + 2].setEnabled(true);
                        comp[pos].setEnabled(false);
                        controlador.addRow(new Object[]{"156", "0:00:00", "0:01:56", "prueba desde boton"});
                    });

                    jPanel4.add(boton);
                    jPanel4.updateUI();

                    break;
                }
                default: {
                    JButton boton = new JButton();
                    boton.setText("Fin");
                    boton.setVisible(true);
                    boton.setEnabled(false);
                    boton.setName("Boton" + i);
                    jPanel4.add(boton);
                    jPanel4.updateUI();
                    break;
                }
            }
        }
        jPanel4.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbed = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        scroll = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(0, 2));
        tabbed.addTab("Elementos", jPanel1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(153, 153, 153))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton1)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        tabbed.addTab("Zonas", jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(jTree1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabbed.addTab("Ficheros", jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(0, 3));
        scroll.setViewportView(jPanel4);

        tabbed.addTab("Scroll", scroll);

        add(tabbed, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTabbedPane tabbed;
    // End of variables declaration//GEN-END:variables

}
