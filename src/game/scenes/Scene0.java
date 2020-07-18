package game.scenes;

import game.gcomponents.Background;
import game.gcomponents.FPS;
import game.gcomponents.Player;
import model.GStateTable;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.Scene;

import java.awt.*;

public class Scene0 extends Scene {

    public Scene0() {
        super();

        addLayer("BACKGROUND");
        addLayer("PLAYER");
        addLayer("UI");

        get("BACKGROUND").addGComponent(new Background());
        get("PLAYER").addGComponent(new Player(300, 300, 50, 50, 3));
        get("UI").addGComponent(new FPS());
    }

    @Override
    public void update() {
        super.update();

        get("PLAYER").update();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        get("BACKGROUND").render(g);
        get("PLAYER").render(g);
        get("UI").render(g);
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        super.input(m, k);

        get("PLAYER").input(m, k);
    }
}