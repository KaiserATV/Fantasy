package spieler;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Spieler {
        private static Spieler aktuellerSpieler;
        private Point position; // Verwende einen Punkt, um die Position zu speichern
        private Color color;
        private int steps;
        private int playerID;
        private static int totalPlayers = 0;
        private static final int MAX_PLAYERS = 10;

        public Spieler() {
                this(new Point(0, 0), Color.BLACK); // Verwende den Konstruktor mit Punkt
        }

        public Spieler(Point position, Color color) {
                this.position = position;
                this.color = color;
                this.steps = 0;
                this.playerID = totalPlayers++;

                if (totalPlayers > MAX_PLAYERS) {
                        throw new IllegalArgumentException("Maximale Anzahl an Spielern erreicht!");
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
}