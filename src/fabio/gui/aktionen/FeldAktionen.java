package fabio.gui.aktionen;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gebauede.*;
import uilogik.ShopUIController;
import lebewesen.Spieler;

public class FeldAktionen  {

        public void wahrscheinlichkeitMonsterInteraktion() {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

                if (chance < 20) { // 20% Chance auf Monsterinteraktion
                        // Methode für die Monsterinteraktion auf
                        System.out.println("Ein Monster taucht auf!");
                }
        }

        public void wahrscheinlichkeitHaendlerTreffen() {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

                if (chance < 10) { // 10% Chance auf fahrenden Händler
                        // Methode für den fahrenden Händler auf
                        System.out.println("Ein fahrender Händler wurde getroffen!");
                }
        }

		public void betreteJuwelier(Spieler ich, JFrame x) {
			Juwelier j = new Juwelier();	
			laufen(ich,j,x);
		}
		public void betreteBuchhandlung(Spieler ich, JFrame x) {
			Buchhandlung b = new Buchhandlung();
			laufen(ich,b,x);
		}
		public void betreteSchmiede(Spieler ich, JFrame x) {
			Schmiede s = new Schmiede();
			laufen(ich,s,x);
		}
		
		private void laufen(Spieler ich, Laden g, JFrame frame) {
			SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
	        	   	ShopUIController shop = new ShopUIController(ich,g,frame);
	           }
	      });
		}
	}