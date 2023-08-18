package fabio.spiel;

import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;
import fabio.gui.karte.Karte;
import fabio.gui.karte.Karte.FeldTyp;
import fabio.gui.spielerDialog.SpielerDialog;
import fabio.spielerbewegung.SpielerBewegung;

public class Spiel {

	private static SpielerBewegung spielerBewegung;
	private Karte karte = new Karte();
	private List<Spieler> alleSpieler = new ArrayList<>();
	private List<Spieler> alleSpielerUr = new ArrayList<>();
	private Spiel spiel;
	private List<String> alleNamen = new ArrayList<>();

	public Spiel() {
		// Spiel-Instanz erstellen
		spiel = this;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				SpielerDialog s = new SpielerDialog(karte.getFrame(), true, spiel);
				s.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						((Window) e.getComponent()).dispose();
					}
				});
			}
		});

	}

	public void addNamen(String s) {
		alleNamen.add(s);
	}

	public FeldTyp getFeldTypAtPosition(Point point) {
		return karte.getFeldTypAtPosition(point);
	}

	public List<Point> getKartenEcken() {
		return karte.getKartenecken();
	}

	public List<String> getNamen() {
		return alleNamen;
	}

	public int getSpielerAnzahl() {
		return alleSpieler.size();
	}

	public boolean gibtNamen(String text) {
		text = text.trim();
		if (alleNamen.contains(text)) {
			return true;
		}
		return false;
	}

	public void spielerAdd(Spieler spieler) {
		// Spieler zur Liste hinzufügen
		spiel.alleSpieler.add(spieler);
		//
		spiel.alleSpielerUr.add(spieler);
		//
		addNamen(spieler.getName());
		
	}

	public void spielern() {
		// Aktuellen Spieler setzen (zum Beispiel zu Beginn des Spiels)
		Lebewesen.setAktuellerSpieler(alleSpieler.get(0));

		for (Spieler player : alleSpieler) {
			spiel.karte.addSpieler(player);
		}

		if (alleSpieler.size() != 0 && alleSpieler.size() < 5) {
			// Aktuellen Spieler bewegen (anhand von Koordinaten)
			spielerBewegung = new SpielerBewegung(spiel.karte, spiel.karte.getFrame(), spiel.alleSpieler,spiel.alleSpielerUr);
			// KartenPanel-Instanz aus dem Karte-Objekt holen
			Karte.KartenPanel kartenPanel = spiel.karte.new KartenPanel();
			// Das KartenPanel neu zeichnen, um die Spielerpositionen zu aktualisieren
			kartenPanel.grabFocus();
			kartenPanel.repaint();
		}
	}

}