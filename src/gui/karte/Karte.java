package karte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.List;
import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;


public class Karte {

    public enum FeldTyp {
        WEG, BAUM, GEBÄUDE
    }

    private FeldTyp[][] karte;
    private JFrame frame;
    private Image wegBild;
    private Image baumBild;
    private Image gebaeudeBild;

    public Karte() {
        karte = new FeldTyp[100][100];
        initKarte();
        
        wegBild = new ImageIcon("Fantasy/src/Bilder/Weg.png").getImage();
        baumBild = new ImageIcon("Fantasy/src/Bilder/Laub.png").getImage();
        gebaeudeBild = new ImageIcon("Fantasy/src/Bilder/Haus.png").getImage();

        frame = new JFrame("Karte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JScrollPane scrollPane = new JScrollPane(new KartenPanel());
        frame.add(scrollPane);
        
        frame.setVisible(true);
    }

    private void initKarte() {
        for (int i = 0; i < karte.length; i++) {
            Arrays.fill(karte[i], FeldTyp.BAUM);
        }

        Random rand = new Random();
        int gebaeudeAnzahl = (karte.length * karte[0].length) / 10;

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

         // Einfaches Verfahren zur Erstellung von Wegen zwischen Gebäuden
    Random wegRand = new Random();
    for (int i = 0; i < gebaeudeListe.size() - 1; i++) {
        if (wegRand.nextInt(50) == 0) { // 2% Chance, einen Weg zu erstellen
            Point start = gebaeudeListe.get(i);
            Point ziel = gebaeudeListe.get(i + 1);
            erstelleWeg(start, ziel);
        }
    }
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
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int feldBreite = 20;
            int feldHöhe = 20;

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
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(karte.length * 8, karte[0].length * 8);
        }
    }

    public static void main(String[] args) {
        new Karte();
    }
}