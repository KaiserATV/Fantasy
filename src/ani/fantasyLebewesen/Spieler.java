package ani.fantasyLebewesen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyItems.Item;

public class Spieler extends Lebewesen {
//	Scanner input = new Scanner(System.in);
	
	//fehlt noch: Pet, wie genau Ausrüstung ???
	//brauche ich für mehrere Spieler ein Array?
	
	public Spieler(String name, Point position, Color color, String backG, int[] bel) {
		// Konstruktor
		super(position,color);
		belegung = bel;
		hpmax = 70;
		
		werte(backG);
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
		break;
		case "Elf":
		break;
		case "Oger":
		break;
		case "Zauberer":
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
	
	
	private String name;
	
		@Override
		public void setName(String s) {
			this.name = s;		
		}
	
		@Override
		public String getName() {
			return name;
		}
	
	private int hpmax;
	
		public int getHpmax() {
			return hpmax;
		}
	
		public void setHpmax(int hpmax) {
			this.hpmax = hpmax;
		}

	
		
	// Rüstung? -> eher Frage ob Rüstung angelegt??? Plätze dafür programmieren??? (Ebenso mit Ringen...)
		private boolean armored;
		
			public boolean isArmored() {
				return armored;
			}
	
			public void setArmored(boolean armored) {
				this.armored = armored;
			}
			
		private int schutz = 0;
		

			public int getSchutz() {
				return schutz;
			}
	
			public void setSchutz(int schutz) {
				this.schutz = schutz;
			}
		
		private	boolean unsterblich; 
			
			public boolean isUnsterblich() {
				return unsterblich;
			}
	
			public void setUnsterblich(boolean unsterblich) {
				this.unsterblich = unsterblich;
			}
			
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
			
		int ausgerüstetRüstung = 0;

		public int getAusrüstenRüstung() {
			return ausgerüstetRüstung;
		}

		public void setAusrüstenRüstung(Item item) {
			ausgerüstetRüstung = bag.getBag().indexOf(item);
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