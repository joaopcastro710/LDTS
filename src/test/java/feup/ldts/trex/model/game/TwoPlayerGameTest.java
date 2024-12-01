package feup.ldts.trex.model.game;

import feup.ldts.trex.controller.game.TwoPlayerController;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.game.TwoPlayerViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TwoPlayerGameTest {

    private TwoPlayerGame twoPlayerGame;

    @Mock
    private TwoPlayerViewer mockViewer;
    @Mock
    private TwoPlayerController mockController;

    @Mock
    private GameOver mockGameOver;
    @Mock
    private Menu mockMenu;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        twoPlayerGame = new TwoPlayerGame();
        twoPlayerGame.controller = mockController;
        twoPlayerGame.viewer = mockViewer;
        twoPlayerGame.menu = mockMenu;
        twoPlayerGame.gameOver = mockGameOver;
    }

    @Test
    public void testFrameTime() {
        int frameTime = twoPlayerGame.frameTime();
        Assertions.assertEquals(frameTime, 90);
    }

    @Test
    public void testManagePowerUps() {
        Dino dino = new Dino(3,5);
        dino.setPowerUp(3);

        twoPlayerGame.managePowerUps(dino);

        Assertions.assertTrue(dino.getPowerUp()<3, "Se o power não for usável neste modo de jogo é trocado");
    }

    @Test
    public void testRunGame() throws Exception {
        twoPlayerGame.dino1 = new Dino(1,6);
        twoPlayerGame.layout1 = new Layout();
        twoPlayerGame.layout1.elements = new char[]{'_','_','&'};
        twoPlayerGame.dino2 = new Dino(0,6);
        twoPlayerGame.layout2 = new Layout();
        twoPlayerGame.layout2.elements = new char[]{'_','_','&'};
        when(mockController.nextAction(any())).thenReturn(null);

        twoPlayerGame.runGame(true);

        verify(mockViewer, times(1)).drawElements(twoPlayerGame.screen, twoPlayerGame.layout1, twoPlayerGame.dino1, twoPlayerGame.layout2, twoPlayerGame.dino2);
        verify(mockController, times(1)).processKey(twoPlayerGame, null);
        Assertions.assertTrue(twoPlayerGame.processFrame(twoPlayerGame.dino1, twoPlayerGame.layout1), "Dino1 colidiu com um objeto");
    }

    @Test
    public void testGameOver() throws Exception {

        twoPlayerGame.gameOver(true, true);

        verify(mockViewer, times(1)).closeGameScreen(any());
        verify(mockGameOver, times(1)).gameOverTwoPlayers(true);
    }
}
