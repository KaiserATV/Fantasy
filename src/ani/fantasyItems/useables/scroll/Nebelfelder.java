package ani.fantasyItems.useables.scroll;


import ani.fantasyLebewesen.Spieler;

public class Nebelfelder extends Scroll {
	public Nebelfelder() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Unsichtbarkeit";
	}
	
	private String name = "Nebelfelder";

	private int p = 300;
	
	@Override
	public String kaufenAsString() {
		String a = "Die Schriftrolle 'Nebelfelder' wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	@Override
	public String anwenden(Spieler player) {
		//Spieler wird für x Züge unsichtbar für Gegner -> kann nicht von Monstern angegriffen werden
		player.setUnsichtbar(true);
		return "Die Schriftrolle 'Nebelfelder' macht dich kurzzeitig unsichtbar\nDu kannst nicht angegriffen werden";
	}

}
