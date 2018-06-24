package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

public interface  Translator {
    String translateMatch(Match match);
    String translatePlayer(Player player);

    String translatePrivateGoalCardDescription(int cardID);
    String translatePrivateGoalCardName(int cardID);
    String getToolCardDescription(int cardID);

    String getToolCardCardName(int cardID);

    String getPrivateGoalCardDescription(int cardID);

    String getPrivateGoalCardName(int cardID);

    String translateException(String exceptioncode);
    String translatePublicGoalCardDescription(int cardID);
    String translatePublicGoalCardName(int cardID);
    String translateSchemeCard(SchemeCard SchemeCard);
}
