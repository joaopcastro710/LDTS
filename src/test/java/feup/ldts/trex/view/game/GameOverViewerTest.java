package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.LanternaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GameOverViewerTest {

    @Mock
    private LanternaViewer mockLanternaViewer;
    @Mock
    private Screen mockScreen;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Terminal mockTerminal;

    private GameOverViewer gameOverViewer;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        when(mockLanternaViewer.setFont(anyString(), anyInt())).thenReturn(new Font("Arial", Font.PLAIN, 12));
        when(mockLanternaViewer.setTerminal(any(), any())).thenReturn(mockTerminal);
        when(mockLanternaViewer.setScreen(mockTerminal)).thenReturn(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        gameOverViewer = new GameOverViewer();
    }

    @Test
    public void testInitializeGameOverScreen() throws URISyntaxException, IOException, FontFormatException {
        Screen screen = gameOverViewer.initializeGameOverScreen();
        assertNotNull(screen, "Ecrã deve ser inicializado.");
    }

    @Test
    public void testDrawGameOverScreen() throws IOException {
        gameOverViewer.drawGameOverScreen(mockScreen, 100, "KKK");
        verify(mockTextGraphics, times(1)).putString(new TerminalPosition(2,5), "Name: KKK");
        verify(mockTextGraphics, times(1)).putString(new TerminalPosition(1,3), "Score: 100");
        verify(mockScreen).refresh();
    }

    @Test
    public void testDrawGameOverScreenTwoPlayers() throws IOException {
        gameOverViewer.drawGameOverScreenTwoPlayers(mockScreen, true);
        verify(mockTextGraphics, times(1)).putString(new TerminalPosition(1,4), "Winner: P1");
        verify(mockScreen).refresh();
    }
}
