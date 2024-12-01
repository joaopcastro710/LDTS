package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameOverControllerTest {

    @Test
    public void testProcessEnterKey() throws Exception {
        GameOverController gameOverController = new GameOverController();
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        String result = gameOverController.processKey(enterKey,"KKK");
        Assertions.assertEquals(result, "","O jogo deve terminar se Enter for premido enquanto a string player tem 3 caracteres");
    }

    @Test
    public void testProcessOtherKey() throws Exception {
        GameOverController gameOverController = new GameOverController();
        KeyStroke otherKey = new KeyStroke(KeyType.ArrowUp);
        String result = gameOverController.processKey(otherKey,"KKK");
        Assertions.assertEquals(result, "&", "Jogo não deve acabar caso Enter não seja premido, independentemente da string player");
    }

    @Test
    public void testProcessNoKey() throws Exception {
        GameOverController gameOverController = new GameOverController();
        String result = gameOverController.processKey(null,"KKK");
        Assertions.assertEquals(result, "&", "Jogo não deve acabar se nada for premido, independentemente da string player");
    }

    @Test
    public void testProcessBackSpace() throws Exception {
        GameOverController gameOverController = new GameOverController();
        KeyStroke backspaceKey = new KeyStroke(KeyType.Backspace);
        String result = gameOverController.processKey(backspaceKey,"KKK");
        Assertions.assertEquals(result, "?", "Backspace esvazia a string player");
    }

    @Test
    public void testProcessCharacter() throws Exception {
        GameOverController gameOverController = new GameOverController();
        KeyStroke backspaceKey = KeyStroke.fromString("w");
        String result = gameOverController.processKey(backspaceKey,"KK");
        Assertions.assertEquals(result, "W", "Letra a adicionar à string player");
    }
}
