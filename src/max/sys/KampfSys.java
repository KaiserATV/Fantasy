package max.sys;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import ani.fantasyItems.Item;
import ani.fantasyItems.useables.Useables;
import ani.fantasyItems.useables.consumables.Consumeables;
import ani.fantasyItems.useables.scroll.Scroll;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;

public class KampfSys extends VorlageSys{
	public KampfSys(Spieler i,Lebewesen g, BufferedImage monster) {
		weiter = true;
		ich = i;
		gegen = g;
		urSprLebenIch = ich.getHp();
		urSprLebenGegen = gegen.getHp();
		schadenGesIch = 0;
		schadenGesGegen = 0;
		
		//Loot der groppt werden kann
		lootGold = gegen.lootGold();
		lootCon = gegen.lootConsu();
		lootItem = gegen.lootExtra();
		
		gekauft = lootItem;
		
		ichBild = ich.getSpielerGesamt();
		if(monster != null) {
			gegenBild = monster;
		}else {
			gegenBild = gegen.getSpielerGesamt();	
		}
		
		
	}
	
	//Funktion für den Angriff des Spielers
	public int kaempfen() {
		if(unsterblich) {
			unsterblich = false;	
		}
		schaden = ich.getStrength();
		if(ich.getGelaehmt()) {
			ich.lowerLaehmung(1);
			return 0; // null wenn gelähmt	
		}else if((gegen.getHp()+gegen.getSchutz()-schaden)> 0 && (gegen.getHp()-ich.getMacht()) >0) {
			gegen.reduziereHp(schaden-gegen.getSchutz());
			gegen.reduziereHp(ich.getMacht());
			return schaden;
		}else {
			if(gegen instanceof Spieler && ((Spieler)gegen).isUnsterblich()) {
				schaden = gegen.getHp()-1;
				((Spieler)gegen).removeBling();
				gegen.setHp(1);
				unsterblich=true;
				return -schaden;
			}else {
				gewonnen();
				return -schaden;	
			}
		}
	}
	
	//Wenn gewonnen
	public void gewonnen() {
			gegen.setHp(0);
			if(gegen instanceof Spieler) {
				verlierer = (Spieler)gegen;	
			}
	}
	
	//Breite der Leiste
	public int bestimmeBreite() {
		return (int) Math.ceil((((double) gegen.getHp())/gegen.getHpMax())*1000);
	}
	
	//Angriff des Monsters
	public int monsterAngriff() {
		if(unsterblich) {
			unsterblich = false;	
		}
		schaden = ((Monster) gegen).getStrength();
		if(gegen.getGelaehmt()) {
			gegen.lowerLaehmung(1);
			return 0;//	Monster greif nicht an/ist gelähmt
		}else if ((ich.getHp()+ich.getSchutz())>schaden) {
			ich.reduziereHp(schaden-ich.getSchutz());
			return schaden;	
		}else {
			if(gegen.getState() == "zahm") {
				zahm = true;
				return -1;
			}else {
				if(ich.isUnsterblich()) {
					schaden = ich.getHp()-1;
					ich.removeBling();
					ich.setHp(1);
					unsterblich=true;
					return -schaden;
				}else {
					ich.setHp(0);
					verlierer = ich;
					return -schaden;	
				}	
			}
		}
	}
	
	//tauscht die Spieler
	public void tauscheReihenfolge() {
		Spieler tmp = ich;
		ich = (Spieler) gegen;
		gegen = tmp;
		int h = urSprLebenIch;
		urSprLebenIch = urSprLebenGegen;
		urSprLebenGegen = h;
		int i = schadenGesIch;
		schadenGesIch = schadenGesGegen;
		schadenGesGegen = i;
		
		BufferedImage t = gegenBild;
		gegenBild = ichBild;
		ichBild = t;
		
	}
	
	//benutzt ein item und entfernt es aus dem rucksack
	public String itemBenutzen(Item i) {
		String name = i.anwendenText(ich);
		if(i instanceof Scroll) {
			((Scroll)ich.bag.get(i)).anwenden(ich,gegen);
		}else {
			ich.bag.get(i).anwenden(ich);
		}
		ich.bag.removeBag(i);
		return name;
	}
	//überprüft ob flucht möglich
	public boolean flucht() {
		if(ich.getHp()>5 && ich.getGold()>=100) {
			ich.reduziereHp(5);
			ich.setGold(ich.getGold()-100);
			return true;
		}
		return false;
	}
	
	
	//Getter - Setter
	
	//Benutzebare Items im Inventar
	public String[] getUsables() {
		List<String> itemsList= new LinkedList<String>();
		if(ich.bag.getBag().size()>0) {
			for(Item g:ich.bag.getBag()) {
				if(g instanceof Useables)
				itemsList.add(g.getName()+" - "+g.getEffekt());
			}
			
		}
		String[] items = new String[itemsList.size()+1];
		items[itemsList.size()]="...";
			for(int i=0; i <itemsList.size(); i++) {
				items[i] = itemsList.get(i);
			}	
		
		return items;
	}
	//returned das ausgewählte item im item menu
	public Item getItem(int index) {
		int j = 0;
		for(Item i:ich.bag.getBag()) {
			if(i instanceof Useables) {
				if (j == index) {
					return i;
				}
				j++;
			}
		}
		
		return null;
	}
	
	//fügt den Loot dem Inventar hinzu
	public void addLootBag() {
		if(lootCon != null) {
			ich.bag.addBag(lootCon);
		}if(lootItem != null) {
			ich.bag.addBag(lootItem);
		}
		ich.setGold(ich.getGold()+lootGold);
	}
	
	
	
	public boolean getWeiter() {
		return weiter;
	}
	public boolean isPve() {
		return gegen instanceof Monster;
	}
	public String getNamenEins() {
		return ich.getName();
	}
	public String getNamenZwei() {
		return gegen.getName();
	}
	public int getLebenEins() {
		return ich.getHp();
	}
	public int getLebenZwei() {
		return gegen.getHp();
	}
	
	public BufferedImage getBildEins() {
		return ichBild;
	}
	public BufferedImage getBildZwei() {
		return gegenBild;
	}
	public String getItemName(Item x) {
		return ich.bag.get(x).getName(); 
	}
	public String getItemEffekt(Item x) {
		return ich.bag.get(x).getEffekt();
	}
	
	public int getLootGold() {
		return lootGold;
	}
	public Consumeables getLootConsum() {
		return lootCon;
	}
	public Item getLootExtra() {
		return lootItem;
	}
	public Spieler getSpielerEins() {
		return ich;
	}
	public Spieler getSpielerZwei() {
		return (Spieler)gegen;
	}
	
	public Item getDropItem() {
		return gekauft;
	}
	public boolean getZahm() {
		return zahm;
	}
	public boolean getUnsterblich() {
		return unsterblich;
	}
	public Spieler getVerlierer() {
		return verlierer;
	}
	
	
	private boolean zahm = false;
	private boolean unsterblich;
	private int schadenGesIch;
	private int schadenGesGegen;
	private int schaden;
	private int urSprLebenIch;
	private int urSprLebenGegen;
	private boolean weiter;
	private Lebewesen gegen;
	private BufferedImage ichBild;
	private BufferedImage gegenBild;
	private int lootGold;
	private Consumeables lootCon;
	private Item lootItem;
	private Spieler verlierer;
	
}
