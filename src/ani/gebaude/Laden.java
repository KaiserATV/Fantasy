package ani.gebaude;

import java.util.*;

import ani.gegenstaende.Gegenstand;
import ani.lebewesen.NPC;
import ani.lebewesen.Spieler;

public abstract class Laden{
	Laden(){
	}
	
	protected List<Gegenstand> gegenstaende = new LinkedList<Gegenstand>();
	
	public List<Gegenstand> getGegenstaende() {
		return gegenstaende;
	}
	
	public void ausgabe() {
		int h = 0;
		for(Gegenstand i:gegenstaende) {
			System.out.println(h+":"+i.toString());
			h++;
		}
		System.out.println("Kassenstand: "+kasse);
	}
	
	
	public String getNamen() {
		return namen;
	}
	
	public void kaufenNPC(int g, NPC n) {
		if(g >= gegenstaende.size() || g < 0) {
			trennen();
			System.out.println("Das ist keine Valide Antwort!");
		}
		else{
			n.setRucksack(gegenstaende.get(g));
		}
	}
	public static void trennen() {
		System.out.println("=============================================");
	}
	public void aktualisieren(int pos) {
		gegenstaende.remove(pos);
	}
	public int getKasse() {
		return kasse;
	}public void setKasse(int i) {
		kasse = i;
	}public void setKasseAdd(int i) {
		kasse += i;
	}
	
	protected String namen;
	protected int kasse;
	
	
}
