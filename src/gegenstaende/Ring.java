package gegenstaende;

import main.Main;

public class Ring extends MagischeWaffen {
	public Ring(){
		super();
		name = items[Main.zufall.nextInt(10)];
		effektSetzten();
	}
	public Ring(String s) {
		super();
		name = items[0];
		effekt = efBesch[3];
	}
	private void effektSetzten() {
		if(name.equals(items[0])) {
			effekt = efBesch[3];
		}
		else if(name.equals(items[1])) {
			effekt = efBesch[4];
		}else {
			effekt =efBesch[0];
		}
	}
	private static String[] items= {"Silberring","Goldring","Ring des Glücks","Ring des Heilens","Ring der Nacht", "Ring des endlosen Wassers","Ring der Geisteskraft","Platinring","Bismuthring","Kaiserlicher Ring"};

}
