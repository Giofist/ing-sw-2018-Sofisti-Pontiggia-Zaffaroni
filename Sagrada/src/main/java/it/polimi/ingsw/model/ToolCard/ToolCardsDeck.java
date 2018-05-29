package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PublicGoalCards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


//questa classe è quella su cui ho lavorato di più ( pon)
// il pattern è il command, copiato pari pari da Wikipedia
//credo che per le nostre limitate esigenze si possa snellire il tutto,
//ma per ora funziona ed è chiara quindi ce la teniamo così
public class ToolCardsDeck {
    //array of costs of action
    private ArrayList<Integer> cardsID;
    private int[] costs;



    // constructor
    public ToolCardsDeck(){
        this.costs = new int[4];
        this.costs[0]=0;
        for (int i=1; i<=3; i++){
            this.costs[1] = 1;
        }
        this.cardsID = new ArrayList<>();
        for(int i =1; i<=12; i++){
            this.cardsID.add(i);
        }

        Collections.shuffle(cardsID);
        for(int i=1; i<=9; i++){
            this.cardsID.remove(0);
        }
        this.cardsID.add(0);
    }

    public int getCost(int toolcardID)throws WrongToolCardIDException{
        for(int ID: this.cardsID){
            if (ID  == toolcardID){
                return this.costs[toolcardID];
            }
        }
        throw new WrongToolCardIDException();
    }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int toolcardID) throws WrongToolCardIDException{
        for(int ID: this.cardsID){
            if (ID== toolcardID){
                if (this.costs[toolcardID] == 1){
                    this.costs[toolcardID]= 2;
                    return;
                }

                if(this.costs[toolcardID] == 0){
                    return;
                }
            }
        }
        throw new WrongToolCardIDException();

    }
    //command design pattern
    //questa classe fa pagare per la toolcard corrispondente e lancia un'eccezione se il giocatore è povero per quell'azione
    public void doAction(ToolAction toolAction, Player player, RequestClass requestClass) throws WrongToolCardIDException,ToolIllegalOperationException, NotEnoughSegnaliniException {
        player.payforTool( this.getCost(toolAction.getID()));
        setCostOfAction(toolAction.getID());
        for(int toolActionID: this.cardsID){
            if(toolAction.getID() == toolActionID){
                toolAction.execute(player, requestClass);
            }else
                throw new WrongToolCardIDException();

        }

    }

}
