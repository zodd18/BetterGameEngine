package model.scenes;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;
import model.generalInterfaces.uri.URI;
import model.utils.GLog;

import java.awt.*;
import java.io.*;
import java.util.*;

public class SceneManager implements URI, Map<String, Scene>, Serializable {

    private HashMap<String, Scene> scenes;

    private Scene currentScene;

    public SceneManager() {
        scenes = new HashMap<>();
    }

    public boolean loadState(String path) {
        boolean success = true;

        ObjectInputStream objectinputstream = null;

        try {

            FileInputStream streamIn = new FileInputStream(path);
            objectinputstream = new ObjectInputStream(streamIn);

            scenes = (HashMap<String, Scene>) objectinputstream.readObject();
            currentScene = (Scene) objectinputstream.readObject();
        } catch (Exception e) {

            success = false;
            GLog.warningLog(String.format("save game in %s does not exist", path));
        } finally {

            if(objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

    public boolean saveState(String path) {
        boolean success = true;

        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try{
            fileOutputStream = new FileOutputStream(path, false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(scenes);
            objectOutputStream.writeObject(currentScene);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        return success;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void changeScene(String sceneName) throws SceneException {
        if (scenes.get(sceneName) == null)
            throw new SceneException(String.format("unknown scene: \"%s\"", sceneName));
        else
            currentScene = scenes.get(sceneName);
    }

    // --------- URI ---------

    @Override
    public void update() {
        for (Scene s : scenes.values()) {
//            if (!(s instanceof UIScene || s instanceof OSScene))
                s.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        currentScene.render(g);
    }

    @Override
    public void input(GMouseAdapter m, GKeyboardAdapter k) {
        currentScene.input(m, k);
    }

    // --------- Interface methods: Map<String, Scene> ---------

    @Override
    public int size() {
        return scenes.size();
    }

    @Override
    public boolean isEmpty() {
        return scenes.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return scenes.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return scenes.containsKey(value);
    }

    @Override
    public Scene get(Object key) {
        return scenes.get(key);
    }

    @Override
    public Scene put(String key, Scene value) {
        value.setName(key);
        return scenes.put(key, value);
    }

    @Override
    public Scene remove(Object key) {
        return scenes.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Scene> m) {
        scenes.putAll(m);
    }

    @Override
    public void clear() {
        scenes.clear();
    }

    @Override
    public Set<String> keySet() {
        return scenes.keySet();
    }

    @Override
    public Collection<Scene> values() {
        return scenes.values();
    }

    @Override
    public Set<Entry<String, Scene>> entrySet() {
        return scenes.entrySet();
    }
}
