package sys;

import lebewesen.Lebewesen;
import lebewesen.Monster;
import lebewesen.Spieler;
import ui.KampfGUI;

public class KampfSys {
	public KampfSys(Spieler i,Lebewesen g) {
		weiter = true;
		ich = i;
		gegen = g;
		urSprLebenIch = ich.getLeben();
		urSprLebenGegen = gegen.getLeben();
		schadenGesIch = 0;
		schadenGesGegen = 0;
		getauscht = false;
		
	}
	public int kaempfen() {//Logik für die Kämpfe, mal sehen, weil keine Ahnung wie struktur aussieht
		schaden = ich.getKampfStaerke();
		if(gegen.getLeben()-schaden > 0) {
			schadenGesIch += schaden;
			gegen.lowerLeben(schaden);
			return schaden;
		}else {
			gewonnen(ich);
			return -schaden;
		}
	}
	
	public void gewonnen(Lebewesen s) {
		if (s == ich) {
			gegen.setLeben(0);
		}else {
			ich.setLeben(0);
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
		//Die Schadenslogik hinter Monsterangriffen hier
		schaden = gegen.getKampfStaerke();
		schadenGesGegen += schaden;
		ich.lowerLeben(schaden);
		
		
		return schaden;
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
		
		getauscht = true;
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
	
	private boolean getauscht;
	private int schadenGesIch;
	private int schadenGesGegen;
	private int schaden;
	private int urSprLebenIch;
	private int urSprLebenGegen;
	private boolean weiter;
	private Spieler ich;
	private Lebewesen gegen;
}
