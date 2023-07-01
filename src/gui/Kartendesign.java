package gui;

import javax.swing.ImageIcon;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Kartendesign {
    private final int NUM_CELLS;
    private final int[][] grid;
    private final SpielfeldGUI spielfeldGUI;
    private final Random random;
    private final ArrayList<Image> images;
    private final int[][] imageIndices;
    private final ArrayList<Point> buildings;

    public Kartendesign(int[][] grid, SpielfeldGUI spielfeldGUI) {
        this.grid = grid;
        this.random = new Random();
        this.spielfeldGUI = spielfeldGUI;
        this.buildings = new ArrayList<>();

        images = new ArrayList<>();
        images.add(new ImageIcon("Fantasy/src/Bilder/Cherry.png").getImage());
        images.add(new ImageIcon("Fantasy/src/Bilder/Abstrakt.png").getImage());
        images.add(new ImageIcon("Fantasy/src/Bilder/Laub.png").getImage());
        images.add(new ImageIcon("Fantasy/src/Bilder/Haus.png").getImage());
        images.add(new ImageIcon("Fantasy/src/Bilder/Weg.png").getImage());

        this.imageIndices = new int[grid.length][grid[0].length];
        
        NUM_CELLS = SpielfeldKonstanten.NUM_CELLS;

        // Rest mit Bäumen auffüllen
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = 0;  // Baum
                imageIndices[i][j] = random.nextInt(3);  // Eins der drei Baumbilder
            }
        }

        // 10 Gebäude pro Viertel platzieren
        placeBuildingsInEachQuarter(10);
        generatePaths();
    }

    // Platziere Gebäude in jedem Viertel
    private void placeBuildingsInEachQuarter(int numBuildingsPerQuarter) {
        int quarterSize = NUM_CELLS / 2;

        // Gehe jedes Viertel durch
        for (int xOffset = 0; xOffset < NUM_CELLS; xOffset += quarterSize) {
            for (int yOffset = 0; yOffset < NUM_CELLS; yOffset += quarterSize) {
                // Platziere Gebäude im aktuellen Viertel
                for (int i = 0; i < numBuildingsPerQuarter; i++) {
                    int x, y;
                    do {
                        x = xOffset + random.nextInt(quarterSize);
                        y = yOffset + random.nextInt(quarterSize);
                    } while (grid[x][y] != 0);  // Stelle sicher, dass die Zelle leer ist (mit Baum gefüllt)
                    grid[x][y] = 1;  // Platziere ein Gebäude
                    imageIndices[x][y] = 3;  // Setze den Index für das Gebäudebild
                    buildings.add(new Point(x, y));  // Füge das Gebäude zur Liste hinzu
                }
            }
        }
    }

    // Generiere Wege
    public void generatePaths() {
        ArrayList<Point> edges = new ArrayList<>();
        edges.add(new Point(0, 0));
        edges.add(new Point(0, NUM_CELLS - 1));
        edges.add(new Point(NUM_CELLS - 1, 0));
        edges.add(new Point(NUM_CELLS - 1, NUM_CELLS - 1));

        for (Point edge : edges) {
            // Wähle ein zufälliges Gebäude, das mit dem Weg verbunden werden soll
            Point target = buildings.get(random.nextInt(buildings.size()));
            generatePathTo(edge, target);
        }

        // Generiere Wege von den Gebäuden zum Zentrum
        Point center = new Point(NUM_CELLS / 2, NUM_CELLS / 2);
        for (Point building : buildings) {
            generatePathTo(building, center);
        }
    }

    // Generiere Weg von Startpunkt zu Zielpunkt
    private void generatePathTo(Point start, Point target) {
        Point current = new Point(start);

        while (!current.equals(target)) {
            current = makeMoveTowards(current, target);

            if (isOutOfBounds(current)) {
                break;
            }

            if (grid[current.x][current.y] != 1) {  // Überschreibe keine Gebäude
                grid[current.x][current.y] = 2;  // Platziere einen Weg
                imageIndices[current.x][current.y] = 4;  // Setze den Index für das Wegbild
            }
        }
    }

    // Bewege dich in Richtung des Zielpunkts
    private Point makeMoveTowards(Point current, Point target) {
        Point next = new Point(current);

        if (current.x < target.x) {
            next.x++;
        } else if (current.x > target.x) {
            next.x--;
        }
        if (current.y < target.y) {
            next.y++;
        } else if (current.y > target.y) {
            next.y--;
        }

        return next;
    }

    // Überprüfe, ob der Punkt außerhalb des Spielfelds liegt
    private boolean isOutOfBounds(Point point) {
        return point.x < 0 || point.x >= NUM_CELLS || point.y < 0 || point.y >= NUM_CELLS;
    }

    // Zeichne das Raster
    public void drawGrid(Graphics g, int cellSize) {
        for (int i = 0; i < NUM_CELLS; i++) {
            for (int j = 0; j < NUM_CELLS; j++) {
                int x = i * SpielfeldKonstanten.CELL_SIZE;
                int y = j * SpielfeldKonstanten.CELL_SIZE;

                Image image;
                switch (grid[i][j]) {
                    case 0:
                        image = images.get(imageIndices[i][j]);
                        break;
                    case 1:
                        image = images.get(3);
                        break;
                    default:
                        image = images.get(4);
                        break;
                }

                g.drawImage(image, x, y, cellSize, cellSize, null);
            }
        }
    }
}
