package ui;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

public abstract class GUIVorlage {
    public GUIVorlage(){
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setBackground(Color.white);
        
        contentPane.setSize(1000,1000);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.black);
        
        altPane.setSize(1000,1000);
        altPane.setVisible(true);
        altPane.setLayout(null);
        altPane.setBackground(Color.cyan);

        //Feld für Infos - Farbe in Unterklassen implementieren
        info.setVisible(true);
        infoText.setVisible(true);
        info.add(infoText);
        
        //Feld für Bilder
        bildEbene.setVisible(true);
        bildEbene.setBackground(Color.gray);
        
        //Menu für Ausgabe über Aktionen - 
        aktion.setVisible(true);
        aktion.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        aktion.setBackground(Color.green);
        aktionText.setVisible(true);
        aktionText.setHorizontalAlignment(JLabel.LEADING);
        aktion.add(aktionText);
        
        //Menu für Entscheidung ob Flucht oder Angriff
        entscheid.setVisible(true);
        entscheid.setLayout(null);
        entscheid.setBackground(Color.red);
        entscheid.setLayout(null);
        fragenText.setVisible(true);
        entscheid.add(fragenText);
        entscheid.add(buttonLinks);
        entscheid.add(buttonRechts);
//        entscheid.add(buttonZurueck);
        
        
        //Button Design
        buttonLinks.setBounds(100, 50,300, 50);
        buttonRechts.setBounds(600, 50, 300, 50);
        buttonZurueck.setBounds(960,0,40,40);
        
        
        //Setzen Position
        info.setBounds(0, 0, 1000, 50);

        bildEbene.setBounds(0,50,1000,650);
        
        aktion.setBounds(0, 700, 1000, 100);
        aktionText.setBounds(0,700, 1000,100);
        
        entscheid.setBounds(0, 800, 1000, 200);
        fragenText.setBounds(0,0,1000,20);
        fragenText.setHorizontalAlignment(JLabel.CENTER);
        
        contentPane.add(info);
        contentPane.add(bildEbene);
        contentPane.add(aktion);
        contentPane.add(entscheid);
        
        main.setSize(1000,1000);
        main.setVisible(true);
        main.setContentPane(contentPane);
    }
    public JButton getLinksButton() {
    	return buttonLinks;
    }
    public JButton getRechtsButton() {
    	return buttonRechts;
    }
    public JButton getZurueckButton() {
    	return buttonZurueck;
    }
   
    public void setInfo(String s) {
    	infoText.setText(s);
    	infoText.repaint();
    }
    
    public String getInfo() {
    	return infoText.getText();
    }
    
    public void setAktion(String s) {
    	aktionText.setText(s);
    	aktionText.repaint();
    }
   
    public String getAktion() {
    	return aktionText.getText();
    }
    
    public void setFragenText(String s) {
    	fragenText.setText(s);
    	fragenText.repaint();
    }
    public String getFragenText() {
    	return fragenText.getText();
    }
    
    protected JFrame main = new JFrame();
    
    protected JPanel altPane = new JPanel();
    protected JPanel contentPane = new JPanel();
    protected JPanel bildEbene = new JPanel();
    protected JPanel entscheid = new JPanel();
   
    
    protected JPanel info = new JPanel(new FlowLayout());
    protected JPanel aktion = new JPanel(new FlowLayout());
    
    protected JLabel infoText = new JLabel();
    protected JLabel aktionText = new JLabel();
    protected JLabel fragenText = new JLabel();
    
    protected JButton buttonLinks = new JButton();
    protected JButton buttonRechts = new JButton();
    
    protected JButton buttonZurueck = new JButton();
    
}
