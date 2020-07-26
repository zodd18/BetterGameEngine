package game.gcomponents;

import model.Game;
import model.builtInGComponents.collisions.CollisionBox;
import model.builtInGComponents.graphics.Animation;
import model.builtInGComponents.graphics.Sprite;
import model.components.GComponent;
import model.settings.builtIn.PSSettings;
import model.settings.builtIn.PositionSizeSettings;
import model.utils.Colors;
import model.utils.GLog;
import model.utils.Hourglass;
import model.utils.RandomUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

public class Enemy extends GComponent {

    private PSSettings data;

    private int xDirection = 0, yDirection = 0;
    private Hourglass directionTimer;

    public Enemy(int x, int y, int w, int h, int s) {
        data = new PositionSizeSettings(x, y, w, h);
        data.addSetting("SPEED", s);
        data.addSetting("COLOR", Color.RED);
        directionTimer = new Hourglass(32);
        directionTimer.setRemainingTime(0);

        disableInput();
    }

    @Override
    public void additionRoutine() {

        //  Extension: CollisionBox
        {
            CollisionBox collisionBox = new CollisionBox(data.getPosition(), data.getSize(), data.getWidth() / 4, data.getHeight() / 4, - data.getWidth() / 2, -data.getHeight() / 2);
            collisionBox.setRenderingColor(Color.WHITE);
            data.addSetting("COLLISION", collisionBox);
            addGExtension(getParentScene().get("COLLISIONS"), collisionBox);
        }

        //  Extension: Animation
        {
            Animation animation = new Animation(data.getPosition(), data.getSize(), 100);
            animation.addFrame("/game/resources/alien.png");
            animation.addFrame("/game/resources/alien.png");

            randomizeSpriteColor(animation.get(0));
            randomizeSpriteColor(animation.get(1));

            animation.additionRoutine();

            addGExtension(animation);

            data.addSetting("ANIMATION", animation);
        }
    }

    @Override
    public void update() {
        directionTimer.update();

        if (directionTimer.finished()) {
            xDirection = RandomUtil.getRandom(0, +1) == 0 ? -1 : +1;
            yDirection = RandomUtil.getRandom(0, +1) == 0 ? -1 : +1;
            directionTimer.reset();
        }

        data.incX(xDirection * data.get("SPEED").valueAsInt());
        if (data.getX() + data.getWidth() > Game.getScreen().getWidth()) data.setX(Game.getScreen().getWidth() - data.getWidth() - 1);
        else if (data.getX() < 0) data.setX(0);
        data.incY(yDirection * data.get("SPEED").valueAsInt());
        if (data.getY() + data.getHeight() > Game.getScreen().getHeight()) data.setY(Game.getScreen().getHeight() - data.getHeight() - 1);
        else if (data.getY() < 0) data.setY(0);

        // Collisions
        LinkedList<GComponent> collisions = ((CollisionBox) data.get("COLLISION").value()).getCollidedGComponents();
        boolean collision = false;
        Iterator<GComponent> it = collisions.iterator();
        while (it.hasNext() && !collision)
            if (!(it.next() instanceof Enemy))
                collision = true;

        if (collision) {
            GLog.log("enemy collision!!!");
            destroy();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (((Animation)data.get("ANIMATION").value()).getTimer().finished()) {
            randomizeSpriteColor(((Animation)data.get("ANIMATION").value()).get(0));
            randomizeSpriteColor(((Animation)data.get("ANIMATION").value()).get(1));
        }
    }

    protected void randomizeSpriteColor(Sprite sprite) {
        Color color = Colors.fixedRandomColor();
        BufferedImage img = sprite.getImg();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color tempColor = new Color(img.getRGB(j, i), true);
                if (!new Color(img.getRGB(j, i)).equals(Color.BLACK) && tempColor.getAlpha() >= 1) {
                    img.setRGB(j, i, color.getRGB());
                }
            }
        }
    }
}
