package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaManualeException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

// DA TERMINARE
public class TaglierinaManuale  extends ToolAction implements Serializable{
    public TaglierinaManuale(){
        this.cost = 1;
        this.ID =12;
    }
    Dice removedDice;
    Dice removedDice2;
    @Override
    public void execute(Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            List<DiceColor> diceColors = player.getGametable().getRoundTrack().allColors();
            if (diceColors.contains(removedDice.getColor())){
                player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
                player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(),false,false,false);
                if(toolRequestClass.getNumberofDicesyouwanttomove() ==2){
                    removedDice2 = player.getScheme().getDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
                    if (removedDice2.getColor() == removedDice.getColor()){
                        player.getScheme().removeDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
                        player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false,false);
                    }else{
                        throw new TaglierinaManualeException("20.1");
                    }
                }
            }else{
                throw new TaglierinaManualeException("20.2");
            }

            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch (OutOfMatrixException e){
            throw new TaglierinaManualeException();
        }catch (DiceNotExistantException e){
            throw new TaglierinaManualeException();
        }catch (TileConstrainException e){
            try{
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(),false,false,false);
                player.getScheme().setDice(removedDice2, toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2(),false,false,false);
            }catch (Exception ecpt){ }
            throw new TaglierinaManualeException();
        }catch (RoundTrackException e){
            throw new TaglierinaManualeException();
        }catch (SchemeCardNotExistantException e){
            //
        }
    }

}
