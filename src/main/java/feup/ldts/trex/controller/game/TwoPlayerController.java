package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.controller.Controller;
import feup.ldts.trex.model.game.TwoPlayerGame;

public class TwoPlayerController extends Controller {

    public void processKey(TwoPlayerGame game, KeyStroke key){
        if (key!=null){
            if (key.getKeyType() == KeyType.ArrowUp && game.dino1.getJumpState()==0){
                game.dino1.setY(game.dino1.getY()-1);
                game.dino1.setJumpState(4);
            }
            if (key.getKeyType() == KeyType.ArrowDown && game.dino1.getJumpState()==0){
                game.dino1.setY(game.dino1.getY()-1);
                game.dino1.setJumpState(7);
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                game.dino1.powerUsed();
            }

            if (key.getKeyType() == KeyType.Character && game.dino2.getJumpState()==0){
                char c = key.getCharacter();
                if (c=='w'){
                    game.dino2.setY(game.dino2.getY()-1);
                    game.dino2.setJumpState(4);
                }
                if (c=='s'){
                    game.dino2.setY(game.dino2.getY()-1);
                    game.dino2.setJumpState(7);
                }

            }

            if (key.getKeyType() == KeyType.Character){
                char c = key.getCharacter();
                if (c=='d'){
                    game.dino2.powerUsed();
                }
            }
        }
    }

}
