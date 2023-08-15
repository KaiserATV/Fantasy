package ani.fantasyItems.equippable.jewelry;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Silberring extends Ring {
	public Silberring() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Stärke +1";
	}
	
	private String name = "Silberring";
		
	private int p = zufall.nextInt(100)+250;
	
	@Override
	public void anwenden(Lebewesen spieler) {
		spieler.setStrength(spieler.getStrength()+1);
	}

	@Override
	public String anwendenText(Lebewesen player) {
		return "Der Silberring leuchtet hoffnungsvoll! " +player.getName()+" spürt die Stärke steigen!";
	}

	@Override
	public void remove(Spieler player) {
		player.setStrength(player.getStrength()-1);
	}

}
