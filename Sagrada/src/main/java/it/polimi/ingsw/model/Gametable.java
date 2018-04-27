package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PrivateGoalCards.PrivateGoalCardDeck;
import it.polimi.ingsw.model.PublicGoalCards.PublicGoalCardDeck;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.SchemeCardDeck;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;

import java.io.IOException;
import java.util.List;


//Gametable non deve esporre la propria implementazione, ma deve fare tutto da sè
public class Gametable {
    private ToolCardsDeck tooldeck;
    private DicePool dicepool;
    private DicePool roundDicepool;//dicepool of the current round
    private PrivateGoalCardDeck privategoalcardsdeck;
    private PublicGoalCardDeck publicGoalCardDeck;
    private SchemeCardDeck schemeCardDeck;

    //constructor
    public Gametable() throws IOException {
        prepareGame();
    }
    //first to do when preparing a game
    private void prepareGame() throws IOException {
        this.dicepool = new DicePool();
        this.tooldeck = new ToolCardsDeck();
        this.privategoalcardsdeck = new PrivateGoalCardDeck();
        this.publicGoalCardDeck = new PublicGoalCardDeck();
        this.schemeCardDeck = new SchemeCardDeck();
    }

    //to do when preparing a round
    public void setupRound(int numberPlayers) {
        this.roundDicepool = new DicePool();
        for (int i = 1; i < numberPlayers * 2 + 1; i++) {
            this.roundDicepool.addDice(this.dicepool.extractDice());
        }
    }

    //per la gestione delle toolAction
    public int costofToolAction (int id){
        return this.tooldeck.getCost(id);
    }
    public void useaToolCard(ToolAction toolAction) throws ToolIllegalOperationException{
       this.tooldeck.doAction(toolAction);
    }

    //to get the private Goal card
    public GoalCard getPrivateGoalCard() throws PrivateGoalCardException {
        return this.privategoalcardsdeck.getCard();
    }


    //asking for a dice in the dicepool of 90 dices
    public Dice getDice() {
        return this.dicepool.extractDice();
    }

    //asking for a specific dice in the round
    public Dice getRoundDice(int position) {
        return this.roundDicepool.getDice(position);
    }

    //to get IDs and description and names of the public goals
    public List<String> getPublicGoalDescriptions() {
        return this.publicGoalCardDeck.getDescriptions();
    }
    public List<String> getPublicGoalNames(){return this.publicGoalCardDeck.getCardsNames();};
    public List getPublicGoalIDs() {
        return this.publicGoalCardDeck.getIDs();
    }


    // to get a scheme card
    public SchemeCard getSchemeCard() throws IOException, MapConstrainReadingException {
        return this.schemeCardDeck.getCard();
    }

    //to calculate all points for all players
    public void calculatePointsforAllPlayers(List<Player> players){
        for (Player player:  players) {
            this.publicGoalCardDeck.doCalculatePoints(player);
        }
    }
}


