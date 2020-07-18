package model;

public class GStateTable {

    private boolean running;

    private int fps;

    private double TARGET_FPS;
    private double GAME_HERTZ;

    public GStateTable() {
        fps = 0;
        running = false;
        TARGET_FPS = 60.0;
        GAME_HERTZ = 60.0;
    }

    public int getFPS() {
        return fps;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }

    public double getTARGET_FPS() {
        return TARGET_FPS;
    }

    public void setTARGET_FPS(double TARGET_FPS) {
        this.TARGET_FPS = TARGET_FPS;
    }

    public double getGAME_HERTZ() {
        return GAME_HERTZ;
    }

    public void setGAME_HERTZ(double GAME_HERTZ) {
        this.GAME_HERTZ = GAME_HERTZ;
    }

    // TIME BEFORE UPDATE
    public double getTBU() {
        return 1000000000 / GAME_HERTZ;
    }
}
