package ani.fantasyItems.useables.scroll;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class FluchSchreibfeder extends Scroll {
	public FluchSchreibfeder() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Schwerter zerstört";
	}
	
	private String name = "Fluch der Schreibfeder";
		
	private int p = zufall.nextInt(100)+1000;


	@Override
	public void anwenden(Spieler ich, Lebewesen gegen) {
		ich.removeWaffe();
		if(gegen instanceof Spieler) {
			((Spieler)gegen).removeWaffe();
		}
	
	}


	@Override
	public String anwendenText(Lebewesen spieler) {
		return "Ein seltsames Geräusch zieht alle ihn seinen Bann...";
	}

}
