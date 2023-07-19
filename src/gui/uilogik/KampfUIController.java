package uilogik;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import lebewesen.Lebewesen;
import lebewesen.Monster;
import lebewesen.Spieler;
import sys.KampfSys;
import ui.KampfGUI;

public class KampfUIController extends UICon{
	public KampfUIController(Spieler e, Lebewesen z) {
		
		gui = new KampfGUI();
		sys = new KampfSys(e,z);
		
		pve = sys.isPve();
		
		aktionZeilen = 0;
		buttonZurueck = gui.getZurueckButton();
		buttonLinks = gui.getLinksButton();
		buttonRechts = gui.getRechtsButton();
		
		entscheidMenu = true;
		angriffsMenu = false;
		itemMenu = false;
		buttonIni();
		
	}
	public void buttonIni(){
		buttonZurueck.addActionListener(e -> menuZurueck());
		buttonLinks.addActionListener(e -> links());
		buttonRechts.addActionListener(e -> rechts());
		gui.getErgebnis().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				gui.clearErgebnis();
				gui.setEntscheid();
				entscheidMenu = true;
				if(!pve) {
					tauschen();
				}
				
			}
		});
		
	}
	
	public void tauschen() {
		sys.tauscheReihenfolge();
		gui.setInfoWidth(sys.bestimmeBreite());
		gui.setAktion("<html><br/>Was wird "+ sys.getNamenEins() +" tun?</html>");
	}
	
	public void menuZurueck() {
		if(entscheidMenu) {
			System.out.println("1");
		}else if(angriffsMenu) {
			gui.clearAngriff();
			gui.setEntscheid();
			angriffsMenu = false;
			entscheidMenu = true;
			System.out.println("2");
		}else if(itemMenu) {
			gui.clearItems();
			gui.setAngriff();
			angriffsMenu = true;
			itemMenu = false;
			System.out.println("3");
		}
	}
	
	public void links() {
		if(entscheidMenu) {
			gui.clearEntscheid();
			gui.setAngriff();
			entscheidMenu = false;
			angriffsMenu = true;
			System.out.println("1");
		}else if(angriffsMenu) {
			angriffsMenu = false;
			addAktion(sys.getNamenEins()+" greift "+ sys.getNamenZwei()+" an!");
			gui.clearAngriff();
			gui.addErgebnis();
			naechsterZug();
			System.out.println("2");
		}
	}
	public void rechts() {
		if(entscheidMenu) {
			System.out.println("1");
			//Fluchtimplementierung
		}else if(angriffsMenu) {
			gui.clearAngriff();
			gui.setItems();//Muss implementiert werden
			angriffsMenu = false;
			itemMenu = true;
			System.out.println("2");
		}
	}
	
	

	public void addAktion(String s) {
		aktionZeilen++;
		if(aktionZeilen > 6) {
			verkuerzen();
			aktionZeilen--;
		}
		System.out.println(s+" Zeile: "+aktionZeilen);
		String ausgang;
		if(gui.getAktion().length() > 0) {
			ausgang = gui.getAktion().substring(0,gui.getAktion().length()-7)+"<br/>";	
		}else {
			ausgang = "<html>";
		}
		gui.setAktion(ausgang+s+"</html>");
	}
	
	public void clearAktion() {
		gui.setAktion("");
		aktionZeilen =0;
	}
	
	public void verkuerzen() {
		String bearbeiten = gui.getAktion();
		String neu;
		neu = bearbeiten;
		neu = neu.substring(bearbeiten.indexOf("/>")+2,bearbeiten.length());
		neu = "<html>"+neu;
		gui.setAktion(neu);
	}
	
	public void naechsterZug() {
		int h = sys.kaempfen();
		if (pve && h > 0) {
			int i = sys.monsterAngriff();
			addAktion(sys.getNamenEins() +"greift an und macht "+sys.getNamenZwei()+ " "+h+" Schaden!<br/>"+sys.getNamenEins()+" nimmt "+i+" Schaden von "+sys.getNamenZwei()+" !");
			//gui.setErgebnis("<html>sys.getNamenEins() machst "+h+ " Schaden!<br/> Und nimmst " +i+ " Schaden!"); 
			gui.setInfoWidth(sys.bestimmeBreite());
		}else if(!pve && h > 0){
			addAktion(sys.getNamenEins()+" macht "+h + " Schaden an "+sys.getNamenZwei());
			gui.setInfoWidth(sys.bestimmeBreite());
		}else {
			gui.setInfoWidth(0);
			gui.setWinPane(sys.getNamenEins(),sys.getNamenZwei());
		}
		
	}
	
	private boolean pve;
	private boolean entscheidMenu;
	private boolean angriffsMenu;
	private boolean itemMenu;
	private KampfGUI gui;
	private KampfSys sys;
	private int aktionZeilen;
}
