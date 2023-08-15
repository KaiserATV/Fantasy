package ani.fantasyItems.useables.consumables.food;

import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;

public class VeggiePan extends Food {
	public VeggiePan() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+20 HP";
	}
	
	private String name = "Veggie Pan";

	private int p = 50;
	
	
	@Override
	public void anwenden(Lebewesen player) {
		//Hp werden aufgefüllt (20?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 20;
		player.setHp(hpNeu);
	}

	@Override
	public String anwendenText(Lebewesen spieler) {
		return "Das Gemüse gibt "+spieler.getName()+ "ein warmes und gutes Gefühl." +spieler.getName()+" fühlt sich erholt";
	}

}
