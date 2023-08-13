package ani.fantasyItems.useables.consumables.drinks;

import ani.fantasyLebewesen.Spieler;

public class Met extends Drinks {
	public Met() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="+10 HP";
	}
	
	private String name = "Met";
		
	private int p = 150;
	
	@Override
	public String kaufenAsString() {
		String a = "Ein Humpen Met wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//Hp werden aufgefüllt (10?)
		int hpAlt = player.getHp();
		int hpNeu = hpAlt + 10;
		player.setHp(hpNeu);
		//player.setState("beschwippst"); ???
		return "Du trinkst einen Humpen Met\nDu fühlst dich erholt\nHicks!";
	}

}
