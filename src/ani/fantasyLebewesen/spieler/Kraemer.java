package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;

public class Kraemer extends Spieler {

	public Kraemer(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = startHpMax = 100;
		strength = startStrength = 50;
		gold = startGold = 2000;
		bewegung = startBewegung = 15;
		hp = hpmax;
	}

}
