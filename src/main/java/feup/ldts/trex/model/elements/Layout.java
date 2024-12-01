package feup.ldts.trex.model.elements;

import java.util.Random;

public class Layout {
    public char[] elements;
    private char[] oddsElements;

    int level;

    public Layout(){
        elements = new char[]{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
        oddsElements = new char[]{'(','(',')',')','&','(','(',')',')','&','P','P','P','P','*','*','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};
        level = 1;
    }

    public char[] getElements(){
        return this.elements;
    }

    public int getLevel(){
        return this.level;
    }

    public char[] getOddsElements() {
        return this.oddsElements;
    }

    public void setOddsElements(char[] oddsElements) {
        this.oddsElements = oddsElements;
    }

    public void deleteElement(int x){ elements[x] = '_';}

    public void levelUp(){
        if (level==1) setOddsElements(new char[]{'(','(',')',')','&','(','(',')',')','&','P','P','P','P','*','*','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'});
        if (level==2) setOddsElements(new char[]{'(','(',')',')','&','(','(',')',')','&','P','P','P','P','*','*','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'});
        if (level==3) setOddsElements(new char[]{'(','(',')',')','&','(','(',')',')','&','P','P','P','P','*','*','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'});
        if (level==4) setOddsElements(new char[]{'(','(',')',')','&','(','(',')',')','&','P','P','P','P','P','P','*','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'});
        level++;
    }

    public void fillLayout(boolean menu){
        if (!menu) {
            new Layout();
        }
        else{
            for (int i=0;i<elements.length;i++){
                Random rand = new Random();
                int rng = rand.nextInt(oddsElements.length);
                elements[i] = oddsElements[rng];
            }
        }
        //Mundo inicial, vazio no jogo, aleatório no menu
    }

    public void updateLayout(){
        nextCell();
        generateCell();
    }

    public void nextCell(){
        for (int i=1;i<elements.length;i++){
            elements[i-1] = elements[i];
        } //substituição de valores no vetor exceto última célula
    }

    public void generateCell(){
        if ( ((elements[elements.length-2]=='(' || elements[elements.length-2]==')' || elements[elements.length-2]=='&') &&  (elements[elements.length-3]=='(' || elements[elements.length-3]==')' || elements[elements.length-3]=='&')) || (elements[elements.length-2]=='P' || elements[elements.length-2]=='*')) {
            Random rand = new Random();
            int cacticount = 0;
            for (char oddsElement : oddsElements) {
                if (oddsElement == '(' || oddsElement == ')' || oddsElement == '&') cacticount++;
            }
            int rng = rand.nextInt(oddsElements.length-cacticount)+cacticount;
            while (oddsElements[rng]=='P' || oddsElements[rng]=='*'){
                rng = rand.nextInt(oddsElements.length-cacticount)+cacticount;
            } //evita geração de elementos voadores após dois catos seguidos
            elements[elements.length - 1] = oddsElements[rng];
        } //evita ter 3 catos seguidos ou catos após elementos voadores.

        else if ((elements[elements.length-2]=='(' || elements[elements.length-2]==')' || elements[elements.length-2]=='&') || (elements[elements.length-3]=='(' || elements[elements.length-3]==')' || elements[elements.length-3]=='&')){
            Random rand = new Random();
            int rng = rand.nextInt(oddsElements.length);
            while (oddsElements[rng]=='P' || oddsElements[rng]=='*'){
                rng = rand.nextInt(oddsElements.length);
            }
            elements[elements.length - 1] = oddsElements[rng];
        } //evita geração de elementos voadores imediatamente após ou duas células após um cato

        else if (elements[elements.length/2]!=' ') {
            Random rand = new Random();
            int rng = rand.nextInt(oddsElements.length);
            elements[elements.length - 1] = oddsElements[rng];
        } //gerador de obstáculos

        else{
            elements[elements.length - 1] = '_' ;
        } //só gera obstáculos depois de ser percorrido metade do ecrã no início do jogo
    }
}
