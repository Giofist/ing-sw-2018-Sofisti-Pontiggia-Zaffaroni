package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.PlayerPackage.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


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
            case 6: this.deck.add(new PennelloperPastaSalda()); break;
            case 7: this.deck.add(new Martelletto()); break;
            case 8: this.deck.add(new TenagliaRotelle()); break;
            case 9: this.deck.add(new RigainSughero()); break;
            case 10: this.deck.add(new TamponeDiamantato()); break;
            case 11: this.deck.add(new DiluenteperPastaSalda());  break;
            case 12: this.deck.add(new TaglierinaManuale());  break;
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
        setCostOfAction(toolActionID);
        for(ToolAction toolAction: this.deck){
            if(toolAction.getID() == toolActionID) {
                toolAction.execute(player, toolRequestClass);
                player.payforToolAction( this.getCost(toolActionID));
                return;
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
    public List getcards(){
        List list = new LinkedList();
        for (ToolAction toolAction: this.deck) {
            list.add(toolAction);
        }
        return list;

    }

}
