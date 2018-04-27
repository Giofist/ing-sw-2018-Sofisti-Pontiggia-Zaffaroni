package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public interface ToolAction {
    public void execute () throws ToolIllegalOperationException, IllegalOperationException;
    public int getID();
    public String getDescription();
    public String getCardTitle();
}
