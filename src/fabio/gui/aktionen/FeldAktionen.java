package fabio.gui.aktionen;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ani.fantasyShops.*;
import fabio.spielerbewegung.SpielerBewegung;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.spieler.Spieler;
import max.uilogik.KampfUIController;
import max.uilogik.ShopUIController;

public class FeldAktionen  {
	
		public FeldAktionen(JFrame jf, SpielerBewegung bew) {
			frame = jf;
			bewegung = bew;
		}
		private SpielerBewegung bewegung;
		
        public void wahrscheinlichkeitMonsterInteraktion(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99


    			Monster m = new Monster();
    			
                if (chance < 0) { // 10% Chance auf Monsterinteraktion
                        monsterLaufen(ich,m);
                        
                }
        }
        public void spielerKampf(Spieler eins, Spieler zwei) {
        	spielerLaufen(eins,zwei);
        }
        
        public void wahrscheinlichkeitHaendlerTreffen(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

                TravelingMerchant t = new TravelingMerchant();
                
                if (chance < 8) { // 8% Chance auf fahrenden Händler
                        shopLaufen(ich,t);
                }
        }

		public void betreteJuwelier(Spieler ich) {
			Juwelier j = new Juwelier();	
			shopLaufen(ich,j);
		}
		public void betreteBuchhandlung(Spieler ich) {
			Buchhandlung b = new Buchhandlung();
			shopLaufen(ich,b);
		}
		public void betreteSchmiede(Spieler ich) {
			Schmiede s = new Schmiede();
			shopLaufen(ich,s);
		}
		public void betreteTaverne(Spieler ich) {
			Taverne t = new Taverne();
			shopLaufen(ich,t);
		}
		
		private void shopLaufen(Spieler ich, Shops s) {
			SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
	        	   	ShopUIController shop = new ShopUIController(ich,s,frame,bewegung);
	           }
	      });
		}
		private void monsterLaufen(Spieler ich,Monster gegen){
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
		        	   	KampfUIController kampf = new KampfUIController(ich,gegen,frame,bewegung);
		           }
		      });
		}
		private void spielerLaufen(Spieler eins, Spieler zwei) {
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
		        	   	KampfUIController kampf = new KampfUIController(eins,zwei,frame,bewegung);
		           }
		      });
			
			
			
			
		}
		
		
		private JFrame frame;
	}