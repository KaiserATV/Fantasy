package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Steak extends Food {
	public Steak() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+25 HP";
	}
	
	private String name = "Steak";

	private int p = 60;
	
	@Override
	public void anwenden(Lebewesen player) {
		//Hp werden aufgefüllt (25?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 25;
		player.setHp(hpNeu);
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" genießt die Mahlzeit und fühlt sich erholt!";
	}


}
