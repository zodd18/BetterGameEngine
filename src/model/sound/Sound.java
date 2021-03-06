package model.sound;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Sound {
    private final Set<Clip> clips = Collections.synchronizedSet(new HashSet<>());

    private final AudioFormat format;
    private final byte[] bytes;

    public Sound(String path) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                new BufferedInputStream(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path))));
            DataInputStream dis = new DataInputStream(stream);
            this.format = stream.getFormat();
            this.bytes = new byte[(int)(stream.getFrameLength() * format.getFrameSize())];
            dis.readFully(bytes);



            for (int i = 0; i < 4; i++) {
                this.createNewClip();
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new Error(e);
        }
    }

    private Clip createNewClip() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(this.format, this.bytes, 0, this.bytes.length);
            clips.add(clip);
            return clip;
        } catch (LineUnavailableException e) {
            throw new Error(e);
        }
    }

    public void play() {
        new Thread(() -> {
            Clip clip = clips.stream()
                .filter(c ->
                    c.getFramePosition() == 0 ||
                        c.getFramePosition() == c.getFrameLength())
                .findFirst()
                .orElseGet(this::createNewClip);

            clip.setFramePosition(0);
            clip.start();
        }).start();
    }

    public void loop() {
        new Thread(() -> {
            Clip clip = clips.stream()
                    .filter(c ->
                            c.getFramePosition() == 0 ||
                                    c.getFramePosition() == c.getFrameLength())
                    .findFirst()
                    .orElseGet(this::createNewClip);

            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }).start();
    }

    public void stop() {
        clips.forEach(c -> {
            c.stop();
            c.close();
        });
    }
}
