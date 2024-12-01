package feup.ldts.trex.model.game;

import feup.ldts.trex.controller.game.SinglePlayerController;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;
import feup.ldts.trex.model.leaderboard.Leaderboard;
import feup.ldts.trex.model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.game.SinglePlayerViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class SinglePlayerGameTest {

    @Mock
    private Dino mockDino;
    @Mock
    private Layout mockLayout;
    @Mock
    private SinglePlayerController mockController;
    @Mock
    private SinglePlayerViewer mockViewer;

    @Mock
    private Menu mockMenu;
    @Mock
    private GameOver mockGameOver;
    @Mock
    private Leaderboard mockLeaderboard;

    private SinglePlayerGame singlePlayerGame;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        singlePlayerGame = new SinglePlayerGame();
        singlePlayerGame.dino = mockDino;
        singlePlayerGame.layout = mockLayout;
        singlePlayerGame.controller = mockController;
        singlePlayerGame.viewer = mockViewer;
        singlePlayerGame.menu = mockMenu;
        singlePlayerGame.gameOver = mockGameOver;
        singlePlayerGame.leaderboard = mockLeaderboard;
    }

    @Test
    public void testUpdateScore(){
        singlePlayerGame.score = 252;

        char[] elements = new char[]{'_','_','&','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};
        when(mockLayout.getElements()).thenReturn(elements);
        when(mockDino.getX()).thenReturn(2);
        when(mockDino.getJumpState()).thenReturn(1);
        when(mockLayout.getLevel()).thenReturn(1);
        singlePlayerGame.updateScore();

        Assertions.assertEquals(singlePlayerGame.score, 259);
        verify(mockLayout, times(1)).levelUp();
    }

    @Test
    public void testFrameTime() {
        when(mockLayout.getLevel()).thenReturn(1);
        int frameTime = singlePlayerGame.frameTime();
        Assertions.assertEquals(frameTime, 100);
    }

    @Test
    public void testRunGame() throws Exception {
        singlePlayerGame.dino = new Dino(1,6);
        singlePlayerGame.layout = new Layout();
        singlePlayerGame.layout.elements = new char[]{'_','_','&'};
        when(mockController.nextAction(any())).thenReturn(null);

        singlePlayerGame.runGame(true);

        verify(mockViewer, times(1)).drawElements(singlePlayerGame.screen, singlePlayerGame.layout, singlePlayerGame.dino, singlePlayerGame.score);
        verify(mockController, times(1)).processKey(singlePlayerGame, null);
    }

    @Test
    public void testGameOver() throws Exception {
        when(mockGameOver.gameOver(singlePlayerGame.score)).thenReturn("AZA");

        singlePlayerGame.gameOver(true);

        verify(mockViewer, times(1)).closeGameScreen(any());
        verify(mockGameOver, times(1)).gameOver(anyInt());
        verify(mockLeaderboard, times(1)).addNewLeader("AZA", 0);
    }
}

