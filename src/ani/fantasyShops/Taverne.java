package ani.fantasyShops;


import ani.fantasyItems.useables.consumables.food.*;
import ani.fantasyItems.useables.consumables.drinks.*;

public class Taverne extends Shops {
		public Taverne() {
			super();
			wareTaverne();	
		}
		
		
		@Override
		public String getName() {
			return "Taverne";
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
	
//	public String ausruhen(Spieler player) {
//		//Hp werden aufgefüllt (komplett?)
//				player.setHp(player.getHpmax());
//				//sollte das Geld kosten???
//				return "Du machst eine Rast und erholst dich gut\nDu fühlst dich erholt";
//				//Runde beenden
//	}

}
