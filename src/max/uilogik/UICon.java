package max.uilogik;

import java.awt.event.KeyEvent;

import ani.fantasyLebewesen.spieler.Spieler;
import fabio.spielerbewegung.SpielerBewegung;

public abstract class UICon {

	public UICon(Spieler x) {
		links = x.getBelegung()[0];
		rechts = x.getBelegung()[1];
		oben = x.getBelegung()[2];
		unten = x.getBelegung()[3];
		auswahl = x.getBelegung()[4];
		zurueck = x.getBelegung()[5];
	}
	protected int rechts;
	protected int links;
	protected int oben;
	protected int unten;
	protected int zurueck;
	protected int auswahl;
	protected SpielerBewegung bewegung;
}
