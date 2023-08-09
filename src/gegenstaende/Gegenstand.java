package gegenstaende;

import main.Main;

public abstract class Gegenstand {
	public Gegenstand(){
		preis = Main.zufall.nextInt(200)+100;
	}
	protected int preis;
	protected String name;
	public int getPreis() {
		return preis;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Name: "+name+" Preis: "+preis;
	}
	protected String effekt;
	public String getEffekt() {
		if(this instanceof Schriftrollen || this instanceof Ring) {
			return this.effekt;
		}else {
			return "none";
		}
	}
}
