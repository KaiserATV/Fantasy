package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ork extends Spieler {

	public Ork(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = 90;
		strength = 5;
		gold = 1;
		bewegung = 8;
		hp = hpmax;
		setBild(color);
	}
	public static String getBeschreibung() {
		return "("+90+" Leben, "+5+" Stärke, "+8+" Bewegungsreichweite, "+1+" Startgold)";
	}
	private void setBild(Color c) {
		try {
			if(c == Color.red) {
					spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzOrk_Rot.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.blue) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzOrk_Blau.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.green) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzOrk_Gruen.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzOrk_Gelb.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
