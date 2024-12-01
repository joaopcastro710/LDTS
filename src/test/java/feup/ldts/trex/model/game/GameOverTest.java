package feup.ldts.trex.model.game;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.controller.game.GameOverController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.game.GameOverViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameOverTest {

    @Mock
    private GameOverViewer mockViewer;

    @Mock
    private GameOverController mockController;
    @Mock
    private Screen mockScreen;

    private GameOver gameOver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gameOver = new GameOver();
        gameOver.viewer = mockViewer;
        gameOver.controller = mockController;
    }

    @Test
    public void testGameOver() throws Exception {
        when(mockViewer.initializeGameOverScreen()).thenReturn(mockScreen);
        when(mockController.processKey(any(), any())).thenReturn("K","F","C","");

        String player = gameOver.gameOver(100);

        assertEquals(player,"KFC");
        verify(mockViewer, atLeastOnce()).drawGameOverScreen(mockScreen, 100, "KFC");
        verify(mockScreen, times(1)).close();
    }

    @Test
    public void testGameOverTwoPlayers() throws Exception {
        when(mockViewer.initializeGameOverScreen()).thenReturn(mockScreen);
        when(mockController.processKey(any(), any())).thenReturn("");

        gameOver.gameOverTwoPlayers(true);

        verify(mockViewer, atLeastOnce()).drawGameOverScreenTwoPlayers(mockScreen,  true);
        verify(mockScreen, times(1)).close();
    }
}
