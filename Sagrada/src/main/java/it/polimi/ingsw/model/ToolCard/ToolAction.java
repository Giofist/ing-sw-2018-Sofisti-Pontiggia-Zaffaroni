package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

import java.io.Serializable;

public abstract  class ToolAction implements Serializable {
    protected int cost;
    protected int ID;


    /**
     * This method allows to execute the effect of a tool card
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    public abstract void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException;


    /**
     * @return The id of the tool card
     */
    public int getID(){
        return  this.ID;
    }


    /**
     * @return The cost of the tool card
     */
    public int getCost(){
        return this.cost;
    }


    /**
     * This method allows to set how much a tool card costs
     * @param cost The cost we want to assign to the tool card
     */
    public void setCost(int cost){
        this.cost = cost;
    }
}
