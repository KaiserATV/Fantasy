package ani.fantasyShops;

import java.util.List;

import ani.fantasyItems.Item;



public abstract class Shops {
	public abstract String getName();
	public List<Item> getInv() {
		return inventar;
	}
	public void addInv(Item i) {
		inventar.add(i);
	}
	public void removeInv(int index) {
		inventar.remove(index);
	}
	protected List<Item> inventar;
}
