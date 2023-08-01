package spieler;

import karte.Karte;
import javax.swing.*;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class SpielerBewegung {
    private Karte karte;
    private JFrame frame;

    public SpielerBewegung(Karte karte, JFrame frame) {
        this.karte = karte;
        this.frame = frame;
        initSpielerBewegung();
    }

    public void initSpielerBewegung() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_NUMPAD8:
                            karte.getSpieler().moveUp(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                            break;
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_NUMPAD2:
                            karte.getSpieler().moveDown(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                            break;
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_NUMPAD4:
                            karte.getSpieler().moveLeft(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                            break;
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_NUMPAD6:
                            karte.getSpieler().moveRight(karte.getNUM_CELLS(), karte.getNUM_CELLS());
                            break;
                    }
                    
                    frame.repaint();
                }
                return false;
            }
        });
    }
}
