package max.sys;

import ani.gebaude.Laden;
import ani.gegenstaende.Gegenstand;
import ani.lebewesen.Spieler;

public class ShopSys {

	public ShopSys(Spieler i, Laden l) {
		ich = i;
		laden = l;
	
	}
	public String getSpielerName() {
		return ich.getName();
	}
	public String getLadenName() {
		return laden.getNamen();
	}
	public String[][] getInventar() {
		String[][] inventar = new String[laden.getGegenstaende().size()][3];
		int i=0;
		for(Gegenstand g: laden.getGegenstaende()) {
			inventar[i][0] = g.getName();
			inventar[i][1]= g.getEffekt();
			inventar[i][2]= String.valueOf(g.getPreis());
			i++;
		}
		return inventar;
	}
	
	public void kaufen(int i) {
		int h = laden.getGegenstaende().get(i).getPreis();
		if(ich.getVermoegen()>h && i < laden.getGegenstaende().size()) {
			ich.setRucksack(laden.getGegenstaende().get(i));
			ich.setVermoegen(-h);
			laden.setKasseAdd(h);
			laden.aktualisieren(i);
		}
	}
	public boolean kannKaufen(int i) {
		if(laden.getGegenstaende().size()==0) {
			return false;
		}
		int h = laden.getGegenstaende().get(i).getPreis();
		if(ich.getVermoegen()>h && i < laden.getGegenstaende().size()) {
			return true;	
		}
		return false;
	}
	public int getItemPreis(int i) {
		return laden.getGegenstaende().get(i).getPreis();
	}
	public String getItemNamen(int i) {
		return laden.getGegenstaende().get(i).getName();
	}
	public int getSpielerVermoegen() {
		return ich.getVermoegen();
	}
	
	
	private Spieler ich;
	private Laden laden;

}
