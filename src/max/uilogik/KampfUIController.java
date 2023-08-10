package max.uilogik;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ani.lebewesen.Lebewesen;
import ani.lebewesen.Spieler;
import max.sys.KampfSys;
import max.ui.KampfGUI;

public class KampfUIController extends UICon{
	public KampfUIController(Spieler e, Lebewesen z, JFrame y) {
		
		gui = new KampfGUI(y);
		sys = new KampfSys(e,z);
		
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
		
		gui.setTravKeys(rechts, links, zurueck,zu,auswahl,aus);
	
	}
	private int rechts = KeyEvent.VK_D;
	private int links = KeyEvent.VK_A;
	private int zurueck = KeyEvent.VK_S;
	private int auswahl = KeyEvent.VK_SPACE;
	
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
			gui.clearAngriff();
			angriffsMenu = false;
			entscheidMenu = true;
			gui.setEntscheid();
		}else if(itemMenu) {
			gui.clearItems();
			itemMenu = false;
			angriffsMenu = true;
			gui.setAngriff();
		}
	}
	
	
	
	
	// Auswahl hat Probleme, wenn in Menu und mit der Ausgabe - idk, daher geht es in das Menu zurück
	private KeyAdapter aus = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				int g = gui.getSelectedIndex();
				if(itemMenu && g < (sys.getUsables().length-1)) {	
					gui.clearItems();
					gui.setAngriff();
					itemMenu = false;
					angriffsMenu = true;
					gui.addAktion(sys.getNamenEins()+" benutzt "+ sys.getItemName(g)+" und bekommt "+sys.getItemEffekt(g));
					sys.itemBenutzen(g);
					gui.removeItem(g);
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
	}
	//skippt von der Anzeige des Geschehenen zum neuen Menu
		private KeyAdapter weiter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == auswahl) {
					gui.clearErgebnis();
					gui.setEntscheid();
					entscheidMenu = true;
					if(!pve) {
						tauschen();
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
				gui.clearEntscheid();
				gui.setAngriff();
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
				gui.clearAngriff();
				gui.setErgebnis();
				gui.addErgebnis("");
				naechsterZug();
			}
		}
	};
	
	private KeyAdapter rechtsEnt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.beenden();
			}	
		}
	};
	private KeyAdapter rechtsAngr = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.clearAngriff();
				gui.setItems();//Muss implementiert werden
				angriffsMenu = false;
				itemMenu = true;
			}
		}
	};
	
	
	private void naechsterZug() {
		int h = sys.kaempfen();
		if (pve && h > 0) {
			int i = sys.monsterAngriff();
			gui.setAktion(sys.getNamenEins() +" greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
			gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.addAktion(sys.getNamenEins()+" hat noch "+sys.getLebenEins()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
		}else if(!pve && h > 0){
			gui.setAktion(sys.getNamenEins() +"greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
			gui.setInfo(sys.getNamenZwei());
		}else {
			winUebergang();
		}
		
	}
	private void winUebergang() {
		gui.setInfoWidth(0);
		gui.setAktion(sys.getNamenEins()+" besiegt "+sys.getNamenZwei()+".");
		gui.addAktion("Und bekommt:");
		gui.clearAngriff();
		gui.setErgebnisListener(lootMenu);
		
	}
	
	private KeyAdapter lootMenu = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				zaehler++;
				switch (zaehler) {
				case 1:
					gui.addErgebnis("--> 1 colles Schwert");
				break;
				case 2: 
					gui.addErgebnisMulti(" --> vieles Geld");
				break;
				case 3:
					gui.addErgebnisMulti("--> viele EXP");
				break;
				case 4:
					gui.beenden();
				break;
				
				}
			}
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
	
	private String itemName;
	private int zaehler = 0;
	private boolean pve;
	private boolean entscheidMenu;
	private boolean angriffsMenu;
	private boolean itemMenu;
	private KampfGUI gui;
	private KampfSys sys;
}
