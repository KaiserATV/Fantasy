package uilogik;

import java.util.concurrent.TimeUnit;

import ui.KampfGUI;

public class KampfUIController extends UICon{
	public KampfUIController(KampfGUI k) {
		gui = k;
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
			//angriffsimplementierung
			//angriffsMenu = false;
			//entscheidMenu = true;
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
		//item implementierung
		
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
		//int dauer = (int)Math.ceil(1000/(s.length()/2.0));//Scalt mit Bestimmter Zeit, derzeit 2sec // 2000 ms
		for(int i=1;i <= s.length();i++) {
			gui.setAktion(ausgang+s.substring(0,i));
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
	
	private boolean entscheidMenu;
	private boolean angriffsMenu;
	private boolean itemMenu;
	private KampfGUI gui;
	private int aktionZeilen;
}
