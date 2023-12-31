package ani.fantasyItems.useables.scroll;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class AugenHypnos extends Scroll {
	public AugenHypnos() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Lähmung";
	}
	
	private String name = "Augen des Hypnos";

	private int p = zufall.nextInt(100)+200;

	@Override
	public void anwenden(Spieler ich, Lebewesen gegen) {
		gegen.setGelaehmt(true);
		gegen.setLaehmungsCounter(2);//anzahl der Runden die gelähmt - mind 1
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return "Die Augen von "+spieler.getName()+" beginnen zu leuchten und ziehen jeden in ihren Bann...";
	}
	
	
	
}
