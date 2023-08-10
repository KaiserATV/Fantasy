package max.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class KampfGUI extends GUIVorlage{
	public KampfGUI(JFrame x) {
		super(x);
		info.setBackground(Color.red);
		bildEbene.setBackground(Color.DARK_GRAY);
		contentPane.setBackground(Color.black);
		
		 //Menu für Entscheidung über Angriff oder Items
        angriff.setVisible(true);
        angriff.setFocusable(false);
        angriff.setLayout(null);
        angriff.setBackground(Color.BLACK);
        angriff.add(buttonLinks2);
        angriff.add(buttonRechts2);
       
      
        //Menu für Items
        items.setVisible(true);
        items.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		items.setFixedCellHeight(100);
		items.setFixedCellWidth(500);
		items.setCellRenderer(new CustomCellRenderer());
		items.setBackground(Color.black);
		items.setForeground(Color.white);
		
		
		
		buttonLinks.setBackground(Color.DARK_GRAY);
		
		//AktionsMenu
		aktion.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#454647")));
		aktion.setBackground(Color.BLACK);
        entscheid.setBackground(Color.BLACK);
        aktionText.setForeground(Color.white);
        
        
		//Spielerbilder
		spieler1.setVisible(true);
		spieler2.setVisible(true);
		spieler1.setSize(400,400);
		spieler2.setSize(175,175);
		
		
        scrollHuelle.setVisible(true);
        scrollHuelle.setBounds(0,800,1000,200);
        angriff.setBounds(0,800,1000,200);
        
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
	
	@Override
	public void setErgebnis() {
		 contentPane.add(ergebnis);
		 ergebnis.repaint();
		 ergebnis.requestFocusInWindow();
	 }
	/**
	 * Funktion um die Items, die ein Spieler hat zu initialisieren
	 * @param it - Ein Array, welcher die Items die der Spieler hat und ihre Effekte beeinhaltet
	 */
	public void setItemData(String[] it) {
		d.removeAllElements();
		for(String s:it) {
			d.addElement(s);
		}
		
		items.setModel(d);
		items.setVisibleRowCount((int) Math.ceil(it.length/2)+1);
		scroll = new JScrollPane(items);
		

		scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		items.setMinimumSize(new Dimension(1000,200));
		items.setBackground(Color.black);
        scroll.setSize(1000,200);
        scroll.getViewport().getView().setSize(1000, 200);
		scroll.getInsets(null);
		scroll.setBackground(Color.black);
		
		scrollHuelle.setBackground(Color.black);
		scrollHuelle.add(scroll);
		scroll.repaint();
		
	}
	
	/*
	 * fügt die items zu der contentpane hinzu
	 */
	public void setItems() {
		contentPane.add(scrollHuelle);
		items.setSelectedIndex(0);
		items.requestFocusInWindow();
		scrollHuelle.repaint();
		
		//Probleme hier, wenn nicht zu menu zurück gekehrt - Keine Ahnung wie zu fixen, aber das wirkt die Lösung vorerst
		
	}
	/**
	 * Entfernt ein Item aus der Liste der Items
	 * @param selIndex - der Index welcher entfernt werden soll
	 */
	public void removeItem(int selIndex) {
		d.removeElementAt(selIndex);
		items.setVisibleRowCount((int) Math.ceil(d.getSize()/2)+1);
		items.repaint();
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
		items.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				items.ensureIndexIsVisible(items.getSelectedIndex());
			}
			
		});
		buttonLinks2.addKeyListener(zu);
		buttonRechts2.addKeyListener(zu);	
		items.addKeyListener(zu);
		items.addKeyListener(aus);
	}
	/**
	 * Funktion die aufgerufen wird, wenn eine Person gewonnen
	 */
	public void setWinPane() {
		main.setContentPane(altPane);
	}
    /**
     * Fügt die Angriffsebene der contentpane hinzu
     */
	 public void setAngriff() {
    	contentPane.add(angriff);
    	contentPane.repaint();
    	buttonLinks2.requestFocusInWindow();
	 }
	 /**
	  * Entfernt die Angriffseben von der Contentpane
	  */
	 public void clearAngriff() {
		 contentPane.remove(angriff);
	 }   
	 /**
	  * Funktion um den ausgewählten Indey von den Items zu bestimmen
	  * @return den ausgewählten Index
	  */
	 public int getSelectedIndex() {
		 return items.getSelectedIndex();
	 }
	 /**
	  * Funktion um die Items von der Contentpane zu entfernen
	  */
	 public void clearItems() {
		 contentPane.remove(scrollHuelle);
	 }
	 /**
	  * Funktion um Bild von der ersten Person zu setzen
	  * @param b - BufferedImage für das Bild der ersten Person
	  */
	 public void setSpieler1Bild(BufferedImage b) {
		 Image bScale = b.getScaledInstance(spieler1.getWidth(), spieler1.getHeight(), Image.SCALE_FAST);
		 spieler1.setIcon(new ImageIcon(bScale));
		 bildEbene.add(spieler1);
		 spieler1.setLocation(50, 130);
	 }
	 /**
	  * Funktion um Bild von der ersten Person zu setzen
	  * @param b - BufferedImage für das Bild der ersten Person
	  */
	 public void setSpieler2Bild(BufferedImage b) {
		 Image bScale = b.getScaledInstance(spieler2.getWidth(), spieler2.getHeight(),Image.SCALE_FAST);
		 spieler2.setIcon(new ImageIcon(bScale));
		 bildEbene.add(spieler2);
		 spieler2.setLocation(700, 225);
	 }
	 /**
	  * fügt dem ergebnis ein Keylistener hinzu 
	  * @param l
	  */
	 public void setErgebnisListener(KeyAdapter l) {
			for(int i = ergebnis.getKeyListeners().length; i > 0; i--) {
				ergebnis.removeKeyListener(ergebnis.getKeyListeners()[i-1]);
			}
			ergebnis.addKeyListener(l);
		}
	  

	DefaultListModel<String> d = new DefaultListModel<String>();
	private JPanel angriff = new JPanel();
	private JList<String> items = new JList<String>();
	private JLabel spieler1 = new JLabel();
	private JLabel spieler2= new JLabel();
}