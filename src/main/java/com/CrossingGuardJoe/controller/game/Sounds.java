package com.CrossingGuardJoe.controller.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;
import java.util.Random;

public class Sounds {
    private Clip sound;

    public Sounds(String sound) {
        this.sound = loadSound(sound);
    }

    private Clip loadSound(String sound) {
        try {
            InputStream audioInputStream = getClass().getClassLoader().getResourceAsStream(sound);
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioInputStream);
            clip.open(ais);
            setVolume(clip, 0.5f);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void play() {
        if (!sound.isRunning()) {
            sound.setFramePosition(0);
            sound.start();
        }
    }

    public void stop() {
        sound.stop();
    }

    public void loop() {
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playRandomSound(Sounds sound1, Sounds sound2) {
        Random random = new Random();
        if (random.nextBoolean()) {
            sound1.play();
        } else {
            sound2.play();
        }
    }

    public static void setVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }
}
