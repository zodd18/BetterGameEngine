package model.settings.builtIn;

import model.settings.Settings;
import model.utils.Vector2D;

import java.awt.*;

import static model.settings.builtIn.PSSettingsDictionary.*;

public class PositionSizeSettings extends Settings implements PSSettings {

    public PositionSizeSettings(Vector2D position, Dimension size) {
        addSetting(POSITION, position);
        addSetting(SIZE, size);
    }

    public PositionSizeSettings(int x, int y, int w, int h) {
        this(new Vector2D(x, y), new Dimension(w, h));
    }

    @Override
    public Vector2D getPosition() {
        return ((Vector2D) get(POSITION).value());
    }

    @Override
    public Dimension getSize() {
        return ((Dimension) get(SIZE).value());
    }

    @Override
    public int getX() {
        return getPosition().getX();
    }

    @Override
    public void setX(int x) {
        getPosition().setX(x);
    }

    @Override
    public void incX(int amount) {
        setX(getX() + amount);
    }

    @Override
    public int getY() {
        return getPosition().getY();
    }

    @Override
    public void setY(int y) {
        getPosition().setY(y);
    }

    @Override
    public void incY(int amount) {
        setY(getY() + amount);
    }

    @Override
    public int getWidth() {
        return (int) getSize().getWidth();
    }

    @Override
    public void setWidth(int w) {
        getSize().setSize(w, getHeight());
    }

    @Override
    public void incWidth(int amount) {
        setWidth(getWidth() + amount);
    }

    @Override
    public int getHeight() {
        return (int) getSize().getHeight();
    }

    @Override
    public void setHeight(int h) {
        getSize().setSize(getWidth(), h);
    }

    @Override
    public void incHeight(int amount) {
        setHeight(getHeight() + amount);
    }
}
