package ani.fantasyItems.useables.scroll;


import ani.fantasyLebewesen.Lebewesen;
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
	public void anwenden(Lebewesen spieler) {
		((Spieler)spieler).setArmor(null);
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return "Eine Stimme ertönt und alle fühlen sich auf magische Art und Weise leichter...";
	}

}
