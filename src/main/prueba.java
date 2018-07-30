/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
import uk.co.caprica.vlcj.test.basic.PlayerVideoAdjustPanel;

/**
 *
 * @author Sociograph
 */
public class prueba {
    public static void main (String args[]){
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

        final PlayerControlsPanel controlsPanel = new PlayerControlsPanel(embeddedMediaPlayer);
        PlayerVideoAdjustPanel videoAdjustPanel = new PlayerVideoAdjustPanel(embeddedMediaPlayer);
        botones botones = new botones();

//            mediaPlayerComponent.getMediaPlayer().playMedia(Constant.PATH_ROOT + Constant.PATH_MEDIA + "tmp.mp4");
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBackground(Color.black);
        mainFrame.add(videoSurface, BorderLayout.CENTER);
        mainFrame.add(controlsPanel, BorderLayout.SOUTH);
        mainFrame.add(botones, BorderLayout.EAST);
        //mainFrame.add(videoAdjustPanel, BorderLayout.EAST);

        //create a button which will hide the panel when clicked.
        mainFrame.pack();
        mainFrame.setVisible(true);

        embeddedMediaPlayer.playMedia("C:\\Users\\Sociograph\\Desktop\\23 julio_Gafa 14_Grupo 3\\MOVI0000.avi");
    }
}
