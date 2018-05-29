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


    public List<TurnActions> getActions() throws NoActionAllowedException {
        return this.actions;
    }
}
