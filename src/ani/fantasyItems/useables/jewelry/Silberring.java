package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.spieler.Spieler;

public class Silberring extends Ring {
	public Silberring() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Stärke hoch";
	}
	
	private String name = "Silberring";
		
	private int p = zufall.nextInt(100)+250;
	
	@Override
	public String anwenden(Spieler player) {
		int neu = player.getStrength();
		neu++;
		player.setStrength(neu);
		return "Der Silberring leuchtet hoffnungsvoll!\nDu spürst deine Stärke steigen";
	}

}
