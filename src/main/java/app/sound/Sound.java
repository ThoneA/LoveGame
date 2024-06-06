package app.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import app.utils.Constants;
import app.utils.Pair;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Class is responsible for handling playback of sound effects.
 */
public class Sound {

    private static Map<SoundEvent, Clip> loadedSounds = new HashMap<>();

    /**
     * Plays the sound corresponding with the event. If event not initialized it
     * will not play the sound.
     * 
     * @param event
     */
    public static void play(SoundEvent event) {
        Clip clip = loadedSounds.get(event);
        if (clip == null)
            return;
        clip.start();
    }

    /**
     * Loads sounds into memory.
     */
    public static void initializeSounds() {
        dispose();
        List<Pair<String, SoundEvent>> filePaths = Constants.SOUND_FILE_PATHS;

        try {
            for (Pair<String, SoundEvent> pair : filePaths) {
                String path = pair.a();
                File file = new File(path);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                SoundEvent event = pair.b();
                loadedSounds.put(event, clip);
            }
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /**
     * Disposes of loaded sounds.
     */
    public static void dispose() {
        for (Clip clip : loadedSounds.values()) {
            clip.close();
        }
        loadedSounds.clear();
    }
}
