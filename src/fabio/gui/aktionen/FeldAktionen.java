package fabio.gui.aktionen;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ani.fantasyShops.*;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.Spieler;
import max.uilogik.KampfUIController;
import max.uilogik.ShopUIController;

public class FeldAktionen  {
	
		public FeldAktionen(JFrame jf) {
			frame = jf;
		}

        public void wahrscheinlichkeitMonsterInteraktion(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99


    			Monster m = new Monster();
    			
                if (chance < 0) { // 20% Chance auf Monsterinteraktion
                        monsterLaufen(ich,m);
                        
                }
        }
        public void spielerKampf(Spieler eins, Spieler zwei) {
        	spielerLaufen(eins,zwei);
        }
        
        public void wahrscheinlichkeitHaendlerTreffen(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

                Buchhandlung b = new Buchhandlung();
                
                if (chance < 1) { // 10% Chance auf fahrenden Händler
                        shopLaufen(ich,b);
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
		
		private void shopLaufen(Spieler ich, Shops s) {
			SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
	        	   	ShopUIController shop = new ShopUIController(ich,s,frame);
	           }
	      });
		}
		private void monsterLaufen(Spieler ich,Monster gegen){
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
		        	   	KampfUIController kampf = new KampfUIController(ich,gegen,frame);
		           }
		      });
		}
		private void spielerLaufen(Spieler eins, Spieler zwei) {
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
		        	   	KampfUIController kampf = new KampfUIController(eins,zwei,frame);
		           }
		      });
			
			
			
			
		}
		
		
		private JFrame frame;
	}