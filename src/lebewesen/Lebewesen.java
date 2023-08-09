package lebewesen;

import java.util.LinkedList;
import java.util.List;

import gegenstaende.Bogen;
import gegenstaende.Gegenstand;
import gegenstaende.Ring;
import gegenstaende.Schriftrollen;
import gegenstaende.Schwert;

public abstract class Lebewesen {
	Lebewesen(){
		posx = 0;
		posy = 0;
		leben = 20;
	}
	//getter für den Rucksack
	public List<Gegenstand> getRucksack() {
		return rucksack;
	}
	//Fügt ein Gegenstand dem Rucksack hinzu
	/**
	 * {@summary} fügt Gegenstand dem Rucksack hinzu
	 * @param s - der Gegenstand
	 */
	
	public void setRucksack(Gegenstand s) {
		rucksack.add(s);
		if(s instanceof Ring || s instanceof Schriftrollen) {
			usable.add(s);
		}
		else if(s instanceof Schwert || s instanceof Bogen) {
			ausruesten(s);
		}
	}
	//abstracte Methode, welche es einem Spieler ermöglicht eine Waffe auszurüsten
	abstract public void ausruesten(Gegenstand g);
	//Gibt den Rucksack aus
	public void rucksackAusgabe() {
		for(Gegenstand i:rucksack) {
			System.out.println("\t-"+i.getName());
		}
	}
	//zerstoert alle Gegenstanden des angebenen Types in den jeweiligen Inventaren
	public void zerstoereTyp(String klasse) {
		for(Gegenstand i:rucksack) {
			if(i.getClass().toGenericString().equals(klasse)) {
				rucksack.remove(i);
			}
		}
	}
	
	//Getter,Setter für Position
	public int getPosX() {
		return posx;
	}public int getPosY() {
		return posy;
	}public void setPos(int x, int y) {
		posx = x;
		posy = y;
	}public double getDistanceTo(Lebewesen l) {
		double abstand = 0;
		abstand = Math.sqrt(Math.pow(this.posx-l.posx,2)+Math.pow(this.posy-l.posy,2));
		return abstand;
	}
	
	//getter ob Lewesen zähmbar ist
	public boolean getTamable(){
		return isTamable;
	}
	
	//getter,setter für Kampfstärke
	public int getKampfStaerke() {
		return kampfStaerke;
	}public void setKampfStaerke(int g) {
		kampfStaerke = g;
	}
	
	//getter für namen
	public String getName() {
		return name;
	}
	
	public int getLeben() {
		return leben;
	}
	public void lowerLeben(int i) {
		leben -= i;
	}
	public void setLeben(int i) {
		leben = i;
	}
	public List<Gegenstand> getUseable(){
		return usable;
	}
	
	
	private int leben;
	protected String[] vorname = {"Aragorn","Balin","Bilbo","Frodo","Gandalf","Legolas","Gimli","Sauron","Thorin","Smaug"};
	protected String[] nachname = {"Abbott","Umbridge","Malfoy","Dumbledore","Finnigan","Granger","Kowalski","Lovegood","Weasley","Potter"};
	protected int posx;
	protected int posy;
	protected List<Gegenstand> rucksack = new LinkedList<Gegenstand>();
	protected String name;
	protected List<Gegenstand> usable= new LinkedList<Gegenstand>();
	protected boolean isTamable;
	protected int kampfStaerke;
	protected int ausgeruestet;
}
