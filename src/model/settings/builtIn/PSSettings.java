package model.settings.builtIn;

import model.settings.GSettings;
import model.utils.Vector2D;

import java.awt.*;

public interface PSSettings extends GSettings {

    Vector2D getPosition();

    Dimension getSize();

    int getX();

    void setX(int x);

    void incX(int amount);

    int getY();

    void setY(int y);

    void incY(int amount);

    int getWidth();

    void setWidth(int w);

    void incWidth(int amount);

    int getHeight();

    void setHeight(int h);

    void incHeight(int amount);
}
