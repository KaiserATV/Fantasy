package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.Spieler;

public class Water extends Drinks {
	public Water() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+5 HP";
	}
	
	private String name = "Water";
	
	private int p =15;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Glas Wasser wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (5?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 5;
		player.setHp(hpNeu);
		return "Du befeuchtest deine Kehle mit einem Glas Wasser\nDu fühlst dich erholt";
	}
}
