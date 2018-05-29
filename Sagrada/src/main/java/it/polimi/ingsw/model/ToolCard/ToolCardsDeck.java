package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PublicGoalCards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


//questa classe è quella su cui ho lavorato di più ( pon)
// il pattern è il command, copiato pari pari da Wikipedia
//credo che per le nostre limitate esigenze si possa snellire il tutto,
//ma per ora funziona ed è chiara quindi ce la teniamo così
public class ToolCardsDeck {
    //array of costs of action
    /*
    private ArrayList<Integer> cardsID;
    private int[] costs;



    // constructor
    public ToolCardsDeck(){
        this.costs = new int[13];
        this.costs[0]=0;
        for (int i=1; i<=12; i++){
            this.costs[1] = 1;
        }
        this.cardsID = new ArrayList<>();
        for(int i=1; i<=12;i++){
            this.cardsID.add(i);
        }

        Collections.shuffle(cardsID);
        addCardToDeck();
        addCardToDeck();
        addCardToDeck();
    }

    public void addCardToDeck(){
        int cardID = this.getValue();
        //questo  codice non compila e non compilerà per un bel po', quindi l'ho messo ocommentato
        switch (cardID){
            case 1:  this.deck.add(new PinzaSgrossatrice()); break;
            case 2 : this.deck.add(new PennelloperEglomise())); break;
            case 3 : this.deck.add(new AlesatoreperLaminadiRame()); break;
            case 4 : this.deck.add(new Lathekin());break;
            case 5 : this.deck.add(new TaglierinaCircolare());break;
            case 6 : this.deck.add(new PennelloperPastaSalda());break;
            case 7 : this.deck.add(new Martelletto());break;
            case 8 : this.deck.add(new TenagliaRotelle());break;
            case 9 : this.deck.add(new RigainSughero());break;
            case 10 : this.deck.add(new TamponeDiamantato());break;
            case 11 : this.deck.add(new DiluenteperPastaSalda()); break;
            case 12 : this.deck.add(new TaglierinaManuale()); break;
            default: break; // add an Exception here
        }

    }

    public int getCost(int id)throws WrongToolCardIDException{
        for (ToolAction toolAction: this.deck){
            if (toolAction.getID() == id){
                return toolAction.getCost();
            }
        }
        throw new WrongToolCardIDException();
    }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int id) throws WrongToolCardIDException{
        for(ToolAction toolAction: this.deck){
            if (toolAction.getID() == id){
                toolAction.setCost(2);
                return;
            }
        }
        throw new WrongToolCardIDException();

    }
    //command design pattern
    //questa classe fa pagare per la toolcard corrispondente e lancia un'eccezione se il giocatore è povero per quell'azione
    public void doAction(ToolAction toolAction, Player player) throws WrongToolCardIDException,ToolIllegalOperationException, NotEnoughSegnaliniException {
        player.payforTool( this.getCost(toolAction.getID()));
        setCostOfAction(toolAction.getID());
        for(int toolActionID: this.cardsID){
            if(toolAction.getID() == toolActionID){
                toolAction.execute(player);
            }

        }

    }

*/
}
