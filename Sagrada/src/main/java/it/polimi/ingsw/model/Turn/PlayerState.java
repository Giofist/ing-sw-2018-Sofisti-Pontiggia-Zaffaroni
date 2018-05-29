package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.util.LinkedList;
import java.util.List;

public class PlayerState {
    protected LinkedList<TurnActions> actions;

    public void checkAction(TurnActions action) throws NotAllowedActionException {
        for (TurnActions allowedactions: this.actions){
            if (allowedactions.equals(action))
                return;
        }
        throw new NotAllowedActionException();
    }


    public String getActions() throws NoActionAllowedException {
        if(this.actions.size() ==0){
            throw new NoActionAllowedException();
        }
        String actions = "";
        for(TurnActions turnActions: this.actions){
            actions += turnActions.toString();
        }
        return actions;
    }
}
