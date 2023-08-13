package fabio.spiel;

import java.awt.Point;
import java.awt.Window;
import fabio.gui.karte.Karte;
import fabio.gui.karte.Karte.FeldTyp;
import fabio.gui.spielerDialog.SpielerDialog;
import fabio.spielerbewegung.SpielerBewegung;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ani.fantasyLebewesen.Spieler;

public class Spiel {

        private Karte karte = new Karte();
        private List < Spieler > alleSpieler = new ArrayList < > ();
        private static SpielerBewegung spielerBewegung;
        private Spiel spiel;
        private ArrayList<String> namen = new ArrayList<String>();
        
        public FeldTyp getFeldTypAtPosition(Point point) {
                return karte.getFeldTypAtPosition(point);
        }

        public Spiel() {
                // Spiel-Instanz erstellen
        		spiel = this;
        		
        		SwingUtilities.invokeLater(new Runnable() {
    				
    				public void run() {
    		        	   	SpielerDialog s = new SpielerDialog(karte.getFrame(),true,spiel);
    		        	    s.addWindowListener(new java.awt.event.WindowAdapter() {
    		        	        public void windowClosing(java.awt.event.WindowEvent e) {
    		        	            ((Window) e.getComponent()).dispose();
    		        	        }
    		        	    });
    		           }
    		      });
      		
        		
        }
        public ArrayList<String> getNamen() {
        	return namen;
        }
        public void addNamen(String s) {
        	s = s.replaceAll("\\s","");
        	namen.add(s);
        }
        public int getSpielerAnzahl() {
        	return alleSpieler.size();
        }
        public void spielerAdd(Spieler spieler) {
    	   // Spieler zur Liste hinzufügen
    		spiel.alleSpieler.add(spieler);
        }
        public void spielern() {
        	System.out.println(alleSpieler.size());
        	// Aktuellen Spieler setzen (zum Beispiel zu Beginn des Spiels)
	        Spieler.setAktuellerSpieler(alleSpieler.get(0));

	        for(Spieler player:alleSpieler) {
            	spiel.karte.addSpieler(player);                 	
            }
	        
	        if (alleSpieler.size() !=0 && alleSpieler.size()<5) {
                // Aktuellen Spieler bewegen (anhand von Koordinaten)
               
	        	
                spielerBewegung = new SpielerBewegung(spiel.karte, spiel.karte.getFrame(), spiel.alleSpieler);
                // KartenPanel-Instanz aus dem Karte-Objekt holen
                Karte.KartenPanel kartenPanel = spiel.karte.new KartenPanel();
                // Das KartenPanel neu zeichnen, um die Spielerpositionen zu aktualisieren
                kartenPanel.grabFocus();
                kartenPanel.repaint();
        }
       }

		public boolean gibtNamen(String text) {
			text = text.replaceAll("\\s","");
			if(namen.contains(text)) {
				return false;
			}else {
				return true;	
			}
		}
        
        
        
        
}