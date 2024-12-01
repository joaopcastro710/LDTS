package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.game.TwoPlayerGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class TwoPlayerControllerTest {

    private TwoPlayerGame game;
    private TwoPlayerController twoPlayerController;

    @BeforeEach
    public void setup() throws URISyntaxException, IOException, FontFormatException {
        game = new TwoPlayerGame();
        twoPlayerController = new TwoPlayerController();
        game.dino1 = new Dino(3,3);
        game.dino2 = new Dino(3, 6);
    }

    @Test
    public void testDino1Jump() {
        int prev = game.dino1.getY();
        game.dino1.setJumpState(0);
        twoPlayerController.processKey(game, new KeyStroke(KeyType.ArrowUp));
        Assertions.assertEquals(4, game.dino1.getJumpState());
        Assertions.assertEquals(game.dino1.getY(), prev-1);
    }

    @Test
    public void testDino2Jump() {
        int prev = game.dino2.getY();
        game.dino2.setJumpState(0);
        twoPlayerController.processKey(game, KeyStroke.fromString("w"));
        Assertions.assertEquals(4, game.dino2.getJumpState());
        Assertions.assertEquals(game.dino2.getY(), prev-1);
    }

    @Test
    public void testDino1SuperJump() {
        int prev = game.dino1.getY();
        game.dino1.setJumpState(0);
        twoPlayerController.processKey(game, new KeyStroke(KeyType.ArrowDown));
        Assertions.assertEquals(7, game.dino1.getJumpState());
        Assertions.assertEquals(game.dino1.getY(), prev-1);
    }

    @Test
    public void testDino2SuperJump() {
        int prev = game.dino2.getY();
        game.dino2.setJumpState(0);
        twoPlayerController.processKey(game, KeyStroke.fromString("s"));
        Assertions.assertEquals(7, game.dino2.getJumpState());
        Assertions.assertEquals(game.dino2.getY(), prev-1);
    }

    @Test
    public void testDino1PowerUsed() {
        game.dino1.setPowerUp(1);
        twoPlayerController.processKey(game, new KeyStroke(KeyType.ArrowRight));
        Assertions.assertTrue(game.dino1.getPowerLength()>0);
    }

    @Test
    public void testDino2PowerUsed() {
        game.dino2.setPowerUp(2);
        twoPlayerController.processKey(game, KeyStroke.fromString("d"));
        Assertions.assertTrue(game.dino2.getPowerLength()>0);
    }

    @Test
    public void testNoKeyPressed() {
        game.dino1.setPowerUp(1);
        game.dino1.setPowerLength(0);
        twoPlayerController.processKey(game, null);
        Assertions.assertEquals(0, game.dino1.getPowerLength(), "Nada deve acontecer se nada for premido");
        Assertions.assertEquals(1, game.dino1.getPowerUp(), "Nada deve acontecer se nada for premido");
    }
}
