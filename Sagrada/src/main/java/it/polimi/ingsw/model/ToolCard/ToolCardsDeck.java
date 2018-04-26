package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;


//questa classe è quella su cui ho lavorato di più ( pon)
// il pattern è il command, copiato pari pari da Wikipedia
//credo che per le nostre limitate esigenze si possa snellire il tutto,
//ma per ora funziona ed è chiara quindi ce la teniamo così
public class ToolCardsDeck {
    //array of costs of action
    private int[] costs;


    // constructor
    public ToolCardsDeck(){
        this.costs = new int[12];
        for (int cost: this.costs){
            cost = 1;
        }
    }
    public int getCost(int id){
        return this.costs[id];
    }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int id){
        if(this.costs[id] == 1){
            this.costs[id] = 2;
        }
    }
    //command design pattern
    public void doAction(ToolAction toolAction) throws ToolIllegalOperationException {
                setCostOfAction(toolAction.getID());
                toolAction.execute();
        }
    }


