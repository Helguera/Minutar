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

/**
 *
 * @author Sociograph
 */
public class TablaSecEsc extends javax.swing.JPanel {

    private Controlador controlador;
    private JTable elementos;
    private JTable zonas;

    /**
     * Creates new form Tabla
     */
    public TablaSecEsc(Controlador controlador) {
        initComponents();

        this.controlador = controlador;

        //Centrar los elementos de la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        centerRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        centerRenderer = new DefaultTableCellRenderer();
        

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
        addLinea = new javax.swing.JButton();
        removeLinea = new javax.swing.JButton();
        marcaIncial = new javax.swing.JButton();
        exportCSV = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Inicio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
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
            tabla.getColumnModel().getColumn(1).setMinWidth(70);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        addLinea.setText("Añadir línea");
        addLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLineaActionPerformed(evt);
            }
        });
        jPanel2.add(addLinea);

        removeLinea.setText("Eliminar línea");
        removeLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLineaActionPerformed(evt);
            }
        });
        jPanel2.add(removeLinea);

        marcaIncial.setText("Marca Inicial");
        jPanel2.add(marcaIncial);

        exportCSV.setText("Exportar CSV");
        exportCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCSVActionPerformed(evt);
            }
        });
        jPanel2.add(exportCSV);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void addLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLineaActionPerformed
        // TODO add your handling code here:
        int seleccionado = tabla.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        if (controlador.getTablaActiva() == 0) {
            model.insertRow(seleccionado + 1, new Object[]{(controlador.getNumLineaElementos()), null, null, null});
            controlador.incrementaNumLineaElementos();
        } else {
            model.insertRow(seleccionado + 1, new Object[]{(controlador.getNumLineaElementos()), null, null, null});
            controlador.incrementaNumLineaZonas();
        }
        reordenaIndices();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JScrollBar sb = jScrollPane1.getVerticalScrollBar();
                sb.setValue(sb.getMaximum());
                jScrollPane1.repaint();
                jScrollPane1.revalidate();
            }
        });
    }//GEN-LAST:event_addLineaActionPerformed

    private void removeLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLineaActionPerformed
        // TODO add your handling code here:
        int seleccionado = tabla.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        if (controlador.getTablaActiva() == 0) {
            model.removeRow(seleccionado);
            controlador.decrementaNumLineaElementos();
        } else {
            model.removeRow(seleccionado);
            controlador.decrementaNumLineaZonas();
        }

        reordenaIndices();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JScrollBar sb = jScrollPane1.getVerticalScrollBar();
                sb.setValue(sb.getMaximum());
                jScrollPane1.repaint();
                jScrollPane1.revalidate();
            }
        });
    }//GEN-LAST:event_removeLineaActionPerformed

    private void exportCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCSVActionPerformed
        // Exportar a CSV

        try {
            String direccion = "C:\\Users\\Sociograph\\Desktop\\" + controlador.getVideoName() + ".csv";
            PrintWriter writer;
            writer = new PrintWriter(direccion, "UTF-8");

            DefaultTableModel model_elementos = (DefaultTableModel) elementos.getModel();
            DefaultTableModel model_zonas = (DefaultTableModel) zonas.getModel();

            int longitud;
            if (model_elementos.getRowCount() > model_zonas.getRowCount()) {
                longitud = model_elementos.getRowCount();
            } else {
                longitud = model_zonas.getRowCount();
            }

            writer.println("Nº,Inicio,Fin,Elemento,,,Nº,Inicio,Fin,Zona");

            for (int i = 0; i < longitud; i++) {
                String linea;
                
                //if ()

                //writer.println(model_elementos.getValueAt(i, 0) + "," + model_elementos.getValueAt(i, 1) + "," + model_elementos.getValueAt(i, 2) + "," + model_elementos.getValueAt(i, 3) + ",,," + model_zonas.getValueAt(i, 0) + "," + model_zonas.getValueAt(i, 1) + "," + model_zonas.getValueAt(i, 2) + "," + model_zonas.getValueAt(i, 3));

                /*if (model_elementos.getRowCount() < longitud) {
                    writer.println(model_elementos.getValueAt(i, 0) + "," + model_elementos.getValueAt(i, 1) + "," + model_elementos.getValueAt(i, 2) + "," + model_elementos.getValueAt(i, 3) + ",,," + model_zonas.getValueAt(i, 0) + "," + model_zonas.getValueAt(i, 1) + "," + model_zonas.getValueAt(i, 2) + "," + model_zonas.getValueAt(i, 3));
                } else {
                    if (model_zonas.getRowCount() > longitud) {
                        writer.println(",,,,,,," + model_zonas.getValueAt(i, 0) + "," + model_zonas.getValueAt(i, 1) + "," + model_zonas.getValueAt(i, 2) + "," + model_zonas.getValueAt(i, 3));
                    } else {

                    }
                }*/
            }

            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TablaSecEsc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TablaSecEsc.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_exportCSVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLinea;
    private javax.swing.JButton exportCSV;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton marcaIncial;
    private javax.swing.JButton removeLinea;
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
        model.setValueAt(model.getValueAt(linea - 1, 1), linea - 1, 1);
        model.setValueAt(controlador.getTime(), linea - 1, 2);
        model.setValueAt(model.getValueAt(linea - 1, 3), linea - 1, 3);

    }

    public JTable getTabla() {
        return tabla;
    }

}