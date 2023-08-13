package max.uilogik;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ani.fantasyShops.Shops;
import fabio.spielerbewegung.SpielerBewegung;
import ani.fantasyLebewesen.Spieler;
import max.sys.ShopSys;
import max.ui.ShopGUI;

public class ShopUIController extends UICon{

	public ShopUIController(Spieler p, Shops s,JFrame y,SpielerBewegung b) {
		super(p);
		
		bewegung = b;
		ergebnisZeile = 0;
		
		gui = new ShopGUI(y);
		sys = new ShopSys(p,s);
		System.out.println(rechts);	
		System.out.println(links);

		urGeld = sys.getSpielerVermoegen();
		
		gui.setButtonLinksText("Kaufen");
		gui.setButtonRechtsText("Verlassen");
		
		gui.setKaufMenuData(sys.getInventar());
		gui.inventarListen(rechts, links);
		
		gui.setInfo(sys.getLadenName());
		
		gui.setTravKeys(rechts, links, zurueckMenu, auswahl, aus);
		
		
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
					if(sys.kaufen(sel)) {
						kaufItem = "--> "+sys.getSpielerName() + " kauft "+sys.getItemNamen(sel) + " bei " +sys.getLadenName() + " fuer "+sys.getItemPreis(sel) +" Gold!";
						gui.removeZeile(sel);	
					}else {
						bewegung.naechsterSpieler();
						gui.beenden();
					}
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
	
	
	private KeyAdapter zurueckMenu = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == zurueck) {
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
}
