package model.builtInGComponents.collisions;

import model.components.GComponent;
import model.scenes.layers.Layer;
import model.settings.GSetting;
import model.utils.GLog;
import model.utils.Vector2D;

import java.awt.*;
import java.util.LinkedList;

import static model.builtInGComponents.collisions.CollisionDictionary.*;

public class CollisionBox extends GComponent {

    private Color renderingColor = Color.GREEN;

    public CollisionBox(Vector2D position, Dimension size, int xOffset, int yOffset, int widthOffset, int heightOffset) {
        getSettings().addSetting(new GSetting(POSITION, position));
        getSettings().addSetting(new GSetting(SIZE, size));
        getSettings().addSetting(new GSetting(X_OFF, xOffset));
        getSettings().addSetting(new GSetting(Y_OFF, yOffset));
        getSettings().addSetting(new GSetting(W_OFF, widthOffset));
        getSettings().addSetting(new GSetting(H_OFF, heightOffset));
    }

    public boolean collides(CollisionBox other) {
        return  !other.equals(this) &&
                getX() < other.getX() + other.getWidth() &&
                getX() + getWidth() > other.getX() &&
                getY() < other.getY() + other.getHeight() &&
                getY() + getHeight() > other.getY();
    }

    public LinkedList<CollisionBox> getCollisions(Layer l) {
        LinkedList<CollisionBox> collisionBoxes = new LinkedList<>();

        getParentLayer().getComponents().forEach(gc -> {
            if (gc instanceof CollisionBox && collides((CollisionBox) gc))
                collisionBoxes.add((CollisionBox) gc);
        });

        return collisionBoxes;
    }

    public LinkedList<CollisionBox> getCollisions() {
        return getCollisions(getParentLayer());
    }

    public LinkedList<GComponent> getCollidedGComponents(Layer l) {
        LinkedList<GComponent> collidedGComponents = new LinkedList<>();

        l.getComponents().forEach(gc -> {
            if (gc instanceof CollisionBox && collides((CollisionBox) gc))
                collidedGComponents.add(gc.getParentComponent());
        });

        return collidedGComponents;
    }

    public LinkedList<GComponent> getCollidedGComponents() {
        return getCollidedGComponents(getParentLayer());
    }

    public void setRenderingColor(Color renderingColor) {
        this.renderingColor = renderingColor;
    }

    @Override
    public void render(Graphics2D g) {
        if (GLog.getSelectedLevel() == GLog.ALL) {
            g.setColor(renderingColor);
            g.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public int getX() {
        return ((Vector2D) getSettings().get(POSITION).value()).getX() + getXOffset();
    }

    public int getY() {
        return ((Vector2D) getSettings().get(POSITION).value()).getY() + getYOffset();
    }

    public int getWidth() {
        return (int) ((Dimension) getSettings().get(SIZE).value()).getWidth() + getWOffset();
    }

    public int getHeight() {
        return (int) ((Dimension) getSettings().get(SIZE).value()).getHeight() + getHOffset();
    }

    public int getXOffset() {
        return getSettings().get(X_OFF).valueAsInt();
    }

    public int getYOffset() {
        return getSettings().get(Y_OFF).valueAsInt();
    }

    public int getWOffset() {
        return getSettings().get(W_OFF).valueAsInt();
    }

    public int getHOffset() {
        return getSettings().get(H_OFF).valueAsInt();
    }
}
