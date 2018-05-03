package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//revisionata by pon
//obiettivo pubblico
public class SfumatureDiverse implements GoalCard {
    static int ID = 8;
    static String name = "Sfumature Diverse";
    static String description = "Set di dadi di ogni valore ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int numerodi1=0;
        int numerodi2=0;
        int numerodi3 = 0;
        int numerodi4 = 0;
        int numerodi5 = 0;
        int numerodi6 = 0;



        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                try {
                    switch(player.getScheme().getDiceIntensity(row, column)){
                        case 1:  numerodi1++; break;
                        case 2: numerodi2++; break;
                        case 3: numerodi2++; break;
                        case 4: numerodi2++; break;
                        case 5: numerodi2++; break;
                        case 6: numerodi2++; break;
                        default: ;
                    }

                } catch (DiceNotExistantException e) {
                    //no dice, no point, zorry
                }
            }
        }
        int numerodiset=numerodi1; // numerodiset indica il numero dei set presenti, che è anche uguale a min( numerodix) per x = 1,2,3,4,5,6.
        if(numerodi2<numerodiset){
            numerodiset = numerodi2;
        }
        if(numerodi2<numerodiset){
            numerodiset = numerodi2;
        }
        if(numerodi3<numerodiset){
            numerodiset = numerodi3;
        }
        if(numerodi4<numerodiset){
            numerodiset = numerodi4;
        }
        if(numerodi5<numerodiset){
            numerodiset = numerodi5;
        }
        if(numerodi6<numerodiset){
            numerodiset = numerodi6;
        }

        player.addPoints(numerodiset*5);
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
