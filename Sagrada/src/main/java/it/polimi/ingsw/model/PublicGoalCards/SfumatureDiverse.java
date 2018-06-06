package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

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
        try{
            for(Tile tile: player.getScheme()) {
                try {
                    switch(tile.getDice().getIntensity()){
                        case 1:  numerodi1++; break;
                        case 2: numerodi2++; break;
                        case 3: numerodi3++; break;
                        case 4: numerodi4++; break;
                        case 5: numerodi5++; break;
                        case 6: numerodi6++; break;
                        default:
                    }
                } catch (Exception e) {
                    //no dice, no point, zorry
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }
        int numerodiset=numerodi1; // numerodiset indica il numero dei set presenti, che è anche uguale a min( numerodix) per x = 1,2,3,4,5,6.
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
