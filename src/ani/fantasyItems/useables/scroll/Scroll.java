package ani.fantasyItems.useables.scroll;

import ani.fantasyItems.useables.Useables;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public abstract class Scroll extends Useables {
	public Scroll() {
		this.setName("Schriftrolle");
	}
	@Override
	public void anwenden(Lebewesen l) {};
	public abstract void anwenden(Spieler ich, Lebewesen gegen);
}
