package ani.fantasyLebewesen.nsc;

import java.util.Random;

import ani.fantasyLebewesen.Spieler;

import java.util.ArrayList;
import java.util.List;

public class Monster extends NSC {
	
	Random zufall = new Random();
	
	private List<Volares> volares = new ArrayList<Volares>();

		public List<Volares> getVolares() {
			return volares;
		}
	
		public void setVolares(List<Volares> volares) {
			this.volares = volares;
		}
		
	private List<Beluaferus> beluaferus = new ArrayList<Beluaferus>();

		public List<Volares> getBeluaferus() {
			return volares;
		}
	
		public void setBeluaferus(List<Beluaferus> belua) {
			this.beluaferus = belua;
		}

		private int monsteranzahl = zufall.nextInt(10)+1;
		
			public int getMonsteranzahl() {
				return monsteranzahl;
			}
	
			public void setMonsteranzahl(int n) {
				this.monsteranzahl = n;
			}

		public void create() {
			
			for(int i=0;i<monsteranzahl;i++) {
				switch(zufall.nextInt(2)) {
				case 0:
					Volares vol = new Volares();
					volares.add(vol);
					break;
				case 1:
					Beluaferus belu = new Beluaferus();
					beluaferus.add(belu);
					break;
				default:
					System.out.println("Etwas ist schief gegangen...");
				}
			}
		}
		
		public void setState(String state) {
	
		}

		public void setMangriff(boolean b) {
			// wird angegriffen? hängt vom jeweiligen Zustand des Monsters ab!
		}

		public boolean isMangriff() {
			return false;
		}
	
		public int getHp() { // idk O.o
			return 0;
		}
		
		
	
	
	
	
	}
