package feup.ldts.trex.model.game;

import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;
    private Dino dino;
    private Layout layout;

    @BeforeEach
    public void setup() {
        game = new Game() {};
        dino = new Dino(5, 0);
        layout = new Layout();
    }

    @Test
    public void testCollisionWithCactusOnGround() {
        layout.deleteElement(5);
        layout.getElements()[5] = '(';
        Assertions.assertTrue(game.checkCollision(dino, layout), "Existe colisão com um cato no chão.");
    }

    @Test
    public void testCollisionWithFlyingObjectWhileJumping() {
        dino.setJumpState(1);
        layout.deleteElement(5);
        layout.getElements()[5] = 'P';
        Assertions.assertTrue(game.checkCollision(dino, layout), "Existe colisão com um objeto voador.");
    }

    @Test
    public void testNoCollisionWhenJumpingOverCactus() {
        dino.setJumpState(1);
        layout.deleteElement(5);
        layout.getElements()[5] = '(';
        Assertions.assertFalse(game.checkCollision(dino, layout), "Não deve existir colisão ao saltar sobre um cato.");
    }

    @Test
    public void testPowerUpAcquisition() {
        layout.deleteElement(5);
        layout.getElements()[5] = '*';
        dino.setJumpState(1);
        game.checkCollision(dino, layout);
        Assertions.assertNotEquals(0, dino.getPowerUp(), "Dino deve adquirir um power-up.");
    }

    @Test
    public void testProcessFrame1(){
        layout.elements = new char[]{'_','_','_','_','_','&','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};

        boolean result = game.processFrame(dino, layout);

        Assertions.assertTrue(result);
    }

    @Test
    public void testProcessFrame2(){
        dino.setJumpState(5);
        layout.elements = new char[]{'_','_','_','_','_','P','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};

        boolean result = game.processFrame(dino, layout);

        Assertions.assertTrue(result);
    }

    @Test
    public void testProcessFrame3(){
        dino.setJumpState(2);
        layout.elements = new char[]{'_','_','_','_','_','(','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};

        boolean result = game.processFrame(dino, layout);

        Assertions.assertFalse(result);
    }

    @Test
    public void testProcessFrame4(){
        dino.setJumpState(2);
        dino.setPowerUp(1);
        dino.setPowerLength(10);
        layout.elements = new char[]{'_','_','_','_','_','_','P','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};

        boolean result = game.processFrame(dino, layout);

        int count=0;
        for (char c: layout.getElements()){
            if (c=='_') count++;
        }

        Assertions.assertFalse(result);
        Assertions.assertEquals(count, layout.getElements().length);
    }

    @Test
    public void testCheckPowerUp1() {
        dino.setPowerUp(1);
        layout.elements = new char[]{'_','_','_','_','_','_','&','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};

        game.checkPowerUp(dino, layout);

        Assertions.assertEquals(layout.getElements()[6], '_');
    }

    @Test
    public void testCheckPowerUp2() {
        dino.setPowerUp(2);
        char[] elements = new char[]{'_','_','&','_','_','_','_','_','P','_','(',')','_','_','_','*'};
        layout.elements = elements;

        game.checkPowerUp(dino, layout);

        int count=0;
        for (char c: layout.getElements()){
            if (c=='_') count++;
        }

        Assertions.assertEquals(count, layout.getElements().length);
    }
}

