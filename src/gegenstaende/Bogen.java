package gegenstaende;

import main.Main;

public class Bogen extends Waffen{
	public Bogen(){
		super();
		name = Boegen[Main.zufall.nextInt(5)] + "Bogen";
	}
	private String[] Boegen = {"durchschlagender ", "entflammender ","zerstoererischer ","beendender ","heulender "};
}
