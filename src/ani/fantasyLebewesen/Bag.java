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
	public void removeBag(int index) {
		bag.remove(index);
	}
	public void setBag(List<Item> bag) {
		this.bag = bag;
	}
	public void printBag() {
		for (Item i: bag) {
			System.out.println(i);
		}
		System.out.println();
	}
	public Item get(Item i){
		return bag.get(bag.indexOf(i));
	}

}
