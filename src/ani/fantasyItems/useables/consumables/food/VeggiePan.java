package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.Spieler;

public class VeggiePan extends Food {
	public VeggiePan() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+20 HP";
	}
	
	private String name = "Veggie Pan";

	private int p = 200;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Teller Gemüsepfanne wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (20?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 20;
		player.setHp(hpNeu);
		return "Das Gemüse gibt dir ein warmes und gutes Gefühl\nDu fühlst dich erholt";
	}

}
