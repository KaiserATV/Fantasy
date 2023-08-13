package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.Spieler;

public class KetteMacht extends Necklace {
	public KetteMacht() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Macht hoch";
	}
	
	private String name = "Kette der Macht";

	private int p = 350;
	
	@Override
	public String kaufenAsString() {
		String a = "Die Kette der Macht wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		// Waffe macht mehr Schaden bzw. Angriffsbonus?
		player.setMacht(+2);
		return "Die Kette der Macht verleiht dir noch mehr Kraft";
	}

}
