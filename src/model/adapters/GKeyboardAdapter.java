package model.adapters;

import model.adapters.keys.Key;
import model.adapters.keys.KeyMap;
import model.utils.GLog;
import view.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GKeyboardAdapter implements KeyListener {

    private final Screen s;

    private KeyMap pressedKeys;

    public GKeyboardAdapter(Screen s) {
        this.s = s;
        pressedKeys = new KeyMap();

        s.addKeyListener(this);
    }

    public boolean isPressed(int KEY_CODE) {
        return pressedKeys.containsKey(KEY_CODE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        GLog.inputLog(String.format("%s::Event::Typed -> %d", getClass().getSimpleName(), e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Key k = pressedKeys.get(e.getKeyCode());
        k.press();

        GLog.inputLog(pressedKeys);

//        GLog.inputLog(String.format("%s::Event::Pressed -> %d", getClass().getSimpleName(), e.getExtendedKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.get(e.getKeyCode()).release();
//        GLog.inputLog(keys);
//        GLog.inputLog(String.format("%s::Event::Released -> %d", getClass().getSimpleName(), e.getKeyCode()));
        pressedKeys.remove(e.getKeyCode());
    }
}
