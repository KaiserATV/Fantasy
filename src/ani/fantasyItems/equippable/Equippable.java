package ani.fantasyItems.equippable;

import ani.fantasyItems.Item;
import ani.fantasyLebewesen.spieler.Spieler;

public abstract class Equippable extends Item {
	public abstract void remove(Spieler player);
}
