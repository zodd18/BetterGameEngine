package model.components;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.components.extensions.GExtensionMap;
import model.generalInterfaces.uri.URI;
import model.scenes.Scene;
import model.scenes.layers.Layer;
import model.settings.GSettings;
import model.settings.Settings;

import java.awt.*;

public abstract class GComponent implements URI, Addition {

    private Layer parentLayer;

    private GSettings settings = new Settings();

    private GComponent parentComponent;

    private String name = "Anonymous";

    private GExtensionMap extensions = new GExtensionMap(this);

    private boolean canUpdate = true, canRender = true, canInput = true;

    public GComponent() {

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

    @Override
    public void additionRoutine() {

    }

    public boolean destroy() {
        if (extensions == null) extensions = new GExtensionMap(this);

        for (GComponent extension : extensions.values()) {
            extension.getParentLayer().destroyGComponent(extension);
        }

        return getParentLayer().destroyGComponent(this);
    }

    public Layer getParentLayer() {
        return parentLayer;
    }

    public void setParentLayer(Layer parentLayer) {
        this.parentLayer = parentLayer;
    }

    public Scene getParentScene() {
        return getParentLayer().getParentScene();
    }

    // ------------------------------ Enable/Disable actions ------------------------------

    public boolean canRender() {
        return canRender;
    }

    public boolean canUpdate() {
        return canUpdate;
    }

    public boolean canInput() {
        return canInput;
    }

    public void enableRender() {
        canRender = true;
    }

    public void enableUpdate() {
        canUpdate = true;
    }

    public void enableInput() {
        canInput = true;
    }

    public void disableRender() {
        canRender = false;
    }

    public void disableUpdate() {
        canUpdate = false;
    }

    public void disableInput() {
        canInput = false;
    }

    // ------------------------------ Extensions ------------------------------

    public boolean addGExtension(String name, Layer l, GComponent extension) {
        return extensions.put(name, l, extension) != null;
    }

    public boolean addGExtension(String name, GComponent extension) {
        return extensions.put(name, extension) != null;
    }

    public boolean addGExtension(GComponent extension) {
        return extensions.addAnonymousExtension(extension) != null;
    }

    public boolean addGExtension(Layer l, GComponent extension) {
        return extensions.addAnonymousExtension(l, extension) != null;
    }

    public boolean destroyGExtension(String name) {
        GComponent extension = extensions.get(name);
        boolean success = false;

        if (extension != null) {
            success = extension.destroy();
            extensions.remove(name);
        }

        return success;
    }

    public boolean destroyAnonymousGExtension(GComponent gc) {
        return extensions.destroyAnonymousGExtension(gc);
    }

    public void destroyAllGExtensions() {
        extensions.values().forEach(GComponent::destroy);
        extensions.clear();
    }

    public GSettings getSettings() {
        return settings;
    }

    public GComponent getExtension(String name) {
        return extensions.get(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public GComponent getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(GComponent parentComponent) {
        this.parentComponent = parentComponent;
    }
}
