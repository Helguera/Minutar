/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sociograph
 */
public class Tabla extends javax.swing.JPanel {

    /**
     * Creates new form Tabla
     */
    public Tabla() {
        initComponents();

        //Centrar los elementos de la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Inicio", "Fin", "Elemento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setColumnSelectionAllowed(true);
        tabla.setIntercellSpacing(new java.awt.Dimension(10, 3));
        tabla.setRowHeight(20);
        tabla.setRowMargin(3);
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMinWidth(25);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(25);
            tabla.getColumnModel().getColumn(0).setMaxWidth(25);
            tabla.getColumnModel().getColumn(0).setCellRenderer(null);
            tabla.getColumnModel().getColumn(1).setMinWidth(70);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(1).setMaxWidth(70);
            tabla.getColumnModel().getColumn(2).setMinWidth(70);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout());

        jButton1.setText("Añadir línea");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Eliminar línea");
        jPanel2.add(jButton2);

        jButton3.setText("Exportar CSV");
        jPanel2.add(jButton3);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    public void setRow(Object elementos) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addRow((Object[]) elementos);
    }

}
