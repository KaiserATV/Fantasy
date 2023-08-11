package ani.fantasyItems.useables.scroll;

import ani.fantasyItems.Item;
import ani.fantasyItems.weapons.Sword;
import ani.fantasyLebewesen.Spieler;

public class FluchSchreibfeder extends Scroll {
	public FluchSchreibfeder() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Schwerter zerstört";
	}
	
	private String name = "Fluch der Schreibfeder";
		
	private int p = 300;
	
	@Override
	public String kaufenAsString() {
		String a = "Schriftrolle 'Fluch der Schreibfeder' wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		//zerstört alle Schwerter im Umkreis (optional auch eigene, aber nicht die des Schmieds)
		for (int i=0;i<player.bag.getBag().size();i++) {
			Item z = player.bag.getBag().get(i);
			if (z instanceof Sword) {
				player.bag.getBag().remove(i);
				//System.out.println("Entferne " +z);
			}
		}
		return "Die Schriftrolle mit dem Fluch der Schreibfeder leuchtet bedrohlich!\nAlle Schwerter im Umkreis zerbrechen!";
	}

}
