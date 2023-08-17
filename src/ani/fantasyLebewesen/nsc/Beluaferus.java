package ani.fantasyLebewesen.nsc;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.Item;
import ani.fantasyItems.weapons.Bow;
import ani.fantasyItems.weapons.Sword;
import ani.fantasyItems.weapons.Weapons;


public class Beluaferus extends Monster {
	public Beluaferus(){
		name = "Beluaferus";
		hpmax = 21;
		hp = hpmax;
		strength = zufall.nextInt(4)+5;
		try {
			spielerGesamt = ImageIO.read(new File("src/img/Beluaferus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Item lootExtra() {
		int i=zufall.nextInt(100);
		if(i < 13) {
			if(i <8) {
				return new Sword();
			}else {
				return new Bow();
			}
		}
		return null;
	}
	
	
					
}
