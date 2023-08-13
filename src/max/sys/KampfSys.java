package max.sys;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ani.fantasyItems.Item;
import ani.fantasyItems.useables.Useables;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.Spieler;
import ani.fantasyLebewesen.nsc.Monster;
import fabio.spielerbewegung.*;

public class KampfSys {
	public KampfSys(Spieler i,Lebewesen g) {
		weiter = true;
		ich = i;
		gegen = g;
		urSprLebenIch = ich.getHp();
		urSprLebenGegen = gegen.getHp();
		schadenGesIch = 0;
		schadenGesGegen = 0;
		getauscht = false;
		
		try {
			ichBild = ImageIO.read(new File("/home/max/eclipse-workspace-neu/FantasyGame/src/img/tree.png"));
			gegenBild = ImageIO.read(new File("/home/max/eclipse-workspace-neu/FantasyGame/src/img/tree2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
//	public int kaempfen() {//Logik für die Kämpfe, mal sehen, weil keine Ahnung wie struktur aussieht
//		schaden = ich.getKampfStaerke()+10;
//		if(gegen.getHp()-schaden > 0) {
//			schadenGesIch += schaden;
//			gegen.reduziereHp(schaden);
//			return schaden;
//		}else {
//			gewonnen(ich);
//			return -schaden;
//		}
//	}
	
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
//	public int monsterAngriff() {
//		//Die Schadenslogik hinter Monsterangriffen hier
//		schaden = ((Monster) gegen).get
//		schadenGesGegen += schaden;
//		ich.reduziereHp(schaden);
//		return schaden;
//	}
	
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
	public String[]getUsables() {
		String[] items;
		if(ich.bag.getBag().size()>0) {
			items = new String[] {};
			int i = 0;
			for(Item g:ich.bag.getBag()) {
				if(g instanceof Useables)
				items[i] = g.getName()+" - "+g.getEffekt();
				i++;
			}
			items[items.length-1] = "...";
		}else {
			items = new String[]{"..."};
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
	
	
	private boolean getauscht;
	private int schadenGesIch;
	private int schadenGesGegen;
	private int schaden;
	private int urSprLebenIch;
	private int urSprLebenGegen;
	private boolean weiter;
	private Spieler ich;
	private Lebewesen gegen;
	private BufferedImage ichBild;
	private BufferedImage gegenBild;
}
