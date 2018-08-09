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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import static java.util.Objects.hash;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Sociograph
 */
public class botones extends javax.swing.JPanel {

    private Controlador controlador;
    private ArrayList<String> elementos;
    private ArrayList<String> zonas;

    private String lastButton = null;

    /**
     * Creates new form botones
     *
     * @param controlador
     */
    @SuppressWarnings("empty-statement")
    public botones(Controlador controlador) {
        this.controlador = controlador;

        elementos = controlador.getElementos();
        zonas = controlador.getZonas();

        initComponents();

        //Crea boton y etiqueta por cada elemento leido del txt
        cargaElementos();

        //Crea un boton zona por cada zona leida del txt
        cargaZonas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        tabbed = new javax.swing.JTabbedPane();
        scroll_elementos = new javax.swing.JScrollPane();
        pnlElementos = new javax.swing.JPanel();
        scroll_zonas = new javax.swing.JScrollPane();
        pnlZonas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setLayout(new java.awt.BorderLayout());

        tabbed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedStateChanged(evt);
            }
        });

        pnlElementos.setLayout(new java.awt.GridLayout(0, 2));
        scroll_elementos.setViewportView(pnlElementos);

        tabbed.addTab("Elementos", scroll_elementos);

        pnlZonas.setLayout(new java.awt.GridLayout(0, 2));
        scroll_zonas.setViewportView(pnlZonas);

        tabbed.addTab("Zonas", scroll_zonas);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(jTree1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabbed.addTab("Ficheros", jPanel3);

        add(tabbed, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabbedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedStateChanged
        // TODO add your handling code here:
        try {
            switch (tabbed.getSelectedIndex()) {
                case 0:
                    controlador.muestraTablaElementos();
                    break;
                case 1:
                    controlador.muestraTablaZonas();
                    break;
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_tabbedStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel pnlElementos;
    private javax.swing.JPanel pnlZonas;
    private javax.swing.JScrollPane scroll_elementos;
    private javax.swing.JScrollPane scroll_zonas;
    private javax.swing.JTabbedPane tabbed;
    // End of variables declaration//GEN-END:variables

    private void cargaElementos() {
        for (int i = 0; i < elementos.size(); i++) {
            JToggleButton boton = new JToggleButton();
            boton.setText("Inicio");
            boton.setVisible(true);
            boton.setName("Boton" + i);
            boton.addActionListener((ActionEvent e) -> {
                Component comp[] = pnlElementos.getComponents();
                int pos = 0;
                for (int j = 0; j < comp.length; j++) {
                    if (e.getSource().hashCode() == (comp[j].hashCode())) {
                        pos = j;
                    }
                }

                // comp[pos].setEnabled(false);
                if (boton.isSelected()) {
                    /*TODO: Montar un mapa que contenga como clave el id, hashcode, nuemro o lo que sea del boton presionado
                         y como valor el número de línea asociado a ese botón, de tal forma que cuando se pulse se crea un nuevo par
                         y al despulsarse se elimina, teniendo siempre acceso a que línea hay que modificar en función del botón pulsado
                         Queda ver donde va mejor este mapa, en principio la idea es en controlador*/
                    boton.setText("Fin");
                    controlador.getLineaBoton().put(boton.getName(), controlador.getNumLineaElementos());
                    controlador.addRowElementos(new Object[]{controlador.getNumLineaElementos(), controlador.getTime(), "", comp[pos + 1].getName()});
                    controlador.incrementaNumLineaElementos();

                } else {
                    boton.setText("Inicio");

                    Iterator it = controlador.getLineaBoton().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (pair.getKey().equals(boton.getName())) {
                            controlador.setFinElemento((int) pair.getValue());
                            controlador.getLineaBoton().remove(pair.getKey());
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }

                }

            });

            pnlElementos.add(boton);
            pnlElementos.updateUI();

            JLabel etiqueta = new JLabel();
            etiqueta.setText(elementos.get(i));
            etiqueta.setHorizontalAlignment(JLabel.CENTER);
            etiqueta.setVisible(true);
            etiqueta.setName(elementos.get(i));
            pnlElementos.add(etiqueta);
            pnlElementos.updateUI();

        }
        pnlElementos.setVisible(true);
    }

    private void cargaZonas() {
        for (int i = 0; i < zonas.size(); i++) {
            JToggleButton boton = new JToggleButton();
            boton.setText(zonas.get(i));
            boton.setVisible(true);
            boton.setName(zonas.get(i));
            grupo.add(boton);
            boton.addActionListener((ActionEvent e) -> {
                Component comp[] = pnlZonas.getComponents();
                int pos = 0;
                for (int j = 0; j < comp.length; j++) {
                    if (e.getSource().hashCode() == (comp[j].hashCode())) {
                        pos = j;
                    }
                }

                // comp[pos].setEnabled(false);
                if (boton.isSelected()) {

                    /*TODO: Montar un mapa que contenga como clave el id, hashcode, nuemro o lo que sea del boton presionado
                         y como valor el número de línea asociado a ese botón, de tal forma que cuando se pulse se crea un nuevo par
                         y al despulsarse se elimina, teniendo siempre acceso a que línea hay que modificar en función del botón pulsado
                         Queda ver donde va mejor este mapa, en principio la idea es en controlador*/
                    controlador.getLineaBoton().put(boton.getName(), controlador.getNumLineaZonas());
                    controlador.addRowZonas(new Object[]{controlador.getNumLineaZonas(), controlador.getTime(), "", comp[pos + 0].getName()});
                    controlador.incrementaNumLineaZonas();

                    if (lastButton == null) {

                    } else {
                        controlador.setFinZonas(controlador.getNumLineaZonas()-2);
                    }

                    /*} else {
                        System.out.println("fafasf");
                        Iterator it = controlador.getLineaBoton().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            if (pair.getKey().equals(lastButton)) {
                                controlador.setFinZonas((int) pair.getValue());
                                controlador.getLineaBoton().remove(pair.getKey());
                                it.remove(); // avoids a ConcurrentModificationException
                            }
                        }

                        
                    }*/
                    lastButton = boton.getName();

                } else {
                    System.out.println("fafasf");
                    Iterator it = controlador.getLineaBoton().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (pair.getKey().equals(boton.getName())) {
                            controlador.setFinZonas((int) pair.getValue());
                            controlador.getLineaBoton().remove(pair.getKey());
                            it.remove(); // avoids a ConcurrentModificationException
                        }
                    }
                }
            });

            pnlZonas.add(boton);
            pnlZonas.updateUI();

        }
        pnlElementos.setVisible(true);

    }

}
