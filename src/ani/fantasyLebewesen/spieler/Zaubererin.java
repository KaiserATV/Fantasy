package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zaubererin extends Spieler {

	public Zaubererin(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax = 30;
		strength = 14;
		gold = 369;
		bewegung = 9;
		hp = hpmax;
		resetBewegungen();
		setBild(color);
	}
	public static String getBeschreibung() {
		return "("+30+" Leben, "+14+" Stärke, "+9+" Bewegungsreichweite, "+369+" Startgold)";
	}
	private void setBild(Color c) {
		try {
			if(c == Color.red) {
					spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZauberer_Rot.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.blue) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZauberer_Blau.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.green) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZauberer_Gruen.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZauberer_Gelb.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
