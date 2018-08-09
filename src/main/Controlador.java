/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Clases.Controles;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static javafx.util.Duration.millis;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;

/**
 *
 * @author Sociograph
 */
public class Controlador {

    private Controles controles;
    private JFrame mainFrame;

    private TablaElemZon tabla_elementos;
    private TablaElemZon tabla_zonas;
    private TablaPerTram tabla_personajes;
    private TablaPerTram tabla_tramas;
    private TablaSecEsc tabla_secuencias;
    private TablaSecEsc tabla_escenas;

    private botones botones;
    private JSplitPane split;
    private ArrayList<String> elementos;
    private ArrayList<String> zonas;

    private int tabla_activa = 0;   // 0 si esta la de elementos, 1 si esta la de zonas

    private HashMap lineaBoton = new HashMap();

    private int numLineaElementos = 1;
    private int numLineaZonas = 1;

    public int getNumLineaElementos() {
        return numLineaElementos;
    }

    public void setNumLinea(int numLineaElementos) {
        this.numLineaElementos = numLineaElementos;
    }

    public int getNumLineaZonas() {
        return numLineaZonas;
    }

    public void setNumLineaZonas(int numLineaZonas) {
        this.numLineaZonas = numLineaZonas;
    }

    public HashMap getLineaBoton() {
        return lineaBoton;
    }

    public void setLineaBoton(HashMap lineaBoton) {
        this.lineaBoton = lineaBoton;
    }

    public Controlador() {

        //Establece los botones al estilo de Windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("UIManager Exception : " + e);
        }

        elementos = new ArrayList<>();
        zonas = new ArrayList<>();

        leeElementos();
        //leeZonas();
        //leePersonajes();
        //leeTramas();
        //leeSecuencias();
        //leeEscenas();

        boolean found = new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();

        Canvas videoSurface = new Canvas();
        videoSurface.setBackground(Color.black);
        videoSurface.setSize(800, 600);

        List<String> vlcArgs = new ArrayList<>();

        vlcArgs.add("--no-plugins-cache");
        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--no-snapshot-preview");

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");
        embeddedMediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        embeddedMediaPlayer.setPlaySubItems(true);

        //Creacion de las ventanas
        final PlayerControlsPanel controlsPanel = new PlayerControlsPanel(embeddedMediaPlayer);

        controles = new Controles(embeddedMediaPlayer);
        botones = new botones(this);
        tabla_elementos = new TablaElemZon(this);
        tabla_zonas = new TablaElemZon(this);
        tabla_personajes = new TablaPerTram(this);
        tabla_tramas = new TablaPerTram(this);
        tabla_secuencias = new TablaSecEsc(this);
        tabla_escenas = new TablaSecEsc(this);

        mainFrame = new JFrame();
        split = new JSplitPane();
        split.setResizeWeight(0.9);
        split.setRightComponent(botones);
        split.setLeftComponent(new JPanel());

        //Colocar las ventanas en el frame original con el border layout
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(Color.black);
        mainFrame.add(videoSurface, BorderLayout.NORTH);
        mainFrame.add(controles, BorderLayout.SOUTH);
        mainFrame.add(split, BorderLayout.CENTER);
        // mainFrame.add(botones, BorderLayout.EAST);
        //mainFrame.add(tabla, BorderLayout.WEST);

        //tabla.setRow(new Object[]{"1", "0:00:00", "0:01:56", "Etiqueta info"});
        mainFrame.pack();
        mainFrame.setVisible(true);

        //embeddedMediaPlayer.playMedia("C:\\Users\\Sociograph\\Desktop\\23 julio_Gafa 14_Grupo 3\\MOVI0000.avi");
        embeddedMediaPlayer.playMedia("images/logo.png");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

    }

    public Controlador(Controles controles) {
        this.controles = controles;
    }

    public String getTime() {
        long tiempo = controles.getTime();
        String format = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(tiempo),
                TimeUnit.MILLISECONDS.toMinutes(tiempo)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(tiempo)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(tiempo)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tiempo)));
        return format;
    }

    public String getVideoName() {
        return controles.getVideoName();
    }

    public void addRowElementos(Object object) {
        tabla_elementos.setRow(object);
    }

    public void getRowsElementos(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_elementos.getRow(numFila, tiempo);
    }

    public void addRowZonas(Object object) {
        tabla_zonas.setRow(object);
    }

    public void getRowsZonas(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_elementos.getRow(numFila, tiempo);
    }

    public void incrementaNumLineaElementos() {
        this.numLineaElementos++;
    }

    public void decrementaNumLineaElementos() {
        this.numLineaElementos--;
    }

    public void decrementaNumLineaZonas() {
        this.numLineaZonas--;
    }

    public void incrementaNumLineaZonas() {
        this.numLineaZonas++;
    }

    private void leeElementos() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\elementos.txt";
        try {

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                elementos.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    private void leeZonas() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\zonas.txt";
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                zonas.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    public ArrayList<String> getElementos() {
        return elementos;
    }

    public ArrayList<String> getZonas() {
        return zonas;
    }

    public void cambiaTabla(int i) {
        try {
            switch (i) {
                case 0:
                    split.setLeftComponent(new JPanel());
                    break;
                case 1:
                    split.setLeftComponent(tabla_elementos);
                    tabla_activa = i;
                    break;
                case 2:
                    split.setLeftComponent(tabla_zonas);
                    tabla_activa = i;
                    break;
                case 3:
                    split.setLeftComponent(tabla_personajes);
                    tabla_activa = i;
                    break;
                case 4:
                    split.setLeftComponent(tabla_tramas);
                    tabla_activa = i;
                    break;
                case 5:
                    split.setLeftComponent(tabla_secuencias);
                    tabla_activa = i;
                    break;
                case 6:
                    split.setLeftComponent(tabla_escenas);
                    tabla_activa = i;
                    break;
            }
        } catch (Exception e) {

        }
    }

    public void muestraTablaElementos() {
        split.setLeftComponent(tabla_elementos);
        tabla_activa = 0;
    }

    public void muestraTablaZonas() {
        split.setLeftComponent(tabla_zonas);
        tabla_activa = 1;
    }

    public int getTablaActiva() {
        return tabla_activa;
    }

    public void setFinElemento(int linea) {
        tabla_elementos.setFinElemento(linea);
    }

    public void setFinZonas(int linea) {
        tabla_zonas.setFinElemento(linea);
    }

    public JTable getTablaElementos() {
        return tabla_elementos.getTabla();
    }

    public JTable getTablaZonas() {
        return tabla_zonas.getTabla();
    }

    private void leePersonajes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void leeTramas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void leeSecuencias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void leeEscenas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
