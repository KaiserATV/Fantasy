package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.Lebewesen;

public class Met extends Drinks {
	public Met() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+10 HP";
	}
	
	private String name = "Met";
		
	private int p = 20;
	
	
	@Override
	public void anwenden(Lebewesen player) {
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 10;
		player.setHp(hpNeu);
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" trinkt einen Humpen Met und fühlt sich erholt... Hicks!";
	}


}
