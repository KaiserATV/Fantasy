package fabio.gui.aktionen;

import java.awt.Point;
import java.awt.image.BufferedImage;
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
import img.BufferedImagesSammlung;
import max.uilogik.KampfUIController;
import max.uilogik.ShopUIController;

public class FeldAktionen {

	private SpielerBewegung bewegung;
	private Point position;
	private Map<Point, Shops> shopsAtPositions = new HashMap<>();
	private JFrame frame;
	private BufferedImagesSammlung bilder;

	public FeldAktionen(JFrame jf, SpielerBewegung bew, BufferedImagesSammlung bis) {
		frame = jf;
		bewegung = bew;
		bilder = bis;
	}

	public void betreteBuchhandlung(Spieler ich) {
		Buchhandlung b=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Buchhandlung) {
			b = (Buchhandlung) shopsAtPositions.get(position);
		} else {
			b = new Buchhandlung(position);
			shopsAtPositions.put(position, b);
		}
		shopLaufen(ich, b,bilder.getBuchhandlungBild());
	}

	public void betreteJuwelier(Spieler ich) {
		Juwelier j=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Juwelier) {
			j = (Juwelier) shopsAtPositions.get(position);
		} else {
				j = new Juwelier(position);
			
			shopsAtPositions.put(position, j);
		}
		shopLaufen(ich, j,bilder.getjuwelierBild());
	}

	public void betreteSchmiede(Spieler ich) {
		Schmiede s=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Schmiede) {
			s = (Schmiede) shopsAtPositions.get(position);
		} else {
				s = new Schmiede(position);
			
			shopsAtPositions.put(position, s);
		}
		shopLaufen(ich, s,bilder.getSchmiedeBild());
	}

	public void betreteTaverne(Spieler ich) {
		Taverne t=null;
		if (shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Taverne) {
			t = (Taverne) shopsAtPositions.get(position);
		} else {
			
				t = new Taverne(position);
			
			shopsAtPositions.put(position, t);
		}
		shopLaufen(ich, t,bilder.getTaverneBild());
	}


	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}

	

	public void wahrscheinlichkeitHaendlerTreffen(Spieler ich) {
		Random rand = new Random();
		int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99
		TravelingMerchant t=null;
		if (chance < 8) { // 8% Chance auf fahrenden Händler
			
			t = new TravelingMerchant(position);
		
			shopLaufen(ich, t, bilder.getKarrenBild());
		}
	}

	public void wahrscheinlichkeitMonsterInteraktion(Spieler ich) {
		Random rand = new Random();
		int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99
		Monster m;
		BufferedImage b;

		if (rand.nextInt(2) == 0) {
			m = new Beluaferus();
			b = bilder.getBeluaferus();
		} else {
			m = new Volares();
			b = bilder.getVolares();
		}

		if (chance < 10) { // 10% Chance auf Monsterinteraktion
			monsterLaufen(ich, m,b);

		}
	}
	
	
	private void shopLaufen(Spieler ich, Shops s, BufferedImage b) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ShopUIController shop = new ShopUIController(ich, s, frame, bewegung, b);
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
				KampfUIController kampf = new KampfUIController(eins, zwei, frame, bewegung, bilder.getKampfHintergrund(), null);
			}
		});

	}
	private void monsterLaufen(Spieler ich, Monster gegen, BufferedImage b) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				KampfUIController kampf = new KampfUIController(ich, gegen, frame, bewegung, bilder.getKampfHintergrund(), b);
			}
		});
	}
}