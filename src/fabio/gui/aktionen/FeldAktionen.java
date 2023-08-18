package fabio.gui.aktionen;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ani.fantasyLebewesen.nsc.Beluaferus;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.nsc.Volares;
import ani.fantasyLebewesen.spieler.Spieler;
import ani.fantasyShops.Buchhandlung;
import ani.fantasyShops.Juwelier;
import ani.fantasyShops.Schmiede;
import ani.fantasyShops.Shops;
import ani.fantasyShops.Taverne;
import ani.fantasyShops.TravelingMerchant;
import fabio.spielerbewegung.SpielerBewegung;
import max.uilogik.KampfUIController;
import max.uilogik.ShopUIController;

public class FeldAktionen {

	private SpielerBewegung bewegung;
	private Point position;
	private Map<Point, Shops> shopsAtPositions = new HashMap<>();
	private JFrame frame;

	public FeldAktionen(JFrame jf, SpielerBewegung bew) {
		frame = jf;
		bewegung = bew;
	}

	public void betreteBuchhandlung(Spieler ich) {
		Buchhandlung b=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Buchhandlung) {
			b = (Buchhandlung) shopsAtPositions.get(position);
		} else {
			try {
			b = new Buchhandlung(position);
			}catch(IOException e) {
				System.out.println("Fehler bei Erstellung des Hintergrundbildes...");
				e.printStackTrace();
			}
			
			shopsAtPositions.put(position, b);
		}
		shopLaufen(ich, b);
	}

	public void betreteJuwelier(Spieler ich) {
		Juwelier j=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Juwelier) {
			j = (Juwelier) shopsAtPositions.get(position);
		} else {
			try {
				j = new Juwelier(position);
			}catch(IOException e) {
				System.out.println("Fehler bei Erstellung des Hintergrundbildes...");
				e.printStackTrace();
			}
			shopsAtPositions.put(position, j);
		}
		shopLaufen(ich, j);
	}

	public void betreteSchmiede(Spieler ich) {
		Schmiede s=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Schmiede) {
			s = (Schmiede) shopsAtPositions.get(position);
		} else {
			try {
				s = new Schmiede(position);
			}catch(IOException e) {
				System.out.println("Fehler bei Erstellung des Hintergrundbildes...");
				e.printStackTrace();
			}
			shopsAtPositions.put(position, s);
		}
		shopLaufen(ich, s);
	}

	public void betreteTaverne(Spieler ich) {
		Taverne t=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Taverne) {
			t = (Taverne) shopsAtPositions.get(position);
		} else {
			try {
				t = new Taverne(position);
			}catch(IOException e) {
				System.out.println("Fehler bei Erstellung des Hintergrundbildes...");
				e.printStackTrace();
			}
			shopsAtPositions.put(position, t);
		}
		shopLaufen(ich, t);
	}

	private void monsterLaufen(Spieler ich, Monster gegen) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				KampfUIController kampf = new KampfUIController(ich, gegen, frame, bewegung);
			}
		});
	}

	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}

	private void shopLaufen(Spieler ich, Shops s) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ShopUIController shop = new ShopUIController(ich, s, frame, bewegung);
			}
		});
	}

	public void spielerKampf(Spieler eins, Spieler zwei) {
		spielerLaufen(eins, zwei);
	}

	private void spielerLaufen(Spieler eins, Spieler zwei) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				KampfUIController kampf = new KampfUIController(eins, zwei, frame, bewegung);
			}
		});

	}

	public void wahrscheinlichkeitHaendlerTreffen(Spieler ich) {
		Random rand = new Random();
		int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99
		TravelingMerchant t=null;
		if (chance < 8) { // 8% Chance auf fahrenden Händler
		try {
			t = new TravelingMerchant(position);
		}catch(IOException e) {
			System.out.println("Fehler bei Erstellung des Hintergrundbildes...");
			e.printStackTrace();
		}
			shopLaufen(ich, t);
		}
	}

	public void wahrscheinlichkeitMonsterInteraktion(Spieler ich) {
		Random rand = new Random();
		int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99
		Monster m;

		if (rand.nextInt(2) == 0) {
			m = new Beluaferus();
		} else {
			m = new Volares();
		}

		if (chance < 10) { // 10% Chance auf Monsterinteraktion
			monsterLaufen(ich, m);

		}
	}
}