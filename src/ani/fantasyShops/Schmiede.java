package ani.fantasyShops;

import java.util.Random;


import ani.fantasyItems.weapons.*;
import ani.fantasyItems.schmiedegut.*;

public class Schmiede extends Shops {
	Random zufall = new Random();
	int n = zufall.nextInt(10)+1;
	
	@Override
	public String getName() {
		return "Schmiede";
	}

	public void create() {
		wareSchmied();
	}
	
	public void wareSchmied() {
		for(int i=0;i<n;i++) {
			switch(zufall.nextInt(3)) {
			case 0:
				Sword schwert = new Sword(); 
				inventar.add(schwert);
				break;
			case 1:
				Bow bogen =new Bow();
				inventar.add(bogen);
				break;
			case 2:
				Armor armor = new Armor();
				inventar.add(armor);
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
//		Schmiedegut s = inventar.get(i); //zwei Methoden mit Armory und weapons?
//		player.getBag().add(s);
//		inventar.remove(i);
//		//Bezahlvorgang
//		int gold = player.getGold();
//		gold = gold - s.getPrice();
//		player.setGold(gold);
//	}
	
}


