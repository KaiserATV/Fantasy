package ani.fantasyItems.useables.scroll;


import ani.fantasyLebewesen.spieler.Spieler;

public class Nebelfelder extends Scroll {
	public Nebelfelder() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Unsichtbarkeit";
	}
	
	private String name = "Nebelfelder";

	private int p = zufall.nextInt(100)+1100;

	@Override
	public String anwenden(Spieler player) {
		//Spieler wird für x Züge unsichtbar für Gegner -> kann nicht von Monstern angegriffen werden
		player.setUnsichtbar(true);
		return "Die Schriftrolle 'Nebelfelder' macht dich kurzzeitig unsichtbar\nDu kannst nicht angegriffen werden";
	}

}
