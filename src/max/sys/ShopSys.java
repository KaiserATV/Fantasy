package max.sys;

import ani.fantasyShops.Shops;
import ani.fantasyShops.Taverne;
import ani.fantasyItems.Item;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.weapons.Weapons;
import ani.fantasyLebewesen.spieler.Spieler;

public class ShopSys extends VorlageSys{

	public ShopSys(Spieler i, Shops s) {
		ich = i;
		laden = s;
	}
	//Kauft ein gegenstand im Laden
	public boolean kaufen(int i) {
		if(laden instanceof Taverne && i == laden.getInv().size()) {
			ich.setHp(ich.getHpMax());
			return false;
		}else if(ich.getGold()>laden.getInv().get(i).getPrice() && i < laden.getInv().size()) {
			gekauft = laden.getInv().get(i);
			ich.bag.addBag(gekauft); 
			ich.setGold(ich.getGold()-gekauft.getPrice());
			laden.removeInv(i);
			return true;
		}
		return true;
	}
	
	//Getter - Setter
	
	//returned das Inventar des Ladens
	public String[][] getInventar() {
		String[][] inventar;
		if(!(laden instanceof Taverne)) {
			inventar = new String[laden.getInv().size()][3];
		}else {
			inventar = new String[laden.getInv().size()+1][3];
			inventar[laden.getInv().size()][0]= "Ausruhen";
			inventar[laden.getInv().size()][1]= "Stellt Leben wieder her";
			inventar[laden.getInv().size()][2]= "Beendet Zug";
		}
		int i=0;
		for( Item g: laden.getInv()) {
			inventar[i][0] = g.getName();
			if(g instanceof Weapons) {
				inventar[i][1]=  String.valueOf(((Weapons)g).getStrength())+" Stärke";	
			}else if(g instanceof Armor) {
				inventar[i][1]=  String.valueOf(((Armor)g).getSchutz())+" Schutz";
			}else {
				inventar[i][1]=g.getEffekt();
			}
			inventar[i][2]= String.valueOf(g.getPrice());
			i++;
		}
		return inventar;
	}
	
	//ob item gekauft werden kann
	public boolean kannKaufen(int i) {
		if(laden.getInv().size()==0) {
			return false;
		}
	
		if(laden instanceof Taverne && i == laden.getInv().size()) {
			return true;
		}
		int h = laden.getInv().get(i).getPrice();
		if(ich.getGold()>h && i < laden.getInv().size()) {
			return true;	
		}
		return false;
	}
	
	public String getSpielerName() {
		return ich.getName();
	}
	public String getLadenName() {
		return laden.getName();
	}
	
	public int getGekauftPreis() {
		return gekauft.getPrice();
	}
	public String getGekauftNamen() {
		return gekauft.getName();
	}
	public int getSpielerVermoegen() {
		return ich.getGold();
	}
	
	private Shops laden;
	
}
