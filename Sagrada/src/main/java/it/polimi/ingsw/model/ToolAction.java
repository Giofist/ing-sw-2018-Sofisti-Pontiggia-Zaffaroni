package it.polimi.ingsw.model;

public interface ToolAction {
    public void execute () throws IllegalOperationException;
    public int getID();
    public String getDescription();
}
