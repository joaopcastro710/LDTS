package feup.ldts.trex.controller.leaderboard;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.controller.Controller;

public class LeaderboardController extends Controller {

    public boolean processKey(KeyStroke key) throws Exception {
        if (key!=null){
            return key.getKeyType() == KeyType.Escape;
        }
        return false;
    }

}
