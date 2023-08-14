package max.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CustomCellRenderer extends JLabel implements ListCellRenderer<Object> {
    private static final long serialVersionUID = 1L;

    // This is the only method defined by ListCellRenderer.
    // We just reconfigure the JLabel each time we're called.

    public Component getListCellRendererComponent(
      JList<?> list,           // the list
      Object value,            // value to display
      int index,               // cell index
      boolean isSelected,      // is the cell selected
      boolean cellHasFocus)    // does the cell have focus
    {
        String s = value.toString();
        
        if (isSelected) {
            setBackground(Color.black);
            setForeground(Color.white);
            setText("<html><u><i>"+s+"</i></u></html>");
        } else {
        	setBackground(Color.black);
            setForeground(Color.white);
            setText(s);
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont().deriveFont(20l));
        setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setSize(500,100);
		
		
        return this;
    }
}
