package ani.fantasyItems.weapons;

import ani.fantasyItems.schmiedegut.Schmiedegut;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public abstract class Weapons extends Schmiedegut {
	public Weapons() {
	}
	public int getStrength() {
		return strength;
	}
	@Override
	public void anwenden(Lebewesen spieler) {
		spieler.setStrength(spieler.getStrength()+strength);
	}
	public String anwendenText(Lebewesen spieler) {
		return spieler.getName()+" legt "+name+" an!";
	}
	public void remove(Spieler spieler) {
		spieler.setStrength(spieler.getStrength()-strength);
	}
}
