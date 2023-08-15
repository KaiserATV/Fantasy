package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Salad extends Food {
	public Salad() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+12 HP";
	}
	
	private String name = "Salad";

	private int p = 25;
	
	
	@Override
	public void anwenden(Lebewesen player) {
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 12;
		player.setHp(hpNeu);
		
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" isst einen Teller Salat und fühlt sich erholt!";
	}


}
