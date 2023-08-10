package fabio.spiel;

import java.awt.Point;
import java.awt.Color;
import fabio.gui.karte.Karte;
import fabio.gui.karte.Karte.FeldTyp;
import fabio.spielerbewegung.SpielerBewegung;

import java.util.ArrayList;
import java.util.List;

import ani.lebewesen.Spieler;

public class Spiel {

        private Karte karte = new Karte();
        private List < Spieler > alleSpieler = new ArrayList < > ();
        private static SpielerBewegung spielerBewegung;

        public FeldTyp getFeldTypAtPosition(Point point) {
                return karte.getFeldTypAtPosition(point);
        }

        public Spiel() {
                // Spiel-Instanz erstellen
        		Spiel spiel = this;

                // Spieler erstellen und Positionen anpassen
                Spieler player1 = new Spieler(new Point(0, 0), Color.RED);
                Spieler player2 = new Spieler(new Point(0, 69), Color.GREEN);
                Spieler player3 = new Spieler(new Point(69, 0), Color.BLUE);
                Spieler player4 = new Spieler(new Point(69, 69), Color.YELLOW);

//                // Spieler zur Liste hinzufügen
                spiel.alleSpieler.add(player1);
                spiel.alleSpieler.add(player2);
                spiel.alleSpieler.add(player3);
                spiel.alleSpieler.add(player4);

                // Aktuellen Spieler setzen (zum Beispiel zu Beginn des Spiels)
                Spieler.setAktuellerSpieler(player1);

//                // Überprüfen, ob Spieler erfolgreich erstellt wurden
                if (player1 != null && player2 != null && player3 != null && player4 != null) {
                        // Aktuellen Spieler bewegen (anhand von Koordinaten)
                        spiel.karte.addSpieler(player1);
                        spiel.karte.addSpieler(player2);
                        spiel.karte.addSpieler(player3);
                        spiel.karte.addSpieler(player4);

                        spielerBewegung = new SpielerBewegung(spiel.karte, spiel.karte.getFrame(), spiel.alleSpieler);

                        // KartenPanel-Instanz aus dem Karte-Objekt holen
                        Karte.KartenPanel kartenPanel = spiel.karte.new KartenPanel();
                        // Das KartenPanel neu zeichnen, um die Spielerpositionen zu aktualisieren
                        kartenPanel.repaint();
                }
        }
}