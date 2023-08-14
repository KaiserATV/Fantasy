package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;

public class Elf extends Spieler {

	public Elf(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = startHpMax = 45;
		strength = startStrength = 7;
		gold = startGold = 150;
		bewegung = startBewegung = 12;
		hp = hpmax;
	}

}
