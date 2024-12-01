package feup.ldts.trex.view.elements;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class DinoViewerTest {

    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Dino mockDino;
    @Mock
    private Screen mockScreen;

    private DinoViewer dinoViewer;

    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        dinoViewer = new DinoViewer();
    }

    @Test
    public void testDrawDino() {
        when(mockDino.getX()).thenReturn(10);
        when(mockDino.getY()).thenReturn(5);
        when(mockDino.updateDinosaurAnimation()).thenReturn("T");

        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        dinoViewer.drawDino(mockTextGraphics, mockDino, "#DBDF51");

        verify(mockTextGraphics).putString(new TerminalPosition(10, 5), "T");
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#DBDF51"));
    }
}