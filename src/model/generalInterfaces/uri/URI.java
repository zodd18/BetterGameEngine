package model.generalInterfaces.uri;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;

import java.awt.*;

public interface URI extends Renderer, Updater, InputHandler {
    @Override
    void update();

    @Override
    void render(Graphics2D g);

    @Override
    void input(GMouseAdapter m, GKeyboardAdapter k);
}
