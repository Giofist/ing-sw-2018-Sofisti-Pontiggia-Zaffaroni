package it.polimi.ingsw.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class SchemeCardDeck {
    String mapName1, mapName2;
    int diff1=0, diff2=0, i=0;
    Tile matrix1[5][4];
    Tile matrix2[5][4];

    public SchemeCardDeck card = getCard();


    public SchemeCardDeck getCard() {
        Random random = new Random();
        int i = 0;
        i = random.nextInt(SchemeCard.getNumMaps() / 2);
        this.mapName1 = SchemeCard.getName(i);
        this.diff1 = SchemeCard.getDifficulty(i);
        this.matrix1 = SchemeCard.getMatrix(i);
        this.mapName2 = SchemeCard.getName(i + 1);
        this.diff2 = SchemeCard.getDifficulty(i + 1);
        this.matrix2 = SchemeCard.getMatrix(i+1);
        return card;
    }

}
