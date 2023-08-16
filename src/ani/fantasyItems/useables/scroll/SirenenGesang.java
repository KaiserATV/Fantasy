package ani.fantasyItems.useables.scroll;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;

public class SirenenGesang extends Scroll {

	public SirenenGesang() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Monsterzähmung";
	}
	private String name = "Sirenengesang";
	
	private int p = zufall.nextInt(100)+250;

	@Override
	public void anwenden(Spieler ich, Lebewesen gegen) {
		if(gegen instanceof Monster) {
			((Monster)gegen).setState("zahm");	
		}
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" fängt an zu singen und alle Monster verfallen den Lauten.";
	}

}
