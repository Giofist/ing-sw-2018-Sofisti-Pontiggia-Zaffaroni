package it.polimi.ingsw.model.Turn;

import java.util.LinkedList;

public class MustPassTurnState extends PlayerState {
    public MustPassTurnState(){
        this.actions = new LinkedList<>();
        this.actions.addLast(TurnActions.PASSTURN);
    }
}
