package ani.fantasyShops;


import ani.fantasyItems.useables.jewelry.*;

public class Juwelier extends Shops {
		public Juwelier() {
			super();
			wareJuwel();
		}
	
		@Override
		public String getName() {
			return "Juwelier";
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
