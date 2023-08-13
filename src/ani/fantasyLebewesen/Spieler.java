package ani.fantasyLebewesen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyItems.Item;
import ani.fantasyItems.schmiedegut.Armor;

public class Spieler extends Lebewesen {
//	Scanner input = new Scanner(System.in);
	
	//fehlt noch: Pet, wie genau Ausrüstung ???
	//brauche ich für mehrere Spieler ein Array?
	
	public Spieler(String n, Point position, Color color, String backG, int[] bel) {
		// Konstruktor
		super(position,color);
		belegung = bel;
		werte(backG);
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
	
	private void werte(String s) {
		switch (s) {
		case "Zwerg":
			hpmax = 70;
			strength = 10;
			gold = 500;
			
		break;
		case "Elf":
			hpmax = 50;
			strength = 10;
			gold = 700;
			
		break;
		case "Ork":
			hpmax = 100;
			strength = 5;
			gold = 0;
			
			
		break;
		case "Zauberer":
			hpmax = 30;
			strength = 20;
			gold = 1000;
			
			
		break;
		default:
			System.out.println("error");
		break;
		}
	}
//	public void create() {
//		System.out.println("1: hp max");
//		System.out.println("2: strength max");
//		System.out.println("3: gold max");
//		String background = input.next();
//		
//		System.out.println("Willkommen " + name);
//		System.out.println("Deine Werte sind:");
//		
//		int gold = 1000;
//		int strength = 5;
//		int hp = 50;
//		
//		switch(background){
//		case "1":
//			hp = 70;
//			break;
//		case "2":
//			strength = 7;
//			break;
//		case "3":
//			gold = 1500;
//			break;
//		default:
//			System.out.println("Eingabe uneindeutig. Durchschnittswerte werden verwendet.");
//		}
//		
//		System.out.println("Leben: " +hp);
//		System.out.println("Stärke: " +strength);
//		System.out.println("Gold: " +gold);
//		
//	}

	public static Spieler createSpieler(String name, Point position, Color color, String back,int[] bel) {
        if (totalPlayers >= MAX_PLAYERS) {
                return null;
        }
        return new Spieler(name, position, color, back, bel);
	}


	
		
	// Rüstung? -> eher Frage ob Rüstung angelegt??? Plätze dafür programmieren??? (Ebenso mit Ringen...)
		
			
		private int macht = 0;
	
			public int getMacht() {
				return macht;
			}
	
			public void setMacht(int macht) {
				this.macht = macht;
			}
		
		private boolean unsichtbar;

			public boolean isUnsichtbar() {
				return unsichtbar;
			}
	
			public void setUnsichtbar(boolean unsichtbar) {
				this.unsichtbar = unsichtbar;
			}

		/*
		private List<Armory> armor = new ArrayList<Armory>();
		public List<Armory> getArmor() {
			return armor;
		}
		public void setArmor(List<Armory> armor) {
			this.armor = armor;
		}
		public void printArmor() {
			for (Item i: armor) {
				System.out.println(i);
			}
		}
		*/
		private boolean unsterblich; 
		
		public boolean isUnsterblich() {
			return unsterblich;
		}

		public void setUnsterblich(boolean unsterblich) {
			this.unsterblich = unsterblich;
		}	
		
			
		Item ausgeruesteteRuestung = null;

		public Item getAusrüstenRüstung() {
			return ausgeruesteteRuestung;
		}

		public void setAusrüstenRüstung(Item item) {
			ausgeruesteteRuestung = item;
			schutz = ((Armor) ausgeruesteteRuestung).getSchutz();
		}
		
		// Rucksack 
		public Bag bag = new Bag();
		
		
		//Pets
		private List<Monster> pet  = new ArrayList<Monster>();
		public List<Monster> getPet() {
			return pet;
		}
		public void setPet(List<Monster> pet) {
			this.pet = pet;
		}
		public void printPet() {
			for (Monster m: pet) {
				System.out.println(m);
			}
			System.out.println();
		}

		public void addPet(Monster monster) {
			pet.add(monster);
			// macht das auch was?
		}
			
	public void attack(Monster monster, Spieler player) { //weitergeben an Bag mit Listen für Item typen???
		for (Item i: bag.getBag()) {
			i.anwenden(monster, player);
		}
	}

	public void anwenden(Spieler player, Monster monster) {
		for (Item i: bag.getBag()) {
			i.anwenden(monster, player);
		}
		
	}

}