package fabio.gui.aktionen;

import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Map;
import java.util.HashMap;
import ani.fantasyShops.*;
import fabio.spielerbewegung.SpielerBewegung;
import ani.fantasyLebewesen.nsc.Beluaferus;
import ani.fantasyLebewesen.nsc.Monster;
import ani.fantasyLebewesen.nsc.Volares;
import ani.fantasyLebewesen.spieler.Spieler;
import max.uilogik.KampfUIController;
import max.uilogik.ShopUIController;

public class FeldAktionen  {
	
		public FeldAktionen(JFrame jf, SpielerBewegung bew) {
			frame = jf;
			bewegung = bew;
		}
		private SpielerBewegung bewegung;
		private Point position;
		private Map<Point, Shops> shopsAtPositions = new HashMap<>();

		
        public void wahrscheinlichkeitMonsterInteraktion(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99
                Monster m;
                
                if(rand.nextInt(2) == 0) {
                	m = new Beluaferus();
                }else {
                	m = new Volares();
                }
                
                
                if (chance < 10) { // 10% Chance auf Monsterinteraktion
                        monsterLaufen(ich,m);
                        
                }
        }
        public void spielerKampf(Spieler eins, Spieler zwei) {
        	spielerLaufen(eins,zwei);
        }
        
        public void setPosition(Point newPosition) {
            this.position = newPosition;
        }

        
        public void wahrscheinlichkeitHaendlerTreffen(Spieler ich) {
                Random rand = new Random();
                int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

				TravelingMerchant t = new TravelingMerchant(position);
                
                if (chance < 8) { // 8% Chance auf fahrenden Händler
                        shopLaufen(ich,t);
                }
        }

        public void betreteJuwelier(Spieler ich) {
            Juwelier j;
            if(shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Juwelier) {
                j = (Juwelier) shopsAtPositions.get(position);
            } else {
                j = new Juwelier(position);
                shopsAtPositions.put(position, j);
            }
            shopLaufen(ich, j);
        }

        public void betreteBuchhandlung(Spieler ich) {
            Buchhandlung b;
            if(shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Buchhandlung) {
                b = (Buchhandlung) shopsAtPositions.get(position);
            } else {
                b = new Buchhandlung(position);
                shopsAtPositions.put(position, b);
            }
            shopLaufen(ich, b);
        }
        
        public void betreteSchmiede(Spieler ich) {
            Schmiede s;
            if(shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Schmiede) {
                s = (Schmiede) shopsAtPositions.get(position);
            } else {
                s = new Schmiede(position);
                shopsAtPositions.put(position, s);
            }
            shopLaufen(ich, s);
        }

        public void betreteTaverne(Spieler ich) {
            Taverne t;
            if(shopsAtPositions.containsKey(position) && shopsAtPositions.get(position) instanceof Taverne) {
                t = (Taverne) shopsAtPositions.get(position);
            } else {
                t = new Taverne(position);
                shopsAtPositions.put(position, t);
            }
            shopLaufen(ich, t);
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