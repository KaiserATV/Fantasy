package ani.fantasyLebewesen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ani.fantasyLebewesen.spieler.Spieler;


public abstract class Lebewesen { 
	//braucht Name, Leben, Stärke, Gold

	protected BufferedImage spielerGesamt;
	private static Spieler aktuellerSpieler;
	private Point position; // Verwende einen Punkt, um die Position zu speichern
	private Color color;
	private int steps;
	private int playerID;
	protected static int totalPlayers = 0;
	protected static final int MAX_PLAYERS = 10;
	protected int bewegung;
	protected String name;
	protected int schutz = 0;
	private boolean armored;
	private String state;
	protected int gold;
	protected int strength;
	protected int hpmax;
	protected int hp;
	
	public Lebewesen(){
		
	}
	
	public Lebewesen(Point position, Color color){
		this.position = position;
        this.color = color;
        this.steps = 0;
        this.playerID = totalPlayers++;

        if (totalPlayers > MAX_PLAYERS) {
                throw new IllegalArgumentException("Maximale Anzahl an Spielern erreicht!");
        }
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


	public int getBewegung() {
		return bewegung;
	}
	public void setBewegung(int b) {
		bewegung = hp;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void reduziereHp(int schaden) {
		this.hp -= schaden;
	}


	public int getHpMax() {
		return hpmax;
	}
	public void setHpMax(int hp) {
		this.hpmax = hp;
	}
	public void reduziereHpMax(int schaden) {
		this.hpmax -= schaden;
	}


	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public boolean isArmored() {
		return armored;
	}
	public void setArmored(boolean armored) {
		this.armored = armored;
	}


	public int getSchutz() {
		return schutz;
	}
	public void setSchutz(int schutz) {
		this.schutz = schutz;
	}
	
	
	public BufferedImage getSpielerGesamt() {
		return spielerGesamt;
	}
}
