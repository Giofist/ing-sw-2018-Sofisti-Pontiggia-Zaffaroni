package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureViola implements GoalCard {
    static private int ID = 5;
    static private String name = "Sfumature Blu";
    static private String description = "Somma dei valori su tutti i dadi blu.";


    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.VIOLET;
        for(int column=0; column<5; column++){
            for(int row=0; row<4; row++) {
                try{
                    if (player.getScheme().getDiceColour(row, column)== color) {
                        player.addPoints(player.getScheme().getDiceIntensity(row, column));
                    }
                }catch (DiceNotExistantException e){
                    //zorry, there is no dice
                }catch (OutOfMatrixException e){
                    //
                }catch (SchemeCardNotExistantException e){

                }
            }
        }
    }
    @Override
    public  int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public  String getDescription() {
        return description;
    }


}
