package max.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public abstract class GUIVorlage {
    public GUIVorlage(JFrame ursprung){
        
        
        main = ursprung;
        urCP = main.getContentPane();
        urD = main.getSize();
        
        
        width = (int) Math.floor(urCP.getSize().getWidth()); 
        height = (int) Math.floor(urCP.getSize().getHeight());
        
        kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        KeyboardFocusManager.setCurrentKeyboardFocusManager(null);
        
        
        //Feld für Infos - Farbe in Unterklassen implementieren
        info.setFocusable(false);
        info.setLayout(null);
        info.add(infoText,0);
        info.add(infoFarbe,1);
        info.revalidate();
        info.repaint();
        infoText.setFont(schrift);
        infoText.setFocusable(false);
        
        //Feld für Bilder
        bildEbene.setFocusable(false);
        bildEbene.setLayout(null);
        
        
        //Menu für Ausgabe über Aktionen - 
        aktionText.setFont(schrift);
        aktionText.setFocusable(false);
        aktionText.setBackground(Color.black);
        aktionText.setForeground(Color.white);
        
        //Menu für entscheidung ob angelegt oder nicht
        anlegen.setLayout(new BorderLayout());
        anlegen.setBackground(Color.black);
        anlegen.add(anlegenLinks,BorderLayout.LINE_START);
        anlegen.add(anlegenRechts, BorderLayout.LINE_END);
        abfrage.setVisible(false);
        abfrage.setPreferredSize(new Dimension(width,40));
        abfrage.setFocusable(false);
        abfrage.setBorder(null);
        abfrage.setEditable(false);
        abfrage.setBackground(Color.black);
        abfrage.setForeground(Color.white);
        abfrage.setFont(schrift);
        anlegen.add(abfrage, BorderLayout.PAGE_START);
        
        
        //Menu für Entscheidung ob Flucht oder Angriff
        entscheid.setLayout(new BorderLayout());
        entscheid.add(buttonLinks,BorderLayout.LINE_START);
        entscheid.add(buttonRechts,BorderLayout.LINE_END);
        
        //Menu für Ergebnis
        ergebnis.setFont(schrift);
        ergebnis.setForeground(Color.white);
        ergebnis.setBackground(Color.black);
        ergebnis.setEditable(false);
        ergebnis.setText("");
        
        
        //Button Design

        buttons[0] = buttonLinks;
        buttons[1] = buttonLinks2;
        buttons[2] = anlegenLinks;
        buttons[3] = buttonRechts;
        buttons[4] = buttonRechts2;
        buttons[5] = anlegenRechts;
        for(JButton b:buttons) {
            b.setPreferredSize(new Dimension(Math.floorDiv(width, 4),  50));
        	b.setFont(schrift);
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
        infoText.setHorizontalTextPosition(JLabel.CENTER);
        
        info.setPreferredSize(new Dimension(width, (int)Math.floor(height*50.0/1000)));
        
        bildEbene.setPreferredSize(new Dimension(width, (int)Math.floor(height*650.0/1000)));
        
        aktionText.setPreferredSize(new Dimension(width, (int)Math.floor(height*100.0/1000)));
        
        entscheid.setPreferredSize(new Dimension(width,  (int)Math.floor(height*200.0/1000)));

        ergebnis.setPreferredSize(new Dimension(width, (int)Math.floor(height*200.0/1000)));
        
        anlegen.setPreferredSize(new Dimension(width, (int)Math.floor(height*200.0/1000)));
        
        
        layout = new GroupLayout(contentPane);
        
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        			.addComponent(info)
        			.addComponent(bildEbene,10,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        			.addComponent(aktionText,50,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        			.addComponent(entscheid,10,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        );
        layout.setHorizontalGroup(
        		layout.createParallelGroup()
        		.addComponent(info,20,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        		.addComponent(bildEbene,100,width,Short.MAX_VALUE)
        		.addComponent(aktionText,50,width,Short.MAX_VALUE)
        		.addComponent(entscheid,10,width,Short.MAX_VALUE)
        	);
        
        contentPane.setPreferredSize(urCP.getSize());
        layout.preferredLayoutSize(contentPane);
        
        contentPane.setLayout(layout);
        
        
        infoFarbe.setBackground(Color.red);
        infoText.setSize(new Dimension((int) Math.floor(info.getPreferredSize().getWidth()),(int)Math.floor(info.getPreferredSize().getHeight())));
        infoFarbe.setSize(new Dimension((int) Math.floor(info.getPreferredSize().getWidth()),(int)Math.floor(info.getPreferredSize().getHeight())));
        

        infoText.setHorizontalAlignment(SwingConstants.CENTER);
        infoText.setVerticalAlignment(SwingConstants.CENTER);
        
        main.setContentPane(contentPane);
        
        
        
        buttonLinks.requestFocusInWindow();
        
        main.pack();
        
    }
    //Funktion, wenn menu beendet
    public void beenden(){
		main.setContentPane(urCP);
		KeyboardFocusManager.setCurrentKeyboardFocusManager(kfm);
		main.setSize(urD);
	}
    
    
    //Getter - Setter
    
    /**
     *  Setzt Breite des Info Container
     * @param w - Die Breite in Pixel
     */
    public void setInfoWidth(int w){
   	infoFarbe.setSize(new Dimension(w,50));
   	info.setBackground(Color.black);
    	
    	
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
	 public abstract void setErgebnis(int i);
	/**
	 * Setzt den Text des Ergebnis zu dem String
	 * @param s - String welcher gesetzt wird
	 */
	 public void addErgebnis(String s) {
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
	  * 
	  * @return - den Text von ergebnis
	  */
	 public String getErgebnis() {
		 return ergebnis.getText();
	 }
	 /**
	  * Funktion um die Anlegen-Ebene hinzuzufügen
	  */
	 public void addAnlegen() {
			layout.replace(ergebnis, anlegen);
			anlegenLinks.requestFocusInWindow();
		}
	 /**
	  * Funktion um die Anlegen-Ebene hinzuzufügen
	  * @param String s - text des Textfeldes
	  */
	 public void addAnlegen(String s) {
		 abfrage.setVisible(true);
		 abfrage.setText(s);
		 layout.replace(ergebnis,anlegen);
		 anlegenLinks.requestFocusInWindow();
		 contentPane.repaint();
	 }
	 /**
	  * Funktion um den Buttons in der Anlegen-Ebene ihre Funktion zuzuweisen
	  * @param links - ActionListener für den linken Button
	  * @param rechts - ActionListener für den rechten Button
	  */
	 public void anlegenListener(ActionListener links, ActionListener rechts) {
		 anlegenLinks.addActionListener(links);
		 anlegenRechts.addActionListener(rechts);
	 }
	 /**
	  * Fügt Entscheid der contentpane hinzu
	  * @param i = 1, wenn aus angriff
	  */
	 public abstract void setEntscheid(int i); 
	 
	 
	 public void setBackground(BufferedImage b) {
		 background = b;
		 bildEbene.setIcon(new ImageIcon(b.getScaledInstance((int) Math.floor(bildEbene.getPreferredSize().getWidth()),(int)Math.floor(bildEbene.getPreferredSize().getHeight()), Image.SCALE_FAST)));
	 }
	 
	 
	 
	
	protected int width;
	protected int height;
	protected KeyboardFocusManager kfm;
    protected JButton[] buttons = new JButton[6];
    
    protected JFrame main;
    
    protected Font schrift = new Font("DejaVu Sans", Font.PLAIN, 17);
    protected JPanel contentPane = new JPanel();
    protected JLabel bildEbene = new JLabel();
    protected JPanel entscheid = new JPanel();
    protected JPanel anlegen = new JPanel();
   
    
    protected JPanel info = new JPanel();
    protected JPanel aktion = new JPanel(new FlowLayout());
    protected JPanel infoFarbe = new JPanel();
    
    protected JLabel infoText = new JLabel();
    protected JTextArea aktionText = new JTextArea();
	protected JTextArea ergebnis = new JTextArea();
	protected JTextField abfrage = new JTextField();
    
    protected JButton buttonLinks = new JButton();
    protected JButton buttonRechts = new JButton();
    protected JButton buttonLinks2 = new JButton();
    protected JButton buttonRechts2 = new JButton();
    protected JButton anlegenLinks = new JButton("Anlegen");
    protected JButton anlegenRechts = new JButton("Ignorieren");
    
    protected JPanel scrollHuelle = new JPanel();
    protected JScrollPane scroll;
	
	private Container urCP;
	private Dimension urD;
	
	protected BufferedImage background;
	
	protected GroupLayout layout;
	
    
}
