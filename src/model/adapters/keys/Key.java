package model.adapters.keys;

public class Key {

    private boolean pressed;

    private int code;

    public Key(int code) {
        this.code = code;
        this.pressed = false;
    }

    public void toggle() {
        pressed = !pressed;
    }

    public void press() {
        pressed = true;
    }

    public void release() {
        pressed = false;
    }

    @Override
    public String toString() {
        return String.format("%s (%d, %s)", getClass().getSimpleName(), code, pressed ? "pressed" : "released");
    }
}
