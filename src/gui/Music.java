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
            if(!file.exists()){
                System.out.println("File not found: " + file.getAbsolutePath());
                return;
}
            AudioFileFormat.Type[] types = AudioSystem.getAudioFileTypes();
            for (AudioFileFormat.Type type : types){
            System.out.println(type);
}

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
