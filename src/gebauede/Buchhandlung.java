package gebauede;


import gegenstaende.Schriftrollen;
import main.Main;

public class Buchhandlung extends Laden{
	public Buchhandlung() {	
		namen = "Buchhandlung";
		bestimmeItems(Main.zufall.nextInt(8)+2);
	}
	private void bestimmeItems(int zufall) {
		for(int i=0; i < zufall;i++) {
				gegenstaende.add(new Schriftrollen());
			}
		}
}
