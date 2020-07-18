package model.components;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.Scene;
import model.scenes.layers.Layer;
import model.uri.URI;

import java.awt.*;

public abstract class GComponent implements URI {

    private Layer parent;

    protected boolean deletion;

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {

    }

    public boolean deletion() {
        return deletion;
    }

    public boolean destroy() {
        return getParent().destroyGComponent(this);
    }

    public Layer getParent() {
        return parent;
    }

    public void setParent(Layer parent) {
        this.parent = parent;
    }

    public Scene getParentScene() {
        return getParent().getParent();
    }
}
