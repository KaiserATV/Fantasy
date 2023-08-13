package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.Spieler;

public class GeschmeideUnsterb extends Necklace {
	public GeschmeideUnsterb() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Unsterblichkeit";
	}
	
	private String name = "Geschmeide der Unsterblichkeit";

	private int p = 900;
	
	@Override
	public String kaufenAsString() {
		String a = name +" wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Leben von Spieler kann nicht auf 0 fallen
		player.setUnsterblich(true);
		String a = player.getName();
		String b = " hat das Geschmeide der Unsterblichkeit angelegt!\n Ein warmer Lichtschimmer schirmt den Angriff ab";
		return (a+b);
	}

}
