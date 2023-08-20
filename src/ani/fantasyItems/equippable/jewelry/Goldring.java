package ani.fantasyItems.equippable.jewelry;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Goldring extends Ring {
	public Goldring() {
		this.setName(name);
		this.setPrice(p);
		this.effekt = "Stärke +2";
	}
	
	private String name = "Goldring";
		
	private int p = zufall.nextInt(100)+200;

	@Override
	public String anwendenText(Lebewesen player) {
		return player.getName()+" legt einen güldenen Ring and und fühlt sich plötzlich viel stärker!";
	}

	@Override
	public void anwenden(Lebewesen spieler) {
		spieler.setStrength(spieler.getStrength()+2);
		
	}

	@Override
	public void remove(Spieler player) {
		player.setStrength(player.getStrength()-2);
	}
	
	
	//zähm = loot und nicht kämpfen
	
	

}
