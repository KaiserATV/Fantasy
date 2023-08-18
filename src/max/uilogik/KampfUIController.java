package max.uilogik;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ani.fantasyItems.Item;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.useables.consumables.Consumeables;
import ani.fantasyItems.weapons.Weapons;
import ani.fantasyLebewesen.Lebewesen;
import ani.fantasyLebewesen.spieler.Spieler;
import fabio.spielerbewegung.SpielerBewegung;
import max.sys.KampfSys;
import max.ui.KampfGUI;

public class KampfUIController extends UICon{
	public KampfUIController(Spieler p, Lebewesen z, JFrame y, SpielerBewegung b) {
		super(p,b);
		
		gui = new KampfGUI(y);
		sys = new KampfSys(p,z);
		
		bewegung = b;
		
		pve = sys.isPve();
		
		gui.setInfo(sys.getNamenZwei());
		gui.setInfoColor(Color.white);
		
		
		gui.setSpieler1Bild(sys.getBildEins());
		gui.setSpieler2Bild(sys.getBildZwei());
		
		entscheidMenu = true;
		angriffsMenu = false;
		itemMenu = false;
		
		gui.setAktion(sys.getNamenEins()+" begegnet "+sys.getNamenZwei()+".");
		gui.addAktion("Was wird "+sys.getNamenEins()+" tun?");
		
		buttonIni();
		
		gui.setItemData(sys.getUsables());
		
		gui.setTravKeys(rechts, links, oben, unten, zu, aus);
	
	}
	
	private KeyAdapter zu = new KeyAdapter () {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == zurueck) {
					back();
			}
		}
	};
	private void back() {
		if(angriffsMenu) {
			angriffsMenu = false;
			entscheidMenu = true;
			gui.setEntscheid(1);
		}else if(itemMenu) {
			itemMenu = false;
			angriffsMenu = true;
			gui.setAngriff(0);
		}
	}
	
	
	
	
	private KeyAdapter aus = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				int g = gui.getSelectedIndex();
				if(itemMenu && g < (sys.getUsables().length-1)) {	
					gui.setAngriff(0);
					itemMenu = false;
					angriffsMenu = true;
					gui.addAktion(sys.itemBenutzen(sys.getItem(g)));
					gui.removeItem(g);
					naechsterZugItem();
				}
			}
		}
	};
	
	
	private void buttonIni(){
		gui.setKeyListenerLinks(linksEnt,0);
		gui.setKeyListenerRechts(rechtsEnt,0);
		gui.setKeyListenerLinks(linksAngr,1);
		gui.setKeyListenerRechts(rechtsAngr,1);
		gui.setErgebnisListener(weiter);
		gui.anlegenListener(anlegenLinks, anlegenRechts);
	}
	//skippt von der Anzeige des Geschehenen zum neuen Menu
		private KeyAdapter weiter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == auswahl) {
					gui.setEntscheid(0);
					entscheidMenu = true;
					if(!pve) {
						tauschen();
					}else if(monsterTod) {
						gui.beenden();
					}else {
						gui.setAktion("Was wird "+sys.getNamenEins() + " tun?");
						gui.addAktion(sys.getNamenEins()+" Leben: "+sys.getLebenEins());
						gui.addAktion(sys.getNamenZwei()+" Leben: "+sys.getLebenZwei());
					}
					
				}
			}		
		};
	

	private KeyAdapter linksEnt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.setAngriff(0);
				entscheidMenu = false;
				angriffsMenu = true;
			}
		}
	};
	private KeyAdapter linksAngr = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				angriffsMenu = false;
				gui.clearAktion();
				gui.addAktion(sys.getNamenEins()+" greift "+ sys.getNamenZwei()+" an!");
				gui.setErgebnis(1);
				gui.addErgebnis("");
				naechsterZug();
			}
		}
	};
	
	
	private KeyAdapter rechtsEnt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				if(sys.flucht()) {
					gui.beenden();	
				}else {
					if(!pve) {
						tauschen();
					}else {
						naechsterZugItem(sys.getNamenEins()+" versucht zu fliehen und scheitert!");
						gui.setErgebnis(1);
					}
					e.getComponent().setVisible(false);
				}
			}	
		}
	};
	private KeyAdapter rechtsAngr = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.setItems();//Muss implementiert werden
				angriffsMenu = false;
				itemMenu = true;
			}
		}
	};
	private void naechsterZugItem(String s) {
		if (pve) {
			int i = sys.monsterAngriff();
			if(i>0) {
				gui.setAktion(s);
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.setInfoWidth(sys.bestimmeBreite());	
			}else if(sys.getZahm()){
				gui.setAktion(s);
				gui.addAktion("Da "+sys.getNamenZwei()+" nun zahm ist überlässt es "+sys.getNamenEins()+" den Inhalt des Rucksackes...");
				winUebergang();
			}else if(sys.getUnsterblich()) {
				gui.setAktion(s);
				gui.addAktion(sys.getNamenEins()+"'s Kette beginnt zu glühen und zerbricht.... "+sys.getNamenEins()+" lives to die another day!");
			}else {
				gui.setAktion(s);
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.addAktion(sys.getNamenEins()+" stirbt an "+sys.getNamenZwei()+"!");	
				monsterTod=true;
				gui.setErgebnis(1);
				bewegung.removeSpieler(sys.getSpielerEins());
			}
		}else if(!pve){
			gui.setAktion(s);
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
			gui.setInfo(sys.getNamenZwei());
		}
	}
	
	
	
	private void naechsterZugItem() {
		if (pve) {
			int i = sys.monsterAngriff();
			if(i>0) {
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.setInfoWidth(sys.bestimmeBreite());	
			}else if(sys.getZahm()){
				gui.addAktion("Da "+sys.getNamenZwei()+" nun zahm ist überlässt es "+sys.getNamenEins()+" den Inhalt des Rucksackes...");
				winUebergang();
			}else if(sys.getUnsterblich()) {
				gui.addAktion(sys.getNamenEins()+"'s Kette beginnt zu glühen und zerbricht.... "+sys.getNamenEins()+" lives to die another day!");
			}else {
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.addAktion(sys.getNamenEins()+" stirbt an "+sys.getNamenZwei()+"!");	
				monsterTod=true;
				gui.setErgebnis(1);
				bewegung.removeSpieler(sys.getSpielerEins());
			}
		}else if(!pve){
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
			gui.setInfo(sys.getNamenZwei());
		}
	}
	
	
	
	private void naechsterZug() {
		int h = sys.kaempfen();
		if (pve && h > 0) {
			int i = sys.monsterAngriff();
			if(i>0) {
				gui.setAktion(sys.getNamenEins() +" greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.setInfoWidth(sys.bestimmeBreite());	
			}else if(sys.getZahm()){
				gui.addAktion("Da "+sys.getNamenZwei()+" nun zahm ist überlässt es "+sys.getNamenEins()+" den Inhalt des Rucksackes...");
				winUebergang();
			}else if(sys.getUnsterblich()) {
				gui.addAktion(sys.getNamenEins()+"'s Kette beginnt zu glühen und zerbricht.... "+sys.getNamenEins()+" lives to die another day!");
			}else {
				gui.setAktion(sys.getNamenEins() +" greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
				gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
				gui.addAktion(sys.getNamenEins()+" stirbt an "+sys.getNamenZwei()+"!");	
				monsterTod=true;
				gui.setErgebnis(1);
				bewegung.removeSpieler(sys.getSpielerEins());
			}
		}else if(!pve && h > 0){
			gui.setAktion(sys.getNamenEins() +"greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
			gui.setInfo(sys.getNamenZwei());
		}else {
			if(sys.getUnsterblich()) {
				gui.addAktion(sys.getNamenEins()+"'s Kette beginnt zu glühen und zerbricht.... "+sys.getNamenEins()+" lives to die another day!");
				}else {
					winUebergang();
					if(!pve) {
						bewegung.removeSpieler(sys.getSpielerZwei());	
					}
				}
		}
	}
	
	private void winUebergang() {
		gui.setInfoWidth(0);
		gui.setAktion(sys.getNamenEins()+" ("+sys.getLebenEins()+" HP) besiegt "+sys.getNamenZwei()+".");
		gui.addAktion("Und bekommt:");
		sys.addLootBag();
		gui.setErgebnisListener(lootMenu);
		
	}
	
	private KeyAdapter lootMenu = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				Consumeables cons = sys.getLootConsum();
				Item extra = sys.getLootExtra();
				switch (zaehler) {
				case 0:
					gui.addErgebnis("--> "+sys.getLootGold()+" Gold!");
				break;
				case 1:
					if(cons != null) {
						gui.addErgebnisMulti(ergebnisGen(cons));
					}
				break;
				case 2:
					if(extra != null) {
						gui.addErgebnisMulti(ergebnisGen(extra));
					}else if(extra == null && cons == null) {
						gui.beenden();
					}
				break;
				case 3:
					if(extra != null) {
						if(sys.anlegbar()) {
							gui.addAnlegen(ergebnisGen(extra)+" Anlegen? - Derzeit: "+sys.getStatsWeniger());
						}
					}else {
						gui.beenden();	
					}
				break;
				default:
					gui.beenden();
				}
				zaehler++;
			}
		}
	};
	
	private String ergebnisGen(Item i) {
		String verschieden= i.getName()+" mit ";
			if(i instanceof Armor) {
				verschieden+=((Armor)i).getSchutz()+" Schutz";
			}else if(i instanceof Weapons) {
				verschieden+=((Weapons)i).getStrength()+" Stärke";
			}else {
				verschieden+= i.getEffekt();
			}
		return "--> "+verschieden+"!";
	}
	
	
	
	
	private ActionListener anlegenLinks = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sys.anlegen();
			gui.setErgebnis(0);
		}
	};
	private ActionListener anlegenRechts = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.setErgebnis(0);
		}	
	};
	
	

	private void tauschen() {
		sys.tauscheReihenfolge();
		gui.setSpieler1Bild(sys.getBildEins());
		gui.setSpieler2Bild(sys.getBildZwei());
		gui.setInfoWidth(sys.bestimmeBreite());
		gui.setInfo(sys.getNamenZwei());
		gui.setItemData(sys.getUsables());
		gui.clearAktion();
		gui.setAktion("Was wird "+ sys.getNamenEins() +" tun?");
	}
	
	private boolean monsterTod=false;
	private int zaehler = 0;
	private boolean pve;
	private boolean entscheidMenu;
	private boolean angriffsMenu;
	private boolean itemMenu;
	private KampfGUI gui;
	private KampfSys sys;
}
