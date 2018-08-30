/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Sociograph
 */
public class TablaSecEsc extends javax.swing.JPanel {

    private Controlador controlador;
    private JTable elementos;
    private JTable zonas;
    private int marca_inicial;

    /**
     * Creates new form Tabla
     */
    public TablaSecEsc(Controlador controlador) {
        initComponents();

        this.controlador = controlador;

        //Centrar los elementos de la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        //tabla.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
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
        marcaIncial = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Secuencia", "Tiempo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
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
        jScrollPane1.setViewportView(tabla);
        tabla.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMinWidth(35);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(35);
            tabla.getColumnModel().getColumn(0).setMaxWidth(35);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setMinWidth(75);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(75);
            tabla.getColumnModel().getColumn(2).setMaxWidth(75);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        marcaIncial.setText("Marca Inicial");
        marcaIncial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaIncialActionPerformed(evt);
            }
        });
        jPanel2.add(marcaIncial);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void marcaIncialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaIncialActionPerformed
        // TODO add your handling code here:
        marca_inicial = toSeconds(controlador.getTime());
    }//GEN-LAST:event_marcaIncialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton marcaIncial;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    public void setRow(Object elementos) {

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addRow((Object[]) elementos);
        revalidate();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JScrollBar sb = jScrollPane1.getVerticalScrollBar();
                sb.setValue(sb.getMaximum());
                jScrollPane1.repaint();
                jScrollPane1.revalidate();
            }
        });

    }

    public void getRow(int numFila, String tiempo) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model = (DefaultTableModel) tabla.getModel();
        model.setValueAt(tiempo, numFila, 2);

    }

    public void reordenaIndices() {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
            model.setValueAt(model.getValueAt(i, 1), i, 1);
            model.setValueAt(model.getValueAt(i, 2), i, 2);
            model.setValueAt(model.getValueAt(i, 3), i, 3);
        }
    }

    public void setFinElemento(int linea) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        System.out.println("LA LINEA ES " + linea);
        model.setValueAt(linea, linea - 1, 0);
        model.setValueAt(controlador.getTime(), linea - 1, 2);
        //model.setValueAt(controlador.getTime(), linea - 1, 2);
        //model.setValueAt(model.getValueAt(linea - 1, 3), linea - 1, 3);

    }

    public JTable getTabla() {
        return tabla;
    }
    
    public void cambiaTextoColumna() {
        JTableHeader th = tabla.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setHeaderValue("Escena");
        th.repaint();
    }
    
    public void setMarcaInicial(int marca_inicial) {
        this.marca_inicial = marca_inicial;
    }
    
    public int getMarcaInicial() {
        return marca_inicial;
    }
    
    private int toSeconds(String time) {
        int segundos = 0;
        String[] tiempo = time.split(":");
        segundos+=Integer.parseInt(tiempo[2]);
        segundos+=Integer.parseInt(tiempo[1])*60;
        segundos+=Integer.parseInt(tiempo[0])*3600;
        marcaIncial.setText("Marca inicial en "+time);
        return segundos;
    }
}
