package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;

public abstract  class ToolAction {
    protected int cost;
    protected int ID;


    public abstract void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException;
    //DicenotExistant in DiluenteperPastaSlada
    public int getID(){
        return  this.ID;
    }
    public int getCost(){
        return this.cost;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
}
