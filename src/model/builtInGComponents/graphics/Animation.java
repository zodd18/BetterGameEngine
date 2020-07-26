package model.builtInGComponents.graphics;

import model.components.GComponent;
import model.settings.builtIn.PositionSizeSettings;
import model.utils.Hourglass;
import model.utils.Vector2D;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Animation extends GComponent {

    private Hourglass timer;

    private LinkedList<Sprite> frames;
    private Iterator<Sprite> it;

    private Sprite currentFrame;

    private PositionSizeSettings data;

    public Animation(Vector2D position, Dimension size) {
        this(position, size, 0);
    }

    public Animation(Vector2D position, Dimension size, int time) {
        timer = new Hourglass(time);
        timer.setRemainingTime(0);

        frames = new LinkedList<>();

        data = new PositionSizeSettings(position, size);

        disableInput();
    }

    @Override
    public void additionRoutine() {
        init();
    }

    public void init() {
        it = frames.iterator();
    }

    public void addFrame(String path) {
        addFrame(new Sprite(data.getPosition(), data.getSize(), path));
    }

    public void addFrame(Sprite sprite) {
        frames.add(sprite);
    }

    public Sprite get(int i) {
        return frames.get(i);
    }

    @Override
    public void update() {
        timer.update();
    }

    @Override
    public void render(Graphics2D g) {
        if (timer.finished()) {

            if (!it.hasNext()) init();

            currentFrame = it.next();
            timer.reset();
        }

        currentFrame.render(g);
    }

    public Hourglass getTimer() {
        return timer;
    }
}
