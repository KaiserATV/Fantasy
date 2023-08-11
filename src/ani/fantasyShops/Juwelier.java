package ani.fantasyShops;

import java.util.Random;

import ani.fantasyItems.useables.jewelry.*;

public class Juwelier extends Shops {
	Random zufall = new Random();
	int n = zufall.nextInt(10)+1;
	
		@Override
		public String getName() {
			return "Juwelier";
		}

	public void create() {
		wareJuwel();
		Goldring goldring = new Goldring();
		Silberring silberring = new Silberring();
		KetteMacht macht = new KetteMacht();		
		GeschmeideUnsterb geschmeide = new GeschmeideUnsterb();
	}
	
	public void wareJuwel() {
		for(int i=0;i<n;i++) {
			switch(zufall.nextInt(4)) {
			case 0:
				Goldring goldring = new Goldring();
				inventar.add(goldring);
				break;
			case 1:
				Silberring silberring = new Silberring();
				inventar.add(silberring);
				break;
			case 2:
				KetteMacht macht = new KetteMacht();
				inventar.add(macht);
				break;
			case 3:
				GeschmeideUnsterb geschmeide = new GeschmeideUnsterb();
				inventar.add(geschmeide);
				break;
			default:
				System.out.println("Etwas ist schief gegangen...");
			}		
		}
	}	
	
	
//	@Override
//	public void kaufen(Spieler player, Item item) { 
//		item.kaufenAsString();
//		int i = Arrays.asList(inventar).indexOf(item);
//		//Objekt umpacken
//		inventarry j = inventar.get(i);
//		player.getBag().add(j);
//		inventar.remove(i); 			// oder ketten.remove(0)
//		//Bezahlvorgang
//		int gold = player.getGold();
//		gold = gold - j.getPrice();
//		player.setGold(gold);
//	}

	
	

}
