package game.scenes;

import game.gcomponents.Background;
import game.gcomponents.Enemy;
import model.Game;
import model.builtInGComponents.FPS;
import game.gcomponents.Player;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.Scene;
import model.utils.Hourglass;
import model.utils.RandomUtil;

import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

public class Scene0 extends Scene {

    private Hourglass alienSpawnTimer;

    public Scene0() {
        super();
        alienSpawnTimer = new Hourglass(125);

        addLayer("BACKGROUND");
        addLayer("PLAYER");
        addLayer("ENEMIES");
        addLayer("COLLISIONS");
        addLayer("UI");

        get("BACKGROUND").addGComponent(new Background());
        get("PLAYER").addGComponent(new Player(300, 300, 32, 32, 3));
    }

    @Override
    public void update() {
        alienSpawnTimer.update();

        get("PLAYER").update();
        get("ENEMIES").update();

        if (alienSpawnTimer.finished()) {
            get("ENEMIES").addGComponent(new Enemy(RandomUtil.getRandom(0, Game.getScreen().getWidth()), RandomUtil.getRandom(0, Game.getScreen().getHeight()), 50, 50, 1));
            alienSpawnTimer.reset();
        }
    }

    @Override
    public void render(Graphics2D g) {
        get("BACKGROUND").render(g);
        get("PLAYER").render(g);
        get("ENEMIES").render(g);
        get("COLLISIONS").render(g);
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        get("PLAYER").input(m, k);
    }
}