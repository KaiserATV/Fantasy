package max.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShopGUI extends GUIVorlage{
	public ShopGUI(JFrame x) {
		super(x);
		
		
		infoText.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.black));
		info.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		
		aktionText.setForeground(Color.white);
		aktionText.setBackground(Color.black);
		info.setBackground(Color.decode("#e0bd0f"));
		bildEbene.setBackground(Color.gray);
		entscheid.setBackground(Color.black);
		
		
		
		scrollHuelle.setVisible(true);
		scrollHuelle.setBounds(bildEbene.getBounds());
		scrollHuelle.setLayout(new BorderLayout());
		
	}
	/**
	 * 
	 * @param rechts Key für Fokus nach rechts
	 * @param links	Key für Fokus nach links
	 * @param zu KeyListener zum zurueck zukehren aus auswahl Menu
	 * @param auswahl Key für Auswahl
	 * @param aus KeyListener welcher bei auswahl feuert (inventar)
	 */
	public void setTravKeys(int rechts, int links, int oben, int unten ,KeyAdapter zu, int auswahl, KeyAdapter aus) {
		inventar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "none");
		inventar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "none");
		inventar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "none");
		inventar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "none");
		inventar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0), "none");
		
		inventar.getActionMap().put("HEY", null);
		
		
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(rechts,0))));
		contentPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke(links,0))));     
		
		inventar.setFocusTraversalKeysEnabled(false);
		inventar.addKeyListener(aus);
		inventar.addKeyListener(zu);
		
		
		inventar.getInputMap().put(KeyStroke.getKeyStroke(links,0), "selectPreviousRow");
		inventar.getInputMap().put(KeyStroke.getKeyStroke(rechts,0), "selectNextRow");
		inventar.getInputMap().put(KeyStroke.getKeyStroke(oben,0), "selectPreviousRow");
		inventar.getInputMap().put(KeyStroke.getKeyStroke(unten,0), "selectNextRow");
		
		
		
		
	}
	
	public void setKaufMenuData(String[][] daten) {
		String[] header ={"Name", "Effekt", "Preis"};
		

		dtm = new DefaultTableModel(daten,header);
		inventar = new JTable(dtm) {

			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			
			}
		};
		
		//idk glaube nicht
		inventar.setFocusTraversalKeysEnabled(false);

		
		//Einstellung für Auswahlen
		inventar.setCellSelectionEnabled(false);
		inventar.setColumnSelectionAllowed(false);
		inventar.setRowSelectionAllowed(true);
		inventar.setCellEditor(null);
		inventar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventar.getTableHeader().setReorderingAllowed(false);
		
		//zentraler Text
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
		dcr.setHorizontalAlignment(JLabel.CENTER);
		dcr.setVerticalAlignment(JLabel.CENTER);
		
		
		//Dimensionen
		for(int i=0; i < 3; i++){
			inventar.getColumnModel().getColumn(i).setCellRenderer(dcr);
		}
		inventar.setRowHeight(150);
		inventar.setSize(inventar.getRowCount()*150, 1000);
	
		styleInv();
		
		//Scollpane init
		scroll=new JScrollPane(inventar);
		scroll.setSize(new Dimension(1000,650));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.black));
		
		
		
		//hinzufuegen zu sachen
		scrollHuelle.add(scroll,BorderLayout.CENTER);
	}
	
	private void styleInv(){
		inventar.setBackground(Color.decode("#211f1f"));
		inventar.setForeground(Color.decode("#ccc3bd"));
		inventar.setSelectionBackground(Color.decode("#1a1d49"));
		inventar.setSelectionForeground(Color.decode("#d9dbf4"));
		
	}
	
	
	public void removeZeile(int i) {
		if(dtm.getRowCount()>1) {
			dtm.removeRow(i);	
		}else if(dtm.getRowCount() == 1){
			dtm.removeRow(0);
		}else {
			
		}	
		if(dtm.getRowCount() == 0) {
			
		}else {
			inventar.setRowSelectionInterval(0, 0);	
		}
	}
	public void setKaufMenu() {
		contentPane.add(scrollHuelle);	
		scrollHuelle.repaint();    	
		inventar.requestFocusInWindow();
		inventar.setRowSelectionInterval(0, 0);
	}
	
	public void clearKaufMenu() {
		contentPane.remove(scrollHuelle);
		contentPane.repaint();
	}
	public void clearBild() {
		contentPane.remove(bildEbene);
	}
	public int getSelected() {
		return inventar.getSelectedRow();
	}
	public int getItemsRows() {
		return dtm.getRowCount();
	}
	
	private DefaultTableModel dtm;
	private JTable inventar;
}
