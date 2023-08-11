package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.Spieler;

public class Salad extends Food {
	public Salad() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+12 HP";
	}
	
	private String name = "Salad";

	private int p = 100;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Teller Salat wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (12?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 12;
		player.setHp(hpNeu);
		return "Du isst einen Teller Salat\nDu fühlst dich erholt";
	}

}
