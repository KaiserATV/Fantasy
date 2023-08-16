package fabio.spielerbewegung;

import fabio.gui.karte.Karte;

import javax.swing.*;

import ani.fantasyLebewesen.spieler.Spieler;

import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import fabio.gui.aktionen.FeldAktionen;

import java.util.ArrayList;
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
                this.feldaktionen = new FeldAktionen(frame, this);
                this.alleSpieler = spielerListe; // Hier haben wir die Änderung vorgenommen
                initSpielerBewegung();
                frame.repaint();
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
                                int[] belegung = aktuellerSpieler.getBelegung();
                                if (e.getID() == KeyEvent.KEY_PRESSED) {
                                        if(e.getKeyCode() == belegung[0]){
                                                aktuellerSpieler.moveLeft(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                        }else if(e.getKeyCode() == belegung[1]){
                                                aktuellerSpieler.moveRight(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                                spielerBewegt();
                                                frame.repaint();
                                        }else if(e.getKeyCode() == belegung[2]){
                                            aktuellerSpieler.moveUp(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                                            spielerBewegt();
                                            frame.repaint();
	                                    }else if(e.getKeyCode() == belegung[3]){
	                                        aktuellerSpieler.moveDown(karte.getNUM_CELLS(), karte.getNUM_CELLS());
	                                        spielerBewegt();
	                                        frame.repaint();
		                                }else if(e.getKeyCode() == belegung[4]){
		                                	naechsterSpieler();
                                            frame.repaint();
		                                }
                        }
                                return false; // Der Tastenanschlag wird nicht verbraucht, andere Listener können ebenfalls
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
                frame.repaint();
                switch (aktuellesFeld) {
                case Juwelier:
                        feldaktionen.betreteJuwelier(aktuellerSpieler);
                        break;
                case Buchhandlung:
                    feldaktionen.betreteBuchhandlung(aktuellerSpieler);
                    break;
                case Schmiede:
                    feldaktionen.betreteSchmiede(aktuellerSpieler);
                    break;
                case Taverne:
                	feldaktionen.betreteTaverne(aktuellerSpieler);
                case BAUM:
                        feldaktionen.wahrscheinlichkeitMonsterInteraktion(aktuellerSpieler);
                        break;
                case WEG:
                        feldaktionen.wahrscheinlichkeitHaendlerTreffen(aktuellerSpieler);
                        break;
                }
                for(Spieler s:alleSpieler) {
                	if(s.getPosition().equals(aktuellerSpieler.getPosition()) && !s.equals(Spieler.getAktuellerSpieler())) {
                		feldaktionen.spielerKampf(aktuellerSpieler,s);
                	}
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
        public void removeSpieler(Spieler s) {
        	alleSpieler.remove(s);
        }
        
        
        
        
}