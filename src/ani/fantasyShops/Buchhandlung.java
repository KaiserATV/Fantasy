package ani.fantasyShops;

import java.util.Random;
import ani.fantasyItems.useables.scroll.*;

public class Buchhandlung extends Shops {
	Random zufall = new Random();
	int n = zufall.nextInt(10)+1;
	
	
		@Override
		public String getName() {
			return "Buchhandlung";
		}
	

	public void create() {
		wareBuch();
	}
	
	public void wareBuch() {
		for(int i=0;i<n;i++) {
			switch(zufall.nextInt(3)) {
			case 0:
				AugenHypnos augen = new AugenHypnos();
				inventar.add(augen);
				break;
			case 1:
				FluchSchreibfeder fluch = new FluchSchreibfeder();
				inventar.add(fluch);
				break;
			case 2:
				Nebelfelder nebel = new Nebelfelder();
				inventar.add(nebel);
				break;
			default:
				System.out.println("Etwas ist schief gegangen...");
			}
		}
	}	
	
	
	public void print() {
		System.out.println(inventar);
	}

	
//	@Override
//	public void kaufen(Spieler player, Item item) { 
//		item.kaufenAsString();
//		int i = Arrays.asList(inventar).indexOf(item);
//		//Objekt umpacken
//		Scroll b = inventar.get(i);
//		player.getBag().add(b);
//		inventar.remove(i);
//		//Bezahlvorgang
//		int gold = player.getGold();
//		gold = gold - b.getPrice();
//		player.setGold(gold);
//	}
	
	
	

}
