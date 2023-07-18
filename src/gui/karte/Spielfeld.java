package karte;
import spieler;
import ui;
import uilogik;

import java.awt.Color;
import javax.swing.SwingUtilities;

public class Spielfeld {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int[][] grid = new int[SpielfeldKonstanten.NUM_CELLS][SpielfeldKonstanten.NUM_CELLS]; // Updated grid variable for multiple players
            new SpielfeldGUI(grid);

            // Start playing music
            Thread musicThread = new Thread(new Music("Fantasy/src/SacredGarden.wav"));
            musicThread.start();
        });
    }
}