package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolActionNotIntheSetOfCostsException;
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
        for (int costIndex = 0; costIndex < costs.length; costIndex++){
            costs[costIndex] = 1;
        }
    }

    public int getCost(int id){
    if(id<0){
        return 0;}
        else{
            return this.costs[id];
        }
    }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int id) throws ToolActionNotIntheSetOfCostsException{
        if(id <0){
            throw new ToolActionNotIntheSetOfCostsException();
        }
        if(this.costs[id] == 1){
            this.costs[id] = 2;
        }
    }
    //command design pattern
    //here a bit lazy: Exception is too generic and does not help with understanding and handling the problem of exceptions
    public void doAction(ToolAction toolAction) throws Exception {
                try{
                    setCostOfAction(toolAction.getID());
                }catch (ToolActionNotIntheSetOfCostsException e){
                    //this toolaction has no cost
                }
                toolAction.execute();
        }
    }


