package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.spieler.Spieler;

public class Juice extends Drinks {
	public Juice() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+8 HP";
	}
	
	private String name = "Juice";

	private int p = 15;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Glas Saft wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (8?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 8;
		player.setHp(hpNeu);
		return "Du trinkst ein Glas Saft\nDu fühlst dich erholt";
	}

}
