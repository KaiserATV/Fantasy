package max.sys;

import ani.fantasyItems.Item;
import ani.fantasyItems.equippable.Equippable;
import ani.fantasyItems.schmiedegut.Armor;
import ani.fantasyItems.weapons.Weapons;
import ani.fantasyLebewesen.spieler.Spieler;

public abstract class VorlageSys {

	//ob Item anlegbar ist
	public boolean anlegbar() {
		if(gekauft instanceof Weapons || gekauft instanceof Armor || gekauft instanceof Equippable) {
			return true;
		}else {
			return false;
		}
	}
	
	//legt item an
	public void anlegen() {
		if(gekauft instanceof Weapons) {
			ich.setWaffe(gekauft);
		}else if(gekauft instanceof Equippable) {
			ich.setBling(gekauft);
		}else if(gekauft instanceof Armor) {
			ich.setArmor(gekauft);
		}	
	}
	
	public String getAnlegText() {
		return gekauft.anwendenText(ich);
	}
	
	public String getStats() {
		return ich.getStats();
	}
	public String getStatsWeniger() {
		if(ich.getWaffe()!=null) {
			return "Stärke(Waffe): "+ich.getWaffe().getStrength()+" Schutz: "+ich.getSchutz()+" Macht:"+ich.getMacht();	
		}else {
			return "Stärke(Waffe): "+0+" Schutz: "+ich.getSchutz()+" Macht:"+ich.getMacht();
		}
		
	}

	protected Item gekauft;
	protected Spieler ich;
}
