package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;

public interface ToolAction {

    public void execute () throws ToolIllegalOperationException;


    //DicenotExistant in DiluenteperPastaSlada
    public int getID();
    public String getDescription();
    public String getCardTitle();
}
