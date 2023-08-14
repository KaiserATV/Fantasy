package ani.fantasyItems.weapons;


public class Bow extends Weapons {
	public Bow() {
		negAttr = new String[]{"zerbrechlicher","ausgeleiherter","alter"};
		neutralAttr = new String[]{"durchschnittlicher","gewöhnlicher","uninteressanter"};
		posAttr = new String[]{"ausergewöhnlicher","starker","strahlender"};
		this.strength = zufall.nextInt(4)+1;
		this.setPrice(zufall.nextInt(100)+300);
		this.setName(bestimmeAttribute()+" Bogen");
	}
	
}
