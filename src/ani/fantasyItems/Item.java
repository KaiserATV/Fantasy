package ani.fantasyItems;

import java.util.Random;

import ani.fantasyItems.useables.Useables;
import ani.fantasyItems.weapons.Bow;
import ani.fantasyItems.weapons.Sword;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;

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
	
		public int getPrice() {
			return price;
		}
	
	
		public void setPrice(int price) {
			this.price = price;
		}
		
	private String name;
		
		
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
	
	public String anwenden(Spieler player) {
		return null;
	}
	
	public String anwenden(Monster monster) {
		return null;
	}
	
	public String anwenden(Monster monster, Spieler player) {
		return null;
	}

	public String kaufenAsString() {
		return null;
	}
	
	protected String effekt;
	public String getEffekt() {
		if(this instanceof Useables) {
			return effekt;	
		}else {
			return "none";
		}
		
	}
	protected String bestimmeAttribute() {
		int best = zufall.nextInt(3);
		int pos = zufall.nextInt(3);
		if(best == 0) {
			if(this instanceof Bow) {
				strength -= 2;	
			}else if(this instanceof Sword) {
				strength -= 1;
			}else {
				schutz -= 1;
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
