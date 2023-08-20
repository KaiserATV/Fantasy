package ani.fantasyShops;

import java.awt.Point;
import ani.fantasyItems.weapons.*;
import ani.fantasyItems.schmiedegut.*;

public class Schmiede extends Shops {
	public Schmiede(Point position) {
		super(position);
		wareSchmied();
	}
	
	@Override
	public String getName() {
		return "Schmiede";
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
	


	
}


