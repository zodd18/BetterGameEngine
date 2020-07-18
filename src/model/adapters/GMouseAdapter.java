package model.adapters;

import model.utils.GLog;
import view.Screen;

import java.awt.event.*;

public class GMouseAdapter implements MouseListener, MouseMotionListener {

    private final Screen s;

    private int mouseX = 0, mouseY = 0;
    private static boolean pressed = false;

    public GMouseAdapter(Screen s) {
        this.s = s;
        s.addMouseListener(this);
        s.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        GLog.inputLog(getClass().getSimpleName() + "::Pressed -> [" + e.getX() + ", " + e.getY() + "]");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

//        GLog.inputLog(this.toString());
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isLeft() {
        return mouseX <= (s.getWidth() / 2);
    }

    public boolean isRight() {
        return !isLeft();
    }

    @Override
    public String toString() {
        return String.format(getClass().getSimpleName() + "::Position -> [%d, %d]", mouseX, mouseY);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
