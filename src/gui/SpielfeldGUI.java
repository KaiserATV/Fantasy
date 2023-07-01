package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SpielfeldGUI extends JFrame {
    private int[][] grid; 
    private final SpielerMovement spielerMovement;
    private final Kartendesign kartendesign;
    private final JLabel diceLabel;
    private final JLabel stepsLabel;
    private final JScrollPane scrollPane;
    private final JPanel panel;

    public SpielfeldGUI(int[][] grid) { 
        this.grid = grid;

        setTitle("Spielfeld");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        diceLabel = new JLabel();
        stepsLabel = new JLabel();

        Spieler spieler1 = new Spieler(0, 0, Color.RED, 1); // Spieler 1  (0, 0)
        Spieler spieler2 = new Spieler(SpielfeldKonstanten.NUM_CELLS - 1, 0, Color.BLUE, 2); // Spieler 2 
        Spieler spieler3 = new Spieler(0, SpielfeldKonstanten.NUM_CELLS - 1, Color.GREEN, 3); // Spieler 3 
        Spieler spieler4 = new Spieler(SpielfeldKonstanten.NUM_CELLS - 1, SpielfeldKonstanten.NUM_CELLS - 1, Color.YELLOW, 4); // Spieler 4 

        Spieler[] spielerArray = {spieler1, spieler2, spieler3, spieler4};
        String[] spielerNamen = {"Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4"};

        spielerMovement = new SpielerMovement(spielerArray, this, diceLabel);
        spielerMovement.ausgewaehlterSpieler = spielerArray[0];
        int firstRoll = spielerMovement.rollDice();
        spielerMovement.ausgewaehlterSpieler.setSteps(firstRoll);
        stepsLabel.setText("Verbleibende Schritte: " + spielerMovement.ausgewaehlterSpieler.getSteps());
        kartendesign = new Kartendesign(grid, this);
        kartendesign.generatePaths();

        JPanel infoPanel = new JPanel(new GridLayout(5, 2));
        infoPanel.add(diceLabel);
        infoPanel.add(stepsLabel);

        for (int i = 0; i < spielerArray.length; i++) {
            Spieler spieler = spielerArray[i];
            String spielerName = spielerNamen[i];
            JPanel playerColorPanel = new JPanel();
            playerColorPanel.setBackground(spieler.getColor());

            JLabel playerNameLabel = new JLabel(spielerName);
            infoPanel.add(playerColorPanel);
            infoPanel.add(playerNameLabel);
        }

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                kartendesign.drawGrid(g, SpielfeldKonstanten.CELL_SIZE);
                for (Spieler spieler : spielerArray) {
                    spieler.draw(g, SpielfeldKonstanten.CELL_SIZE);
                }
            }
        };

        int NUM_CELLS = SpielfeldKonstanten.NUM_CELLS;
        int CELL_SIZE = SpielfeldKonstanten.CELL_SIZE;

        panel.setPreferredSize(new Dimension(NUM_CELLS * CELL_SIZE, NUM_CELLS * CELL_SIZE));
        panel.setMinimumSize(new Dimension(NUM_CELLS * CELL_SIZE, NUM_CELLS * CELL_SIZE));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                spielerMovement.handleKeyEvent(e, panel, stepsLabel);
                scrollToActivePlayer(); 
            }
        });

        scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
    }
    
    public int getNUM_CELLS() {
        return SpielfeldKonstanten.NUM_CELLS;
    }

    private void scrollToActivePlayer() {
        Spieler activePlayer = spielerMovement.ausgewaehlterSpieler;
        Rectangle playerBounds = activePlayer.getBounds(SpielfeldKonstanten.CELL_SIZE);
        panel.scrollRectToVisible(playerBounds);
    }

    
}

