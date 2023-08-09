package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

class CustomTableCellRenderer extends JLabel implements TableCellRenderer {
    private static final long serialVersionUID = 1L;

    CustomTableCellRenderer(){
    	setOpaque(true);
    }
    
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		 
		 
		 
		if(row<table.getColumnCount()-1) {
			setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.red));	
		}else if(row == table.getColumnCount()-1) {
			setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.red));	
		}
		// TODO Auto-generated method stub
		return this;
	}
}
