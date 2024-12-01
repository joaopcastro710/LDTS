package feup.ldts.trex.model.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DinoTest {
    private Dino dino;

    @BeforeEach
    public void setup() {
        dino = new Dino(0, 0);
    }

    @Test
    public void testInitialPosition() {
        Assertions.assertEquals(0, dino.getX());
        Assertions.assertEquals(0, dino.getY());
    }

    @Test
    public void testSetY() {
        dino.setY(5);
        Assertions.assertEquals(5, dino.getY());
    }

    @Test
    public void testUpdateJumpState() {
        dino.setJumpState(3);
        dino.updateJumpState();
        Assertions.assertEquals(2, dino.getJumpState());
        dino.setJumpState(1);
        dino.updateJumpState();
        Assertions.assertEquals(0, dino.getJumpState());
        Assertions.assertEquals(1, dino.getY());
    }

    @Test
    public void testPowerUsed() {
        dino.setPowerUp(1);
        dino.powerUsed();
        Assertions.assertEquals(50, dino.getPowerLength());
        dino.setPowerUp(2);
        dino.powerUsed();
        Assertions.assertEquals(2, dino.getPowerLength());
    }

    @Test
    public void testUpdatePower() {
        dino.setPowerUp(3);
        dino.setPowerLength(1);
        boolean bonus = dino.updatePower();
        Assertions.assertTrue(bonus);
        Assertions.assertEquals(0, dino.getPowerUp());
        dino.setPowerUp(2);
        dino.setPowerLength(1);
        bonus = dino.updatePower();
        Assertions.assertFalse(bonus);
        Assertions.assertEquals(0, dino.getPowerUp());
    }

    @Test
    public void testUpdateDinosaurAnimation() {
        dino.setAnimation(1);
        String anim = dino.updateDinosaurAnimation();
        Assertions.assertEquals("t", anim);
        dino.setAnimation(-2);
        anim = dino.updateDinosaurAnimation();
        Assertions.assertEquals("T", anim);
    }
}