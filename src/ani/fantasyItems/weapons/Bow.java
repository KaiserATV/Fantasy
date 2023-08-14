package ani.fantasyItems.weapons;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.Spieler;

public class Bow extends Weapons {
	public Bow() {
		this.setName(name);
		this.setPrice(p);
	}
	
	private String name = "Bow";
		
	private int p = 700;

	@Override
	public String kaufenAsString() {
		String a = "Ein Bogen wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	
}
