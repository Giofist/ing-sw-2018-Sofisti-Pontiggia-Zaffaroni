package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public interface ToolAction {
    public void execute () throws ToolIllegalOperationException;
    public int getID();
    public String getDescription();
}
