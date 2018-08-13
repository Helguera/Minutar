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
import javax.swing.JOptionPane;
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
    private ArrayList<String> personajes;
    private ArrayList<String> tramas;
    private ArrayList<String> secuencias;
    private ArrayList<String> escenas;

    private int tabla_activa = 0;   // 0 si esta la de elementos, 1 si esta la de zonas

    private HashMap lineaBoton = new HashMap();

    private int numLineaElementos = 1;
    private int numLineaZonas = 1;
    private int numLineaPersonajes = 1;
    private int numLineaTramas = 1;
    private int numLineaSecuencias = 1;
    private int numLineaEscenas = 1;
    
   
    

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

    public int getNumLineaPersonajes() {
        return numLineaPersonajes;
    }

    public void setNumLineaPersonajes(int numLineaPersonajes) {
        this.numLineaPersonajes = numLineaPersonajes;
    }

    public int getNumLineaTramas() {
        return numLineaTramas;
    }

    public void setNumLineaTramas(int numLineaTramas) {
        this.numLineaTramas = numLineaTramas;
    }

    public int getNumLineaSecuencias() {
        return numLineaSecuencias;
    }

    public void setNumLineaSecuencias(int numLineaSecuencias) {
        this.numLineaSecuencias = numLineaSecuencias;
    }

    public int getNumLineaEscenas() {
        return numLineaEscenas;
    }

    public void setNumLineaEscenas(int numLineaEscenas) {
        this.numLineaEscenas = numLineaEscenas;
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
        personajes = new ArrayList<>();
        tramas = new ArrayList<>();
        secuencias = new ArrayList<>();
        escenas = new ArrayList<>();

        leeElementos();
        leeZonas();
        leePersonajes();
        leeTramas();
        leeSecuencias();
        leeEscenas();

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
        tabla_tramas.cambiaTextoColumna();
        tabla_secuencias = new TablaSecEsc(this);
        tabla_escenas = new TablaSecEsc(this);
        tabla_escenas.cambiaTextoColumna();

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
        String name = controles.getVideoName();
        name = name.substring(name.length() - 10);
        return name;
    }

    public void addRowElementos(Object object) {
        tabla_elementos.setRow(object);
    }

    public void getRowsElementos(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_elementos.getRow(numFila, tiempo);
    }

    public void addRowPersonajes(Object object) {
        tabla_personajes.setRow(object);
    }

    public void getRowsPersonajes(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_personajes.getRow(numFila, tiempo);
    }

    public void addRowTramas(Object object) {
        tabla_tramas.setRow(object);
    }

    public void getRowsTramas(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_tramas.getRow(numFila, tiempo);
    }

    public void addRowSecuencias(Object object) {
        tabla_secuencias.setRow(object);
    }

    public void getRowsSecuencias(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_secuencias.getRow(numFila, tiempo);
    }

    public void addRowEscenas(Object object) {
        tabla_escenas.setRow(object);
    }

    public void getRowsEscenas(int numFila, String tiempo) {
        numFila = numFila - 2;
        tabla_escenas.getRow(numFila, tiempo);
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

    public void incrementaNumLineaPersonajes() {
        this.numLineaPersonajes++;
    }

    public void decrementaNumLineaPersonajes() {
        this.numLineaPersonajes--;
    }

    public void incrementaNumLineaTramas() {
        this.numLineaTramas++;
    }

    public void decrementaNumLineaTramas() {
        this.numLineaTramas--;
    }

    public void incrementaNumLineaSecuencias() {
        this.numLineaSecuencias++;
    }

    public void decrementaNumLineaSecuencias() {
        this.numLineaSecuencias--;
    }

    public void incrementaNumLineaEscenas() {
        this.numLineaEscenas++;
    }

    public void decrementaNumLineaEscenas() {
        this.numLineaEscenas--;
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

    private void leePersonajes() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\personajes.txt";
        try {

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {

                personajes.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    private void leeTramas() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\tramas.txt";
        try {

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                tramas.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    private void leeSecuencias() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\secuencias.txt";
        try {

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                secuencias.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    private void leeEscenas() {
        String fichero = "C:\\Users\\Sociograph\\Desktop\\escenas.txt";
        try {

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                escenas.add(linea);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
        }
    }

    public ArrayList<String> getElementos() {
        return elementos;
    }

    public ArrayList<String> getPersonajes() {
        return personajes;
    }

    public ArrayList<String> getTramas() {
        return tramas;
    }

    public ArrayList<String> getSecuencias() {
        return secuencias;
    }

    public ArrayList<String> getEscenas() {
        return escenas;
    }

    public ArrayList<String> getZonas() {
        return zonas;
    }

    public void cambiaTabla(String i) {
        try {
            switch (i) {

                case "Elementos":
                    split.setLeftComponent(tabla_elementos);
                    // tabla_activa = i;
                    break;
                case "Zonas":
                    split.setLeftComponent(tabla_zonas);
                    //  tabla_activa = i;
                    break;
                case "Personajes":
                    split.setLeftComponent(tabla_personajes);
                    //tabla_activa = i;
                    break;
                case "Tramas":
                    split.setLeftComponent(tabla_tramas);
                    //tabla_activa = i;
                    break;
                case "Secuencias":
                    split.setLeftComponent(tabla_secuencias);
                    //tabla_activa = i;
                    break;
                case "Escenas":
                    split.setLeftComponent(tabla_escenas);
                    //tabla_activa = i;
                    break;
                default:
                    split.setLeftComponent(new JPanel());
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

    public void setFinSecuencias(int linea) {
        tabla_secuencias.setFinElemento(linea);
    }

    public void setFinSPersonajes(int linea) {
        tabla_personajes.setFinElemento(linea);
    }

    public void setFinTramas(int linea) {
        tabla_tramas.setFinElemento(linea);
    }

    public void setFinEscenas(int linea) {
        tabla_escenas.setFinElemento(linea);
    }

    public JTable getTablaElementos() {
        return tabla_elementos.getTabla();
    }

    public JTable getTablaZonas() {
        return tabla_zonas.getTabla();
    }

    public JTable getTablaPersonajes() {
        return tabla_personajes.getTabla();
    }

    public JTable getTablaTramas() {
        return tabla_tramas.getTabla();
    }

    public JTable getTablaSecuencias() {
        return tabla_secuencias.getTabla();
    }

    public JTable getTablaEscenas() {
        return tabla_escenas.getTabla();
    }

    public void showDialog(String texto) {
        JOptionPane.showMessageDialog(mainFrame, texto);
    }
    
    
}
