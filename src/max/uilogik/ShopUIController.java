package max.uilogik;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ani.gebaude.Laden;
import ani.lebewesen.Spieler;
import max.sys.ShopSys;
import max.ui.ShopGUI;

public class ShopUIController extends UICon{

	public ShopUIController(Spieler i, Laden l,JFrame y) {
		super();
		
		auswahl = KeyEvent.VK_SPACE;
		zurueckKey = KeyEvent.VK_S;
		links = KeyEvent.VK_A;
		rechts = KeyEvent.VK_D;
		
		ergebnisZeile = 0;
		
		gui = new ShopGUI(y);
		sys = new ShopSys(i,l);
		

		urGeld = sys.getSpielerVermoegen();
		
		gui.setButtonLinksText("Kaufen");
		gui.setButtonRechtsText("Verlassen");
		
		gui.setKaufMenuData(sys.getInventar());
		gui.inventarListen(rechts, links);
		
		gui.setInfo(sys.getLadenName());
		
		gui.setTravKeys(rechts, links, zurueck, auswahl, aus);
		
		
		gui.setAktion(sys.getSpielerName()+" betritt " +sys.getLadenName()+".");
		gui.addAktion("Willst du etwas kaufen?");	
		gui.addAktion("Du hast "+sys.getSpielerVermoegen()+ " Gold!");
	
		buttonInit();
	}
	
	private void buttonInit() {
		gui.setKeyListenerLinks(setMenu, 0);
		gui.setKeyListenerRechts(verlassen, 0);
	}
	
	private KeyAdapter aus = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				int sel = gui.getSelected();
				if(sys.kannKaufen(sel)) {
					kaufItem = "--> "+sys.getSpielerName() + " kauft "+sys.getItemNamen(sel) + " bei " +sys.getLadenName() + " fuer "+sys.getItemPreis(sel) +" Gold!";
					sys.kaufen(sel);
					gui.removeZeile(sel);
					
					if(ergebnisZeile== 6) {
						String alt = gui.getErgebnis();
						alt = alt.substring(alt.indexOf("\n", alt.indexOf("\n")+2)+2,alt.length());
						gui.addErgebnis(alt);
						ergebnisZeile-=2;
					}
					
					if(gui.getErgebnis().length()>1) {
						gui.addErgebnisMulti(kaufItem);
					}else {
						gui.addErgebnis(kaufItem);	
					}
					gui.setInfoWidth((int) Math.ceil(((double) sys.getSpielerVermoegen()/urGeld)*1000));
					
					gui.addErgebnisMulti("Du hast noch "+sys.getSpielerVermoegen()+" Gold!");
					ergebnisZeile+=2;
					
					
				}else {
					gui.addErgebnisMulti("Das kann "+sys.getSpielerName()+" nicht kaufen! Du hast "+sys.getSpielerVermoegen()+ " Gold!");
					
				}
			}
		}
	};
	
	
	private KeyAdapter zurueck = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == zurueckKey) {
				gui.clearKaufMenu();
				gui.clearErgebnis();
				gui.setEntscheid();
				gui.clearAktion();
				gui.setAktion("Willst du etwas anderes Kaufen?");
				gui.addAktion("Du hast noch "+sys.getSpielerVermoegen()+" Gold!");
			}
		}
	};
	
	
	private KeyAdapter verlassen = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				beenden();
			}
		}
	};
	
	private void beenden() {
		gui.beenden();
	}
	
	private KeyAdapter setMenu = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				gui.clearEntscheid();
				gui.clearBild();
				gui.setKaufMenu();
				gui.setErgebnis();
			}
		}
	};
	
	private int ergebnisZeile;
	private int urGeld;
	private String kaufItem;
	private ShopGUI gui;
	private ShopSys sys;
	private int links;
	private int rechts;
	private int auswahl;
	private int zurueckKey;

}
