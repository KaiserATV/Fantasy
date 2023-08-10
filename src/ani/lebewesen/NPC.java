package ani.lebewesen;

import ani.gegenstaende.Gegenstand;
import main.Main;

public abstract class NPC extends Lebewesen{
	public NPC(){
	name = vorname[Main.zufall.nextInt(vorname.length)]+" "+nachname[Main.zufall.nextInt(nachname.length)];
	isTamable = false;
	}
	public void statusNPC() {
		System.out.println("\n"+name+" hat in dem Rucksack:");
		for (Gegenstand i:rucksack) {
			System.out.println("\t - " +i.toString());
			
		}
		System.out.print(name +" hat die Staerke "+kampfStaerke+".");
		String zaehmbar = " nicht";
		if(isTamable) {
			zaehmbar = "";	
		}
		System.out.print(name+" ist"+zaehmbar+" zähmbar");
		if(isTamable) {
			String gezaehmt ="nicht ";
			if(isTamed) {
				gezaehmt = "";
			}
			System.out.println(" und wurde "+gezaehmt+"gezaehmt.");
		}
	}
	protected boolean isTamed;
}
