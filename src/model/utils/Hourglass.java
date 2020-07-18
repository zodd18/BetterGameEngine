package model.utils;

import model.uri.Updater;

public class Hourglass implements Updater {

    private int initialTime;
    private int currentTime;

    private boolean stopped;

    private int speed;

    public Hourglass(int time) {
        this.initialTime = time;
        this.currentTime = initialTime;
        this.speed = 1;
        this.stopped = false;
    }

    public Hourglass(int time, int speed) {
        this(time);
        this.speed = speed;
    }

    public void stop() {
        stopped = true;
    }

    public void resume() {
        stopped = false;
    }

    @Override
    public void update() {
        if (currentTime > 0 && !stopped) currentTime -= speed;
    }

    public boolean finished() {
        return currentTime <= 0;
    }

    public void reset() {
        currentTime = initialTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getInitialTime() {
        return initialTime;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
