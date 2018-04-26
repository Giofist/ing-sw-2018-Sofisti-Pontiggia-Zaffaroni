package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.IllegalOperationException;

public interface ToolAction {
    public void execute () throws IllegalOperationException;
    public int getID();
    public String getDescription();
}
