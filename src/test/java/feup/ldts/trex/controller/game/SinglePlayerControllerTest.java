package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.game.SinglePlayerGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class SinglePlayerControllerTest {

    private SinglePlayerGame game;
    private SinglePlayerController singlePlayerController;

    @Mock
    private Screen mockScreen;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        game = new SinglePlayerGame();
        singlePlayerController = new SinglePlayerController();
        game.dino = new Dino(3,5);
    }

    @Test
    public void testDinoJump() {
        int prev = game.dino.getY();
        game.dino.setJumpState(0);
        singlePlayerController.processKey(game, new KeyStroke(KeyType.ArrowUp));
        Assertions.assertEquals(4, game.dino.getJumpState());
        Assertions.assertEquals(game.dino.getY(), prev-1);
    }

    @Test
    public void testDinoSuperJump() {
        int prev = game.dino.getY();
        game.dino.setJumpState(0);
        singlePlayerController.processKey(game, new KeyStroke(KeyType.ArrowDown));
        Assertions.assertEquals(7, game.dino.getJumpState());
        Assertions.assertEquals(game.dino.getY(), prev-1);
    }

    @Test
    public void testDinoPowerUsed() {
        game.dino.setPowerUp(1);
        singlePlayerController.processKey(game, new KeyStroke(KeyType.ArrowRight));
        Assertions.assertTrue(game.dino.getPowerLength()>0);
    }

    @Test
    public void testNextAction() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        KeyStroke key = singlePlayerController.nextAction(mockScreen);
        verify(mockScreen, times(1)).pollInput();
        Assertions.assertEquals(key, new KeyStroke(KeyType.ArrowRight));
    }

    @Test
    public void testNoKeyPressed() {
        game.dino.setPowerUp(1);
        game.dino.setPowerLength(0);
        singlePlayerController.processKey(game, null);
        Assertions.assertEquals(0, game.dino.getPowerLength(), "Nada deve acontecer se nada for premido");
        Assertions.assertEquals(1, game.dino.getPowerUp(), "Nada deve acontecer se nada for premido");
    }
}
