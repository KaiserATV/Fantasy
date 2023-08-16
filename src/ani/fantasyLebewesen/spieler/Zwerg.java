package ani.fantasyLebewesen.spieler;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zwerg extends Spieler {

	public Zwerg(String n, Point position, Color color, int[] bel) {
		super(n, position, color, bel);
		hpmax= 60;
		strength = 7;
		gold = 169;
		bewegung = 9;
		hp = hpmax;
		resetBewegungen();
		setBild(color);
	}
	public static String getBeschreibung() {
		return "("+60+" Leben, "+7+" Stärke, "+9+" Bewegungsreichweite, "+169+" Startgold)";
	}
	private void setBild(Color c) {
		try {
			if(c == Color.red) {
					spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZwerg_Rot.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.blue) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZwerg_Blau.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else if(c == Color.green) {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZwerg_Gruen.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}else {
				spielerGesamt = ImageIO.read(new File("src/img/klassen/GanzZwerg_Gelb.png"));
	//			spielerKopf = ImageIO.read(new File("src/img/klassen/GanzElf_Rot.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
