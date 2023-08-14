package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.Spieler;

public class Goldring extends Ring {
	public Goldring() {
		this.setName(name);
		this.setPrice(p);
	}
	
	private String name = "Goldring";
		
	private int p = 500;
	
	@Override
	public String kaufenAsString() {
		String a = name +" wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	//zähm = loot und nicht kämpfen
	
	
	@Override
	public String anwenden(Monster monster, Spieler player) { // Begrenzung durch Fingeranzahl? -> ausrüsten nur wenn noch ein Finger frei?
		boolean nurEinMal = true;
		while (monster.getMonsteranzahl() >=1 && nurEinMal == true) {
			//monster.setZahm(true);		// stattdessen neues Objekt Pet erzeugen?
			monster.setState("zahm");
			player.addPet(monster);
			int neu = monster.getMonsteranzahl();
			neu--;
			monster.setMonsteranzahl(neu);
			nurEinMal = false;
		}
		return "Der Goldring schimmert verheißungsvoll!\nDu zähmst ein Monster";
	}

}
