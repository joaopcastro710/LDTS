package feup.ldts.trex.view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.Viewer;
import feup.ldts.trex.view.elements.LayoutViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewer{

    Viewer viewer = new LanternaViewer();
    LayoutViewer layoutViewer = new LayoutViewer();

    public Screen initializeMenu() throws URISyntaxException, IOException, FontFormatException {
        Font font1 = viewer.setFont("Fonts/versao7.ttf", 72);
        Font font2 = viewer.setFont("Fonts/prstart.ttf", 72);

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(font1,font2);

        TerminalSize terminalSize = new TerminalSize(16, 12);
        Terminal terminal = viewer.setTerminal(terminalSize, cfg);
        return viewer.setScreen(terminal);
    }

    public void closeMenu(Screen screen) throws IOException {
        viewer.closeScreen(screen);
    }

    public void drawMenu(Screen screen, Layout layout, int optionMenu) throws URISyntaxException, IOException, FontFormatException {

        TextGraphics graphics = screen.newTextGraphics();

        viewer.drawBackground(graphics, "#012000",screen.getTerminalSize());

        drawOptions(optionMenu, graphics);

        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.CYAN),new TerminalPosition(1,9),"Controls ^ v >");

        layoutViewer.drawLayout(graphics, layout, 11);

        viewer.screenRefresh(screen);
    }

    public void drawOptions(int optionMenu, TextGraphics graphics){
        String[] text = new String[]{"TNew GameT", "New Game", "T2player gameT", "2player game",
                "TLeaderboardT", "Leaderboard","TLeaveT","Leave"};
        TerminalPosition[] position = new TerminalPosition[]{new TerminalPosition(3,1), new TerminalPosition(4,1),
                new TerminalPosition(1,3), new TerminalPosition(2,3), new TerminalPosition(1,5),
                new TerminalPosition(2,5), new TerminalPosition(4,7), new TerminalPosition(5,7)};

        for (int i=0;i<4;i++){
            if (i==optionMenu) {
                graphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
                graphics.putString(position[i*2],text[i*2]);
            }
            else {
                graphics.setForegroundColor(TextColor.ANSI.YELLOW);
                graphics.putString(position[i*2+1],text[i*2+1]);
            }
        }
    }

}
