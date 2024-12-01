package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import feup.ldts.trex.model.elements.Dino;
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

public class GameViewerTest {

    @Mock
    private LanternaViewer mockLanternaViewer;
    @Mock
    private Screen mockScreen;
    @Mock
    private Terminal mockTerminal;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Dino mockDino;

    private GameViewer gameViewer;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        when(mockLanternaViewer.setFont(anyString(), anyInt())).thenReturn(new Font("Arial", Font.PLAIN, 12));
        when(mockLanternaViewer.setTerminal(any(), any())).thenReturn(mockTerminal);
        when(mockLanternaViewer.setScreen(mockTerminal)).thenReturn(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        gameViewer = new GameViewer() {};
    }

    @Test
    public void testInitializeGame() throws URISyntaxException, IOException, FontFormatException {
        Screen screen = gameViewer.initializeGame();
        assertNotNull(screen, "Ecrã deve ser inicializado.");
    }

    @Test
    public void drawPowerNotUsed(){
        gameViewer.drawPowerNotUsed(mockTextGraphics, 3, 2);

        verify(mockTextGraphics).putString(new TerminalPosition(29,2), "]");
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(String.valueOf(TextColor.ANSI.YELLOW_BRIGHT)));

        gameViewer.drawPowerNotUsed(mockTextGraphics, 1, 2);

        verify(mockTextGraphics).putString(new TerminalPosition(29,2), "f");
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(String.valueOf(TextColor.ANSI.RED_BRIGHT)));
    }

    @Test
    public void drawPowerUsed(){
        when(mockDino.getX()).thenReturn(10);
        when(mockDino.getY()).thenReturn(5);
        when(mockDino.getPowerLength()).thenReturn(10);

        gameViewer.drawPowerUsed(mockTextGraphics, 1, mockDino, 2);

        verify(mockTextGraphics).putString(new TerminalPosition(11,5), "f");
        verify(mockTextGraphics).putString(new TerminalPosition(12,3), "||||");
    }
}
