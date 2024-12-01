package feup.ldts.trex.controller.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import feup.ldts.trex.model.menu.Menu;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MenuControllerTest {

    @Mock
    private Menu mockMenu;

    private MenuController menuController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        menuController = new MenuController();
    }

    @Test
    public void testMenuArrowDown() throws Exception {
        Menu menu = new Menu();

        menuController.processKey(menu, new KeyStroke(KeyType.ArrowDown));
        Assertions.assertEquals(1, menu.getOptionMenu());
    }

    @Test
    public void testMenuNoKey() throws Exception {
        Menu menu = new Menu();

        menuController.processKey(menu, null);
        Assertions.assertEquals(0, menu.getOptionMenu());
    }

    @Test
    public void testMenuArrowUp() throws Exception {
        Menu menu = new Menu();

        menuController.processKey(menu, new KeyStroke(KeyType.ArrowUp));
        Assertions.assertEquals(3, menu.getOptionMenu());
    }

    @Test
    public void testMenuSelectOption() throws Exception {
        when(mockMenu.getOptionMenu()).thenReturn(0);

        menuController.processKey(mockMenu, new KeyStroke(KeyType.Enter));

        verify(mockMenu, times(1)).chooseOption();
    }
}
