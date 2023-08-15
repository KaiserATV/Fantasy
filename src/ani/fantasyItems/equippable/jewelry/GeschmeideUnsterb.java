package ani.fantasyItems.equippable.jewelry;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class GeschmeideUnsterb extends Necklace {
	public GeschmeideUnsterb() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Unsterblichkeit";
	}
	
	private String name = "Geschmeide der Unsterblichkeit";

	private int p = 900;
	
	
	@Override
	public void anwenden(Lebewesen spieler) {
		//Leben von Spieler kann nicht auf 0 fallen
		((Spieler) spieler).setUnsterblich(true);
	}

	@Override
	public String anwendenText(Lebewesen player) {
		return player.getName()+" hat das Geschmeide der Unsterblichkeit angelegt!\n Ein warmer Lichtschimmer schirmt den Angriff ab";
	}

	@Override
	public void remove(Spieler player) {
		player.setUnsterblich(false);
	}
	
}
