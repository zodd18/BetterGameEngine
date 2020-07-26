package model.scenes.layers;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.components.GComponent;
import model.generalInterfaces.Named;
import model.scenes.Scene;
import model.generalInterfaces.uri.URI;

import java.awt.*;
import java.util.LinkedList;

public class Layer implements URI, Named {

    private Scene parent;

    private LinkedList<GComponent> gComponents;

    private LinkedList<GComponent> waitingGComponents;
    private LinkedList<GComponent> destroyingGComponents;
    private String name;

    public Layer() {
        this.gComponents = new LinkedList<>();
        this.waitingGComponents = new LinkedList<>();
        this.destroyingGComponents = new LinkedList<>();
    }

    @Override
    public void update() {
        gComponents.forEach(gc -> { if (gc.canUpdate()) gc.update(); });
        extraRoutine();
    }

    @Override
    public void render(Graphics2D g) {
        gComponents.forEach(gc -> { if (gc.canRender()) gc.render(g); });
        extraRoutine();
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        gComponents.forEach(gc -> { if (gc.canInput()) gc.input(m, k); });
        extraRoutine();
    }

    private void extraRoutine() {
        recollection();
        destruction();
    }

    private void recollection() {
        // Adding internally spawned gComponents
        waitingGComponents.forEach(gc -> addGComponent(gc));
        waitingGComponents.clear();
    }

    private void destruction() {
        // Removing gComponents ready for destruction
        destroyingGComponents.forEach(gc -> gComponents.remove(gc));
        destroyingGComponents.clear();
    }

    public boolean addGComponent(GComponent gc) {
        gc.setParentLayer(this);
        gc.additionRoutine();
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

    public Scene getParentScene() {
        return parent;
    }

    public void setParent(Scene parent) {
        this.parent = parent;
    }

    public LinkedList<GComponent> getComponents() {
        return gComponents;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}