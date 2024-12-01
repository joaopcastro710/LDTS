package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverViewer {

    Viewer viewer = new LanternaViewer();

    public Screen initializeGameOverScreen() throws URISyntaxException, IOException, FontFormatException {
        Font font1 = viewer.setFont("Fonts/prstart.ttf", 72);

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(font1);

        TerminalSize terminalSize = new TerminalSize(13, 9);
        Terminal terminal = viewer.setTerminal(terminalSize, cfg);

        return(viewer.setScreen(terminal));
    }

    public void drawGameOverScreen(Screen screen, int score, String player) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();

        player=player.concat("___");

        viewer.drawBackground(graphics, "#012000", screen.getTerminalSize());
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.YELLOW), new TerminalPosition(2, 1), "GAME OVER");
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.YELLOW_BRIGHT), new TerminalPosition(1, 3), "Score: " + score);
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.CYAN_BRIGHT), new TerminalPosition(2, 5), "Name: " + player.substring(0,3));
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.GREEN_BRIGHT), new TerminalPosition(0, 7),"Press <Enter>");

        viewer.screenRefresh(screen);
    }

    public void drawGameOverScreenTwoPlayers(Screen screen, boolean p1Won) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();

        viewer.drawBackground(graphics, "#012000", screen.getTerminalSize());
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.YELLOW), new TerminalPosition(2, 1), "GAME OVER");
        if (p1Won) viewer.drawText(graphics, String.valueOf(TextColor.ANSI.YELLOW_BRIGHT), new TerminalPosition(1, 4), "Winner: P1");
        else viewer.drawText(graphics, String.valueOf(TextColor.ANSI.CYAN_BRIGHT), new TerminalPosition(1, 4), "Winner: P2");
        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.GREEN_BRIGHT), new TerminalPosition(0, 7),"Press <Enter>");

        viewer.screenRefresh(screen);
    }

}
