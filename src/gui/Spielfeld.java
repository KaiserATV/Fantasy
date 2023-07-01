package gui;

import javax.swing.*;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;



public class Spielfeld extends JFrame {
    private static final int GRID_SIZE = 1000;
    private static final int CELL_SIZE = 10;
    private static final int NUM_CELLS = GRID_SIZE / CELL_SIZE;
    private final int[][] grid = new int[NUM_CELLS][NUM_CELLS];
    private Random random = new Random();
    private ArrayList<Spieler> spielerListe = new ArrayList<>();
    private Spieler ausgewaehlterSpieler;
    private JLabel diceLabel;  
    private JLabel stepsLabel;


    public Spielfeld() {
        setTitle("Spielfeld");
        new Thread(new Music("The Shire.wav")).start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generatePaths();

        diceLabel = new JLabel(); // Initialisierung des diceLabel
        stepsLabel = new JLabel();

        spielerListe.add(new Spieler(0, 0, Color.RED));  // Einen Spieler hinzufügen
        ausgewaehlterSpieler = spielerListe.get(0);  // Den ersten Spieler auswählen
        ausgewaehlterSpieler.setSteps(rollDice()); // Würfeln beim Starten

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
                for (Spieler spieler : spielerListe) {
                    spieler.draw(g, CELL_SIZE);
                }
            }
        };
        panel.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
        panel.setMinimumSize(new Dimension(GRID_SIZE, GRID_SIZE)); // Setzen Sie die Mindestgröße des Panels


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;

                if (ausgewaehlterSpieler != null) {
                    ausgewaehlterSpieler.moveTo(x, y);
                    stepsLabel.setText("Verbleibende Schritte: " + ausgewaehlterSpieler.getSteps());
                    panel.repaint();

                    // Der Check und die Aktionen für das Ende der Runde sollten erst 
                    // nachdem der Spieler sich bewegt und die Änderungen dargestellt wurden, ausgeführt werden
                    if (ausgewaehlterSpieler.getSteps() == 0) {
                        ausgewaehlterSpieler.setSteps(rollDice()); // Würfeln wenn keine Schritte mehr übrig sind
                        JOptionPane.showMessageDialog(null, "Neue Runde!"); // Nachricht anzeigen
                    }
                }
            }
        });
    
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(diceLabel);
        infoPanel.add(stepsLabel);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        add(panel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        add(infoPanel, c);

        pack();
        setVisible(true);
    }


    private int rollDice() {
        int diceRoll = random.nextInt(6) + 1;
        ausgewaehlterSpieler.setSteps(diceRoll);
        diceLabel.setText("Gewürfelt: " + diceRoll);
        stepsLabel.setText("Verbleibende Schritte: " + ausgewaehlterSpieler.getSteps());
        return diceRoll;
    }


    private void drawGrid(Graphics g) {
        Random random = new Random();

        ImageIcon baumIcon = new ImageIcon("Baum.png");
        ImageIcon hausIcon = new ImageIcon("Haus.png");
        ImageIcon wegIcon = new ImageIcon("Weg.png");

        for (int i = 0; i < NUM_CELLS; i++) {
            for (int j = 0; j < NUM_CELLS; j++) {
                int x = i * CELL_SIZE;
                int y = j * CELL_SIZE;

                Image image;
                switch (grid[i][j]) {
                    case 0:
                        image = baumIcon.getImage();
                        break;
                    case 1:
                        image = hausIcon.getImage();
                        break;
                    default:
                        image = wegIcon.getImage();
                        break;
                }

                g.drawImage(image, x, y, CELL_SIZE, CELL_SIZE, null);
            }
        }
    }

    private static final int PATH_ITERATIONS = 10;
    private static final double PREFER_BEST_MOVE = 0.6;

    private void generatePaths() {
        Random random = new Random();

        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(0, 0));
        points.add(new Point(0, NUM_CELLS - 1));
        points.add(new Point(NUM_CELLS - 1, 0));
        points.add(new Point(NUM_CELLS - 1, NUM_CELLS - 1));

        Point middle = new Point(NUM_CELLS / 2, NUM_CELLS / 2);

        for (int iter = 0; iter < points.size(); iter++) {
            Point start = points.get(iter);
            Point current = new Point(start.x, start.y);
            while (!current.equals(middle)) {
                if (random.nextDouble() < 0.7) {  // 70% Chance sich Richtung Mitte zu bewegen
                    current = makeMove(current, middle);
                } else {  // 30% Chance random wegzuwandern
                    current = wander(current);
                }
                grid[current.x][current.y] = 2;  // Pfad generieren
                if (random.nextDouble() < 0.05) {  // 5% Chance, eine Abzweigung zu generieren
                    generateBranch(current, random.nextInt(4), random.nextInt(5) + 5);
                }
            }
        }

        generateGrid();
    }

    private Point wander(Point current) {
        ArrayList<Point> candidates = new ArrayList<>();
        Random random = new Random();

        if (current.x > 0) candidates.add(new Point(current.x - 1, current.y));
        if (current.x < NUM_CELLS - 1) candidates.add(new Point(current.x + 1, current.y));
        if (current.y > 0) candidates.add(new Point(current.x, current.y - 1));
        if (current.y < NUM_CELLS - 1) candidates.add(new Point(current.x, current.y + 1));

        return candidates.get(random.nextInt(candidates.size()));
    }

    private void generateGrid() {
        Random random = new Random();

        for (int i = 0; i < NUM_CELLS; i++) {
            for (int j = 0; j < NUM_CELLS; j++) {
                if (grid[i][j] != 2 && isAdjacentToPath(i, j) && random.nextDouble() < 0.1) { // Gebäude neben Pfad 10%
                    grid[i][j] = 1;  // Gebäude
                } else if (grid[i][j] != 2) {
                    grid[i][j] = 0;  // Baum
                }
            }
        }
    }


    private Point makeMove(Point current, Point target) {
        ArrayList<Point> candidates = new ArrayList<>();
        Random random = new Random();

        if (current.x > 0) candidates.add(new Point(current.x - 1, current.y));  // left
        if (current.x < NUM_CELLS - 1) candidates.add(new Point(current.x + 1, current.y));  // right
        if (current.y > 0) candidates.add(new Point(current.x, current.y - 1));  // up
        if (current.y < NUM_CELLS - 1) candidates.add(new Point(current.x, current.y + 1));  // down

        // Züge, die einen näher zum Ziel bringen, werden bevorzugt
        candidates.sort(Comparator.comparingDouble(p -> p.distance(target)));

        // Einen der 5 besten Wege, oder zufällig
        if (random.nextDouble() < PREFER_BEST_MOVE && candidates.size() >= 3) {
            java.util.List<Point> bestMoves = candidates.subList(0, 2);
            return bestMoves.get(random.nextInt(bestMoves.size()));
        } else {
            return candidates.get(random.nextInt(candidates.size()));
        }
    }

    private boolean isAdjacentToPath(int x, int y) {
        if (x > 0 && grid[x-1][y] == 2) return true;
        if (x < NUM_CELLS - 1 && grid[x+1][y] == 2) return true;
        if (y > 0 && grid[x][y-1] == 2) return true;
        if (y < NUM_CELLS - 1 && grid[x][y+1] == 2) return true;
        return false;
    }


    private void generateBranch(Point start, int direction, int length) {
        Point current = new Point(start);
        for (int i = 0; i < length; i++) {
            switch (direction) {
                case 0:  // up
                    current = new Point(current.x, current.y - 1);
                    break;
                case 1:  // down
                    current = new Point(current.x, current.y + 1);
                    break;
                case 2:  // left
                    current = new Point(current.x - 1, current.y);
                    break;
                default:  // right
                    current = new Point(current.x + 1, current.y);
                    break;
            }

            if (current.x >= 0 && current.x < NUM_CELLS && current.y >= 0 && current.y < NUM_CELLS) {
                grid[current.x][current.y] = 2;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Spielfeld::new);
    }
}


