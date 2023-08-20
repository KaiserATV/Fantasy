package fabio.spielerbewegung;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;
import fabio.gui.aktionen.FeldAktionen;
import fabio.gui.karte.Karte;
import img.BufferedImagesSammlung;
import max.ui.WinPane;

public class SpielerBewegung {

	private Karte karte; // Das Spielfeld
	private JFrame frame; // Das Fenster, in dem sich das Spielfeld befindet
	private FeldAktionen feldaktionen; // Eine Sammlung von Aktionen, die auf dem Spielfeld ausgelöst werden können
	private List<Spieler> alleSpieler;
	private List<Spieler> alleSpielerUr;
	private int aktuellerSpielerIndex = 0;
	private BufferedImagesSammlung bis;

	/**
	 * Konstruktor, der die notwendigen Parameter initialisiert und die
	 * Tastenverwaltung startet.
	 *
	 * @param karte Das Spielfeld.
	 * @param frame Das Fenster, in dem sich das Spielfeld befindet.
	 */
	
	
	public SpielerBewegung(Karte karte, JFrame frame, List<Spieler> spielerListe, List<Spieler> ur, BufferedImagesSammlung b) {
		this.karte = karte;
		this.frame = frame;
		this.feldaktionen = new FeldAktionen(frame, this, b);
		this.alleSpieler = spielerListe;
		this.alleSpielerUr = ur;
		bis = b;
		
		
		initSpielerBewegung();
		frame.repaint();
	}
	public FeldAktionen getFeldaktionen() {
		return feldaktionen;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Karte getKarte() {
		return karte;
	}
	
	private boolean interaktionAktiv = false;

    public void starteInteraktion() {
        this.interaktionAktiv = true;
    }

    public void beendeInteraktion() {
        this.interaktionAktiv = false;
    }

    public boolean istInteraktionAktiv() {
        return this.interaktionAktiv;
    }

	/**
	 * Initialisiert die Verwaltung der Tastenanschläge, um die Bewegung des
	 * Spielers zu steuern.
	 */
    public void initSpielerBewegung() {
    	final boolean[] isDialogOpen = { false };

    	KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
    		@Override
    		public boolean dispatchKeyEvent(KeyEvent e) {
    			if (isDialogOpen[0]) {
    				return false; // Ignoriert den Tastenanschlag, wenn ein Dialog geöffnet ist.
    			}
    			Spieler aktuellerSpieler = Lebewesen.getAktuellerSpieler();
    			int[] belegung = aktuellerSpieler.getBelegung();

    			if (e.getID() == KeyEvent.KEY_RELEASED && aktuellerSpieler.hatBewegungen()) {
    				if (e.getKeyCode() == belegung[0]) {
    						aktuellerSpieler.moveLeft(karte.getNUM_CELLS(), karte.getNUM_CELLS());
    						spielerBewegt();
    						frame.repaint();
    				} else if (e.getKeyCode() == belegung[1]) {
    						aktuellerSpieler.moveRight(karte.getNUM_CELLS(), karte.getNUM_CELLS());
    						spielerBewegt();
    						frame.repaint();
    				} else if (e.getKeyCode() == belegung[2]) {
    						aktuellerSpieler.moveUp(karte.getNUM_CELLS(), karte.getNUM_CELLS());
    						spielerBewegt();
    						frame.repaint();
    				} else if (e.getKeyCode() == belegung[3]) {
    						aktuellerSpieler.moveDown(karte.getNUM_CELLS(), karte.getNUM_CELLS());
    						spielerBewegt();
    						frame.repaint();
    				}
  
    			}

    			if (e.getID() == KeyEvent.KEY_PRESSED) {
    				if (e.getKeyCode() == belegung[4]) {
    					Spieler nextSpieler = alleSpieler.get(
    							(alleSpieler.indexOf(aktuellerSpieler) + 1) % alleSpieler.size());
    					showDialogAndMoveToNextSpieler("Du hast deinen Zug beendet. Nächster Spieler: " + nextSpieler.getName());
    				} else if (!aktuellerSpieler.hatBewegungen()) {
    					Spieler nextSpieler = alleSpieler.get(
    							(alleSpieler.indexOf(aktuellerSpieler) + 1) % alleSpieler.size());
    					showDialogAndMoveToNextSpieler("Du bist erschöpft. Nächster Spieler: " + nextSpieler.getName());
    				}
    			}

    			return true; // Der Tastenanschlag wird nicht verbraucht, andere Listener können ebenfalls reagieren.
    		}

    		private void showDialogAndMoveToNextSpieler(String message) {
    			SwingUtilities.invokeLater(new Runnable() {
    				@Override
    				public void run() {
    					isDialogOpen[0] = true;
    					JOptionPane.showMessageDialog(frame, message, null, JOptionPane.INFORMATION_MESSAGE);
    					isDialogOpen[0] = false;
    					naechsterSpieler();
    					frame.repaint();
    				}
    			});
    		}
    	});
    }


	/**
	 * Methode, um den nächsten Spieler auszuwählen.
	 */
	public void naechsterSpieler() {
		if (alleSpieler != null && !alleSpieler.isEmpty()) {
			int currentIndex = alleSpieler.indexOf(Lebewesen.getAktuellerSpieler());
			int nextIndex = (currentIndex + 1) % alleSpieler.size();
			Lebewesen.setAktuellerSpieler(alleSpieler.get(nextIndex));
			alleSpieler.get(nextIndex).resetBewegungen();
		}
	}

	public void removeSpieler(Spieler s) {
	    s.setPlatzierung(alleSpieler.size()-1);
	    alleSpieler.remove(s);
	    System.out.println(alleSpieler.size());
	    if(alleSpieler.size() < 2) {
	        gewonnen();
	    } else {
	        naechsterSpieler();  // Hier wechseln Sie zum nächsten Spieler, nachdem ein Spieler entfernt wurde
	    }
	}

	
	private void gewonnen() {
		frame.setContentPane(new WinPane(alleSpielerUr,bis).getContentPane());
		System.out.println("test");
		frame.repaint();
		frame.revalidate();
		
	}

	public void setFeldaktionen(FeldAktionen feldaktionen) {
		this.feldaktionen = feldaktionen;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	/**
	 * Wird aufgerufen, wenn der Spieler sich bewegt hat. Es prüft den Feldtyp auf
	 * dem sich der Spieler gerade befindet und führt die entsprechende Aktion aus.
	 */
	private void spielerBewegt() {
	    Spieler aktuellerSpieler = Lebewesen.getAktuellerSpieler();

	    // Prüfe zuerst, ob es einen Kampf mit einem anderen Spieler gibt
	    boolean kampfGefunden = false;
	    for (Spieler s : alleSpieler) {
	        if (s.getPosition().equals(aktuellerSpieler.getPosition()) && !s.equals(Lebewesen.getAktuellerSpieler())) {
	            feldaktionen.spielerKampf(aktuellerSpieler, s);
	            kampfGefunden = true;
	            break;
	        }
	    }

	    // Wenn kein Kampf gefunden wurde, fahre mit den anderen Aktionen fort
	    if (!kampfGefunden) {
	        Karte.FeldTyp aktuellesFeld = karte.getFeldTypAtPosition(aktuellerSpieler.getPosition());
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
	                break;
	            case BAUM:
	                feldaktionen.wahrscheinlichkeitMonsterInteraktion(aktuellerSpieler);
	                break;
	            case WEG:
	                feldaktionen.wahrscheinlichkeitHaendlerTreffen(aktuellerSpieler);
	                break;
	        }
	    }

	    // Zeige die Änderungen an der Spielerposition auf der Karte an
	    frame.repaint();
	}


}

