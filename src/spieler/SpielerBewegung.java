package spieler;

import karte.Karte;
import javax.swing.*;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import aktionen.FeldAktionen;
import java.util.List;

public class SpielerBewegung {

        private Karte karte; // Das Spielfeld
        private JFrame frame; // Das Fenster, in dem sich das Spielfeld befindet
        private FeldAktionen feldaktionen; // Eine Sammlung von Aktionen, die auf dem Spielfeld ausgelöst werden können
        private List < Spieler > alleSpieler;
        private int aktuellerSpielerIndex = 0;

        /**
         * Konstruktor, der die notwendigen Parameter initialisiert und die
         * Tastenverwaltung startet.
         *
         * @param karte Das Spielfeld.
         * @param frame Das Fenster, in dem sich das Spielfeld befindet.
         */
        public SpielerBewegung(Karte karte, JFrame frame, List < Spieler > spielerListe) {
                this.karte = karte;
                this.frame = frame;
                this.feldaktionen = new FeldAktionen();
                this.alleSpieler = spielerListe; // Hier haben wir die Änderung vorgenommen
                initSpielerBewegung();
        }

        /**
         * Initialisiert die Verwaltung der Tastenanschläge, um die Bewegung des
         * Spielers zu steuern.
         */
        public void initSpielerBewegung() {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
                        @Override
                        public boolean dispatchKeyEvent(KeyEvent e) {
                                Spieler aktuellerSpieler = Spieler.getAktuellerSpieler();
                                if (e.getID() == KeyEvent.KEY_PRESSED) {
                                        switch (e.getKeyCode()) {
                                        case KeyEvent.VK_UP:
                                        case KeyEvent.VK_NUMPAD8:
                                                aktuellerSpieler.moveUp(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                                break;
                                        case KeyEvent.VK_DOWN:
                                        case KeyEvent.VK_NUMPAD2:
                                                aktuellerSpieler.moveDown(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                                break;
                                        case KeyEvent.VK_LEFT:
                                        case KeyEvent.VK_NUMPAD4:
                                                aktuellerSpieler.moveLeft(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                                break;
                                        case KeyEvent.VK_RIGHT:
                                        case KeyEvent.VK_NUMPAD6:
                                                aktuellerSpieler.moveRight(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                                break;
                                        case KeyEvent.VK_ENTER:
                                                naechsterSpieler(); // Methode aufrufen, um den nächsten Spieler auszuwählen
                                                break;
                                        }
                                }
                                return false; // Der Tastenanschlag wird nicht verbraucht, andere Listener können ebenfalls
                                // reagieren
                        }
                });
        }

        /**
         * Methode, um den nächsten Spieler auszuwählen.
         */
        public void naechsterSpieler() {
                if (alleSpieler != null && !alleSpieler.isEmpty()) {
                        int currentIndex = alleSpieler.indexOf(Spieler.getAktuellerSpieler());
                        int nextIndex = (currentIndex + 1) % alleSpieler.size();
                        Spieler.setAktuellerSpieler(alleSpieler.get(nextIndex));
                }
        }

        /**
         * Wird aufgerufen, wenn der Spieler sich bewegt hat. Es prüft den Feldtyp auf
         * dem sich der Spieler
         * gerade befindet und führt die entsprechende Aktion aus.
         */
        private void spielerBewegt() {
                Spieler aktuellerSpieler = Spieler.getAktuellerSpieler();
                Karte.FeldTyp aktuellesFeld = karte.getFeldTypAtPosition(aktuellerSpieler.getPosition());

                switch (aktuellesFeld) {
                case GEBÄUDE:
                        feldaktionen.betreteGebaeude();
                        break;
                case BAUM:
                        feldaktionen.wahrscheinlichkeitMonsterInteraktion();
                        break;
                case WEG:
                        feldaktionen.wahrscheinlichkeitHaendlerTreffen();
                        break;
                }

                // Zeige die Änderungen an der Spielerposition auf der Karte an
                frame.repaint();
        }

        // Nachfolgend sind die Getter- und Setter-Methoden:

        public Karte getKarte() {
                return karte;
        }

        public void setKarte(Karte karte) {
                this.karte = karte;
        }

        public JFrame getFrame() {
                return frame;
        }

        public void setFrame(JFrame frame) {
                this.frame = frame;
        }

        public FeldAktionen getFeldaktionen() {
                return feldaktionen;
        }

        public void setFeldaktionen(FeldAktionen feldaktionen) {
                this.feldaktionen = feldaktionen;
        }
}