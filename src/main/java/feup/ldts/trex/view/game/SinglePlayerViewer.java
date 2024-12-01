package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;

import java.io.IOException;

public class SinglePlayerViewer extends GameViewer{

    public void drawElements(Screen screen, Layout layout, Dino dino, int score) throws IOException {

        TextGraphics graphics = screen.newTextGraphics();

        viewer.drawBackground(graphics, "#012000",new TerminalSize(32,8));

        viewer.drawText(graphics,String.valueOf(TextColor.ANSI.WHITE_BRIGHT),new TerminalPosition(1, 0),String.valueOf(score));

        viewer.drawText(graphics,String.valueOf(TextColor.ANSI.BLUE_BRIGHT),new TerminalPosition(13, 0),"Lvl "+layout.getLevel());

        layoutViewer.drawLayout(graphics,layout, 6);

        dinoViewer.drawDino(graphics, dino, "#DBDF51");

        drawPowerUp(graphics, dino.getPowerUp(), dino.getPowerLength()>0, dino);

        screen.refresh();
    }

    public void drawPowerUp(TextGraphics graphics, int type, boolean used, Dino dino){
        if (!used) {
            drawPowerNotUsed(graphics, type, 0);
        }
        if (used) {
            drawPowerUsed(graphics, type, dino, 6);
        }
    }
}
