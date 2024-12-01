package feup.ldts.trex.view.leaderboard;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.LanternaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class LeaderboardViewerTest {

    @Mock
    private Screen mockScreen;
    @Mock
    private LanternaViewer mockLanternaViewer;
    @Mock
    private TextGraphics mockTextGraphics;

    private LeaderboardViewer leaderboardViewer;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        MockitoAnnotations.openMocks(this);
        when(mockLanternaViewer.setFont(anyString(), anyInt())).thenReturn(new Font("Arial", Font.PLAIN, 12));
        when(mockLanternaViewer.setScreen(any())).thenReturn(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        leaderboardViewer = new LeaderboardViewer();
    }

    @Test
    public void testDisplayLeaderboard() throws IOException {
        List<String> allLines = List.of(new String[]{"ABC 1000","DEF 800","GHI 600","JKL 400","MNO 300","PQR 250"});

        leaderboardViewer.displayLeaderboard(mockScreen, allLines);

        verify(mockTextGraphics, times(14)).putString(any(), any());
        verify(mockTextGraphics, times(1)).putString(new TerminalPosition(6,2), "ABC 1000");
        verify(mockTextGraphics, times(1)).putString(new TerminalPosition(3,7), "PQR 250");
        verify(mockScreen).refresh();
    }
}
