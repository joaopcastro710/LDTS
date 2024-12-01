package feup.ldts.trex.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.trex.controller.Controller;
import feup.ldts.trex.model.menu.Menu;

public class MenuController extends Controller {

    public void processKey(Menu menu, KeyStroke key) throws Exception {
        if (key!=null) {
            if (key.getKeyType() == KeyType.ArrowUp){
                menu.previousOption();
            }
            if (key.getKeyType() == KeyType.ArrowDown){
                menu.nextOption();
            }
            if (key.getKeyType() == KeyType.Enter){
                menu.chooseOption();
            }
        }
    }

}
