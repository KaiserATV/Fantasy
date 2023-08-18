package ani.fantasyShops;


import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.equippable.jewelry.*;

public class Juwelier extends Shops {
		public Juwelier(Point position) throws IOException {
			super(position);
			wareJuwel();
			hintergrund = ImageIO.read(new File("src/img/shops/juwelierBild_pixl.png"));
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
	

}
