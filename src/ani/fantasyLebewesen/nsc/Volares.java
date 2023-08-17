package ani.fantasyLebewesen.nsc;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.Item;
import ani.fantasyItems.equippable.jewelry.GeschmeideUnsterb;
import ani.fantasyItems.equippable.jewelry.Goldring;
import ani.fantasyItems.equippable.jewelry.KetteMacht;
import ani.fantasyItems.equippable.jewelry.Silberring;
import ani.fantasyItems.useables.scroll.AugenHypnos;
import ani.fantasyItems.useables.scroll.FluchSchreibfeder;
import ani.fantasyItems.useables.scroll.Nebelfelder;

public class Volares extends Monster {
	public Volares(){
		name = "Volares";
		hpmax = 15;
		hp = hpmax;
		strength = zufall.nextInt(3)+8;
		try {
			spielerGesamt = ImageIO.read(new File("src/img/Volares.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public Item lootExtra() {
		int i=zufall.nextInt(100);
		if(i <8) {
			if(i%7 == 0) {
				return new GeschmeideUnsterb();
			}else if(i%7 == 1){
				return new Goldring();
			}else if(i%7 == 2){
				return new KetteMacht();
			}else if(i%7 == 3){
				return new Silberring();
			}else if(i%7 == 4){
				return new AugenHypnos();
			}else if(i%7 == 5){
				return new FluchSchreibfeder();
			}else if(i%7 == 6){
				return new Nebelfelder();
			}
		}
		return null;
	}
	

	
}
