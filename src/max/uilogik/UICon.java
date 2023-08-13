package max.uilogik;

import java.awt.event.KeyEvent;

import ani.fantasyLebewesen.Spieler;
import fabio.spielerbewegung.SpielerBewegung;

public abstract class UICon {

	public UICon(Spieler x) {
		links = x.getBelegung()[0];
		rechts = x.getBelegung()[1];
		auswahl = x.getBelegung()[4];
		zurueck = x.getBelegung()[5];
	}
	protected int rechts = KeyEvent.VK_D;
	protected int links = KeyEvent.VK_A;
	protected int zurueck = KeyEvent.VK_S;
	protected int auswahl = KeyEvent.VK_SPACE;
	protected SpielerBewegung bewegung;
}
