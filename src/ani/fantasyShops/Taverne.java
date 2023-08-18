package ani.fantasyShops;


import ani.fantasyItems.useables.consumables.food.*;
import ani.fantasyLebewesen.spieler.Spieler;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.useables.consumables.drinks.*;

public class Taverne extends Shops {
		public Taverne(Point position) throws IOException {
			super(position);
			wareTaverne();	
			hintergrund = ImageIO.read(new File("src/img/shops/taverneBild.png"));
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

}
