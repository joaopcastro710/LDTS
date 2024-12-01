package feup.ldts.trex.view.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.Viewer;

public class DinoViewer {

    Viewer viewer = new LanternaViewer();

    public void drawDino(TextGraphics graphics, Dino dino, String color){
        viewer.drawText(graphics, color ,new TerminalPosition(dino.getX(),dino.getY()),dino.updateDinosaurAnimation());
    }
}
