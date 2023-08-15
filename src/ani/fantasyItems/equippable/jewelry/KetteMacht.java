package ani.fantasyItems.equippable.jewelry;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class KetteMacht extends Necklace {
	public KetteMacht() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Macht hoch";
	}
	
	private String name = "Kette der Macht";

	private int p = zufall.nextInt(100)+400;
	
	@Override
	public void anwenden(Lebewesen spieler) {
		// Waffe macht mehr Schaden bzw. Angriffsbonus?
		((Spieler) spieler).setMacht(2);
	}

	@Override
	public String anwendenText(Lebewesen player) {
		return "Die Kette der Macht verleiht dir noch mehr Kraft!";
	}

	@Override
	public void remove(Spieler player) {
		player.setMacht(0);
	}



}
