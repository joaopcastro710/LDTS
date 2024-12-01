package feup.ldts.trex.view.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SinglePlayerViewerTest {

    @Mock
    private Screen mockScreen;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Layout mockLayout;
    @Mock
    private Dino mockDino;

    private SinglePlayerViewer singlePlayerViewer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        singlePlayerViewer = new SinglePlayerViewer();
    }

    @Test
    public void testDrawElements() throws IOException {
        char[] elements = {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};
        when(mockLayout.getElements()).thenReturn(elements);

        singlePlayerViewer.drawElements(mockScreen, mockLayout, mockDino, 100);

        verify(mockTextGraphics, atLeastOnce()).putString(any(), anyString());
        verify(mockScreen).refresh();
    }

    @Test
    public void testDrawPowerUp() {
        singlePlayerViewer.drawPowerUp(mockTextGraphics, 1, true, mockDino);
        singlePlayerViewer.drawPowerUp(mockTextGraphics, 2, false, mockDino);

        verify(mockTextGraphics).putString(any(), eq("f"));
        verify(mockTextGraphics).putString(any(), eq("["));
    }

    @Test
    public void testCloseScreen() throws IOException {
        singlePlayerViewer.closeGameScreen(mockScreen);
        verify(mockScreen).close();
    }
}