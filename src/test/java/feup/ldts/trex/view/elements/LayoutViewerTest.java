package feup.ldts.trex.view.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import feup.ldts.trex.model.elements.Layout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LayoutViewerTest {

    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Layout mockLayout;

    private LayoutViewer layoutViewer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        layoutViewer = new LayoutViewer();
    }

    @Test
    public void testDrawLayout() {
        char[] elements = {'P', '(', ')', '*', '_', ' '};
        when(mockLayout.getElements()).thenReturn(elements);

        layoutViewer.drawLayout(mockTextGraphics, mockLayout, 5);

        verify(mockTextGraphics).putString(new TerminalPosition(0,4),"P");
        verify(mockTextGraphics).putString(new TerminalPosition(1,5),"(");
        verify(mockTextGraphics).putString(new TerminalPosition(2,5),")");
        verify(mockTextGraphics).putString(new TerminalPosition(3,4),"*");

        verify(mockTextGraphics, times(8)).putString(any(), anyString());//8 elementos são imprimidos já que ao desenhar 'P' e '*', o programa desenha também '_' na linha de baixo
    }
}
