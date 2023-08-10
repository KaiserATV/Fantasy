package ani.lebewesen;

import ani.gegenstaende.Bogen;
import ani.gegenstaende.Gegenstand;
import ani.gegenstaende.Schwert;
import ani.gegenstaende.Waffen;

public class Monster extends NPC{
	public Monster(){
	super();
	isTamed = false;
	isTamable = true;
	kampfStaerke =1; 
	randomWaffe();
	}
	public void tame() {
		isTamed = true;
	}
	public boolean getIsTamed() {
		return isTamed;
	}
	private void randomWaffe() {
		int schwertOderBogen = main.Main.zufall.nextInt(1);
		if(schwertOderBogen == 0) {
			rucksack.add(new Schwert());	
		}else {
			rucksack.add(new Bogen());
		}
		kampfStaerke = ((Waffen) rucksack.get(0)).getStearke();	
		ausgeruestet = 0;
	}
	@Override
	public String toString() {
		String zahm=" nicht ";
		if(isTamed) {
			zahm =" ";
		}
		String inventar =".";
		if(!rucksack.isEmpty()) {
			inventar = " und besitzt "+rucksack.get(0).getName();
		}
		return name + " ist"+zahm+"gezaehmt und hat die Kampfstaerke "+kampfStaerke+inventar;
	}
	final public void ausruesten(Gegenstand g) {};
}
