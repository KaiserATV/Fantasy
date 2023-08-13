package ani.fantasyItems.useables.jewelry;

import ani.fantasyLebewesen.Spieler;

public class Silberring extends Ring {
	public Silberring() {
		this.setName(name);
		this.setPrice(p);
		this.effekt="Stärke hoch";
	}
	
	private String name = "Silberring";
		
	private int p = 250;
	
	@Override
	public String kaufenAsString() {
		String a = name +" wird für ";
		String b = String.valueOf(this.getPrice());
		String c = " Goldstücke gekauft";
		return (a+b+c);
	}
	
	@Override
	public String anwenden(Spieler player) {
		int neu = player.getStrength();
		neu++;
		player.setStrength(neu);
		return "Der Silberring leuchtet hoffnungsvoll!\nDu spürst deine Stärke steigen";
	}

}
