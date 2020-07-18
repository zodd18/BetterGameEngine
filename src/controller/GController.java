package controller;

import game.BEGame;
import model.Game;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.uri.Renderer;
import model.utils.GLog;
import model.utils.GraphicsUtil;
import view.GFrame;
import view.Screen;

import javax.swing.*;
import java.awt.*;

public class GController implements Renderer {

    private final GMouseAdapter gMouse;
    private final GKeyboardAdapter gKeyboard;

    private final GFrame gFrame;
    private final Screen s;

    private final Game game;

    public GController(GFrame gFrame) {
        GLog.setSelectedLevel(GLog.MAX);

        this.gFrame = gFrame;
        this.gFrame.setVisible(true);

        s = gFrame.getScreen();
        render(s.getGraphics());

        gMouse = new GMouseAdapter(s);
        gKeyboard = new GKeyboardAdapter(s);

        game = new BEGame(s, gMouse, gKeyboard);
        game.start();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, s.getWidth(), s.getHeight());

        g.setColor(Color.WHITE);
        GraphicsUtil.drawCenteredString(g, "waiting for response...", new Rectangle(20, 0, 100, 20), ((JPanel) s).getFont());

        g.setColor(Color.WHITE);
        GraphicsUtil.drawCenteredString(g, "Oops... Something went wrong... :(", new Rectangle(345, 275, 100, 20), new Font("Arial", Font.PLAIN, 32));
    }
}
