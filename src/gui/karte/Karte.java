package karte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import javax.swing.*;
import spieler.Spieler;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class Karte {

    public enum FeldTyp {
        WEG, BAUM, GEBÄUDE
    }

    private FeldTyp[][] karte;
    private JFrame frame;
    private Image wegBild;
    private Image baumBild;
    private Image gebaeudeBild;
    private Spieler spieler;

    public Karte() {
        karte = new FeldTyp[70][70];
        spieler = new Spieler();
        spieler.setPosition(new Point(0, 0)); // Spieler startet bei (0,0)
        initKarte();
        
        wegBild = new ImageIcon("Fantasy/src/Bilder/Weg.png").getImage();
        baumBild = new ImageIcon("Fantasy/src/Bilder/Laub.png").getImage();
        gebaeudeBild = new ImageIcon("Fantasy/src/Bilder/Haus.png").getImage();
    
        frame = new JFrame("Karte");
frame.setLayout(new GridBagLayout());

GridBagConstraints gbc = new GridBagConstraints();
gbc.gridx = 0;
gbc.gridy = 0;
gbc.weightx = 1.0;
gbc.weighty = 1.0;
gbc.fill = GridBagConstraints.BOTH;

KartenPanel kartenPanel = new KartenPanel();
JScrollPane scrollPane = new JScrollPane(kartenPanel);

scrollPane.setPreferredSize(new Dimension(karte.length * 25, karte[0].length * 25));
frame.add(scrollPane, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        frame.setVisible(true);
    }
    
    
    public Spieler getSpieler() {
        return spieler;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getNUM_CELLS() {
        return karte.length;
    }
    
    private void initKarte() {
        for (int i = 0; i < karte.length; i++) {
            Arrays.fill(karte[i], FeldTyp.BAUM);
        }

        Random rand = new Random();
        int gebaeudeAnzahl = (karte.length * karte[0].length) / 30;
        List<Point> gebaeudeListe = new ArrayList<>();

        for (int i = 0; i < gebaeudeAnzahl; i++) {
            int x = rand.nextInt(karte.length);
            int y = rand.nextInt(karte[0].length);
            if (karte[x][y] != FeldTyp.GEBÄUDE) {
                karte[x][y] = FeldTyp.GEBÄUDE;
                gebaeudeListe.add(new Point(x, y));
            } else {
                i--;
            }
        }

        Point[] ecken = { 
            new Point(0, 0), new Point(0, karte[0].length - 1), 
            new Point(karte.length - 1, 0), new Point(karte.length - 1, karte[0].length - 1) 
        };
        
        for (Point ecke : ecken) {
            List<Point> kopieGebaeudeListe = new ArrayList<>(gebaeudeListe);  // Kopiere die Gebäudeliste für jede Ecke
            verbindeMitNaechstenGebaeuden(ecke, kopieGebaeudeListe, 3);       // Erhöhe die Anzahl der Verbindungen ein wenig
        }
    }
    
    private void verbindeMitNaechstenGebaeuden(Point start, List<Point> gebaeudeListe, int verbleibendeVerbindungen) {
        if (verbleibendeVerbindungen <= 0 || gebaeudeListe.isEmpty()) {
            return;
        }
    
        Point naechstesGebaeude = findeNaechstesGebaeude(start, gebaeudeListe);
        if (naechstesGebaeude == null) {
            return;
        }
    
        erstelleWeg(start, naechstesGebaeude);
        gebaeudeListe.remove(naechstesGebaeude);
    
        List<Point> naheGebaeude = findeNaheGebaeude(naechstesGebaeude, gebaeudeListe, verbleibendeVerbindungen);
        for (Point ziel : naheGebaeude) {
            if (!ziel.equals(naechstesGebaeude)) {  // Vermeide, das gleiche Gebäude erneut zu verbinden
                erstelleWeg(naechstesGebaeude, ziel);
                gebaeudeListe.remove(ziel);
            }
        }
    
        for (Point ziel : naheGebaeude) {
            verbindeMitNaechstenGebaeuden(ziel, gebaeudeListe, verbleibendeVerbindungen - 1);
        }
    }
    

    private Point findeNaechstesGebaeude(Point start, List<Point> gebaeudeListe) {
        Point naechstesGebaeude = null;
        double minimaleDistanz = Double.MAX_VALUE;
    
        for (Point gebaeude : gebaeudeListe) {
            double distanz = start.distance(gebaeude);
            if (distanz < minimaleDistanz) {
                minimaleDistanz = distanz;
                naechstesGebaeude = gebaeude;
            }
        }

        return naechstesGebaeude;
    }

    private List<Point> findeNaheGebaeude(Point start, List<Point> gebaeudeListe, int maxAnzahl) {
        List<Point> naheGebaeude = new ArrayList<>();
        List<Double> distanzen = new ArrayList<>();

        for (Point gebaeude : gebaeudeListe) {
            double distanz = start.distance(gebaeude);
            distanzen.add(distanz);
        }

        for (int i = 0; i < maxAnzahl && i < gebaeudeListe.size(); i++) {
            int minIndex = distanzen.indexOf(Collections.min(distanzen));
            if (minIndex != -1) {
                naheGebaeude.add(gebaeudeListe.get(minIndex));
                distanzen.set(minIndex, Double.MAX_VALUE);
            }
        }

        return naheGebaeude;
    }

    private void erstelleWeg(Point start, Point ziel) {
        int x = start.x;
        int y = start.y;
    
        while (x != ziel.x || y != ziel.y) {
            if (x < ziel.x) {
                x++;
            } else if (x > ziel.x) {
                x--;
            }
    
            if (y < ziel.y) {
                y++;
            } else if (y > ziel.y) {
                y--;
            }
    
            if (karte[x][y] != FeldTyp.GEBÄUDE) {
                karte[x][y] = FeldTyp.WEG;
            }
        }
    }
    

    class KartenPanel extends JPanel {
        public KartenPanel() {
            int padding =100; // Ändern Sie den Wert entsprechend Ihrem Wunschabstand
            this.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int feldBreite = 25;
            int feldHöhe = 25;

            for (int i = 0; i < karte.length; i++) {
                for (int j = 0; j < karte[0].length; j++) {
                    Image aktuellesBild = null;
                    switch (karte[i][j]) {
                        case WEG:
                            aktuellesBild = wegBild;
                            break;
                        case BAUM:
                            aktuellesBild = baumBild;
                            break;
                        case GEBÄUDE:
                            aktuellesBild = gebaeudeBild;
                            break;
                    }
                    g.drawImage(aktuellesBild, i * feldBreite, j * feldHöhe, feldBreite, feldHöhe, this);
                }
            }

            // Spieler darstellen
            g.setColor(Color.RED); // Spielerfarbe
            g.fillRect(spieler.getPosition().x * 20, spieler.getPosition().y * 20, 20, 20);
        }
    

        @Override
        public Dimension getPreferredSize() {
        return new Dimension(karte.length * 25, karte[0].length * 25);
        }

    }

    public static void main(String[] args) {
        Karte karte = new Karte();
        new spieler.SpielerBewegung(karte, karte.getFrame());
    }
}
