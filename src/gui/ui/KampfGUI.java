package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KampfGUI extends GUIVorlage{
	public KampfGUI() {
		super();
		
		info.setBackground(Color.red);
		bildEbene.setBackground(Color.DARK_GRAY);
		
		 //Menu für Entscheidung über Angriff oder Items
        angriff.setVisible(true);
        angriff.setFocusable(false);
        angriff.setBackground(Color.BLACK);
        angriff.add(buttonLinks2);
        angriff.add(buttonRechts2);
       
      
        //Menu für Items
        items.setVisible(true);
        items.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		items.setVisibleRowCount(2);
		items.setFixedCellHeight(50);
		items.setFixedCellWidth(500);
		items.setCellRenderer(new CustomCellRenderer());
		items.setBackground(Color.black);
		items.setForeground(Color.white);
		
		//Buttondesgin
		for(JButton b:buttons) {
			b.setForeground(Color.white);
			b.setBackground(Color.black);
			b.setBorder(null);
			b.setFocusPainted(false);
			b.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					e.getComponent().setBackground(Color.DARK_GRAY);
				}
				@Override
				public void focusLost(FocusEvent e) {
					e.getComponent().setBackground(Color.black);
				}
			});
		}
		buttonLinks.setBackground(Color.DARK_GRAY);
		
		//AktionsMenu
		aktion.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#454647")));
		
        
		//Spielerbilder
		spieler1.setVisible(true);
		spieler2.setVisible(true);
		spieler1.setSize(400,400);
		spieler2.setSize(175,175);
		
		
        //Menu für Ergebnis
        ergebnis.setVisible(true);
        ergebnis.setHorizontalAlignment(JLabel.CENTER);
        ergebnis.setVerticalAlignment(JLabel.NORTH);
        ergebnis.setFont(schrift);
        ergebnis.setForeground(Color.white);
        
        items.setBounds(0,800,1000,200);
        angriff.setBounds(0,800,1000,200);
        ergebnis.setBounds(0,800,1000,200);
        
        buttonLinks.setFocusable(true);
        buttonRechts.setFocusable(true);
        buttonLinks2.setFocusable(true);
        buttonRechts2.setFocusable(true);
        
        buttonLinks.setText("Kampf");
        buttonLinks2.setText("Angriff");
        buttonRechts.setText("Flucht");
        buttonRechts2.setText("Items");
        
        contentPane.repaint();
	}
	/**
	 * 
	 * @param rechts Key um nach rechts den Focus zu ändern
	 * @param links Key um den Focus nach links zu ändern
	 * @param zureuck Key um Menu zurück zu gehen
	 * @param auswahl Key um Button auszuwählen
	 */
	public void setTravKeys(int rechts, int links, int zurueck, KeyAdapter zu, int auswahl, KeyAdapter aus) {
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(rechts,0))));
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(links,0))));       
		items.setFocusTraversalKeysEnabled(false);
		items.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == rechts) {
					items.setSelectedIndex(items.getSelectedIndex()+1);
				}else if(e.getKeyCode() == links) {
					items.setSelectedIndex(items.getSelectedIndex()-1);
				}
			}
		});
		buttonLinks2.addKeyListener(zu);
		buttonRechts2.addKeyListener(zu);	
		items.addKeyListener(zu);
		items.addKeyListener(aus);
	}
	
	public void setButtonRechtsText(String s) {
		buttonRechts.setText(s);
	}
	public void setButtonLinksText(String s) {
		buttonLinks.setText(s);
	}
	
	public void setKeyListenerLinks(KeyAdapter a,int i) {
		if(i == 0) {
			buttonLinks.addKeyListener(a);
		}else {
			buttonLinks2.addKeyListener(a);
		}
	}
	public void setKeyListenerRechts(KeyAdapter a, int i) {
		if(i == 0) {
			buttonRechts.addKeyListener(a);
		}else {
			buttonRechts2.addKeyListener(a);
		}	
	}
	public void setErgebnisListener(KeyAdapter l) {
		for(int i = ergebnis.getKeyListeners().length; i > 0; i--) {
			ergebnis.removeKeyListener(ergebnis.getKeyListeners()[i-1]);
		}
		ergebnis.addKeyListener(l);
	}
	
	public void addAktion(String s) {
		aktionZeilen++;
		if(aktionZeilen > 6) {
			verkuerzen();
			aktionZeilen--;
		}
		System.out.println(s+" Zeile: "+aktionZeilen);
		String ausgang;
		if(getAktion().length() > 0) {
			ausgang = getAktion().substring(0,getAktion().length()-7)+"<br/>";	
		}else {
			ausgang = "<html>";
		}
		setAktion(ausgang+s+"</html>");
	}
	public void addAktion(String s, int time) {
		aktionZeilen++;
		if(aktionZeilen > 6) {
			verkuerzen();
			aktionZeilen--;
		}
		System.out.println(s+" Zeile: "+aktionZeilen);
		String ausgang;
		if(getAktion().length() > 0) {
			ausgang = getAktion().substring(0,getAktion().length()-7)+"<br/>";	
		}else {
			ausgang = "<html>";
		}
		for(int i = 0; i < s.length();i++) {
			setAktion(ausgang+s.substring(0,i)+"</html>");
			try {
				Thread.sleep(10L);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void clearAktion() {
		setAktion("");
		aktionZeilen =0;
	}
	
	private void verkuerzen() {
		String bearbeiten = getAktion();
		String neu;
		neu = bearbeiten;
		neu = neu.substring(bearbeiten.indexOf("/>")+2,bearbeiten.length());
		neu = "<html>"+neu;
		setAktion(neu);
	}
	
	
	public void setWinPane() {
		main.setContentPane(altPane);
	}
	
	 public void setEntscheid() {
    	contentPane.add(entscheid);
    	contentPane.repaint();
    	buttonLinks.requestFocusInWindow();
	 }
	 public void clearEntscheid() {
		contentPane.remove(entscheid);
	 }
	    
	 public void setAngriff() {
    	contentPane.add(angriff);
    	contentPane.repaint();
    	buttonLinks2.requestFocusInWindow();
	 }
	 public void clearAngriff() {
		 contentPane.remove(angriff);
	 }   
	 public void addErgebnis() {
		 contentPane.add(ergebnis);
		 ergebnis.requestFocusInWindow();
		 contentPane.repaint();
	 }
	 public void setErgebnis(String s) {
		 ergebnis.setText(s);
		 ergebnis.repaint();
	 }
	 public void clearErgebnis() {
		 contentPane.remove(ergebnis);
	 }
	 
	 public void setItems(String[] it) {
		contentPane.add(items);
		items.setListData(it);
		items.setSelectedIndex(0);
		items.requestFocusInWindow();
		contentPane.repaint();
	 }
	 public int getSelectedIndex() {
		 return items.getSelectedIndex();
	 }
	 public void clearItems() {
		 contentPane.remove(items);
	 }
	 public JLabel getErgebnis() {
		 return ergebnis;
	 }
	 public void setInfoWidth(int w){
		 info.setBounds(0, 0, w, 50);
		 info.repaint();
	 }
	 public void setSpieler1Bild(BufferedImage b) {
		 System.out.println(spieler1.getWidth());
		 Image bScale = b.getScaledInstance(spieler1.getWidth(), spieler1.getHeight(), Image.SCALE_FAST);
		 spieler1.setIcon(new ImageIcon(bScale));
		 bildEbene.add(spieler1);
		 System.out.println(spieler1.getWidth());
		 spieler1.setLocation(50, 130);
	 }
	 public void setSpieler2Bild(BufferedImage b) {
		 Image bScale = b.getScaledInstance(spieler2.getWidth(), spieler2.getHeight(),Image.SCALE_FAST);
		 spieler2.setIcon(new ImageIcon(bScale));
		 bildEbene.add(spieler2);
		 spieler2.setLocation(700, 225);
	 }
	  
	 
	private JPanel angriff = new JPanel();
	private JList<String> items = new JList<String>();
	private JLabel ergebnis = new JLabel();
	private int aktionZeilen=0;
	private JLabel spieler1 = new JLabel();
	private JLabel spieler2= new JLabel();
}
