package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;

public class Ork extends Spieler {

	public Ork(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = startHpMax = 90;
		strength = startStrength = 5;
		gold = startGold = 1;
		bewegung = startBewegung = 8;
		hp = hpmax;
	}

}
