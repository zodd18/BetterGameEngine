package model.adapters.keys;

import java.util.HashMap;
import java.util.StringJoiner;

public class KeyMap extends HashMap<Integer, Key> {

    public KeyMap() {

    }

    @Override
    public Key get(Object keyCode) {
        Key k = super.get(keyCode);

        if (k == null) {
            k = new Key((Integer) keyCode);
            put((Integer) keyCode, k);
        }

        return k;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        forEach((key, value) -> {
            sj.add(value.toString());
        });

        return sj.toString();
    }
}
