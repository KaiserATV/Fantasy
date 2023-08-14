package ani.fantasyItems.useables.jewelry;

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
	public String anwenden(Spieler player) {
		// Waffe macht mehr Schaden bzw. Angriffsbonus?
		player.setMacht(+2);
		return "Die Kette der Macht verleiht dir noch mehr Kraft";
	}

}
