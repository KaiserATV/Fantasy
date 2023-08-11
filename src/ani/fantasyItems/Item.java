package ani.fantasyItems;

import ani.fantasyItems.useables.Useables;
import ani.fantasyLebewesen.Spieler;
import ani.fantasyLebewesen.nsc.Monster;

public abstract class Item {
	// Jedes Item hat Preis und Art/Namen
	// braucht Ausgabeform
	// muss gekauft und genutzt/angewendet werden
	
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
}
