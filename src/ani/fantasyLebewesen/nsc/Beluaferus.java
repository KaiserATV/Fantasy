package ani.fantasyLebewesen.nsc;

import ani.fantasyItems.Item;
import ani.fantasyItems.weapons.Bow;
import ani.fantasyItems.weapons.Sword;


public class Beluaferus extends Monster {
	public Beluaferus(){
		name = "Beluaferus";
		hpmax = 21;
		hp = hpmax;
		strength = zufall.nextInt(4)+5;
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
