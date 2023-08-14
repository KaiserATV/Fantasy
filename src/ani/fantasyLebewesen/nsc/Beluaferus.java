package ani.fantasyLebewesen.nsc;

import ani.fantasyLebewesen.Spieler;

public class Beluaferus extends Monster {
	// haben Name(hängt zusammen mit Angriffstyp), Leben, Stärke, Gold (bzw. Drops?), Status 
		Beluaferus(){
		name = "Beluaferus";
		hp = 21;
		strength = 5;//Schaden 5-9
		}
		private boolean mangriff = true;
			@Override
			public boolean isMangriff() {
				return mangriff;
			}
			@Override
			public void setMangriff(boolean mangriff) {
				this.mangriff = mangriff;
			}
	
					
}
