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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;
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
    private Tabla tabla;
    private botones botones;
    private Controlador controlador;

    public Controlador() {
        
        //Establece los botones al estilo de Windows
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("UIManager Exception : " + e);
        }

        boolean found = new NativeDiscovery().discover();
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();

        Canvas videoSurface = new Canvas();
        videoSurface.setBackground(Color.black);
        videoSurface.setSize(800, 600);

        List<String> vlcArgs = new ArrayList<String>();

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
        tabla = new Tabla();
        mainFrame = new JFrame();

        //Colocar las ventanas en el frame original con el border layout
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(Color.black);
        mainFrame.add(videoSurface, BorderLayout.CENTER);
        mainFrame.add(controles, BorderLayout.SOUTH);
        mainFrame.add(botones, BorderLayout.EAST);
        mainFrame.add(tabla, BorderLayout.WEST);

        tabla.setRow(new Object[]{"1", "0:00:00", "0:01:56", "Etiqueta info"});
        tabla.setRow(new Object[]{"2", "0:00:00", "0:01:57", "Etiqueta info"});
        tabla.setRow(new Object[]{"3", "0:00:00", "0:01:58", "Etiqueta info"});
        tabla.setRow(new Object[]{"4", "0:00:00", "0:01:59", "Etiqueta info"});

        System.out.println(controles.getTime());

        mainFrame.pack();
        mainFrame.setVisible(true);

        //embeddedMediaPlayer.playMedia("C:\\Users\\Sociograph\\Desktop\\23 julio_Gafa 14_Grupo 3\\MOVI0000.avi");
        embeddedMediaPlayer.playMedia("C:\\Users\\Sociograph\\Downloads\\logo-nuevo-sociograph-min-1024x238.png");

    }

    public Controlador(Controles controles) {
        this.controles = controles;
    }

    public long getTime() {
        return controles.getTime();
    }
    
    public void addRow(Object object){
        tabla.setRow(object);
    }

}
