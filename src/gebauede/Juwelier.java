package gebauede;

import gegenstaende.Ring;
import main.Main;

public class Juwelier extends Laden{
	public Juwelier(){
		namen = "Juwelier";
		bestimmeItems(Main.zufall.nextInt(8)+2);
	}
	private void bestimmeItems(int zufall) {
		for(int i=0; i < 12;i++) {
				if(i == 0) {
					gegenstaende.add(new Ring("silber"));
				}else {
					gegenstaende.add(new Ring());	
				}
			}
		}
}
