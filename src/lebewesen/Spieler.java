package lebewesen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;

import gegenstaende.Gegenstand;
import gegenstaende.Waffen;
import gebauede.Laden;

import main.Main;

public class Spieler extends Lebewesen {
	  public Spieler(Point position, Color color) {
		  name = vorname[Main.zufall.nextInt(vorname.length)]+" "+nachname[Main.zufall.nextInt(nachname.length)];
			vermoegen = 1000;
			isTamable = false;
			kampfStaerke=1;
		  this.position = position;
          this.color = color;
          this.steps = 0;
          this.playerID = totalPlayers++;

          if (totalPlayers > MAX_PLAYERS) {
                  throw new IllegalArgumentException("Maximale Anzahl an Spielern erreicht!");
          }
  }
	  public Spieler() {
          this(new Point(0, 0), Color.BLACK); // Verwende den Konstruktor mit Punkt
          name = vorname[Main.zufall.nextInt(vorname.length)]+" "+nachname[Main.zufall.nextInt(nachname.length)];
  		vermoegen = 1000;
  		isTamable = false;
  		kampfStaerke=1;
  }

	
	public void status() {
		if(rucksack.size() == 0) {
			System.out.println(name+" hat "+vermoegen+" Goldmuenzen.");
		}else {
			System.out.println(name+" hat "+vermoegen+" Goldmuenzen. In dem Rucksack befindet sich:");
				
		}
		for (Gegenstand i:rucksack) {
			System.out.println("\t - " +i.toString());
		}
		System.out.println(name+" hat Kampfstaerke "+kampfStaerke+".\n");
		
		if(!pets.isEmpty()) {
			System.out.println("Desweiteren hat "+name+" die folgenden zahmen Monster:");
				for(Monster m:pets) {
					System.out.println("\t - "+m.toString());
				}
		}
	}
	public static Spieler createSpieler(Point position, Color color) {
        if (totalPlayers >= MAX_PLAYERS) {
                return null;
        }
        return new Spieler(position, color);
}

/**
 * Gibt den aktuellen Spieler zurück.
 * 
 * @return Der aktuelle Spieler.
 */
public static Spieler getAktuellerSpieler() {
        return aktuellerSpieler;
}

/**
 * Setzt den aktuellen Spieler.
 * 
 * @param spieler Der Spieler, der als aktueller Spieler festgelegt werden soll.
 */
public static void setAktuellerSpieler(Spieler spieler) {
        aktuellerSpieler = spieler;
}

public void setPosition(Point point) {
        this.position = point;
}

public Point getPosition() {
        return new Point(position); // Verhindere das Ändern der internen Position
}

public void moveTo(int newX, int newY, int maxX, int maxY) {
        Point newPosition = new Point(newX, newY);
        if (newPosition.x < 0 || newPosition.y < 0 || newPosition.x >= maxX || newPosition.y >= maxY) {
                return; // Bewegung außerhalb der Grenzen verhindern
        }
        position = newPosition;
        steps++; // Erhöht die Anzahl der Schritte um 1
}

public void moveUp(int maxX, int maxY) {
        moveTo(position.x, position.y - 1, maxX, maxY);
}

public void moveDown(int maxX, int maxY) {
        moveTo(position.x, position.y + 1, maxX, maxY);
}

public void moveLeft(int maxX, int maxY) {
        moveTo(position.x - 1, position.y, maxX, maxY);
}

public void moveRight(int maxX, int maxY) {
        moveTo(position.x + 1, position.y, maxX, maxY);
}

public void draw(Graphics g, int cellSize) {
        g.setColor(color);
        g.fillOval(position.x * cellSize, position.y * cellSize, cellSize, cellSize);
}

// Nachfolgend sind die Getter- und Setter-Methoden:

public int getX() {
        return position.x;
}

public int getY() {
        return position.y;
}

public Color getColor() {
        return this.color;
}

public int getSteps() {
        return this.steps;
}

public int getPlayerID() {
        return this.playerID;
}

/**
 * Gibt die Grenzen des Spielers als Rechteck zurück.
 *
 * @param cellSize Größe einer Zelle im Raster.
 * @return Das Rechteck, das die Grenzen des Spielers darstellt.
 */
public Rectangle getBounds(int cellSize) {
        return new Rectangle(position.x * cellSize, position.y * cellSize, cellSize, cellSize);
}

public void setX(int x) {
        position.x = x;
}

public void setY(int y) {
        position.y = y;
}

public void setColor(Color color) {
        this.color = color;
}

public void setSteps(int steps) {
        this.steps = steps;
}
	
	public int getVermoegen() {
		return vermoegen;
	}
	/**
	 * 
	 * @param i wird hinzuaddiert
	 */
	public void setVermoegen(int i) {
		vermoegen+=i;
	}
//	public void use() {
//		int j = 0;
//		System.out.println("\n\nDu hast:");
//		for(Gegenstand i:usable) {
//				System.out.println(j+": "+i.getName()+". Effekt: "+i.getEffekt());
//				j++;
//		}
//		Laden.trennen();
//		System.out.println("Welchen Gegenstand willst du benutzten?");
//		Scanner input = new Scanner(System.in);
//		int ein =input.nextInt();
//		Gegenstand ausgew = usable.get(ein);
//		while(ein < 0 || ein >= usable.size()) {
//			System.out.println("Bitte gib die Nummer des Items an!");
//			ein = input.nextInt();
//		}
//		if(effektAusführen(ausgew.getEffekt())) {
//			System.out.println(getName()+" benutzt "+ausgew.getName() + " mit Effekt "+ausgew.getEffekt()+"\n");	
//			rucksack.remove(ausgew);
//			usable.remove(ausgew);
//		}else {
//			System.out.println(ausgew.getName()+" konnte nicht benutzt werden....\n");
//		}
//	}
//	private boolean effektAusführen(String spell) {
//		switch (spell) {
//		case "none":
//			return true;
//		case "destroySword":
//			for(Lebewesen l:Main.exist) {
//				if(this.getDistanceTo(l) < 6.0 && l != this) {
//					System.out.println("\nDas Schwert/die Schwerter von "+l.getName()+" wurden zerstört!");
//					l.zerstoereTyp("public class gegenstaende.Schwert");
//				}
//			}
//			return true;
//		case "monsterStDown":
//			for(Lebewesen l:Main.exist) {
//				if(this.getDistanceTo(l)< 6.0 && l instanceof Monster) {
//					System.out.println("\nDie Kampfstaerke von "+l.getName()+" wurde um 3 auf "+l.getKampfStaerke()+" verringert!");
//					int monsterS = l.getKampfStaerke();
//					if(monsterS -3 < 0) {
//						l.setKampfStaerke(0);
//					}else {
//						l.setKampfStaerke(l.getKampfStaerke()-3);
//					}
//				}
//			}
//			return true;
//		case "staerkeUp":
//			setKampfStaerke(getKampfStaerke()+3);
//			System.out.println("Kampfstaerke erfolgreich auf "+this.getKampfStaerke()+" erhoet!");
//			return true;
//		case "tameMonster":
//			boolean erster = true;
//			for(Lebewesen l:Main.exist) {
//				if(this.getDistanceTo(l)< 6.0 && l instanceof Monster && erster) {
//					System.out.println("\n"+name+"wurde erfolgreich von "+this.getName()+" gezaehmt!");
//					((Monster) l).tame();
//					pets.add((Monster) l);
//					erster = false;
//				}
//			}
//			return true;
//		default:
//			return false;
//		}
//	}
	final public void ausruesten(Gegenstand g) {
			System.out.println("Willst du die Waffe mit Staerke "+((Waffen) g).getStearke()+" ausrüsten?(y/n)");
			Scanner eingabe = new Scanner(System.in);
			if(eingabe.next().toUpperCase().charAt(0) == 'Y') {
				kampfStaerke = ((Waffen) g).getStearke();
				ausgeruestet = rucksack.indexOf(g);
				System.out.println("Die Waffe wurde ausgeruestet!");
			}
	}
	private static Spieler aktuellerSpieler;
    private Point position; // Verwende einen Punkt, um die Position zu speichern
    private Color color;
    private int steps;
    private int playerID;
    private static int totalPlayers = 0;
    private static final int MAX_PLAYERS = 10;
	
	List <Monster> pets= new LinkedList<Monster>();
	private int vermoegen;
}
