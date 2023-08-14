package max.sys;

import ani.fantasyShops.Shops;
import ani.fantasyShops.Taverne;
import fabio.spielerbewegung.*;
import ani.fantasyItems.Item;
import ani.fantasyLebewesen.spieler.Spieler;

public class ShopSys {

	public ShopSys(Spieler i, Shops s) {
		ich = i;
		laden = s;
	}
	public String getSpielerName() {
		return ich.getName();
	}
	public String getLadenName() {
		return laden.getName();
	}
	public String[][] getInventar() {
		String[][] inventar;
		if(!(laden instanceof Taverne)) {
			inventar = new String[laden.getInv().size()][3];
		}else {
			inventar = new String[laden.getInv().size()+1][3];
			inventar[laden.getInv().size()][0] = "Ausruhen";
			inventar[laden.getInv().size()][1]=  "Stellt Leben wieder her";
			inventar[laden.getInv().size()][2]= "Beendet Zug";
		}
		int i=0;
		for( Item g: laden.getInv()) {
			inventar[i][0] = g.getName();
			inventar[i][1]=  g.getEffekt();
			inventar[i][2]= String.valueOf(g.getPrice());
			i++;
		}
		return inventar;
	}
	
	public boolean kaufen(int i) {
		if(laden instanceof Taverne && i == laden.getInv().size()) {
			ich.setHp(ich.getHpMax());
			return false;
		}else if(ich.getGold()>laden.getInv().get(i).getPrice() && i < laden.getInv().size()) {
			ich.bag.addBag(laden.getInv().get(i)); 
			ich.setGold(ich.getGold()-laden.getInv().get(i).getPrice());
			laden.removeInv(i);
			return true;
		}
		return true;
	}
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
	public int getItemPreis(int i) {
		return laden.getInv().get(i).getPrice();
	}
	public String getItemNamen(int i) {
		return laden.getInv().get(i).getName();
	}
	public int getSpielerVermoegen() {
		return ich.getGold();
	}
	
	
	private Spieler ich;
	private Shops laden;
}
