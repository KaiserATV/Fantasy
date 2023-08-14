package ani.fantasyItems.schmiedegut;


public class Armor extends Armory {
	public Armor() {
		negAttr = new String[]{"löchrige","seltsame","alte"};
		neutralAttr = new String[]{"vernünftige","gewöhnliche","normale"};
		posAttr = new String[]{"polierte","standhafte","sichere"};
		this.setName(bestimmeAttribute()+" Rüstung");
		this.schutz = zufall.nextInt(5)+1;
		this.setPrice(zufall.nextInt(100)+400);
	}	
	public int getSchutz () {
		return schutz;
	}
	
	
}
