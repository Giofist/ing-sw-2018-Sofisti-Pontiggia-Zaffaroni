package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.util.LinkedList;
import java.util.List;

public class NotYourTurnState extends PlayerState{


    public NotYourTurnState(){
        this.actions = new LinkedList();
        this.actions.addLast(TurnActions.GETMAPS);

    }





}
