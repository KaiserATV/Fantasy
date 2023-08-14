package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;

public class Zaubererin extends Spieler {

	public Zaubererin(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = startHpMax = 30;
		strength = startStrength = 14;
		gold = startGold =360;
		bewegung = startBewegung = 9;
		hp = hpmax;
	}

}
