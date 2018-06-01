package it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class ToolCardsDeck {
    private ArrayList<Integer> cardsID;
    private LinkedList<ToolAction> deck;

    // constructor
    public ToolCardsDeck(){
        this.cardsID = new ArrayList<>();
        for(int i =1; i<=12; i++){
            this.cardsID.add(i);
        }
        this.deck = new LinkedList<>();
        Collections.shuffle(cardsID);
        addCardToDeck();
        addCardToDeck();
        addCardToDeck();
    }
    public void addCardToDeck() {
        int cardID = this.getValue();
        switch (cardID){
            case 1: this.deck.add(new PinzaSgrossatrice());  break;
            case 2: this.deck.add(new PennelloperEglomise()); break;
            case 3: this.deck.add(new AlesatoreperLaminadiRame()); break;
            case 4: this.deck.add(new Lathekin()); break;
            case 5: this.deck.add(new TaglierinaCircolare()); break;
            case 6: this.deck.add(new PennelloperPastaSalda()); this.deck.add(new PennelloPerPastaSalda2()); break;
            case 7: this.deck.add(new Martelletto()); break;
            case 8: this.deck.add(new TenagliaRotelle()); break;
            case 9: this.deck.add(new RigainSughero()); break;
            case 10: this.deck.add(new TamponeDiamantato()); break;
            case 11: this.deck.add(new DiluenteperPastaSalda()); this.deck.add(new DiluentePerPastaSalda2()); break;
            case 12: this.deck.add(new TaglierinaManuale()); this.deck.add(new TaglierinaManuale2()); break;
            default: break;
        }
    }


    public int getCost(int toolcardID)throws WrongToolCardIDException{
        for(ToolAction toolAction: this.deck){
            if (toolAction.getID()== toolcardID){
                return toolAction.getCost();
            }
        }
        throw new WrongToolCardIDException();
    }


    //dopo il primo utilizzo, il costo sale a due
    public void setCostOfAction(int toolcardID) throws WrongToolCardIDException{
        for(ToolAction toolAction: this.deck){
            if (toolAction.getID()== toolcardID){
                if(toolAction.getCost()==1){
                    toolAction.setCost(2);
                    return;
                }else if(toolAction.getCost()==0){
                    // le seconde toolcard sono a costo
                    return;
                }
            }
        }
        throw new WrongToolCardIDException();
    }
    //command design pattern
    //questa classe fa pagare per la toolcard corrispondente e lancia un'eccezione se il giocatore è povero per quell'azione
    public void doAction(int toolActionID, Player player, ToolRequestClass toolRequestClass) throws WrongToolCardIDException,ToolIllegalOperationException, NotEnoughSegnaliniException {
        player.payforTool( this.getCost(toolActionID));
        setCostOfAction(toolActionID);
        for(ToolAction toolAction: this.deck){
            if(toolAction.getID() == toolActionID) {
                toolAction.execute(player, toolRequestClass);
            }
        }
        throw new WrongToolCardIDException();
    }
    private int getValue(){
        int value =this.cardsID.get(0);
        this.cardsID.remove(0);
        return value;
    }

    //to get the IDs, descriptions and Names of public goal cards
    public String getIDs(){
        String IDs = "";
        for (ToolAction toolAction: this.deck) {
            IDs += toolAction.getID();
            IDs += "!";
        }
        return IDs;

    }
    public String getDescriptions(){
        String descriptions = "";
        for (ToolAction  toolAction: this.deck) {
            descriptions += toolAction.getDescription();
            descriptions += "!";
        }
        return descriptions;
    }

    public String getCardsTitles() {
        String names = "";
        for (ToolAction toolAction : this.deck) {
            names += toolAction.getCardTitle();
            names += "!";
        }
        return names;
    }

    public String getCardsCosts() {
        String names = " ";
        for (ToolAction toolAction : this.deck) {
            names += toolAction.getCost();
            names += "!";
        }
        return names;
    }
}
