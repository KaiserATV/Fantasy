package spieler;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Point;


public class Spieler {
    private int x, y;
    private Color color;
    private int steps;
    private int playerId;

    public Spieler() {
        // Initialwerte, z.B.:
        this(0, 0, Color.BLACK, 1);
    }

    public Spieler(int x, int y, Color color, int playerId) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.steps = 0;
        this.playerId = playerId;
    }
   
    
    public void setPosition(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
    

    public Point getPosition() {
        return new Point(this.x, this.y);
    }

    
    public void moveTo(int newX, int newY, int maxX, int maxY) {
        if (newX < 0 || newY < 0 || newX >= maxX || newY >= maxY) {
            return; // Bewegung außerhalb der Grenzen verhindern
        }
        this.x = newX;
        this.y = newY;
    }
    
    public void moveUp(int maxX, int maxY) {
        moveTo(this.x, this.y - 1, maxX, maxY);
    }
        
    public void moveDown(int maxX, int maxY) {
        moveTo(this.x, this.y + 1, maxX, maxY);
    }
        
    public void moveLeft(int maxX, int maxY) {
        moveTo(this.x - 1, this.y, maxX, maxY);
    }
        
    public void moveRight(int maxX, int maxY) {
        moveTo(this.x + 1, this.y, maxX, maxY);
    }
    

    public void draw(Graphics g, int cellSize) {
        g.setColor(color);
        g.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    // Getter Methoden
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getPlayerId() {
    	return this.playerId;
    }

    public Rectangle getBounds(int cellSize) {
        return new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    // Setter Methoden
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setPlayerId(int playerId) {
    	this.playerId = playerId;
    }
}
