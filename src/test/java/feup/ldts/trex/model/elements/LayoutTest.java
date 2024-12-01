package feup.ldts.trex.model.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayoutTest {

    private Layout layout;

    @BeforeEach
    public void setup() {
        layout = new Layout();
    }

    @Test
    public void testInitialLayout() {
        char[] elements = layout.getElements();
        for (char element : elements) {
            Assertions.assertEquals(' ', element, "Layout deve estar totalmente vazio no início do jogo.");
        }
    }

    @Test
    public void testLevelUp() {
        layout.levelUp();
        Assertions.assertEquals(2, layout.getLevel(), "Nível do layout deve aumentar");
        Assertions.assertEquals(135, layout.getOddsElements().length);
    }

    @Test
    public void testDeleteElement() {
        layout.deleteElement(5);
        Assertions.assertEquals('_', layout.getElements()[5], "Elemento no index 5 deve ser substituído por '_'");
    }

    @Test
    public void testFillLayout() {
        layout.fillLayout(false);
        char[] elements = layout.getElements();
        for (char element : elements) {
            Assertions.assertEquals(' ', element, "layout deve estar totalmente vazio no início do jogo.");
        }

        layout.fillLayout(true);
        boolean isRandom = false;
        char[] initialElements = layout.getElements();
        for (char element : initialElements) {
            if (element != ' ') {
                isRandom = true;
                break;
            }
        }
        Assertions.assertTrue(isRandom, "Layout deve ter elementos aleatórios quando está no menu.");
    }

    @Test
    public void testUpdateLayout() {
        layout.elements = new char[]{'_','_','&','_'};
        char[] initialElements = layout.getElements().clone();
        layout.updateLayout();
        char[] updatedElements = layout.getElements();
        for (int i = 0; i < updatedElements.length - 1; i++) {
            Assertions.assertEquals(initialElements[i + 1], updatedElements[i], "Elements should shift left after updateLayout.");
        }
        Assertions.assertNotEquals(updatedElements[3], 'P', "Elementos voadores só podem ser gerados três células após um cato");
        Assertions.assertNotEquals(updatedElements[3], '*', "Elementos voadores só podem ser gerados três células após um cato");
    }

    @Test
    public void testUpdateLayoutWithTwoCacti() {
        layout.elements = new char[]{'_','_','&','('};
        layout.updateLayout();
        char[] updatedElements = layout.getElements();
        Assertions.assertNotEquals(updatedElements[3], '(', "Depois de dois catos consecutivos não deve ser gerado outro");
        Assertions.assertNotEquals(updatedElements[3], ')', "Depois de dois catos consecutivos não deve ser gerado outro");
        Assertions.assertNotEquals(updatedElements[3], '&', "Depois de dois catos consecutivos não deve ser gerado outro");
    }

    @Test
    public void testUpdateLayoutNormalCondition() {
        layout.elements = new char[]{'_','_','_','_'};
        layout.updateLayout();
        char[] updatedElements = layout.getElements();
        char[] possibleElements = new char[]{'_','P','*','(',')','&'};
        boolean flag = false;
        for (char possibleElement : possibleElements) {
            if (updatedElements[updatedElements.length - 1] == possibleElement) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag, "Qualquer elemento pode ser gerado");
    }

    @Test
    public void testUpdateLayoutWithEmptyLayout() {
        layout.elements = new char[]{' ',' ',' ',' '};
        layout.updateLayout();
        char[] updatedElements = layout.getElements();
        Assertions.assertEquals(updatedElements[3], '_', "Quando o layout está vazio no início do jogo não devem ser gerados quaisquer elementos");
    }
}
