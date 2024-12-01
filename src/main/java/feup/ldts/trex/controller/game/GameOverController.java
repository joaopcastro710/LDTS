package feup.ldts.trex.controller.game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.controller.Controller;

public class GameOverController extends Controller {

    public String processKey(KeyStroke key, String player) throws Exception {

        if (key!=null){
            if (key.getKeyType() == KeyType.Enter) return "";
            else if (key.getKeyType() == KeyType.Character && player.length()<3) return key.getCharacter().toString().toUpperCase();
            else if (key.getKeyType() == KeyType.Backspace && !player.isEmpty()) return "?";
        }
        return "&";
    }

}
