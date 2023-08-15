package ani.fantasyItems.schmiedegut;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class Armor extends Armory {
	public Armor() {
		negAttr = new String[]{"löchrige","seltsame","alte"};
		neutralAttr = new String[]{"vernünftige","gewöhnliche","normale"};
		posAttr = new String[]{"polierte","standhafte","sichere"};
		this.setName(bestimmeAttribute()+" Rüstung");
		this.schutz = zufall.nextInt(5)+1;
		this.setPrice(zufall.nextInt(100)+400);
	}	
	public int getSchutz () {
		return schutz;
	}
	@Override
	public void anwenden(Lebewesen spieler) {
		spieler.setSchutz(schutz);
	}
	@Override
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" legt eine "+name+" Rüstung an und fühlt sich sicherer!";
	}
	public void remove(Spieler spieler) {
		spieler.setSchutz(0);
	}
	
	
}
