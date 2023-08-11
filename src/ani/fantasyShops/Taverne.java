package ani.fantasyShops;

import java.util.Random;

import ani.fantasyItems.useables.consumables.food.*;
import ani.fantasyItems.useables.consumables.drinks.*;

public class Taverne extends Shops {
	Random zufall = new Random();
	int n = zufall.nextInt(10)+1;
		
		
		@Override
		public String getName() {
			return "Taverne";
		}

	public void create() {
		wareTaverne();
	}
	
	public void wareTaverne() {
		for(int i=0;i<n;i++) {
			switch(zufall.nextInt(6)) {
			case 0:
				Water wasser = new Water();
				inventar.add(wasser);
				break;
			case 1:
				Juice saft = new Juice();
				inventar.add(saft);
				break;
			case 2:
				Met met = new Met();
				inventar.add(met);
				break;
			case 3:
				Salad salat = new Salad();
				inventar.add(salat);
				break;
			case 4:
				VeggiePan gemüse = new VeggiePan();
				inventar.add(gemüse);
				break;
			case 5:
				Steak steak = new Steak();
				inventar.add(steak);
				break;
			default:
				System.out.println("Etwas ist schief gegangen...");
			}
		}
	}
	
	
	
	
		//Ich implementiere das
//	@Override
//	public void kaufen(Spieler player, Item item) { 
//		item.kaufenAsString();
//		int i = Arrays.asList(consume).indexOf(item);
//		//Objekt umpacken
//		Consumeables c = consume.get(i);
//		player.getBag().add(c);
//		consume.remove(i);
//		//Bezahlvorgang
//		int gold = player.getGold();
//		gold = gold - c.getPrice();
//		player.setGold(gold);
//	}
//	
//	public String ausruhen(Spieler player) {
//		//Hp werden aufgefüllt (komplett?)
//				player.setHp(player.getHpmax());
//				//sollte das Geld kosten???
//				return "Du machst eine Rast und erholst dich gut\nDu fühlst dich erholt";
//				//Runde beenden
//	}

}
