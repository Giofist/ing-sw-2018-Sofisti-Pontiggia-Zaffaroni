package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ToolCardsDeck {
    private ArrayList<Integer> cardsID;
    private LinkedList<ToolAction> deck;


    /**
     * This constructor returns a deck with 3 randomly extracted cards
     */
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


    /**
     * This method adds a new random card to the deck
     */
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


    /**
     * @param toolcardID The id of the tool card for which we want to receive the cost
     * @return The price of the tool card
     * @throws WrongToolCardIDException Exception thrown when an invalid ID is specified
     */
    public int getCost(int toolcardID)throws WrongToolCardIDException{
        for(ToolAction toolAction: this.deck){
            if (toolAction.getID()== toolcardID){
                return toolAction.getCost();
            }
        }
        throw new WrongToolCardIDException();
    }


    /**
     * Method for updating the cost of a tool card when it's used during a match
     * @param toolcardID The id of the tool card for which we want to update the cost when used
     * @throws WrongToolCardIDException Exception thrown when an invalid ID is specified
     */
    public void setCostOfAction(int toolcardID) throws WrongToolCardIDException{
        for(ToolAction toolAction: this.deck){
            if (toolAction.getID()== toolcardID){
                if(toolAction.getCost()==1){
                    toolAction.setCost(2);
                    return;
                }else if(toolAction.getCost()==0){
                    return;
                }
            }
        }
        throw new WrongToolCardIDException();
    }


    /**
     * Here we use the command pattern for performing the action of a tool card
     * @param toolActionID The id of the tool card that we want to use
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card (see each tool card for the necessary parameters that needs to be set)
     * @throws WrongToolCardIDException Exception thrown when an invalid ID is specified
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     * @throws NotEnoughSegnaliniException Exception thrown when a player is not able to pay for the selected tool card
     */
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


    /**
     * This method returns a random id associated to a tool card. Once we get one value we won't be able to receive the same
     * value again
     * @return The random id
     */
    private int getValue(){
        int value = this.cardsID.get(0);
        this.cardsID.remove(0);
        return value;
    }

    /**
     * @return A list with all the tool cards available
     */
    public List getcards(){
        List list = new LinkedList();
        for (ToolAction toolAction: this.deck) {
            list.add(toolAction);
        }
        return list;

    }

}
