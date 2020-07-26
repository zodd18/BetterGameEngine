package game;

import game.scenes.Scene0;
import model.Game;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.Scene;
import model.scenes.SceneException;
import model.settings.GSetting;
import model.utils.GLog;
import view.Screen;

import java.awt.*;

public class BEGame extends Game {

    public BEGame(Screen s, GMouseAdapter mouse, GKeyboardAdapter keyboard) {
        super(s, mouse, keyboard);

        Scene s0 = new Scene0();
        getSceneManager().put("SCENE0", s0);

        try {
            getSceneManager().changeScene("SCENE0");
        } catch (SceneException e) {
            e.printStackTrace();
        }

        GLog.setSelectedLevel(GLog.ALL);
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
