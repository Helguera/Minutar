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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;
import uk.co.caprica.vlcj.test.basic.PlayerVideoAdjustPanel;

/**
 *
 * @author Sociograph
 */
public class prueba implements Runnable {

    private JFrame mainFrame;

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("UIManager Exception : " + e);
        }
        SwingUtilities.invokeLater(new prueba());

    }

    public void run() {
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

        final PlayerControlsPanel controlsPanel = new PlayerControlsPanel(embeddedMediaPlayer);
        Controles controles = new Controles(embeddedMediaPlayer);
        botones botones = new botones();
        Tabla tabla = new Tabla();
        JSplitPane split = new JSplitPane();

        mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(Color.black);
        mainFrame.add(videoSurface, BorderLayout.CENTER);
        mainFrame.add(controles, BorderLayout.SOUTH);
        mainFrame.add(botones, BorderLayout.EAST);
        mainFrame.add(tabla, BorderLayout.WEST);

        //mainFrame.add(videoAdjustPanel, BorderLayout.EAST);

        DefaultTableModel model = (DefaultTableModel) tabla.getM;
        //model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
        
        
        
        mainFrame.pack();
        mainFrame.setVisible(true);

        //embeddedMediaPlayer.playMedia("C:\\Users\\Sociograph\\Desktop\\23 julio_Gafa 14_Grupo 3\\MOVI0000.avi");
    }

}
