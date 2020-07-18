package model.uri;

import model.adapters.GKeyboardAdapter;
import model.adapters.GMouseAdapter;

public interface InputHandler {

    void input(GMouseAdapter m, GKeyboardAdapter k);
}
