package feup.ldts.trex.model.menu;

import feup.ldts.trex.controller.menu.MenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.model.leaderboard.Leaderboard;
import feup.ldts.trex.view.menu.MenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuTest {

    @Mock
    private MenuViewer mockViewer;
    @Mock
    private MenuController mockController;
    @Mock
    Leaderboard leaderboard;

    private Menu menu;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        menu = new Menu();
        menu.menuViewer = mockViewer;
        menu.menuController = mockController;
        menu.leaderboard = leaderboard;
    }

    @Test
    public void testRunMenu() throws Exception {
        menu.runMenu();

        verify(mockViewer, times(1)).drawMenu(any(),any(), anyInt());
        verify(mockController, times(1)).processKey(menu, null);
    }

    @Test
    public void testChooseOption() throws Exception {
        menu.previousOption();
        menu.previousOption();

        menu.chooseOption();

        verify(leaderboard).showTop6(any());
    }
}
