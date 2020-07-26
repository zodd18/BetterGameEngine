package model.scenes;

import model.Game;
import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.generalInterfaces.Named;
import model.scenes.layers.Layer;
import model.generalInterfaces.uri.URI;

import java.awt.*;
import java.util.HashMap;

public abstract class Scene extends HashMap<String, Layer> implements URI, Named {

    private Scene nextScene;

    private String sceneName;

    private boolean terminated;

    public Scene() {
        setTerminated(false);
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
        return this.put(key, new Layer());
    }

    @Override
    public Layer put(String key, Layer value) {
        value.setParent(this);
        value.setName(key);

        return super.put(key, value);
    }

    public Scene getNextScene() {
        return nextScene;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void changeToNextScene() {
        try {
            Game.getSceneManager().changeScene(getNextScene().getName());
        } catch (SceneException e) {
            e.printStackTrace();
        }
    }

    public void exit() {

    }

    @Override
    public void setName(String sceneName) {
        this.sceneName = sceneName;
    }

    @Override
    public String getName() {
        return sceneName;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public boolean isTerminated() {
        return terminated;
    }
}
