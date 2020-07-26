package model.components.extensions;

import model.components.GComponent;
import model.scenes.layers.Layer;

import java.util.*;

public class GExtensionMap extends HashMap<String, GComponent> {

    private GComponent owner;

    private LinkedList<GComponent> anonymousExtensions;

    public GExtensionMap(GComponent owner) {
        this.owner = owner;
        anonymousExtensions = new LinkedList<>();
    }

    public GComponent put(String key, Layer l, GComponent value) {
        value.setName(key);
        value.setParentComponent(owner);
        value.setParentLayer(l);

        l.enqueueGComponent(value);

        return super.put(key, value);
    }

    @Override
    public GComponent put(String key, GComponent value) {
        return put(key, owner.getParentLayer(), value);
    }

    public GComponent addAnonymousExtension(GComponent value) {
        return addAnonymousExtension(owner.getParentLayer(), value);
    }

    public GComponent addAnonymousExtension(Layer l, GComponent value) {
        value.setName("Anonymous");
        value.setParentComponent(owner);
        value.setParentLayer(l);
        anonymousExtensions.add(value);

        l.enqueueGComponent(value);

        return value;
    }

    public boolean destroyAnonymousGExtension(GComponent gc) {
        return anonymousExtensions.remove(gc);
    }

    @Override
    public Collection<GComponent> values() {
        List<GComponent> values = new LinkedList<>(super.values());
        values.addAll(anonymousExtensions);

        return values;
    }

    @Override
    public void clear() {
        super.clear();
        anonymousExtensions.clear();
    }
}
