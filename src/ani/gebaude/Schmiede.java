package ani.gebaude;

import ani.gegenstaende.Bogen;
import ani.gegenstaende.Schwert;
import main.Main;

public class Schmiede extends Laden{
	public Schmiede(){
		namen = "Schmiede";
		zufall = Main.zufall.nextInt(8)+2;
		bestimmeItems();
	}
	private void bestimmeItems() {
		for(int i=0; i < Math.round(zufall/2);i++) {
				gegenstaende.add(new Schwert());
			}
		for(int i = Math.round(zufall/2); i< zufall; i++) {
			gegenstaende.add(new Bogen());}
	}
	private static int zufall;
}
