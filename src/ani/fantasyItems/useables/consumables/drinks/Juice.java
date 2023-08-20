package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.Lebewesen;

public class Juice extends Drinks {
	public Juice() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+8 HP";
	}
	
	private String name = "Juice";

	private int p = 15;
	
	@Override
	public void anwenden(Lebewesen player) {
		//Hp werden aufgefüllt (8?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 8;
		player.setHp(hpNeu);
	}

	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" trinkt ein Glas Saft und fühlt sich erholt";
	}
}
