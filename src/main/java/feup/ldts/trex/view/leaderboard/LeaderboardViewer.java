package feup.ldts.trex.view.leaderboard;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.view.LanternaViewer;
import feup.ldts.trex.view.Viewer;

import java.io.IOException;
import java.util.List;

public class LeaderboardViewer {

    Viewer viewer = new LanternaViewer();

    public void displayLeaderboard(Screen screen, List<String> top6) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();

        String[] colors= new String[]{String.valueOf(TextColor.ANSI.YELLOW_BRIGHT), String.valueOf(TextColor.ANSI.BLACK_BRIGHT), String.valueOf(TextColor.ANSI.YELLOW),
                String.valueOf(TextColor.ANSI.GREEN_BRIGHT), String.valueOf(TextColor.ANSI.GREEN_BRIGHT), String.valueOf(TextColor.ANSI.GREEN_BRIGHT)};

        int[] posX = new int[]{4,3,2,1,1,1};

        viewer.drawBackground(graphics, "#012000",screen.getTerminalSize());

        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.YELLOW), new TerminalPosition(3,0), "Leaderboard");

        for (int i =0; i<top6.size(); i++){
            viewer.drawText(graphics, colors[i], new TerminalPosition(posX[i],2+i), i+1+".");
            viewer.drawText(graphics, String.valueOf(TextColor.ANSI.WHITE_BRIGHT), new TerminalPosition(posX[i]+2,2+i), top6.get(i));
        }

        viewer.drawText(graphics, String.valueOf(TextColor.ANSI.GREEN_BRIGHT), new TerminalPosition(1, 9),"press <escape>");

        viewer.screenRefresh(screen);
    }

}
