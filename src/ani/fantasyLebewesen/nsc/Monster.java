package ani.fantasyLebewesen.nsc;

import java.util.Random;

import ani.fantasyItems.useables.consumables.Consumeables;
import ani.fantasyItems.useables.consumables.drinks.Juice;
import ani.fantasyItems.useables.consumables.drinks.Met;
import ani.fantasyItems.useables.consumables.drinks.Water;
import ani.fantasyItems.useables.consumables.food.Salad;
import ani.fantasyItems.useables.consumables.food.Steak;
import ani.fantasyItems.useables.consumables.food.VeggiePan;

public class Monster extends NSC {
	protected Random zufall = new Random();
	public int lootGold() {
		return zufall.nextInt(150)+150;
	}
	public Consumeables lootConsu() {
		int zahl = zufall.nextInt(100);
		if(zahl < 60) { //Wkeit das Consumeable gedroppt
			if(zahl < 45) { // Wkeit das Drink gedroppt (75%)
				if(zahl%3 ==0) { //Wkeit für welchen Typ - 33%
					return new Water();
				}else if(zahl%3==1) {
					return new Juice();
				}else {
					return new Met();
				}
			}else {
				if(zahl%3==0) {
					return new Salad();
				}else if(zahl%3==1) {
					return new Steak();
				}else {
					return new VeggiePan();
				}
			}	
		}
		return null;
	}





}
