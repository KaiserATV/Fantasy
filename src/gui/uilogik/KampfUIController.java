package uilogik;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import lebewesen.Lebewesen;
import lebewesen.Spieler;
import sys.KampfSys;
import ui.KampfGUI;

public class KampfUIController extends UICon{
	public KampfUIController(Spieler e, Lebewesen z) {
		
		gui = new KampfGUI();
		sys = new KampfSys(e,z);
		
		pve = sys.isPve();
		
		gui.setInfo(sys.getNamenZwei());
		gui.setInfoColor(Color.white);
		
		
		gui.setSpieler1Bild(sys.getBildEins());
		gui.setSpieler2Bild(sys.getBildZwei());
		
		entscheidMenu = true;
		angriffsMenu = false;
		itemMenu = false;
		
		
		gui.addAktion(sys.getNamenEins()+" begegnet "+sys.getNamenZwei()+".");
		gui.addAktion("Was wird "+sys.getNamenEins()+" tun?</html>",1000);
		
		buttonIni();
		
		gui.setTravKeys(rechts, links, zurueck,zu,auswahl,aus);
	
	}
	private int rechts = KeyEvent.VK_D;
	private int links = KeyEvent.VK_A;
	private int zurueck = KeyEvent.VK_S;
	private int auswahl = KeyEvent.VK_SPACE;
	
	private KeyAdapter zu = new KeyAdapter () {
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode()+" "+zurueck);
			if(e.getKeyCode() == zurueck) {
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
		}
	};
	private KeyAdapter aus = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				if(itemMenu) {
					gui.setAktion(sys.getNamenEins() + " benutzt "+sys.itemBenutzen(gui.getSelectedIndex()));
				}
			}
		}
	};
	private void buttonIni(){
		gui.setKeyListenerLinks(linksEnt,0);
		gui.setKeyListenerRechts(rechtsEnt,0);
		gui.setKeyListenerLinks(linksAngr,1);
		gui.setKeyListenerRechts(rechtsAngr,1);
		gui.setErgebnisListener(f);
	}
	
	private void tauschen() {
		sys.tauscheReihenfolge();
		gui.setSpieler1Bild(sys.getBildEins());
		gui.setSpieler2Bild(sys.getBildZwei());
		gui.setInfoWidth(sys.bestimmeBreite());
		gui.setInfo(sys.getNamenZwei());
		gui.clearAktion();
		gui.setAktion("<html><br/>Was wird "+ sys.getNamenEins() +" tun?</html>");
	}
	

	private KeyAdapter linksEnt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.clearEntscheid();
				gui.setAngriff();
				entscheidMenu = false;
				angriffsMenu = true;
				System.out.println("1");
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
				gui.addErgebnis();
				naechsterZug();
				System.out.println("2");
			}
		}
	};
	
	private KeyAdapter rechtsEnt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				System.out.println("1");	
			}	
		}
			//Fluchtimplementierung
	};
	private KeyAdapter rechtsAngr = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.clearAngriff();
				gui.setItems(sys.getUsables());//Muss implementiert werden
				angriffsMenu = false;
				itemMenu = true;
				System.out.println("2");
			}
		}
	};
	
	
	private void naechsterZug() {
		int h = sys.kaempfen();
		if (pve && h > 0) {
			int i = sys.monsterAngriff();
			gui.addAktion(sys.getNamenEins() +"greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!");
			gui.addAktion(sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			//gui.setErgebnis("<html>sys.getNamenEins() machst "+h+ " Schaden!<br/> Und nimmst " +i+ " Schaden!"); 
			gui.setInfoWidth(sys.bestimmeBreite());
		}else if(!pve && h > 0){
			gui.addAktion(sys.getNamenEins() +"greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!"/*+sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !"*/);
			gui.addAktion(sys.getNamenZwei()+" hat noch "+sys.getLebenZwei()+" Leben!");
			gui.setInfoWidth(sys.bestimmeBreite());
			gui.setInfo(sys.getNamenZwei());
		}else {
			winUebergang();
		}
		
	}
	private void winUebergang() {
		gui.setInfoWidth(0);
		gui.addAktion(sys.getNamenEins()+" besiegt "+sys.getNamenZwei()+".");
		gui.addAktion("Und bekommt:");
		gui.setErgebnisListener(lootMenu);
		
	}
	
	private KeyAdapter lootMenu = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				zaehler++;
				switch (zaehler) {
				case 1:
					gui.setErgebnis("<html><br/>--> 1 colles Schwert");
				break;
				case 2: 
					gui.setErgebnis(gui.getErgebnis().getText()+" <br/>--> vieles Geld");
				break;
				case 3:
					gui.setErgebnis(gui.getErgebnis().getText()+"<br/>--> viele EXP</html>");
				break;
				case 4:
					gui.setWinPane();
				break;
				
				}
			}
		}
	};
	
	private KeyAdapter f = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.clearErgebnis();
				gui.setEntscheid();
				angriffsMenu = false;
				entscheidMenu = true;
				if(!pve) {
					tauschen();
				}
			}
		}		
	};
	
	private int zaehler = 0;
	private boolean pve;
	private boolean entscheidMenu;
	private boolean angriffsMenu;
	private boolean itemMenu;
	private KampfGUI gui;
	private KampfSys sys;
}
