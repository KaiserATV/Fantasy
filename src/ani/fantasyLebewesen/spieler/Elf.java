package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Elf extends Spieler {
	
	public Elf(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = 45;
		strength = 7;
		gold = 150;
		bewegung = 12;
		hp = hpmax;
		setBild(color);
	}
	public static String getBeschreibung() {
		return "("+45+" Leben, "+7+" Stärke, "+12+" Bewegungsreichweite, "+150+" Startgold)";
	}

	private void setBild(Color c) {
		try {
			if(c == Color.red) {
					spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.blue) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzElf_Blau.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.green) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzElf_Gruen.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzElf_Gelb.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
