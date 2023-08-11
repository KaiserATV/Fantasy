package max.sys;

import ani.fantasyShops.Shops;
import ani.fantasyItems.Item;
import ani.fantasyLebewesen.Spieler;

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
		String[][] inventar = new String[laden.getInv().size()][3];
		int i=0;
		for( Item g: laden.getInv()) {
			inventar[i][0] = g.getName();
			inventar[i][1]=  g.getEffekt();
			inventar[i][2]= String.valueOf(g.getPrice());
			i++;
		}
		return inventar;
	}
	
	public void kaufen(int i) {
		int h = laden.getInv().get(i).getPrice();
		if(ich.getGold()>h && i < laden.getInv().size()) {
			ich.bag.addBag(laden.getInv().get(i)); 
			ich.setGold(ich.getGold()-h);
			laden.removeInv(i);
		}
	}
	public boolean kannKaufen(int i) {
		if(laden.getInv().size()==0) {
			return false;
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
