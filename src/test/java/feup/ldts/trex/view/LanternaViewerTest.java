package feup.ldts.trex.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LanternaViewerTest {

    @Mock
    private Screen mockScreen;
    @Mock
    private Terminal mockTerminal;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private TerminalSize mockTerminalSize;
    @Mock
    private AWTTerminalFontConfiguration mockTfc;

    private LanternaViewer lanternaViewer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        lanternaViewer = new LanternaViewer();
    }

    @Test
    public void testSetTerminal() throws IOException {
        Terminal terminal = lanternaViewer.setTerminal(mockTerminalSize, mockTfc);
        assertNotNull(terminal, "O terminal não deve ser nulo");
    }

    @Test
    public void testSetFont() throws URISyntaxException, IOException, FontFormatException {
        Font font = lanternaViewer.setFont("Fonts/prstart.ttf", 72);
        assertEquals(font.getSize(), 72);
        assertEquals(font.getFontName(), "Press Start Regular");

        font = lanternaViewer.setFont("t.ttf",72);
        assertNull(font);
    }

    @Test
    public void testSetScreen() throws IOException {
        when(mockTerminal.getTerminalSize()).thenReturn(new TerminalSize(32,8));

        Screen screen = lanternaViewer.setScreen(mockTerminal);

        assertNotNull(screen, "O ecrã não deve ser nulo");
        assertEquals(screen.getTerminalSize().getColumns(), 32);
        assertEquals(screen.getTerminalSize().getRows(), 8);
    }

    @Test
    public void testDrawBackground() {
        lanternaViewer.drawBackground(mockTextGraphics, "#012000", new TerminalSize(10, 10));
        verify(mockTextGraphics).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), anyChar());
    }

    @Test
    public void testDrawText() {
        lanternaViewer.drawText(mockTextGraphics, "#FFFFFF", new TerminalPosition(1, 1), "Test");
        verify(mockTextGraphics).putString(any(TerminalPosition.class), eq("Test"));
    }

    @Test
    public void testScreenRefresh() throws IOException {
        lanternaViewer.screenRefresh(mockScreen);
        verify(mockScreen).refresh();
    }

    @Test
    public void testCloseScreen() throws IOException {
        lanternaViewer.closeScreen(mockScreen);
        verify(mockScreen).close();
    }
}
