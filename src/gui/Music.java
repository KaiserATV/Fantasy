package gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music implements Runnable {
    private String filename;

    public Music(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            File file = new File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Wenn Sie möchten, dass die Musik dauerhaft läuft, verwenden Sie diese Zeile:
            // clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
