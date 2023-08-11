package ani.fantasyShops;

import java.util.Random;

import ani.fantasyItems.Item;
import ani.fantasyItems.useables.scroll.*;
import ani.fantasyItems.useables.jewelry.*;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.weapons.*;
import ani.fantasyItems.useables.consumables.drinks.*;
import ani.fantasyItems.useables.consumables.food.*;

import java.util.ArrayList;
import java.util.List;

public class TravelingMerchant extends Shops {
		Random zufall = new Random();
		
	// hat eine Auswahl der Waren von anderen Händlern
		// eine Schriftrolle
		// ein Schmuckstück
		// eine Waffe oder Rüstung
		// ein Lebensmittel
	// Auswahl wird zufällig bestimmt
	private List<Item> karren = new ArrayList<Item>();
	
		@Override
		public List<Item> getInv() {
			return karren;
		}
		@Override
		public String getName() {
			return "Reisender Händler";
		}
	
		public void setKarren(List<Item> karren) {
			this.karren = karren;
		}
		
	public void create() {
		wareBuch();
		wareJuwel();
		wareSchmied();
		wareTaverne();			
	}
	
	public void print() {
		for (Item i: karren) {
			System.out.println(i);
		}
	}
	
	public void wareBuch() {
		switch(zufall.nextInt(3)) {
		case 0:
			AugenHypnos augen = new AugenHypnos();
			karren.add(augen);
			break;
		case 1:
			FluchSchreibfeder fluch = new FluchSchreibfeder();
			karren.add(fluch);
			break;
		case 2:
			Nebelfelder nebel = new Nebelfelder();
			karren.add(nebel);
			break;
		default:
			System.out.println("Etwas ist schief gegangen...");
		}
	}
	
	public void wareJuwel() {
		switch(zufall.nextInt(4)) {
		case 0:
			Goldring goldring = new Goldring();
			karren.add(goldring);
			break;
		case 1:
			Silberring silberring = new Silberring();
			karren.add(silberring);
			break;
		case 2:
			KetteMacht macht = new KetteMacht();
			karren.add(macht);
			break;
		case 3:
			GeschmeideUnsterb geschmeide = new GeschmeideUnsterb();
			karren.add(geschmeide);
			break;
		default:
			System.out.println("Etwas ist schief gegangen...");
		}		
	}

	public void wareSchmied() {
		switch(zufall.nextInt(3)) {
		case 0:
			Sword schwert = new Sword(); 
			karren.add(schwert);
			break;
		case 1:
			Bow bogen =new Bow();
			karren.add(bogen);
			break;
		case 2:
			Armor rüstung = new Armor();
			karren.add(rüstung);
			break;
		default:
			System.out.println("Etwas ist schief gegangen...");
		}
	}
	
	public void wareTaverne() {
		switch(zufall.nextInt(6)) {
		case 0:
			Water wasser = new Water();
			karren.add(wasser);
			break;
		case 1:
			Juice saft = new Juice();
			karren.add(saft);
			break;
		case 2:
			Met met = new Met();
			karren.add(met);
			break;
		case 3:
			Salad salat = new Salad();
			karren.add(salat);
			break;
		case 4:
			VeggiePan gemüse = new VeggiePan();
			karren.add(gemüse);
			break;
		case 5:
			Steak steak = new Steak();
			karren.add(steak);
			break;
		default:
			System.out.println("Etwas ist schief gegangen...");
		}
	}
	
//	@Override
//	public void kaufen(Spieler player, Item item) { 
//		item.kaufenAsString();
//		int i = Arrays.asList(karren).indexOf(item);
//		//Objekt umpacken
//		item = karren.get(i);
//		player.getBag().add(item);
//		karren.remove(i);
//		//Bezahlvorgang
//		int gold = player.getGold();
//		gold = gold - item.getPrice();
//		player.setGold(gold);
//		//potenzielle Ausgabe zu Vorgang
//	}
}
