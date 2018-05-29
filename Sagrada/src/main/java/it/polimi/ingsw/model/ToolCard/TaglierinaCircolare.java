package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaCircolareException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;


//per implementare questa classe bisogna prima pensare al traccaito round
public class TaglierinaCircolare  implements ToolAction {

    final static int ID = 5;
    final static String cardTitle = "Taglierina circolare";
    final static String description = "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato Round.\n";

    @Override
    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException {
        try {
            Dice RoundDicepooldice = player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedRoundDicepoolDiceIndex());
            Dice RoundTrackdice = player.getGametable().getRoundTrack().getroundTrackDices(requestClass.getRoundWhereThediceis()).getDice(requestClass.getSelectedRoundTrackDiceIndex());
            player.getGametable().getRoundTrack().getroundTrackDices(requestClass.getRoundWhereThediceis()).addDice(RoundDicepooldice);
            player.getGametable().getRoundDicepool().removeDice(requestClass.getSelectedRoundDicepoolDiceIndex());
            player.getGametable().getRoundDicepool().addDice(RoundTrackdice);
            player.getGametable().getRoundTrack().getroundTrackDices(requestClass.getRoundWhereThediceis()).removeDice(requestClass.getSelectedRoundTrackDiceIndex());
        }catch(RoundTrackException e){
            throw new TaglierinaCircolareException(TaglierinaCircolareException.getMsg() + e.getMessage());
        }catch (EmpyDicepoolException e){

        }

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle(){return cardTitle;}

    @Override
    public String getDescription(){
        return description;
    }

}
