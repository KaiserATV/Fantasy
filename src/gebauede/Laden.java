package gebauede;

import java.util.*;

import gegenstaende.Gegenstand;
import lebewesen.NPC;
import lebewesen.Spieler;

public abstract class Laden implements kaufMenu{
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
	public void betreten(Spieler ich) {
		trennen();
		ich.status();
		trennen();
		System.out.println("\nDu betrittst das Geschaeft und siehst: ");
		ausgabe();
		trennen();
		System.out.println("\nWillst du etwas kaufen?(y/n)");
		Scanner input = new Scanner(System.in);
		String antwort = input.next();
		while(antwort.toUpperCase().charAt(0) == 'Y') {
			System.out.println("Nummer des Gegenstands?");
			int h = input.nextInt();
			kaufen(h, ich);
			trennen();
			System.out.println("\nDu hast noch: "+ich.getVermoegen()+".\nIn deinem Rucksack befindet sich:");
			ich.rucksackAusgabe();
			trennen();
			if(gegenstaende.isEmpty()) {
				System.out.println("In diesem Laden kannst du nichts mehr kaufen!");
				trennen();
				return;
			}
			System.out.println("Der Laden bietet nun an:");
			ausgabe();
			trennen();
			System.out.println("Willst du etwas anderes erwerben?");
			antwort = input.next();
		}
		System.out.println("Du verlaesst den Laden!");	
	}
	
	
	public void kaufen(int l, Spieler s) {
		if(l >= gegenstaende.size() || l < 0) {
			trennen();
			System.out.println("Das ist keine Valide Antwort!");
		}
		else if(gegenstaende.get(l).getPreis() <= s.getVermoegen()) {
			s.setRucksack(gegenstaende.get(l));
			s.setVermoegen(-gegenstaende.get(l).getPreis());
			this.kasse += gegenstaende.get(l).getPreis();
			aktualisieren(l);
		}else {
			trennen();
			System.out.println("Du hast leider nicht genug Geld!");
		}
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
