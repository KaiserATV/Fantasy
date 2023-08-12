package fabio.gui.karte;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ani.fantasyLebewesen.Spieler;

public class Karte {

        private static final int FELD_BREITE = 25;
        private static final int FELD_HOEHE = FELD_BREITE;
        private static final int KARTE_BREITE = 70;
        private static final int KARTE_HOEHE = KARTE_BREITE;
        private static final int GEBAEUDE_RATIO = 30;
        private static final int MAX_VERBINDUNGEN_VON_ECKE = 40;
        private static final String WEG_BILD_PATH = "Fantasy/src/img/Weg.png";
        private static final String BAUM_BILD_PATH = "Fantasy/src/img/Laub.png";
        private static final String GEBAEUDE_BILD_PATH = "Fantasy/src/img/Haus.png";
        private FeldTyp[][] karte;
        private JPanel contentPane = new JPanel();
        private JFrame frame;
        private Image wegBild;
        private Image baumBild;
        private Image gebaeudeBild; 

        public boolean add(Spieler arg0) {
                return spielerListe.add(arg0);
        }

        public enum FeldTyp {
                WEG,
                BAUM,
                Juwelier,
        		Buchhandlung,
        		Schmiede
        }
      
        
        /**
         * Zeichnet die Karte und den Spieler.
         * 
         * @param g Das Graphics-Objekt, das zum Zeichnen verwendet wird.
         */
        public class KartenPanel extends JPanel {
                
                private static final long serialVersionUID = 1L;

                @Override
                public Dimension getPreferredSize() {
                        return new Dimension(karte.length * FELD_BREITE, karte[0].length * FELD_HOEHE);
                }

                @Override
                protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

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
                                        case Juwelier:
                                        case Buchhandlung:
                                        case Schmiede:
                                                aktuellesBild = gebaeudeBild;
                                                break;
                                        }
                                        g.drawImage(aktuellesBild, i * FELD_BREITE, j * FELD_HOEHE, FELD_BREITE, FELD_HOEHE, this);

                                        // Hier zeichnest du die Spieler, falls sie auf dieser Kachel sind
                                        for (Spieler spieler: spielerListe) {
                                                if (spieler.getPosition().equals(new Point(i, j))) {
                                                        spieler.draw(g, FELD_BREITE);
                                                }
                                        }
                                }
                        }
                }
        }
              
        private List < Spieler > spielerListe = new ArrayList < > ();

        // Getter-Methode für das JFrame-Objekt der Karte.
        // @return Gibt den Frame zurück, auf dem die Karte dargestellt wird.

        public Karte() {
                karte = new FeldTyp[KARTE_BREITE][KARTE_HOEHE];
                initKarte();

                wegBild = new ImageIcon(WEG_BILD_PATH).getImage();
                baumBild = new ImageIcon(BAUM_BILD_PATH).getImage();
                gebaeudeBild = new ImageIcon(GEBAEUDE_BILD_PATH).getImage();

                frame = new JFrame("Karte");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                contentPane.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;

                KartenPanel kartenPanel = new KartenPanel();
                JScrollPane scrollPane = new JScrollPane(kartenPanel);
                contentPane.add(scrollPane, gbc);
                contentPane.setVisible(true);
                frame.setLayout(new CardLayout());
                frame.add("a",contentPane);
                
                frame.pack();
                frame.setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
                frame.setVisible(true);

                spielerListe = new ArrayList < > ();

        }

        public JFrame getFrame() {
                return frame;
        }

        // Getter-Methode für die Anzahl der Zellen in der Karte.
        // @return Gibt die Länge des karte-Arrays zurück.

        public void addSpieler(Spieler spieler) {
                spielerListe.add(spieler);
        }

        public int getNUM_CELLS() {
                return karte.length;
        }

        /**
         * Getter-Methode, um den FeldTyp an einer gegebenen Position zu erhalten.
         * 
         * @param point Die Position auf der Karte.
         * @return Gibt den FeldTyp an der gegebenen Position zurück.
         */
        public FeldTyp getFeldTypAtPosition(Point point) {
                if (point.x >= 0 && point.x < karte.length && point.y >= 0 && point.y < karte[0].length) {
                        return karte[point.x][point.y];
                } else {
                        throw new IndexOutOfBoundsException("Position außerhalb der Karte!");
                }
        }

        // Hinzugefügt: Getter für das karte-Array
        public FeldTyp[][] getKarte() {
                return karte;
        }

        // Hinzugefügt: Setter für das karte-Array
        public void setKarte(FeldTyp[][] karte) {
                this.karte = karte;
        }

        /**
         * Initialisiert die Karte mit Bäumen, erstellt zufällig Gebäude und verbindet
         * sie mit Wegen.
         */
        private void initKarte() {
                for (int i = 0; i < karte.length; i++) {
                        Arrays.fill(karte[i], FeldTyp.BAUM);
                }

                Random rand = new Random();
                int gebaeudeAnzahl = (karte.length * karte[0].length) / GEBAEUDE_RATIO;
                List < Point > gebaeudeListe = new ArrayList < > ();

                for (int i = 0; i < gebaeudeAnzahl; i++) {
                        int x = rand.nextInt(karte.length);
                        int y = rand.nextInt(karte[0].length);
                        if (karte[x][y] != FeldTyp.Juwelier && karte[x][y] != FeldTyp.Buchhandlung && karte[x][y] != FeldTyp.Schmiede) {
                        		if(i%3==0) {
                        			karte[x][y] = FeldTyp.Juwelier;
                        		}else if(i%3==1) {
                        			karte[x][y] = FeldTyp.Buchhandlung;
                        		}else if(i%3==2) {
                        			karte[x][y] = FeldTyp.Schmiede;
                        		}
                                gebaeudeListe.add(new Point(x, y));
                        } else {
                                i--;
                        }
                }

                Point[] ecken = {
                        new Point(0, 0),
                        new Point(0, karte[0].length - 1),
                        new Point(karte.length - 1, 0),
                        new Point(karte.length - 1, karte[0].length - 1)
                };

                for (Point ecke: ecken) {
                        List < Point > kopieGebaeudeListe = new ArrayList < > (gebaeudeListe);
                        verbindeMitNaechstenGebaeuden(ecke, kopieGebaeudeListe, MAX_VERBINDUNGEN_VON_ECKE); // Verbinde bis zu xx Gebäude von jeder Ecke
                        // aus
                }
        }

        /**
         * Verbindet einen Startpunkt mit dem nächsten Gebäude bis zu einem gegebenen
         * Maximum an Verbindungen.
         * 
         * @param start           Der Startpunkt.
         * @param gebaeudeListe   Liste aller Gebäude.
         * @param maxVerbindungen Maximale Anzahl der Verbindungen.
         */

        private void verbindeMitNaechstenGebaeuden(Point start, List < Point > gebaeudeListe, int maxVerbindungen) {
                if (gebaeudeListe.isEmpty() || maxVerbindungen <= 0) {
                        return;
                }

                Point naechstesGebaeude = findeNaechstesGebaeude(start, gebaeudeListe);
                if (naechstesGebaeude == null) {
                        return;
                }

                erstelleWeg(start, naechstesGebaeude);
                gebaeudeListe.remove(naechstesGebaeude);

                // Rekursiver Aufruf
                verbindeMitNaechstenGebaeuden(naechstesGebaeude, gebaeudeListe, maxVerbindungen - 1);
        }

        /**
         * Findet das nächstgelegene Gebäude zu einem gegebenen Startpunkt.
         * 
         * @param start         Der Startpunkt.
         * @param gebaeudeListe Liste aller Gebäude.
         * @return Gibt den Punkt des nächstgelegenen Gebäudes zurück.
         */
        private Point findeNaechstesGebaeude(Point start, List < Point > gebaeudeListe) {
                Point naechstesGebaeude = null;
                double minimaleDistanz = Double.MAX_VALUE;

                for (Point gebaeude: gebaeudeListe) {
                        double distanz = start.distance(gebaeude);
                        if (distanz < minimaleDistanz) {
                                minimaleDistanz = distanz;
                                naechstesGebaeude = gebaeude;
                        }
                }

                return naechstesGebaeude;
        }

        /**
         * Erstellt einen Weg zwischen zwei Punkten auf der Karte.
         * 
         * @param start Der Startpunkt des Weges.
         * @param ziel  Der Endpunkt des Weges.
         */
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

                        if (karte[x][y] != FeldTyp.Juwelier && karte[x][y] != FeldTyp.Buchhandlung && karte[x][y] != FeldTyp.Schmiede) {
                                karte[x][y] = FeldTyp.WEG;
                        }
                }
        }
}