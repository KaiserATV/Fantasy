package ani.fantasyItems.schmiedegut;

import ani.fantasyLebewesen.Spieler;

public class Armor extends Armory {
	public Armor() {
		this.setName(name);
		this.setPrice(p);
	}
	
	private String name = "Armor";

	private int p = 500;

	@Override
	public String kaufenAsString() {
		String a = "Rüstung wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) { //getragene Rüstung wird ggf. einfach durch neue ersetzt
	 // bzw. ausrüsten    //soll erhaltenen Schaden um 2 reduzieren
		player.setArmored(true);
		player.setSchutz(2);
		/*
		 if(player.isArmored() == false) {
		 	player.setArmored(true);
			player.setSchutz(2);
			String a = player.getName() + " trägt nun Rüstung";
			return a;
		}
		else {
			String a = player.getName() + " trägt bereits Rüstung";
			return a;
		}
		*/
		String a = player.getName() + " trägt nun Rüstung";
		return a;
	}
}
