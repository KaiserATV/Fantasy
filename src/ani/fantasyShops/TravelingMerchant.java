package ani.fantasyShops;


import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.Item;
import ani.fantasyItems.equippable.jewelry.*;
import ani.fantasyItems.useables.scroll.*;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.weapons.*;
import ani.fantasyItems.useables.consumables.drinks.*;
import ani.fantasyItems.useables.consumables.food.*;

public class TravelingMerchant extends Shops {
	
	
	
		public TravelingMerchant(Point position) throws IOException {
			super(position);
			wareBuch();
			wareJuwel();
			wareSchmied();
			wareTaverne();	
			hintergrund = ImageIO.read(new File("src/img/shops/karrenBild_pixl.png"));
		}
		
		
		@Override
		public String getName() {
			return "Reisender Händler";
		}
	
	public void wareBuch() {
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
	
	public void wareJuwel() {
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

	public void wareSchmied() {
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
			Armor rüstung = new Armor();
			inventar.add(rüstung);
			break;
		default:
			System.out.println("Etwas ist schief gegangen...");
		}
	}
	
	public void wareTaverne() {
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
