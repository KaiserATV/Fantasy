package ui;

import java.awt.Color;

import javax.swing.JPanel;

public class KampfGUI extends GUIVorlage{
	public KampfGUI() {
		super();
		
		 //Menu für Entscheidung über Angriff oder Items
        angriff.setVisible(true);
        angriff.setBackground(Color.yellow);
       
      
        //Menu für Items
        items.setVisible(true);
        items.setBackground(Color.black);
        
        
        items.setBounds(0,800,1000,200);
        angriff.setBounds(0,800,1000,200);
	}
	 public void setEntscheid() {
    	contentPane.add(entscheid);
    	entscheid.add(buttonLinks);
        entscheid.add(buttonRechts);
    	contentPane.repaint();
	 }
	 public void clearEntscheid() {
		contentPane.remove(entscheid);
//		entscheid.remove(buttonLinks);
//        entscheid.remove(buttonRechts);
//        entscheid.remove(buttonZurueck);
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
//		 angriff.remove(buttonLinks);
//	     angriff.remove(buttonRechts);
//	     angriff.remove(buttonZurueck);
	 }   
	 
	 
	 public void setItems() {
		contentPane.add(items);
		items.add(buttonZurueck);
		contentPane.repaint();
	 }
	 public void clearItems() {
		 contentPane.remove(items);
		 //items.remove(buttonZurueck);
	 }
	    
	private JPanel angriff = new JPanel();
	private JPanel items = new JPanel();
}
