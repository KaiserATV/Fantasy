package ani.fantasyShops;

import ani.fantasyItems.useables.scroll.*;

public class Buchhandlung extends Shops {
	public Buchhandlung() {
		super();
		wareBuch();
	}
		@Override
		public String getName() {
			return "Buchhandlung";
		}
	

	
	
	public void wareBuch() {
		for(int i=0;i<n;i++) {
			switch(zufall.nextInt(3)) {
			case 0:
				AugenHypnos augen = new AugenHypnos();
				inventar.add(augen);
				break;
			case 1:
				FluchSchreibfeder fluch = new FluchSchreibfeder();
				inventar.add(fluch);
				break;
			case 2:
				Nebelfelder nebel = new Nebelfelder();
				inventar.add(nebel);
				break;
			default:
				System.out.println("Etwas ist schief gegangen...");
			}
		}
	}	
}
