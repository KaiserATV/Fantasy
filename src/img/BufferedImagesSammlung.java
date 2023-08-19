package img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImagesSammlung {
	private BufferedImage beluaferus = null;
	private BufferedImage volares = null;
	private BufferedImage haus = null;
	private BufferedImage kampfHintergrund = null;
	private BufferedImage laub = null;
	private BufferedImage weg = null;
	private BufferedImage buchhandlungBild = null;
	private BufferedImage juwelierBild = null;
	private BufferedImage karrenBild = null;
	private BufferedImage schmiedeBild = null;
	private BufferedImage taverneBild = null;

	public BufferedImagesSammlung() {
	String path = getBasePath();
	String shopPath = getShopPath();
	try {
		beluaferus = ImageIO.read(new File(path+"beluaferus.png"));
		volares = ImageIO.read(new File(path+"volares.png"));
		haus = ImageIO.read(new File(path+"haus.png"));
		kampfHintergrund = ImageIO.read(new File(path+"kampfHintergrund.png"));
		laub = ImageIO.read(new File(path+"laub.png"));
		weg = ImageIO.read(new File(path+"weg.png"));
		buchhandlungBild = ImageIO.read(new File(shopPath+"buchhandlungBild.png"));
		juwelierBild = ImageIO.read(new File(shopPath+"juwelierBild.png"));
		karrenBild = ImageIO.read(new File(shopPath+"karrenBild.png"));
		schmiedeBild = ImageIO.read(new File(shopPath+"schmiedeBild.png"));
		taverneBild = ImageIO.read(new File(shopPath+"taverneBild.png"));
		}catch (IOException e) {
			System.out.println("Fehller bei Bilderstellung!");
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBeluaferus() {
		return beluaferus;
	}
	public BufferedImage getVolares() {
		return volares;
	}
	public BufferedImage getHaus() {
		return haus;
	}
	public BufferedImage getKampfHintergrund() {
		return kampfHintergrund;
	}
	public BufferedImage getLaub() {
		return laub;
	}
	public BufferedImage getWeg() {
		return weg;
	}
	public BufferedImage getBuchhandlungBild() {
		return buchhandlungBild;
	}
	public BufferedImage getjuwelierBild() {
		return juwelierBild;
	}
	public BufferedImage getKarrenBild() {
		return karrenBild;
	}
	public BufferedImage getSchmiedeBild() {
		return schmiedeBild;
	}
	public BufferedImage getTaverneBild() {
		return taverneBild;
	}
	
	
	
	private static String getBasePath() {
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "img" + File.separator;
	}
	private static String getShopPath() {
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "img" + File.separator + "shops"+ File.separator;
	}

}
