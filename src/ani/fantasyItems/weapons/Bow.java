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
	
	@Override
	public String anwenden(Monster monster, Spieler player) {
		int a = player.getStrength();
		int b = monster.getHp();
		int c = player.getMacht();
		int schaden = b - (a + c) ;
		monster.setHp(schaden);
		String d ="Du schießt mit deinem Bogen ";
		String e = String.valueOf(player.getStrength());
		String f = " Pfeile auf die Monster ab";
		return (d+e+f);	
	}
}
