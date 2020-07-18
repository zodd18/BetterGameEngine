package model;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.uri.URI;
import model.scenes.SceneManager;
import model.utils.GLog;
import view.Screen;

import java.awt.*;

public abstract class Game extends Thread implements URI {

    private final GMouseAdapter mouse;
    private final GKeyboardAdapter keyboard;

    private static Screen s;
    private static GStateTable gameState;
    private static SceneManager sm;

    public Game(Screen s, GMouseAdapter mouse, GKeyboardAdapter keyboard) {
        Game.s = s;
        this.mouse = mouse;
        this.keyboard = keyboard;

        gameState = new GStateTable();
        sm = new SceneManager();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {

    }

    @Override
    public void run() {

        // GAME RUNNING
        gameState.setRunning(true);

        gameState.setGAME_HERTZ(60.0);

        final int MUBR = 5; // MUST UPDATE BEFORE RENDER

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        gameState.setTARGET_FPS(60.0);
        final double TTBR = 1000000000 / gameState.getTARGET_FPS(); // TOTAL TIME BEFORE RENDER

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        // GAME LOOP
        while (gameState.isRunning()) {
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > gameState.getTBU()) && (updateCount < MUBR)) {
                update();
                input(mouse, keyboard);
                lastUpdateTime += gameState.getTBU();
                updateCount++;
            }

            if (now - lastUpdateTime > gameState.getTBU()) {
                lastUpdateTime = now - gameState.getTBU();
            }

            input(mouse, keyboard);
            update();
            render(s.getGraphics());

            lastRenderTime = now;
            frameCount++;
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != gameState.getFPS()) {
                    gameState.setFPS(frameCount);
                }

                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < gameState.getTBU()) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    GLog.errorLog("yielding thread");
                    e.printStackTrace();
                }
                now = System.nanoTime();
            }
        }
    }


    public static Screen getScreen() {
        return s;
    }

    public static GStateTable getGameState() {
        return gameState;
    }

    public static SceneManager getSceneManager() {
        return sm;
    }
}

