package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.util.LinkedList;
import java.util.List;

public class StartTurnState extends PlayerState {

    public StartTurnState(){
        this.actions = new LinkedList<>();
        //all'inizio del turno si può fare quasi ogni cosa
        for (TurnActions action: TurnActions.values())
            this.actions.add(action);

    }

}
