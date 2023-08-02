package welt;
import java.util.*;

import gebauede.*;
import gegenstaende.Gegenstand;
import lebewesen.*;


public class Main {
	public static Random zufall = new Random();
	public static void main(String[] args) {
		Schmiede S = new Schmiede();
		Buchhandlung B = new Buchhandlung();
		Juwelier J = new Juwelier(); 
		Spieler ich = new Spieler();
		exist.add(ich);
		Monster an = new Monster();
		exist.add(an);
		an.setPos(3, 4);
		Monster aus = new Monster();
		aus.setPos(10, 10);
		
		//S.betreten(ich);
		//B.betreten(ich);
		//J.betreten(ich);
		
		ich.status();
		S.ausgabe();
		J.ausgabe();
		B.ausgabe();
		
		
		
		S.kaufen(0, ich);
		B.kaufen(0, ich);
		
		for(int i = 0; i < J.getGegenstaende().size();i++) {
			if(J.getGegenstaende().get(i).getPreis() <= ich.getVermoegen()) {
				J.kaufen(i, ich);
			}
		}
		//Silberring erhöht Kampfstaerke um 3
		ich.status();
		S.ausgabe();
		J.ausgabe();
		B.ausgabe();
		
		
		ich.use();
		
		ich.status();
		
		an.statusNPC();
		aus.statusNPC();
	
	}
	public static List<Lebewesen> exist = new LinkedList<Lebewesen>();
}
