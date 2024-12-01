package feup.ldts.trex.model.game;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.model.elements.Dino;
import feup.ldts.trex.model.elements.Layout;

import java.util.Random;

public abstract class Game {

    public Screen screen;

    public void runGame(boolean test) throws Exception {};

    public boolean processFrame(Dino dino, Layout layout) {
        if (dino.getPowerUp()!=0 && dino.getPowerLength()>0) checkPowerUp(dino, layout);
        return checkCollision(dino, layout);
    }
    public void checkPowerUp(Dino dino, Layout layout){
        if (dino.getPowerUp()==1) {
            if ((layout.getElements()[dino.getX() + 1] == '(' || layout.getElements()[dino.getX() + 1] == ')' || layout.getElements()[dino.getX() + 1] == '&') && dino.getJumpState() == 0) {
                layout.deleteElement(dino.getX() + 1); //elementos terrestres
            }
            if ((layout.getElements()[dino.getX()+1] == 'P' || layout.getElements()[dino.getX()+1] == '*') && dino.getJumpState() != 0){
                layout.deleteElement(dino.getX() + 1); //elementos aéreos
            }
        }
        if (dino.getPowerUp()==2) {
            for (int i=0;i<layout.getElements().length;i++){
                layout.deleteElement(i);
            }
        }
    }

    public boolean checkCollision(Dino dino, Layout layout) {
        if ((layout.getElements()[dino.getX()] == '(' || layout.getElements()[dino.getX()] == ')' || layout.getElements()[dino.getX()] == '&') && dino.getJumpState() == 0) {
            return true;
        }
        if (layout.getElements()[dino.getX()] == 'P' && dino.getJumpState() != 0) {
            return true;
        }
        if (layout.getElements()[dino.getX()] == '*' && dino.getJumpState() != 0) {
            generatePowerUp(dino, layout);
        }
        return false;
    }

    public void generatePowerUp(Dino dino, Layout layout){
        Random rand = new Random();
        int type = rand.nextInt(3) + 1;
        dino.setPowerUp(type);
        layout.deleteElement(dino.getX());
    }

    public long sleepTime(long startTime, long frameTime) {
        return frameTime - (System.currentTimeMillis() - startTime);
    }
}
