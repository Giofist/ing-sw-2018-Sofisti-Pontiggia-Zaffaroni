package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public interface ToolAction {

    public void execute () throws Exception;

    public int getID();
    public String getDescription();
    public String getCardTitle();
}
