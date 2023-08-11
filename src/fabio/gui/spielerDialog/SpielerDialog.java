package fabio.gui.spielerDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import ani.fantasyLebewesen.Spieler;
import fabio.spiel.Spiel;

public class SpielerDialog extends Dialog {
    private static final long serialVersionUID = 1L;

	public SpielerDialog(Frame parent, boolean modal,Spiel spiel) {
        super(parent, modal);
        s= spiel;
        initComponents();
    }
    private Spiel s;
    private int[] beleg = new int[6]; 
                        
    private void initComponents() {

        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jButton1 = new JButton();
        jTextField6 = new JTextField();
        jLabel7 = new JLabel();
        jTextField7 = new JTextField();
        jLabel8 = new JLabel();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jButton2 = new JButton();
        jTextField0 = new JTextField();

        setPreferredSize(new java.awt.Dimension(800, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        defaultText();
        jLabel1.setText("Gib deinen Namen ein:");
        
        jLabel2.setText("Herkunft:");

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Zwerg", "Ork", "Elf", "Zauberer" }));

        
        jTextField6.setEditable(false);
        jTextField6.setFocusable(true);
        
        jLabel7.setText("Auswahl");

        jTextField7.setEditable(false);
        jTextField7.setFocusable(true);
        
        jLabel8.setText("Zurück im Menu");

        jTextField4.setEditable(false);
        jTextField4.setFocusable(true);
        
        jTextField5.setEditable(false);
        jTextField5.setFocusable(true);
      
        jTextField2.setEditable(false);
        jTextField2.setFocusable(true);
        
        jTextField3.setEditable(false);
        jTextField3.setFocusable(true);
        
        jLabel3.setText("Links");

        jLabel4.setText("Rechts");

        jLabel5.setText("Oben");

        jLabel6.setText("Unten");

        jButton2.setText("Weiterer Spieler");

        jTextField0.setEditable(false);
        jTextField0.setFocusable(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(52, 52, 52)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
                                .addGap(94, 94, 94)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel1))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(69, 69, 69)
                                .addComponent(jButton2))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField0, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField0, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(17, 17, 17))
        );

        pack();
        
        initListeners();
        jTextField1.requestFocus();
        this.setVisible(true);
    }
    
    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        setVisible(false);
        dispose();
    } 
    //3-7
    private void defaultText() {
    	if(s.getSpielerAnzahl() == 3) {
    		jButton2.setVisible(false);
    	}

    	jButton1.setText("Spielen ("+(s.getSpielerAnzahl()+1)+"/4)?");
        jTextField0.setText("Spielererstelltung: Spieler "+(s.getSpielerAnzahl()+1));
    	
    	jTextField1.setText(vorname[zufall.nextInt(vorname.length)]+" "+nachname[zufall.nextInt(nachname.length)]);
    	jTextField2.setText("LEFT");
    	beleg[0] = KeyEvent.VK_LEFT;
    	jTextField3.setText("RIGHT");
    	beleg[0] = KeyEvent.VK_RIGHT;
    	jTextField4.setText("UP");
    	beleg[0] = KeyEvent.VK_UP;
        jTextField5.setText("DOWN");
    	beleg[0] = KeyEvent.VK_DOWN;
        jTextField6.setText("SPACE");
    	beleg[0] = KeyEvent.VK_SPACE;
        jTextField7.setText("S");
    	beleg[0] = KeyEvent.VK_S;
    	
    	this.repaint();
    	
    }
    private void initListeners() {	
    	jTextField1.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
    				jComboBox1.requestFocus();
    			}
    		}
    	});
    	
    	ArrayList<Component> key = new ArrayList<Component>();
    	key.add(jTextField2);
    	key.add(jTextField3);
    	key.add(jTextField4);
    	key.add(jTextField5);
    	key.add(jTextField6);
    	key.add(jTextField7);
    	
    	
    	
    	MouseListener ml = new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			System.out.println("click");
    					JTextField tf = (JTextField) e.getComponent();
    					tf.setText("");
    					tf.setBackground(Color.RED);
    					tf.addKeyListener(new KeyAdapter() {
    						@Override
    						public void keyPressed(KeyEvent e) {
    							String in = KeyEvent.getKeyText(e.getKeyCode());
    							beleg[key.indexOf(e.getComponent())]=e.getKeyCode();
    							tf.setText(in);
    							KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
    							tf.setBackground(Color.white);
    						}
    					});
    		}
    	};
    	
    	for(Component c:key) {
    		c.addMouseListener(ml);
    	}
    	
    	Point pos;
		switch(s.getSpielerAnzahl()) {
		case 0:
			pos = new Point(0,0);
			break;
		case 1:
			pos = new Point(0,69);
			break;
		case 2:
			pos = new Point(69,0);
			break;
		case 3:
			pos = new Point(69,69);
			break;
		default: 
			pos=null;
			System.out.println("Fehler bei Positionserstellung");
		break;
		}
		
    	jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			s.spielerAdd(Spieler.createSpieler(jTextField1.getText(), pos, Color.red, jComboBox1.getSelectedItem().toString(), beleg));	
			defaultText();
			jTextField1.requestFocusInWindow();
	        repaint();
			}
    	});
    	
    	jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.spielerAdd(Spieler.createSpieler(jTextField1.getText(), pos, Color.red, jComboBox1.getSelectedItem().toString(), beleg));
				dispose();
				s.spielern();
			}
    		
    	});
    }
    
    private Random zufall = new Random();
    protected String[] vorname = {"Aragorn","Balin","Bilbo","Frodo","Gandalf","Legolas","Gimli","Sauron","Thorin","Smaug"};
	protected String[] nachname = {"Abbott","Umbridge","Malfoy","Dumbledore","Finnigan","Granger","Kowalski","Lovegood","Weasley","Potter"};
    private JButton jButton1;
    private JButton jButton2;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JTextField jTextField0;
    private JTextField jTextField1;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField6;
    private JTextField jTextField7;                
}
