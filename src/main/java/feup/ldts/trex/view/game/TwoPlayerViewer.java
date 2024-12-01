package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;

import java.io.IOException;

public class TwoPlayerViewer extends GameViewer{

    public void drawElements(Screen screen, Layout layout1, Dino dino1, Layout layout2, Dino dino2 ) throws IOException {

        TextGraphics graphics = screen.newTextGraphics();

        viewer.drawBackground(graphics, "#012000",new TerminalSize(32,8));

        layoutViewer.drawLayout(graphics,layout1, 3);

        dinoViewer.drawDino(graphics, dino1, "#DBDF51");

        layoutViewer.drawLayout(graphics,layout2, 6);

        dinoViewer.drawDino(graphics, dino2, String.valueOf(TextColor.ANSI.CYAN_BRIGHT));

        drawPowerUp(graphics, dino1.getPowerUp(), dino1.getPowerLength()>0, dino1, dino2.getPowerUp(), dino2.getPowerLength()>0, dino2);

        screen.refresh();
    }

    public void drawPowerUp(TextGraphics graphics, int type1, boolean used1, Dino dino1, int type2, boolean used2, Dino dino2){
        if (!used1) {
            drawPowerNotUsed(graphics, type1, 0);
        }
        if (used1) {
            drawPowerUsed(graphics, type1, dino1, 3);
        }

        if (!used2) {
            drawPowerNotUsed(graphics, type2, 4);
        }
        if (used2) {
            drawPowerUsed(graphics, type2, dino2, 6);
        }
    }

}
