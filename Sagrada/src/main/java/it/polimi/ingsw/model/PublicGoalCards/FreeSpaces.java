package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
//fatta da pon
public class FreeSpaces implements GoalCard {
    @Override
    public void calculatepoint(Player player) {
        try{
            for(int column=0; column<5; column++) {
                for (int row = 0; row < 4; row++) {
                    if(!player.getScheme().IsTileOccupied(row,column)){
                        player.addPoints(-1);
                    }

                }
            }
        }catch (OutOfMatrixException e){
            //impossibile, sto iterando correttamente
            //per cose del genere ho messo un iteratore nella clase SchemeCard, ma non l'ho mai usato
            //potremmo testarlo su una classe e poi usarlo ovunque se è il caso e ci troviamo bene
            //andatevelo a vedere grazie
        }catch (SchemeCardNotExistantException e){

        }

    }

    @Override
    public int getID() {
        return -1;
    }

    @Override
    public String getName() {
        return "Free Spaces";
    }

    @Override
    public String getDescription() {
        return "A point less for every blank space in your window.";
    }
}
