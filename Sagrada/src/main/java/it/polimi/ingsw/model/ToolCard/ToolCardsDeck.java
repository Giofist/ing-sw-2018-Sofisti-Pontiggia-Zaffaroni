package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolActionNotIntheSetOfCostsException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;


//questa classe è quella su cui ho lavorato di più ( pon)
// il pattern è il command, copiato pari pari da Wikipedia
//credo che per le nostre limitate esigenze si possa snellire il tutto,
//ma per ora funziona ed è chiara quindi ce la teniamo così
public class ToolCardsDeck {
    //array of costs of action
    private int[] costs;


    // constructor
    public ToolCardsDeck(){
        this.costs = new int[13];
        costs[0] = 0;
        for (int costIndex = 1; costIndex < costs.length; costIndex++){
            costs[costIndex] = 1;
        }
    }

    public int getCost(int id){
        return this.costs[id];
        }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int id) throws ToolActionNotIntheSetOfCostsException{
        if(id <0 || id >12){
            throw new ToolActionNotIntheSetOfCostsException();
        }
        if(this.costs[id] == 1){
            this.costs[id] = 2;
        }
    }
    //command design pattern

    public void doAction(ToolAction toolAction, Player player) throws ToolIllegalOperationException, NotEnoughSegnaliniException {
                try{
                    player.payforTool( this.getCost(toolAction.getID()));
                    setCostOfAction(toolAction.getID());
                }catch (ToolActionNotIntheSetOfCostsException e){
                    //this toolaction has no cost
                }
                toolAction.execute(player);
        }
    }


