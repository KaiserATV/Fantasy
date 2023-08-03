package spiel;

import java.awt.Point;
import aktionen.FeldAktionen;
import karte.Karte;
import karte.Karte.FeldTyp;
import spieler.Spieler;

public class Spiel {

    private FeldAktionen aktionen = new FeldAktionen();
    private Karte karte = new Karte();
    private Spieler spieler = new Spieler();

    public FeldTyp getFeldTypAtPosition(Point point) {
        return karte.getFeldTypAtPosition(point);  
    }


    public static void main(String[] args) {
        Spiel spiel = new Spiel();
        // Weitere Logik für Ihr Spiel
    }
}