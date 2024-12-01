package feup.ldts.trex.view.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.Viewer;

public class LayoutViewer {

    Viewer viewer = new LanternaViewer();

    public void drawLayout(TextGraphics graphics, Layout l, int y) {
        for (int i = 0; i < l.getElements().length; i++) {
            String s = String.valueOf(l.getElements()[i]);
            switch (s) {
                case "P" -> drawPterodactyl(graphics, i, y);

                case "(", ")", "&" -> drawCacti(graphics, i, s, y);

                case "*" -> drawPowerUP(graphics, i, y);

                default -> drawGround(graphics, i, y);
            }
        }
    }

    public void drawPterodactyl(TextGraphics graphics, int position, int y){
        viewer.drawText(graphics,String.valueOf(TextColor.ANSI.CYAN),new TerminalPosition(position,y-1),"P");
        drawGround(graphics, position, y);
    }

    public void drawCacti(TextGraphics graphics, int position, String type, int y){
        viewer.drawText(graphics,String.valueOf(TextColor.ANSI.GREEN_BRIGHT),new TerminalPosition(position,y),type);
    }

    public void drawPowerUP(TextGraphics graphics, int position, int y){
        viewer.drawText(graphics,"#E49300",new TerminalPosition(position,y-1),"*");
        drawGround(graphics, position, y);
    }

    public void drawGround(TextGraphics graphics, int position, int y){
        viewer.drawText(graphics,String.valueOf(TextColor.ANSI.WHITE_BRIGHT),new TerminalPosition(position,y),"_");
    }
}
