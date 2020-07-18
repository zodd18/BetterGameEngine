package model.scenes;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.scenes.layers.Layer;
import model.uri.URI;

import java.awt.*;
import java.util.HashMap;

public abstract class Scene extends HashMap<String, Layer> implements URI {

    protected Scene nextScene;

    protected boolean terminated;

    public Scene() {
        this.terminated = false;
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

    public Layer addLayer(String key) {
        Layer l = new Layer();
        l.setParent(this);

        return super.put(key, l);
    }

    @Override
    public Layer put(String key, Layer value) {
        value.setParent(this);

        return super.put(key, value);
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void exit() {

    }

    public boolean isTerminated() {
        return terminated;
    }
}
