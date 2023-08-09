package gegenstaende;

import main.Main;

public class Schwert extends Waffen{
	public Schwert(){
		super();
		name = Schwerter[Main.zufall.nextInt(5)] + "Schwert";
	}
	private String[] Schwerter = {"unheilvolles ", "kaiserliches ", "koengliches ", "zerbrechliches ", "glaessernes "};
}
