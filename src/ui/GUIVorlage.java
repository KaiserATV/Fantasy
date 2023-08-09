package ui;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.*;

public abstract class GUIVorlage {
    public GUIVorlage(JFrame ursprung){
       
        
        aktionZeilen = 0;
        ergebnisZeilen = 1;
        
        urCP = ursprung.getContentPane();
        
        
        main = ursprung;
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        
        contentPane.setVisible(true);
        contentPane.setSize(1000,1000);
        contentPane.setLayout(null);
        contentPane.setFocusable(false);
        
        altPane.setSize(1000,1000);
        altPane.setVisible(true);
        altPane.setLayout(null);
        altPane.setBackground(Color.cyan);
        altPane.setFocusable(false);

        //Feld für Infos - Farbe in Unterklassen implementieren
        info.setVisible(true);
        info.setFocusable(false);
        infoText.setVisible(true);
        infoText.setFont(schrift);
        infoText.setFocusable(false);
        contentPane.add(infoText);
        infoText.setHorizontalAlignment(JLabel.CENTER);
        infoText.setVerticalAlignment(JLabel.CENTER);
        
        //Feld für Bilder
        bildEbene.setVisible(true);
        bildEbene.setFocusable(false);
        bildEbene.setLayout(null);
        
        
        //Menu für Ausgabe über Aktionen - 
        aktionText.setVisible(true);
        aktionText.setFont(schrift);
        aktionText.setFocusable(false);
        aktionText.setBackground(Color.black);
        aktionText.setForeground(Color.white);
        
        
        //Menu für Entscheidung ob Flucht oder Angriff
        entscheid.setVisible(true);
        entscheid.setFocusable(false);
        entscheid.setLayout(null);
        entscheid.setLayout(null);
        entscheid.add(buttonLinks);
        entscheid.add(buttonRechts);
        
        //Menu für Ergebnis
        ergebnis.setVisible(true);
        ergebnis.setFont(schrift);
        ergebnis.setForeground(Color.white);
        ergebnis.setBackground(Color.black);
        ergebnis.setEditable(false);
        ergebnis.setText("");
        
        
        //Button Design
        buttonLinks.setBounds(100, 50,300, 50);
        buttonRechts.setBounds(600, 50, 300, 50);
        buttonLinks2.setBounds(100, 50,300, 50);
        buttonRechts2.setBounds(600, 50, 300, 50);
        buttons[0] = buttonLinks;
        buttons[1] = buttonLinks2;
        buttons[2] = buttonRechts;
        buttons[3] = buttonRechts2;
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
        
        //Setzen Position
        infoText.setBounds(0, 0, 1000, 50);
        info.setBounds(0, 0, 1000, 50);

        bildEbene.setBounds(0,50,1000,650);
        
        aktionText.setBounds(0,700, 1000,100);
        
        entscheid.setBounds(0, 800, 1000, 200);

        ergebnis.setBounds(0,800,1000,200);
        
        contentPane.add(info);
        contentPane.add(bildEbene);
        contentPane.add(aktionText);
        contentPane.add(entscheid);
        
        main.setSize(1000,1000);
        main.setVisible(true);
        
        
        main.setContentPane(contentPane);
    }
    /**
     *  Setzt Breite des Info Container
     * @param w - Die Breite in Pixel
     */
    public void setInfoWidth(int w){
		 info.setBounds(0, 0, w, 50);
		 info.repaint();
	 }
   /**
    * Setzt den infoText
    * @param s Der String, des Textes
    */
    public void setInfo(String s) {
    	infoText.setText(s);
    	infoText.repaint();
    }
    /**
     * Setzt die Farbe des InfoTextes
     * @param b - Die Farbe
     */
    public void setInfoColor(Color b) {
    	infoText.setForeground(b);
    	infoText.repaint();
    } 
    /**
     * Setzt den aktionText für eine Zeile
     * @see addAktion für mehrere Zeilen
     * @param s - Der String mit dem Text
     */
    public void setAktion(String s) {
    	aktionText.setText(s);
    }
    /**
     * Fügt den String mit einem Newline Zeichen hinzu
     * @param s - Der hinzuzufügende String
     */
    public void addAktion(String s) {
    	aktionText.append("\n"+s);
	}
    /**
     * 
     * @return den Inhalt von aktionText
     */
    public String getAktion() {
    	return aktionText.getText();
    }
    /**
     * Shortcut um den Aktiontext = "" zu setzten
     */
	public void clearAktion() {
		setAktion("");
		aktionZeilen =0;
	}
	public void beenden(){
		main.setContentPane(urCP);
	}
	/**
	 * Setzt den Text des rechten Buttons
	 * @param s - Text des Buttons
	 */
	public void setButtonRechtsText(String s) {
		buttonRechts.setText(s);
	}
	/**
	 * Setzt den Text des linken Buttons
	 * @param s - Text des Buttons
	 */
	public void setButtonLinksText(String s) {
		buttonLinks.setText(s);
	}
	/**
	 * Setzt den KeyListener des linken Buttons
	 * @param a - KeyAdapter für den Button
	 * @param int i - 0 wenn auf erster Ebene(entscheid), ansonsten für Button auf AngriffsEbene
	 */
	public void setKeyListenerLinks(KeyAdapter a,int i) {
		if(i == 0) {
			buttonLinks.addKeyListener(a);
		}else {
			buttonLinks2.addKeyListener(a);
		}
	}
	/**
	 * Setzt den KeyListener des rechten Buttons
	 * @param a - KeyAdapter für den Button
	 * @param int i - 0 wenn auf erster Ebene(entscheid), ansonsten für Button auf AngriffsEbene
	 */
	public void setKeyListenerRechts(KeyAdapter a, int i) {
		if(i == 0) {
			buttonRechts.addKeyListener(a);
		}else {
			buttonRechts2.addKeyListener(a);
		}	
	}
	/**
	 * Fügt die Ergebnisebene hinzu
	 */
	 public void setErgebnis() {
		 contentPane.add(ergebnis);
		 contentPane.repaint();
	 }
	/**
	 * Setzt den Text des Ergebnis zu dem String
	 * @param s - String welcher gesetzt wird
	 */
	 public void addErgebnis(String s) {
		 ergebnis.setFocusable(true);
		 ergebnis.setText(s);
		 
	 }
	 /**
	  * Fügt den Text mit einem Newline zeichen hinzu
	  * @param s - der hinzuzufügende Text
	  */
	 public void addErgebnisMulti(String s) {
		 ergebnis.append("\n"+s);
	 }
	 /**
	  * entfernt ergebnis von der Contentpane
	  */
	 public void clearErgebnis() {
		 contentPane.remove(ergebnis);
	 }
	 /**
	  * 
	  * @return - den Text von ergebnis
	  */
	 public String getErgebnis() {
		 return ergebnis.getText();
	 }
	 /**
	  * Fügt Entscheid der contentpane hinzu
	  */
	 public void setEntscheid() {
    	contentPane.add(entscheid);
    	contentPane.repaint();
    	buttonLinks.requestFocusInWindow();
	 }
	 /**
	  * entfernt entscheid von der contentpane
	  */
	 public void clearEntscheid() {
		contentPane.remove(entscheid);
	 }
	
    protected JButton[] buttons = new JButton[4];
    protected Font schrift = new Font("DejaVu Sans", Font.PLAIN, 17);
    
    protected JFrame main;
    
    protected JPanel altPane = new JPanel();
    protected JPanel contentPane = new JPanel();
    protected JPanel bildEbene = new JPanel();
    protected JPanel entscheid = new JPanel();
   
    
    protected JPanel info = new JPanel(new FlowLayout());
    protected JPanel aktion = new JPanel(new FlowLayout());
    
    protected JLabel infoText = new JLabel();
    protected JTextArea aktionText = new JTextArea();
	protected JTextArea ergebnis = new JTextArea();
    
    protected JButton buttonLinks = new JButton();
    protected JButton buttonRechts = new JButton();
    protected JButton buttonLinks2 = new JButton();
    protected JButton buttonRechts2 = new JButton();
    
    protected JPanel scrollHuelle = new JPanel();
    protected JScrollPane scroll;
    
	private int aktionZeilen;
	private int ergebnisZeilen;
	
	private Container urCP;
	private JFrame ur;
    
}
