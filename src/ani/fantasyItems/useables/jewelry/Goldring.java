package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;

public class Goldring extends Ring {
	public Goldring() {
		this.setName(name);
		this.setPrice(p);
		this.effekt = "Zähmung";
	}
	
	private String name = "Goldring";
		
	private int p = zufall.nextInt(100)+200;
	
	//zähm = loot und nicht kämpfen
	
	

}
