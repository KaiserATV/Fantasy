package ani.fantasyItems.weapons;


public class Sword extends Weapons { 
	
	public Sword() {
		negAttr = new String[]{"zerbrechliches","stumpfes","zerkratztes"};
		neutralAttr = new String[]{"akzeptables","gewöhnliches","uninteressantes"};
		posAttr = new String[]{"ausergewöhnliches","scharfes","leuchtendes"};
		this.strength = zufall.nextInt(3)+1;
		this.setPrice(zufall.nextInt(100)+200);
		this.setName(bestimmeAttribute()+" Schwert");
	}
	
}
