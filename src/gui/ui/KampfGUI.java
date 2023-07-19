package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class KampfGUI extends GUIVorlage{
	public KampfGUI() {
		super();
		info.setBackground(Color.red);
		
		 //Menu für Entscheidung über Angriff oder Items
        angriff.setVisible(true);
        angriff.setBackground(Color.yellow);
       
      
        //Menu für Items
        items.setVisible(true);
        items.setBackground(Color.black);
        
        //Menu für Ergebnis
        ergebnis.setVisible(true);
        ergebnis.setHorizontalAlignment(JLabel.CENTER);
        ergebnis.setVerticalAlignment(JLabel.CENTER);
        
        items.setBounds(0,800,1000,200);
        angriff.setBounds(0,800,1000,200);
        ergebnis.setBounds(0,800,1000,200);
        
        
        
	}
	public void setWinPane(String gewinner, String verlierer) {
		main.setContentPane(altPane);
		String text = gewinner+" schlaegt "+ verlierer+"!";
		JLabel eL = new JLabel(text);
		eL.setVisible(true);
		main.getContentPane().add(eL);
		eL.setBounds(100,100,400,600);
		
	}
	
	 public void setEntscheid() {
    	contentPane.add(entscheid);
    	entscheid.add(buttonLinks);
        entscheid.add(buttonRechts);
    	contentPane.repaint();
	 }
	 public void clearEntscheid() {
		contentPane.remove(entscheid);
	 }
	    
	 public void setAngriff() {
    	contentPane.add(angriff);
    	angriff.add(buttonLinks);
        angriff.add(buttonRechts);
        angriff.add(buttonZurueck);
    	contentPane.repaint();
	 }
	 public void clearAngriff() {
		 contentPane.remove(angriff);
	 }   
	 public void addErgebnis() {
		 contentPane.add(ergebnis);
		 contentPane.repaint();
	 }
	 public void setErgebnis(String s) {
		 ergebnis.setText(s);
		 ergebnis.repaint();
	 }
	 public void clearErgebnis() {
		 contentPane.remove(ergebnis);
	 }
	 
	 public void setItems() {
		contentPane.add(items);
		items.add(buttonZurueck);
		contentPane.repaint();
	 }
	 public void clearItems() {
		 contentPane.remove(items);
	 }
	 public JLabel getErgebnis() {
		 return ergebnis;
	 }
	 public void setInfoWidth(int w){
		 info.setBounds(0, 0, w, 50);
	 }
	 
	private JPanel angriff = new JPanel();
	private JPanel items = new JPanel();
	private JLabel ergebnis = new JLabel();
}
