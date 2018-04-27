package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class VarietaDiColore implements GoalCard {
    static int ID = 10;
    static String name = "Varietà di Colore";
    static String description = "Set di dadi di ogni colore ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int[] counter;   // array che conta le occorrenze
        counter = new int[5];
        int nRow=0;
        int min=4; // inizializzo a 4 ma massima possibile combinazione è 3 da 1 a 6

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                if(player.getScheme().getDiceColour(row, column)== DiceColor.RED){
                    counter[0]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.BLUE){
                    counter[1]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.VIOLET){
                    counter[2]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.YELLOW){
                    counter[3]++;
                }
                else if(player.getScheme().getDiceColour(row, column)== DiceColor.GREEN){
                    counter[4]++;
                }
            }
        }

        for(int i=0; i<5; i++){ // verifico numero massimo di set presenti
            if(counter[i]<min){
                min=counter[i];
            }
        }
        player.addPoints(min*4);
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
}
