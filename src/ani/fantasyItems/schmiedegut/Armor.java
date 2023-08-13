package ani.fantasyItems.schmiedegut;

import java.util.Random;

import ani.fantasyLebewesen.Spieler;

public class Armor extends Armory {
	public Armor() {
		this.setName(name);
		this.setPrice(p);
	}
	
	private String name = "Armor";

	private int p = 500;

	private int schutz = new Random().nextInt(5)+1;
	
	public int getSchutz () {
		return schutz;
	}
	
	
}
