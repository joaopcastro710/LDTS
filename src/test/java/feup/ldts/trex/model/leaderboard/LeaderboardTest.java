package feup.ldts.trex.model.leaderboard;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.controller.leaderboard.LeaderboardController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import feup.ldts.trex.view.leaderboard.LeaderboardViewer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LeaderboardTest {

    @Mock
    private LeaderboardViewer mockViewer;
    @Mock
    private LeaderboardController mockController;
    @Mock
    private Screen mockScreen;

    private final String file = "src/main/resources/Leaderboard/scoreLeaders.txt";

    private List<String> allLines;
    private BufferedReader reader;
    private Leaderboard leaderboard;

    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.openMocks(this);

        allLines = new ArrayList<>(Collections.emptyList());
        reader = new BufferedReader(new FileReader(file));
        int i=0;
        String line=reader.readLine();
        while (line!=null){
            allLines.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        leaderboard = new Leaderboard();
        leaderboard.controller = mockController;
        leaderboard.viewer = mockViewer;
    }

    @Test
    public void testAddNewLeader() throws IOException {
        int originalSize = allLines.size();

        leaderboard.addNewLeader("LEA", -1);

        allLines = new ArrayList<>(Collections.emptyList());
        reader = new BufferedReader(new FileReader(file));
        int i=0;
        String line=reader.readLine();
        while (line!=null){
            allLines.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        assertEquals(originalSize+1, allLines.size());

        BufferedWriter writer = new BufferedWriter(new FileWriter(file)); //restaura a leaderboard original
        for (i=0;i<allLines.size()-1;i++){
            writer.write(allLines.get(i)+"\n");
        }
        writer.close();
    }

    @Test
    public void testShowTop6() throws Exception {
        when(mockController.processKey(any())).thenReturn(false, false, true);

        leaderboard.showTop6(mockScreen);

        verify(mockViewer, times(4)).displayLeaderboard(any(), any());
    }
}
