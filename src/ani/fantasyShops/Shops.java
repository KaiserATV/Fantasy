package ani.fantasyShops;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import ani.fantasyItems.Item;



public abstract class Shops {
	protected static Random zufall = new Random();
	
	protected Point position;
	Shops(Point position) {
		this.position = position;
		n = zufall.nextInt(10)+1;
	}
	public abstract String getName();
	
	public ArrayList<Item> getInv() {
		return inventar;
	}
	public void addInv(Item i) {
		inventar.add(i);
	}
	public void removeInv(int index) {
		inventar.remove(index);
	}
	
	protected ArrayList<Item> inventar = new ArrayList<Item>();
	protected int n;
}
