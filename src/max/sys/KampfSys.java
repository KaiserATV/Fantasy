package max.sys;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import ani.fantasyItems.Item;
import ani.fantasyItems.useables.Useables;
import ani.fantasyItems.useables.consumables.Consumeables;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;

public class KampfSys extends VorlageSys{
	public KampfSys(Spieler i,Lebewesen g) {
		weiter = true;
		ich = i;
		gegen = g;
		urSprLebenIch = ich.getHp();
		urSprLebenGegen = gegen.getHp();
		schadenGesIch = 0;
		schadenGesGegen = 0;
		getauscht = false;
		
		lootGold = gegen.lootGold();
		lootCon = gegen.lootConsu();
		lootItem = gegen.lootExtra();
		
		gekauft = lootItem;
		
		ichBild = ich.getSpielerGesamt();
		gegenBild = gegen.getSpielerGesamt();
		
	}
	public int kaempfen() {
		schaden = ich.getStrength()+ich.getMacht();
		
		gegen.reduziereHp(schaden-gegen.getSchutz());
		if(gegen.getHp() > 0) {
			return schaden;
		}else {
			gewonnen(ich);
			return -schaden;
		}
	}
	
	public void gewonnen(Lebewesen s) {
		if (s == ich) {
			gegen.setHp(0);
		}else {
			ich.setHp(0);
		}
	}
	
	
	public boolean getWeiter() {
		return weiter;
	}
	public int bestimmeBreite() {
		double prozente = ((double)schadenGesIch/urSprLebenGegen);
		return (int) Math.floor(1000-prozente*1000);
		
	}
	public int monsterAngriff() {
		schaden = ((Monster) gegen).getStrength();
		if((ich.getHp()+ich.getSchutz())>schaden) {
			ich.reduziereHp(schaden-ich.getSchutz());
			return schaden;	
		}else {
			ich.setHp(0);
			return -schaden;
		}
		
	}
	
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
		
		getauscht = true;
	}
	
	public String itemBenutzen(Item i) {
		String name = ich.bag.get(i).getName();
		ich.bag.removeBag(ich.bag.getBag().indexOf(i));
		return name;
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
	public Item getItem(int index) {
		int i = 0;
		for(Item item:ich.bag.getBag()) {
			if(i == index) {
				return item;
			}
			i++;
		}
		return null;
	}
	public boolean flucht() {
		if(ich.getHp()>5 && ich.getGold()>100) {
			ich.reduziereHp(5);
			ich.setGold(ich.getGold()-100);
			return true;
		}
		return false;
	}
	public void addLootBag() {
		if(lootCon != null) {
			ich.bag.addBag(lootCon);
		}if(lootItem != null) {
			ich.bag.addBag(lootItem);
		}
		ich.setGold(ich.getGold()+lootGold);
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
	
	
	private boolean getauscht;
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
	
	
}
