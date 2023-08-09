package gegenstaende;

import main.Main;

public class Schriftrollen extends MagischeWaffen{
	public Schriftrollen(){
		super();
		name = schriftrollen[Main.zufall.nextInt(10)];
		effektSetzten();
	}
	private void effektSetzten() {
		if(name.equals(schriftrollen[0])) {
			effekt = efBesch[1];
		}
		else if(name.equals(schriftrollen[1])) {
			effekt = efBesch[2];
		}else {
			effekt =efBesch[0];
		}
	}
	private String schriftrollen[] = {"Fluch der Schreibfeder ","Augen von Hypnos","Alvusias Verbannung", "Brevas Abkehr", "Daerirs Segen","Ikarus' Flugrolle", "Ondusis Schluessel","Prinz Ovs Lichtkugel","Schriftrolle des Unheilvollen Leids", "Schriftrolle des Reinen Herzens"};
	
}
