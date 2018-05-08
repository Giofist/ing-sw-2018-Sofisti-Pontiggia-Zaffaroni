package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PrivateGoalCards.PrivateGoalCardDeck;
import it.polimi.ingsw.model.PublicGoalCards.PublicGoalCardDeck;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.SchemeCardDeck;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;

import java.io.IOException;
import java.util.List;



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
            this.getRoundDicepool().addDice(this.getRoundDicepool().extractDice());
        }
    }

    //per la gestione delle toolAction
    public int costofToolAction (int id){
        return this.tooldeck.getCost(id);
    }

    public void useaToolCard(ToolAction toolAction) throws Exception {
        this.tooldeck.doAction(toolAction);

    }


    //to get the private Goal card
    public GoalCard getPrivateGoalCard() throws PrivateGoalCardException {
        return this.privategoalcardsdeck.getCard();
    }

    // Get public goal cards
    public PublicGoalCardDeck getPublicGoalCardDeck() {
        return publicGoalCardDeck;
    }



    //to get IDs and description and names of the public goals
    public List getPublicGoalDescriptions() {
        return this.publicGoalCardDeck.getDescriptions();
    }
    public List getPublicGoalNames(){return this.publicGoalCardDeck.getCardsNames();};
    public List getPublicGoalIDs() {
        return this.publicGoalCardDeck.getIDs();
    }

    // Get functions useful for testing
    public ToolCardsDeck getToolCardsDeck() { return this.tooldeck; }
    public DicePool getDicepool() { return this.dicepool; }
    public DicePool getRoundDicepool() { return this.roundDicepool; }

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


