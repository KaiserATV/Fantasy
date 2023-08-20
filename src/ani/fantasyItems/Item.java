package ani.fantasyItems;

import java.util.Random;

import ani.fantasyItems.weapons.Bow;
import ani.fantasyItems.weapons.Sword;
import ani.fantasyLebewesen.Lebewesen;

public abstract class Item {
	// Jedes Item hat Preis und Art/Namen
	// braucht Ausgabeform
	// muss gekauft und genutzt/angewendet werden
	protected static Random zufall = new Random();
	protected int strength;
	protected int schutz;
	protected String[] negAttr;
	protected String[] neutralAttr;
	protected String[] posAttr;
	private int price;
	protected String effekt;
	
		public int getPrice() {
			return price;
		}
	
	
		public void setPrice(int price) {
			this.price = price;
		}
		
	protected String name;
		
		
		public String getName() {
			return name;
		}
	
	
		public void setName(String name) {
			this.name = name;
		}
		

	@Override
	public String toString() {
		return "|" + this.name + ": " + this.price + "|";
	}
	
	/**
	 * Funktion um Items zu benutzen
	 * @param spieler auf wen das Item angewandt werden soll
	 */
	public abstract void anwenden(Lebewesen spieler);

	public abstract String anwendenText(Lebewesen spieler);
	
	
	public String getEffekt() {
			return effekt;	
	}
	
	protected String bestimmeAttribute() {
		int best = zufall.nextInt(3);
		int pos = zufall.nextInt(3);
		if(best == 0) {
			if(this instanceof Bow) {
				if(this.strength>2) {
					strength -= 2;	
				}else {
					strength = 1;
				}
					
			}else if(this instanceof Sword) {
				if(this.strength>1) {
					strength -= 1;	
				}else {
					strength = 1;
				}
				
			}else {
				if(this.schutz>1) {
					schutz -= 1;			
				}else {
					schutz =1;
				}
		
			}
			this.setPrice(this.getPrice()-100);
			return negAttr[pos];
		}else if(best == 1) {
			return neutralAttr[pos];
		}else {
			if(this instanceof Bow) {
				strength += 3;	
			}else if(this instanceof Sword){
				strength += 2;
			}else {
				schutz += 2;
			}
			this.setPrice(this.getPrice()+200);
			return posAttr[pos];
		}
	}
}
