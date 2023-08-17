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
import max.ui.WinPane;

public class SpielerBewegung {

	private Karte karte; // Das Spielfeld
	private JFrame frame; // Das Fenster, in dem sich das Spielfeld befindet
	private FeldAktionen feldaktionen; // Eine Sammlung von Aktionen, die auf dem Spielfeld ausgelöst werden können
	private List<Spieler> alleSpieler;
	private List<Spieler> alleSpielerUr;
	private int aktuellerSpielerIndex = 0;

	/**
	 * Konstruktor, der die notwendigen Parameter initialisiert und die
	 * Tastenverwaltung startet.
	 *
	 * @param karte Das Spielfeld.
	 * @param frame Das Fenster, in dem sich das Spielfeld befindet.
	 */
	public SpielerBewegung(Karte karte, JFrame frame, List<Spieler> spielerListe) {
		this.karte = karte;
		this.frame = frame;
		this.feldaktionen = new FeldAktionen(frame, this);
		this.alleSpieler = spielerListe;
		this.alleSpielerUr = spielerListe;
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

	// Nachfolgend sind die Getter- und Setter-Methoden:

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
					return false; //
				}
				Spieler aktuellerSpieler = Lebewesen.getAktuellerSpieler();
				int[] belegung = aktuellerSpieler.getBelegung();

				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (aktuellerSpieler.hatBewegungen()) {
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
						} else if (e.getKeyCode() == belegung[4]) {
							naechsterSpieler();
							Spieler nextSpieler = alleSpieler.get(
									(alleSpieler.indexOf(Lebewesen.getAktuellerSpieler()) + 1) % alleSpieler.size());
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									isDialogOpen[0] = true;
									JOptionPane.showMessageDialog(frame,
											"Du hast deinen Zug beendet. Nächster Spieler: " + nextSpieler.getName(),
											null, JOptionPane.INFORMATION_MESSAGE);
									isDialogOpen[0] = false;
									frame.repaint();
								}
							});
						}
					} else if (!aktuellerSpieler.hatBewegungen()) {
						naechsterSpieler();
						// Erstellt ein Popup mit dem Namen des nächsten Spielers
						Spieler nextSpieler = alleSpieler
								.get((alleSpieler.indexOf(Lebewesen.getAktuellerSpieler()) + 1) % alleSpieler.size());
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								isDialogOpen[0] = true;
								JOptionPane.showMessageDialog(frame,
										"Du bist erschöpft. Nächster Spieler: " + nextSpieler.getName(), null,
										JOptionPane.INFORMATION_MESSAGE);
								isDialogOpen[0] = false;
								frame.repaint();
							}
						});
					}
				}

				return false; // Der Tastenanschlag wird nicht verbraucht, andere Listener können ebenfalls
								// reagieren.
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
		System.out.println("removed");
		s.setPlatzierung(alleSpieler.size()-1);
		alleSpieler.remove(s);
	
		if(alleSpieler.size() == 1) {
			gewonnen();
		}
	
	
	}
	
	private void gewonnen() {
		frame.setContentPane(new WinPane(alleSpielerUr,frame.WIDTH,frame.HEIGHT));
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
		for (Spieler s : alleSpieler) {
			if (s.getPosition().equals(aktuellerSpieler.getPosition()) && !s.equals(Lebewesen.getAktuellerSpieler())) {
				feldaktionen.spielerKampf(aktuellerSpieler, s);
			}
		}

		// Zeige die Änderungen an der Spielerposition auf der Karte an
		frame.repaint();
	}

}

