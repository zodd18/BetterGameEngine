package model.builtInGComponents;

import model.Game;
import model.components.GComponent;
import model.utils.GraphicsUtil;

import java.awt.*;

public class FPS extends GComponent {

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setFont(new Font("Arial", Font.PLAIN, 12));

        g.setColor(Color.WHITE);
        GraphicsUtil.drawCenteredString(g, String.format("FPS: %d", Game.getGameState().getFPS()), new Rectangle(0, 5, 55, 20), g.getFont());
    }
}
