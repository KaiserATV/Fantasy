package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Spieler {
    private int x, y;
    private Color color;
    private int steps;
    private int playerId;

    public Spieler(int x, int y, Color color, int playerId) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.steps = 0;
        this.playerId = playerId;
    }


    public void moveTo(int newX, int newY) {
        int distance = Math.abs(this.x - newX) + Math.abs(this.y - newY);
        if (distance <= steps) {
            this.x = newX;
            this.y = newY;
            steps -= distance;
        }
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