package feup.ldts.trex.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class Controller {

    public KeyStroke nextAction(Screen screen) throws IOException {
        return screen.pollInput();
    }

}
