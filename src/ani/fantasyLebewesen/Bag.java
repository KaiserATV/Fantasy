package ani.fantasyLebewesen;

import java.util.ArrayList;
import java.util.List;

import ani.fantasyItems.Item;

public class Bag {
	
	private List<Item> bag = new ArrayList<Item>();
	public List<Item> getBag() {
		return bag;
	}
	public void addBag(Item i) {
		bag.add(i);
	}
	public void removeBag(Item i) {
		bag.remove(i);
	}
	public void setBag(List<Item> bag) {
		this.bag = bag;
	}
	public Item get(Item i){
		return bag.get(bag.indexOf(i));
	}

}
