package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;

public interface ToolAction {

    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException;
    //DicenotExistant in DiluenteperPastaSlada
    public int getID();
    public  String getDescription();
    public  String getCardTitle();

}
