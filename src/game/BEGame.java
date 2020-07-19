package game;

import game.gcomponents.Player;
import game.scenes.Scene0;
import model.Game;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.Scene;
import model.scenes.SceneException;
import view.Screen;

import java.awt.*;

public class BEGame extends Game {

    public BEGame(Screen s, GMouseAdapter mouse, GKeyboardAdapter keyboard) {
        super(s, mouse, keyboard);

        Scene s0 = new Scene0();
        getSceneManager().put("SCENE0", s0);

        try {
            getSceneManager().setCurrentScene("SCENE0");
        } catch (SceneException e) {
            e.printStackTrace();
        }

        getSettings().getBooleanSetting("SHOW FPS").enable();
    }

    @Override
    public void update() {
        getSceneManager().update();
    }

    @Override
    public void render(Graphics2D g) {
        getSceneManager().render(g);
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        getSceneManager().input(m, k);
    }
}
