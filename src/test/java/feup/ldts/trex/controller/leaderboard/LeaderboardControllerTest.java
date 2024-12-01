package feup.ldts.trex.controller.leaderboard;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeaderboardControllerTest {

    @Test
    public void testProcessEscapeKey() throws Exception {
        LeaderboardController leaderboardController = new LeaderboardController();
        KeyStroke escapeKey = new KeyStroke(KeyType.Escape);
        boolean result = leaderboardController.processKey(escapeKey);
        Assertions.assertTrue(result, "O jogo deve voltar ao menu se Escape for premido");
    }

    @Test
    public void testProcessOtherKey() throws Exception {
        LeaderboardController leaderboardController = new LeaderboardController();
        KeyStroke otherKey = new KeyStroke(KeyType.Enter);
        boolean result = leaderboardController.processKey(otherKey);
        Assertions.assertFalse(result, "Nada acontece se outra tecla sem ser Escape for premida");
    }

    @Test
    public void testProcessNullKey() throws Exception {
        LeaderboardController leaderboardController = new LeaderboardController();
        KeyStroke otherKey = null;
        boolean result = leaderboardController.processKey(otherKey);
        Assertions.assertFalse(result, "Nada acontece se nada for premido");
    }
}
