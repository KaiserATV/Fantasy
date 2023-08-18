package max.uilogik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ani.fantasyLebewesen.spieler.Spieler;
import ani.fantasyShops.Shops;
import fabio.spielerbewegung.SpielerBewegung;
import max.sys.ShopSys;
import max.ui.ShopGUI;

public class ShopUIController extends UICon{

	public ShopUIController(Spieler p, Shops s,JFrame y,SpielerBewegung b) {
		super(p,b);
		
		ergebnisZeile = 0;
		
		gui = new ShopGUI(y);
		sys = new ShopSys(p,s);
		
		gui.setBackground(s.getHintergrundOhnePixl());
//		gui.setBackground(s.getHintergrundPixl());

		urGeld = sys.getSpielerVermoegen();
		
		gui.setButtonLinksText("Kaufen");
		gui.setButtonRechtsText("Verlassen");
		
		gui.setKaufMenuData(sys.getInventar());
		
		gui.setInfo(sys.getLadenName());
		
		gui.setTravKeys(rechts, links,oben,unten, zurueckMenu, auswahl, aus);
		
		
		gui.setAktion(sys.getSpielerName()+" betritt " +sys.getLadenName()+".");
		gui.addAktion("Willst du etwas kaufen?");	
		gui.addAktion("Du hast "+sys.getSpielerVermoegen()+ " Gold!");
	
		buttonInit();
	}
	
	private void buttonInit() {
		gui.setKeyListenerLinks(setMenu, 0);
		gui.setKeyListenerRechts(verlassen, 0);
		gui.anlegenListener(anlegenLinks, anlegenRechts);
	}
	
	private KeyAdapter aus = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == auswahl) {
				int sel = gui.getSelected();
				
				
				if(sys.kannKaufen(sel)) {
					if(sys.kaufen(sel)) {
						if(sys.anlegbar()) {
							gui.clearErgebnis();
							gui.addAnlegen();
						}
						kaufItem = "--> "+sys.getSpielerName() + " kauft "+sys.getGekauftNamen() + " bei " +sys.getLadenName() + " fuer "+sys.getGekauftPreis() +" Gold!";
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
				}else if(gui.getItemsRows() == 0) {
					gui.addErgebnisMulti("Dieser Laden ist leer! - Leere kann nicht gekauft werden!");
				}else if(!sys.kannKaufen(sel)){
					gui.addErgebnisMulti("Das kann "+sys.getSpielerName()+" nicht kaufen! Du hast "+sys.getSpielerVermoegen()+ " Gold!");
					
				}
			}
		}
	};
	
	
	private KeyAdapter zurueckMenu = new KeyAdapter() {
		@Override 
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == zurueck && gui.getItemsRows()>1) {
				gui.clearKaufMenu();
				gui.clearErgebnis();
				gui.setEntscheid();
				gui.clearAktion();
				gui.setAktion("Willst du etwas anderes Kaufen?");
				gui.addAktion(sys.getStats());
			}else if(e.getKeyCode() == zurueck && gui.getItemsRows() == 0) {
				gui.clearKaufMenu();
				gui.clearErgebnis();
				gui.setEntscheid();
				gui.clearAktion();
				gui.setAktion("Dieser Laden ist leer!");
				gui.addAktion(sys.getStats());
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
			if(e.getKeyCode() == auswahl && gui.getItemsRows()>0) {
				gui.clearEntscheid();
				gui.clearBild();
				gui.setKaufMenu();
				gui.setErgebnis();
			}
		}
	};
	
	private ActionListener anlegenLinks = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sys.anlegen();
			gui.addAktion(sys.getAnlegText());
			gui.clearAnlegen();
			gui.setErgebnis();
		}
	};
	private ActionListener anlegenRechts = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.clearAnlegen();
			gui.setErgebnis();
		}	
	};
	
	
	
	
	
	
	private int ergebnisZeile;
	private int urGeld;
	private String kaufItem;
	private ShopGUI gui;
	private ShopSys sys;
}
