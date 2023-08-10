package ani.gegenstaende;

import main.Main;

public abstract class Waffen extends Gegenstand{
	Waffen(){
		super();
		stearke= Main.zufall.nextInt(3)+1;
	}
	protected int stearke;
	public int getStearke() {
		return stearke;
	}
	@Override
	public String toString() {
		return "Name: "+name+" Staerke: "+stearke+" Preis: "+preis;
	}
}
