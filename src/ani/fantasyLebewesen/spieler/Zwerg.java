package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;

public class Zwerg extends Spieler {

	public Zwerg(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax= startHpMax = 60;
		strength = startStrength = 7;
		gold = startGold = 169;
		bewegung = startBewegung = 9;
		hp = hpmax;
	}

}
