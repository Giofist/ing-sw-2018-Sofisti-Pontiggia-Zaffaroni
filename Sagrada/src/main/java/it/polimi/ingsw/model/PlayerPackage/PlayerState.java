package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.util.LinkedList;

public class PlayerState{
    protected LinkedList<TurnActions> actions;
    private State state;

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
            actions += "- ";
            actions += turnActions.toString().toLowerCase();
            actions +="\n";
        }
        System.out.println("OK "+actions);
        return actions;
    }

    public void updateState(State state) {
        this.state = state;
        this.actions = new LinkedList<>();
        switch (state){
            case ENDMATCHSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCHATTHEEND);
                break;
            case ERRORSTATE:
                break;
            case HASSETADICESTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                this.actions.addLast(TurnActions.PASSTURN);

                break;
            case HASUSEDATOOLCARDACTIONSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.SETDICE);
                break;
            case MATCHNOTSTARTEDYETSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCHBEFORESTARTING);
                break;
            case MUSTPASSTURNSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                break;
            case MUSTSSETDILUENTEPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                this.actions.addLast(TurnActions.SETTOOLCARDDICEINTENSITY);
                break;
            case MUSTSETPENNELLOPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                break;
            case NOTYOURTURNSTATE:
                this.actions.addLast(TurnActions.GETMAPS);
                break;
            case STARTTURNSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.GETMAPS);
                this.actions.addLast(TurnActions.SETDICE);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                break;
            case MUSTSETSCHEMECARD:
                this.actions.addLast(TurnActions.SETSCHEMECARD);
                break;
            case MATCHISSTARTEDSTATE:
                break;




        }
    }

    public State getState() {
        return state;
    }
}
