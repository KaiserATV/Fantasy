package spieler;

import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import karte.SpielfeldGUI;
import karte.SpielfeldKonstanten;

import java.util.Random;
import java.awt.Graphics;

public class SpielerMovement {
    Spieler ausgewaehlterSpieler;
    private SpielfeldGUI spielfeldGUI;
    private Random random = new Random();
    private JLabel diceLabel;
    private int ausgewaehlterSpielerIndex;
    private Spieler[] spielerArray;

    public SpielerMovement(Spieler[] spielerArray, SpielfeldGUI spielfeldGUI, JLabel diceLabel) {
        this.spielerArray = spielerArray;
        this.spielfeldGUI = spielfeldGUI;
        this.diceLabel = diceLabel;
        this.ausgewaehlterSpielerIndex = 0; // Erster Spieler beginnt

    }

    public void drawPlayer(Graphics g) {
    	int CELL_SIZE = SpielfeldKonstanten.CELL_SIZE;
    	ausgewaehlterSpieler.draw(g, CELL_SIZE);

    }

    public void handleKeyEvent(KeyEvent e, JPanel panel, JLabel stepsLabel) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            Spieler currentPlayer = spielerArray[ausgewaehlterSpielerIndex];
            int x = ausgewaehlterSpieler.getX();
            int y = ausgewaehlterSpieler.getY();

              updateStepsLabel(stepsLabel); // Aktualisiere das stepsLabel vor dem Setzen des Schritts


            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_NUMPAD8:  // Hoch
                    if (y > 0) 
                        ausgewaehlterSpieler.moveTo(x, y - 1);
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_NUMPAD2:  // Runter
                    if (y < spielfeldGUI.getNUM_CELLS() - 1)
                        ausgewaehlterSpieler.moveTo(x, y + 1);
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_NUMPAD4:  // Links
                    if (x > 0)
                        ausgewaehlterSpieler.moveTo(x - 1, y);
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_NUMPAD6:  // Rechts
                    if (x < spielfeldGUI.getNUM_CELLS() - 1)
                        ausgewaehlterSpieler.moveTo(x + 1, y);
                    break;
            }


                stepsLabel.setText("Verbleibende Schritte: " + ausgewaehlterSpieler.getSteps());
            	panel.repaint();

                checkNextPlayer(stepsLabel);
    }
}

private void checkNextPlayer(JLabel stepsLabel) {
    // Prüfen, ob der aktuelle Spieler keine Schritte mehr übrig hat
    if (ausgewaehlterSpieler.getSteps() == 0) {
        int previousPlayerIndex = ausgewaehlterSpielerIndex; // Vorheriger Spielerindex
        ausgewaehlterSpielerIndex = (ausgewaehlterSpielerIndex + 1) % spielerArray.length;
        ausgewaehlterSpieler = spielerArray[ausgewaehlterSpielerIndex];

        // Überprüfen, ob der aktuelle Spieler gewechselt wurde
        if (previousPlayerIndex != ausgewaehlterSpielerIndex) {
            JOptionPane.showMessageDialog(null, "Spieler " + ausgewaehlterSpieler.getPlayerId() + " ist dran!"); // Nachricht anzeigen
            ausgewaehlterSpieler.setSteps(rollDice()); // Würfeln für den neuen Spieler
            updateStepsLabel(stepsLabel); // Aktualisiere das stepsLabel nach dem Spielerwechsel
        } else {
            ausgewaehlterSpieler.setSteps(0); // Setze Schritte auf 0 für den aktuellen Spieler
            stepsLabel.setText("Verbleibende Schritte: " + ausgewaehlterSpieler.getSteps());
            JOptionPane.showMessageDialog(null, "Runde beendet! Nächster Spieler ist dran.");
        }
    }
}

private void updateStepsLabel(JLabel stepsLabel) {
    int remainingSteps = ausgewaehlterSpieler.getSteps();
    stepsLabel.setText("Verbleibende Schritte: " + remainingSteps);
}

public int rollDice() {  
    int diceRoll = random.nextInt(6) + 1;
    diceLabel.setText("Würfelergebnis: " + diceRoll); // Zeigt das Würfelergebnis an
    return diceRoll;
}
}