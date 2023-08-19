package max.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
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
		contentPane.setBackground(Color.black);
		
		
		 //Menu für Entscheidung über Angriff oder Items
        angriff.setVisible(true);
        angriff.setFocusable(false);
        angriff.setLayout(null);
        angriff.setBackground(Color.BLACK);
        angriff.setLayout(new BorderLayout());
        angriff.add(buttonLinks2,BorderLayout.LINE_START);
        angriff.add(buttonRechts2,BorderLayout.LINE_END);
       
      
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
		spieler1.setSize(300,300);
		spieler2.setSize(175,175);
		 
		spieler1.setLocation((int)Math.floor((50/1000)*width), 350); 
		spieler2.setLocation((int)Math.floor((420/1000) * width), 225);
		bildEbene.add(spieler1);
		bildEbene.add(spieler2);
		 
		
        scrollHuelle.setVisible(true);
        scrollHuelle.setPreferredSize(new Dimension(width,200));
        angriff.setPreferredSize(new Dimension(width,200));
        
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
	/**
	 * @param i - 1 wenn aus angriff
	 */
	public void setErgebnis(int i) {
		if(i == 1) {
			layout.replace(angriff, ergebnis);	
		}else {
			layout.replace(anlegen, ergebnis);
		}
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
		
		items.setMinimumSize(new Dimension(width,200));
		items.setBackground(Color.black);
        scroll.setPreferredSize(new Dimension(width,200));
        scroll.getViewport().getView().setPreferredSize(new Dimension(width, 200));
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
		layout.replace(angriff, scrollHuelle);
		items.setSelectedIndex(0);
		items.requestFocusInWindow();
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
	 * @param oben Key um nach oben den Focus zu ändern
	 * @param unten Key um den Focus unten links zu ändern
	 * @param zureuck Key um Menu zurück zu gehen
	 * @param auswahl Key um Button auszuwählen
	 */
	public void setTravKeys(int rechts, int links, int oben, int unten, KeyAdapter zu, KeyAdapter aus) {
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(rechts,0))));
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(links,0))));     
		
		
		items.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "none");
		items.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "none");
		items.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "none");
		items.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "none");
		items.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0), "none");
			
		

		items.setFocusTraversalKeysEnabled(false);
	
		
		items.getInputMap().put(KeyStroke.getKeyStroke(links,0), "selectPreviousColumn");
		items.getInputMap().put(KeyStroke.getKeyStroke(rechts,0), "selectNextColumn");
		items.getInputMap().put(KeyStroke.getKeyStroke(oben,0), "selectPreviousRow");
		items.getInputMap().put(KeyStroke.getKeyStroke(unten,0), "selectNextRow");
	
		
		
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
     * Fügt die Angriffsebene der contentpane hinzu
     * @param zahl 1- wenn von items, 0 - wenn von entscheid
     */
	 public void setAngriff(int i) {
		 if(i == 1) {
			 layout.replace(scrollHuelle, angriff);
		 }else {
			 layout.replace(entscheid, angriff);
		 }
    	contentPane.repaint();
    	buttonLinks2.requestFocusInWindow();
	 } 
	 /**
	  * Funktion um den ausgewählten Indey von den Items zu bestimmen
	  * @return den ausgewählten Index
	  */
	 public int getSelectedIndex() {
		 return items.getSelectedIndex();
	 }
	 /**
	  * Funktion um Bild von der ersten Person zu setzen
	  * @param b - BufferedImage für das Bild der ersten Person
	  */
	 public void setSpieler1Bild(BufferedImage b) {
		 Image bScale = b.getScaledInstance(spieler1.getWidth(), spieler1.getHeight(), Image.SCALE_FAST);
		 spieler1.setIcon(new ImageIcon(bScale));
	 }
	 /**
	  * Funktion um Bild von der ersten Person zu setzen
	  * @param b - BufferedImage für das Bild der ersten Person
	  */
	 public void setSpieler2Bild(BufferedImage b) {
		 Image bScale = b.getScaledInstance(spieler2.getWidth(), spieler2.getHeight(),Image.SCALE_FAST);
		 spieler2.setIcon(new ImageIcon(bScale));
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
	 
	 @Override
	 public void setEntscheid(int i) {
		 if(i == 1) {
			 layout.replace(angriff, entscheid);	 
		 }else {
			 layout.replace(ergebnis, entscheid);
		 }
		 buttonLinks.requestFocusInWindow();
	 }
	 
	 
	 
	 
	 
	 
	 

	DefaultListModel<String> d = new DefaultListModel<String>();
	private JPanel angriff = new JPanel();
	private JList<String> items = new JList<String>();
	private JLabel spieler1 = new JLabel();
	private JLabel spieler2= new JLabel();
	
	
}
