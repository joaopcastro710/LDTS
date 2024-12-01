package feup.ldts.trex.view.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Layout;
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

public class MenuViewerTest {

    @Mock
    private LanternaViewer mockLanternaViewer;
    @Mock
    private Screen mockScreen;
    @Mock
    private TextGraphics mockTextGraphics;
    @Mock
    private Layout mockLayout;

    private MenuViewer menuViewer;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        when(mockLanternaViewer.setFont(anyString(), anyInt())).thenReturn(new Font("Arial", Font.PLAIN, 12));
        when(mockLanternaViewer.setScreen(any())).thenReturn(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        menuViewer = new MenuViewer();
    }

    /*@Test
    public void testInitializeMenu() throws URISyntaxException, IOException, FontFormatException {
        Screen screen = menuViewer.initializeMenu();
        assertNotNull(screen, "Ecrã deve ser inicializado.");
    }*/

    @Test
    public void testDrawMenu() throws URISyntaxException, IOException, FontFormatException {
        when(mockLayout.getElements()).thenReturn(new char[]{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'});

        menuViewer.drawMenu(mockScreen, mockLayout, 0);

        verify(mockTextGraphics, times(37)).putString(any(), anyString()); //4 drawOptions + 1 drawText + 32 drawLayout
        verify(mockScreen).refresh();
    }

    @Test
    public void testCloseMenu() throws IOException {
        menuViewer.closeMenu(mockScreen);
        verify(mockScreen).close();
    }
}