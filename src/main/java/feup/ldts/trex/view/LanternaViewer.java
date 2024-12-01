package feup.ldts.trex.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaViewer implements Viewer{

    public Font setFont(String name, int size) throws URISyntaxException, IOException, FontFormatException {
        URL resource = Viewer.class.getClassLoader().getResource(name);
        if (resource == null) return null;
        File fontFile = new File(resource.toURI());
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font loadedFont = customFont.deriveFont(Font.PLAIN, size);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
        return loadedFont;
    }

    public Terminal setTerminal(TerminalSize ts, AWTTerminalFontConfiguration cfg) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(ts).setTerminalEmulatorFontConfiguration(cfg);
        terminalFactory.setForceAWTOverSwing(true);
        return terminalFactory.createTerminal();
    }

    public Screen setScreen(Terminal terminal) throws IOException {
        Screen s = new TerminalScreen(terminal);
        s.setCursorPosition(null);
        s.startScreen();
        return s;
    }

    public void drawBackground(TextGraphics graphics, String color, TerminalSize size){
        graphics.setBackgroundColor(TextColor.ANSI.Factory.fromString(color));
        graphics.fillRectangle(TerminalPosition.TOP_LEFT_CORNER,size,' ');
    }

    public void drawText(TextGraphics graphics, String color, TerminalPosition position, String text){
        graphics.setForegroundColor(TextColor.ANSI.Factory.fromString(color));
        graphics.putString(position, text);
    }

    public void screenRefresh(Screen screen) throws IOException {
        screen.refresh();
    }

    public void closeScreen(Screen screen) throws IOException {
        screen.close();
    }
}
