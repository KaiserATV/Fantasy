package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ani.fantasyLebewesen.Bag;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyItems.Item;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.useables.jewelry.Jewelry;
import ani.fantasyItems.weapons.Weapons;

public abstract class Spieler extends Lebewesen {

	private Armor ausgeruesteteRuestung = null;
	private Weapons ausgeruesteteWaffe = null;
	private Jewelry ausgeruestetesBling = null;
	protected BufferedImage spielerKopf;
	private int macht = 0;
	private boolean unsichtbar;
	private boolean unsterblich; 
	
	
	// Rucksack 
	public Bag bag = new Bag();
	//Pets
//	private List<Monster> pet  = new ArrayList<Monster>();
	
	public Spieler(String n, Point position, Color color, int[] bel) {
		// Konstruktor
		super(position,color);
		belegung = bel;
		name = n;
	}
	private int[] belegung;
	/**
	 * Methode um die Belegung der Tasten des Spielers zu bekommen
	 * @return eine Array mit den Tasten in der Reihenfolge: Links - Rechts - Oben - Unten - Auswahl - Zurück
	 */
	public int[] getBelegung() {
		return belegung;
	}
	
	public int getMacht() {
		return macht;
	}

	public void setMacht(int macht) {
		this.macht = macht;
	}



	public boolean isUnsichtbar() {
		return unsichtbar;
	}
	public void setUnsichtbar(boolean unsichtbar) {
		this.unsichtbar = unsichtbar;
	}
	
	public boolean isUnsterblich() {
		return unsterblich;
	}
	public void setUnsterblich(boolean unsterblich) {
		this.unsterblich = unsterblich;
	}
	
	public Weapons getWaffe() {
		return ausgeruesteteWaffe;
	}
	public Weapons setWaffe() {
		return ausgeruesteteWaffe;
	}
	
	public Jewelry getBling() {
		return ausgeruestetesBling;
	}
	public Jewelry setBling() {
		return ausgeruestetesBling;
	}	
	
	public Armor getAusrüstenRüstung() {
		return ausgeruesteteRuestung;
	}
	public void setAusrüstenRüstung(Item item) {
		ausgeruesteteRuestung = (Armor) item;
		schutz = ausgeruesteteRuestung.getSchutz();
	}
	
//	public List<Monster> getPet() {
//		return pet;
//	}
//	public void setPet(List<Monster> pet) {
//		this.pet = pet;
//	}
//	public void printPet() {
//		for (Monster m: pet) {
//			System.out.println(m);
//		}
//		System.out.println();
//	}
//	public void addPet(Monster monster) {
//		pet.add(monster);
//		
//	}		
	
	public String getStats() {
		return name +" hat "+hp+" Leben, "+(strength+macht+ausgeruesteteWaffe.getStrength())+" Stärke, "+bewegung+" Bewegungsreichweite und "+gold+" Gold.";
	}
	
	public BufferedImage getSpielerKopf() {
		return spielerKopf;
	}

}