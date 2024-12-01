package feup.ldts.trex.view.game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TwoPlayerViewerTest {

    @Mock
    private Screen mockScreen;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Layout mockLayout1;
    @Mock
    private Dino mockDino1;
    @Mock
    private Layout mockLayout2;
    @Mock
    private Dino mockDino2;

    private TwoPlayerViewer twoPlayerViewer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        twoPlayerViewer = new TwoPlayerViewer();
    }

    @Test
    public void testDrawElements() throws IOException {
        char[] elements = {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'};
        when(mockLayout1.getElements()).thenReturn(elements);
        when(mockLayout2.getElements()).thenReturn(elements);

        twoPlayerViewer.drawElements(mockScreen, mockLayout1, mockDino1, mockLayout2, mockDino2);

        verify(mockTextGraphics, times(58)).putString(any(), any()); //28 de cada layout + 1 de cada Dino
        verify(mockScreen).refresh();
    }

    @Test
    public void testDrawPowerUp(){
        when(mockDino1.getX()).thenReturn(2);
        when(mockDino2.getX()).thenReturn(2);

        twoPlayerViewer.drawPowerUp(mockTextGraphics, 1, true, mockDino1, 2, true, mockDino2);

        verify(mockTextGraphics).putString(any(), eq("f")); //power 1
        verify(mockTextGraphics).setBackgroundColor(TextColor.ANSI.YELLOW); //power 2, granada
    }
}
