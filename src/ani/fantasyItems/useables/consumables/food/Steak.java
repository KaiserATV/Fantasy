package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.spieler.Spieler;

public class Steak extends Food {
	public Steak() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+25 HP";
	}
	
	private String name = "Steak";

	private int p = 60;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein saftiges Steak wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (25?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 25;
		player.setHp(hpNeu);
		return "Du genießt deine Mahlzeit.\nDu fühlst dich erholt";
	}

}
