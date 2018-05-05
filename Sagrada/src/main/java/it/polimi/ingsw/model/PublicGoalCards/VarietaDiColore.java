package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;


//revisionata by pon
//obiettivo pubblico
public class VarietaDiColore implements GoalCard {
    static int ID = 10;
    static String name = "Varietà di Colore";
    static String description = "Set di dadi di ogni colore ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int numberofRED =0;
        int numberofBLUE=0;
        int numberofGREEN = 0;
        int numberofYELLOW = 0;
        int numberofVIOLET = 0;


        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                try {
                    switch(player.getScheme().getDiceColour(row, column)){
                        case RED: numberofRED++; break;
                        case BLUE: numberofBLUE++; break;
                        case GREEN: numberofGREEN++; break;
                        case YELLOW: numberofYELLOW++; break;
                        case VIOLET: numberofVIOLET++; break;
                        default: ;
                    }

                } catch (Exception e) {
                    //no dice, no point, zorry
                }
            }
        }
        int numerodiset=numberofRED; // numerodiset indica il numero dei set presenti, che è anche uguale a min( numerodix) per x = 1,2,3,4,5,6.
        if(numberofBLUE<numerodiset){
            numerodiset = numberofBLUE;
        }
        if(numberofGREEN<numerodiset){
            numerodiset = numberofGREEN;
        }
        if(numberofYELLOW<numerodiset){
            numerodiset = numberofYELLOW;
        }
        if(numberofVIOLET<numerodiset){
            numerodiset = numberofVIOLET;
        }

        player.addPoints(numerodiset*4);
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
