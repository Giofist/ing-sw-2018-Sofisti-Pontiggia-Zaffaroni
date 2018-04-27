package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.TileException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public interface ToolAction {
<<<<<<< HEAD
    public void execute () throws ToolIllegalOperationException, TileException;
=======
    public void execute () throws ToolIllegalOperationException;
>>>>>>> 0b9e2f6ef25f97857c8719fe848c7c3bd461c939
    public int getID();
    public String getDescription();
    public String getCardTitle();
}
