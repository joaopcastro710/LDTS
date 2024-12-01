package feup.ldts.trex.model.game;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.controller.game.GameOverController;
import feup.ldts.trex.view.game.GameOverViewer;

import java.util.Objects;

public class GameOver {

    GameOverViewer viewer = new GameOverViewer();
    GameOverController controller = new GameOverController();

    public String gameOver(int score) throws Exception {
        Screen screen = viewer.initializeGameOverScreen();

        String player="";

        viewer.drawGameOverScreen(screen, score, player);
        String next = "";
        while(player.length() < 3 || !next.isEmpty()){
            viewer.drawGameOverScreen(screen, score, player);
            next = controller.processKey(controller.nextAction(screen),player);
            if (!Objects.equals(next, "&") && !Objects.equals(next,"?")) player = player.concat(next);
            if (Objects.equals(next,"?")) player = "";
        }

        screen.close();
        return player.substring(0,3);
    }

    public void gameOverTwoPlayers(boolean p1Won) throws Exception {
        Screen screen = viewer.initializeGameOverScreen();

        String player="";

        viewer.drawGameOverScreenTwoPlayers(screen, p1Won);
        String next = " ";
        while(!next.isEmpty()){
            viewer.drawGameOverScreenTwoPlayers(screen, p1Won);
            next = controller.processKey(controller.nextAction(screen),player);
        }

        screen.close();
    }

}
