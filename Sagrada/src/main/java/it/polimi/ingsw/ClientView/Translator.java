package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TurnActions;

/**
 * Interface for translator classes that returns names and descriptions of cards in the match
 */
public interface  Translator {
    String translateMatch(Match match);
    String translatePlayer(Player player);
    String translatePrivateGoalCardDescription(int cardID);
    String translatePrivateGoalCardName(int cardID);
    String translateToolCardDescription(int cardID);
    String translateToolCardCardName(int cardID);
    String translateException(String exceptioncode);
    String translatePublicGoalCardDescription(int cardID);
    String translatePublicGoalCardName(int cardID);
    String translateTurnAction(TurnActions turnActions);
    String detranslateTurnAction(String turnaction);
}
