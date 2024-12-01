package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.elements.DinoViewer;
import feup.ldts.trex.view.elements.LayoutViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public abstract class GameViewer {

    LanternaViewer viewer = new LanternaViewer();
    LayoutViewer layoutViewer = new LayoutViewer();
    DinoViewer dinoViewer = new DinoViewer();

    public Screen initializeGame() throws URISyntaxException, IOException, FontFormatException {

        Font font1 = viewer.setFont("Fonts/versao7.ttf", 56);
        Font font2 = viewer.setFont("Fonts/prstart.ttf", 56);

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(font1,font2);

        Terminal terminal = viewer.setTerminal(new TerminalSize(32, 8), cfg);

        return viewer.setScreen(terminal);
    }

    public void closeGameScreen(Screen screen) throws IOException {
        viewer.closeScreen(screen);
    }

    public void drawPowerNotUsed(TextGraphics graphics, int type, int y){
        if (type==1){
            viewer.drawText(graphics,String.valueOf(TextColor.ANSI.RED_BRIGHT),new TerminalPosition(29, y),"f");
        }
        if (type==2){
            viewer.drawText(graphics,String.valueOf(TextColor.ANSI.GREEN_BRIGHT),new TerminalPosition(29, y),"[");
        }
        if (type==3){
            viewer.drawText(graphics,String.valueOf(TextColor.ANSI.YELLOW_BRIGHT),new TerminalPosition(29, y),"]");
        }
    }

    public void drawPowerUsed(TextGraphics graphics, int type, Dino dino, int y){
        if (type==1) {
            viewer.drawText(graphics, String.valueOf(TextColor.ANSI.RED_BRIGHT), new TerminalPosition(dino.getX() + 1, dino.getY()), "f");
            String bar = "";
            for (int i = 0; i < dino.getPowerLength(); i+=3) {
                bar = bar.concat("|");
            }
            viewer.drawText(graphics,"#9900EB", new TerminalPosition(dino.getX()+2, y+1), bar);
        }

        if (type==2) {
            viewer.drawBackground(graphics, String.valueOf(TextColor.ANSI.YELLOW), graphics.getSize());
        }

        if (type==3) {
            viewer.drawText(graphics,String.valueOf(TextColor.ANSI.WHITE_BRIGHT), new TerminalPosition(1, 1), "+30");
        }
    }

}
