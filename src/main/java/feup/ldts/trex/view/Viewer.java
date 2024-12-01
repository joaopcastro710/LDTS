package feup.ldts.trex.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface Viewer {
    Font setFont(String name, int size) throws URISyntaxException, IOException, FontFormatException;

    Terminal setTerminal(TerminalSize ts, AWTTerminalFontConfiguration cfg) throws IOException;

    Screen setScreen(Terminal terminal) throws IOException;

    void drawBackground(TextGraphics graphics, String color, TerminalSize terminalSize);

    void drawText(TextGraphics graphics, String color, TerminalPosition position, String text);

    void screenRefresh(Screen screen) throws IOException;

    void closeScreen(Screen screen) throws IOException;
}
