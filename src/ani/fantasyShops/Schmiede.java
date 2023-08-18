package ani.fantasyShops;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import ani.fantasyItems.weapons.*;
import ani.fantasyItems.schmiedegut.*;

public class Schmiede extends Shops {
	Random zufall = new Random();
	public Schmiede(Point position) throws IOException {
		super(position);
		wareSchmied();
		hintergrund = ImageIO.read(new File("src/img/shops/schmiedeBild_pixl.png"));
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


