package feup.ldts.trex.model.game;

import feup.ldts.trex.controller.game.TwoPlayerController;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.menu.Menu;
import feup.ldts.trex.view.game.TwoPlayerViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class TwoPlayerGame extends Game{

    public Dino dino1 = new Dino(2, 3);
    public Dino dino2 = new Dino(2, 6);
    public Layout layout1 = new Layout();
    public Layout layout2 = new Layout();

    TwoPlayerViewer viewer = new TwoPlayerViewer();
    TwoPlayerController controller = new TwoPlayerController();

    GameOver gameOver = new GameOver();
    Menu menu = new Menu();

    public TwoPlayerGame() throws URISyntaxException, IOException, FontFormatException {
        screen = viewer.initializeGame();
        layout1.fillLayout(false);
        layout2.fillLayout(false);
    }
    public void runGame(boolean test) throws Exception {
        while (!processFrame(dino1, layout1) && !processFrame(dino2, layout2)){
            long startTime = System.currentTimeMillis();

            controller.processKey(this,controller.nextAction(screen));

            dino1.updateJumpState();
            dino2.updateJumpState();

            managePowerUps(dino1);
            managePowerUps(dino2);

            dino1.updatePower();
            dino2.updatePower();

            layout1.updateLayout();
            layout2.updateLayout();

            viewer.drawElements(screen, layout1, dino1, layout2, dino2);

            if (!test) if (sleepTime(startTime, frameTime())>0) Thread.sleep(sleepTime(startTime, frameTime()));
        }
        if (!test) gameOver(!processFrame(dino1, layout1), test);
    }

    public int frameTime(){
        return 90;
    }

    public void gameOver(boolean p1Won, boolean test) throws Exception {
        viewer.closeGameScreen(screen);
        gameOver.gameOverTwoPlayers(p1Won);

        while(!test) menu.runMenu();
    }

    void managePowerUps(Dino dino){
        if (dino.getPowerUp()>2){
            Random rand = new Random();
            int type = rand.nextInt(2) + 1;
            dino.setPowerUp(type);
        }
    }

}
