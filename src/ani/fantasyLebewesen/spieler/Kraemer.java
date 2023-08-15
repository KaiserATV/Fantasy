package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Kraemer extends Spieler {

	public Kraemer(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = 100;
		strength = 50;
		gold = 2000;
		bewegung = 15;
		hp = hpmax;
		setBild(color);
	}
	public static String getBeschreibung() {
		return "("+100+" Leben, "+50+" Stärke, "+15+" Bewegungsreichweite, "+2000+" Startgold)";
	}
	private void setBild(Color c) {
		try {
			if(c == Color.red) {
					spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzKraemer_Rot.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.blue) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzKraemer_Blau.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.green) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzKraemer_Gruen.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzKraemer_Gelb.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
