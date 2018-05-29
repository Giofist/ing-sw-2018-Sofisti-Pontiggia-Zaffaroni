package it.polimi.ingsw.model.Turn;

import java.util.LinkedList;

public class HasUsedAToolCardAction extends PlayerState {
    public HasUsedAToolCardAction(){
        this.actions = new LinkedList<>();
        this.actions.addLast(TurnActions.SETDICE);
        this.actions.addLast(TurnActions.PASSTURN);
    }
}
