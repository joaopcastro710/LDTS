package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.controller.Controller;
import feup.ldts.trex.model.game.SinglePlayerGame;

public class SinglePlayerController extends Controller {

    public void processKey(SinglePlayerGame game, KeyStroke key){
        if (key!=null){
            if (key.getKeyType() == KeyType.ArrowUp && game.dino.getJumpState()==0){
                game.dino.setY(game.dino.getY()-1);
                game.dino.setJumpState(4);
            }
            if (key.getKeyType() == KeyType.ArrowDown && game.dino.getJumpState()==0){
                game.dino.setY(game.dino.getY()-1);
                game.dino.setJumpState(7);
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                game.dino.powerUsed();
            }
        }
    }

}
