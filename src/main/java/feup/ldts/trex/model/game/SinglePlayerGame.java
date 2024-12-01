package feup.ldts.trex.model.game;

import feup.ldts.trex.controller.game.SinglePlayerController;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.leaderboard.Leaderboard;
import feup.ldts.trex.model.menu.Menu;
import feup.ldts.trex.view.game.SinglePlayerViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SinglePlayerGame extends Game{

    int score = 0;
    public Dino dino = new Dino(2, 6);
    public Layout layout = new Layout();

    Leaderboard leaderboard = new Leaderboard();
    SinglePlayerViewer viewer = new SinglePlayerViewer();
    SinglePlayerController controller = new SinglePlayerController();

    GameOver gameOver = new GameOver();
    Menu menu = new Menu();

    public SinglePlayerGame() throws URISyntaxException, IOException, FontFormatException {
        screen = viewer.initializeGame();
        layout.fillLayout(false);
    }
    public void runGame(boolean test) throws Exception {
        while (!processFrame(dino, layout)){
            long startTime = System.currentTimeMillis();

            controller.processKey(this,controller.nextAction(screen));

            updateScore();

            dino.updateJumpState();

            boolean bonus = dino.updatePower();
            if (bonus) score=score+30;

            layout.updateLayout();

            viewer.drawElements(screen, layout, dino, score);

            if (!test) if (sleepTime(startTime, frameTime())>0) Thread.sleep(sleepTime(startTime, frameTime()));
        }
        if (!test) gameOver(test);
    }

    public int frameTime(){
        return 1000/(layout.getLevel()+9);
    }

    public void updateScore(){
        for (int i=0;i<layout.getElements().length;i++){
            if(i==dino.getX()){
                if (layout.getElements()[i]=='(' || layout.getElements()[i]==')') score += 4;
                else if (layout.getElements()[i]=='&') score += 6;
                else if (layout.getElements()[i]=='P') score += 2;
                else if (layout.getElements()[i]=='*' && dino.getJumpState()!=0) score += 1;
            }
        }
        score += 1;
        if (score/256 > (layout.getLevel()-1)) layout.levelUp();
    }
    public void gameOver(boolean test) throws Exception {
        viewer.closeGameScreen(screen);
        String player = gameOver.gameOver(score);

        leaderboard.addNewLeader(player, score);

        while(!test) menu.runMenu();
    }
}
