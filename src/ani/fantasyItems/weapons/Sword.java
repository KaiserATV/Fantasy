package ani.fantasyItems.weapons;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.Spieler;

public class Sword extends Weapons { 
	
	public Sword() {
		this.setName(n);
		this.setPrice(p);
	}
	
	private String n = "Sword";

	private int p = 400;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Schwert wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	

}
