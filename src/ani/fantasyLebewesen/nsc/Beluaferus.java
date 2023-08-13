package ani.fantasyLebewesen.nsc;

import ani.fantasyLebewesen.Spieler;

public class Beluaferus extends Monster {
	// haben Name(hängt zusammen mit Angriffstyp), Leben, Stärke, Gold (bzw. Drops?), Status 
		Beluaferus(){
			
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
