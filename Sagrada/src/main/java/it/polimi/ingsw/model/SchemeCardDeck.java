package it.polimi.ingsw.model;

import java.util.Random;

public class SchemeCardDeck {
    String mapName1, mapName2;
    int diff1=0, diff2=0, i=0;

    public SchemeCardDeck card = getCard();


    public SchemeCardDeck getCard() {
        Random random = new Random();
        int i = 0;
        i = random.nextInt(SchemeCard.getNumMaps() / 2)*2; // i give to my function only pair position 0,2,4... position of master card in the txt file. I need the n'th map
        this.mapName1 = SchemeCard.getName(i);
        this.diff1 = SchemeCard.getDifficulty(i);
        this.matrix1 = SchemeCard.getMatrix(i);
        this.mapName2 = SchemeCard.getName(i + 1);   //i+1 is the twin map
        this.diff2 = SchemeCard.getDifficulty(i + 1);
        this.matrix2 = SchemeCard.getMatrix(i+1);
        return card;
    }

}
