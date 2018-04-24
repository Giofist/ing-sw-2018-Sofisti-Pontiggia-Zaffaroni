package it.polimi.ingsw.model;
import java.util.*;


//questa classe è quella su cui ho lavorato di più ( pon)
// il pattern è il command, copiato pari pari da Wikipedia
//credo che per le nostre limitate esigenze si possa snellire il tutto,
//ma per ora funziona ed è chiara quindi ce la teniamo così
public class ToolCardsHandler {
    //array of costs of action
    private int[] costs;


    // constructor
    public ToolCardsHandler(){
        this.costs = new int[12];
        for (int cost: this.costs){
            cost = 1;
        }
    }
    public int getCost(int id){
        return this.costs[id];
    }
    public void setCostOfAction(int id){
        if(this.costs[id] == 1){
            this.costs[id] = 2;
        }
    }
    //command design pattern
    public void doAction(ToolAction toolAction) throws IllegalOperationException {

            try{
                setCostOfAction(toolAction.getID());
                toolAction.execute();
            }
            catch (IllegalOperationException e){
                throw e;
            }
        }
    }


