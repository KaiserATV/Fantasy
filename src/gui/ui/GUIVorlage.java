package ui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public abstract class GUIVorlage {
    public GUIVorlage(){
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane.setSize(1000,1000);
        contentPane.setLayout(null);
        contentPane.setFocusable(false);
        contentPane.setBackground(Color.black);
        
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
        aktion.setVisible(true);
        aktion.setFocusable(false);
        aktion.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        aktion.setBackground(Color.BLACK);
        aktionText.setVisible(true);
        aktionText.setFont(schrift);
        aktionText.setForeground(Color.white);
        aktionText.setFocusable(false);
        aktionText.setHorizontalAlignment(JLabel.LEADING);
        aktion.add(aktionText);
        
        //Menu für Entscheidung ob Flucht oder Angriff
        entscheid.setVisible(true);
        entscheid.setFocusable(false);
        entscheid.setLayout(null);
        entscheid.setLayout(null);
        entscheid.setBackground(Color.BLACK);
        entscheid.add(buttonLinks);
        entscheid.add(buttonRechts);
        
        
        //Button Design
        buttonLinks.setBounds(100, 50,300, 50);
        buttonRechts.setBounds(600, 50, 300, 50);
        buttonLinks2.setBounds(100, 50,300, 50);
        buttonRechts2.setBounds(600, 50, 300, 50);
        buttons[0] = buttonLinks;
        buttons[1] = buttonLinks2;
        buttons[2] = buttonRechts;
        buttons[3] = buttonRechts2;
        
        
        //Setzen Position
        infoText.setBounds(0, 0, 1000, 50);
        info.setBounds(0, 0, 1000, 50);

        bildEbene.setBounds(0,50,1000,650);
        
        aktion.setBounds(0, 700, 1000, 100);
        aktionText.setBounds(0,700, 1000,100);
        
        entscheid.setBounds(0, 800, 1000, 200);
        
        contentPane.add(info);
        contentPane.add(bildEbene);
        contentPane.add(aktion);
        contentPane.add(entscheid);
        
        main.setSize(1000,1000);
        main.setVisible(true);
        main.setContentPane(contentPane);
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
    
    public void setInfoColor(Color b) {
    	infoText.setForeground(b);
    	infoText.repaint();
    }

    protected JButton[] buttons = new JButton[4];
    protected Font schrift = new Font("DejaVu Sans", Font.PLAIN, 17);
    
    protected JFrame main = new JFrame();
    
    protected JPanel altPane = new JPanel();
    protected JPanel contentPane = new JPanel();
    protected JPanel bildEbene = new JPanel();
    protected JPanel entscheid = new JPanel();
   
    
    protected JPanel info = new JPanel(new FlowLayout());
    protected JPanel aktion = new JPanel(new FlowLayout());
    
    protected JLabel infoText = new JLabel();
    protected JLabel aktionText = new JLabel();
    
    protected JButton buttonLinks = new JButton();
    protected JButton buttonRechts = new JButton();
    protected JButton buttonLinks2 = new JButton();
    protected JButton buttonRechts2 = new JButton();
    
}
