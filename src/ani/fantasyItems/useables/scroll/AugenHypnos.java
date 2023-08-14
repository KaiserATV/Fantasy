package ani.fantasyItems.useables.scroll;

import ani.fantasyLebewesen.nsc.Monster;

public class AugenHypnos extends Scroll {
	public AugenHypnos() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Monsterlähmung";
	}
	
	private String name = "Augen des Hypnos";

	private int p = zufall.nextInt(100)+200;
	
	
	//lähmrolle
	
//	@Override
//	public String anwenden(Monster monster) {
//		// lähmt alle Monster im Umkreis
//		boolean nurEinMal = true;		//könnte auch mit status bzw. mangriff gehen? monster.getMangriff == true
//		while (monster.getMonsteranzahl() >= 1 && nurEinMal == true) {
//			monster.setMangriff(false); // zustand wird verändert...
//			monster.setState("gelähmt");
//			nurEinMal = false;
//		}
//		return "Die Augen des Hypnos beginnen zu glühen:\nAlle Monster im Umkreis werden gelähmt!";
//	}
}
