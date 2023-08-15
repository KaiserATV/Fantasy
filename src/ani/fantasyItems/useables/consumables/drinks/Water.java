package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Water extends Drinks {
	public Water() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+5 HP";
	}
	
	private String name = "Water";
	
	private int p = 10;
	
	
	@Override
	public void anwenden(Lebewesen player) {
		//Hp werden aufgefüllt (5?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 5;
		player.setHp(hpNeu);
		
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" befeuchtest sich die Kehle mit einem Glas Wasser und fühlt sich erholt!";
	}

	
}
