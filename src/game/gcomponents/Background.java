package game.gcomponents;

import model.Game;
import model.components.GComponent;
import view.Screen;

import java.awt.*;

public class Background extends GComponent {

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.getScreen().getWidth(), Game.getScreen().getHeight());
    }
}
