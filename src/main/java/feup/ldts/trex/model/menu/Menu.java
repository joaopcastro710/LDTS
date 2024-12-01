package feup.ldts.trex.model.menu;

import feup.ldts.trex.controller.menu.MenuController;
import feup.ldts.trex.model.game.Game;
import feup.ldts.trex.model.game.TwoPlayerGame;
import feup.ldts.trex.model.leaderboard.Leaderboard;
import feup.ldts.trex.view.menu.MenuViewer;
import feup.ldts.trex.model.game.SinglePlayerGame;
import feup.ldts.trex.model.elements.Layout;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Menu {
    private int optionMenu = 0;
    private final Screen screen;
    Layout l = new Layout();
    MenuViewer menuViewer = new MenuViewer();
    MenuController menuController = new MenuController();
    Leaderboard leaderboard = new Leaderboard();

    public Menu() throws URISyntaxException, IOException, FontFormatException {
        screen = menuViewer.initializeMenu();
        l.fillLayout(true);
    }

    public void previousOption(){
        if (optionMenu>=1) optionMenu--;
        else optionMenu=3;
    }
    public void nextOption() {
        if (optionMenu<=2) optionMenu++;
        else optionMenu=0;
    }

    public void chooseOption() throws Exception {
        if (optionMenu==0){
            menuViewer.closeMenu(screen);
            Game game = new SinglePlayerGame();
            game.runGame(false);
            gameExit();
        }
        if (optionMenu==1){
            menuViewer.closeMenu(screen);
            Game game = new TwoPlayerGame();
            game.runGame(false);
            gameExit();
        }
        if (optionMenu==2){
            leaderboard.showTop6(screen);
        }
        if (optionMenu==3){
            menuViewer.closeMenu(screen);
            gameExit();
        }

    }
    public void runMenu(){
        try {

            menuViewer.drawMenu(screen, l, optionMenu);

            l.updateLayout();

            menuController.processKey(this, menuController.nextAction(screen));

            Thread.sleep(100);

        } catch (IOException | URISyntaxException | FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getOptionMenu(){
        return this.optionMenu;
    }

    public void gameExit(){
        System.exit(0);
    }
}
