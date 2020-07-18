package model.scenes.layers;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.components.GComponent;
import model.scenes.Scene;
import model.uri.URI;

import java.awt.*;
import java.util.LinkedList;

public class Layer implements URI {

    private Scene parent;

    private LinkedList<GComponent> gComponents;

    private LinkedList<GComponent> waitingGComponents;
    private LinkedList<GComponent> destroyingGComponents;

    public Layer() {
        this.gComponents = new LinkedList<>();
        this.waitingGComponents = new LinkedList<>();
        this.destroyingGComponents = new LinkedList<>();
    }

    @Override
    public void update() {
        gComponents.forEach(GComponent::update);

        // Adding internally spawned gComponents
        waitingGComponents.forEach(gc -> addGComponent(gc));
        waitingGComponents.clear();

        // Removing gComponents ready for destruction
        destroyingGComponents.forEach(gc -> gComponents.remove(gc));
        destroyingGComponents.clear();
    }

    @Override
    public void render(Graphics2D g) {
        gComponents.forEach(gc -> gc.render(g));
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        gComponents.forEach(gc -> gc.input(m, k));
    }

    public boolean addGComponent(GComponent gc) {
        gc.setParent(this);
        return gComponents.add(gc);
    }

    public boolean enqueueGComponent(GComponent gc) {
        return waitingGComponents.add(gc);
    }

    public boolean destroyGComponent(GComponent gc) {
        return destroyingGComponents.add(gc);
    }

    public boolean removeGComponent(GComponent gc) {
        return gComponents.remove(gc);
    }

    public Scene getParent() {
        return parent;
    }

    public void setParent(Scene parent) {
        this.parent = parent;
    }
}