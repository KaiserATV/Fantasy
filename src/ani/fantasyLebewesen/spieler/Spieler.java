package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ani.fantasyLebewesen.Bag;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyItems.Item;
import ani.fantasyItems.equippable.Equippable;
import ani.fantasyItems.equippable.jewelry.Jewelry;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.useables.consumables.Consumeables;
import ani.fantasyItems.weapons.Weapons;

public abstract class Spieler extends Lebewesen {

	private Armor ausgeruesteteRuestung = null;
	private Weapons ausgeruesteteWaffe = null;
	private Equippable ausgeruestetesBling = null;
	protected BufferedImage spielerKopf;
	private int macht = 0;
	private boolean unsichtbar;
	private boolean unsterblich; 
	private int platzierung = 0;
	
	
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

	
	public int getPlatzierung() {
		return platzierung;
	}
	public void setPlatzierung(int i) {
		platzierung = i;
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
	public void setWaffe(Item w) {
		if(ausgeruesteteWaffe != null) {
			ausgeruesteteWaffe.remove(this);
		}
		if(w != null) {
			
		}else {
			ausgeruesteteWaffe =  (Weapons) w;
			w.anwenden(this);	
		}
	}
	
	public Equippable getBling() {
		return ausgeruestetesBling;
	}
	public void setBling(Item b) {
		if(ausgeruestetesBling != null) {
			ausgeruestetesBling.remove(this);
		}
		ausgeruestetesBling = (Equippable) b;
		b.anwenden(this);
	}	
	
	public Armor getArmor() {
		return ausgeruesteteRuestung;
	}
	public void setArmor(Item item) {
		if(ausgeruesteteRuestung != null) {
			ausgeruesteteRuestung.remove(this);
		}
		ausgeruesteteRuestung = (Armor) item;
		ausgeruesteteRuestung.anwenden(this);
	}	
	
	public String getStats() {
		return name +" hat "+hp+" Leben, "+(strength)+" Stärke, "+macht+" Macht, "+schutz+" Schutz, "+bewegung+" Bewegungsreichweite und "+gold+" Gold.";
	}
	
	public BufferedImage getSpielerKopf() {
		return spielerKopf;
	}
	
	@Override
	public int lootGold() {
		return gold;
	}
	@Override
	public Consumeables lootConsu() {
		for(Item i: bag.getBag()) {
			if(i instanceof Consumeables) {
				return (Consumeables) i;
			}
		}
		return null;
	}
	@Override
	public Item lootExtra() {
		Random zufall = new Random();
		int i = zufall.nextInt(3);
		if(i == 0) {
			return ausgeruesteteRuestung;
		}else if(i == 1) {
			return ausgeruesteteWaffe;
		}else {
			return ausgeruestetesBling;
		}
	}
}