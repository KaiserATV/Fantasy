package max.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ani.fantasyLebewesen.spieler.Spieler;
import fabio.spiel.Spiel;
import ani.fantasyLebewesen.nsc.Beluaferus;
import ani.fantasyLebewesen.nsc.Volares;

public class WinPane extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	
	private ImageIcon spieler1 = null;
	
	private ImageIcon spieler2 = new ImageIcon(new Beluaferus().getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
	private ImageIcon spieler3 = new ImageIcon(new Volares().getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
	private ImageIcon spieler4 = new ImageIcon(new Volares().getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));	
	
	
	private JLabel bild1 = new JLabel();
	private JLabel bild2 = new JLabel();
	private JLabel bild3 = new JLabel();
	private JLabel bild4 = new JLabel();
	
	
	protected Font schrift = new Font("DejaVu Sans", Font.PLAIN, 17);;
	private JPanel platz1 = new JPanel();
	private JPanel platz2 = new JPanel();
	private JPanel platz3 = new JPanel();
	private JPanel boden = new JPanel();
	
	private JPanel contentPane = new JPanel();
	
	private List<Spieler> alleSpieler;
	
	private String namenEins;
	private String namenZwei="Beluaferus";
	private String namenDrei="Volares";
	
	
	
	public WinPane(List<Spieler> as) {	
		alleSpieler = as;
	
		for(Spieler s: alleSpieler) {
			setBild(s);	
		}
		
		
		bild1.setIcon(spieler1);
		bild2.setIcon(spieler2);
		bild3.setIcon(spieler3);
		bild4.setIcon(spieler4);
		
		
		
		GroupLayout layout = new GroupLayout(contentPane);
		
		
		//hierdurch die größe des Frames bestimmbar
		platz1.setPreferredSize(new Dimension(250,400));
		platz1.setLayout(new BorderLayout());
		platz1.setBackground(Color.black);
		JLabel platz1Label = new JLabel("Winner - "+namenEins,SwingConstants.CENTER);
		platz1Label.setFont(schrift);
		platz1Label.setForeground(Color.white);
		platz1.add(platz1Label,BorderLayout.PAGE_START);
		
		platz2.setPreferredSize(new Dimension(250,300));
		platz2.setLayout(new BorderLayout());
		platz2.setBackground(Color.black);
		JLabel platz2Label = new JLabel("2. Platz - "+namenZwei,SwingConstants.CENTER);
		platz2Label.setFont(schrift);
		platz2Label.setForeground(Color.white);
		platz2.add(platz2Label,BorderLayout.PAGE_START);
		
		
		platz3.setPreferredSize(new Dimension(250,200));
		platz3.setLayout(new BorderLayout());
		platz3.setBackground(Color.black);
		JLabel platz3Label = new JLabel("3. Platz - "+namenDrei,SwingConstants.CENTER);
		platz3Label.setFont(schrift);
		platz3Label.setForeground(Color.white);
		platz3.add(platz3Label,BorderLayout.PAGE_START);
		
		
		boden.setPreferredSize(new Dimension(1000,1));
		boden.setBackground(Color.decode("#9ad827"));
		
		
		
		bild1.setPreferredSize(new Dimension(250,250));
		bild2.setPreferredSize(new Dimension(250,250));
		bild3.setPreferredSize(new Dimension(250,250));
		bild4.setPreferredSize(new Dimension(250,250));
		
		
		
		layout.setAutoCreateContainerGaps(false);
		layout.setAutoCreateGaps(false);
		
		layout.linkSize(bild1,bild2,bild3,bild4);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(layout.createSequentialGroup()
									.addComponent(bild1)
									.addComponent(platz1,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
									)
							.addGroup(layout.createSequentialGroup()
									.addComponent(bild2)
									.addComponent(platz2,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
									)
							.addGroup(layout.createSequentialGroup()
									.addComponent(bild3)
									.addComponent(platz3,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
									)
							.addComponent(bild4))
					.addComponent(boden)
					
				);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(bild2,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
								.addComponent(platz2))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(bild1,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
								.addComponent(platz1))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(bild3,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
								.addComponent(platz3))
							.addComponent(bild4))
					.addComponent(boden))
		);
		contentPane.setLayout(layout);
		this.setVisible(true);
	}
	
	private void setBild(Spieler s) {
		switch(s.getPlatzierung()) {
		case 0:
			spieler1 = new ImageIcon(s.getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
			namenEins = s.getName();
		break;
		case 1:
			spieler2 = new ImageIcon(s.getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
			namenZwei = s.getName();
		break;
		case 2:
			spieler3 = new ImageIcon(s.getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
			namenDrei = s.getName();
		break;
		case 3: 
			spieler4 = new ImageIcon(s.getSpielerGesamt().getScaledInstance(250, 250,Image.SCALE_FAST));
		break;
		}	
	}
	
	public JPanel getContentPane() {
		contentPane.repaint();
		contentPane.revalidate();
		return contentPane;
	}

}
